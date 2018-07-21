package com.pm.vo;

import com.pm.util.log.EntityAnnotation;

@SuppressWarnings("serial")
public class SalesStatisticsYear implements java.io.Serializable {


	@EntityAnnotation(item_name="年度",item_sort=1)
	private String year;
	

	//@EntityAnnotation(item_name="项目名称",item_sort=2)
	//private String project_name;
	

	@EntityAnnotation(item_name="销售额",item_sort=2)
	private double statistics_amount;




	public double getStatistics_amount() {
		return statistics_amount;
	}


	public void setStatistics_amount(double statistics_amount) {
		this.statistics_amount = statistics_amount;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year1) {
		this.year = year1;
	}


	




}
