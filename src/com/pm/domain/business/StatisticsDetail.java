package com.pm.domain.business;

import java.sql.Timestamp;

import com.pm.util.log.EntityAnnotation;


/**
 * 统计明细
 * @author zhonglh
 *
 */
public class StatisticsDetail {	
	
	private String obj_id;	
	
	private String project_id;	

	@EntityAnnotation(item_name="项目名称",item_sort=1)
	private String project_name;
	

	@EntityAnnotation(item_name="项目编号",item_sort=2)
	private String project_no;

	
	//收入  成本
	@EntityAnnotation(item_name="账款类型",item_sort=3)
	private String amount_type;

	@EntityAnnotation(item_name="业务类型",item_sort=4)
	private String business_type;

	@EntityAnnotation(item_name="金额",item_sort=5)
	private double amount;

	@EntityAnnotation(item_name="月份",item_sort=6)
	private int use_month;	

	@EntityAnnotation(item_name="实际(收支)日期",item_sort=7)
	private Timestamp pay_date;

	@EntityAnnotation(item_name="名称",item_sort=8)
	private String business_name;	

	@EntityAnnotation(item_name="其他",item_sort=9)
	private String otherdesc;

	@EntityAnnotation(item_name="描述",item_sort=10)
	private String description;

	
	
	public String getObj_id() {
		return obj_id;
	}


	public void setObj_id(String obj_id) {
		this.obj_id = obj_id;
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


	public String getAmount_type() {
		return amount_type;
	}


	public void setAmount_type(String amount_type) {
		this.amount_type = amount_type;
	}


	public String getBusiness_type() {
		return business_type;
	}


	public void setBusiness_type(String business_type) {
		this.business_type = business_type;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public int getUse_month() {
		return use_month;
	}


	public void setUse_month(int use_month) {
		this.use_month = use_month;
	}


	


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getOtherdesc() {
		return otherdesc;
	}


	public void setOtherdesc(String otherdesc) {
		this.otherdesc = otherdesc;
	}


	public String getBusiness_name() {
		return business_name;
	}


	public void setBusiness_name(String business_name) {
		this.business_name = business_name;
	}


	public String getProject_id() {
		return project_id;
	}


	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}


	public Timestamp getPay_date() {
		return pay_date;
	}


	public void setPay_date(Timestamp pay_date) {
		this.pay_date = pay_date;
	}	
	
	

}
