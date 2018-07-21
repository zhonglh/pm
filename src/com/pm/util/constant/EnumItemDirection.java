package com.pm.util.constant;

public enum EnumItemDirection {
	
	Pay("0","支付"),
	Income("1","收入");
	
	private String code;
	private String val;
	
	
	
	private EnumItemDirection(String code, String val) {
		this.code = code;
		this.val = val;
	}
	
	
	public static EnumItemDirection getEnumByCode(String code){
		for(EnumItemDirection enum1 : EnumItemDirection.values()){
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
