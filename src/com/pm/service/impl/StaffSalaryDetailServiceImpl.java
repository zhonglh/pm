package com.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pm.dao.IStaffSalaryDetailDao;
import com.pm.domain.business.StaffSalaryDetail;
import com.pm.service.IStaffSalaryDetailService;

@Component
public class StaffSalaryDetailServiceImpl implements  IStaffSalaryDetailService {

	@Autowired IStaffSalaryDetailDao staffsalaryDetailDao;
	

	public void doProcessStaffSalaryDetailByStaff(String staff_id) {
		staffsalaryDetailDao.doProcessStaffSalaryDetailByStaff(staff_id);
	}
	

	public void doProcessStaffSalaryDetailByItem(String item_id) {
		staffsalaryDetailDao.doProcessStaffSalaryDetailByItem(item_id);
	}
	

	public List<StaffSalaryDetail> getStaffSalaryDetail(StaffSalaryDetail staffSalaryDetail) {
		return staffsalaryDetailDao.getStaffSalaryDetail(staffSalaryDetail);
	}
	

	public List<StaffSalaryDetail> queryVirtualStaffSalaryDetail(String item_id , String computational_formula){
		return staffsalaryDetailDao.queryVirtualStaffSalaryDetail(item_id,computational_formula);
	}

}