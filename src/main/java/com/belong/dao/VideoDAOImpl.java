package com.belong.dao;

import com.belong.db.DBCP;
import com.belong.vo.PageBean;
import com.belong.vo.Review;
import com.belong.vo.User;
import com.belong.vo.Video;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by belong on 16-11-6.
 */
public class VideoDAOImpl implements IVideoDAO {
    private Connection conn;
    public VideoDAOImpl(){
        conn = DBCP.getConnection();
    }
    @Override
    public boolean upload(Review review) {
        boolean flag = false;
        CallableStatement cs = null;
        String sql = "call pro_upload_movies(?,?,?,?,?,?,?,?)";
        try {
            cs = conn.prepareCall(sql);
            cs.setString(1,review.getVideo().getVname());
            cs.setString(2,review.getVideo().getVinfo());
            FileInputStream fis = new FileInputStream(review.getVideo().getPicpath());
            cs.setBinaryStream(3,fis,fis.available());//从服务器中转到数据库中
            cs.setString(4,review.getVideo().getSrcpath());
            cs.setInt(5,review.getVideo().getUser().getId());
            cs.setString(6,review.getVideo().getType());
            cs.setString(7,review.getVdirector());
            cs.setString(8,review.getVactor());
            flag = cs.executeUpdate()>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }


    @Override
    public String getPath(int Vid) {
        PreparedStatement ps = null;
        String path = "";
        String sql = "select Vsrc from movies where Vid = ?";
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,Vid);
            rs = ps.executeQuery();
            if(rs.next()){
                path = rs.getString("Vsrc");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return path;
    }

    @Override
    public byte[] getPic(int Vid) {
        byte[] buffer = null;
        PreparedStatement ps = null;
        String sql = "select Vpic from movies where Vid = ?";
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,Vid);
            rs = ps.executeQuery();
            if(rs.next()){
                Blob blob = rs.getBlob("Vpic");
                buffer = blob.getBytes(1, (int) blob.length());
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

    //用于显示全部正常电影
    @Override
    public PageBean getInfo(int n,int cur_page,int userid) {
        boolean flag = false;
        PageBean pageBean = new PageBean();
        CallableStatement cs = null;//用于调用存储过程中的函数
        ResultSet rs = null;
        String sql = "call pro_pagenum1(?,?,?,?,?,?)";//三输入三输出
        try {
            cs = conn.prepareCall(sql);
            cs.setInt(1,n);
            cs.setInt(2,cur_page);
            cs.setInt(3,userid);
            cs.registerOutParameter(4, Types.INTEGER);
            cs.registerOutParameter(5, Types.INTEGER);
            cs.registerOutParameter(6, Types.INTEGER);
            flag = cs.execute();
            //给video和 pagebean 赋值
            while(flag){
                ArrayList<Video> list = new ArrayList<Video>();
                pageBean.setCur_page(cur_page);
                pageBean.setRow_num(cs.getInt(4));
                pageBean.setRow_total(cs.getInt(5));
                pageBean.setPage_total(cs.getInt(6));
                //为电影赋值
                rs = cs.getResultSet();
                while(rs.next()){
                    User user = new User();
                    Video video = new Video();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    video.setUser(user);
                    video.setId(rs.getInt("Vid"));
                    video.setVname(rs.getString("Vname"));
                    video.setVinfo(rs.getString("Vinfo"));
                    video.setSrcpath(rs.getString("Vsrc"));
                    video.setDate(rs.getString("Vdate"));
                    video.setViews(rs.getLong("views"));
                    list.add(video);
                }
                pageBean.setData(list);
                flag = cs.getMoreResults();
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

    @Override
    public PageBean search(String txt,int cur_page,int userid) {
        boolean flag = false;
        PageBean pageBean = new PageBean();
        CallableStatement cs = null;
        ResultSet rs = null;
        String sql = "call pro_pagenum2(?,?,?,?,?,?)";
        try {
            cs = conn.prepareCall(sql);
            String txt_str = "%"+txt+"%";
            cs.setString(1,txt_str);
            cs.setInt(2,cur_page);
            cs.setInt(3,userid);
            cs.registerOutParameter(4, Types.INTEGER);
            cs.registerOutParameter(5, Types.INTEGER);
            cs.registerOutParameter(6, Types.INTEGER);
            flag = cs.execute();
            //给video和 pagebean 赋值
            while(flag){
                ArrayList<Video> list = new ArrayList<Video>();
                pageBean.setCur_page(cur_page);
                pageBean.setRow_num(cs.getInt(4));
                pageBean.setRow_total(cs.getInt(5));
                pageBean.setPage_total(cs.getInt(6));
                //为电影赋值
                rs = cs.getResultSet();
                while(rs.next()){
                    User user = new User();
                    Video video = new Video();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    video.setUser(user);
                    video.setId(rs.getInt("Vid"));
                    video.setVname(rs.getString("Vname"));
                    video.setVinfo(rs.getString("Vinfo"));
                    video.setSrcpath(rs.getString("Vsrc"));
                    video.setDate(rs.getString("Vdate"));
                    video.setViews(rs.getLong("views"));
                    list.add(video);
                }
                pageBean.setData(list);
                flag = cs.getMoreResults();
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

    @Override
    public boolean views(int Vid) {
        boolean flag = false;
        PreparedStatement ps = null;
        String sql = "update movies set views = views + 1 where Vid = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,Vid);
            flag = ps.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(ps!=null){
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
    public Review review(int Vid) {
        Review review = new Review();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from review r join movies m on(r.Vid=m.Vid) where m.Vid = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,Vid);
            rs = ps.executeQuery();
            review.setVid(Vid);
            if(rs.next()){
                Video video = new Video();
                video.setVinfo(rs.getString("Vinfo"));
                video.setVname(rs.getString("Vname"));
                video.setViews(rs.getLong("views"));
                video.setDate(rs.getString("Vdate"));
                review.setVideo(video);
                review.setVactor(rs.getString("Vactor"));
                review.setVdirector(rs.getString("Vdirector"));
                review.setVamount(rs.getString("Vamount"));
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
        return review;
    }
}
