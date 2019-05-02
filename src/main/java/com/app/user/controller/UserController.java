package com.app.user.controller;

import com.app.common.controller.BaseController;
import com.app.common.entity.AppConfig;
import com.app.common.entity.Constant;
import com.app.common.exception.MessageException;
import com.app.common.util.LoginUtil;
import com.app.common.util.Util;
import com.app.order.service.MovieOrderService;
import com.app.user.entity.User;
import com.app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 注册登陆相关操作Controller
 */
@RequestMapping("/system")
@Controller
public class UserController extends BaseController<User>{

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private UserService userService;

    @Autowired
    private MovieOrderService movieOrderService;

    @RequestMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String json = getJsonFromRequest(request);
        Map<String, String> params = Util.jsonToMap(json);
        // 根据用户名密码查询数据库
        try {
            User user = userService.validateLogin(params.get("username"), params.get("password"));
            // 缓存用户信息
            LoginUtil.login(user);
            // 向session中放入用户信息
            request.getSession().setAttribute(LoginUtil.LOGINUSER, user);
            // session缓存用户拥有的订单信息
            request.getSession().setAttribute(Constant.USER_ORDER_LIST, movieOrderService.findOrderForIndex());
            response.sendRedirect(LoginUtil.getInterceptorPath());
        } catch (MessageException e) {
            request.setAttribute("error", e.getMessage());
            response.sendRedirect(LoginUtil.LOGINPAGE);
        }
    }

    @RequestMapping("/regist")
    public ModelAndView regist(HttpServletRequest request) {
        String json = this.getJsonFromRequest(request);
        User user = Util.jsonToBean(json, User.class);
        user.setCreatedDt(new Date());
        user.setCreatedBy(user.getUsername());
        ModelAndView mv = new ModelAndView("/login");
        mv.addObject(user);
        Map<String,String> params = new HashMap<>();
        params.put("username",user.getUsername());
        List<User> userList = userService.findByParam(params);
        if (!userList.isEmpty()){
            mv.addObject("msg","此用户名已被注册，请登录！");
            return mv;
        }

        userService.insert(user);
        mv.addObject("msg","注册成功，请登录！");
        return mv;
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "/";
    }

}
