package com.pm.vo;

import com.pm.util.log.EntityAnnotation;

@SuppressWarnings("serial")
public class ReceivablesStatisticsInfoSourcer implements java.io.Serializable {


	@EntityAnnotation(item_name="信息来源",item_sort=1)
	private String info_sources_username;
	

	@EntityAnnotation(item_name="应收款",item_sort=2)
	private double statistics_amount;




	public double getStatistics_amount() {
		return statistics_amount;
	}


	public void setStatistics_amount(double statistics_amount) {
		this.statistics_amount = statistics_amount;
	}


	public String getInfo_sources_username() {
		return info_sources_username;
	}


	public void setInfo_sources_username(String info_sources_username) {
		this.info_sources_username = info_sources_username;
	}


}
