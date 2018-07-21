package com.common.http.entity;

import java.util.Date;

public class RemoteTokenInfo {

    private String token;
    private Date   expireDate;

    public String getToken(){
        return token;
    }

    public void setToken(String token){
        this.token = token;
    }

    public Date getExpireDate(){
        return expireDate;
    }

    public void setExpireDate(Date expireDate){
        this.expireDate = expireDate;
    }

}
