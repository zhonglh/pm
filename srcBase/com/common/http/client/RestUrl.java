package com.common.http.client;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 获取rest url类
 * 
 * @author liweixi
 * @since 2014-1-22
 * @version1.0
 */
public class RestUrl {

    private static Logger logger        = LoggerFactory.getLogger (RestUrl.class);
    static final String   patternString = "\\{[^\\}]+\\}";

    public static String replaceParam(String url,String... strings){
        Pattern pattern = Pattern.compile (patternString);
        Matcher matcher = pattern.matcher (url);
        StringBuffer sb = new StringBuffer ();
        int i = 0;
        while (matcher.find ()) {
            if (strings != null && i < strings.length) matcher.appendReplacement (sb, strings[i]);
            i++;
        }
        matcher.appendTail (sb);
        String urlStr = sb.toString ();
        if (checkUrl (urlStr)) {
            logger.error ("the url of rest is error:" + urlStr);
            throw new RuntimeException ("the url of rest is error:" + urlStr);
        }
        return urlStr;
    }

    private static boolean checkUrl(String url){
        Pattern pattern = Pattern.compile (patternString);
        Matcher matcher = pattern.matcher (url);
        return matcher.find ();
    }

}
