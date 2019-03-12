package com.app.movie.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import com.app.common.controller.BaseController;
import com.app.movie.entity.Movie;

import java.util.List;
import java.util.Map;

@Controller
@Scope("prototype")
@RequestMapping("/movie")
public class MovieController extends BaseController<Movie>{

}
