package com.pm.domain.business;

import com.common.utils.spring.SpringContextUtil;
import com.pm.util.log.EntityAnnotation;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("serial")
public class StaffPerformance extends IdEntity implements Serializable {
		


	@EntityAnnotation(item_name="月份" ,item_sort=1)
	private int the_month;		

	
	private String dept_id;	
		
	private String project_id;	
		
	private String staff_id;
			

	private int import_order;
	private Timestamp build_datetime;
	private String build_userid;
	@EntityAnnotation(item_name="制表人" ,item_sort=200) 
	private String build_username;	
	@EntityAnnotation(item_name="核单人" ,item_sort=201)
	private String verify_username;		
	private String verify_userid;		
	private Timestamp verify_datetime;


	/////////////////////////////////////////////
	//////////////扩展////////////////////////////
	/////////////////////////////////////////////
	

	//@EntityAnnotation(item_name="部门" ,item_sort=5) 
	private String dept_name;
	

	@EntityAnnotation(item_name="项目名称" ,item_sort=3) 
	private String project_name;
	
	


	@EntityAnnotation(item_name="工号" ,item_sort=4 , length = 30) 
	private String staff_no;	

	@EntityAnnotation(item_name="姓名" ,item_sort=5, length = 60) 
	private String staff_name;

	

	//是否申请 取消核单状态 1:已申请取消核单  其他:正常
	private String need_approve;
	
	//1为是 ， 0为否
	private String verify_flag ;
	
	
	private String errorInfo = "";
	
	public int getThe_month() {
		return the_month;
	}

	public void setThe_month(int the_month) {
		this.the_month = the_month;
	}


	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

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

	public int getImport_order() {
		return import_order;
	}

	public void setImport_order(int import_order) {
		this.import_order = import_order;
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

	public String getVerify_username() {
		return verify_username;
	}

	public void setVerify_username(String verify_username) {
		this.verify_username = verify_username;
	}

	public String getVerify_userid() {
		return verify_userid;
	}

	public void setVerify_userid(String verify_userid) {
		this.verify_userid = verify_userid;
	}

	public Timestamp getVerify_datetime() {
		return verify_datetime;
	}

	public void setVerify_datetime(Timestamp verify_datetime) {
		this.verify_datetime = verify_datetime;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
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

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public String getNeed_approve() {
		return need_approve;
	}

	public void setNeed_approve(String need_approve) {
		this.need_approve = need_approve;
	}



	public String getVerify_flag() {
		return verify_flag;
	}

	public void setVerify_flag(String verify_flag) {
		this.verify_flag = verify_flag;
	}
	
	



	@Override
	public String toString() {
		return staff_name+"("+staff_no+")"+the_month+" 绩效";
	}
	
	
	
	
}