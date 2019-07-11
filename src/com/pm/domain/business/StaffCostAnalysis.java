package com.pm.domain.business;

import java.sql.Timestamp;

import com.pm.util.log.EntityAnnotation;



@SuppressWarnings("serial")
public class StaffCostAnalysis implements java.io.Serializable {
	
	

	private String staff_id;	

	@EntityAnnotation(item_name="员工工号", item_sort=1)
	private String staff_no;	
	

	@EntityAnnotation(item_name="员工名称", item_sort=2)
	private String staff_name;	
	

	@EntityAnnotation(item_name="身份证号", item_sort=3)
	private String identity_card_number;
	

	@EntityAnnotation(item_name="联系电话", item_sort=4)
	private String tel;	
	

	@EntityAnnotation(item_name="客户最新报价",item_sort=5)
	private double qustomerquotes;
	
	@EntityAnnotation(item_name="入职时间", item_sort=6)
	private Timestamp join_datetime;	
	

	@EntityAnnotation(item_name="离职时间", item_sort=7)
	private Timestamp leave_job_datetime;
	

	@EntityAnnotation(item_name="招聘专员名称", item_sort=8)
	private String recruiter_name;		
	

	@EntityAnnotation(item_name="是否外协人员", item_sort=9)
	private String outsource_staff;
	
	

	@EntityAnnotation(item_name="总收入", item_sort=10)
	private double technical_income;

	@EntityAnnotation(item_name="总成本", item_sort=11)
	private double technical_cost;	

	@EntityAnnotation(item_name="总利润", item_sort=12)
	private double technical_profit;	
	

	@EntityAnnotation(item_name="平均月利润", item_sort=13)
	private double profit;






	@EntityAnnotation(item_name="工作天数", item_sort=14)
	private double work_days;


	@EntityAnnotation(item_name="请假天数", item_sort=15)
	private double holidays;


	@EntityAnnotation(item_name="周末加班天数", item_sort=16)
	private double weekend_overtime_days;


	@EntityAnnotation(item_name="平常加班小时", item_sort=17)
	private double every_overtime;
	

	private String delete_flag;
	
	
	//月数
	private int months;

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

	public String getIdentity_card_number() {
		return identity_card_number;
	}

	public void setIdentity_card_number(String identity_card_number) {
		this.identity_card_number = identity_card_number;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public double getQustomerquotes() {
		return qustomerquotes;
	}

	public void setQustomerquotes(double qustomerquotes) {
		this.qustomerquotes = qustomerquotes;
	}

	public Timestamp getJoin_datetime() {
		return join_datetime;
	}

	public void setJoin_datetime(Timestamp join_datetime) {
		this.join_datetime = join_datetime;
	}

	public Timestamp getLeave_job_datetime() {
		return leave_job_datetime;
	}

	public void setLeave_job_datetime(Timestamp leave_job_datetime) {
		this.leave_job_datetime = leave_job_datetime;
	}

	public String getRecruiter_name() {
		return recruiter_name;
	}

	public void setRecruiter_name(String recruiter_name) {
		this.recruiter_name = recruiter_name;
	}

	public double getTechnical_income() {
		return technical_income;
	}

	public void setTechnical_income(double technical_income) {
		this.technical_income = technical_income;
	}

	public double getTechnical_cost() {
		return technical_cost;
	}

	public void setTechnical_cost(double technical_cost) {
		this.technical_cost = technical_cost;
	}

	public double getTechnical_profit() {
		return technical_profit;
	}

	public void setTechnical_profit(double technical_profit) {
		this.technical_profit = technical_profit;
	}

	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

	public int getMonths() {
		return months;
	}

	public void setMonths(int months) {
		this.months = months;
	}

	public String getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}

	public String getOutsource_staff() {
		return outsource_staff;
	}

	public void setOutsource_staff(String outsource_staff) {
		this.outsource_staff = outsource_staff;
	}

	public double getWork_days() {
		return work_days;
	}

	public void setWork_days(double work_days) {
		this.work_days = work_days;
	}

	public double getHolidays() {
		return holidays;
	}

	public void setHolidays(double holidays) {
		this.holidays = holidays;
	}

	public double getWeekend_overtime_days() {
		return weekend_overtime_days;
	}

	public void setWeekend_overtime_days(double weekend_overtime_days) {
		this.weekend_overtime_days = weekend_overtime_days;
	}

	public double getEvery_overtime() {
		return every_overtime;
	}

	public void setEvery_overtime(double every_overtime) {
		this.every_overtime = every_overtime;
	}

	public String getDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(String delete_flag) {
		this.delete_flag = delete_flag;
	}

}
