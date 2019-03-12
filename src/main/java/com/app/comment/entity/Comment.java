package com.app.comment.entity;

import com.app.common.entity.AbstractEntity;

import java.math.BigDecimal;
import java.util.Date;

public class Comment extends AbstractEntity{

    private String content;

    private String level;

    private String rMovieId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRMovieId() {
        return rMovieId;
    }

    public void setRMovieId(String rMovieId) {
        this.rMovieId = rMovieId;
    }

}
