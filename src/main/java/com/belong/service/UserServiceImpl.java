package com.belong.service;

import com.belong.dao.IUserDAO;
import com.belong.dao.UserDAOImpl;
import com.belong.vo.User;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.util.Streams;

import java.io.*;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by belong on 16-11-5.
 */
public class UserServiceImpl implements IUserService {
    private IUserDAO dao = new UserDAOImpl();
    private HashMap<String,String> types = new HashMap<String, String>();
    private static final String UPLOAD = "upload";
    private static final String RUSERNAME = "rusername";
    private static final String RPASSWORD = "rpassword";
    private static final String ENCODER = "utf-8";
    public UserServiceImpl(){
        types.put("image/jpeg", ".jpg");
        types.put("image/png", ".png");
        types.put("image/gif", ".gif");
        types.put("image/x-ms-bmp", ".bmp");
    }
    public boolean register(User user) {
        return dao.register(user);
    }

    public User upLoad(FileItemIterator fii, String tpath) {
        //用于存放信息完整得用户信息
        User user = new User();
        //循环查看表单信息
        try {
            while(fii.hasNext()){
                FileItemStream fis = fii.next();
                //用于把文件从磁盘读到内存中
                InputStream is = fis.openStream();
                if(!fis.isFormField()&&fis.getName().length()>0){//二进制流
                    //eg:image/jpeg
                    String upLoadType = fis.getContentType();
                    if(!types.containsKey(upLoadType)){//不符合类型就直接退出
                        break;
                    }
                    //定义上传文件夹
                    String upload = UPLOAD;
                    UUID fileName = UUID.randomUUID();
                    String file = fileName+types.get(upLoadType);
                    String path = tpath+ File.separator+upload+ File.separator+file;
                    //把输入流包成字符流
                    BufferedInputStream bis = new BufferedInputStream(is);
                    //向服务器得磁盘中写入数据
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(path)));
                    Streams.copy(bis, bos, true);
                    user.setPath(path);
                } else {//是基本表单信息
                    String fieldName = fis.getFieldName();
                    switch(fieldName){
                        case RUSERNAME:
                            String username = Streams.asString(is,ENCODER);
                            user.setUsername(username);
                            break;
                        case RPASSWORD:
                            String password = Streams.asString(is,ENCODER);
                            user.setPassword(password);
                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User login(User user) {
        return dao.login(user);
    }

    @Override
    public byte[] getPic(int id) {
        return dao.getPic(id);
    }

    @Override
    public String getAuthor(int userid) {
        return dao.getAuthor(userid);
    }

    @Override
    public boolean updataSelect(int value, int userid) {
        return dao.updateSelect(value,userid);
    }
}
