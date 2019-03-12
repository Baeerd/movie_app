package com.app.part.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.app.common.mapper.BaseMapper;
import com.app.part.entity.MoviePart;

import java.util.List;
import java.util.Map;

@Mapper
public interface MoviePartMapper extends BaseMapper<MoviePart>{

}
