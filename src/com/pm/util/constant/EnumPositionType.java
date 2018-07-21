package com.pm.util.constant;

/**
 * 职位类型
 * @author zhonglh
 *
 */
public enum EnumPositionType {
	
	RECRUIT("1","position.type.1"),
	SALES("2","position.type.2"),
	PRESALES("3","position.type.3"),
	BACKSALES("4","position.type.4"),
	PROJECTMANAGE("5","position.type.5"),
	INFOSOURCE("6","position.type.6")	,
	CLERICALSTAFF("7","position.type.7")	,
	FINANCE("8","position.type.8")	,
	LEAD("9","position.type.9")	,
	OTHER("10","position.type.10")	
	
	;

	private String id;

	private String i18ncode;
	

	private EnumPositionType(String id, String i18ncode) {
		this.i18ncode = i18ncode;
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getI18ncode() {
		return i18ncode;
	}

	public void setI18ncode(String i18ncode) {
		this.i18ncode = i18ncode;
	}
}
