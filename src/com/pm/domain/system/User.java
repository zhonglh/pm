package com.pm.domain.system;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.pm.util.log.EntityAnnotation;


/**
 * 用户信息， 对应 ts_user
 * @author zhonglh
 *
 */
@SuppressWarnings("serial")
public class User implements Serializable {
	
	private String 	user_id	;

	private String 	staff_id	;
	private String 	staff_type	;
	

	@EntityAnnotation(item_name="用户名称")
	private String 	user_name	;
	private String 	user_deptid	;

	@EntityAnnotation(item_name="登录名")
	private String 	user_loginname	;
	private String 	user_password	;

	@EntityAnnotation(item_name="描述")
	private String 	description		;
	private Timestamp 	build_time;	
	private String 	build_userid	;
	private String 	build_username	;
	private String 	delete_flag	;
	private Timestamp 	delete_datetime	;
	private String 	delete_userid	;
	private String 	delete_username	;


	////////////////////////////////////////
	////////////以下是扩展的//////////////////
	////////////////////////////////////////
	
	//部门名称
	@EntityAnnotation(item_name="部门名称")
	private String user_deptname;
	
	List<String> role_ids ;
	String role_id;
	

	String permit_id;
	
	
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_deptid() {
		return user_deptid;
	}
	public void setUser_deptid(String user_deptid) {
		this.user_deptid = user_deptid;
	}
	public String getUser_loginname() {
		return user_loginname;
	}
	public void setUser_loginname(String user_loginname) {
		this.user_loginname = user_loginname;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	
	public Timestamp getBuild_time() {
		return build_time;
	}
	public void setBuild_time(Timestamp build_time) {
		this.build_time = build_time;
	}
	public String getBuild_userid() {
		return build_userid;
	}
	public void setBuild_userid(String build_userid) {
		this.build_userid = build_userid;
	}
	public String getDelete_flag() {
		return delete_flag;
	}
	public void setDelete_flag(String delete_flag) {
		this.delete_flag = delete_flag;
	}
	public Timestamp getDelete_datetime() {
		return delete_datetime;
	}
	public void setDelete_datetime(Timestamp delete_datetime) {
		this.delete_datetime = delete_datetime;
	}
	public String getDelete_userid() {
		return delete_userid;
	}
	public void setDelete_userid(String delete_userid) {
		this.delete_userid = delete_userid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBuild_username() {
		return build_username;
	}
	public void setBuild_username(String build_username) {
		this.build_username = build_username;
	}
	public String getDelete_username() {
		return delete_username;
	}
	public void setDelete_username(String delete_username) {
		this.delete_username = delete_username;
	}
	
	@Override
	public String toString() {
		return String
				.format("User [getUser_id()=%s, getUser_name()=%s, getUser_deptid()=%s, getUser_loginname()=%s, getUser_password()=%s, getBuild_time()=%s, getBuild_userid()=%s, getDelete_flag()=%s, getDelete_datetime()=%s, getDelete_userid()=%s, getDescription()=%s, getBuild_username()=%s, getDelete_username()=%s]",
						getUser_id(), getUser_name(),
						getUser_deptid(), getUser_loginname(),
						getUser_password(), getBuild_time(),
						getBuild_userid(), getDelete_flag(),
						getDelete_datetime(), getDelete_userid(),
						getDescription(), getBuild_username(),
						getDelete_username());
	}
	public String getUser_deptname() {
		return user_deptname;
	}
	public void setUser_deptname(String user_deptname) {
		this.user_deptname = user_deptname;
	}
	public List<String> getRole_ids() {
		return role_ids;
	}
	public void setRole_ids(List<String> role_ids) {
		this.role_ids = role_ids;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	public String getPermit_id() {
		return permit_id;
	}
	public void setPermit_id(String permit_id) {
		this.permit_id = permit_id;
	}
	public String getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}
	public String getStaff_type() {
		return staff_type;
	}
	public void setStaff_type(String staff_type) {
		this.staff_type = staff_type;
	}
	
	
	


}
