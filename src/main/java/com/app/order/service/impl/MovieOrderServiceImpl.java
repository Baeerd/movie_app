package com.app.order.service.impl;

import com.app.comment.entity.Comment;
import com.app.common.entity.Constant;
import com.app.common.entity.PageModel;
import com.app.common.util.LoginUtil;
import com.app.common.util.Util;
import com.app.main.entity.MainDataVo;
import com.app.main.mapper.RMoviePartMapper;
import com.app.order.entity.MovieOrderVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.common.service.impl.BaseServiceImpl;
import com.app.order.entity.MovieOrder;
import com.app.order.mapper.MovieOrderMapper;
import com.app.order.service.MovieOrderService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovieOrderServiceImpl extends BaseServiceImpl<MovieOrder> implements MovieOrderService {

    @Autowired
    private MovieOrderMapper movieOrderMapper;

    @Autowired
    private RMoviePartMapper rMoviePartMapper;

    @Override
    public List<MovieOrder> findOrderForIndex() {
        return movieOrderMapper.findOrderForIndex(LoginUtil.getUserName());
    }

    @Override
    public PageModel<MovieOrder> findOrderList(Map<String, String> params) {
        // 过滤params
        params = filterParams(params);
        Integer total = movieOrderMapper.findOrderListCount(params);
        Page<MovieOrder> page = PageHelper.startPage(Integer.valueOf(params.get("pageNum")), Integer.valueOf(params.get("pageSize")));
        movieOrderMapper.findOrderList(params);
        PageModel<MovieOrder> result = PageModel.build(page, params);
        result.setTotal(total);
        return result;
    }

    @Override
    public MainDataVo getOrderDataById(String id) {
        MainDataVo mainDataVo = rMoviePartMapper.getOrderDataById(id);
        filtMainData(mainDataVo);
        return mainDataVo;
    }

    /**
     * 添加场次显示时间
     * @param mainDataVo
     */
    private void filtMainData(MainDataVo mainDataVo) {
        Date showStart = mainDataVo.getShowStart();
        String showNo = mainDataVo.getShowNo();
        String[] times = Constant.SHOW_NO_MAP.get(showNo);
        mainDataVo.setShowTime(Util.formatDate(showStart) + " " + times[0]);
    }
}
