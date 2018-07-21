package com.pm.domain.business;

import java.util.Date;

import com.common.utils.DateKit;
import com.pm.util.log.EntityAnnotation;

/**
 * 奖惩记录
 * @author zhonglihong
 * @date 2016年5月13日 下午5:51:10
 */
@SuppressWarnings("serial")
public class StaffRewardPenalty extends IdEntity {
	   
	private String staff_id ;  

	@EntityAnnotation(item_name="奖惩时间")
	private Date rp_time    ;  
	@EntityAnnotation(item_name="奖惩金额")
	private double amount   ;    
	@EntityAnnotation(item_name="备注")
	private String description ;    
	private Date build_datetime ; 
	private String build_userid ;   
	private String build_username  ;
	
	
	///////////////////////////////
	
	@EntityAnnotation(item_name="人员名称")
	private String staff_name ; 
	
	public String getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}
	public Date getRp_time() {
		return rp_time;
	}
	public void setRp_time(Date rp_time) {
		this.rp_time = rp_time;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getBuild_datetime() {
		return build_datetime;
	}
	public void setBuild_datetime(Date build_datetime) {
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
	public String getStaff_name() {
		return staff_name;
	}
	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
	}	

	@Override
	public String toString() {
		return staff_name ;
	}

}
