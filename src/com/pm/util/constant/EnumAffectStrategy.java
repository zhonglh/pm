package com.pm.util.constant;

public enum EnumAffectStrategy {
	
	fixed("1","固定"),
	decline("2","递减")
	;
	
	private String code;
	private String val;
	
	
	
	private EnumAffectStrategy(String code, String val) {
		this.code = code;
		this.val = val;
	}
	
	
	public static EnumAffectStrategy getEnumByCode(String code){
		for(EnumAffectStrategy enum1 : EnumAffectStrategy.values()){
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
