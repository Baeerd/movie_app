package com.app.order.controller;

import com.app.comment.entity.Comment;
import com.app.common.entity.Constant;
import com.app.common.entity.PageModel;
import com.app.common.entity.Response;
import com.app.common.util.LoginUtil;
import com.app.common.util.Util;
import com.app.main.entity.MainDataVo;
import com.app.main.service.RMoviePartService;
import com.app.movie.entity.Movie;
import com.app.movie.service.MovieService;
import com.app.order.service.MovieOrderService;
import com.app.part.entity.MoviePart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import com.app.common.controller.BaseController;
import com.app.order.entity.MovieOrder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Scope("prototype")
@RequestMapping("/order")
public class MovieOrderController extends BaseController<MovieOrder>{

    @Autowired
    private MovieOrderService movieOrderService;

    @Autowired
    private RMoviePartService rMoviePartService;

    @Autowired
    private MovieService movieService;

    @RequestMapping("/orderList")
    public ModelAndView commentList(HttpServletRequest request) {
        String json = getJsonFromRequest(request);
        Map<String, String> params = Util.jsonToMap(json);
        // 判断是否是管理员，如果不是管理员，则只能查看自己的订单
        if(!LoginUtil.isAdmin()) {
            params.put("createdBy", LoginUtil.getUserName());
        }
        PageModel<MovieOrder> page = movieOrderService.findOrderList(params);
        ModelAndView modelAndView = new ModelAndView("order/orderList");
        modelAndView.addObject("page", page);
        // 回显查询条件
        if(params.get("orderSearch") != null && !"".equals(params.get("orderSearch"))) {
            modelAndView.addObject("orderSearch", params.get("orderSearch"));
        }
        return modelAndView;
    }

    @RequestMapping("/addOrder")
    public ModelAndView addOrder(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("/order/addOrder");
        String paramJson = super.getJsonFromRequest(request);
        Map<String, String> param = Util.jsonToMap(paramJson);
        // 获取拥有电影的所有影院，包含场次信息
        List<MainDataVo> partList = rMoviePartService.findPartByMovieId(param.get("movieId"));
        modelAndView.addObject("partList", partList);

        // 获取电影详细信息
        Map<String, String> movieParam = new HashMap<>();
        movieParam.put("id", param.get("movieId"));
        List<Movie> movieList = movieService.findByParam(movieParam);
        if(movieList != null && movieList.size() > 0) {
            modelAndView.addObject("movie", movieList.get(0));
        }
        return modelAndView;
    }

    /**
     * 保存订单信息
     * @param request
     * @return
     */
    @RequestMapping("/saveOrder")
    public Response saveOrder(HttpServletRequest request) {
        String json = super.getJsonFromRequest(request);
        Map<String, String> map = Util.jsonToMap(json);
        Long orderId = rMoviePartService.saveOrder(map);
        request.getSession().setAttribute(Constant.USER_ORDER_LIST, movieOrderService.findOrderForIndex());
        return new Response().success(orderId);
    }

    /**
     * 订单详细信息页面
     * @return
     */
    @RequestMapping("/orderDetail")
    public ModelAndView orderDetail(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("/order/orderDetail");
        String json = super.getJsonFromRequest(request);
        Map<String, String> params = Util.jsonToMap(json);
        MainDataVo mainData = movieOrderService.getOrderDataById(params.get("id"));
        modelAndView.addObject("mainData", mainData);
        return modelAndView;
    }
}
