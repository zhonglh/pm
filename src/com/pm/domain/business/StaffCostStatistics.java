package com.pm.domain.business;

import java.io.Serializable;
import java.sql.Timestamp;

import com.pm.util.log.EntityAnnotation;

@SuppressWarnings("serial")
public class StaffCostStatistics implements Serializable {
	
	
	private String staff_id;
	@EntityAnnotation(item_name="员工工号",item_sort=1)
	private String staff_no;
	@EntityAnnotation(item_name="员工名称",item_sort=2)
	private String staff_name;
	
	

	@EntityAnnotation(item_name="项目名称",item_sort=3)
	private String project_name;
	

	private String dept_id;
	
	@EntityAnnotation(item_name="部门名称",item_sort=4)
	private String dept_name;
	private String project_id;
	
	
	private String recruitment_userid;
	
	@EntityAnnotation(item_name="招聘专员",item_sort=5)
	private String recruitment_username;

	
	@EntityAnnotation(item_name="加入项目时间",item_sort=6,business_name="JOIN")
	private Timestamp join_project_datetime;	

	@EntityAnnotation(item_name="离开项目时间",item_sort=6,business_name="LEAVE")
	private Timestamp leave_project_datetime;
	
	
	

	@EntityAnnotation(item_name="技术费用",item_sort=7)
	private double technical_cost;	
	
	@EntityAnnotation(item_name="客户所在部门",item_sort=8)
	private String client_dept;

	@EntityAnnotation(item_name="说明",item_sort=9)
	private String description;
	
	
	/////////////////////////////
	/////////////////扩展/////////
	/////////////////////////////
	
	private java.util.Date date1;
	private java.util.Date date2;
	
	
	private String delete_flag;

	public String getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}

	public String getStaff_no() {
		return staff_no;
	}

	public void setStaff_no(String staff_no) {
		this.staff_no = staff_no;
	}

	public String getStaff_name() {
		return staff_name;
	}

	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getProject_id() {
		return project_id;
	}

	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getRecruitment_userid() {
		return recruitment_userid;
	}

	public void setRecruitment_userid(String recruitment_userid) {
		this.recruitment_userid = recruitment_userid;
	}

	public String getRecruitment_username() {
		return recruitment_username;
	}

	public void setRecruitment_username(String recruitment_username) {
		this.recruitment_username = recruitment_username;
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

	public String getClient_dept() {
		return client_dept;
	}

	public void setClient_dept(String client_dept) {
		this.client_dept = client_dept;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public java.util.Date getDate1() {
		return date1;
	}

	public void setDate1(java.util.Date date1) {
		this.date1 = date1;
	}

	public java.util.Date getDate2() {
		return date2;
	}

	public void setDate2(java.util.Date date2) {
		this.date2 = date2;
	}

	public String getDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(String delete_flag) {
		this.delete_flag = delete_flag;
	}


}
