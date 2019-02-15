package com.pm.domain.business;

import java.util.Date;

import com.pm.util.log.EntityAnnotation;

@SuppressWarnings("serial")
public class PersonnelMonthlySalary extends PersonnelMonthlyBase {

   
   @EntityAnnotation(item_name="入职时间" ,item_sort=6)
   private Date join_datetime ;

   @EntityAnnotation(item_name="调薪时间" ,item_sort=7,   length = 10)
   private Date change_time     ;


	@EntityAnnotation(item_name="当月工作日天数" ,item_sort=8,   length = 2)
	private int work_days ;
   

   @EntityAnnotation(item_name="调薪前薪资" ,item_sort=9,   length = 10)
   private double old_salary ;



	@EntityAnnotation(item_name="当月调薪前工作日天数" ,item_sort=10,   length = 2)
	private int old_days ;
   

   @EntityAnnotation(item_name="调薪后薪资" ,item_sort=11,   length = 10)
   private double new_salary  ;


	@EntityAnnotation(item_name="当月调薪后工作日天数" ,item_sort=12,   length = 2)
	private int new_days ;
   

   @EntityAnnotation(item_name="调薪当月工资" ,item_sort=13,   length = 10)
   private double curr_salary  ;

   @EntityAnnotation(item_name="备注" ,item_sort=14,   length = 50)
   private String description      ;
   


	public Date getJoin_datetime() {
		return join_datetime;
	}

	public void setJoin_datetime(Date join_datetime) {
		this.join_datetime = join_datetime;
	}

	public Date getChange_time() {
		return change_time;
	}

	public void setChange_time(Date change_time) {
		this.change_time = change_time;
	}

	public double getOld_salary() {
		return old_salary;
	}

	public void setOld_salary(double old_salary) {
		this.old_salary = old_salary;
	}

	public double getNew_salary() {
		return new_salary;
	}

	public void setNew_salary(double new_salary) {
		this.new_salary = new_salary;
	}

	public double getCurr_salary() {
		return curr_salary;
	}

	public void setCurr_salary(double curr_salary) {
		this.curr_salary = curr_salary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public int getWork_days() {
		return work_days;
	}

	public void setWork_days(int work_days) {
		this.work_days = work_days;
	}

	public int getOld_days() {
		return old_days;
	}

	public void setOld_days(int old_days) {
		this.old_days = old_days;
	}

	public int getNew_days() {
		return new_days;
	}

	public void setNew_days(int new_days) {
		this.new_days = new_days;
	}
}
