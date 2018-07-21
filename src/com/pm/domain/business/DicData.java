package com.pm.domain.business;

import java.sql.Timestamp;

import com.pm.util.log.EntityAnnotation;

/**
 * 字典数据
 * @author zhonglihong
 * @date 2016年5月11日 下午11:43:14
 */
@SuppressWarnings("serial")
public class DicData extends IdEntity{
	
	private String dic_type_id   ; 
	
	@EntityAnnotation(item_name="字典数据")
	private String dic_data_name ;
	
	private int dic_sort;

	private Timestamp build_datetime;
	private String build_userid;
	private String build_username;
	private String delete_flag;
	private Timestamp delete_datetime;
	private String delete_userid;
	private String delete_username;
	
	//////////扩展/////////

	@EntityAnnotation(item_name="字典类型")
	private String dic_type_name   ; 
	
	
	public String getDic_type_id() {
		return dic_type_id;
	}
	public void setDic_type_id(String dic_type_id) {
		this.dic_type_id = dic_type_id;
	}
	public String getDic_data_name() {
		return dic_data_name;
	}
	public void setDic_data_name(String dic_data_name) {
		this.dic_data_name = dic_data_name;
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
	public String getDelete_flag() {
		return delete_flag;
	}
	public void setDelete_flag(String delete_flag) {
		this.delete_flag = delete_flag;
	}
	public Timestamp getDelete_datetime() {
		return delete_datetime;
	}
	public void setDelete_datetime(Timestamp delete_datetime) {
		this.delete_datetime = delete_datetime;
	}
	public String getDelete_userid() {
		return delete_userid;
	}
	public void setDelete_userid(String delete_userid) {
		this.delete_userid = delete_userid;
	}
	public String getDelete_username() {
		return delete_username;
	}
	public void setDelete_username(String delete_username) {
		this.delete_username = delete_username;
	}
	public String getDic_type_name() {
		return dic_type_name;
	}
	public void setDic_type_name(String dic_type_name) {
		this.dic_type_name = dic_type_name;
	}
	
	@Override
	public String toString() {
		return dic_data_name ;
	}
	public int getDic_sort() {
		return dic_sort;
	}
	public void setDic_sort(int dic_sort) {
		this.dic_sort = dic_sort;
	}
	
	

}
