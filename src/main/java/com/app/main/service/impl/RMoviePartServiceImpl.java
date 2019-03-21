package com.app.main.service.impl;

import com.app.common.entity.Constant;
import com.app.common.exception.MessageException;
import com.app.common.util.BeanUtils;
import com.app.common.util.LoginUtil;
import com.app.common.util.Util;
import com.app.main.entity.MainDataVo;
import com.app.movie.entity.Movie;
import com.app.movie.entity.MovieVo;
import com.app.movie.mapper.MovieMapper;
import com.app.order.entity.MovieOrder;
import com.app.order.service.MovieOrderService;
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

    @Autowired
    private MovieOrderService movieOrderService;

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
    public List<MainDataVo> findPartByMovieId(String movieId) {
        List<MainDataVo> mainDataList = rMoviePartMapper.findPartByMovieId(movieId);
        for (MainDataVo mainDataVo : mainDataList) {
            filtMainData(mainDataVo);
        }
        return mainDataList;
    }

    /**
     * 添加场次显示时间
     * @param mainDataVo
     */
    private void filtMainData(MainDataVo mainDataVo) {
        Date showStart = mainDataVo.getShowStart();
        String showNo = mainDataVo.getShowNo();
        String[] times = Constant.SHOW_NO_MAP.get(showNo);
        mainDataVo.setShowTime(Util.formatDate(showStart) + " " + times[0]);
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

    @Override
    public Long saveOrder(Map<String, String> map) {
        String rMovieId = map.get("rMovieId");
        String ids = map.get("ids");
        String placeNos = map.get("placeNos");
        String totalPrice = map.get("totalPrice");
        if(StringUtils.isEmpty(rMovieId) || StringUtils.isEmpty(placeNos)) {
            throw new MessageException("场次和座位号不允许为空");
        }
        // 保存订单信息
        Long orderId = saveOrder(rMovieId, placeNos, totalPrice);
        // 修改座位占用信息
        updatePlace(ids);
        return orderId;
    }

    /**
     * 保存订单信息
     * @param rMovieId
     */
    private Long saveOrder(String rMovieId, String placeNos, String totalPrice) {
        String[] placeNoList = placeNos.split(",");
        MovieOrder order = new MovieOrder();
        order.setCreatedDt(new Date());
        order.setCreatedBy(LoginUtil.getUserName());
        order.setNum(Integer.toString(placeNoList.length));
        order.setRMovieId(rMovieId);
        order.setState("1");// 已支付
        order.setTotalPrice(totalPrice);
        // 订单号生成规则：用户名+年月日时分秒(yyyyMMddHHmmss)(admin20180808120000)
        order.setOrderNo(LoginUtil.getUserName()+Util.formatDate(new Date(), "yyyyMMddHHmmss"));
        order.setPlaceNo(placeNos.substring(0,placeNos.length()-1));// 座位号，格式 01,02,03
        movieOrderService.insert(order);
        return order.getId();
    }

    /**
     * 修改座位占用信息
     * @param ids
     */
    private void updatePlace(String ids) {
        String[] idList = ids.split(",");
        for (String id : idList) {
            placeMapper.updateIsUseById(id);
        }
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
