package com.pm.domain.system;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Permit implements Serializable {

	
	private String permit_id;
	private String permit_name;
	private String permit_name_i18n;
	
	private int group_no;
	private String group_code_i18n;
	

	//////////////////////////////////////////
	///////////////////扩展///////////////////
	//////////////////////////////////////////
	private boolean  selected = false;
	
	
	public String getPermit_id() {
		return permit_id;
	}
	public void setPermit_id(String permit_id) {
		this.permit_id = permit_id;
	}
	public String getPermit_name() {
		return permit_name;
	}
	public void setPermit_name(String permit_name) {
		this.permit_name = permit_name;
	}
	public String getPermit_name_i18n() {
		return permit_name_i18n;
	}
	public void setPermit_name_i18n(String permit_name_i18n) {
		this.permit_name_i18n = permit_name_i18n;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public int getGroup_no() {
		return group_no;
	}
	public void setGroup_no(int group_no) {
		this.group_no = group_no;
	}
	public String getGroup_code_i18n() {
		return group_code_i18n;
	}
	public void setGroup_code_i18n(String group_code_i18n) {
		this.group_code_i18n = group_code_i18n;
	}
	
}
