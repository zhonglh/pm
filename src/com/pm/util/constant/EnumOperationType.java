package com.pm.util.constant;

/**
 * 操作类型
 * @author zhonglh
 *
 */
public enum EnumOperationType {

	INSERT("operation_insert", "operation_insert","1"), 
	UPDATE("operation_update", "operation_update","2"), 
	DELETE("operation_delete", "operation_delete","3"), 
	READ("operation_read", "operation_read","4"),
	CHECK("operation_check", "operation_check","5"),
	UNCHECK("operation_uncheck", "operation_uncheck","6"),
	
	
	UPDATEPWD("operation_changepwd", "operation_changepwd","7"),
	
	ADDROLE("operation_addrole", "operation_addrole","8"),
	DELETEROLE("operation_deleterole", "operation_deleterole","9"),	

	DALLOT("operation_dallot","operation_dallot","10"),
	
	SYNCHRODATA("operation_synchrodata","operation_synchrodata","11") ,
	

	SHARE("operation_share", "operation_share","12"),
	CREATEFOLDER("operation_create_folder", "operation_folder","13");
	
	

	private String key;

	private String i18ncode;
	

	private String value;

	private EnumOperationType(String key, String i18ncode,String value) {
		this.i18ncode = i18ncode;
		this.key = key;
		this.value = value;
	}

	public final String getKey() {
		return key;
	}

	public final String getI18ncode() {
		return i18ncode;
	}

	public String getValue() {
		return value;
	}

	

}
