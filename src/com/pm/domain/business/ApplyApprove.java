package com.pm.domain.business;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("serial")
public class ApplyApprove implements Serializable {

	 private String id;               
	 private String user_id;          
	 private String user_name;        
	 private Timestamp operatioin_time;  
	 private String operation_type;   
	 private String data_type;        
	 private String data_id;          
	 private String description;      
	 private String need_approve;     
	 private String approve_result;   
	 private String delete_flag ;
	 
	 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public Timestamp getOperatioin_time() {
		return operatioin_time;
	}
	public void setOperatioin_time(Timestamp operatioin_time) {
		this.operatioin_time = operatioin_time;
	}
	public String getOperation_type() {
		return operation_type;
	}
	public void setOperation_type(String operation_type) {
		this.operation_type = operation_type;
	}
	public String getData_type() {
		return data_type;
	}
	public void setData_type(String data_type) {
		this.data_type = data_type;
	}
	public String getData_id() {
		return data_id;
	}
	public void setData_id(String data_id) {
		this.data_id = data_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getNeed_approve() {
		return need_approve;
	}
	public void setNeed_approve(String need_approve) {
		this.need_approve = need_approve;
	}
	public String getApprove_result() {
		return approve_result;
	}
	public void setApprove_result(String approve_result) {
		this.approve_result = approve_result;
	}
	public String getDelete_flag() {
		return delete_flag;
	}
	public void setDelete_flag(String delete_flag) {
		this.delete_flag = delete_flag;
	}     


}
