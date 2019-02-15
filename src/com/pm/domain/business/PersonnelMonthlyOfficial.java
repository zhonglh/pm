package com.pm.domain.business;

import java.util.Date;

import com.pm.util.log.EntityAnnotation;


/**
 * @author Administrator
 */
@SuppressWarnings("serial")
public class PersonnelMonthlyOfficial extends PersonnelMonthlyBase{
	

   

   @EntityAnnotation(item_name="入职时间" ,item_sort=6)
   private Date join_datetime ;
   

   @EntityAnnotation(item_name="转正日期" ,item_sort=7,   length = 10)
   private Date confirmation_date;

	@EntityAnnotation(item_name="当月工作日天数" ,item_sort=8,   length = 2)
   private int work_days ;
   

   @EntityAnnotation(item_name="试用期工资" ,item_sort=9 ,length = 10)
   private double tryout_salary ;


	@EntityAnnotation(item_name="当月试用期工作日天数" ,item_sort=10,   length = 2)
	private int tryout_days ;



	@EntityAnnotation(item_name="转正工资" ,item_sort=11,length = 10)
   private double official_salary;


	@EntityAnnotation(item_name="当月转正工作日天数" ,item_sort=12,   length = 2)
	private int official_days ;
   

   @EntityAnnotation(item_name="转正当月工资" ,item_sort=13,   length = 10)
   private double curr_salary  ;

   
   

	public Date getJoin_datetime() {
		return join_datetime;
	}


	public void setJoin_datetime(Date join_datetime) {
		this.join_datetime = join_datetime;
	}


	public Date getConfirmation_date() {
		return confirmation_date;
	}


	public void setConfirmation_date(Date confirmation_date) {
		this.confirmation_date = confirmation_date;
	}


	public double getTryout_salary() {
		return tryout_salary;
	}


	public void setTryout_salary(double tryout_salary) {
		this.tryout_salary = tryout_salary;
	}


	public double getOfficial_salary() {
		return official_salary;
	}


	public void setOfficial_salary(double official_salary) {
		this.official_salary = official_salary;
	}


	public double getCurr_salary() {
		return curr_salary;
	}


	public void setCurr_salary(double curr_salary) {
		this.curr_salary = curr_salary;
	}

	public int getWork_days() {
		return work_days;
	}

	public void setWork_days(int work_days) {
		this.work_days = work_days;
	}

	public int getTryout_days() {
		return tryout_days;
	}

	public void setTryout_days(int tryout_days) {
		this.tryout_days = tryout_days;
	}

	public int getOfficial_days() {
		return official_days;
	}

	public void setOfficial_days(int official_days) {
		this.official_days = official_days;
	}
}
