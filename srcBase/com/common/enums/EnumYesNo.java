package com.common.enums;

public enum EnumYesNo {
	Yes("1"),
	No("0")
	;
	
	private String code;

	private EnumYesNo(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	

	
	
}
