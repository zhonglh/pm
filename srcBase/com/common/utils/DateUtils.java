package com.common.utils;

import java.util.Date;

/**
 * @author Administrator
 */
public class DateUtils {


    public static void main(String[] args) {
        System.out.println(getTimeQuantum(201901,201912));
    }

    /**
     * 获取时间段说明
     * @return
     */
    public static String getTimeQuantum(int month1 , int month2){
        if(month1 == month2){
            return ymTitle(month1);
        }else {

            if(month1%100==1 && month2%100==12 && month1/100 == month2/100){
                return yTitle(month1);
            }else {
                return ymTitle(month1) + " 至 " + ymTitle(month2);
            }
        }
    }


    public static String ymTitle(int month){
        Date date = DateKit.fmtShortYMTStrToDate(String.valueOf(month)+"01");
        return DateKit.fmtDateToStr(date , "yyyy年MM月");
    }


    public static String yTitle(int month){
        Date date = DateKit.fmtShortYMTStrToDate(String.valueOf(month)+"01");
        return DateKit.fmtDateToStr(date , "yyyy年");
    }

}
