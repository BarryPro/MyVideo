package com.belong.vo;

import java.io.Serializable;

/**
 * Created by belong on 16-11-16.
 */
public class Article implements Serializable{
    private int aid;
    private User user;
    private Video video;
    private String content;
    private String adate;
    private int arootid;

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAdate() {
        return adate;
    }

    public void setAdate(String adate) {
        this.adate = adate;
    }

    public int getArootid() {
        return arootid;
    }

    public void setArootid(int arootid) {
        this.arootid = arootid;
    }
}
