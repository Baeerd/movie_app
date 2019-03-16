package com.app.main.mapper;

import com.app.main.entity.MainDataVo;
import org.apache.ibatis.annotations.Mapper;

import com.app.common.mapper.BaseMapper;
import com.app.main.entity.RMoviePart;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface RMoviePartMapper extends BaseMapper<RMoviePart>{

    List<MainDataVo> getMovieData(Map<String, String> param);

    List<Long> findPartIdsByMovieId(@Param("movieId") Long movieId);

    void deleteByPartId(@Param("partId") String partId);

    void updateIsGrounding(Map<String, Object> params);
}
