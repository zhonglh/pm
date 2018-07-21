package com.common.constants;

public class StaticConstant {

    private StaticConstant() {}

    /** 设定平台类型,供树展现用 */
    public static final String OBJECT_TYPE_PLATFORM                    = "platform";

    /** 设定主机类型,供树展现用 */
    public static final String OBJECT_TYPE_HOST                        = "host";

    /** 设定lpar类型,供树展现用 */
    public static final String OBJECT_TYPE_LPAR                        = "lpar";

    /** 日志操作目标类型 */

    /**
     * @Fields LOG_OPERATION_ENTITY_TYPE_OS_IMAGE : OS image
     */
    public static final String LOG_OPERATION_ENTITY_TYPE_OS_IMAGE      = "OS_IMAGE";
    /**
     * @Fields LOG_OPERATION_ENTITY_TYPE_SOFT_BUNDLE : 软件包
     */
    public static final String LOG_OPERATION_ENTITY_TYPE_SOFT_BUNDLE   = "SOFT_BUNDLE";
    /**
     * @Fields LOG_OPERATION_ENTITY_TYPE_DEPLOY_ENGINE : 部署引擎
     */
    public static final String LOG_OPERATION_ENTITY_TYPE_DEPLOY_ENGINE = "DEPLOY_ENGINE";

    /**
     * @Fields LOG_OPERATION_ENTITY_TYPE_CRON_JOB : 定时任务
     */
    public static final String LOG_OPERATION_ENTITY_TYPE_CRON_JOB      = "CRON_JOB";

    /**
     * @Fields LOG_OPERATION_ENTITY_USER : 用户
     */
    public static final String LOG_OPERATION_ENTITY_USER               = "USER";

    public static final String LOG_OPERATION_ENTITY_PLATFORM           = "PLATFORM";
    public static final String LOG_OPERATION_ENTITY_HOST               = "HOST";
    public static final String LOG_OPERATION_ENTITY_LPAR               = "LPAR";

    public static final String LOG_OPERATION_TYPE_ADD                  = "ADD";                                    // 增加
    public static final String LOG_OPERATION_TYPE_DEPLOY               = "DEPLOY";                                 // 部署
    public static final String LOG_OPERATION_TYPE_UPDATE               = "UPDATE";                                 // 更新
    public static final String LOG_OPERATION_TYPE_DELETE               = "DELETE";                                 // 删除
    public static final String LOG_OPERATION_TYPE_CANCEL               = "CANCEL";                                 // 注销
    public static final String LOG_OPERATION_TYPE_LOCK                 = "LOCK";                                   // 锁定
    public static final String LOG_OPERATION_TYPE_UNLOAD               = "UNLOAD";                                 // 卸载
    public static final String LOG_OPERATION_TYPE_UPLOAD               = "UPLOAD";                                 // 上传
    public static final String LOG_OPERATION_TYPE_IMPORT               = "IMPORT";                                 // 导入
    public static final String LOG_OPERATION_TYPE_EDIT                 = "EDIT";                                   // 编辑
    public static final String LOG_OPERATION_TYPE_UPGRADE              = "UPGRADE";                                // 升级
    public static final String LOG_OPERATION_TYPE_START                = "START";                                  // 启动
    public static final String LOG_OPERATION_TYPE_STOP                 = "STOP";                                   // 停止
    public static final String LOG_OPERATION_TYPE_ALTERPASSWORD        = "ALTERPASS";                              // 改密友
    public static final String LOG_OPERATION_TYPE_RESETPASSWORD        = "RESETPASS";                              // 重置密友

    public static final String LOG_OPERATION_TYPE_REGISTER             = "REGISTER";                               // 注册
    public static final String LOG_OPERATION_TYPE_RENAME               = "RENAME";                                 // 重命名
    public static final String LOG_OPERATION_TYPE_REFRESH              = "REFRESH";                                // 刷新
    public static final String LOG_OPERATION_TYPE_UPDATE_IP            = "UPDATEIP";                               // 修改IP
    public static final String LOG_OPERATION_TYPE_SETUP_TRUST          = "SETUPTRUST";                             // 互信
    public static final String LOG_OPERATION_TYPE_INSTALL_OS           = "INSTALLOS";                              // 安装操作系统
    public static final String LOG_OPERATION_TYPE_UPDATE_OS_INFO       = "UPDATEOSINFO";                           // 关联系统映像
    public static final String LOG_OPERATION_TYPE_RESTART              = "RESTART";                                // 重启
    public static final String LOG_OPERATION_TYPE_CREATE               = "CREATE";                                 // 新建
    public static final String LOG_OPERATION_TYPE_OPEN_CONSOLE         = "OPENCONSOLE";                            // 打开控制台
    public static final String LOG_OPERATION_TYPE_AGGREGATENETWORKCARD = "AGGREGATENETWORKCARD";//聚合网卡
    /*注册主机 lpar错误信息*/
    /*数据格式错误*/
    public static final String REGISTER_RESOLVE_HOST_DATA_FORMAT_ERROR = "REGISTER_RESOLVE_HOST_DATA_FORMAT_ERROR";
    /*机架编号重复*/
    public static final String REGISTER_RESOLVE_HOST_RACK_NUMBER_ERROR = "REGISTER_RESOLVE_HOST_RACK_NUMBER_ERROR";

    /*用户重置的密码*/
    public static final String USER_RESET_PASSWORD                     = "111111";

    // MessageJson的flag值
    public static final String SUCCESS_FLAG                            = "1";
    // MessageJson的flag值
    public static final String FAILURE_FLAG                            = "0";

    // 验证 名称 ip 等是否被使用
    // 未使用
    public static final String NOTUSE_FLAG                             = "1";
    // 已使用
    public static final String USE_FLAG                                = "2";

    // 平台类型Platform type (0:IVM; 1:HMC; 2:XCAT)
    public static final String PLATFORM_TYPE_IVM                       = "0";
    public static final String PLATFORM_TYPE_HMC                       = "1";
    public static final String PLATFORM_TYPE_XCAT                      = "2";

    // 日志操作状态 1 成功 2 失败

    public static final String LOG_OPERATION_STATUS_SUCCESS            = "1";
    public static final String LOG_OPERATION_STATUS_FAILURE            = "2";

    /**
     * @Fields OTHER_EXCETOPON_END_CHARS : 其它异常结尾字符
     */
    public static final String OTHER_EXCETOPON_END_CHARS               = "99";
}
