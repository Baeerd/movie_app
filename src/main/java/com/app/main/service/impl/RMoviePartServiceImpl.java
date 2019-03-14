package com.app.main.service.impl;

import com.app.common.entity.Constant;
import com.app.common.util.BeanUtils;
import com.app.common.util.LoginUtil;
import com.app.main.entity.MainDataVo;
import com.app.movie.entity.Movie;
import com.app.movie.entity.MovieVo;
import com.app.movie.mapper.MovieMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.common.service.impl.BaseServiceImpl;
import com.app.main.entity.RMoviePart;
import com.app.main.mapper.RMoviePartMapper;
import com.app.main.service.RMoviePartService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class RMoviePartServiceImpl extends BaseServiceImpl<RMoviePart> implements RMoviePartService {

    @Autowired
    private RMoviePartMapper rMoviePartMapper;

    @Autowired
    private MovieMapper movieMapper;

    @Override
    public List<MainDataVo> getMovieData(Map<String, String> param) {
        return rMoviePartMapper.getMovieData(param);
    }

    @Override
    public List<MovieVo> findFilterAll(String partId) {
        return filterMovieList(movieMapper.find(null), partId);
    }

    @Override
    public void saveOption(Map<String, String> dataMap) {
        // 先删除影院的对应关系
        rMoviePartMapper.deleteByPartId(dataMap.get("partId"));
        String[] movieIds = dataMap.get("movieId").split(",");
        for (String movieId : movieIds) {
            RMoviePart rMoviePart = new RMoviePart();
            rMoviePart.setPartId(dataMap.get("partId"));
            rMoviePart.setPrice(dataMap.get("price"));
            rMoviePart.setShowNo(dataMap.get("showNo"));
            rMoviePart.setMovieId(movieId);
            rMoviePart.setCreatedDt(new Date());
            rMoviePart.setCreatedBy(LoginUtil.getUserName());
            fillMoviePart(dataMap, rMoviePart);

            rMoviePartMapper.insert(rMoviePart);
        }

    }

    /**
     * 根据规则填充主数据(场次起止时间)
     * @param dataMap
     * @param rMoviePart
     */
    private void fillMoviePart(Map<String, String> dataMap, RMoviePart rMoviePart) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String showNo = dataMap.get("showNo");
        String startDate = dataMap.get("startDate");
        String[] times = Constant.SHOW_NO_MAP.get(showNo);
        try {
            rMoviePart.setShowStart(format.parse(startDate + " " + times[0]));
            rMoviePart.setShowEnd(format.parse(startDate + " " + times[1]));
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    private List<MovieVo> filterMovieList(List<Movie> movies, String partId) {
        if(movies == null || movies.size() == 0) {
            return new ArrayList<>();
        }
        List<MovieVo> resultList = new ArrayList<>();
        for (Movie movie : movies) {
            MovieVo movieVo = new MovieVo();
            BeanUtils.copyProperties(movieVo, movie);
            // 判断是否属于当前影院
            if(isCurrentPart(movie, partId)) {
                movieVo.setIsCurentPart("1");
            } else {
                movieVo.setIsCurentPart("0");
            }
            resultList.add(movieVo);
        }
        return resultList;
    }

    /**
     * 判断是否属于当前影院
     * @param movie
     * @param partId
     * @return
     */
    private boolean isCurrentPart(Movie movie, String partId) {
        if(StringUtils.isEmpty(partId)) {
            return false;
        }
        List<Long> partIds = rMoviePartMapper.findPartIdsByMovieId(movie.getId());
        return partIds.contains(Long.valueOf(partId));
    }
}
