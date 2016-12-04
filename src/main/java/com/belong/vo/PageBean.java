package com.belong.vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by belong on 16-11-12.
 */
public class PageBean implements Serializable{
    private int row_num;//每页的行数
    private int row_total;//一共由多少行
    private int page_total;//一共有多少页
    private int cur_page;//当前是第几页
    private ArrayList<Video> data;//存放电影信息
    private ArrayList<Article> articles;//用于存放评论信息

    public ArrayList<Article> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }

    public ArrayList<Video> getData() {
        return data;
    }

    public void setData(ArrayList<Video> data) {
        this.data = data;
    }

    public int getRow_num() {
        return row_num;
    }

    public void setRow_num(int row_num) {
        this.row_num = row_num;
    }

    public int getRow_total() {
        return row_total;
    }

    public void setRow_total(int row_total) {
        this.row_total = row_total;
    }

    public int getPage_total() {
        return page_total;
    }

    public void setPage_total(int page_total) {
        this.page_total = page_total;
    }

    public int getCur_page() {
        return cur_page;
    }

    public void setCur_page(int cur_page) {
        this.cur_page = cur_page;
    }
}
