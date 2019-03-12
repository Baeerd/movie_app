package com.app.place.entity;

import com.app.common.entity.AbstractEntity;

import java.math.BigDecimal;
import java.util.Date;

public class Place extends AbstractEntity{

    private String placeNo;

    private String isUse;

    private String rMovieId;

    public String getPlaceNo() {
        return placeNo;
    }

    public void setPlaceNo(String placeNo) {
        this.placeNo = placeNo;
    }

    public String getIsUse() {
        return isUse;
    }

    public void setIsUse(String isUse) {
        this.isUse = isUse;
    }

    public String getRMovieId() {
        return rMovieId;
    }

    public void setRMovieId(String rMovieId) {
        this.rMovieId = rMovieId;
    }

}
