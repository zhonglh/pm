package com.common.filters;

import java.util.Locale;

public class ThreadLocalHolder {

    private static final ThreadLocal<Locale> locale = new ThreadLocal<Locale> () {

                                                        public Locale initialValue(){
                                                            return Locale.CHINESE;
                                                        }
                                                    };

    public static Locale getLocale(){
        return locale.get ();
    }

    public static void setLocale(Locale local){
        if (null == local) local = Locale.CHINA;
        locale.set (local);
    }
}
