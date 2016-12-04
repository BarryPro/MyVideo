package com.belong.control;

import com.alibaba.fastjson.JSON;
import com.belong.service.IVideoService;
import com.belong.service.VideoServiceImpl;
import com.belong.vo.PageBean;
import com.belong.vo.Review;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by belong on 16-11-6.
 */
@WebServlet(
        name = "VideoControl",
        urlPatterns = "/VideoControl",
        initParams = {
                @WebInitParam(name="index",value="index.jsp"),
                @WebInitParam(name="player",value="player.jsp"),
                @WebInitParam(name="comment",value="comment.jsp")
        }
)
public class VideoControl extends HttpServlet{
    private IVideoService vservice = new VideoServiceImpl();
    private HashMap<String,String> types = new HashMap<String,String>();
    private HashMap<String,String> map = new HashMap<String,String>();
    private static final String ACTION = "action";
    private static final String INDEX = "index";
    private static final String UPLOAD = "upload";
    private static final String TMP = "tmp";
    private static final String MSG = "msg";
    private static final String RFAILED = "对不起，上传失败了，别灰心再重新来一次吧";
    private static final String RSUCCESS = "恭喜你上传成功了，快去观看吧";
    private static final String SRCPATH = "srcpath";
    private static final String IMAGE = "image/jpeg";
    private static final String PIC = "pic";
    private static final String VID = "Vid";
    private static final String PLAYER = "player";
    private static final String INFO = "db_info";
    private static final String ENCODER = "utf-8";
    private static final String N = "n";
    private static final String SEARCH_INFO = "search";
    private static final String TXT = "txt";
    private static final String USERID = "userid";
    private static final String CUR_PAGE = "cur_page";
    private static final String REVIEW = "review";
    private static final String COMMENT = "comment";
    private static final String SYSTEMSEPARATOR = "/";

    @Override
    public void init(ServletConfig config) throws ServletException {
        map.put(INDEX,config.getInitParameter(INDEX));
        map.put(PLAYER,config.getInitParameter(PLAYER));
        map.put(COMMENT,config.getInitParameter(COMMENT));
        types.put("video/avi", ".avi");
        types.put("video/mp4", ".mp4");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter(ACTION);
        switch(action){
            case UPLOAD:
                upload(request,response);
                break;
            case SRCPATH:
                getPath(request, response);
                break;
            case PIC:
                getPic(request, response);
                break;
            case INFO:
                getInfo(request, response);
                break;
            case SEARCH_INFO:
                search(request, response);
                break;
            case REVIEW:
                review(request, response);
                break;
            default:
                break;
        }
    }

    private void review(HttpServletRequest request, HttpServletResponse response) {
        String vid = request.getParameter(VID);
        Review review = vservice.review(Integer.parseInt(vid));
        RequestDispatcher dispatcher = request.getRequestDispatcher(map.get(COMMENT));
        request.setAttribute(REVIEW,review);
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //搜索引擎
    private void search(HttpServletRequest request, HttpServletResponse response) {
        String txt = request.getParameter(TXT);
        String userid = request.getParameter(USERID);
        String cur_page = request.getParameter(CUR_PAGE);
        PageBean pageBean = vservice.search(txt,Integer.parseInt(cur_page),Integer.parseInt(userid));
        response.setCharacterEncoding(ENCODER);
        String json = JSON.toJSONString(pageBean);
        try {
            PrintWriter pw = response.getWriter();
            pw.write(json);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //得到数据库得信息
    private void getInfo(HttpServletRequest request, HttpServletResponse response) {
        String n = request.getParameter(N);
        String userid = request.getParameter(USERID);
        String cur_page = request.getParameter(CUR_PAGE);
        PageBean pageBean = vservice.getInfo(Integer.parseInt(n),Integer.parseInt(cur_page),Integer.parseInt(userid));
        response.setCharacterEncoding(ENCODER);
        try {
            String json = JSON.toJSONString(pageBean);
            PrintWriter pw = response.getWriter();
            pw.write(json);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //得到电影海报
    private void getPic(HttpServletRequest request, HttpServletResponse response) {
        String vid = request.getParameter(VID);
        byte[] buffer = vservice.getPic(Integer.parseInt(vid));
        response.setContentType(IMAGE);
        try {
            ServletOutputStream sos = response.getOutputStream();
            sos.write(buffer);
            sos.flush();
            sos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //得到播放路径
    private void getPath(HttpServletRequest request, HttpServletResponse response) {
        String vid = request.getParameter(VID);
        int Vid = Integer.parseInt(vid);
        //增加点击量
        vservice.views(Vid);
        String src = vservice.getPath(Vid);
        //获得后4位字符用于判断是什么格式的
        String postfix = src.substring(src.length()-4,src.length());
        Set set = types.entrySet();
        //得到遍历器
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            if(entry.getValue().equals(postfix)){
                //设置前端响应得视频类型
                response.setContentType((String) entry.getKey());
                break;
            }
        }
        //设置播放路径
        request.setAttribute(SRCPATH,src);
        RequestDispatcher dispatcher = request.getRequestDispatcher(map.get(PLAYER));
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //上传电影
    private void upload(HttpServletRequest request, HttpServletResponse response) {
        //得到服务器得路径
        String tpath = request.getServletContext().getRealPath(SYSTEMSEPARATOR);
        String tmp = TMP;
        File tmpDir = new File(tmp);
        DiskFileItemFactory dfif = new DiskFileItemFactory();
        dfif.setRepository(tmpDir);
        dfif.setSizeThreshold(1024*1024);
        ServletFileUpload sfu = new ServletFileUpload(dfif);
        //1G
        sfu.setFileSizeMax(1024*1024*1024);
        sfu.setSizeMax(1024*1024*1024*10);
        try {
            FileItemIterator fii = sfu.getItemIterator(request);
            Review review = vservice.uploadFile(fii, tpath);
            RequestDispatcher dispatcher = null;
            if(vservice.upload(review)){
                request.setAttribute(MSG,RSUCCESS);
            } else {
                request.setAttribute(MSG,RFAILED);
            }
            dispatcher = request.getRequestDispatcher(map.get(INDEX));
            try {
                dispatcher.forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
