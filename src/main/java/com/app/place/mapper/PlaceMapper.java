package com.app.place.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.app.common.mapper.BaseMapper;
import com.app.place.entity.Place;

import java.util.List;
import java.util.Map;

@Mapper
public interface PlaceMapper extends BaseMapper<Place>{

}
