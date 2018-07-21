package com.common.beans;

import java.io.Serializable;

/**
 * 文件上传状态
 * 
 * @author Riche's
 */
public class FileUploadStatus implements Serializable {

    private static final long serialVersionUID = 2931220585206749847L;

    private long              pBytesRead       = 0L;
    private long              pContentLength   = 0L;
    private int               pItems;
    private String            state            = "0";

    public FileUploadStatus() {
        pBytesRead = 0L;
        pContentLength = 0L;
    }

    public long getPBytesRead(){
        return pBytesRead;
    }

    public void setPBytesRead(long bytesRead){
        pBytesRead = bytesRead;
    }

    public long getPContentLength(){
        return pContentLength;
    }

    public void setPContentLength(long contentLength){
        pContentLength = contentLength;
    }

    public int getPItems(){
        return pItems;
    }

    public void setPItems(int items){
        pItems = items;
    }

    public String getState(){
        return state;
    }

    public void setState(String state){
        this.state = state;
    }
}
