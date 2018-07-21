package com.pm.util.constant;

public enum EnumSalaryModel {
	
	OriginalMode("0","原有模式"),
	MarketingModel("1","营销模式")
	;
	
	private String code;
	private String val;
	
	
	
	private EnumSalaryModel(String code, String val) {
		this.code = code;
		this.val = val;
	}
	
	
	public static EnumSalaryModel getEnumByCode(String code){
		for(EnumSalaryModel enum1 : EnumSalaryModel.values()){
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
