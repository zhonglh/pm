package com.pm.domain.business;

import java.sql.Timestamp;

public class BaseVO {
	

	

	
	//核单标记， 用于查询
	//1: 已核单，  2:未核单
	private String verify_flag;
	


	//是否申请 取消核单状态 1:已申请取消核单  其他:正常
	private String need_approve;
	

	
	
	private String errorInfo = "";
	
	

	private Timestamp build_datetime	;
	private String build_userid;	
	private String build_username;	
	private String verify_userid;	
	private String verify_username;	
	private Timestamp verify_datetime;
	
	

	public String getVerify_flag() {
		return verify_flag;
	}
	public void setVerify_flag(String verify_flag) {
		this.verify_flag = verify_flag;
	}
	public String getNeed_approve() {
		return need_approve;
	}
	public void setNeed_approve(String need_approve) {
		this.need_approve = need_approve;
	}
	public String getErrorInfo() {
		return errorInfo;
	}
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
	public Timestamp getBuild_datetime() {
		return build_datetime;
	}
	public void setBuild_datetime(Timestamp build_datetime) {
		this.build_datetime = build_datetime;
	}
	public String getBuild_userid() {
		return build_userid;
	}
	public void setBuild_userid(String build_userid) {
		this.build_userid = build_userid;
	}
	public String getBuild_username() {
		return build_username;
	}
	public void setBuild_username(String build_username) {
		this.build_username = build_username;
	}
	public String getVerify_userid() {
		return verify_userid;
	}
	public void setVerify_userid(String verify_userid) {
		this.verify_userid = verify_userid;
	}
	public String getVerify_username() {
		return verify_username;
	}
	public void setVerify_username(String verify_username) {
		this.verify_username = verify_username;
	}
	public Timestamp getVerify_datetime() {
		return verify_datetime;
	}
	public void setVerify_datetime(Timestamp verify_datetime) {
		this.verify_datetime = verify_datetime;
	}

}
