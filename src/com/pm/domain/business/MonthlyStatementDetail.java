package com.pm.domain.business;

import java.sql.Timestamp;

import com.pm.util.log.EntityAnnotation;

public class MonthlyStatementDetail {

	private String monthly_statement_detail_id;
	private String monthly_statement_id	;
	private String project_id;		
	private String staff_id	;	

	@EntityAnnotation(item_name="服务费用(人月)",item_sort=6)
	private double technical_cost	;
	
	@EntityAnnotation(item_name="应出勤天数",item_sort=7)	
	private double should_work_days;	

	@EntityAnnotation(item_name="实工作天数",item_sort=8)
	private double work_days;	

	@EntityAnnotation(item_name="日常加班",item_sort=9)
	private double daily_overtime	;	

	@EntityAnnotation(item_name="周末加班",item_sort=10)
	private double weekend_overtime;

	@EntityAnnotation(item_name="加班费用",item_sort=11)
	private double overtime_cost;	
	

	@EntityAnnotation(item_name="出差天数",item_sort=12)
	private double  business_trip_days;
	

	@EntityAnnotation(item_name="出差费用",item_sort=13)
	private double  business_trip_cost;
	
	

	@EntityAnnotation(item_name="其他费用",item_sort=14)
	private double other_cost;	

	@EntityAnnotation(item_name="人月")
	private double man_month;	

	@EntityAnnotation(item_name="小计费用",item_sort=15)
	private double total_cost;	
	

	@EntityAnnotation(item_name="客户所在部门",item_sort=16)
	private String client_dept;
	

	@EntityAnnotation(item_name="说明",item_sort=17)
	private String description;	
	
	private Timestamp build_datetime	;	
	private String build_userid	;	
	private String build_username;
	

	/////////////////////////////////////////////
	//////////////////扩展///////////////////////
	////////////////////////////////////////////

	@EntityAnnotation(item_name="人员名称",item_sort=1)
	private String staff_name;

	@EntityAnnotation(item_name="人员工号")
	private String staff_no;
	
	@EntityAnnotation(item_name="月份",item_sort=3)
	private String statement_month;
	

	@EntityAnnotation(item_name="项目名称",item_sort=2)
	private String project_name;
	
	
	

	@EntityAnnotation(item_name="入场时间" ,item_sort=4)
	private Timestamp join_project_datetime;	

	@EntityAnnotation(item_name="退场时间" ,item_sort=5)
	private Timestamp leave_project_datetime;	
	
	
	
	
	
	public String getMonthly_statement_detail_id() {
		return monthly_statement_detail_id;
	}

	public void setMonthly_statement_detail_id(String monthly_statement_detail_id) {
		this.monthly_statement_detail_id = monthly_statement_detail_id;
	}

	public String getMonthly_statement_id() {
		return monthly_statement_id;
	}

	public void setMonthly_statement_id(String monthly_statement_id) {
		this.monthly_statement_id = monthly_statement_id;
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

	public double getTechnical_cost() {
		return technical_cost;
	}

	public void setTechnical_cost(double technical_cost) {
		this.technical_cost = technical_cost;
	}

	public double getShould_work_days() {
		return should_work_days;
	}

	public void setShould_work_days(double should_work_days) {
		this.should_work_days = should_work_days;
	}

	public double getWork_days() {
		return work_days;
	}

	public void setWork_days(double work_days) {
		this.work_days = work_days;
	}

	public double getDaily_overtime() {
		return daily_overtime;
	}

	public void setDaily_overtime(double daily_overtime) {
		this.daily_overtime = daily_overtime;
	}

	public double getWeekend_overtime() {
		return weekend_overtime;
	}

	public void setWeekend_overtime(double weekend_overtime) {
		this.weekend_overtime = weekend_overtime;
	}

	public double getOvertime_cost() {
		return overtime_cost;
	}

	public void setOvertime_cost(double overtime_cost) {
		this.overtime_cost = overtime_cost;
	}

	public double getOther_cost() {
		return other_cost;
	}

	public void setOther_cost(double other_cost) {
		this.other_cost = other_cost;
	}

	public double getMan_month() {
		return man_month;
	}

	public void setMan_month(double man_month) {
		this.man_month = man_month;
	}

	public double getTotal_cost() {
		return total_cost;
	}

	public void setTotal_cost(double total_cost) {
		this.total_cost = total_cost;
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

	public String getStatement_month() {
		return statement_month;
	}

	public void setStatement_month(String statement_month) {
		this.statement_month = statement_month;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public double getBusiness_trip_days() {
		return business_trip_days;
	}

	public void setBusiness_trip_days(double business_trip_days) {
		this.business_trip_days = business_trip_days;
	}

	public double getBusiness_trip_cost() {
		return business_trip_cost;
	}

	public void setBusiness_trip_cost(double business_trip_cost) {
		this.business_trip_cost = business_trip_cost;
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

	public String getClient_dept() {
		return client_dept;
	}

	public void setClient_dept(String client_dept) {
		this.client_dept = client_dept;
	}




}
