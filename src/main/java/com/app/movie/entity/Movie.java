package com.app.movie.entity;

import com.app.common.entity.AbstractEntity;

import java.math.BigDecimal;
import java.util.Date;

public class Movie extends AbstractEntity{

    private String isGrounding;

    private String name;

    private String remark;

    private String image;

    public String getIsGrounding() {
        return isGrounding;
    }

    public void setIsGrounding(String isGrounding) {
        this.isGrounding = isGrounding;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
