package com.pm.vo.reports;

import java.math.BigDecimal;

@SuppressWarnings("serial")
public class StaffCountVO implements java.io.Serializable{
	
	private String staff_id;
	private String staff_no;
	private String staff_name;
	

	private BigDecimal allChilds;
	private BigDecimal coreChilds;
	

	private double income_;
	private double pay_;
	private double tax;
	private double actual_salary;
	
	
	public String getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
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
	public BigDecimal getAllChilds() {
		return allChilds;
	}
	public void setAllChilds(BigDecimal allChilds) {
		this.allChilds = allChilds;
	}
	public BigDecimal getCoreChilds() {
		return coreChilds;
	}
	public void setCoreChilds(BigDecimal coreChilds) {
		this.coreChilds = coreChilds;
	}
	public double getIncome_() {
		return income_;
	}
	public void setIncome_(double income_) {
		this.income_ = income_;
	}
	public double getPay_() {
		return pay_;
	}
	public void setPay_(double pay_) {
		this.pay_ = pay_;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public double getActual_salary() {
		return actual_salary;
	}
	public void setActual_salary(double actual_salary) {
		this.actual_salary = actual_salary;
	}
	
	

}
