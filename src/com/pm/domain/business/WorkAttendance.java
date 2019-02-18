package com.pm.domain.business;

import java.io.Serializable;
import java.sql.Timestamp;

import com.common.utils.sorts.ISort;
import com.pm.util.log.EntityAnnotation;


/**
 * 考勤记录
 * @author zhonglh
 *
 */
@SuppressWarnings("serial")
public class WorkAttendance implements Serializable , ISort{

	private String attendance_id;		
	private String staff_id;
	private String project_id;	
	
	//@EntityAnnotation(item_name="考勤月份" , item_sort=1,length=6) 
	private int attendance_month;

	@EntityAnnotation(item_name="工作日天数" , item_sort=5,length=5) 
	private double should_work_days;	

	@EntityAnnotation(item_name="实际工作天数" , item_sort=6,length=5) 
	private double work_days;	

	@EntityAnnotation(item_name="法定假日天数" , item_sort=7,length=5) 
	private double legal_holidays;
	

	@EntityAnnotation(item_name="倒休天数" , item_sort=8,length=5) 		
	private double swopped_holidays;	
	
	@EntityAnnotation(item_name="年假天数" , item_sort=9,length=5) 	
	private double annual_leave_days;


	@EntityAnnotation(item_name="出差天数" , item_sort=10,length=5) 
	private double business_trip_days;	

	@EntityAnnotation(item_name="事假天数" , item_sort=11,length=5) 
	private double personal_leave_days;	

	@EntityAnnotation(item_name="病假天数" , item_sort=12,length=5) 
	private double sick_leave_days;


	@EntityAnnotation(item_name="待岗天数" , item_sort=13,length=5)
	private double waiting_post_days;
	@EntityAnnotation(item_name="产假天数" , item_sort=14,length=5)
	private double maternity_leave_days;

	//@EntityAnnotation(item_name="医疗假天数" , item_sort=15,length=5)
	private double medical_days;







	@EntityAnnotation(item_name="旷工天数" , item_sort=16,length=5)
	private double neglect_work_days;	

	@EntityAnnotation(item_name="迟到天数" , item_sort=17,length=5)
	private double late_days;		

	@EntityAnnotation(item_name="周末加班" , item_sort=18,length=5)
	private double weekend_overtime_days;	
	

	@EntityAnnotation(item_name="日常加班(小时)" , item_sort=19,length=5)
	private double every_overtime;	
	

	@EntityAnnotation(item_name="备注" , item_sort=20,length=150)
	private String remark;	
	

	private double technical_cost;
	private String client_dept;
	private Timestamp build_datetime;	
	private String build_userid;
	private String build_username;
	private String delete_flag;	
	private Timestamp delete_datetime;
	private String delete_userid	;
	private String delete_username;
	

	private String verify_userid;	
	private String verify_username;	
	private Timestamp verify_datetime;
	
	
	////////////////////////////////////////////////
	/////////////////////扩展///////////////////////
	////////////////////////////////////////////////

	//仅用于导入导出,月份
	@EntityAnnotation(item_name="考勤月份", item_sort=1,length=20)
	private String str_month;

	@EntityAnnotation(item_name="项目名称" , item_sort=2,length=100) 
	private String project_name;

	@EntityAnnotation(item_name="项目编号")
	private String project_no;

	@EntityAnnotation(item_name="人员名称" , item_sort=3,length=50) 
	private String staff_name;

	@EntityAnnotation(item_name="人员工号" , item_sort=4,length=30) 
	private String staff_no;
	
	


	
	/**
	 * 考勤人数
	 */
	private int project_attendance_number;
	
	
	//核单个数
	private int verify_number;
	

	//核单标记， 用于查询
	//1: 已核单，  2:未核单
	private String verify_flag;
	
	//部门查询ID
	private String dept_id;
	private String dept_name;
	

	
	
	/**
	 * 用于查询
	 */
	private String attendance_day1;
	private String attendance_day2;
	
	
	private int attendance_month1;
	private int attendance_month2;
	
	
	//用于重新计算考勤记录, 默认为修改
	//1:insert   2:update  3:delete
	private String operationType = "2";
	
	
	

	private String errorInfo = "";
	
	
	public String getAttendance_id() {
		return attendance_id;
	}
	public void setAttendance_id(String attendance_id) {
		this.attendance_id = attendance_id;
	}
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
	public int getAttendance_month() {
		return attendance_month;
	}
	public void setAttendance_month(int attendance_month) {
		this.attendance_month = attendance_month;
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
	public double getLegal_holidays() {
		return legal_holidays;
	}
	public void setLegal_holidays(double legal_holidays) {
		this.legal_holidays = legal_holidays;
	}
	public double getSwopped_holidays() {
		return swopped_holidays;
	}
	public void setSwopped_holidays(double swopped_holidays) {
		this.swopped_holidays = swopped_holidays;
	}
	public double getBusiness_trip_days() {
		return business_trip_days;
	}
	public void setBusiness_trip_days(double business_trip_days) {
		this.business_trip_days = business_trip_days;
	}
	public double getPersonal_leave_days() {
		return personal_leave_days;
	}
	public void setPersonal_leave_days(double personal_leave_days) {
		this.personal_leave_days = personal_leave_days;
	}
	public double getSick_leave_days() {
		return sick_leave_days;
	}
	public void setSick_leave_days(double sick_leave_days) {
		this.sick_leave_days = sick_leave_days;
	}
	public double getNeglect_work_days() {
		return neglect_work_days;
	}
	public void setNeglect_work_days(double neglect_work_days) {
		this.neglect_work_days = neglect_work_days;
	}
	public double getLate_days() {
		return late_days;
	}
	public void setLate_days(double late_days) {
		this.late_days = late_days;
	}
	public double getWeekend_overtime_days() {
		return weekend_overtime_days;
	}
	public void setWeekend_overtime_days(double weekend_overtime_days) {
		this.weekend_overtime_days = weekend_overtime_days;
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
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getStaff_name() {
		return staff_name;
	}
	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
	}
	public String getProject_no() {
		return project_no;
	}
	public void setProject_no(String project_no) {
		this.project_no = project_no;
	}
	public int getProject_attendance_number() {
		return project_attendance_number;
	}
	public void setProject_attendance_number(int project_attendance_number) {
		this.project_attendance_number = project_attendance_number;
	}
	public String getAttendance_day1() {
		return attendance_day1;
	}
	public void setAttendance_day1(String attendance_day1) {
		this.attendance_day1 = attendance_day1;
	}
	public String getAttendance_day2() {
		return attendance_day2;
	}
	public void setAttendance_day2(String attendance_day2) {
		this.attendance_day2 = attendance_day2;
	}
	public double getTechnical_cost() {
		return technical_cost;
	}
	public void setTechnical_cost(double technical_cost) {
		this.technical_cost = technical_cost;
	}
	public double getAnnual_leave_days() {
		return annual_leave_days;
	}
	public void setAnnual_leave_days(double annual_leave_days) {
		this.annual_leave_days = annual_leave_days;
	}
	public String getVerify_userid() {
		return verify_userid;
	}
	public void setVerify_userid(String verify_userid) {
		this.verify_userid = verify_userid;
	}
	public String getVerify_username() {
		return verify_username;
	}
	public void setVerify_username(String verify_username) {
		this.verify_username = verify_username;
	}
	public Timestamp getVerify_datetime() {
		return verify_datetime;
	}
	public void setVerify_datetime(Timestamp verify_datetime) {
		this.verify_datetime = verify_datetime;
	}
	public int getVerify_number() {
		return verify_number;
	}
	public void setVerify_number(int verify_number) {
		this.verify_number = verify_number;
	}
	public String getVerify_flag() {
		return verify_flag;
	}
	public void setVerify_flag(String verify_flag) {
		this.verify_flag = verify_flag;
	}
	public String getStaff_no() {
		return staff_no;
	}
	public void setStaff_no(String staff_no) {
		this.staff_no = staff_no;
	}
	public String getErrorInfo() {
		return errorInfo;
	}
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public double getWaiting_post_days() {
		return waiting_post_days;
	}

	public void setWaiting_post_days(double waiting_post_days) {
		this.waiting_post_days = waiting_post_days;
	}

	public double getMaternity_leave_days() {
		return maternity_leave_days;
	}

	public void setMaternity_leave_days(double maternity_leave_days) {
		this.maternity_leave_days = maternity_leave_days;
	}

	public double getMedical_days() {
		return medical_days;
	}

	public void setMedical_days(double medical_days) {
		this.medical_days = medical_days;
	}




	public String getStr_month() {
		if(str_month == null && attendance_month != 0) {
			return String.valueOf(attendance_month);
		} else {
			return str_month;
		}
	}
	public void setStr_month(String str_month) {
		this.str_month = str_month;
	}	

	//查唯一主键(业务)
	public String getBusinessKey(){
		return this.getProject_id()+this.getAttendance_month()+this.getStaff_id();
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	
	
	@Override
	public String getSortKey() {		
		return this.getOperationType();
	}
	public double getEvery_overtime() {
		return every_overtime;
	}
	public void setEvery_overtime(double every_overtime) {
		this.every_overtime = every_overtime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public int getAttendance_month1() {
		return attendance_month1;
	}
	public void setAttendance_month1(int attendance_month1) {
		this.attendance_month1 = attendance_month1;
	}
	public int getAttendance_month2() {
		return attendance_month2;
	}
	public void setAttendance_month2(int attendance_month2) {
		this.attendance_month2 = attendance_month2;
	}
	public String getClient_dept() {
		return client_dept;
	}
	public void setClient_dept(String client_dept) {
		this.client_dept = client_dept;
	}
	
	

}
