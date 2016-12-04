package com.belong.control;

import com.belong.service.IUserService;
import com.belong.service.UserServiceImpl;
import com.belong.vo.User;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created by belong on 16-11-5.
 */
@WebServlet(
        name = "UserControl",
        urlPatterns = "/UserControl",
        initParams = {
                @WebInitParam(name="index",value="index.jsp"),
                @WebInitParam(name="count",value="100")
        }
)
public class UserControl extends HttpServlet{
    private HashMap<String,String> map = new HashMap<String, String>();
    private IUserService service = new UserServiceImpl();
    private static final String ACTION = "action";
    private static final String TMP = "tmp";
    private static final String MSG = "msg";
    private static final String RFAILED = "对不起，注册失败了，别灰心再重新来一次吧";
    private static final String RSUCCESS = "恭喜你注册成功了，快去登陆吧";
    private static final String IMAGE = "image/jpeg";
    private static final String LOGIN = "login";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String COOKIE = "cookie";
    private static final String COOKIEUSERNAME = "com.belong.username";
    private static final String COOKIEPASSWORD = "com.belong.password";
    private static final String SUCCESS = "登录成功！ 欢迎";
    private static final String POST = "光临本帖";
    private static final String FAILED = "对不起！登录失败，请重新登录或注册新的用户";
    private static final String INDEX = "index";
    private static final String USER = "user";
    private static final String LOGINOUT = "loginout";
    private static final String GETPIC = "pic";
    private static final String USERID = "userid";
    private static final String VISTER = "vister";
    private static final String COUNT = "count";
    private static final String VALUE = "value";
    private static final String SELECT = "my_select";
    private static final String UPDATESUCCESS = "修改成功";
    private static final String UPDATEFAILED = "修改失败";
    private static final String FILE = "vister.txt";
    private static final String ENCODER = "utf-8";
    private static final String SYSTEMSEPARATOR = "/";
    @Override
    public void init(ServletConfig config) throws ServletException {
        map.put(INDEX,config.getInitParameter(INDEX));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(ServletFileUpload.isMultipartContent(request)){
            register(request,response);
        } else {
            String action = request.getParameter(ACTION);
            switch(action){
                case LOGIN:
                    login(request, response);
                    add();
                    break;
                case LOGINOUT:
                    loginout(request, response);
                    break;
                case GETPIC:
                    getPic(request, response);
                    break;
                case VISTER:
                    vister(request, response);
                    break;
                case SELECT:
                    select(request, response);
                    break;
                default:
                    break;
            }
        }
    }

    private void select(HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding(ENCODER);
        String value = request.getParameter(VALUE);
        String userid = request.getParameter(USERID);
        PrintWriter pw = null;
        try {
            pw= response.getWriter();
            if(service.updataSelect(Integer.parseInt(value),Integer.parseInt(userid))){
                pw.write(UPDATESUCCESS);
            } else {
                pw.write(UPDATEFAILED);
            }
            pw.flush();
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //添加访客只有登录可以触发
    private void add(){
        InputStream is = UserControl.class.getClassLoader().getResourceAsStream(FILE);
        Properties pro = new Properties();
        try {
            pro.load(is);
            String counter = pro.get(COUNT).toString();
            int sum = Integer.parseInt(counter);
            sum++;
            //得到项目目录
            String tpath = UserControl.class.getClassLoader().getResource("").toString();
            String upload = tpath+FILE;
            //去掉file: 5个字符
            String stdupload = upload.substring(5,upload.length());
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(stdupload)));
            String str = "count="+sum;
            bos.write(str.getBytes());
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void vister(HttpServletRequest request, HttpServletResponse response) {
        InputStream is = UserControl.class.getClassLoader().getResourceAsStream(FILE);
        Properties pro = new Properties();
        try {
            pro.load(is);
            String counter = pro.get(COUNT).toString();
            PrintWriter pw = response.getWriter();
            pw.write(counter);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void getPic(HttpServletRequest request, HttpServletResponse response) {
        String userid = request.getParameter(USERID);
        byte [] buffer = service.getPic(Integer.parseInt(userid));
        response.setContentType(IMAGE);
        try {
            //传照片要用服务器流来实现
            ServletOutputStream sos = response.getOutputStream();
            sos.write(buffer);
            sos.flush();
            sos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loginout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        RequestDispatcher dispatcher = request.getRequestDispatcher(map.get(INDEX));
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        User globalUser = service.login(user);
        String msg = "";
        RequestDispatcher dispatcher = null;
        if(globalUser.getUsername() != null){
            String cook = request.getParameter(COOKIE);
            if(cook != null){
                Cookie cookie1 = new Cookie(COOKIEUSERNAME,username);
                cookie1.setMaxAge(7*3600*24);
                Cookie cookie2 = new Cookie(COOKIEPASSWORD,password);
                cookie2.setMaxAge(7*3600*24);
                response.addCookie(cookie1);
                response.addCookie(cookie2);
            }
            request.getSession().setAttribute(USER,globalUser);
            msg = SUCCESS+username+POST;
        } else {
            msg = FAILED;
        }
        request.setAttribute(MSG,msg);
        dispatcher = request.getRequestDispatcher(map.get(INDEX));
        try {
            dispatcher.forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void register(HttpServletRequest request, HttpServletResponse response) {
        //1.得到服务器路径
        String tpath=request.getServletContext().getRealPath(SYSTEMSEPARATOR);
        //2.定义临时文件
        File tmp = new File(TMP);
        DiskFileItemFactory dfif = new DiskFileItemFactory();
        dfif.setRepository(tmp);
        dfif.setSizeThreshold(1024*1024);
        ServletFileUpload sfu = new ServletFileUpload(dfif);
        sfu.setFileSizeMax(1024*1024);
        sfu.setSizeMax(10*1024*1024);
        try {
            FileItemIterator fii = sfu.getItemIterator(request);
            User user = service.upLoad(fii, tpath);
            String msg = null;
            if(service.register(user)){//注册成功
                msg = RSUCCESS;
            } else {//注册失败
                msg = RFAILED;
            }
            request.setAttribute(MSG,msg);
            RequestDispatcher dispatcher = request.getRequestDispatcher(map.get(INDEX));
            dispatcher.forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
