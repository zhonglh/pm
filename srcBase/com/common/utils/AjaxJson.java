package com.common.utils;

import java.io.Serializable;

public class AjaxJson implements Serializable{

	private static final long serialVersionUID = 8682544193005274335L;

	private boolean isOk = true;
	private String msg ;
	
	public boolean isOk() {
		return isOk;
	}
	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
