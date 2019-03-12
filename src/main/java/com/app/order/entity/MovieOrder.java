package com.app.order.entity;

import com.app.common.entity.AbstractEntity;

import java.math.BigDecimal;
import java.util.Date;

public class MovieOrder extends AbstractEntity{

    private String state;

    private String rMovieId;

    private String totalPrice;

    private String num;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRMovieId() {
        return rMovieId;
    }

    public void setRMovieId(String rMovieId) {
        this.rMovieId = rMovieId;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

}
