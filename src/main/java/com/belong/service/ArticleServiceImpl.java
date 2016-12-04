package com.belong.service;

import com.belong.dao.ArticleDAOImpl;
import com.belong.dao.IArticleDAO;
import com.belong.vo.Article;
import com.belong.vo.PageBean;

/**
 * Created by belong on 16-11-16.
 */
public class ArticleServiceImpl implements IArticleService{
    private IArticleDAO adao = new ArticleDAOImpl();
    @Override
    public boolean insertArticle(Article article) {
        return adao.insertArticle(article);
    }

    @Override
    public PageBean queryArticle(int userid, int vid, int curpage) {
        return adao.queryArticle(userid,vid,curpage);
    }
}
