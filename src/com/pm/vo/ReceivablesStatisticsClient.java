package com.pm.vo;

import com.pm.util.log.EntityAnnotation;

@SuppressWarnings("serial")
public class ReceivablesStatisticsClient implements java.io.Serializable {


	@EntityAnnotation(item_name="客户名称",item_sort=1)
	private String project_client_name;
	

	@EntityAnnotation(item_name="应收款",item_sort=2)
	private double statistics_amount;




	public double getStatistics_amount() {
		return statistics_amount;
	}


	public void setStatistics_amount(double statistics_amount) {
		this.statistics_amount = statistics_amount;
	}


	public String getProject_client_name() {
		return project_client_name;
	}


	public void setProject_client_name(String project_client_name) {
		this.project_client_name = project_client_name;
	}



}
