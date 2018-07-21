package com.pm.vo;

import com.pm.util.log.EntityAnnotation;

@SuppressWarnings("serial")
public class SalesStatisticsAll implements java.io.Serializable {


	@EntityAnnotation(item_name="项目名称",item_sort=1)
	private String project_name;
	

	@EntityAnnotation(item_name="销售额总计",item_sort=2)
	private double statistics_amount;




	public double getStatistics_amount() {
		return statistics_amount;
	}


	public void setStatistics_amount(double statistics_amount) {
		this.statistics_amount = statistics_amount;
	}


	public String getProject_name() {
		return project_name;
	}


	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}






}
