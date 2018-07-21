package com.pm.vo;

import com.pm.util.log.EntityAnnotation;

@SuppressWarnings("serial")
public class ReceivablesStatisticsQuarter implements java.io.Serializable {


	@EntityAnnotation(item_name="季度",item_sort=1)
	private String quarter;
	


	

	@EntityAnnotation(item_name="应收款",item_sort=2)
	private double statistics_amount;




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




	




}
