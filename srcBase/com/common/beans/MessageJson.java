package com.common.beans;

import java.util.ArrayList;
import java.util.List;

public class MessageJson {

    /**
     * @Fields ERROR :失败
     */
    public static final String ERROR   = "0";
    /**
     * @Fields SUCCESS : 成功
     */
    public static final String SUCCESS = "1";
    /**
     * @Fields flag : 标志 0 失败，1成功 其它：异常代码
     */
    private String             flag;

    /**
     * @Fields msg : 描述
     */
    private String             msg;

    /**
     * @Fields detail : 异常堆栈
     */
    private String             detail;

    /**
     * @Fields taskId : 任务ID
     */
    private String             taskId;

    /**
     * 数据集
     */
    @SuppressWarnings("rawtypes")
    private List               items;

    public MessageJson(String flag) {
        this.flag = flag;
    }

    public MessageJson(String flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }

    @SuppressWarnings("rawtypes")
    public MessageJson(String flag, String msg, List items) {
        this.flag = flag;
        this.msg = msg;
        this.items = items;
    }

    public String getFlag(){
        return flag;
    }

    public void setFlag(String flag){
        this.flag = flag;
    }

    public String getMsg(){
        return msg;
    }

    public void setMsg(String msg){
        this.msg = msg;
    }

    @SuppressWarnings("rawtypes")
    public List getItems(){
        if (null == items) {
            items = new ArrayList ();
        }
        return items;
    }

    @SuppressWarnings("rawtypes")
    public void setItems(List items){
        this.items = items;
    }

    public String getDetail(){
        return detail;
    }

    public void setDetail(String detail){
        this.detail = detail;
    }

    public String getTaskId(){
        return taskId;
    }

    public void setTaskId(String taskId){
        this.taskId = taskId;
    }

}
