package com.common.utils;

import java.util.UUID;

public class IDKit {

    public static String generateId(){
        return generateId (null);
    }

    /**
     * 生成ID type + uuid
     * 
     * @param type
     * @return
     */
    public static String generateId(String type){
        if (type == null) return UUID.randomUUID ().toString ();
        else {
            StringBuffer sb = new java.lang.StringBuffer (type).append (UUID.randomUUID ());
            return sb.toString ();
        }
    }

    public static String getUUID(){
        return UUID.randomUUID ().toString ();
    }

    public static String getUUIDCurrTime(){
        return System.currentTimeMillis () + UUID.randomUUID ().toString ();
    }

    public static String generateUUID(){
        return UUID.randomUUID ().toString ().replace ("-", "");
    }
}
