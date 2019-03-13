package com.app.order.controller;

import com.app.comment.entity.Comment;
import com.app.common.entity.PageModel;
import com.app.common.util.LoginUtil;
import com.app.common.util.Util;
import com.app.order.service.MovieOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import com.app.common.controller.BaseController;
import com.app.order.entity.MovieOrder;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@Scope("prototype")
@RequestMapping("/order")
public class MovieOrderController extends BaseController<MovieOrder>{

    @Autowired
    private MovieOrderService movieOrderService;

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
}
