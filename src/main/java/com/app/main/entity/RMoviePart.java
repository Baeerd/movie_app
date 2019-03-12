package com.app.main.entity;

import com.app.common.entity.AbstractEntity;

import java.math.BigDecimal;
import java.util.Date;

public class RMoviePart extends AbstractEntity{

    private String partId;

    private String movieId;

    private String showNo;

    private Date showStart;

    private Date showEnd;

    private String price;

    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        this.partId = partId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getShowNo() {
        return showNo;
    }

    public void setShowNo(String showNo) {
        this.showNo = showNo;
    }

    public Date getShowStart() {
        return showStart;
    }

    public void setShowStart(Date showStart) {
        this.showStart = showStart;
    }

    public Date getShowEnd() {
        return showEnd;
    }

    public void setShowEnd(Date showEnd) {
        this.showEnd = showEnd;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
