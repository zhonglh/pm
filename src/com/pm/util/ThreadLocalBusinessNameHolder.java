package com.pm.util;

/**
 * 本地线程类， 用于Excel 导入导出 时  列的分组， 用于一个JavaBean对应多个业务
 * @author zhonglihong
 * @date 2016年5月29日 下午11:33:58
 */
public class ThreadLocalBusinessNameHolder {


	private static ThreadLocal<String> tl = new ThreadLocal<String>(){
		public String initialValue(){
			return null;
		}
	};
	
	
	public static void setBusinessName(String businessName){
		tl.set(null);
		tl.set(businessName);
	}
	

	public static String getBusinessName(){
		return tl.get();
	}

}
