package com.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PatternKit {

    /**
     * 验证字符串是否是email
     * 
     * @param str
     * @return
     */
    public static boolean isEmail(String str){
        Pattern pattern = Pattern.compile ("[//w//.//-]+@([//w//-]+//.)+[//w//-]+", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher (str);
        if (matcher.matches ()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 验证是否是手机号码
     * 
     * @param str
     * @return
     */
    public static boolean isCellphone(String str){
        Pattern pattern = Pattern.compile ("1[0-9]{10}");
        Matcher matcher = pattern.matcher (str);
        if (matcher.matches ()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 验证是否是QQ号码
     * 
     * @param str
     * @return
     */
    public static boolean isQQ(String str){
        Pattern pattern = Pattern.compile ("[1-9]{5,10}");
        Matcher matcher = pattern.matcher (str);
        if (matcher.matches ()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断字符串是否是数字
     */
    public static boolean isNumber(String value){
        return isInteger (value) || isDouble (value);
    }

    /**
     * 判断字符串是否是整数
     */
    public static boolean isInteger(String value){
        try {
            Integer.parseInt (value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 判断字符串是否是浮点数
     */
    public static boolean isDouble(String value){
        try {
            Double.parseDouble (value);
            if (value.contains (".")) return true;
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}