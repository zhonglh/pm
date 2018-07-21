package com.pm.vo;

import com.pm.util.log.EntityAnnotation;

@SuppressWarnings("serial")
public class ReceivablesStatisticsManager implements java.io.Serializable {


	@EntityAnnotation(item_name="项目管理人",item_sort=1)
	private String manage_username;
	

	@EntityAnnotation(item_name="应收款",item_sort=2)
	private double statistics_amount;




	public double getStatistics_amount() {
		return statistics_amount;
	}


	public void setStatistics_amount(double statistics_amount) {
		this.statistics_amount = statistics_amount;
	}


	public String getManage_username() {
		return manage_username;
	}


	public void setManage_username(String manage_username) {
		this.manage_username = manage_username;
	}
}
