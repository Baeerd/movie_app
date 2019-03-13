package com.app.comment.entity;

import com.app.common.util.Util;

import java.util.Date;

public class CommentVo extends Comment{

    private String partName;

    private String movieName;

    private Date createdDt;

    private String createdDtView;

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

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
        this.createdDt = createdDt;
        this.createdDtView = Util.formatDateTime(createdDt);
    }

    public String getCreatedDtView() {
        return createdDtView;
    }

}
