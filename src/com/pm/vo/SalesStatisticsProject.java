package com.pm.vo;

import com.pm.util.log.EntityAnnotation;

@SuppressWarnings("serial")
public class SalesStatisticsProject implements java.io.Serializable {


	@EntityAnnotation(item_name="项目名称",item_sort=1)
	private String project_name;

	@EntityAnnotation(item_name="项目编号",item_sort=2)
	private String project_no;
	

	@EntityAnnotation(item_name="销售额",item_sort=3)
	private double statistics_amount;
	

	@EntityAnnotation(item_name="销售负责人",item_sort=4)
	private String sales_username;
	
	@EntityAnnotation(item_name="信息来源",item_sort=5)
	private String info_sources_username;

	@EntityAnnotation(item_name="项目管理人",item_sort=6)
	private String manage_username;
	

	@EntityAnnotation(item_name="销售额归属",item_sort=7)
	private String sales_amount_deptname;	
	
	@EntityAnnotation(item_name="执行额归属",item_sort=8)
	private String exec_amount_deptname;
	

	@EntityAnnotation(item_name="所属部门",item_sort=9)
	private String deptname;

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


	public double getStatistics_amount() {
		return statistics_amount;
	}


	public void setStatistics_amount(double statistics_amount) {
		this.statistics_amount = statistics_amount;
	}


	public String getSales_username() {
		return sales_username;
	}


	public void setSales_username(String sales_username) {
		this.sales_username = sales_username;
	}


	public String getInfo_sources_username() {
		return info_sources_username;
	}


	public void setInfo_sources_username(String info_sources_username) {
		this.info_sources_username = info_sources_username;
	}


	public String getManage_username() {
		return manage_username;
	}


	public void setManage_username(String manage_username) {
		this.manage_username = manage_username;
	}


	public String getSales_amount_deptname() {
		return sales_amount_deptname;
	}


	public void setSales_amount_deptname(String sales_amount_deptname) {
		this.sales_amount_deptname = sales_amount_deptname;
	}


	public String getExec_amount_deptname() {
		return exec_amount_deptname;
	}


	public void setExec_amount_deptname(String exec_amount_deptname) {
		this.exec_amount_deptname = exec_amount_deptname;
	}


	public String getDeptname() {
		return deptname;
	}


	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}		
}
