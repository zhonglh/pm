package com.pm.domain.system;

import java.io.Serializable;
import java.sql.Timestamp;

import com.pm.util.PubMethod;
import com.pm.util.log.EntityAnnotation;

/**
 * 部门信息， 对应ts_dept
 * @author zhonglh
 *
 */
@SuppressWarnings("serial")
public class Dept implements Serializable {
	
	private String 	dept_id;	
	
	@EntityAnnotation(item_name="部门编号")
	private String 	dept_no;	

	@EntityAnnotation(item_name="部门名称")
	private String 	dept_name;	
	
	//经理ID
	private String 	lead_id;

	@EntityAnnotation(item_name="是否统计")
	private String statisticsed;
	

	@EntityAnnotation(item_name="描述")
	private String 	description;
	
	private Timestamp build_time;	
	private String 	build_userid;	
	private String 	build_username;
	private String 	delete_flag;	
	private Timestamp delete_datetime;
	private String 	delete_userid;	
	private String 	delete_username;	
	
	
	
	///////////////扩展或者查询 使用

	@EntityAnnotation(item_name="部门领导")
	private String lead_name;


	private boolean  selected = false;
	
	
	private int curr_years = PubMethod.getCurrentYear();
	private double curr_profit_objective ;
	
	
	//用于查询
	private int year1;
	private int year2;
	
	public String getDept_id() {
		return dept_id;
	}
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	public String getDept_no() {
		return dept_no;
	}
	public void setDept_no(String dept_no) {
		this.dept_no = dept_no;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public Timestamp getBuild_time() {
		return build_time;
	}
	public void setBuild_time(Timestamp build_time) {
		this.build_time = build_time;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public String getLead_id() {
		return lead_id;
	}
	public void setLead_id(String lead_id) {
		this.lead_id = lead_id;
	}
	public String getLead_name() {
		return lead_name;
	}
	public void setLead_name(String lead_name) {
		this.lead_name = lead_name;
	}
	public int getCurr_years() {
		return curr_years;
	}
	public void setCurr_years(int curr_years) {
		this.curr_years = curr_years;
	}
	public double getCurr_profit_objective() {
		return curr_profit_objective;
	}
	public void setCurr_profit_objective(double curr_profit_objective) {
		this.curr_profit_objective = curr_profit_objective;
	}
	public int getYear1() {
		return year1;
	}
	public void setYear1(int year1) {
		this.year1 = year1;
	}
	public int getYear2() {
		return year2;
	}
	public void setYear2(int year2) {
		this.year2 = year2;
	}
	public String getStatisticsed() {
		return statisticsed;
	}
	public void setStatisticsed(String statisticsed) {
		this.statisticsed = statisticsed;
	}


	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
