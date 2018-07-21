package com.common.exceptions;


public class CommonErrorConstants extends ErrorConstants {

    /**
     * @Fields e999999 : 系统错误
     */
    public static final CommonErrorConstants e999999 = new CommonErrorConstants ("999999","系统错误!");
    /**
     * @Fields e229903 : 密文处理失败，请刷新页面解决此问题
     */
    public static final CommonErrorConstants e229903 = new CommonErrorConstants ("229903","密文处理失败，请刷新页面解决此问题!");

    /**
     * @Fields e222001 : http请求失败
     */
    public static final CommonErrorConstants e222001 = new CommonErrorConstants ("222001","http请求失败!");
    /**
     * @Fields e223000 : 创建数据库失败
     */
    public static final CommonErrorConstants e223000 = new CommonErrorConstants ("223000","创建数据库失败!");

    /**
     * @Fields e119901 : JSON数据格式不正确
     */
    public static final CommonErrorConstants e119901 = new CommonErrorConstants ("119901","JSON数据格式不正确");
    

    /**
     * @Fields e229902 SQL中不能存在创建或删除数据库的语句！:
     */
    public static final CommonErrorConstants e229902 = new CommonErrorConstants ("229902","SQL中不能存在创建或删除数据库的语句");
    

    /**
     * 数据已经核实过了
     */
    public static final CommonErrorConstants e029901 = new CommonErrorConstants ("029901","数据已经核实过了");
    

    /**
     * 数据已经取消核实了
     */
    public static final CommonErrorConstants e029902 = new CommonErrorConstants ("e029902","数据已经取消核实了");

    protected CommonErrorConstants(String code, String msg) {
        super (code, msg);
    }

}
