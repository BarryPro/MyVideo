package com.belong.service;

import com.belong.vo.User;
import org.apache.commons.fileupload.FileItemIterator;

/**
 * Created by belong on 16-11-5.
 */
public interface IUserService {
    public boolean register(User user);
    public User upLoad(FileItemIterator fii,String tpath);
    public User login(User user);
    public byte[] getPic(int id);
    public String getAuthor(int userid);
    public boolean updataSelect(int value,int userid);
}
