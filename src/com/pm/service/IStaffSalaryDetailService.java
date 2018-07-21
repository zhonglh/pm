package com.pm.service;

import java.util.List;

import com.pm.domain.business.StaffSalaryDetail;

public interface IStaffSalaryDetailService {

	public void doProcessStaffSalaryDetailByStaff(String staff_id) ;
	

	public void doProcessStaffSalaryDetailByItem(String item_id) ;
	
	
	public List<StaffSalaryDetail> getStaffSalaryDetail(StaffSalaryDetail staffSalaryDetail) ;
	
	
	public List<StaffSalaryDetail> queryVirtualStaffSalaryDetail(String item_id , String computational_formula);
	
	
}