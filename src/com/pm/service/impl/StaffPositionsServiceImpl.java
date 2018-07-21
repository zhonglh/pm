package com.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.utils.IDKit;
import com.pm.dao.IStaffPositionsDao;
import com.pm.domain.business.StaffPositions;
import com.pm.service.IStaffPositionsService;
import com.pm.vo.UserPermit;


@Component
public class StaffPositionsServiceImpl implements  IStaffPositionsService {

	@Autowired IStaffPositionsDao staffPositionsDao;
	@Override
	public int addStaffPositions(StaffPositions staffPositions) {
		staffPositions.setId(IDKit.generateId());
		return staffPositionsDao.addStaffPositions(staffPositions);
	}

	@Override
	public int updateStaffPositions(StaffPositions staffPositions) {
		return staffPositionsDao.updateStaffPositions(staffPositions);
	}

	@Override
	public void deleteStaffPositions(StaffPositions[] staffPositionss) {
		for(StaffPositions staffPositions : staffPositionss){
			staffPositionsDao.deleteStaffPositions(staffPositions);
		}
	}


	@Override
	public StaffPositions getStaffPositions(String id) {
		return staffPositionsDao.getStaffPositions(id);
	}

	@Override
	public Pager<StaffPositions> queryStaffPositions(
		StaffPositions staffPositions,		
		Pager<StaffPositions> pager){

		return staffPositionsDao.queryStaffPositions(staffPositions,  pager);
	}

	@Override
	public <T> T get(String id) {
		return (T)getStaffPositions(id);
	}


}