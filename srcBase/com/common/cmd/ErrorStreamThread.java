package com.common.cmd;


public class ErrorStreamThread extends Thread {

    private RuntimeException e;

    protected void setException(RuntimeException e){
        this.e = e;
    }

    public RuntimeException getException(){
        return e;
    }

    public boolean hasVMException(){
        return e != null;
    }

}