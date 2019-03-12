package com.app.main.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import com.app.common.controller.BaseController;
import com.app.main.entity.RMoviePart;

import java.util.List;
import java.util.Map;

@Controller
@Scope("prototype")
@RequestMapping("/rMoviePart")
public class RMoviePartController extends BaseController<RMoviePart>{

}
