package com.pm.util.constant;

public enum EnumEnable {
	
	Normal("0","停用"),
	Enable("1","启用");
	
	private String code;
	private String val;
	
	
	
	private EnumEnable(String code, String val) {
		this.code = code;
		this.val = val;
	}
	
	
	public static EnumEnable getEnumByCode(String code){
		for(EnumEnable enum1 : EnumEnable.values()){
			if(enum1.getCode().equals(code)){
				return enum1;
			}
		}
		return null;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
	
	

}
