package com.pm.domain.business;

import java.io.Serializable;

/**
 * 月度结算单明细历史信息实体类
 * 
 * @author zhonglh
 * @date 2017-9-24 21:59:01
 */
public class ThMonthlyStatementDetail extends MonthlyStatementDetail implements Serializable {
	private static final long serialVersionUID = 1L;


	private String id;


	private String monthly_statement_his_id;




	private String monthly_statement_detail_id;




	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	public String getMonthly_statement_his_id() {
		return monthly_statement_his_id;
	}




	public void setMonthly_statement_his_id(String monthly_statement_his_id) {
		this.monthly_statement_his_id = monthly_statement_his_id;
	}




	public String getMonthly_statement_detail_id() {
		return monthly_statement_detail_id;
	}




	public void setMonthly_statement_detail_id(String monthly_statement_detail_id) {
		this.monthly_statement_detail_id = monthly_statement_detail_id;
	}





}