package com.common.sync;

public class LockType {

    private String               type;

    public static final LockType OSImageImport = new LockType ("OSImageImport");

    public LockType(String type) {
        this.type = type;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

}
