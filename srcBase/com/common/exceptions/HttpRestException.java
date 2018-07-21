package com.common.exceptions;


public class HttpRestException extends PMException {

    private static final long serialVersionUID = 1L;

    public HttpRestException(ErrorConstants ec, Throwable t) {
        super (ec, t);
    }

    public HttpRestException(ErrorConstants ec) {
        super (ec);
    }

    public HttpRestException(String errcode, String errmsg, Throwable t) {
        super (errcode, errmsg, t);
    }

    public HttpRestException(String errcode, String errmsg) {
        super (errcode, errmsg);
    }

}
