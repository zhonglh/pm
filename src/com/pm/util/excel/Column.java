package com.pm.util.excel;

import java.lang.reflect.Field;

public class Column {
	

	//对应列序号
	private int number;

	//对应列长度
	private int length;
	
	//对应列代码
	private String code;

	//对应列名称
	private String name;

	//格式化
	private String[] formats;
	

	private Field field;
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Field getField() {
		return field;
	}
	public void setField(Field field) {
		this.field = field;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}

	public String[] getFormats() {
		return formats;
	}

	public void setFormats(String[] formats) {
		this.formats = formats;
	}
}
