package com.app.order.service;

import com.app.comment.entity.Comment;
import com.app.common.entity.PageModel;
import com.app.common.service.BaseService;
import com.app.main.entity.MainDataVo;
import com.app.order.entity.MovieOrder;

import java.util.List;
import java.util.Map;

public interface MovieOrderService extends BaseService<MovieOrder>{

    /**
     * 查询个人所有订单(前4个)
     * @return
     */
    public List<MovieOrder> findOrderForIndex();

    PageModel<MovieOrder> findOrderList(Map<String, String> params);

    /**
     * 通过id获取订单相关信息
     * @param id
     * @return
     */
    MainDataVo getOrderDataById(String id);
}
