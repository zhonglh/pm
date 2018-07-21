package com.pm.domain.business;

import java.io.Serializable;

@SuppressWarnings("serial")
public class IdEntity implements Serializable {
	

	private String id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
