package com.common.enums;

import com.common.utils.StringKit;

/**
 * @ClassName: DeployEngineConstants
 * @Title:
 * @Description:部署引擎常量
 * @Author:Hongli
 * @Since:2014年6月25日上午11:00:56
 * @Version:1.0
 */
public enum DeployEngineConstants {
    /**
     * @Fields deploy_engine_ip : 部署引擎ip
     */
    deploy_engine_ip ("X-Auth-XcatIp", ""),
    /**
     * @Fields deploy_engine_user : 部署引擎用户
     */
    deploy_engine_user ("X-Auth-XcatUser", ""),
    /**
     * @Fields deploy_engine_paaswd : 部署引擎密码
     */
    deploy_engine_paaswd ("X-Auth-XcatPassword", ""),
    /**
     * @Fields deploy_engine_port : 部署引擎端口
     */
    deploy_engine_port ("X-Auth-XcatPort", "");

    private String key;

    private String value;

    private DeployEngineConstants(String key, String vlue) {
        this.value = vlue;
        this.key = key;
    }

    public String getKey(){
        return key;
    }

    public String getValue(){
        return value;
    }

    public static void init(String ip,String user,String passwd){
        init (ip, user, passwd, "0");
    }

    public static synchronized void init(String ip,String user,String passwd,String port){
        DeployEngineConstants.deploy_engine_ip.value = StringKit.isBlank (ip) ? "" : ip;
        DeployEngineConstants.deploy_engine_user.value = StringKit.isBlank (user) ? "" : user;
        DeployEngineConstants.deploy_engine_paaswd.value = StringKit.isBlank (passwd) ? "" : passwd;
        DeployEngineConstants.deploy_engine_port.value = StringKit.isBlank (port) ? "" : port;
    }
}
