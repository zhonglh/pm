package com.common.exceptions;

import java.text.MessageFormat;
import java.util.Locale;

import com.common.filters.ThreadLocalHolder;
import com.common.utils.spring.SpringContextUtil;

public class ErrorConstants {

    private String   code;
    private String   msg;
    private Object[] params;

    protected ErrorConstants(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ErrorConstants setParams(Object... params){
        this.params = params;
        return this;
    }

    public String getCode(){
        return code;
    }

    public String getMsg(){
        if (ThreadLocalHolder.getLocale ().toString ().equals (Locale.CHINA.toString ())) {
            if (null == msg || "".equals (msg)) return SpringContextUtil.getMessage (getCode (), ThreadLocalHolder.getLocale (), msg, params);
            else return MessageFormat.format (msg, params);
        } else return SpringContextUtil.getMessage (getCode (), ThreadLocalHolder.getLocale (), msg, params);
    }

}
