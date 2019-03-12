package com.app.order.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.app.common.mapper.BaseMapper;
import com.app.order.entity.MovieOrder;

import java.util.List;
import java.util.Map;

@Mapper
public interface MovieOrderMapper extends BaseMapper<MovieOrder>{

}
