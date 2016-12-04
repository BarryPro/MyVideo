package com.belong.dao;

import com.belong.db.DBCP;
import com.belong.vo.Article;
import com.belong.vo.PageBean;
import com.belong.vo.User;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by belong on 16-11-16.
 */
public class ArticleDAOImpl implements IArticleDAO {
    private Connection conn = null;
    public ArticleDAOImpl(){
        conn = DBCP.getConnection();
    }

    @Override
    public boolean insertArticle(Article article) {
        boolean flag = false;
        PreparedStatement ps = null;
        String sql = "insert into article(Acontent,Uid,Arootid,Adate,Vid) values(?,?,?,now(),?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, article.getContent());
            ps.setInt(2,article.getUser().getId());
            ps.setInt(3,article.getArootid());
            ps.setInt(4,article.getVideo().getId());
            flag = ps.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    @Override
    public PageBean queryArticle(int userid,int vid,int curpage) {
        boolean flag = false;
        PageBean pageBean = new PageBean();
        CallableStatement cs = null;
        String sql = "call pro_pagenum3(?,?,?,?,?,?)";
        ResultSet rs = null;
        try {
            cs = conn.prepareCall(sql);
            cs.setInt(1,userid);
            cs.setInt(2,vid);
            cs.setInt(3,curpage);
            cs.registerOutParameter(4, Types.INTEGER);
            cs.registerOutParameter(5,Types.INTEGER);
            cs.registerOutParameter(6,Types.INTEGER);
            flag = cs.execute();
            while(flag){
                ArrayList<Article> list = new ArrayList<Article>();
                pageBean.setPage_total(cs.getInt(4));
                pageBean.setRow_total(cs.getInt(5));
                pageBean.setRow_num(cs.getInt(6));
                pageBean.setCur_page(curpage);
                rs = cs.getResultSet();
                while(rs.next()){
                    Article article = new Article();
                    User user = new User();
                    article.setContent(rs.getString("Acontent"));
                    article.setArootid(rs.getInt("Arootid"));
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    article.setUser(user);
                    article.setAdate(rs.getString("Adate"));
                    list.add(article);
                }
                pageBean.setArticles(list);
                flag = cs.getMoreResults();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            if(cs != null){
                try {
                    cs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return pageBean;
    }
}
