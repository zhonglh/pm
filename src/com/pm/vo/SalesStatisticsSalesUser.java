package com.pm.vo;

import com.pm.util.log.EntityAnnotation;

@SuppressWarnings("serial")
public class SalesStatisticsSalesUser implements java.io.Serializable {


	@EntityAnnotation(item_name="销售负责人",item_sort=1)
	private String sales_username;
	

	@EntityAnnotation(item_name="销售额",item_sort=2)
	private double statistics_amount;


	public String getSales_username() {
		return sales_username;
	}


	public void setSales_username(String sales_username) {
		this.sales_username = sales_username;
	}


	public double getStatistics_amount() {
		return statistics_amount;
	}


	public void setStatistics_amount(double statistics_amount) {
		this.statistics_amount = statistics_amount;
	}
}
