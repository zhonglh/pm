package com.pm.domain.business;

import java.io.Serializable;
import java.sql.Timestamp;

import com.pm.util.log.EntityAnnotation;

/**
 * 保险档次表
 * @author zhonglh
 *
 */
@SuppressWarnings("serial")
public class InsuranceGrade implements Serializable {
	
	
	private String insurance_grade_id;
	

	@EntityAnnotation(item_name="社保种类")
	private String insurance_radix;
	
	

	@EntityAnnotation(item_name="社保基数")
	private double base_cardinal;

	@EntityAnnotation(item_name="个人缴纳养老保险")
	private double endowment_insurance_bypersonal;

	@EntityAnnotation(item_name="个人缴纳医疗保险")
	private double medical_insurance_bypersonal;

	@EntityAnnotation(item_name="个人缴纳失业保险")
	private double losejob_insurance_bypersonal;

	@EntityAnnotation(item_name="个人缴纳公积金")
	private double reservefund_bypersonal;

	@EntityAnnotation(item_name="缴纳个人所得税")
	private double incometax_bypersonal;

	@EntityAnnotation(item_name="公司缴纳养老保险")
	private double endowment_insurance_bycompany;

	@EntityAnnotation(item_name="公司缴纳医疗保险")
	private double medical_insurance_bycompany;

	@EntityAnnotation(item_name="公司缴纳失业保险")
	private double losejob_insurance_bycompany;

	@EntityAnnotation(item_name="公司缴纳生育保险")
	private double maternity_insurance_bycompany;

	@EntityAnnotation(item_name="公司缴纳工伤保险")
	private double jobharm_insurance_bycompany;

	@EntityAnnotation(item_name="公司缴纳公积金")
	private double reservefund_bypcompany;
	

	private Timestamp 	build_datetime;	
	private String 	build_userid	;
	private String 	build_username	;
	private String 	delete_flag	;
	private Timestamp 	delete_datetime	;
	private String 	delete_userid	;
	private String 	delete_username	;
	

	public String getInsurance_grade_id() {
		return insurance_grade_id;
	}

	public void setInsurance_grade_id(String insurance_grade_id) {
		this.insurance_grade_id = insurance_grade_id;
	}

	public String getInsurance_radix() {
		return insurance_radix;
	}

	public void setInsurance_radix(String insurance_radix) {
		this.insurance_radix = insurance_radix;
	}

	public double getEndowment_insurance_bypersonal() {
		return endowment_insurance_bypersonal;
	}

	public void setEndowment_insurance_bypersonal(
			double endowment_insurance_bypersonal) {
		this.endowment_insurance_bypersonal = endowment_insurance_bypersonal;
	}

	public double getMedical_insurance_bypersonal() {
		return medical_insurance_bypersonal;
	}

	public void setMedical_insurance_bypersonal(double medical_insurance_bypersonal) {
		this.medical_insurance_bypersonal = medical_insurance_bypersonal;
	}

	public double getLosejob_insurance_bypersonal() {
		return losejob_insurance_bypersonal;
	}

	public void setLosejob_insurance_bypersonal(double losejob_insurance_bypersonal) {
		this.losejob_insurance_bypersonal = losejob_insurance_bypersonal;
	}

	public double getReservefund_bypersonal() {
		return reservefund_bypersonal;
	}

	public void setReservefund_bypersonal(double reservefund_bypersonal) {
		this.reservefund_bypersonal = reservefund_bypersonal;
	}

	public double getIncometax_bypersonal() {
		return incometax_bypersonal;
	}

	public void setIncometax_bypersonal(double incometax_bypersonal) {
		this.incometax_bypersonal = incometax_bypersonal;
	}

	public double getEndowment_insurance_bycompany() {
		return endowment_insurance_bycompany;
	}

	public void setEndowment_insurance_bycompany(
			double endowment_insurance_bycompany) {
		this.endowment_insurance_bycompany = endowment_insurance_bycompany;
	}

	public double getMedical_insurance_bycompany() {
		return medical_insurance_bycompany;
	}

	public void setMedical_insurance_bycompany(double medical_insurance_bycompany) {
		this.medical_insurance_bycompany = medical_insurance_bycompany;
	}

	public double getLosejob_insurance_bycompany() {
		return losejob_insurance_bycompany;
	}

	public void setLosejob_insurance_bycompany(double losejob_insurance_bycompany) {
		this.losejob_insurance_bycompany = losejob_insurance_bycompany;
	}

	public double getMaternity_insurance_bycompany() {
		return maternity_insurance_bycompany;
	}

	public void setMaternity_insurance_bycompany(
			double maternity_insurance_bycompany) {
		this.maternity_insurance_bycompany = maternity_insurance_bycompany;
	}

	public double getJobharm_insurance_bycompany() {
		return jobharm_insurance_bycompany;
	}

	public void setJobharm_insurance_bycompany(double jobharm_insurance_bycompany) {
		this.jobharm_insurance_bycompany = jobharm_insurance_bycompany;
	}

	public double getReservefund_bypcompany() {
		return reservefund_bypcompany;
	}

	public void setReservefund_bypcompany(double reservefund_bypcompany) {
		this.reservefund_bypcompany = reservefund_bypcompany;
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

	public Timestamp getBuild_datetime() {
		return build_datetime;
	}

	public void setBuild_datetime(Timestamp build_datetime) {
		this.build_datetime = build_datetime;
	}

	public double getBase_cardinal() {
		return base_cardinal;
	}

	public void setBase_cardinal(double base_cardinal) {
		this.base_cardinal = base_cardinal;
	}
	
	


}
