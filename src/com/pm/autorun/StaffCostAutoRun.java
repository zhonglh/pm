package com.pm.autorun;

import org.springframework.beans.factory.annotation.Autowired;

import com.pm.service.IStaffCostService;

public class StaffCostAutoRun {
	
	

	@Autowired
	private IStaffCostService staffCostService;

	//自动增加工作年限
	public void autoAddWorkingLife(){
		staffCostService.updateWorkinfLife();
	}

}
