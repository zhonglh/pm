package com.pm.domain.business;

import java.io.Serializable;

import com.pm.util.log.EntityAnnotation;

@SuppressWarnings("serial")
public class ParamExtend implements Serializable {
	
	private String param_id;
	

	@EntityAnnotation(item_name="大类")
	private String group1;

	@EntityAnnotation(item_name="小类")
	private String group2;

	@EntityAnnotation(item_name="类型")
	private String type1;
	

	@EntityAnnotation(item_name="实际值")
	private String realVal;

	@EntityAnnotation(item_name="计算")
	private String processor;
	
	//真正的总表达式
	private String expression;
	
	
	public String getParam_id() {
		return param_id;
	}
	public void setParam_id(String param_id) {
		this.param_id = param_id;
	}
	public String getGroup1() {
		return group1;
	}
	public void setGroup1(String group1) {
		this.group1 = group1;
	}
	public String getGroup2() {
		return group2;
	}
	public void setGroup2(String group2) {
		this.group2 = group2;
	}
	public String getType1() {
		return type1;
	}
	public void setType1(String type1) {
		this.type1 = type1;
	}
	public String getRealVal() {
		return realVal;
	}
	public void setRealVal(String realVal) {
		this.realVal = realVal;
	}
	public String getProcessor() {
		return processor;
	}
	public void setProcessor(String processor) {
		this.processor = processor;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	
}
