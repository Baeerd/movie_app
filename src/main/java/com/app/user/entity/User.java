package com.app.user.entity;

import com.app.common.entity.AbstractEntity;

import java.math.BigDecimal;
import java.util.Date;

public class User extends AbstractEntity{

    private String sex;

    private String name;

    private String username;

    private String password;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
