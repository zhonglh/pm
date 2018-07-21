package com.pm.domain.business;

import java.io.Serializable;
import java.util.Date;

import com.pm.util.log.EntityAnnotation;


/**
 * 简历
 * @author zhonglihong
 * @date 2016年5月11日 下午10:09:01
 */
@SuppressWarnings("serial")
public class RecruitInfo extends IdEntity implements Serializable{
	
	
	@EntityAnnotation(item_name="简历编号",item_sort=1, length=50 )
	public String resume_no;    
	
	public String resume_source;
	
	public Date build_datetime ;  
	public String build_username ; 
	public String build_userid   ;
	
	///////////////

	@EntityAnnotation(item_name="简历来源",item_sort=2, length=50 )
	public String resume_source_name;
	

	public String getResume_no() {
		return resume_no;
	}
	public void setResume_no(String resume_no) {
		this.resume_no = resume_no;
	}
	public String getResume_source() {
		return resume_source;
	}
	public void setResume_source(String resume_source) {
		this.resume_source = resume_source;
	}
	public Date getBuild_datetime() {
		return build_datetime;
	}
	public void setBuild_datetime(Date build_datetime) {
		this.build_datetime = build_datetime;
	}
	public String getBuild_username() {
		return build_username;
	}
	public void setBuild_username(String build_username) {
		this.build_username = build_username;
	}
	public String getBuild_userid() {
		return build_userid;
	}
	public void setBuild_userid(String build_userid) {
		this.build_userid = build_userid;
	}
	@Override
	public String toString() {
		return  resume_no;
	}
	public String getResume_source_name() {
		return resume_source_name;
	}
	public void setResume_source_name(String resume_source_name) {
		this.resume_source_name = resume_source_name;
	}

}
