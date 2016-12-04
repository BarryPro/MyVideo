package com.belong.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * Created by belong on 16-11-5.
 */
@WebFilter(
        urlPatterns = "/*",
        initParams = {
                @WebInitParam(name="encoder",value = "utf-8")
        })
public class EncoderFilter implements javax.servlet.Filter{
    private static final String ENCODER = "encoder";
    private String encoder;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.encoder = filterConfig.getInitParameter(ENCODER);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //过滤后全部设成utf-8得编码制度
        request.setCharacterEncoding(this.encoder);
        //递归过滤(逐层过滤)
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
