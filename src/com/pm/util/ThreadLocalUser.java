package com.pm.util;

import com.pm.domain.system.User;

/**
 * 本地线程类， 用于记录操作人员
 * @author zhonglihong
 * @date 2016年5月29日 下午11:33:58
 */
public class ThreadLocalUser {


	private static ThreadLocal<User> users = new ThreadLocal<User>(){
		public User initialValue(){
			return null;
		}
	};
	
	
	public static void setUser(User user){
		users.set(null);
		users.set(user);
	}
	

	public static User getUser(){
		return users.get();
	}

}
