package com.pm.domain.business;

import java.io.Serializable;
import java.sql.Timestamp;

import com.pm.util.log.EntityAnnotation;

/**
 * 其他人员表
 * @author zhonglh
 *
 */
@SuppressWarnings("serial")
public class OtherStaff implements Serializable {
	
	
	private String staff_id;
	

	@EntityAnnotation(item_name="工号" , item_sort=1 , length=20 )
	private String staff_no;
	
	@EntityAnnotation(item_name="姓名" ,item_sort=2 , length=50 )
	private String staff_name;
	
	//部门ID
	private String dept_id;
	

	
	@EntityAnnotation(item_name="职位类型")
	private String position_type ;
	

	@EntityAnnotation(item_name="兼职类型1")
	private String position_type1 ;
	
	@EntityAnnotation(item_name="兼职类型2")
	private String position_type2 ;
	
	@EntityAnnotation(item_name="兼职类型3")
	private String position_type3 ;


	
	
	private Timestamp build_datetime;
	private String build_userid;
	private String build_username;
	private String delete_flag;
	private Timestamp delete_datetime;
	private String delete_userid;
	private String delete_username;
	
	
	///////////////////////////
	////////////扩展使用///////
	//////////////////////////
	
	
	private String errorInfo="" ;
	@EntityAnnotation(item_name="职位类型" ,item_sort=4 , length=20 )
	private String position_type_temp ;
	

	@EntityAnnotation(item_name="部门名称" ,item_sort=3 , length=50 )
	private String dept_name;
	
	
	public String getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}
	public String getStaff_name() {
		return staff_name;
	}
	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
	}
	public String getPosition_type() {
		return position_type;
	}
	public void setPosition_type(String position_type) {
		this.position_type = position_type;
	}
	public String getPosition_type1() {
		return position_type1;
	}
	public void setPosition_type1(String position_type1) {
		this.position_type1 = position_type1;
	}
	public String getPosition_type2() {
		return position_type2;
	}
	public void setPosition_type2(String position_type2) {
		this.position_type2 = position_type2;
	}
	public String getPosition_type3() {
		return position_type3;
	}
	public void setPosition_type3(String position_type3) {
		this.position_type3 = position_type3;
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
	public String getDelete_username() {
		return delete_username;
	}
	public void setDelete_username(String delete_username) {
		this.delete_username = delete_username;
	}
	public String getErrorInfo() {
		return errorInfo;
	}
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
	public String getPosition_type_temp() {
		return position_type_temp;
	}
	public void setPosition_type_temp(String position_type_temp) {
		this.position_type_temp = position_type_temp;
	}
	public String getStaff_no() {
		return staff_no;
	}
	public void setStaff_no(String staff_no) {
		this.staff_no = staff_no;
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
	



}
