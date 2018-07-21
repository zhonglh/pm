package com.common.filters;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.SimpleTimeZone;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.cache.ehcache.EhCacheCacheManager;

import com.common.utils.ehcache.CacheKit;
import com.common.utils.spring.SpringContextUtil;

/**
 * @ClassName: GeneralFilter
 * @Title:
 * @Description:(跨域js调用过滤器)
 * @Author:lixin
 * @Since:2014年3月21日下午4:35:06
 * @Version:1.0
 */
public class GeneralFilter implements Filter {

    /** 
    * @Fields threadLocal : 使用ThreadLocal以空间换时间解决SimpleDateFormat线程安全问题。
    */ 
    private static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat> () {

                                                                 protected synchronized SimpleDateFormat initialValue(){

                                                                     return new SimpleDateFormat ("E, dd MMM yyyy HH:mm:ss z",Locale.UK);

                                                                 }
                                                             };

    public static String toUTCString(Date date){
        threadLocal.get ().setTimeZone (new SimpleTimeZone (0,"GMT"));
        return threadLocal.get ().format (date);
    }

    public void listHeaders(HttpServletRequest request){
        for ( Enumeration<String> e = request.getHeaderNames () ; e.hasMoreElements () ; ) {
            String name = e.nextElement ();
            String value = request.getHeader (name);
            
        }
    }

    public void listParameters(HttpServletRequest request){
        
        for ( Enumeration<String> e = request.getParameterNames () ; e.hasMoreElements () ; ) {
            String name = e.nextElement ();
            String[] values = request.getParameterValues (name);
            for ( int i = 0 ; i < values.length ; i++ ) {
                
            }
        }
       
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException{

    }

    @Override
    public void doFilter(ServletRequest req,ServletResponse res,FilterChain chain) throws IOException,ServletException{
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        request.setCharacterEncoding ("UTF-8");
        // listHeaders(request);
        // listParameters(request);

        response.setHeader ("Access-Control-Allow-Origin", "*");
        response.setHeader ("Cache-Control", "max-age=0,must-revalidate");
        response.setHeader ("Pragma", "no-cache");
        
        
        //EhCacheCacheManager ehcachf = (EhCacheCacheManager) SpringContextUtil.getBean ("cacheManager");
        //if (null != ehcachf) CacheKit.init (ehcachf.getCacheManager ());  
        

        chain.doFilter (request, response);
    }

    @Override
    public void destroy(){

    }

}
