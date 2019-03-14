package com.app.movie.entity;


public class MovieVo extends Movie {

    /**
     *  1-是， 0-否
     */
    private String isCurentPart;

    private Long partId;

    public String getIsCurentPart() {
        return isCurentPart;
    }

    public void setIsCurentPart(String isCurentPart) {
        this.isCurentPart = isCurentPart;
    }

    public Long getPartId() {
        return partId;
    }

    public void setPartId(Long partId) {
        this.partId = partId;
    }
}
