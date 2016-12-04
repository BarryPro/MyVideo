package com.belong.service;

import com.belong.dao.IVideoDAO;
import com.belong.dao.VideoDAOImpl;
import com.belong.vo.PageBean;
import com.belong.vo.Review;
import com.belong.vo.User;
import com.belong.vo.Video;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.util.Streams;

import java.io.*;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by belong on 16-11-6.
 */

public class VideoServiceImpl implements IVideoService {
    private IVideoDAO vdao = new VideoDAOImpl();
    private HashMap<String, String> types = new HashMap<String, String>();
    private HashMap<String, String> types1 = new HashMap<String, String>();
    private static final String UPLOAD = "upload";
    private static final String MOVIES = "movies";
    private static final String VINFO = "vinfo";
    private static final String VNAME = "vname";
    private static final String ENCODER = "utf-8";
    private static final String USERID = "userid";
    private static final String TYPE = "type";
    private static final String VDIRECTOR = "vdirector";
    private static final String VACTOR = "vactor";

    public VideoServiceImpl() {
        types.put("video/x-msvideo", ".avi");
        types.put("video/mp4", ".mp4");
        types1.put("image/jpeg", ".jpg");
        types1.put("image/png", ".png");
        types1.put("image/gif", ".gif");
        types1.put("image/x-ms-bmp", ".bmp");
    }

    @Override
    public Review uploadFile(FileItemIterator fii, String tpath) {
        Video video = new Video();
        Review review = new Review();
        try {
            while (fii.hasNext()) {
                FileItemStream fis = fii.next();
                InputStream is = fis.openStream();
                String fileType = fis.getContentType();
                if (!fis.isFormField() && fis.getName().length() > 0) {
                    if (types.containsKey(fileType)) {//电影
                        BufferedInputStream bis = new BufferedInputStream(is);
                        UUID filename = UUID.randomUUID();
                        String file = filename + types.get(fileType);
                        String movies = MOVIES+ File.separator + file;
                        String path = tpath +File.separator + movies;
                        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(path)));
                        Streams.copy(bis, bos, true);
                        video.setSrcpath(movies);
                    }
                    if (types1.containsKey(fileType)) {//图片
                        BufferedInputStream bis = new BufferedInputStream(is);
                        UUID filename = UUID.randomUUID();
                        String file = filename + types1.get(fileType);
                        String upload = UPLOAD;
                        String path = tpath +File.separator + upload + File.separator + file;
                        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(path)));
                        Streams.copy(bis, bos, true);
                        video.setPicpath(path);
                    } else {
                        break;
                    }
                } else {
                    String fieldname = fis.getFieldName();
                    switch (fieldname) {
                        case USERID:
                            String userid = Streams.asString(is, ENCODER);
                            User user = new User();
                            user.setId(Integer.parseInt(userid));
                            video.setUser(user);
                            break;
                        case VINFO:
                            String vinfo = Streams.asString(is, ENCODER);
                            video.setVinfo(vinfo);
                            break;
                        case VNAME:
                            String vname = Streams.asString(is, ENCODER);
                            video.setVname(vname);
                            break;
                        case TYPE:
                            String type = Streams.asString(is, ENCODER);
                            video.setType(type);
                            break;
                        case VDIRECTOR:
                            String vdirector = Streams.asString(is, ENCODER);
                            review.setVdirector(vdirector);
                            break;
                        case VACTOR:
                            String vactor = Streams.asString(is, ENCODER);
                            review.setVactor(vactor);
                            break;
                        default:
                            break;
                    }

                }
            }
            review.setVideo(video);
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return review;
    }

    @Override
    public String getPath(int Vid) {
        return vdao.getPath(Vid);
    }

    @Override
    public boolean upload(Review review) {
        return vdao.upload(review);
    }

    @Override
    public byte[] getPic(int Vid) {
        return vdao.getPic(Vid);
    }

    @Override
    public PageBean getInfo(int type,int cur_page,int userid) {
        return vdao.getInfo(type,cur_page,userid);
    }

    @Override
    public PageBean search(String txt,int cur_page,int userid) {
        return vdao.search(txt,cur_page,userid);
    }

    @Override
    public boolean views(int Vid) {
        return vdao.views(Vid);
    }

    @Override
    public Review review(int Vid) {
        return vdao.review(Vid);
    }
}


