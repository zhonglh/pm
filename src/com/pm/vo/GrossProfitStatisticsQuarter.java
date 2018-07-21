package com.pm.vo;

import com.pm.util.log.EntityAnnotation;

@SuppressWarnings("serial")
public class GrossProfitStatisticsQuarter implements java.io.Serializable {


	@EntityAnnotation(item_name="季度",item_sort=1)
	private String quarter;
	


	

	@EntityAnnotation(item_name="毛利润",item_sort=2)
	private double statistics_amount;



	@EntityAnnotation(item_name="毛利率(%)",item_sort=3)
	private double statistics_rate;

	public double getStatistics_amount() {
		return statistics_amount;
	}


	public void setStatistics_amount(double statistics_amount) {
		this.statistics_amount = statistics_amount;
	}


	public String getQuarter() {
		return quarter;
	}


	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}


	public double getStatistics_rate() {
		return statistics_rate;
	}


	public void setStatistics_rate(double statistics_rate) {
		this.statistics_rate = statistics_rate;
	}




	




}
