package com.common.exceptions;

/**
 * 业务校验异常
 * 
 * @author Riche's
 */
public class ValidationException extends PMException {

    private static final long serialVersionUID = 1L;

    public ValidationException(String s) {
        super (null, s);
    }

    public ValidationException(String s, Object aobj[]) {
        super (null, s);
        this.messageId = s;
        this.aobj = aobj;
    }

    private String messageId;
    private Object aobj[];

    public String getMessageId(){
        return messageId;
    }

    public Object[] getAobj(){
        return aobj;
    }
}
