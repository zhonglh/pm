package com.pm.domain.system;

import java.io.Serializable;

@SuppressWarnings("serial")
public class RolePermit implements Serializable {

	private String role_id;
	private String permit_id;
	
	
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	public String getPermit_id() {
		return permit_id;
	}
	public void setPermit_id(String permit_id) {
		this.permit_id = permit_id;
	}

}
