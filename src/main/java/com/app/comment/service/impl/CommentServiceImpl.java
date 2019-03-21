package com.app.comment.service.impl;

import com.app.common.entity.PageModel;
import com.app.common.util.LoginUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.common.service.impl.BaseServiceImpl;
import com.app.comment.entity.Comment;
import com.app.comment.mapper.CommentMapper;
import com.app.comment.service.CommentService;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl extends BaseServiceImpl<Comment> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public PageModel<Comment> findCommentList(Map<String, String> params) {
        // 过滤params
        params = filterParams(params);
        Integer total = commentMapper.findCommentListCount(params);
        Page<Comment> page = PageHelper.startPage(Integer.valueOf(params.get("pageNum")), Integer.valueOf(params.get("pageSize")));
        commentMapper.findCommentList(params);
        PageModel<Comment> result = PageModel.build(page, params);
        result.setTotal(total);
        return result;
    }

    @Override
    public void addComment(Map<String, String> map) {
        String rMovieId = map.get("rMovieId");
        String content = map.get("content");
        Comment comment = new Comment();
        comment.setCreatedDt(new Date());
        comment.setCreatedBy(LoginUtil.getUserName());
        comment.setRMovieId(rMovieId);
        comment.setContent(content);
        comment.setLevel("");

        commentMapper.insert(comment);
    }
}
