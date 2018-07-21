package com.pm.domain.business;

import java.sql.Timestamp;
import java.util.Date;

@SuppressWarnings("serial")
public class ShareInfo extends IdEntity implements java.io.Serializable{


	private String file_id;
	private String share_user_id;
	private String is_share_all;
	private Date begin_time;
	private Date end_time;
	private Timestamp build_datetime;
	private String build_userid;
	private String build_username;
	
	public String getFile_id() {
		return file_id;
	}
	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}
	public String getShare_user_id() {
		return share_user_id;
	}
	public void setShare_user_id(String share_user_id) {
		this.share_user_id = share_user_id;
	}
	public String getIs_share_all() {
		return is_share_all;
	}
	public void setIs_share_all(String is_share_all) {
		this.is_share_all = is_share_all;
	}
	public Date getBegin_time() {
		return begin_time;
	}
	public void setBegin_time(Date begin_time) {
		this.begin_time = begin_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
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
	public String getBuild_username() {
		return build_username;
	}
	public void setBuild_username(String build_username) {
		this.build_username = build_username;
	}
	
	
	
	
}
