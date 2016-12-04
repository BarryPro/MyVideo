package com.belong.dao;

import com.belong.vo.User;

/**
 * Created by belong on 16-11-5.
 */
public interface IUserDAO {
    public boolean register(User user);
    public User login(User user);
    public byte[] getPic(int userid);
    public String getAuthor(int userid);
    public boolean updateSelect(int value,int userid);

}
