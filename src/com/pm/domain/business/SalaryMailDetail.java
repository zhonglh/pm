package com.pm.domain.business;

import java.io.Serializable;

import com.pm.util.log.EntityAnnotation;

@SuppressWarnings("serial")
public class SalaryMailDetail implements Serializable {

	private String detail_id;
	private String salary_id;
	private java.sql.Timestamp send_date;
	
	//发送结果, 1:成功  0:失败
	private String send_status;
	
	

	@EntityAnnotation(item_name="邮箱服务器")
	private String mail_server_host;
	
	@EntityAnnotation(item_name="邮箱服务器端口")
	private String mail_server_port;
	
	@EntityAnnotation(item_name="发件箱用户名")
	private String from_username;
	
	@EntityAnnotation(item_name="发件箱密码")
	private String from_password;

	@EntityAnnotation(item_name="收件箱地址")
	private String to_Address;
	
	
	
	
	
	
	
	
	
	
	
	//发送说明
	private String send_remark;
	
	//发送人ID
	private String send_userid;
	
	
	

	//发送人名称
	private String send_username;

	public String getDetail_id() {
		return detail_id;
	}

	public void setDetail_id(String detail_id) {
		this.detail_id = detail_id;
	}

	public String getSalary_id() {
		return salary_id;
	}

	public void setSalary_id(String salary_id) {
		this.salary_id = salary_id;
	}

	public java.sql.Timestamp getSend_date() {
		return send_date;
	}

	public void setSend_date(java.sql.Timestamp send_date) {
		this.send_date = send_date;
	}

	public String getSend_status() {
		return send_status;
	}

	public void setSend_status(String send_status) {
		this.send_status = send_status;
	}

	public String getSend_remark() {
		return send_remark;
	}

	public void setSend_remark(String send_remark) {
		this.send_remark = send_remark;
		if(this.send_remark != null) {
			if(this.send_remark.length() > 150)
				this.send_remark = this.send_remark.substring(0,150);
		}
	}

	public String getSend_userid() {
		return send_userid;
	}

	public void setSend_userid(String send_userid) {
		this.send_userid = send_userid;
	}

	public String getSend_username() {
		return send_username;
	}

	public void setSend_username(String send_username) {
		this.send_username = send_username;
	}

	public String getMail_server_host() {
		return mail_server_host;
	}

	public void setMail_server_host(String mail_server_host) {
		this.mail_server_host = mail_server_host;
	}

	public String getMail_server_port() {
		return mail_server_port;
	}

	public void setMail_server_port(String mail_server_port) {
		this.mail_server_port = mail_server_port;
	}

	public String getFrom_username() {
		return from_username;
	}

	public void setFrom_username(String from_username) {
		this.from_username = from_username;
	}

	public String getFrom_password() {
		return from_password;
	}

	public void setFrom_password(String from_password) {
		this.from_password = from_password;
	}

	public String getTo_Address() {
		return to_Address;
	}

	public void setTo_Address(String to_Address) {
		this.to_Address = to_Address;
	}

}
