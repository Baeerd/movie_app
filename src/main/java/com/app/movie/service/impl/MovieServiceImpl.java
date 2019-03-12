package com.app.movie.service.impl;

import org.springframework.stereotype.Service;

import com.app.common.service.impl.BaseServiceImpl;
import com.app.movie.entity.Movie;
import com.app.movie.mapper.MovieMapper;
import com.app.movie.service.MovieService;

import java.util.List;
import java.util.Map;

@Service
public class MovieServiceImpl extends BaseServiceImpl<Movie> implements MovieService {

}
