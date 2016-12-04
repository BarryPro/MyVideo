package com.belong.service;

import com.belong.vo.Article;
import com.belong.vo.PageBean;

/**
 * Created by belong on 16-11-16.
 */
public interface IArticleService {
    public boolean insertArticle(Article article);
    public PageBean queryArticle(int userid,int vid,int curpage);
}
