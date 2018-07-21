package com.pm.domain.business;

import com.pm.util.log.EntityAnnotation;

@SuppressWarnings("serial")
public class PersonnelMonthlyReserveFund extends PersonnelMonthlyBase{
	
	


	private  String old_insurance_grade_id;
	private String old_securty_unit;

   private String insurance_grade_id;

   @EntityAnnotation(item_name="社保缴纳单位" ,item_sort=10,   length = 200)
   private String securty_unit;

   @EntityAnnotation(item_name="备注" ,item_sort=11,   length = 50)
   private String description      ;


	/////////////////////////////////////////////
	//////////////扩展////////////////////////////
	/////////////////////////////////////////////
   

	@EntityAnnotation(item_name="社保档次", item_sort=6 ,   length = 60)
	private String insurance_radix;
   

	@EntityAnnotation(item_name="个人", item_sort=7)
	private double reservefund_bypersonal;
	   

	@EntityAnnotation(item_name="公司", item_sort=8)
	private double reservefund_bypcompany;

	

	@EntityAnnotation(item_name="基数", item_sort=9)
	private double base_cardinal;

	public String getInsurance_grade_id() {
		return insurance_grade_id;
	}


	public void setInsurance_grade_id(String insurance_grade_id) {
		this.insurance_grade_id = insurance_grade_id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}



	public double getReservefund_bypersonal() {
		return reservefund_bypersonal;
	}


	public void setReservefund_bypersonal(double reservefund_bypersonal) {
		this.reservefund_bypersonal = reservefund_bypersonal;
	}


	public double getReservefund_bypcompany() {
		return reservefund_bypcompany;
	}


	public void setReservefund_bypcompany(double reservefund_bypcompany) {
		this.reservefund_bypcompany = reservefund_bypcompany;
	}


	public String getSecurty_unit() {
		return securty_unit;
	}


	public void setSecurty_unit(String securty_unit) {
		this.securty_unit = securty_unit;
	}


	public double getBase_cardinal() {
		return base_cardinal;
	}


	public void setBase_cardinal(double base_cardinal) {
		this.base_cardinal = base_cardinal;
	}


	public String getInsurance_radix() {
		return insurance_radix;
	}


	public void setInsurance_radix(String insurance_radix) {
		this.insurance_radix = insurance_radix;
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
	


}
