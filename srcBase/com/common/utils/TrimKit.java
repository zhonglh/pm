package com.common.utils;

import com.common.utils.StringKit;

public class TrimKit {

    public static String getString(String str){
        if (str != null) return str.trim ();
        return null;
    }

    public static int getInt(String str){
        if (str != null && !StringKit.isBlank (str.trim ())) return Integer.parseInt (str.trim ());
        return 0;
    }

    public static double getDouble(String str){
        if (str != null && !StringKit.isBlank (str.trim ())) return Double.parseDouble (str.trim ());
        return 0;
    }

    public static float getFloat(String str){
        if (str != null && !StringKit.isBlank (str.trim ())) return Float.parseFloat (str.trim ());
        return 0;
    }

    public static Long getLong(String str){
        if (str != null && !StringKit.isBlank (str.trim ())) return Long.parseLong (str.trim ());
        return 0l;
    }

    public static boolean getBoolean(String str){
        if (str != null && !StringKit.isBlank (str.trim ())) return Boolean.parseBoolean (str.trim ());
        return false;
    }
}
