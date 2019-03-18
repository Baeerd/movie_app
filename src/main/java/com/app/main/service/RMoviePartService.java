package com.app.main.service;

import com.app.common.service.BaseService;
import com.app.main.entity.MainDataVo;
import com.app.main.entity.RMoviePart;
import com.app.movie.entity.MovieVo;
import com.app.part.entity.MoviePart;

import java.util.List;
import java.util.Map;

public interface RMoviePartService extends BaseService<RMoviePart>{

    /**
     * 获取电影相关数据
     * @param param
     * @return
     */
    List<MainDataVo> getMovieData(Map<String, String> param);

    /**
     * 获取所有影视数据，带是否所属影院标识
     * @return
     * @param partId
     */
    List<MovieVo> findFilterAll(String partId);

    /**
     * 保存影院配置信息
     * @param dataMap
     */
    void saveOption(Map<String, String> dataMap);

    /**
     * 通过movieId查询拥有此电影的所有影院信息
     * @param movieId
     * @return
     */
    List<MoviePart> findPartByMovieId(String movieId);
}
