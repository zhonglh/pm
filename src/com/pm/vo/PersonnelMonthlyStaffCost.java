package com.pm.vo;

import java.io.Serializable;

import com.common.utils.spring.SpringContextUtil;
import com.pm.domain.business.StaffCost;
import com.pm.util.log.EntityAnnotation;

public class PersonnelMonthlyStaffCost extends StaffCost implements Serializable{
	
	private static final long serialVersionUID = 6429461500876138865L;

	@EntityAnnotation(item_name="月报月份" ,item_sort=-2) 
	private int the_month;		

	//类型
	private String monthly_type;		
	

	@EntityAnnotation(item_name="月报类型" ,item_sort=-1) 
	private String monthly_type_name;


	public int getThe_month() {
		return the_month;
	}


	public void setThe_month(int the_month) {
		this.the_month = the_month;
	}


	public String getMonthly_type() {
		return monthly_type;
	}


	public void setMonthly_type(String monthly_type) {
		this.monthly_type = monthly_type;
	}


	public String getMonthly_type_name() {
		if(monthly_type_name == null || monthly_type_name.isEmpty()){
			monthly_type_name = SpringContextUtil.getMessage("personnel.monthly."+monthly_type);
		}
		return monthly_type_name;
	}


	public void setMonthly_type_name(String monthly_type_name) {
		this.monthly_type_name = monthly_type_name;
	}
	
	
	

}
