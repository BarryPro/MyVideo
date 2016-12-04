package com.belong.service;

import com.belong.vo.PageBean;
import com.belong.vo.Review;
import org.apache.commons.fileupload.FileItemIterator;

/**
 * Created by belong on 16-11-6.
 */
public interface IVideoService {
    public Review uploadFile(FileItemIterator fii,String tpath);
    public String getPath(int Vid);
    public boolean upload(Review review);
    public byte[] getPic(int Vid);
    public PageBean getInfo(int type,int cur_page,int userid);
    public PageBean search(String txt,int cur_page,int userid);
    public boolean views(int Vid);
    public Review review(int Vid);
}
