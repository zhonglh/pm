package com.common.utils.spring.i18n;

import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * @author jianghs
 */
public class I18NUtil {

    private HttpServletRequest request;
    private ServletContext     servletContext;

    public I18NUtil(HttpServletRequest request) {
        this.request = request;
        this.servletContext = request.getSession ().getServletContext ();
    }

    public String getMsg(String key){
        WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext (servletContext);
        Locale locale = RequestContextUtils.getLocaleResolver (request).resolveLocale (request);
        String msg = applicationContext.getMessage (key, null, locale);
        if (null == msg) {
            msg = "";
        }

        return msg;
    }

    public String getMsg(String key,String[] args){
        WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext (servletContext);
        Locale locale = RequestContextUtils.getLocaleResolver (request).resolveLocale (request);
        String msg = applicationContext.getMessage (key, args, locale);
        if (null == msg) {
            msg = "";
        }
        return msg;
    }

}
