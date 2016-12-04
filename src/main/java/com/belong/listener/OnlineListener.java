package com.belong.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by belong on 16-11-5.
 */
@WebListener
public class OnlineListener implements HttpSessionListener{
    private int count;
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ++count;
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        --count;
    }
}
