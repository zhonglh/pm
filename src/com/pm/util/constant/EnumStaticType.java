package com.pm.util.constant;

public enum EnumStaticType {
	
	
	S10("10",""),
	S20("20",""),
	S30("30",""),
	S40("40",""),
	S41("41",""),
	S50("50",""),
	S51("51",""),
	S100("100",""),
	S101("101","")
	;
	
	private String code;
	private String name;

	private EnumStaticType(String code, String name) {
		this.code = code;
		this.name = name;
	}

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
	
	
	

}
