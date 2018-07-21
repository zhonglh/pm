package com.common.exceptions;


/**
 * @ClassName: PDException
 * @Title:
 * @Description:PD3自定义异常
 * @Author:Hongli
 * @Since:2014年6月30日下午6:09:17
 * @Version:1.0
 */
public class PMException extends RuntimeException implements java.io.Serializable {

    /**
     * @Fields serialVersionUID :
     */
    private static final long serialVersionUID = 1L;
    /**
     * 异常码
     */
    private String            errcode;
    /**
     * 异常描述信息
     */
    private String            errmsg;

    public PMException(ErrorConstants ec) {
        this (ec, null);
    }

    public PMException(ErrorConstants ec, Throwable t) {
        this (ec.getCode (), ec.getMsg (), t);
    }

    public PMException(String errcode, String errmsg) {
        this (errcode, errmsg, null);
    }

    public PMException(String errcode, String errmsg, Throwable t) {
        super (errmsg, t);
        this.errcode = errcode;
        if (errcode.startsWith ("e")) this.errcode = errcode.substring (1, errcode.length ());
        this.errmsg = errmsg;
    }

    public String getErrcode(){
        return errcode;
    }

    public void setErrcode(String errcode){
        this.errcode = errcode;
    }

    public String getErrmsg(){
        return errmsg;
    }

    public void setErrmsg(String errmsg){
        this.errmsg = errmsg;
    }

    @Override
    public String toString(){
        return "[".concat (getErrcode ()).concat ("]").concat (getErrmsg ());
    }

}