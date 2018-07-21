package com.pm.vo;

import com.pm.util.log.EntityAnnotation;

@SuppressWarnings("serial")
public class ReceivablesStatisticsSalesDept implements java.io.Serializable {


	@EntityAnnotation(item_name="销售额所属部门",item_sort=1)
	private String sales_amount_deptname;
	

	@EntityAnnotation(item_name="应收款",item_sort=2)
	private double statistics_amount;




	public double getStatistics_amount() {
		return statistics_amount;
	}


	public void setStatistics_amount(double statistics_amount) {
		this.statistics_amount = statistics_amount;
	}


	public String getSales_amount_deptname() {
		return sales_amount_deptname;
	}


	public void setSales_amount_deptname(String sales_amount_deptname) {
		this.sales_amount_deptname = sales_amount_deptname;
	}




}
