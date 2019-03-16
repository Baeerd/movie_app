package com.app.part.controller;

import com.app.common.entity.PageModel;
import com.app.common.util.Util;
import com.app.part.service.MoviePartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.common.controller.BaseController;
import com.app.part.entity.MoviePart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@Scope("prototype")
@RequestMapping("/moviePart")
public class MoviePartController extends BaseController<MoviePart>{

    @Autowired
    private MoviePartService moviePartService;

    /**
     * 跳转到影院浏览页面
     * @return
     */
    @RequestMapping("/partListPanel")
    public ModelAndView movieListPanel(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("/part/partListPanel");
        String jsonFromRequest = super.getJsonFromRequest(request);
        Map<String, String> param = Util.jsonToMap(jsonFromRequest);
        param.put("pageSize", "6");// 每页6个
        PageModel<MoviePart> page = moviePartService.findByPage(param);
        modelAndView.addObject("page", page);
        return modelAndView;
    }
}
