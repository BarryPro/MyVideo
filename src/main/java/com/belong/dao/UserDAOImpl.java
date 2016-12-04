package com.belong.dao;

import com.belong.db.DBCP;
import com.belong.encrypt.MD5;
import com.belong.vo.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

/**
 * Created by belong on 16-11-5.
 */
public class UserDAOImpl implements IUserDAO {
    //得到链接DBCP
    private Connection conn;
    public UserDAOImpl(){
        conn = DBCP.getConnection();
    }
    public boolean register(User user) {
        boolean flag = false;
        PreparedStatement ps = null;
        FileInputStream fis = null;
        String sql = "insert into user(username,password,pic) values(?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            //对密码进行加密保护用户的信息
            ps.setString(2,MD5.getMD5(user.getPassword()));
            //插入得时候用流  读取时用得是blob
            fis = new FileInputStream(user.getPath());
            ps.setBinaryStream(3,fis,fis.available());
            flag = ps.executeUpdate()>0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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
    public User login(User user) {
        User user1 = new User();
        PreparedStatement ps = null;
        String sql = "select * from user where username=? and password=?";
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,user.getUsername());
            //对用户密码进行进行加密比较
            ps.setString(2,MD5.getMD5(user.getPassword()));
            rs = ps.executeQuery();
            if(rs.next()){
                user1.setId(rs.getInt("id"));
                user1.setUsername(rs.getString("username"));
                user1.setPassword(rs.getString("password"));
                Integer pagenum = rs.getInt("pagenum");
                user1.setPagenum(pagenum.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return user1;
    }

    @Override
    public byte[] getPic(int userid) {
        byte[] buffer = null;
        PreparedStatement ps=null;
        ResultSet rs = null;
        String sql = "select pic from user where id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,userid);
            rs = ps.executeQuery();
            if(rs.next()){
                Blob blob = rs.getBlob("pic");
                buffer = blob.getBytes(1,(int)blob.length());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return buffer;
    }

    @Override
    public String getAuthor(int userid) {
        String author = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select username from user where id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,userid);
            rs = ps.executeQuery();
            if(rs.next()){
                author = rs.getString("username");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return author;
    }

    @Override
    public boolean updateSelect(int value ,int userid) {
        boolean flag = false;
        PreparedStatement ps = null;
        String sql = "update user set pagenum = ? where id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,value);
            ps.setInt(2,userid);
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
}
