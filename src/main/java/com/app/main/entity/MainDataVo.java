package com.app.main.entity;

import com.app.common.entity.AbstractEntity;

import java.util.Date;

public class MainDataVo extends RMoviePart{

    private String partName;

    private String movieName;

    private String showTime;

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

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }
}
