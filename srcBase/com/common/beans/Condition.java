package com.common.beans;

/**
 * @ClassName: Condition
 * @Title:
 * @Description:(查询条件)
 * @Version:1.0
 */
public class Condition {

    private String key;
    private String value;

    public Condition(String key, String value) {
        this.key = key;
        this.value = value;
    }
    public Condition() {
    }

    public String getKey(){
        return key;
    }

    public void setKey(String key){
        this.key = key;
    }

    public String getValue(){
        return value;
    }

    public void setValue(String value){
        this.value = value;
    }

}
