package com.pm.domain.business;

import java.util.Date;

import com.pm.util.log.EntityAnnotation;


@SuppressWarnings("serial")
public class PersonnelMonthlyBonus extends PersonnelMonthlyBase{
	

   

   @EntityAnnotation(item_name="入职时间" ,item_sort=6)
   private Date join_datetime ;
   

   @EntityAnnotation(item_name="奖惩时间" ,item_sort=7 ,  length = 10)
   private Date change_time;
   

   @EntityAnnotation(item_name="奖惩金额" ,item_sort=8,  length = 10)
   private double amount ;

   @EntityAnnotation(item_name="备注" ,item_sort=9,   length = 50)
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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	   
	   
	   
	   
	   

}
