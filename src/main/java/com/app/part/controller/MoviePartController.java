package com.app.part.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import com.app.common.controller.BaseController;
import com.app.part.entity.MoviePart;

import java.util.List;
import java.util.Map;

@Controller
@Scope("prototype")
@RequestMapping("/moviePart")
public class MoviePartController extends BaseController<MoviePart>{

}
