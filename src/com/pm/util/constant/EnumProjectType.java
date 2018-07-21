package com.pm.util.constant;

public enum EnumProjectType {
	
	PROJECT("1","project.type.1"),
	STAFF("2","project.type.2"),
	OTHER("3","project.type.3");
	
	private String key;
	private String i18n;
	
	private EnumProjectType(String key,String i18n){
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
