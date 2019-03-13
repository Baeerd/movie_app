package com.app.order.entity;

import com.app.common.util.Util;

import java.util.Date;

public class MovieOrderVo extends MovieOrder{

    private String movieName;

    private String partName;

    private Date createdDt;

    private String createdDtView;

    private Date showStart;

    private String showStartView;

    private String image;

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    @Override
    public Date getCreatedDt() {
        return createdDt;
    }

    @Override
    public void setCreatedDt(Date createdDt) {
        this.createdDtView = Util.formatDateTime(createdDt);
        this.createdDt = createdDt;
    }

    public String getCreatedDtView() {
        return createdDtView;
    }

    public Date getShowStart() {
        return showStart;
    }

    public void setShowStart(Date showStart) {
        this.showStart = showStart;
        this.showStartView = Util.formatDateTime(showStart);
    }

    public String getShowStartView() {
        return showStartView;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }
}
