package com.common.utils;

/**
 * @author Administrator
 */
public class DateUtils {

    /**
     * 获取时间段说明
     * @return
     */
    public static String getTimeQuantum(int month1 , int month2){
        if(month1 == month2){
            return String.valueOf(month1);
        }

        return String.valueOf(month1) +"至"+ String.valueOf(month2) ;
    }
}
