package com.pm.domain.business;

import java.sql.Timestamp;
import java.util.Date;

import com.pm.util.log.EntityAnnotation;

/**
 * 跟进记录
 * @author zhonglihong
 * @date 2016年5月13日 下午3:34:21
 */
@SuppressWarnings("serial")
public class PotentialClientFollowup extends IdEntity {

	private String potential_client_id ;
	
	@EntityAnnotation(item_name="联系时间", item_sort=1, length=15)
	private Timestamp link_time    ;        

	@EntityAnnotation(item_name="联系方式", item_sort=2, length=15)
	private String link_type      ;      
	

	@EntityAnnotation(item_name="内容", item_sort=3, length=15)
	private String link_content     ;    

	@EntityAnnotation(item_name="备注", item_sort=4, length=15)
	private String description      ;    
	
	private Date build_datetime      ; 
	private String build_userid      ;   
	private String build_username     ;
	
	
	////////////////////////////////////
	////////扩展

	@EntityAnnotation(item_name="潜在客户名称")
	private String potential_client_name;
	
	
	
	
	public String getPotential_client_id() {
		return potential_client_id;
	}
	public void setPotential_client_id(String potential_client_id) {
		this.potential_client_id = potential_client_id;
	}
	public Timestamp getLink_time() {
		return link_time;
	}
	public void setLink_time(Timestamp link_time) {
		this.link_time = link_time;
	}
	public String getLink_type() {
		return link_type;
	}
	public void setLink_type(String link_type) {
		this.link_type = link_type;
	}
	public String getLink_content() {
		return link_content;
	}
	public void setLink_content(String link_content) {
		this.link_content = link_content;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getBuild_datetime() {
		return build_datetime;
	}
	public void setBuild_datetime(Date build_datetime) {
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
	public String getPotential_client_name() {
		return potential_client_name;
	}
	public void setPotential_client_name(String potential_client_name) {
		this.potential_client_name = potential_client_name;
	}
	
	@Override
	public String toString() {
		return  potential_client_name ;
	} 
	
	

}
