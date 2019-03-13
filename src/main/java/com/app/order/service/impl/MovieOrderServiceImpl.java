package com.app.order.service.impl;

import com.app.common.entity.PageModel;
import com.app.common.util.LoginUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.common.service.impl.BaseServiceImpl;
import com.app.order.entity.MovieOrder;
import com.app.order.mapper.MovieOrderMapper;
import com.app.order.service.MovieOrderService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovieOrderServiceImpl extends BaseServiceImpl<MovieOrder> implements MovieOrderService {

    @Autowired
    private MovieOrderMapper movieOrderMapper;

    @Override
    public List<MovieOrder> findOrderForIndex() {
        return movieOrderMapper.findOrderForIndex(LoginUtil.getUserName());
    }
}
