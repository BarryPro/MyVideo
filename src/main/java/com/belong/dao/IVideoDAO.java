package com.belong.dao;

import com.belong.vo.PageBean;
import com.belong.vo.Review;

/**
 * Created by belong on 16-11-6.
 */
public interface IVideoDAO {
    public boolean upload(Review review);
    public String getPath(int Vid);
    public byte[] getPic(int Vid);
    public PageBean getInfo(int n,int cur_page,int userid);
    public PageBean search(String txt,int cur_page,int userid);
    public boolean views(int Vid);
    public Review review(int Vid);
}
