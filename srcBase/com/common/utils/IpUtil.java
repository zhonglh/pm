package com.common.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

/**
 * IP工具类
 * 
 * @author Riche 2012-08-10
 */
public class IpUtil {

    private static final int TWO           = 2;

    private static final int THREE         = 3;

    private static final int FOUR          = 4;

    private static final int EIGHT         = 8;

    private static final int ONE_SIX       = 16;

    private static final int TWO_FOUR      = 24;

    private static final int TWO_FIVE_FIVE = 255;

    private static final int SIX_F         = 0x00FFFFFF;

    private static final int FOUR_F        = 0x0000FFFF;

    private static final int TWO_F         = 0x000000FF;

    /**
     * @param request
     *            IP
     * @return IP Address
     */
    public static String getIpAddrByRequest(HttpServletRequest request){
        String ip = request.getHeader ("x-forwarded-for");
        if (ip == null || ip.length () == 0 || "unknown".equalsIgnoreCase (ip)) {
            ip = request.getHeader ("Proxy-Client-IP");
        }
        if (ip == null || ip.length () == 0 || "unknown".equalsIgnoreCase (ip)) {
            ip = request.getHeader ("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length () == 0 || "unknown".equalsIgnoreCase (ip)) {
            ip = request.getRemoteAddr ();
        }
        return ip;
    }

    /**
     * @return 本机IP
     * @throws SocketException
     */
    public static String getRealIp() throws SocketException{
        String localip = null;// 本地IP，如果没有配置外网IP则返回它
        String netip = null;// 外网IP

        Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces ();
        InetAddress ip = null;
        boolean finded = false;// 是否找到外网IP
        while (netInterfaces.hasMoreElements () && !finded) {
            NetworkInterface ni = netInterfaces.nextElement ();
            Enumeration<InetAddress> address = ni.getInetAddresses ();
            while (address.hasMoreElements ()) {
                ip = address.nextElement ();
                if (!ip.isSiteLocalAddress () && !ip.isLoopbackAddress () && ip.getHostAddress ().indexOf (":") == -1) {// 外网IP
                    netip = ip.getHostAddress ();
                    finded = true;
                    break;
                } else if (ip.isSiteLocalAddress () && !ip.isLoopbackAddress () && ip.getHostAddress ().indexOf (":") == -1) {// 内网IP
                    localip = ip.getHostAddress ();
                }
            }
        }

        if (netip != null && !"".equals (netip)) {
            return netip;
        } else {
            return localip;
        }
    }

    /**
     * @Title: IP2Long
     * @Description:将127.0.0.1形式的IP地址转换成十进制整数，这里没有进行任何错误处理
     * @Author: Hongli
     * @Since: 2014年7月2日上午11:39:34
     * @param IPStr
     * @return
     */
    public static long IP2Long(String strIp){
        if (strIp == null) return 0;
        long[] ip = new long[FOUR];
        // 先找到IP地址字符串中.的位置
        int position1 = strIp.indexOf (".");
        int position2 = strIp.indexOf (".", position1 + 1);
        int position3 = strIp.indexOf (".", position2 + 1);
        // 将每个.之间的字符串转换成整型
        try {
            ip[0] = Long.parseLong (strIp.substring (0, position1));
            ip[1] = Long.parseLong (strIp.substring (position1 + 1, position2));
            ip[TWO] = Long.parseLong (strIp.substring (position2 + 1, position3));
            ip[THREE] = Long.parseLong (strIp.substring (position3 + 1));
        } catch (Exception e) {
            return 0;
        }
        boolean errorFlag = ip[0] > TWO_FIVE_FIVE || ip[1] > TWO_FIVE_FIVE || ip[TWO] > TWO_FIVE_FIVE || ip[THREE] > TWO_FIVE_FIVE;
        if (errorFlag || ip[THREE] < 0 || ip[TWO] < 0 || ip[1] < 0 || ip[0] < 0) { return 0; }
        return (ip[0] << TWO_FOUR) + (ip[1] << ONE_SIX) + (ip[TWO] << EIGHT) + ip[THREE];
    }

    /**
     * @Title: Long2Binary
     * @Description: 将IP地址十进制整数转化成二进制，这里没有进行任何错误处理
     * @Author: Hongli
     * @Since: 2014年7月2日上午11:39:28
     * @param longIP
     * @return
     */
    public static String Long2Binary(long longIP){
        return Long.toBinaryString (longIP);
    }

    /**
     * @Title: Long2IP
     * @Description:将十进制整数形式转换成127.0.0.1形式的ip地址
     * @Author: Hongli
     * @Since: 2014年7月2日上午11:39:16
     * @param longIP
     * @return
     */
    public static String Long2IP(long longIp){
        StringBuffer sb = new StringBuffer ("");
        // 直接右移24位
        sb.append (String.valueOf (longIp >>> TWO_FOUR));
        sb.append ('.');
        // 将高8位置0，然后右移16位
        sb.append (String.valueOf ((longIp & SIX_F) >>> ONE_SIX));
        sb.append ('.');
        // 将高16位置0，然后右移8位
        sb.append (String.valueOf ((longIp & FOUR_F) >>> EIGHT));
        sb.append ('.');
        // 将高24位置0
        sb.append (String.valueOf (longIp & TWO_F));
        boolean errFlag = (longIp >>> TWO_FOUR) > TWO_FIVE_FIVE || ((longIp & SIX_F) >>> ONE_SIX) > TWO_FIVE_FIVE || ((longIp & FOUR_F) >>> EIGHT) > TWO_FIVE_FIVE;
        errFlag = errFlag || TWO_FIVE_FIVE < (longIp & TWO_F) || 0 > (longIp >>> TWO_FOUR) || 0 > ((longIp & SIX_F) >>> ONE_SIX);
        if (errFlag || 0 > ((longIp & FOUR_F) >>> EIGHT) || 0 > (longIp & TWO_F)) { return null; }
        return sb.toString ();
    }

    /**
     * 是否是本地IP
     * 
     * @param strIp
     * @return
     */
    public static boolean isLocal(String strIp){
        if ("127.0.0.1".equals (strIp)) return true;
        long l = IP2Long (strIp);
        if (l >= 3232235520L) return l <= 3232301055L;
        return (l >= 167772160L) && (l <= 184549375L);
    }

    /**
     * 检查所给的是否为一个合法的IP地址<br>
     * 
     * @param ip
     *            IP地址
     * @return 是否合法
     */
    public static boolean ipValid(String ip){
        if (null == ip) return false;
        //String regex = "\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b";
        //String regex = "^([1-9]|[1-9]\\d|1\\d{2}|2[0-1]\\d|22[0-3]).(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])" + ".(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5]).(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])$";
        String regex = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
        Pattern p = Pattern.compile (regex);
        Matcher m = p.matcher (ip);
        return m.matches ();
    }

   public static void main(String[] args){
    System.out.println (ipValid ("256.1.1.2551"));
    System.out.println (Long2IP (111111111));
}
}
