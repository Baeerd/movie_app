package com.app.order.mapper;

import com.app.order.entity.MovieOrderVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.app.common.mapper.BaseMapper;
import com.app.order.entity.MovieOrder;

import java.util.List;
import java.util.Map;

@Mapper
public interface MovieOrderMapper extends BaseMapper<MovieOrder>{

    List<MovieOrder> findOrderForIndex(@Param("username") String username);

    List<MovieOrderVo> findOrderList(Map<String, String> params);

    Integer findOrderListCount(Map<String,String> params);
}
