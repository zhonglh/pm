package com.pm.domain.business;

import java.util.Date;

import com.pm.util.log.EntityAnnotation;

@SuppressWarnings("serial")
public class PersonnelMonthlySalarySupply extends PersonnelMonthlyBase{


	   

	   @EntityAnnotation(item_name="工资补充类型" ,item_sort=6,   length = 25)
	   private String supply_type ;
	   

	   @EntityAnnotation(item_name="工资补充时间" ,item_sort=7,   length = 10)
	   private Date supply_time;
	   

	   @EntityAnnotation(item_name="补贴扣除金额" ,item_sort=8,   length = 10)
	   private double amount ;

	   @EntityAnnotation(item_name="备注" ,item_sort=9,   length = 50)
	   private String description      ;	   
	     
		   
	   
	   
	
	
		public String getSupply_type() {
			return supply_type;
		}
	
		public void setSupply_type(String supply_type) {
			this.supply_type = supply_type;
		}
	
		public Date getSupply_time() {
			return supply_time;
		}
	
		public void setSupply_time(Date supply_time) {
			this.supply_time = supply_time;
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
