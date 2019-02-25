package com.pm.domain.business;

import com.pm.util.log.EntityAnnotation;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 项目人员成本
 * @author zhonglh
 *
 */
@SuppressWarnings("serial")
public class OtherStaffCost implements Serializable {
	
	private String other_staff_cost_id;
	private String staff_id	;
	private String dept_id;
	
	@EntityAnnotation(item_name="实发金额",item_sort=7)
	private double amount;		
	

	@EntityAnnotation(item_name="社保",item_sort=8)
	private double insurance_amount;	

	@EntityAnnotation(item_name="公积金",item_sort=9)
	private double pub_funds_amount;		

	@EntityAnnotation(item_name="个税",item_sort=10)
	private double personal_income_tax;
	
	//其他
	private double other_amount;	

	@EntityAnnotation(item_name="总成本",item_sort=11)
	private double all_amount;

	@EntityAnnotation(item_name="月份",item_sort=3)
	private int work_month;		
	

	@EntityAnnotation(item_name="实际支付日期", item_sort=12) 
	private Timestamp pay_date;
	
	private String salary_id;	
	private String attendance_id;	
	

	@EntityAnnotation(item_name="工作天数",item_sort=6)
	private double work_days;
	
	private Timestamp build_datetime;
	private String build_userid;	
	private String build_username;	
	private String verify_userid;	
	private String verify_username;	
	private Timestamp verify_datetime;
	
	//////////////////////////////////////////////
	//////////////扩展////////////////////////////
	/////////////////////////////////////////////

	@EntityAnnotation(item_name="部门名称",item_sort=1)
	private String dept_name;


	@EntityAnnotation(item_name="人员名称",item_sort=4)
	private String staff_name;

	@EntityAnnotation(item_name="人员工号",item_sort=5)
	private String staff_no;

	private double total_work_days;
	
	
	//用于查询
	private int work_month1;	
	private int work_month2;	
	;

	
	
		public String getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getSalary_id() {
		return salary_id;
	}
	public void setSalary_id(String salary_id) {
		this.salary_id = salary_id;
	}
	public String getAttendance_id() {
		return attendance_id;
	}
	public void setAttendance_id(String attendance_id) {
		this.attendance_id = attendance_id;
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
	public double getWork_days() {
		return work_days;
	}
	public void setWork_days(double work_days) {
		this.work_days = work_days;
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
	public double getTotal_work_days() {
		return total_work_days;
	}
	public void setTotal_work_days(double total_work_days) {
		this.total_work_days = total_work_days;
	}
	public int getWork_month() {
		return work_month;
	}
	public void setWork_month(int work_month) {
		this.work_month = work_month;
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
	public Timestamp getPay_date() {
		return pay_date;
	}
	public void setPay_date(Timestamp pay_date) {
		this.pay_date = pay_date;
	}
	public double getInsurance_amount() {
		return insurance_amount;
	}
	public void setInsurance_amount(double insurance_amount) {
		this.insurance_amount = insurance_amount;
	}
	public double getPub_funds_amount() {
		return pub_funds_amount;
	}
	public void setPub_funds_amount(double pub_funds_amount) {
		this.pub_funds_amount = pub_funds_amount;
	}
	public double getOther_amount() {
		return other_amount;
	}
	public void setOther_amount(double other_amount) {
		this.other_amount = other_amount;
	}
	public double getAll_amount() {
		return all_amount;
	}
	public void setAll_amount(double all_amount) {
		this.all_amount = all_amount;
	}
	public int getWork_month1() {
		return work_month1;
	}
	public void setWork_month1(int work_month1) {
		this.work_month1 = work_month1;
	}
	public int getWork_month2() {
		return work_month2;
	}
	public void setWork_month2(int work_month2) {
		this.work_month2 = work_month2;
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
	public double getPersonal_income_tax() {
		return personal_income_tax;
	}
	public void setPersonal_income_tax(double personal_income_tax) {
		this.personal_income_tax = personal_income_tax;
	}

	public String getOther_staff_cost_id() {
		return other_staff_cost_id;
	}

	public void setOther_staff_cost_id(String other_staff_cost_id) {
		this.other_staff_cost_id = other_staff_cost_id;
	}
}
