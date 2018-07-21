package com.pm.domain.business;

import java.util.List;

public class EasyUIDatagrid {

	public EasyUIDatagrid(int total, List rows) {
		super();
		this.total = total;
		this.rows = rows;
	}
	
	int total;
	List rows;
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	

}
