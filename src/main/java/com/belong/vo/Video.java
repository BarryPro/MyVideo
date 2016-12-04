package com.belong.vo;

import java.io.Serializable;

/**
 * Created by belong on 16-11-6.
 */
public class Video implements Serializable{
    private int id;//影片的ID用于唯一的确认影片
    private String picpath;//影片海报资源
    private String srcpath;//资源在服务器的存储位置
    private User user;//上传的用户
    private String date;//上传的日期
    private String vname;//影片的名字
    private String vinfo;//影片信息
    private String  type;//上传的电影类型
    private long views;//点击观看的次数

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    public String  getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPicpath() {
        return picpath;
    }

    public void setPicpath(String picpath) {
        this.picpath = picpath;
    }

    public String getSrcpath() {
        return srcpath;
    }

    public void setSrcpath(String srcpath) {
        this.srcpath = srcpath;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }

    public String getVinfo() {
        return vinfo;
    }

    public void setVinfo(String vinfo) {
        this.vinfo = vinfo;
    }
}
