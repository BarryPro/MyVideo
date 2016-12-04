package com.belong.vo;

import java.io.Serializable;

/**
 * Created by belong on 16-11-15.
 */
public class Review implements Serializable{
    private int Vid;
    private Video video;
    private String vdirector;
    private String vactor;
    private String vamount;

    public int getVid() {
        return Vid;
    }

    public void setVid(int vid) {
        Vid = vid;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public String getVdirector() {
        return vdirector;
    }

    public void setVdirector(String vdirector) {
        this.vdirector = vdirector;
    }

    public String getVactor() {
        return vactor;
    }

    public void setVactor(String vactor) {
        this.vactor = vactor;
    }

    public String getVamount() {
        return vamount;
    }

    public void setVamount(String vamount) {
        this.vamount = vamount;
    }

}
