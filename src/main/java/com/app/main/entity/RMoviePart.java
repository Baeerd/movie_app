package com.app.main.entity;

import com.app.common.entity.AbstractEntity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class RMoviePart extends AbstractEntity implements Comparable<RMoviePart>{

    private String partId;

    private String movieId;

    private String showNo;

    private Date showStart;

    private Date showEnd;

    private String price;

    private String isGrounding;

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

    public void setIsGrounding(String isGrounding) {
        this.isGrounding = isGrounding;
    }

    public String getIsGrounding() {
        return isGrounding;
    }

    /**
     * 影视号、电影号、场次时间都相同才相同
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        RMoviePart that = (RMoviePart) o;
        String movieId = that.getMovieId();
        String partId = that.getPartId();
        Date showStart = that.getShowStart();
        String showNo = that.getShowNo();
        return movieId.equals(this.movieId) && partId.equals(this.partId) && showStart.equals(this.showStart) && showNo.equals(this.showNo);
    }

    /**
     * 影视号、电影号、场次时间都相同才相同
     * @param rMoviePart
     * @return
     */
    @Override
    public int compareTo(RMoviePart rMoviePart) {
        String movieId = rMoviePart.getMovieId();
        String partId = rMoviePart.getPartId();
        String showNo = rMoviePart.getShowNo();
        Date showStart = rMoviePart.getShowStart();
        if(movieId.equals(this.movieId) && partId.equals(this.partId) && showStart.equals(this.showStart) && showNo.equals(this.showNo)) {
            return 0;
        }
        return -1;
    }
}
