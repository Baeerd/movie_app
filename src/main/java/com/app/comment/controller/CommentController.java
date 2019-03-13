package com.app.comment.controller;

import com.app.comment.service.CommentService;
import com.app.common.entity.PageModel;
import com.app.common.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import com.app.common.controller.BaseController;
import com.app.comment.entity.Comment;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@Scope("prototype")
@RequestMapping("/comment")
public class CommentController extends BaseController<Comment>{

    @Autowired
    private CommentService commentService;

    @RequestMapping("/commentList")
    public ModelAndView commentList(HttpServletRequest request) {
        String json = getJsonFromRequest(request);
        Map<String, String> params = Util.jsonToMap(json);
        PageModel<Comment> page = commentService.findCommentList(params);
        ModelAndView modelAndView = new ModelAndView("comment/commentList");
        modelAndView.addObject("page", page);
        if(params.get("commentSearch") != null && !"".equals(params.get("commentSearch"))) {
            modelAndView.addObject("commentSearch", params.get("commentSearch"));
        }
        return modelAndView;
    }
}
