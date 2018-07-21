package com.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Hongli
 */
public class ComKit {

    /**
     * @Title: isNull
     * @Description: 判断是否为空 ,支持Object ,String
     * @Author: Hongli
     * @Since: 2013-6-14上午9:28:20
     * @param obj
     * @return true 为空
     */
    public static boolean isNull(Object obj){
        boolean boo = false;
        if (null == obj) { return true; }
        if (obj instanceof String) if ("".equals (obj) || "null".equals (obj)) return true;
        if (obj instanceof Collection) if (((Collection<?>) obj).isEmpty ()) return true;
        if (obj instanceof Map) if (((Map<?, ?>) obj).isEmpty ()) boo = true;
        return boo;
    }

    public static boolean isNull(Object... objs){
        for ( Object object : objs ) {
            if (isNotNull (object)) return false;
        }
        return true;
    }

    public static boolean isNotNull(Object obj){
        return !isNull (obj);
    }

    public static boolean isNotNull(Object... objs){
        for ( Object object : objs ) {
            if (isNull (object)) return false;
        }
        return true;
    }

    public static boolean equals(String src,String target){
        return src.equals (target);
    }

    public static boolean equalsOR(String target,String... srcs){
        for ( String str : srcs ) {
            if (str.equals (target)) return true;
        }
        return false;
    }

    public static boolean equalsAND(String target,String... srcs){
        for ( String str : srcs ) {
            if (!str.equals (target)) return false;
        }
        return true;
    }

    /**
     * @Title: stringSort
     * @Description: 排序
     * @Author: Hongli
     * @Since: 2013-6-14上午9:27:41
     * @param str
     * @return
     */
    public static String stringSort(String str){
        if (isNull (str)) return null;
        String a1 = "";
        char temp;
        int b = str.length ();
        char[] c = str.toCharArray ();
        for ( int i = 0 ; i < b ; i++ )
            for ( int j = 0 ; j < b - i - 1 ; j++ )
                if (c[j] >= c[j + 1]) {
                    temp = c[j];
                    c[j] = c[j + 1];
                    c[j + 1] = temp;
                }
        for ( int k = 0 ; k < b ; k++ )
            a1 += String.valueOf (c[k]);
        return a1;
    }

    /**
     * @Title: isChinese
     * @Description: 判断是否为中文字符
     * @Author: Hongli
     * @Since: 2013-6-14上午9:34:02
     * @param c
     * @return
     */
    public static boolean isChinese(char c){
        Character.UnicodeBlock ub = Character.UnicodeBlock.of (c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) return true;
        return false;
    }

    /**
     * @Title: getIso2Utf8
     * @Description: ISO转UTF-8
     * @Author: Hongli
     * @Since: 2013-6-17下午1:13:27
     * @param message
     * @return
     */
    public static String getIso2Utf8(String message){
        try {
            if (!isNull (message)) return new String (message.getBytes ("iso-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace ();
        }
        return message;
    }

    /**
     * @Title: getUtf82Iso
     * @Description: UTF-8转ISO
     * @Author: Hongli
     * @Since: 2013-6-17下午1:13:45
     * @param message
     * @return
     */
    public static String getUtf82Iso(String message){
        try {
            return new String (message.getBytes ("UTF-8"),"iso-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace ();
        }
        return message;
    }

    /**
     * @Title: listTOMap
     * @Description: "="号分隔的List字符转成Map
     * @Author: Hongli
     * @Since: 2013-6-17下午1:17:48
     * @param list
     * @return
     */
    public static Map<String, String> listTOMap(List<String> list){
        Map<String, String> map = null;
        if (!isNull (list) && !list.isEmpty ()) for ( String str : list )
            if (str.indexOf ("=") > -1) {
                if (null == map) map = new HashMap<String, String> ();
                if (!isNull (str)) {
                    String key = str.substring (0, str.indexOf ("="));
                    String value = str.substring (str.indexOf ("=") + 1, str.length ());
                    map.put (key, value);
                }

            }
        return map;
    }

    public static long[] bubbleSort(long[] args){// 冒泡排序算法
        for ( int i = 0 ; i < args.length - 1 ; i++ )
            for ( int j = i + 1 ; j < args.length ; j++ )
                if (args[i] > args[j]) {
                    long temp = args[i];
                    args[i] = args[j];
                    args[j] = temp;
                }
        return args;

    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static List<String> getKeys(Map map){
        Map<String, String> sorted = new TreeMap<String, String> (String.CASE_INSENSITIVE_ORDER);
        Iterator<Map.Entry<String, String>> pairs = sorted.entrySet ().iterator ();
        List list = new ArrayList ();
        while (pairs.hasNext ()) {
            Map.Entry<String, String> pair = pairs.next ();
            list.add (pair.getKey ());
        }
        return list;
    }

    public static long[] stringArr2Long(List<String> list){
        long[] lon = new long[list.size ()];
        for ( int i = 0 ; i < list.size () ; i++ )
            lon[0] = Long.valueOf (list.get (i));
        return lon;
    }

    /**
     * @Title: getUUID
     * @Description:生成UUID
     * @Author: Hongli
     * @Since: 2014年3月17日下午6:06:13
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID ().toString ();
    }

    /**
     * @Title: getUUIDNoMidline
     * @Description: 生成没有“-”的uuid
     * @Author: Hongli
     * @Since: 2014年3月17日下午6:05:16
     * @return
     */
    public static String getUUIDNoMidline(){
        return UUID.randomUUID ().toString ().replace ("-", "");
    }

    /**
     * @Title: getRandom
     * @Description: 随机生成区间数
     * @Author: Hongli
     * @Since: 2014年3月17日下午6:04:48
     * @param min
     * @param max
     * @return
     */
    public static int getRandom(int min,int max){
        Random random = new Random ();
        return random.nextInt (max) % (max - min + 1) + min;
    }

    /**
     * @Title: replaceBlank
     * @Description: 去除字符串中的空格、回车、换行符、制表符
     * @Author: Hongli
     * @Since: 2014年4月23日上午10:31:10
     * @param str
     * @return
     */
    public static String replaceBlank(String str){
        String dest = "";
        if (str != null) {
            // Pattern p = Pattern.compile ("\\s*|\t|\r|\n");
            Pattern p = Pattern.compile ("\\t|\r|\n");
            Matcher m = p.matcher (str);
            dest = m.replaceAll ("");
        }
        return dest;
    }

    /**
     * 产生随机字符串
     */
    private static Object initLock          = new Object ();
    private static String NUMBERSANDLETTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String random(int length){
        char[] char_ = null;
        Random randGen = null;
        if (length < 1) { return null; }
        synchronized (initLock) {
            randGen = new Random ();
            char_ = NUMBERSANDLETTERS.toCharArray ();
        }
        char[] randBuffer = new char[length];
        for ( int i = 0 ; i < randBuffer.length ; i++ ) {
            randBuffer[i] = char_[randGen.nextInt (NUMBERSANDLETTERS.length ())];
        }
        return new String (randBuffer);
    }

    public static String getIP(String string){
        Pattern p = Pattern.compile ("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");
        Matcher m = p.matcher (string);
        if (m.find ()) { return m.group (); }
        return "";
    }

}
