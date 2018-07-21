package com.pm.domain.business;

import java.io.Serializable;
import java.sql.Timestamp;

import com.pm.util.log.EntityAnnotation;

@SuppressWarnings("serial")
public class Client implements Serializable {

	private String client_id;
	


	@EntityAnnotation(item_name="客户编号")
	private String client_no;
	
	@EntityAnnotation(item_name="客户名称")
	private String client_name;

	private Timestamp build_datetime;	
	private String 	build_userid;	
	private String 	build_username;
	private String 	delete_flag;
	private Timestamp delete_datetime;
	private String 	delete_userid;	
	private String 	delete_username;
	
	
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
	public String getDelete_username() {
		return delete_username;
	}
	public void setDelete_username(String delete_username) {
		this.delete_username = delete_username;
	}
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	
	
	public String getClient_name() {
		return client_name;
	}
	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}
	public String getClient_no() {
		return client_no;
	}
	public void setClient_no(String client_no) {
		this.client_no = client_no;
	}	
}
