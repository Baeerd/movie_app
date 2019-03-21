package com.app.comment.controller;

import com.app.comment.service.CommentService;
import com.app.common.entity.PageModel;
import com.app.common.entity.Response;
import com.app.common.util.LoginUtil;
import com.app.common.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import com.app.common.controller.BaseController;
import com.app.comment.entity.Comment;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@Scope("prototype")
@RequestMapping("/comment")
public class CommentController extends BaseController<Comment>{

    @Autowired
    private CommentService commentService;

    @RequestMapping("/commentList")
    public ModelAndView commentList(HttpServletRequest request) {
        String json = getJsonFromRequest(request);
        Map<String, String> params = Util.jsonToMap(json);
        // 判断是否是管理员，如果不是管理员，则只能查看自己的评论
        if(!LoginUtil.isAdmin()) {
            params.put("createdBy", LoginUtil.getUserName());
        }
        PageModel<Comment> page = commentService.findCommentList(params);
        ModelAndView modelAndView = new ModelAndView("comment/commentList");
        modelAndView.addObject("page", page);
        // 回显查询条件
        if(params.get("commentSearch") != null && !"".equals(params.get("commentSearch"))) {
            modelAndView.addObject("commentSearch", params.get("commentSearch"));
        }
        return modelAndView;
    }

    @RequestMapping("/addComment")
    public Response addComment(HttpServletRequest request) {
        String json = super.getJsonFromRequest(request);
        Map<String, String> map = Util.jsonToMap(json);
        commentService.addComment(map);
        return new Response().success();
    }
}
