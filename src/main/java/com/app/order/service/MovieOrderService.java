package com.app.order.service;

import com.app.common.service.BaseService;
import com.app.order.entity.MovieOrder;

import java.util.List;
import java.util.Map;

public interface MovieOrderService extends BaseService<MovieOrder>{

    /**
     * 查询个人所有订单
     * @return
     */
    public List<MovieOrder> findOrderForIndex();
}
