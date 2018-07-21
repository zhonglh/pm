package com.pm.vo;

import java.io.Serializable;

import com.pm.domain.system.Permit;

@SuppressWarnings("serial")
public class UserPermit extends Permit implements Serializable {	
	private String range ;
	private String user_id;
	private String user_deptid;
	private String staff_id;

	

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_deptid() {
		return user_deptid;
	}

	public void setUser_deptid(String user_deptid) {
		this.user_deptid = user_deptid;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public String getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}


}
