package com.pm.domain.business;

import java.io.Serializable;
import java.sql.Timestamp;

import com.pm.util.PubMethod;
import com.pm.util.log.EntityAnnotation;

/**
 * 部门费用
 * @author zhonglh
 *
 */@SuppressWarnings("serial")
public class DepartCosts extends IdEntity implements Serializable {
	
	private String staff_id;
	private String dept_id;
	private String project_id;	
	private String pay_item_id;	
	

	@EntityAnnotation(item_name="报销金额", item_sort=3,length=12)
	private double amount;

	//@EntityAnnotation(item_name="报销月份")
	private int use_month;
	
	

	@EntityAnnotation(item_name="备注" , item_sort=2,length=300)
	private String description;
	

	@EntityAnnotation(item_name="实际支付日期", item_sort=8,length=10) 
	private Timestamp pay_date;
	
	

	private int import_order;
	
	
	private Timestamp build_datetime	;
	private String build_userid;	
	private String build_username;	
	private String delete_flag;	
	private String delete_userid;	
	private String delete_username;	
	private Timestamp delete_datetime;	
	private String verify_userid;	
	private String verify_username;	
	private Timestamp verify_datetime;
	
	

	
	//////////////////////////////////////////////
	//////////////扩展////////////////////////////
	/////////////////////////////////////////////
	
	
	@EntityAnnotation(item_name="报销类别" , item_sort=5,length=60) 
	private String pay_item_name;

	@EntityAnnotation(item_name="项目名称", item_sort=4,length=100) 
	private String project_name;

	@EntityAnnotation(item_name="项目编号")
	private String project_no;
	

	@EntityAnnotation(item_name="所属部门", item_sort=6,length=30) 
	private String dept_name;

	@EntityAnnotation(item_name="报销人", item_sort=7,length=60) 
	private String staff_name;

	@EntityAnnotation(item_name="报销人工号")
	private String staff_no;
	


	//仅用于导入导出,月份
	@EntityAnnotation(item_name="报销月份", item_sort=1,length=10)
	private String str_month;
	
	
	
	

	private String errorInfo = "";
	
	

	//核单标记， 用于查询
	//1: 已核单，  2:未核单
	private String verify_flag;
	
	
	private int use_month1;
	private int use_month2;
	private Timestamp pay_date1;
	private Timestamp pay_date2;
	
	


	//是否申请 取消核单状态 1:已申请取消核单  其他:正常
	private String need_approve;
	

	public String getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public String getPay_item_id() {
		return pay_item_id;
	}
	public void setPay_item_id(String pay_item_id) {
		this.pay_item_id = pay_item_id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
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
	public String getDelete_flag() {
		return delete_flag;
	}
	public void setDelete_flag(String delete_flag) {
		this.delete_flag = delete_flag;
	}
	public String getDelete_userid() {
		return delete_userid;
	}
	public void setDelete_userid(String delete_userid) {
		this.delete_userid = delete_userid;
	}
	public Timestamp getDelete_datetime() {
		return delete_datetime;
	}
	public void setDelete_datetime(Timestamp delete_datetime) {
		this.delete_datetime = delete_datetime;
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
	public int getUse_month() {
		return use_month;
	}
	public void setUse_month(int use_month) {
		this.use_month = use_month;
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
	public String getVerify_username() {
		return verify_username;
	}
	public void setVerify_username(String verify_username) {
		this.verify_username = verify_username;
	}
	public String getPay_item_name() {
		return pay_item_name;
	}
	public void setPay_item_name(String pay_item_name) {
		this.pay_item_name = pay_item_name;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getProject_no() {
		return project_no;
	}
	public void setProject_no(String project_no) {
		this.project_no = project_no;
	}
	public String getStaff_name() {
		return staff_name;
	}
	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
	}
	public String getStaff_no() {
		return staff_no;
	}
	public void setStaff_no(String staff_no) {
		this.staff_no = staff_no;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public String getVerify_flag() {
		return verify_flag;
	}
	public void setVerify_flag(String verify_flag) {
		this.verify_flag = verify_flag;
	}
	public int getImport_order() {
		return import_order;
	}
	public void setImport_order(int import_order) {
		this.import_order = import_order;
	}
	public String getErrorInfo() {
		return errorInfo;
	}
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
	public String getStr_month() {
		if(str_month == null && use_month != 0) return String.valueOf(use_month);
		else {
			return PubMethod.fromatExcelDecimal(str_month);
		}
	}
	public void setStr_month(String str_month) {
		this.str_month = str_month;
	}
	public String getNeed_approve() {
		return need_approve;
	}
	public void setNeed_approve(String need_approve) {
		this.need_approve = need_approve;
	}
	public Timestamp getPay_date() {
		return pay_date;
	}
	public void setPay_date(Timestamp pay_date) {
		this.pay_date = pay_date;
	}
	public int getUse_month1() {
		return use_month1;
	}
	public void setUse_month1(int use_month1) {
		this.use_month1 = use_month1;
	}
	public int getUse_month2() {
		return use_month2;
	}
	public void setUse_month2(int use_month2) {
		this.use_month2 = use_month2;
	}
	public Timestamp getPay_date1() {
		return pay_date1;
	}
	public void setPay_date1(Timestamp pay_date1) {
		this.pay_date1 = pay_date1;
	}
	public Timestamp getPay_date2() {
		return pay_date2;
	}
	public void setPay_date2(Timestamp pay_date2) {
		this.pay_date2 = pay_date2;
	}
	@Override
	public String toString() {
		return this.getStaff_name() + this.getUse_month() ;
	}



}
