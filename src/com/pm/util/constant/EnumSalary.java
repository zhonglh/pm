package com.pm.util.constant;

public enum EnumSalary {
	
	sick_leave_salary("病假工资","sick_leave_salary","sick_leave_salary"),
	waiting_post_salary("待岗工资","waiting_post_salary","waiting_post_salary"),
	maternity_leave_salary("产假工资","maternity_leave_salary","maternity_leave_salary"),
	;
	
	private String title;
	private String id;
	private String i18n;
	
	
	
	private EnumSalary(String title, String id, String i18n) {
		this.title = title;
		this.id = id;
		this.i18n = i18n;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getI18n() {
		return i18n;
	}
	public void setI18n(String i18n) {
		this.i18n = i18n;
	}
	
	
}
