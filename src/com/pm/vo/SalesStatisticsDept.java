package com.pm.vo;

import com.pm.util.log.EntityAnnotation;

@SuppressWarnings("serial")
public class SalesStatisticsDept implements java.io.Serializable {


	@EntityAnnotation(item_name="项目所属部门",item_sort=1)
	private String deptname;
	

	@EntityAnnotation(item_name="销售额",item_sort=2)
	private double statistics_amount;




	public double getStatistics_amount() {
		return statistics_amount;
	}


	public void setStatistics_amount(double statistics_amount) {
		this.statistics_amount = statistics_amount;
	}


	public String getDeptname() {
		return deptname;
	}


	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}




}
