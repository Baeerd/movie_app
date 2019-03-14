package com.app.main.controller;

import com.app.common.entity.Response;
import com.app.common.util.Util;
import com.app.main.entity.MainDataVo;
import com.app.main.service.RMoviePartService;
import com.app.movie.entity.Movie;
import com.app.movie.entity.MovieVo;
import com.app.movie.service.MovieService;
import com.app.part.entity.MoviePart;
import com.app.part.service.MoviePartService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import com.app.common.controller.BaseController;
import com.app.main.entity.RMoviePart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@Scope("prototype")
@RequestMapping("/main")
public class RMoviePartController extends BaseController<RMoviePart>{

    @Autowired
    private RMoviePartService rMoviePartService;

    @Autowired
    private MoviePartService moviePartService;

    @Autowired
    private MovieService movieService;

    /**
     * 跳转到影院设置页面
     * @param request
     * @return
     */
    @RequestMapping("/option")
    public ModelAndView option(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("/part/option");
        String json = super.getJsonFromRequest(request);
        Map<String, String> param = Util.jsonToMap(json);
        if(StringUtils.isNotEmpty(param.get("partId"))) {
            // 回显影院下拉框
            modelAndView.addObject("partId", param.get("partId"));
        }
        // 加载所有影院信息
        List<MoviePart> partList = moviePartService.findAll();
        modelAndView.addObject("partList", partList);

        // 加载电影数据
        List<MovieVo> movieList = rMoviePartService.findFilterAll(param.get("partId"));
        modelAndView.addObject("movieList", movieList);

        return modelAndView;
    }

    /**
     * 获取电影影院相关信息
     * @param request
     * @return
     */
    @RequestMapping("/getMovieData")
    public Response getMovieData(HttpServletRequest request) {
        String json = super.getJsonFromRequest(request);
        Map<String, String> param = Util.jsonToMap(json);
        List<MainDataVo> mainDataList = rMoviePartService.getMovieData(param);
        return new Response().success(mainDataList);
    }

    /**
     * 保存影院配置
     * @return
     */
    @RequestMapping("/saveOption")
    public Response saveOption(HttpServletRequest request) {
        String jsonStr = getJsonFromRequest(request);
        Map<String, String> dataMap = Util.jsonToMap(jsonStr);
        rMoviePartService.saveOption(dataMap);
        return new Response().success();
    }
}
