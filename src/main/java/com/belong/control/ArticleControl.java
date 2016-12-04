package com.belong.control;

import com.alibaba.fastjson.JSON;
import com.belong.service.ArticleServiceImpl;
import com.belong.service.IArticleService;
import com.belong.vo.Article;
import com.belong.vo.PageBean;
import com.belong.vo.User;
import com.belong.vo.Video;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by belong on 16-11-15.
 */
@WebServlet(
    name = "ArticleControl",
    urlPatterns = "/ArticleControl"
)
public class ArticleControl extends HttpServlet{
    private IArticleService aservice = new ArticleServiceImpl();
    private static final String ARTICLE = "article";
    private static final String ACTION = "action";
    private static final String CONTENT = "content";
    private static final String ROOTID = "rootid";
    private static final String USERID = "userid";
    private static final String VID = "Vid";
    private static final String CURPAGE = "cur_page";
    private static final String ENCODER = "utf-8";
    private static final String QUERY = "query";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter(ACTION);
        switch (action){
            case ARTICLE:
                article(request, response);
                break;
            case QUERY:
                query(request, response);
                break;
            default:
                break;
        }
    }

    private void query(HttpServletRequest request, HttpServletResponse response) {
        String userid = request.getParameter(USERID);
        String Vid = request.getParameter(VID);
        String curpage = request.getParameter(CURPAGE);
        PageBean pageBean = aservice.queryArticle(Integer.parseInt(userid),Integer.parseInt(Vid),Integer.parseInt(curpage));
        response.setCharacterEncoding(ENCODER);
        String json = JSON.toJSONString(pageBean,true);
        try {
            PrintWriter pw = response.getWriter();
            pw.write(json);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void article(HttpServletRequest request, HttpServletResponse response) {
        //编辑的内容
        String content = request.getParameter(CONTENT);
        String userid = request.getParameter(USERID);
        String rootid = request.getParameter(ROOTID);
        String Vid = request.getParameter(VID);
        Article article = new Article();
        Video video = new Video();
        video.setId(Integer.parseInt(Vid));
        User user = new User();
        user.setId(Integer.parseInt(userid));
        article.setVideo(video);
        article.setUser(user);
        article.setArootid(Integer.parseInt(rootid));
        article.setContent(content);
        if(aservice.insertArticle(article)){//插入成功
            //查询帖子
            query(request,response);
        }
        //插入失败

    }


}
