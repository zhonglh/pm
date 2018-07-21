package com.pm.domain.business;

import java.io.Serializable;
import java.sql.Timestamp;

import com.pm.util.log.EntityAnnotation;

/**
 * 项目人员信息
 * @author zhonglh
 *
 */
@SuppressWarnings("serial")
public class ProjectStaff implements Serializable {
	
	private String project_staff_id;

	private String project_id;	
	private String staff_id;
	
	@EntityAnnotation(item_name="加入项目时间")
	private Timestamp join_project_datetime;	

	@EntityAnnotation(item_name="离开项目时间")
	private Timestamp leave_project_datetime;	
	

	@EntityAnnotation(item_name="技术费用")
	private double technical_cost;	
	
	@EntityAnnotation(item_name="客户所在部门")
	private String client_dept;

	@EntityAnnotation(item_name="说明")
	private String description;
	
	private Timestamp build_datetime;	
	private String build_userid;	
	private String build_username;	
	private String delete_flag;	
	private Timestamp delete_datetime;
	private String delete_userid;
	private String delete_username;
	
	
	////////////////////////////////////////////////
	/////////////////扩展///////////////////////////
	///////////////////////////////////////////////

	@EntityAnnotation(item_name="人员名称")
	private String staff_name;
	

	@EntityAnnotation(item_name="项目名称")
	private String project_name;
	
	//时间段
	private String start_end_dates;
	
	
	
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public String getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}
	public Timestamp getJoin_project_datetime() {
		return join_project_datetime;
	}
	public void setJoin_project_datetime(Timestamp join_project_datetime) {
		this.join_project_datetime = join_project_datetime;
	}
	public Timestamp getLeave_project_datetime() {
		return leave_project_datetime;
	}
	public void setLeave_project_datetime(Timestamp leave_project_datetime) {
		this.leave_project_datetime = leave_project_datetime;
	}
	public double getTechnical_cost() {
		return technical_cost;
	}
	public void setTechnical_cost(double technical_cost) {
		this.technical_cost = technical_cost;
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
	public Timestamp getBuild_datetime() {
		return build_datetime;
	}
	public void setBuild_datetime(Timestamp build_datetime) {
		this.build_datetime = build_datetime;
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
	public String getStaff_name() {
		return staff_name;
	}
	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
	}
	public String getProject_staff_id() {
		return project_staff_id;
	}
	public void setProject_staff_id(String project_staff_id) {
		this.project_staff_id = project_staff_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getClient_dept() {
		return client_dept;
	}
	public void setClient_dept(String client_dept) {
		this.client_dept = client_dept;
	}
	public String getStart_end_dates() {
		return start_end_dates;
	}
	public void setStart_end_dates(String start_end_dates) {
		this.start_end_dates = start_end_dates;
	}	


}
