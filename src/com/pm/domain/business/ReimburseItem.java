package com.pm.domain.business;

import java.io.Serializable;
import java.sql.Timestamp;

import com.pm.util.log.EntityAnnotation;

@SuppressWarnings("serial")
public class ReimburseItem implements Serializable {

	private String reimburse_item_id;

	@EntityAnnotation(item_name="报销类别")
	private String item_name;

	private Timestamp build_datetime;	
	private String 	build_userid;	
	private String 	build_username;
	private String 	delete_flag;	
	private Timestamp delete_datetime;
	private String 	delete_userid;	
	private String 	delete_username;
	
	
	public String getReimburse_item_id() {
		return reimburse_item_id;
	}
	public void setReimburse_item_id(String reimburse_item_id) {
		this.reimburse_item_id = reimburse_item_id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
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
}
