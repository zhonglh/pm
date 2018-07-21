package com.pm.util.constant;

import java.util.ArrayList;
import java.util.List;

import com.common.utils.ConstantUtil;

public class BusinessUtil {
	
	/**
	 * Admin的ID
	 */
	public final static String ADMIN_USER_ID = "1";
	
	
	/**
	 * 两个日期的分隔符
	 */
	public final static String SPLIT_DATE= "---";
	
	/**
	 * 身份证号码的正则
	 */
	public static final String IDCARD = "^\\d{17}([0-9]|X|x)$";
	
	//默认基本工资
	public static double DEFAULT_BASIC_SALARY = 2000;
	
	
	

    /**
     * 营销模式 全局设置 ID
     */
	public static final  String MARKET_SETS_ID = "1";
	
	public static final int MAX_LEVEL = 100;
	
	/**
	 * 图表名称
	 */
	public static final String[] orgName = {"收入部分","支出部分","整体情况"};
	
	
	
	/**
	 * 参数， 一级大类
	 */
	public final static String PARAM_GROUP_SALARY = "salary";

	
	public final static String LOG_PACKAGE = "com.pm.util.log";

	public final static String SPLITSTRING= "___";
	
	public final static String COOKIE_LOGINNAME ="loginName";
	
	public final static String SESSION_USER = "session_user";
	public final static String USER_LOGIN_NAME    = "username";
	public final static String USER_LOGIN_PASSWD  = "password";
	
	public final static String DELETEED  = "1";
	public final static String NOT_DELETEED  = "0";
	

	public final static String ROLE_TYPE_SYSTEM  = "1";
	public final static String ROLE_TYPE_GENERAL  = "2";
	
	public final static int PAGE_SIZE = ConstantUtil.PAGE_SIZE;
	public final static int MAX_SIZE = ConstantUtil.MAX_SIZE;
	
	
	public final static String STATEMENT_TYPE_1 = "1";
	public final static String STATEMENT_TYPE_2 = "2";
	public final static String STATEMENT_TYPE_3 = "3";
	public final static String STATEMENT_TYPE_4 = "4";
	

	public final static String DATA_RANGE_ALL  = "1";
	public final static String DATA_RANGE_DEPT  = "2";
	public final static String DATA_RANGE_USER  = "3";
	
	
	public final static String SEND_SALARY_MAIL_COMPLETE="1";
	public final static String SEND_SALARY_MAIL_WAIT="2";
	

	public final static String SEND_SALARY_MAIL_SUCCESS="1";
	public final static String SEND_SALARY_MAIL_FAILURE="0";
	
	
	public final static String DT_FORMAT = "yyyyMMdd";
	
	/**
	 * 人员默认可以发送信息 
	 */
	public final static String STAFF_CAN_SEND_MESSAGE="1";
	

	public final static List<String> EXCEL_TYPE = new ArrayList<String>();//{"XLS","xls","XLSX","xlsx"};
	
	static {
		EXCEL_TYPE.add("xls");
		EXCEL_TYPE.add("xlsx");
		EXCEL_TYPE.add("XLSX");
		EXCEL_TYPE.add("XLS");
	}


}
