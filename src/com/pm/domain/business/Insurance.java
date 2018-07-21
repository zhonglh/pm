package com.pm.domain.business;

import java.io.Serializable;
import java.sql.Timestamp;

import com.pm.util.log.EntityAnnotation;

/**
 * 保险
 * @author zhonglh
 *
 */
@SuppressWarnings("serial")
public class Insurance extends IdEntity implements Serializable {
	

	private String staff_id ;

	@EntityAnnotation(item_name="月份" ,item_sort=1)
	private int salary_month ;

	@EntityAnnotation(item_name="养老个人", item_sort=4,length=10)
	private double endowment_insurance_bypersonal;	
	
	@EntityAnnotation(item_name="养老单位" , item_sort=5,length=10)
	private double endowment_insurance_bycompany;
	


	@EntityAnnotation(item_name="医疗个人", item_sort=6,length=10)
	private double medical_insurance_bypersonal;
	

	@EntityAnnotation(item_name="医疗单位", item_sort=7,length=10)
	private double medical_insurance_bycompany;	


	@EntityAnnotation(item_name="失业个人", item_sort=8,length=10)
	private double losejob_insurance_bypersonal;
	

	@EntityAnnotation(item_name="失业单位", item_sort=9,length=10)
	private double losejob_insurance_bycompany;

	@EntityAnnotation(item_name="生育单位", item_sort=10,length=10)
	private double maternity_insurance_bycompany;
	

	@EntityAnnotation(item_name="工伤单位", item_sort=11,length=10)
	private double jobharm_insurance_bycompany;
	


	@EntityAnnotation(item_name="公积金个人", item_sort=12,length=10)
	private double reservefund_bypersonal;

	@EntityAnnotation(item_name="公积金单位", item_sort=13,length=10)
	private double reservefund_bypcompany;



	@EntityAnnotation(item_name="社保缴纳单位", item_sort=14,length=60)
	private String securty_unit;




	

	private Timestamp 	build_datetime;	
	private String 	build_userid	;
	private String 	build_username	;
	
	private String verify_username;
	private String verify_userid;
	private Timestamp verify_datetime;
	
	
	//////////////////////////////////////////////////////////////////

	@EntityAnnotation(item_name="工号" , item_sort=2,length=30)
	private String staff_no;
	

	@EntityAnnotation(item_name="姓名" , item_sort=3,length=30)
	private String staff_name;


	private String project_id ;
	
	//1: 已核单，  2:未核单
	private String verify_flag;
	

	//是否申请 取消核单状态 1:已申请取消核单  其他:正常
	private String need_approve;
	

	private String errorInfo = "";
	
	
	
	private int salary_month1;
	
	private int salary_month2;
	
	
	
	public String getStaff_id() {
		return staff_id;
	}


	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}


	public double getEndowment_insurance_bycompany() {
		return endowment_insurance_bycompany;
	}


	public void setEndowment_insurance_bycompany(double endowment_insurance_bycompany) {
		this.endowment_insurance_bycompany = endowment_insurance_bycompany;
	}


	public double getEndowment_insurance_bypersonal() {
		return endowment_insurance_bypersonal;
	}


	public void setEndowment_insurance_bypersonal(double endowment_insurance_bypersonal) {
		this.endowment_insurance_bypersonal = endowment_insurance_bypersonal;
	}


	public double getLosejob_insurance_bycompany() {
		return losejob_insurance_bycompany;
	}


	public void setLosejob_insurance_bycompany(double losejob_insurance_bycompany) {
		this.losejob_insurance_bycompany = losejob_insurance_bycompany;
	}


	public double getLosejob_insurance_bypersonal() {
		return losejob_insurance_bypersonal;
	}


	public void setLosejob_insurance_bypersonal(double losejob_insurance_bypersonal) {
		this.losejob_insurance_bypersonal = losejob_insurance_bypersonal;
	}


	public double getJobharm_insurance_bycompany() {
		return jobharm_insurance_bycompany;
	}


	public void setJobharm_insurance_bycompany(double jobharm_insurance_bycompany) {
		this.jobharm_insurance_bycompany = jobharm_insurance_bycompany;
	}


	public double getMaternity_insurance_bycompany() {
		return maternity_insurance_bycompany;
	}


	public void setMaternity_insurance_bycompany(double maternity_insurance_bycompany) {
		this.maternity_insurance_bycompany = maternity_insurance_bycompany;
	}


	public double getMedical_insurance_bycompany() {
		return medical_insurance_bycompany;
	}


	public void setMedical_insurance_bycompany(double medical_insurance_bycompany) {
		this.medical_insurance_bycompany = medical_insurance_bycompany;
	}


	public double getMedical_insurance_bypersonal() {
		return medical_insurance_bypersonal;
	}


	public void setMedical_insurance_bypersonal(double medical_insurance_bypersonal) {
		this.medical_insurance_bypersonal = medical_insurance_bypersonal;
	}


	public double getReservefund_bypcompany() {
		return reservefund_bypcompany;
	}


	public void setReservefund_bypcompany(double reservefund_bypcompany) {
		this.reservefund_bypcompany = reservefund_bypcompany;
	}


	public double getReservefund_bypersonal() {
		return reservefund_bypersonal;
	}


	public void setReservefund_bypersonal(double reservefund_bypersonal) {
		this.reservefund_bypersonal = reservefund_bypersonal;
	}


	public String getSecurty_unit() {
		return securty_unit;
	}


	public void setSecurty_unit(String securty_unit) {
		this.securty_unit = securty_unit;
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


	public String getVerify_flag() {
		return verify_flag;
	}


	public void setVerify_flag(String verify_flag) {
		this.verify_flag = verify_flag;
	}


	public String getNeed_approve() {
		return need_approve;
	}


	public void setNeed_approve(String need_approve) {
		this.need_approve = need_approve;
	}


	public String getErrorInfo() {
		return errorInfo;
	}


	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}


	public int getSalary_month() {
		return salary_month;
	}


	public void setSalary_month(int salary_month) {
		this.salary_month = salary_month;
	}


	public int getSalary_month1() {
		return salary_month1;
	}


	public void setSalary_month1(int salary_month1) {
		this.salary_month1 = salary_month1;
	}


	public int getSalary_month2() {
		return salary_month2;
	}


	public void setSalary_month2(int salary_month2) {
		this.salary_month2 = salary_month2;
	}


	@Override
	public String toString() {
		return  staff_name +"  "+ String.valueOf(salary_month);
	}


	public String getProject_id() {
		return project_id;
	}


	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}




	
	
	
	


}
