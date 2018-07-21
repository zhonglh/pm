package com.pm.domain.business;

import java.io.Serializable;
import java.sql.Timestamp;

import com.pm.util.log.EntityAnnotation;

@SuppressWarnings("serial")
public class MailSender implements Serializable {
	
	private String sender_id;
	

	@EntityAnnotation(item_name="邮箱服务器")
	private String mail_server_host;
	
	@EntityAnnotation(item_name="邮箱服务器端口")
	private String mail_server_port;
	
	@EntityAnnotation(item_name="发件箱用户名")
	private String from_username;
	
	@EntityAnnotation(item_name="发件箱密码")
	private String from_password;
	
	@EntityAnnotation(item_name="状态")
	private String status;
	private String build_username;
	private Timestamp build_datetime;
	private String build_userid;
	
	public String getSender_id() {
		return sender_id;
	}
	public void setSender_id(String sender_id) {
		this.sender_id = sender_id;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBuild_username() {
		return build_username;
	}
	public void setBuild_username(String build_username) {
		this.build_username = build_username;
	}
	public Timestamp getBuild_datetime() {
		return build_datetime;
	}
	public void setBuild_datetime(Timestamp build_datetime) {
		this.build_datetime = build_datetime;
	}
	public String getBuild_userid() {
		return build_userid;
	}
	public void setBuild_userid(String build_userid) {
		this.build_userid = build_userid;
	}
	
	
	

}
