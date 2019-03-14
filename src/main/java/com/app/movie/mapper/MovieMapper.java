package com.app.movie.mapper;

import com.app.movie.entity.MovieVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.app.common.mapper.BaseMapper;
import com.app.movie.entity.Movie;

import java.util.List;
import java.util.Map;

@Mapper
public interface MovieMapper extends BaseMapper<Movie>{

}
