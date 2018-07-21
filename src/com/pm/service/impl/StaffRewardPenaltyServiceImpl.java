package com.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.utils.IDKit;
import com.pm.dao.IStaffRewardPenaltyDao;
import com.pm.domain.business.StaffRewardPenalty;
import com.pm.service.IStaffRewardPenaltyService;

@Component
public class StaffRewardPenaltyServiceImpl implements  IStaffRewardPenaltyService {

	@Autowired IStaffRewardPenaltyDao staffRewardPenaltyDao;
	@Override
	public int addStaffRewardPenalty(StaffRewardPenalty staffRewardPenalty) {
		staffRewardPenalty.setId(IDKit.generateId());
		return staffRewardPenaltyDao.addStaffRewardPenalty(staffRewardPenalty);
	}

	@Override
	public int updateStaffRewardPenalty(StaffRewardPenalty staffRewardPenalty) {
		return staffRewardPenaltyDao.updateStaffRewardPenalty(staffRewardPenalty);
	}

	@Override
	public void deleteStaffRewardPenalty(StaffRewardPenalty[] staffRewardPenaltys) {
		for(StaffRewardPenalty staffRewardPenalty : staffRewardPenaltys){
			staffRewardPenaltyDao.deleteStaffRewardPenalty(staffRewardPenalty);
		}
	}


	@Override
	public StaffRewardPenalty getStaffRewardPenalty(String id) {
		return staffRewardPenaltyDao.getStaffRewardPenalty(id);
	}

	@Override
	public Pager<StaffRewardPenalty> queryStaffRewardPenalty(
		StaffRewardPenalty staffRewardPenalty,
		Pager<StaffRewardPenalty> pager){

		return staffRewardPenaltyDao.queryStaffRewardPenalty(staffRewardPenalty,  pager);
	}
	
	
	@Override
	public <T> T get(String id) {
		return (T)getStaffRewardPenalty(id);
	}


}