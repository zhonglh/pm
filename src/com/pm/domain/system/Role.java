package com.pm.domain.system;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.pm.util.log.EntityAnnotation;

@SuppressWarnings("serial")
public class Role  implements Serializable {
	
	private String role_id;	
	

	@EntityAnnotation(item_name="角色名称")
	private String role_name;
	
	/**
	 * 数据范围 1: 全部 2: 本部门  3:本人相关
	 */
	@EntityAnnotation(item_name="数据范围")
	private String data_range;	
	
	/**
	 * 角色类型 1: 系统角色 2:普通角色
	 */
	private String role_type;
	
	private Timestamp build_time;	
	private String build_userid;	
	private String build_username;
	private String delete_flag;	
	private Timestamp delete_datetime;	
	private String delete_userid;
	private String delete_username;
	
	
	//////////////////////////////////////////
	///////////////////扩展///////////////////
	//////////////////////////////////////////
	
	private List<String> permit_ids;
	private String permit_id;
	

	private boolean  selected = false;
	
	
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getData_range() {
		return data_range;
	}
	public void setData_range(String data_range) {
		this.data_range = data_range;
	}
	public String getRole_type() {
		return role_type;
	}
	public void setRole_type(String role_type) {
		this.role_type = role_type;
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
	public List<String> getPermit_ids() {
		return permit_ids;
	}
	public void setPermit_ids(List<String> permit_ids) {
		this.permit_ids = permit_ids;
	}
	public String getPermit_id() {
		return permit_id;
	}
	public void setPermit_id(String permit_id) {
		this.permit_id = permit_id;
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
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}
