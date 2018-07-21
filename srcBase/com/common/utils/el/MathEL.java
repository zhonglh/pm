package com.common.utils.el;

import java.math.RoundingMode;
import java.text.NumberFormat;

public class MathEL {

    public final static NumberFormat fixed2 = NumberFormat.getInstance ();
    static {
        fixed2.setGroupingUsed (false);
        fixed2.setRoundingMode (RoundingMode.DOWN);
    }

    public static double abs(double a){
        return Math.abs (a);
    }

    public static int absint(int a){
        return (int) Math.abs (a);
    }

    public static long ceil(double a){
        return (long) Math.ceil (a);
    }

    public static long floor(double input){
        return (long) Math.floor (input);
    }

    public static long round(double a){
        return Math.round (a);
    }

    public static String toFixed(double d,int f){
        fixed2.setMaximumFractionDigits (f);
        return fixed2.format (d);
    }

    public static String toByteSize(double d,int f){
        String ret = "";
        double gb, tb, pb;
        if ((gb = d / 0x100000L) < 1024) {
            ret = toFixed (gb, f) + " GB";
        } else if ((tb = d / 0x40000000L) < 1024) {
            ret = toFixed (tb, f) + " TB";
        } else if ((pb = tb / 1024d) > 0) {
            ret = toFixed (pb, f) + " PB";
        }
        return ret;
    }

    public static double max(double a,double b){
        return Math.max (a, b);
    }

    public static int maxint(int a,int b){
        return (int) Math.max (a, b);
    }

    public static double min(double a,double b){
        return Math.min (a, b);
    }

    public static int minint(int a,int b){
        return (int) Math.min (a, b);
    }

    public static double random(){
        return Math.random ();
    }
}
