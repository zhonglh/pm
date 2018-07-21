package com.pm.domain.business;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.pm.util.log.EntityAnnotation;

@SuppressWarnings("serial")
public class SalaryMail implements Serializable {
	
	//最后的发送明细ID
	private String max_detail_id;
	
	//状态， 1:发送完成  2:等待发送
	private String send_status;
	
	private String send_userId;
	
	
	private List<SalaryMailDetail> details;
	
	
	private String detail_status;
	
	//extend salary object


	private String salary_id;
	private String staff_id;
	private String project_id;
	
	

	@EntityAnnotation(item_name="工资月份")
	private int salary_month;
	

	@EntityAnnotation(item_name="基本工资",item_sort=2)
	private double basic_salary;
	
	

	@EntityAnnotation(item_name="岗位工资", item_sort=3)
	private double post_salary;

	@EntityAnnotation(item_name="绩效津贴", item_sort=4)
	private double performance_allowances;	
	
	
	
	@EntityAnnotation(item_name="出差补助",item_sort=5)
	private double travel_allowance;
	
	@EntityAnnotation(item_name="含税奖金",item_sort=6)
	private double tax_bonus;
	
	@EntityAnnotation(item_name="应出勤天数",item_sort=7)
	private double should_work_days;
	
	@EntityAnnotation(item_name="实际工作天数",item_sort=8)
	private double work_days;
	
	@EntityAnnotation(item_name="带薪假天数",item_sort=9)
	private double paid_leave_days;
	
	@EntityAnnotation(item_name="出差天数",item_sort=10)
	private double business_trip_days;
	
	@EntityAnnotation(item_name="事假天数",item_sort=11)
	private double personal_leave_days;
	
	@EntityAnnotation(item_name="病假天数",item_sort=12)
	private double sick_leave_days;
	
	@EntityAnnotation(item_name="旷工天数",item_sort=13)
	private double neglect_work_days;
	
	@EntityAnnotation(item_name="迟到天数",item_sort=14)
	private double late_days;
	
	@EntityAnnotation(item_name="加班天数",item_sort=15)
	private double weekend_overtime_days;
	
	@EntityAnnotation(item_name="病假工资",item_sort=16)
	private double sick_leave_salary;
	
	@EntityAnnotation(item_name="旷工工资",item_sort=17)
	private double neglect_work_salary;
	
	@EntityAnnotation(item_name="迟到工资",item_sort=18)
	private double late_salary;
	
	@EntityAnnotation(item_name="出差补贴",item_sort=19)
	private double actual_travel_allowance;
	
	@EntityAnnotation(item_name="电脑补贴",item_sort=20)
	private double actual_computer_allowance;
	
	@EntityAnnotation(item_name="额外补贴",item_sort=21)
	private double actual_extra_allowance;
	
	@EntityAnnotation(item_name="加班补贴",item_sort=22)
	private double overtime_allowance;
	
	@EntityAnnotation(item_name="餐补",item_sort=23)
	private double meals_allowance;
	
	@EntityAnnotation(item_name="实发奖金（税前）",item_sort=24)
	private double actual_tax_bonus;
	
	@EntityAnnotation(item_name="应发工资",item_sort=25)
	private double should_salary;
	
	@EntityAnnotation(item_name="养老保险",item_sort=26)
	private double pension_insurance;

	@EntityAnnotation(item_name="公司缴纳养老保险",item_sort=27)
	private double endowment_insurance_bycompany;	
	
	@EntityAnnotation(item_name="医疗保险",item_sort=28)
	private double medical_Insurance;
	
	@EntityAnnotation(item_name="公司缴纳医疗保险",item_sort=29)
	private double medical_insurance_bycompany;

	
	@EntityAnnotation(item_name="失业保险",item_sort=30)
	private double unemployment_insurance;
	
	@EntityAnnotation(item_name="公司缴纳失业保险",item_sort=31)
	private double losejob_insurance_bycompany;

	@EntityAnnotation(item_name="公司缴纳生育保险",item_sort=32)
	private double maternity_insurance_bycompany;

	@EntityAnnotation(item_name="公司缴纳工伤保险",item_sort=33)
	private double jobharm_insurance_bycompany;


	@EntityAnnotation(item_name="公积金",item_sort=34)
	private double accumulation_fund;

	@EntityAnnotation(item_name="公司缴纳公积金",item_sort=35)
	private double reservefund_bypcompany;	
	
	@EntityAnnotation(item_name="准许扣除的费用",item_sort=36)
	private double deductions_cost;
	
	@EntityAnnotation(item_name="应纳税所得额",item_sort=37)
	private double taxable_income;
	
	@EntityAnnotation(item_name="个人所得税",item_sort=38)
	private double personal_income_tax;
	
	@EntityAnnotation(item_name="奖金(后)",item_sort=39)
	private double actual_bonus;
	
	@EntityAnnotation(item_name="补税工资",item_sort=40)
	private double overdue_tax_salary;
	
	@EntityAnnotation(item_name="实发工资",item_sort=41)
	private double actual_salary;

	@EntityAnnotation(item_name="说明",item_sort=42)
	private String description;
	
	
	private Timestamp build_datetime;
	private String build_userid;
	private String build_username;
	private String delete_flag;
	private Timestamp delete_datetime;
	private String delete_userid;
	private String delete_username;
	private String verify_username;
	private String verify_userid;
	private Timestamp verify_datetime;
	
	////////////////////////////////////////////////////
	////////////////////////扩展////////////////////////
	////////////////////////////////////////////////////

	@EntityAnnotation(item_name="项目名称")
	private String project_name;
	


	@EntityAnnotation(item_name="项目编号")
	private String project_no;
	

	@EntityAnnotation(item_name="姓名",item_sort=1)
	private String staff_name;
	
	
	private String email;
	
	//项目工资人数
	private int project_salary_number;
	
	//核单个数
	private int verify_number;
	
	//上个工资月
	private int salary_pre_month;
	

	//核单标记， 用于查询
	//1: 已核单，  2:未核单
	private String verify_flag;
	
	private String dept_id;
	private String dept_name;
	

	private int salary_month1;
	private int salary_month2;
	

	//是否申请 取消核单状态 1:已申请取消核单  其他:正常
	private String need_approve;	

	//电脑补助
	private double computer_allowance;

	//餐补
	private double meal_allowance;
	
	
		

	@Override
	public String toString() {
		return String
				.format("SalaryMail [max_detail_id=%s, send_status=%s, send_userId=%s, details=%s, getSalary_id()=%s]",
						max_detail_id, send_status, send_userId,
						details, getSalary_id());
	}
	

	public String getMax_detail_id() {
		return max_detail_id;
	}

	public void setMax_detail_id(String max_detail_id) {
		this.max_detail_id = max_detail_id;
	}


	public List<SalaryMailDetail> getDetails() {
		return details;
	}

	public void setDetails(List<SalaryMailDetail> details) {
		this.details = details;
	}

	

	public String getSend_userId() {
		return send_userId;
	}

	public void setSend_userId(String send_userId) {
		this.send_userId = send_userId;
	}

	public String getSend_status() {
		return send_status;
	}

	public void setSend_status(String send_status) {
		this.send_status = send_status;
	}


	public String getSalary_id() {
		return salary_id;
	}

	public void setSalary_id(String salary_id) {
		this.salary_id = salary_id;
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

	public int getSalary_month() {
		return salary_month;
	}

	public void setSalary_month(int salary_month) {
		this.salary_month = salary_month;
	}

	public double getBasic_salary() {
		return basic_salary;
	}

	public void setBasic_salary(double basic_salary) {
		this.basic_salary = basic_salary;
	}

	public double getTravel_allowance() {
		return travel_allowance;
	}

	public void setTravel_allowance(double travel_allowance) {
		this.travel_allowance = travel_allowance;
	}

	public double getTax_bonus() {
		return tax_bonus;
	}

	public void setTax_bonus(double tax_bonus) {
		this.tax_bonus = tax_bonus;
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

	public double getPaid_leave_days() {
		return paid_leave_days;
	}

	public void setPaid_leave_days(double paid_leave_days) {
		this.paid_leave_days = paid_leave_days;
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

	public double getSick_leave_salary() {
		return sick_leave_salary;
	}

	public void setSick_leave_salary(double sick_leave_salary) {
		this.sick_leave_salary = sick_leave_salary;
	}

	public double getNeglect_work_salary() {
		return neglect_work_salary;
	}

	public void setNeglect_work_salary(double neglect_work_salary) {
		this.neglect_work_salary = neglect_work_salary;
	}

	public double getLate_salary() {
		return late_salary;
	}

	public void setLate_salary(double late_salary) {
		this.late_salary = late_salary;
	}

	public double getActual_travel_allowance() {
		return actual_travel_allowance;
	}

	public void setActual_travel_allowance(double actual_travel_allowance) {
		this.actual_travel_allowance = actual_travel_allowance;
	}

	public double getActual_computer_allowance() {
		return actual_computer_allowance;
	}

	public void setActual_computer_allowance(double actual_computer_allowance) {
		this.actual_computer_allowance = actual_computer_allowance;
	}

	public double getActual_extra_allowance() {
		return actual_extra_allowance;
	}

	public void setActual_extra_allowance(double actual_extra_allowance) {
		this.actual_extra_allowance = actual_extra_allowance;
	}

	public double getOvertime_allowance() {
		return overtime_allowance;
	}

	public void setOvertime_allowance(double overtime_allowance) {
		this.overtime_allowance = overtime_allowance;
	}

	public double getMeals_allowance() {
		return meals_allowance;
	}

	public void setMeals_allowance(double meals_allowance) {
		this.meals_allowance = meals_allowance;
	}

	public double getActual_tax_bonus() {
		return actual_tax_bonus;
	}

	public void setActual_tax_bonus(double actual_tax_bonus) {
		this.actual_tax_bonus = actual_tax_bonus;
	}

	public double getShould_salary() {
		return should_salary;
	}

	public void setShould_salary(double should_salary) {
		this.should_salary = should_salary;
	}

	public double getPension_insurance() {
		return pension_insurance;
	}

	public void setPension_insurance(double pension_insurance) {
		this.pension_insurance = pension_insurance;
	}

	public double getEndowment_insurance_bycompany() {
		return endowment_insurance_bycompany;
	}

	public void setEndowment_insurance_bycompany(
			double endowment_insurance_bycompany) {
		this.endowment_insurance_bycompany = endowment_insurance_bycompany;
	}

	public double getMedical_Insurance() {
		return medical_Insurance;
	}

	public void setMedical_Insurance(double medical_Insurance) {
		this.medical_Insurance = medical_Insurance;
	}

	public double getMedical_insurance_bycompany() {
		return medical_insurance_bycompany;
	}

	public void setMedical_insurance_bycompany(double medical_insurance_bycompany) {
		this.medical_insurance_bycompany = medical_insurance_bycompany;
	}

	public double getUnemployment_insurance() {
		return unemployment_insurance;
	}

	public void setUnemployment_insurance(double unemployment_insurance) {
		this.unemployment_insurance = unemployment_insurance;
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

	public void setMaternity_insurance_bycompany(
			double maternity_insurance_bycompany) {
		this.maternity_insurance_bycompany = maternity_insurance_bycompany;
	}

	public double getAccumulation_fund() {
		return accumulation_fund;
	}

	public void setAccumulation_fund(double accumulation_fund) {
		this.accumulation_fund = accumulation_fund;
	}

	public double getReservefund_bypcompany() {
		return reservefund_bypcompany;
	}

	public void setReservefund_bypcompany(double reservefund_bypcompany) {
		this.reservefund_bypcompany = reservefund_bypcompany;
	}

	public double getDeductions_cost() {
		return deductions_cost;
	}

	public void setDeductions_cost(double deductions_cost) {
		this.deductions_cost = deductions_cost;
	}

	public double getTaxable_income() {
		return taxable_income;
	}

	public void setTaxable_income(double taxable_income) {
		this.taxable_income = taxable_income;
	}

	public double getPersonal_income_tax() {
		return personal_income_tax;
	}

	public void setPersonal_income_tax(double personal_income_tax) {
		this.personal_income_tax = personal_income_tax;
	}

	public double getActual_bonus() {
		return actual_bonus;
	}

	public void setActual_bonus(double actual_bonus) {
		this.actual_bonus = actual_bonus;
	}

	public double getOverdue_tax_salary() {
		return overdue_tax_salary;
	}

	public void setOverdue_tax_salary(double overdue_tax_salary) {
		this.overdue_tax_salary = overdue_tax_salary;
	}

	public double getActual_salary() {
		return actual_salary;
	}

	public void setActual_salary(double actual_salary) {
		this.actual_salary = actual_salary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getProject_no() {
		return project_no;
	}

	public void setProject_no(String project_no) {
		this.project_no = project_no;
	}

	public String getStaff_name() {
		return staff_name;
	}

	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
	}

	public int getProject_salary_number() {
		return project_salary_number;
	}

	public void setProject_salary_number(int project_salary_number) {
		this.project_salary_number = project_salary_number;
	}

	public int getVerify_number() {
		return verify_number;
	}

	public void setVerify_number(int verify_number) {
		this.verify_number = verify_number;
	}

	public int getSalary_pre_month() {
		return salary_pre_month;
	}

	public void setSalary_pre_month(int salary_pre_month) {
		this.salary_pre_month = salary_pre_month;
	}

	public String getVerify_flag() {
		return verify_flag;
	}

	public void setVerify_flag(String verify_flag) {
		this.verify_flag = verify_flag;
	}


	public String getDetail_status() {
		return detail_status;
	}


	public void setDetail_status(String detail_status) {
		this.detail_status = detail_status;
	}


	public double getPost_salary() {
		return post_salary;
	}


	public void setPost_salary(double post_salary) {
		this.post_salary = post_salary;
	}


	public double getPerformance_allowances() {
		return performance_allowances;
	}


	public void setPerformance_allowances(double performance_allowances) {
		this.performance_allowances = performance_allowances;
	}


	public String getNeed_approve() {
		return need_approve;
	}


	public void setNeed_approve(String need_approve) {
		this.need_approve = need_approve;
	}


	public double getComputer_allowance() {
		return computer_allowance;
	}


	public void setComputer_allowance(double computer_allowance) {
		this.computer_allowance = computer_allowance;
	}


	public double getMeal_allowance() {
		return meal_allowance;
	}


	public void setMeal_allowance(double meal_allowance) {
		this.meal_allowance = meal_allowance;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
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

	
	
	

}
