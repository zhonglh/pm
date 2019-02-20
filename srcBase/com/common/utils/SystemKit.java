package com.common.utils;

/**
 * @ClassName: SystemKit
 * @Title:
 * @Description:系统工具类
 * @Author:Hongli
 * @Since:2014年10月16日下午4:11:49
 * @Version:1.0
 */
public class SystemKit {

    private SystemKit() {}

    /**
     * @Title: getVendor
     * @Description: 取JDK作者
     * @Author: ZhongLiHong
     * @Since: 2014年10月16日下午4:12:10
     * @return
     */
    public static String getJdkVendor(){
        return System.getProperty ("java.vm.vendor");
    }

    /**
     * @Title: isIBMJDKVendor
     * @Description: 是否IBMjdk
     * @Author: ZhongLiHong
     * @Since: 2014年10月16日下午4:14:21
     * @return
     */
    public static boolean isIBMJDKVendor(){
        String vendor = getJdkVendor ();
        if (null != vendor && vendor.startsWith ("IBM")) return true;
        return false;
    }
}
