package com.app.main.service.impl;

import com.app.common.entity.Constant;
import com.app.common.util.BeanUtils;
import com.app.common.util.LoginUtil;
import com.app.main.entity.MainDataVo;
import com.app.movie.entity.Movie;
import com.app.movie.entity.MovieVo;
import com.app.movie.mapper.MovieMapper;
import com.app.part.entity.MoviePart;
import com.app.place.entity.Place;
import com.app.place.mapper.PlaceMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.common.service.impl.BaseServiceImpl;
import com.app.main.entity.RMoviePart;
import com.app.main.mapper.RMoviePartMapper;
import com.app.main.service.RMoviePartService;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RMoviePartServiceImpl extends BaseServiceImpl<RMoviePart> implements RMoviePartService {

    @Autowired
    private RMoviePartMapper rMoviePartMapper;

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private PlaceMapper placeMapper;

    @Override
    public List<MainDataVo> getMovieData(Map<String, String> param) {
        return rMoviePartMapper.getMovieData(param);
    }

    @Override
    public List<MovieVo> findFilterAll(String partId) {
        return filterMovieList(movieMapper.find(null), partId);
    }

    /**
     *  逻辑：
     *  查询影院电影场次配置表是否存在数据
     *  如果不存在，添加（上架状态为上架）
     *  如果存在，查看此次配置中是否要下架此场次，如果要下架，改上架状态，否则不操作
     * @param dataMap
     */
    @Override
    public void saveOption(Map<String, String> dataMap) {
        // 查询影院电影场次配置表是否存在数据
        List<RMoviePart> moviePartList = getMoviePartList(dataMap.get("partId"));

        String[] movieIds = dataMap.get("movieId").split(",");
        List<Long> Ids = moviesNotInData(moviePartList, movieIds, dataMap);
        if(Ids != null && Ids.size() > 0) {
            // 将数据库中不包含的电影置为下架
            Map<String, Object> params = new HashMap<>();
            params.put("ids", Ids);
            params.put("isGrounding", "0");
            rMoviePartMapper.updateIsGrounding(params);
        }

        for (String movieId : movieIds) {
            if(StringUtils.isEmpty(dataMap.get("movieId")) || StringUtils.isEmpty(dataMap.get("partId"))) {
                return;
            }
            RMoviePart rMoviePart = new RMoviePart();
            rMoviePart.setPartId(dataMap.get("partId"));
            rMoviePart.setPrice(dataMap.get("price"));
            rMoviePart.setShowNo(dataMap.get("showNo"));
            rMoviePart.setMovieId(movieId);
            rMoviePart.setCreatedDt(new Date());
            rMoviePart.setCreatedBy(LoginUtil.getUserName());
            rMoviePart.setIsGrounding("1");// 已上架
            fillMoviePart(dataMap, rMoviePart);

            if(isExists(moviePartList, rMoviePart)) {
                // 存在
                Long id = 0L;
                for (RMoviePart moviePart : moviePartList) {
                    if(moviePart.equals(rMoviePart)) {
                        id = moviePart.getId();
                    }
                }
                rMoviePart.setId(id);
                rMoviePartMapper.update(rMoviePart);
            } else {
                // 添加影院电影场次关系表时，同时添加座位表信息（定位每个场次的电影都是50个座位）
                rMoviePartMapper.insert(rMoviePart);
                for(int i=1; i<=50; i++) {
                    Place place = new Place();
                    place.setCreatedBy(LoginUtil.getUserName());
                    place.setCreatedDt(new Date());
                    place.setIsUse("0");
                    place.setPlaceNo(i+"");
                    place.setRMovieId(rMoviePart.getId().toString());
                    placeMapper.insert(place);
                }
            }

        }

    }

    @Override
    public List<MoviePart> findPartByMovieId(String movieId) {
        return rMoviePartMapper.findPartByMovieId(movieId);
    }

    /**
     * 获取数据库中不存在的movie id
     * @param moviePartList
     * @param movieIds
     * @param dataMap
     * @return
     */
    private List<Long> moviesNotInData(List<RMoviePart> moviePartList, String[] movieIds, Map<String, String> dataMap) {
        String partId = dataMap.get("partId");
        String showNo = dataMap.get("showNo");
        Date startDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            startDate = format.parse(dataMap.get("startDate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Long> moviesNotInData = new ArrayList<>();
        List<String> movieIdsList = Arrays.asList(movieIds);
        for (RMoviePart rMoviePart : moviePartList) {
            if(!movieIdsList.contains(rMoviePart.getMovieId()) && partId.equals(rMoviePart.getPartId()) && showNo.equals(rMoviePart.getShowNo()) && startDate.equals(rMoviePart.getShowStart())) {
                moviesNotInData.add(rMoviePart.getId());
            }
        }
        return moviesNotInData;
    }

    /**
     * 判断影院电影场次配置表是否存在数据
     * @param moviePartList
     * @param rMoviePart
     * @return
     */
    private boolean isExists(List<RMoviePart> moviePartList, RMoviePart rMoviePart) {
        return moviePartList.contains(rMoviePart);
    }

    /**
     * 获取配置表map
     * @param partId
     * @return
     */
    private List<RMoviePart> getMoviePartList(String partId) {
        Map<String, String> param = new HashMap<>();
        param.put("partId", partId);
        List<RMoviePart> rMovieParts = rMoviePartMapper.find(param);
        return rMovieParts;
    }

    /**
     * 根据规则填充主数据(场次起止时间)
     * @param dataMap
     * @param rMoviePart
     */
    private void fillMoviePart(Map<String, String> dataMap, RMoviePart rMoviePart) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String showNo = dataMap.get("showNo");
        String startDate = dataMap.get("startDate");
        try {
            rMoviePart.setShowStart(format.parse(startDate));
            rMoviePart.setShowEnd(format.parse(startDate));
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
