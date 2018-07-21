package com.pm.domain.business;

import java.io.Serializable;

import com.pm.util.log.EntityAnnotation;

@SuppressWarnings("serial")
public class Params implements Serializable {
	
	String param_id;
	
	String param_key;
	String param_name;
	

	@EntityAnnotation(item_name="值")
	String param_value;


	public String getParam_id() {
		return param_id;
	}


	public void setParam_id(String param_id) {
		this.param_id = param_id;
	}


	public String getParam_key() {
		return param_key;
	}


	public void setParam_key(String param_key) {
		this.param_key = param_key;
	}


	public String getParam_name() {
		return param_name;
	}


	public void setParam_name(String param_name) {
		this.param_name = param_name;
	}


	public String getParam_value() {
		return param_value;
	}


	public void setParam_value(String param_value) {
		this.param_value = param_value;
	}


	@Override
	public String toString() {
		return String
				.format("Params [param_id=%s, param_key=%s, param_name=%s, param_value=%s]",
						param_id, param_key, param_name,
						param_value);
	}
	
}
