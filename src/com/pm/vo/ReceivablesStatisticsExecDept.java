package com.pm.vo;

import com.pm.util.log.EntityAnnotation;

@SuppressWarnings("serial")
public class ReceivablesStatisticsExecDept implements java.io.Serializable {


	@EntityAnnotation(item_name="执行额所属部门",item_sort=1)
	private String exec_amount_deptname;
	

	@EntityAnnotation(item_name="应收款",item_sort=2)
	private double statistics_amount;




	public double getStatistics_amount() {
		return statistics_amount;
	}


	public void setStatistics_amount(double statistics_amount) {
		this.statistics_amount = statistics_amount;
	}


	public String getExec_amount_deptname() {
		return exec_amount_deptname;
	}


	public void setExec_amount_deptname(String exec_amount_deptname) {
		this.exec_amount_deptname = exec_amount_deptname;
	}




}
