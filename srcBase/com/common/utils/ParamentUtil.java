package com.common.utils;

import java.util.List;

public class ParamentUtil {

    public static String array2String(String[] args){
        if (args == null || args.length <= 0) return "";
        StringBuilder param = new StringBuilder ();
        for ( String str : args ) {
            param.append (str).append ("|");
        }
        return param.deleteCharAt (param.length () - 1).toString ().trim ();

    }

    /*
     * 字符串加单引号
     */
    public static String addSingleQuote(String param){
        return "\'".concat (param).concat ("\'");
    }

    /*字符串加空格
     * 
     */
    public static String betweenSpace(String... params){
        StringBuffer param = new StringBuffer ();
        for ( int i = 0 ; i < params.length ; i++ ) {
            param.append (params[i] + " ");
        }
        return param.deleteCharAt (param.length () - 1).toString ().trim ();
    }

    public static String betweenSpace(List<String> params){
        StringBuffer param = new StringBuffer ();
        for ( int i = 0 ; i < params.size () - 1 ; i++ ) {
            param.append (params.get (i) + " ");
        }
        return param.deleteCharAt (param.length () - 1).toString ().trim ();
    }

    /*字符串加|
     * 
     */
    public static String addLine(String... params){
        StringBuffer param = new StringBuffer ();

        for ( int i = 0 ; i < params.length ; i++ ) {
            param.append (params[i] + "|");

        }
        return param.deleteCharAt (param.length () - 1).toString ().trim ();
    }

    public static String[] addSingleQuote(String... params){
        String[] newStr = new String[params.length];
        for ( int i = 0 ; i < params.length ; i++ ) {
            if (i < params.length) {
                newStr[i] = "'" + params[i] + "'";
            } else {
                newStr[i] = params[i];
            }
            // newStr[i] = "'".concat ( params[i]).concat ("'");
        }
        return newStr;
    }

    public static String addLine(List<String> params){
        StringBuffer param = new StringBuffer ();
        for ( int i = 0 ; i < params.size () ; i++ ) {
            param.append (params.get (i) + "|");
        }
        return param.deleteCharAt (param.length () - 1).toString ().trim ();
    }

    /*字符串加,
     * 
     */
    public static String addComma(String... params){
        StringBuffer param = new StringBuffer ();

        for ( int i = 0 ; i < params.length ; i++ ) {
            param.append (params[i] + ",");

        }
        return param.deleteCharAt (param.length () - 1).toString ().trim ();
    }

    public static String addComma(List<String> params){
        StringBuffer param = new StringBuffer ();

        for ( int i = 0 ; i < params.size () ; i++ ) {
            param.append (params.get (i) + ",");

        }
        return param.deleteCharAt (param.length () - 1).toString ().trim ();

    }

    public static String addSpace(String... params){
        StringBuffer param = new StringBuffer ();
        for ( int i = 0 ; i < params.length ; i++ ) {
            param.append (params[i] + " ");
        }
        return param.deleteCharAt (param.length () - 1).toString ().trim ();
    }

    public static void main(String[] args){
        StringBuffer aa = new StringBuffer ("aab");
        System.out.println (aa.deleteCharAt (aa.length () - 1).toString ());
    }
}
