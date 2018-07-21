package com.common.filters;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.common.utils.ComKit;
import com.common.utils.StringFormatKit;
import com.common.utils.json.Jsonkit;
import com.pm.util.PubMethod;
import com.pm.util.constant.BusinessUtil;




/**
 * 登录验证过滤器
 * @author zhonglh
 *
 */
@WebFilter(filterName = "SessionFilter")
public class SessionFilter implements Filter {

    protected static final Logger logger       = LoggerFactory.getLogger (SessionFilter.class);

    @Override
    public void destroy(){}

    @Override
    public void doFilter(ServletRequest arg0,ServletResponse arg1,FilterChain filterChain) throws IOException,ServletException{
        HttpServletRequest req = (HttpServletRequest) arg0;
        HttpServletResponse res = (HttpServletResponse) arg1;
        res.setHeader ("Access-Control-Allow-Origin", "*");
        ThreadLocalHolder.setLocale ((Locale) req.getSession ().getAttribute (SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME));

        String url = req.getRequestURI ();
        Object obj = req.getSession ().getAttribute (BusinessUtil.SESSION_USER);
        if (null != obj && obj.getClass ().getName ().equals ("com.pm.domain.system.User")) {
            filterChain.doFilter (req, res);
        } else {
            if (req.getQueryString () != null) url = url + "?" + req.getQueryString ();
            if (url.indexOf ("toLogin") > -1 || url.indexOf ("login") > -1 ||  
            		url.indexOf ("ajaxLogin") > -1 || url.indexOf ("toAjaxLogin") > -1 || url.indexOf ("CommonAction.do") > -1) {
                filterChain.doFilter (req, res);
                return;
            }
            Map<String, Object> map = new HashMap<String, Object> ();

            if (PubMethod.isAjax(req)) {
			PrintWriter out = res.getWriter();
			out.println("{\"statusCode\":\"301\", \"message\":\"会话超时， 请重新登录!\"}");
            }else {
            	res.sendRedirect(res.encodeRedirectURL("LoginAction.do?method=toLogin"));
            }
            
            return;
        }
    }

    @Override
    public void init(FilterConfig arg0){}

    @SuppressWarnings("unused")
    private boolean checkCookie(HttpServletRequest req,HttpServletResponse res){
        Cookie[] cookie = req.getCookies ();
        if (null == cookie || cookie.length == 0) return false;
        else {
            Map<String, Cookie> map = ReadCookieMap (req);
            Cookie user_name = map.get (BusinessUtil.USER_LOGIN_NAME);
            Cookie user_passwd = map.get (BusinessUtil.USER_LOGIN_PASSWD);
            if (null == user_name || null == user_passwd) return false;
            else {

            }
        }
        return false;

    }

    protected void writeResJson(HttpServletResponse res,Object obj){
        res.setContentType ("application/json; charset=utf-8");
        res.setCharacterEncoding ("utf-8");
        PrintWriter pw = null;
        try {
            pw = res.getWriter ();
            // String str = JSONUtil.getJSONString (obj);
            if (obj instanceof String) {
                pw.write (obj.toString ());
            } else {
                String str = Jsonkit.object2JsonString (obj, true);
                logger.debug ("\n" + StringFormatKit.formatJson (str, StringFormatKit.TAB));
                // str = toCamelCase (str);
                // logger.debug ("\n" + Jsonkit.formatJson (str, Jsonkit.TAB));
                pw.write (str);
            }
        } catch (IOException e) {
            logger.error (e.getMessage (), e);
        } finally {
            if (ComKit.isNotNull (pw)) {
                pw.flush ();
                pw.close ();
            }
        }
    }

    /**
     * 根据名字获取cookie
     * 
     * @param request
     * @param name
     *            cookie名字
     * @return
     */
    @SuppressWarnings("unused")
    private Cookie getCookieByName(HttpServletRequest request,String name){
        Map<String, Cookie> cookieMap = ReadCookieMap (request);
        if (cookieMap.containsKey (name)) {
            Cookie cookie = (Cookie) cookieMap.get (name);
            return cookie;
        } else {
            return null;
        }
    }

    /**
     * 将cookie封装到Map里面
     * 
     * @param request
     * @return
     */
    private Map<String, Cookie> ReadCookieMap(HttpServletRequest request){
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie> ();
        Cookie[] cookies = request.getCookies ();
        if (null != cookies) {
            for ( Cookie cookie : cookies ) {
                cookieMap.put (cookie.getName (), cookie);
            }
        }
        return cookieMap;
    }

}
