package com.common.utils.spring;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;


@Component
public class SpringContextUtil {

    final static Logger               logger = LoggerFactory.getLogger (SpringContextUtil.class);

    // Spring应用上下文环境
    private static ApplicationContext applicationContext;

    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     * 
     * @param applicationContext
     */
    public static void setApplicationContext(ApplicationContext applicationContext){
        synchronized (SpringContextUtil.class) {
            if (null == SpringContextUtil.applicationContext) {
                logger.debug ("setApplicationContext, notifyAll");
                SpringContextUtil.applicationContext = applicationContext;
                SpringContextUtil.class.notifyAll ();
            }
        }
    }

    /**
     * @return ApplicationContext
     */
    public static synchronized ApplicationContext getApplicationContext(){
        if (null == applicationContext) applicationContext = ContextLoader.getCurrentWebApplicationContext ();
        return applicationContext;
    }

    /**
     * 获取对象 这里重写了bean方法，起主要作用
     * 
     * @param name
     * @return Object 一个以所给名字注册的bean的实例
     * @throws BeansException
     */
    public static Object getBean(String name) throws BeansException{
        try {
            if (getApplicationContext () != null) return getApplicationContext ().getBean (name);
        } catch (Exception e) {
            logger.error (e.getMessage (), e);
        }
        return null;
    }

    public static <T> T getBean(Class<T> requiredType){
        if (getApplicationContext () != null) return getApplicationContext ().getBean (requiredType);
        return null;
    }

    public static <T> List<T> getBeans(Class<T> requiredType){
        if (getApplicationContext () != null) {
            Map<String, T> map = getApplicationContext ().getBeansOfType (requiredType);
            if (null != map && map.size () > 0) {
                Iterator<Entry<String, T>> iters = map.entrySet ().iterator ();
                List<T> list = new ArrayList<T> ();
                while (iters.hasNext ()) {
                    list.add (iters.next ().getValue ());
                }
                return list;
            } else return null;
        }
        return null;
    }

    /**
     * @Title: getMessage
     * @Description: 取国际化
     * @Author: ZhongLiHong
     * @Since: 2014年11月4日上午10:27:08
     * @param key
     * @param locale
     * @param args
     * @return
     */
    public static String getMessage(String key,Locale locale,Object... args){
        if (null == locale) locale = Locale.CHINA;
        return getApplicationContext ().getMessage (key, args, locale);
    }
    
    public static String getMessage(String key,Object... args){
    	try{
	    	Locale locale = RequestContextUtils.getLocale (((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest()); 
	        return getApplicationContext ().getMessage (key, args, locale);
    	}catch(Exception e){
    		return "";
    	}
    } 
    

    /**
     * @Title: getMessage
     * @Description: 取国际化
     * @Author: ZhongLiHong
     * @Since: 2014年11月4日上午10:27:20
     * @param key
     * @param locale
     * @param defaultMessage
     * @param args
     * @return
     */
    public static String getMessage(String key,Locale locale,String defaultMessage,Object... args){
        if (null == locale) locale = Locale.CHINA;
        return getApplicationContext ().getMessage (key, args, defaultMessage, locale);
    }
}
