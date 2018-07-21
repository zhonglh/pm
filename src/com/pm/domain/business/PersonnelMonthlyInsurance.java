package com.pm.domain.business;

import com.pm.util.log.EntityAnnotation;

@SuppressWarnings("serial")
public class PersonnelMonthlyInsurance extends PersonnelMonthlyBase{
	
	

	private  String old_insurance_grade_id;
	private String old_securty_unit;

   private String insurance_grade_id;    
   
   @EntityAnnotation(item_name="社保缴纳单位" ,item_sort=15,   length = 200)
   private String securty_unit;

   //@EntityAnnotation(item_name="社保说明" ,item_sort=14)
   private String social_security ;

   //@EntityAnnotation(item_name="参保城市" ,item_sort=15)
   private String insured_city ;


   @EntityAnnotation(item_name="备注" ,item_sort=16,   length = 60)
   private String description      ;

	/////////////////////////////////////////////
	//////////////扩展////////////////////////////
	/////////////////////////////////////////////
	   
	@EntityAnnotation(item_name="养老个人", item_sort=7)
	private double endowment_insurance_bypersonal;
	
	@EntityAnnotation(item_name="养老单位", item_sort=8)
	private double endowment_insurance_bycompany;
	
	
	@EntityAnnotation(item_name="医疗个人", item_sort=9)
	private double medical_insurance_bypersonal;
	
	@EntityAnnotation(item_name="医疗单位", item_sort=10)
	private double medical_insurance_bycompany;
	
	@EntityAnnotation(item_name="失业个人", item_sort=11)
	private double losejob_insurance_bypersonal;
	
	@EntityAnnotation(item_name="失业单位", item_sort=12)
	private double losejob_insurance_bycompany;
	
	@EntityAnnotation(item_name="工伤单位", item_sort=13)
	private double jobharm_insurance_bycompany;
	
	@EntityAnnotation(item_name="生育单位", item_sort=14)
	private double maternity_insurance_bycompany; 	   
	   
	
	@EntityAnnotation(item_name="社保档次", item_sort=6 ,   length = 60)
	private String insurance_radix;
	

	
	public String getInsurance_grade_id() {
		return insurance_grade_id;
	}
	
	public void setInsurance_grade_id(String insurance_grade_id) {
		this.insurance_grade_id = insurance_grade_id;
	}
	
	public String getSecurty_unit() {
		return securty_unit;
	}
	
	public void setSecurty_unit(String securty_unit) {
		this.securty_unit = securty_unit;
	}
	
	public String getSocial_security() {
		return social_security;
	}
	
	public void setSocial_security(String social_security) {
		this.social_security = social_security;
	}
	
	public String getInsured_city() {
		return insured_city;
	}
	
	public void setInsured_city(String insured_city) {
		this.insured_city = insured_city;
	}

	public double getEndowment_insurance_bypersonal() {
		return endowment_insurance_bypersonal;
	}

	public void setEndowment_insurance_bypersonal(double endowment_insurance_bypersonal) {
		this.endowment_insurance_bypersonal = endowment_insurance_bypersonal;
	}

	public double getEndowment_insurance_bycompany() {
		return endowment_insurance_bycompany;
	}

	public void setEndowment_insurance_bycompany(double endowment_insurance_bycompany) {
		this.endowment_insurance_bycompany = endowment_insurance_bycompany;
	}

	public double getMedical_insurance_bypersonal() {
		return medical_insurance_bypersonal;
	}

	public void setMedical_insurance_bypersonal(double medical_insurance_bypersonal) {
		this.medical_insurance_bypersonal = medical_insurance_bypersonal;
	}

	public double getMedical_insurance_bycompany() {
		return medical_insurance_bycompany;
	}

	public void setMedical_insurance_bycompany(double medical_insurance_bycompany) {
		this.medical_insurance_bycompany = medical_insurance_bycompany;
	}

	public double getLosejob_insurance_bypersonal() {
		return losejob_insurance_bypersonal;
	}

	public void setLosejob_insurance_bypersonal(double losejob_insurance_bypersonal) {
		this.losejob_insurance_bypersonal = losejob_insurance_bypersonal;
	}

	public double getLosejob_insurance_bycompany() {
		return losejob_insurance_bycompany;
	}

	public void setLosejob_insurance_bycompany(double losejob_insurance_bycompany) {
		this.losejob_insurance_bycompany = losejob_insurance_bycompany;
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


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOld_insurance_grade_id() {
		return old_insurance_grade_id;
	}

	public void setOld_insurance_grade_id(String old_insurance_grade_id) {
		this.old_insurance_grade_id = old_insurance_grade_id;
	}

	public String getOld_securty_unit() {
		return old_securty_unit;
	}

	public void setOld_securty_unit(String old_securty_unit) {
		this.old_securty_unit = old_securty_unit;
	}

	public String getInsurance_radix() {
		return insurance_radix;
	}

	public void setInsurance_radix(String insurance_radix) {
		this.insurance_radix = insurance_radix;
	}
	   
	   

}
