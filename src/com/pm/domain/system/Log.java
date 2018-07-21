package com.pm.domain.system;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@SuppressWarnings("serial")
public class Log implements Serializable {
	
	private String log_id;		
	private String operation_type;	
	private String operation_userid;
	private String operation_username;
	private String operation_deptid;
	private Timestamp   operation_time;	
	private String project_id;	
	private String entity_type;	
	private String entity_id;	
	private String entity_name;
	
	
	//////////////////////////////////////////
	/////////////扩展用的//////////////////////
	//////////////////////////////////////////

	private Timestamp   operation_time1;
	private Timestamp   operation_time2;
	
	
	private String project_name;
	
	List<LogDetail> details;
	
	
	public String getLog_id() {
		return log_id;
	}
	public void setLog_id(String log_id) {
		this.log_id = log_id;
	}
	public String getOperation_type() {
		return operation_type;
	}
	public void setOperation_type(String operation_type) {
		this.operation_type = operation_type;
	}
	public String getOperation_userid() {
		return operation_userid;
	}
	public void setOperation_userid(String operation_userid) {
		this.operation_userid = operation_userid;
	}
	public String getOperation_deptid() {
		return operation_deptid;
	}
	public void setOperation_deptid(String operation_deptid) {
		this.operation_deptid = operation_deptid;
	}
	public Timestamp getOperation_time() {
		return operation_time;
	}
	public void setOperation_time(Timestamp operation_time) {
		this.operation_time = operation_time;
	}
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public String getEntity_type() {
		return entity_type;
	}
	public void setEntity_type(String entity_type) {
		this.entity_type = entity_type;
	}
	public String getEntity_id() {
		return entity_id;
	}
	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}
	public String getEntity_name() {
		return entity_name;
	}
	public void setEntity_name(String entity_name) {
		this.entity_name = entity_name;
	}
	public Timestamp getOperation_time1() {
		return operation_time1;
	}
	public void setOperation_time1(Timestamp operation_time1) {
		this.operation_time1 = operation_time1;
	}
	public Timestamp getOperation_time2() {
		return operation_time2;
	}
	public void setOperation_time2(Timestamp operation_time2) {
		this.operation_time2 = operation_time2;
	}
	public String getOperation_username() {
		return operation_username;
	}
	public void setOperation_username(String operation_username) {
		this.operation_username = operation_username;
	}
	public List<LogDetail> getDetails() {
		return details;
	}
	public void setDetails(List<LogDetail> details) {
		this.details = details;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}


}
