package com.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pm.dao.IStaffChildsDao;
import com.pm.domain.business.StaffChilds;
import com.pm.service.IStaffChildsService;

@Component
public class StaffChildsServiceImpl implements  IStaffChildsService {

	@Autowired IStaffChildsDao staffChildsDao;
	
	@Override
	public void doProcessStaffChilds(String staff_id) {
		staffChildsDao.processStaffChilds(staff_id);
	}
	
	@Override
	public void deleteStaffChilds(StaffChilds[] staffChildss) {
		for(StaffChilds staffChilds : staffChildss){
			staffChildsDao.deleteStaffChilds(staffChilds);
		}
	}
	


}
