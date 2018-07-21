package com.pm.util.constant;

public enum EnumApplyApproveType {

	APPLY("1","operation_type.1") ,
	APPROVE("2","operation_type.2") ,
	BUILD("3","operation_type.3") ,
	CHECK("4","operation_type.4") ;
	
	private String key;
	private String i18n;
	
	private EnumApplyApproveType(String key,String i18n){
		this.key = key;
		this.i18n = i18n;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getI18n() {
		return i18n;
	}

	public void setI18n(String i18n) {
		this.i18n = i18n;
	}
}
