package com.pm.util.excel;


public class ThreadLocalHolder {


	private static ThreadLocal<Class> clzThreadLocal = new ThreadLocal<Class>(){
		@Override
		public Class initialValue(){
			return null;
		}
	};
	
	
	public static void setClz(Class clz){
		clzThreadLocal.set(null);
		clzThreadLocal.set(clz);
	}
	

	public static Class getClz(){
		return clzThreadLocal.get();
	}

}
