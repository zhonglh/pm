package com.common.beans;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(value = "error")
public class Error {

    private int    code;
    private String result;

    public Error() {
    }

    public Error(int code) {
        this.code = code;
    }

    public Error(int code, String result) {
        this.code = code;
        this.result = result;
    }

    public int getCode(){
        return code;
    }

    public Error setCode(int code){
        this.code = code;
        return this;
    }

    public String getResult(){
        return result;
    }

    public void setResult(String result){
        this.result = result;
    }

    public Error set(int code,String result){
        this.code = code;
        this.result = result;
        return this;
    }
}
