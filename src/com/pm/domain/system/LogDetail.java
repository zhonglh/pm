package com.pm.domain.system;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("serial")
public class LogDetail implements Serializable {

	private String log_detail_id;
	private String log_id;
	private String data_item_code;	
	private String data_item_i18n;	
	private String operation_before;
	private String operation_after;
	private Timestamp operation_time = new Timestamp(System.currentTimeMillis());
	private int order_no;
	
	
	
	public String getLog_detail_id() {
		return log_detail_id;
	}
	public void setLog_detail_id(String log_detail_id) {
		this.log_detail_id = log_detail_id;
	}
	public String getLog_id() {
		return log_id;
	}
	public void setLog_id(String log_id) {
		this.log_id = log_id;
	}
	public String getData_item_code() {
		return data_item_code;
	}
	public void setData_item_code(String data_item_code) {
		this.data_item_code = data_item_code;
	}
	public String getData_item_i18n() {
		return data_item_i18n;
	}
	public void setData_item_i18n(String data_item_i18n) {
		this.data_item_i18n = data_item_i18n;
	}
	public String getOperation_before() {
		return operation_before;
	}
	public void setOperation_before(String operation_before) {
		this.operation_before = operation_before;
	}
	public String getOperation_after() {
		return operation_after;
	}
	public void setOperation_after(String operation_after) {
		this.operation_after = operation_after;
	}
	public Timestamp getOperation_time() {
		return operation_time;
	}
	public void setOperation_time(Timestamp operation_time) {
		this.operation_time = operation_time;
	}
	public int getOrder_no() {
		return order_no;
	}
	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}
	


}
