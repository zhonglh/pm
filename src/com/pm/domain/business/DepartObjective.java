package com.pm.domain.business;

import java.io.Serializable;

import com.pm.util.log.EntityAnnotation;

@SuppressWarnings("serial")
public class DepartObjective implements Serializable {

	private String id;
	private String depart_id;
	

	@EntityAnnotation(item_name="年度")
	private int years;
	

	@EntityAnnotation(item_name="利润目标")
	private double profit_objective;
	
	
	//部门名称
	private String depart_name;

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getDepart_id() {
		return depart_id;
	}


	public void setDepart_id(String depart_id) {
		this.depart_id = depart_id;
	}


	public int getYears() {
		return years;
	}


	public void setYears(int years) {
		this.years = years;
	}


	public double getProfit_objective() {
		return profit_objective;
	}


	public void setProfit_objective(double profit_objective) {
		this.profit_objective = profit_objective;
	}


	public String getDepart_name() {
		return depart_name;
	}


	public void setDepart_name(String depart_name) {
		this.depart_name = depart_name;
	}
}
