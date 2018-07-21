package com.pm.util.constant;

public enum EnumDataType {
	
	
	monthly_statement("1"),
	invoice("2"),
	received_payment("3"),
	
	
	;

	
	private EnumDataType(String id) {
		this.id = id;
	}

	String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
