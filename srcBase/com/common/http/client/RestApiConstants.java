package com.common.http.client;

import com.common.utils.file.PropertyConfig;

public enum RestApiConstants {
    // 根URL
    python_base_url (""),
    /**
     * @Fields python_get_token : 获取token
     */
    python_get_token (""),
    /**
     * @Fields python_token_user : 获取token用户名
     */
    python_token_user (""),
    /**
     * @Fields python_token_pwd : 获取token密码
     */
    python_token_pwd (""),

    /**
     * @Fields python_xcat_check : 检查xcat是否可用
     */
    python_xcat_check (""),
    /**
     * @Fields python_image_import :导入镜像
     */
    python_image_import (""),
    /**
     * @Fields python_polling_log : python轮询日志
     */
    python_polling_log (""),

    /**
     * @Fields get_fsp_list : 获取FSP列表
     */
    get_fsp_list (""),
    /**
     * @Fields get_fsp_detail 获取FSP详情
     */
    get_fsp_detail (""),
    /**
     * @Fields get_fsp_detail_name : 获取某个fsp详情
     */
    get_fsp_detail_name (""),
    /**
     * @Fields add_fsp 添加fsp
     */
    add_fsp (""),
    /**
     * @Fields alter_fsp_ip 修改fsp的ip
     */
    alter_fsp_ip (""),
    /**
     * @Fields startup_host 启动主机
     */
    startup_host (""),
    /**
     * @Fields shutdown_host 关闭主机
     */
    shutdown_host (""),
    /**
     * @Fields shutdown_host 重启主机
     */
    reboot_host (""),
    /**
     * @Fields delete_fsp 删除fsp
     */
    delete_fsp (""),
    /**
     * @Fields queyr_lpar_list 获取lpar列表
     */
    query_lpar_list (""),
    /**
     * @Fields get_lpar_detail 获取lpar列表
     */
    get_lpar_detail (""),
    /**
     * @Fields create_lpar 新创建 lpar
     */
    create_lpar (""),
    /**
     * @Fields add_lpar 境加 lpar
     */
    add_lpar (""),
    /**
     * @Fields delete_lpar 删除 lpar
     */
    delete_lpar (""),
    /**
     * @Fields edit_lpar 编辑lpar
     */
    edit_lpar (""),
    /**
     * @Fields conf_lpar_mutual_trust 配置 lpar互信
     */
    conf_lpar_mutual_trust (""),
    /**
     * @Fields startup_lpar 启动lpar
     */
    startup_lpar (""),
    /**
     * @Fields shutdown_lpar 关闭动lpar
     */
    shutdown_lpar (""),

    /**
     * @Fields reboot_lpar 重启lpar
     */
    reboot_lpar (""),
    /**
     * @Fields lpar_relevance_osimage lpar关联osimage
     */
    lpar_relevance_osimage (""),
    /**
     * @Fields _NIC_Teaming 聚合网卡
     */
    _NIC_Teaming (""),
    /**
     * @Fields Install_operation_system 安装操作系统
     */
    Install_operation_system (""),
    /**
     * @Fields chec_processes 查看安装操作系统进度
     */
    chec_processes (""),
    /**
     * @Fields import_image 导入镜像
     */
    import_image (""),
    /**
     * @Fields delete_image 删除镜像
     */
    delete_image (""),
    /**
     * @Fields check_xcat_available 检查xcat是否可用
     */
    check_xcat_available (""),

    /** ----------------------------IVM----------------------------------- */
    /**
     * @Fields get_ivm_info 获取ivm信息
     */
    get_ivm_info (""),
    /**
     * @Fields add_ivm 添加ivm
     */
    add_ivm (""),
    /**
     * @Fields conf_ivm_mutual_trust :配置ivm互信
     */
    conf_ivm_mutual_trust (""),
    /**
     * @Fields shutdown_ivm_host :关闭ivm主机
     */
    shutdown_ivm_host (""),
    /**
     * @Fields get_ivms :获取vm
     */
    get_vms (""),
    /**
     * @Fields get_vms_detail :获取vm
     */
    get_vms_detail (""),
    /**
     * @Fields add_vm :获取vm
     */
    add_vm (""),
    /**
     * @Fields delete_vm :获取vm
     */
    delete_vm (""),
    /**
     * @Fields conf_vm_mutual_trust :配置vm互信
     */
    conf_vm_mutual_trust (""),
    /**
     * @Fields start_vm :配置vm互信
     */
    start_vm (""),
    /**
     * @Fields shutdown_vm :配置vm互信
     */
    shutdown_vm (""),
    /**
     * @Fields reboot_vm :重启vm
     */
    reboot_vm (""),
    /**
     * @Fields modify_vm_ip :修改vm ip
     */
    modify_vm_ip (""),
    /**
     * @Fields vm_associated_osimage :修改vm ip
     */
    vm_associated_osimage (""),
    /**
     * @Fields python_image_delete :镜像删除
     */
    python_image_delete ("");

    private String value;

    private RestApiConstants(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public void setValue(String value){
        this.value = value;
    }

    public String getUrl(){
        return python_base_url.value + value;
    }

    static {
        init ();
    }

    public static synchronized void init(){
        PropertyConfig.me ().loadPropertyFile ("restapi.txt");
        RestApiConstants[] apis = RestApiConstants.values ();
        for ( RestApiConstants api : apis ) {
            api.value = PropertyConfig.me ().getProperty (api.name (), api.value);
        }
    }

}
