package com.common.exceptions;

/**
 * 异常编码接口 异常编码规则 1)编码长度为6位； 2)第一位用来表示异常发生在哪个层级或系统中（如：1－虚拟化层、2－虚拟化支持层、3－业务层, 4 -rest）； 3)第二位和第三位用来表示异常的分类。分类包括：01－平台、02－主机、03－VIOS、04－存储（VG/LV/PV）、 05－虚拟机、06－网络或网卡适配器、07-模板 11-SSP 12-EXSTO 13-NPIV
 * 20－数据库、21－文件、22－脚本、99－其他； 4)其余位数用来表示顺序号。
 * 
 * @author Administrator
 * @version 1.0
 * @created 20-七月-2012 17:28:54
 */
public interface ExceptionCode {

    /**
     * 平台licence无效
     */
    public static int PLATFORM_LICENCE_INVALID    = 301001;

    /**
     * 平台已经存在
     */
    public static int PLATFORM_ALREADY_REGISTERED = 201001;

    /**
     * 平台没有
     */
    public static int PLATFORM_NOT_FOUND          = 201002;

    /**
     * 正在同步中
     */
    public static int PLATFORM_SYNC_ING           = 201004;

    /****************************** 主机类型 *********************************/

    /**
     * 没有找到主机
     */
    public static int HOST_NOT_FOUND              = 102001;

    /***************************** 虚拟机类型 ********************************/

    /**
     * 模板格式错误异常
     */
    public static int TEMPLATE_FORMAT_EXCEPT      = 105053;

    public static int VM_NOT_FOUND_               = 105067;

    /**
     * 对虚拟机进行操作时已经有一个时间很长的操作作用在虚拟机上
     */
    public static int VM_TASK_IN_PROGRESS_TIMEOUT = 205003;

    /**
     * 虚机不存在
     */
    public static int VM_NOT_FOUND                = 205005;

    /***************************** SSP ************************/

    /****************** 集群 **************/
    /**
     * 添加节点异常
     */
    public static int ADD_NODE_EXCEPT             = 115014;

    /**
     * 主节点信息为空
     */
    public static int MASTER_NODE_IS_EMPTY        = 115018;

    /****************** 数据库类型（L2和L3都使用，这里需分别定义） ***************/
    /**
     * 无法获取数据库连接
     */
    public static int DB_GET_NOT_CONNECT          = 220001;
    /**
     * 数据库插入数据异常
     */
    public static int DB_INSERT_EXCEPT            = 220002;
    public static int DB_INSERT_EXCEPT_3          = 320002;
    /**
     * 数据库修改数据异常
     */
    public static int DB_UPDATE_EXCEPT            = 220003;
    /**
     * 数据库删除数据异常
     */
    public static int DB_DELETE_EXCEPT            = 220004;
    /**
     * 数据库执行sql语句异常
     */
    public static int DB_SQL_EXCEPT               = 220005;

    /******************************** 文件类型 ******************************/
    /**
     * 文件内容格式不合法，解析文件内容时出现错误
     */
    public static int FILE_FORMAT_ERROR           = 221001;

    /**
     * 文件不存在
     */
    public static int FILE_NOT_FOUND              = 221002;

    /**
     * IO输入输出流异常
     */
    public static int IO_ERROR                    = 121001;

    public static int IMAGE_NOT_FOUND             = 121001;

    /****************************** 脚本类型 ********************************/
    /**
     * 脚本执行异常
     */
    public static int SCRIPT_ERROR                = 122001;

    /***************************** 其他类型 ********************************/
    /**
     * 运行时未知异常
     */
    public static int UNKNOWN_EXCEPT              = 199001;
    /**
     * 解析JSON对像失败
     */
    public static int JSON_PARSER_ERROR           = 199002;
    /**
     * 参数为null
     */
    public static int PARAM_NULL                  = 199003;

    public static int MALFORMED_URL               = 199004;

    public static int GET_CONNECT                 = 199005;

    /**
     * 没有查询到原始对象唯一标识,请确认传入的标识是否存在
     */
    public static int CODE_NO_ORGOBJECTID         = 299001;

    /**
     * 创建虚拟机已经达到最大并发数
     */
    public static int VM_CREATE_MAX_ERROR         = 402021;

    /**
     * 该虚拟机正在操作
     */
    public static int VM_OPERATING_ERROR          = 402022;

    // //////////EXSTO(外置存储)异常编码列表/////////////////////
    // L1层
    /**
     * 脚本执行异常
     */
    public static int EXSTO_SCRIPT_ERROR          = 112001;

    // L2层

    /**
     * 数据库相应的记录不存在
     */
    public static int EXSTO_DB_RECORD_NOT_EXISTS  = 212002;

    /**
     * 存储控制器刷新异常
     */
    public static int EXSTO_CTRL_REFRESH_ERROR    = 212003;

    /**
     * 卷在实际环境中不存在
     */
    public static int EXSTO_LUN_NOT_EXISTS        = 212004;

    /**
     * 卷的大小只能增大
     */
    public static int EXSTO_LUN_INCREASE_ERROR    = 212005;

    /**
     * 卷的大小
     */
    public static int EXSTO_HOST_NOT_EXISTS_ERROR = 212006;

    /**
     * 刷新存储控制器异常
     */
    public static int EXSTO_REFRESH_ERROR         = 212007;

    /**
     * 删除实际环境的存储主机发生异常
     */
    public static int EXSTO_DELETE_HOST_ERROR     = 212008;

    /************* 网络错误 L2 ****************/
    /** VIOS的详细信息未找到 */
    public static int NETWORK_VIOS_NOT_FOUND      = 206001;

    /** L3层调用时缺少关联关系的PCID */
    public static int NETWORK_PARAM_LACK          = 206002;

    /******* tebe Client客户端 *******/
    /**
     * @Fields 服务器内部异常
     */
    public static int PD3_INTERNAL_EXCEPTION      = 1021001;
    /** 编码异常 */
    public static int PD3_CHARSET_EXCEPTION       = 1021002;
    /** 服务器超时 */
    public static int PD3_TIMEOUT_EXCEPTION       = 1021003;

    public static int PD3_CAPTURE_EXCEPTION       = 1021004;

    public static int PD3_IO_EXCEPTION            = 1021004;

    public static int PD3_TOKEN_EMPTY             = 1021005;

}