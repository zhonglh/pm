package com.common.exceptions;


public class BaseException extends PMException implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    Exception                 sourceException  = null;

    public BaseException(ErrorConstants ec, Throwable t) {
        super (ec, t);
    }

    public BaseException(ErrorConstants ec) {
        super (ec);
    }

    public BaseException(String errcode, String errmsg, Throwable t) {
        super (errcode, errmsg, t);
    }

    public BaseException(String errcode, String errmsg) {
        super (errcode, errmsg);
    }

}
