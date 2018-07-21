package com.pm.domain.business;



import java.io.Serializable;
import java.util.Date;

/**
 * 月度结算单历史信息实体类
 * 
 * @author zhonglh
 * @date 2017-9-24 21:59:01
 */
public class ThMonthlyStatement extends MonthlyStatement implements Serializable{
	private static final long serialVersionUID = 1L;

	private String id ;


	private Date his_date;




	private String his_user_id;



	private String his_user_name;




	private String his_operation_type;




	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	public Date getHis_date() {
		return his_date;
	}




	public void setHis_date(Date his_date) {
		this.his_date = his_date;
	}




	public String getHis_user_id() {
		return his_user_id;
	}




	public void setHis_user_id(String his_user_id) {
		this.his_user_id = his_user_id;
	}




	public String getHis_user_name() {
		return his_user_name;
	}




	public void setHis_user_name(String his_user_name) {
		this.his_user_name = his_user_name;
	}




	public String getHis_operation_type() {
		return his_operation_type;
	}




	public void setHis_operation_type(String his_operation_type) {
		this.his_operation_type = his_operation_type;
	}
	
	
	
}