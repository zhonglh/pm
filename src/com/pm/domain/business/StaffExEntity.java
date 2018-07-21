package com.pm.domain.business;

import com.pm.util.log.EntityAnnotation;

@SuppressWarnings("serial")
public class StaffExEntity extends IdEntity {
	
	private String salary_model;
	
	@EntityAnnotation(item_name="垫资金额", item_sort=4,length=30)
	private double guarantee_amount; 
	
	private String parent_id;
	
	@EntityAnnotation(item_name="其它固定支出金额", item_sort=6,length=30)
	private double other_expenditures;
	

	@EntityAnnotation(item_name="其它支出系数", item_sort=7,length=30)
	private double other_ratio;
	
	private int level_num;
	

	@EntityAnnotation(item_name="抵税发票金额", item_sort=8,length=9)
	private double credit_tax_amount;
	

	@EntityAnnotation(item_name="说明", item_sort=9,length=1000)
	private String remarks;
	//////////////////////////////////////////
	
	

	@EntityAnnotation(item_name="员工工号", item_sort=1,length=30)
	private String staff_no;


	@EntityAnnotation(item_name="员工姓名", item_sort=2)
	private String staff_name;
	
	
	@EntityAnnotation(item_name="工资模式", item_sort=3,length=30)
	private String salary_model_name;
	
	@EntityAnnotation(item_name="上线", item_sort=5,length=30)
	private String parent_name;
	
	//最新报价
	private double qustomerquotes;
	
	//正式工资
	private double official_salary;
	
	//查询条件
	private String staff_id;
	private String dept_id;
	private String dept_name;
	private String project_name;
	

	public String getSalary_model() {
		return salary_model;
	}

	public void setSalary_model(String salary_model) {
		this.salary_model = salary_model;
	}

	public double getGuarantee_amount() {
		return guarantee_amount;
	}

	public void setGuarantee_amount(double guarantee_amount) {
		this.guarantee_amount = guarantee_amount;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public double getOther_expenditures() {
		return other_expenditures;
	}

	public void setOther_expenditures(double other_expenditures) {
		this.other_expenditures = other_expenditures;
	}

	public String getSalary_model_name() {
		return salary_model_name;
	}

	public void setSalary_model_name(String salary_model_name) {
		this.salary_model_name = salary_model_name;
	}

	public String getParent_name() {
		return parent_name;
	}

	public void setParent_name(String parent_name) {
		this.parent_name = parent_name;
	}

	public int getLevel_num() {
		return level_num;
	}

	public void setLevel_num(int level_num) {
		this.level_num = level_num;
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

	@Override
	public String toString() {
		return staff_name;
	}

	public String getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}

	public double getOther_ratio() {
		return other_ratio;
	}

	public void setOther_ratio(double other_ratio) {
		this.other_ratio = other_ratio;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public double getCredit_tax_amount() {
		return credit_tax_amount;
	}

	public void setCredit_tax_amount(double credit_tax_amount) {
		this.credit_tax_amount = credit_tax_amount;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public double getQustomerquotes() {
		return qustomerquotes;
	}

	public void setQustomerquotes(double qustomerquotes) {
		this.qustomerquotes = qustomerquotes;
	}

	public double getOfficial_salary() {
		return official_salary;
	}

	public void setOfficial_salary(double official_salary) {
		this.official_salary = official_salary;
	}
	
	
	
	
	

	
	
}
