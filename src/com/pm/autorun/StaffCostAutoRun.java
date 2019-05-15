package com.pm.autorun;

import com.pm.service.IOtherStaffService;
import org.springframework.beans.factory.annotation.Autowired;

import com.pm.service.IStaffCostService;

public class StaffCostAutoRun {
	
	

	@Autowired
	private IStaffCostService staffCostService;


	@Autowired
	private IOtherStaffService otherStaffervice;

	//自动增加工作年限
	public void autoCompute(){
		try {
			staffCostService.autoUpdateWorkinfLife();
		}catch (Exception e){
			e.printStackTrace();
		}

		try {
			staffCostService.autoUpdateSalary();
		}catch (Exception e){
			e.printStackTrace();
		}


		try {
			otherStaffervice.autoUpdateWorkinfLife();
		}catch (Exception e){
			e.printStackTrace();
		}


		try {
			otherStaffervice.autoUpdateSalary();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
