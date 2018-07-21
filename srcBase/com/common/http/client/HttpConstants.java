package com.common.http.client;

/**
 * @ClassName: Constants
 * @Title:
 * @Description:常量
 * @Author:Hongli
 * @Since:2013-6-14上午9:40:12
 * @Version:1.0
 */
public final class HttpConstants {

    /**
     * @Fields METHOD_GET : GET
     */
    public final static String METHOD_GET    = "GET";
    /**
     * @Fields METHOD_PUT : PUT
     */
    public final static String METHOD_PUT    = "PUT";
    /**
     * @Fields METHOD_DELETE : DELETE
     */
    public final static String METHOD_DELETE = "DELETE";
    /**
     * @Fields METHOD_POST : POST
     */
    public final static String METHOD_POST   = "POST";
    /**
     * @Fields TIMEOUT : http 超时
     */
    public final static int    TIMEOUT       = 1000 * 60 * 30;

    public static final String CHARSET       = "UTF-8";
    public static final String X_AUTH_USER   = "X-Auth-User";
    public static final String X_AUTH_PASSWD = "X-Auth-Passwd";
    public static final String X_AUTH_TOKEN  = "X-Auth-Token";
    public static final String X_Console_ID  = "X-Console_ID";

    /**
     * <p>
     * Title: 私有构造函数
     * </p>
     * <p>
     * Description:禁止实例化
     * </p>
     */
    private HttpConstants() {}

}
