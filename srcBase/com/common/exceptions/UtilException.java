package com.common.exceptions;

/**
 * 虚拟机自定义异常类
 * 
 * @version 1.0
 * @modifier liguoqing
 * @date 2013-10-08
 */
public class UtilException extends RuntimeException {

    private static final long serialVersionUID = -6817514779282712785L;
    /**
     * 异常码
     */
    private int               exceptionCode;
    /**
     * 异常描述信息
     */
    private String            easyMessage;

    public UtilException(int code) {
        super ();
        this.exceptionCode = code;
    }

    public UtilException(int code, String message) {
        super (message);
        this.exceptionCode = code;
        this.easyMessage = message;
    }

    public UtilException(int code, String message, Throwable e) {
        super (message, e);
        this.exceptionCode = code;
        this.easyMessage = message;
    }

    public int getErrcode(){
        return this.exceptionCode;
    }

    public String getExceptionMessage(){
        return this.easyMessage;
    }

    @Override
    public String toString(){
        return "UtilException [easyMessage=" + easyMessage + ", exceptionCode=" + exceptionCode + "]";
    }

}