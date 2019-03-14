package com.app.movie.service.impl;

import com.app.common.util.BeanUtils;
import com.app.movie.entity.MovieVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.common.service.impl.BaseServiceImpl;
import com.app.movie.entity.Movie;
import com.app.movie.mapper.MovieMapper;
import com.app.movie.service.MovieService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MovieServiceImpl extends BaseServiceImpl<Movie> implements MovieService {

    @Autowired
    private MovieMapper movieMapper;

}
