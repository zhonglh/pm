package com.pm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.IStaffRewardPenaltyDao;
import com.pm.domain.business.StaffRewardPenalty;

@Component
public class StaffRewardPenaltyDaoImpl extends BatisDao implements IStaffRewardPenaltyDao  {

	@Override
	public int addStaffRewardPenalty(StaffRewardPenalty staffRewardPenalty) {
		String sql = "StaffRewardPenaltyMapping.addStaffRewardPenalty";
		return this.insert(sql, staffRewardPenalty);
	}

	@Override
	public int updateStaffRewardPenalty(StaffRewardPenalty staffRewardPenalty) {
		String sql = "StaffRewardPenaltyMapping.updateStaffRewardPenalty";
		return this.update(sql, staffRewardPenalty);
	}

	@Override
	public void deleteStaffRewardPenalty(StaffRewardPenalty staffRewardPenalty) {
		String sql = "StaffRewardPenaltyMapping.deleteStaffRewardPenalty";
		this.delete(sql, staffRewardPenalty);
	}


	@Override
	public StaffRewardPenalty getStaffRewardPenalty(String id) {

		String sql = "StaffRewardPenaltyMapping.getStaffRewardPenalty"; 
		StaffRewardPenalty staffRewardPenalty = new StaffRewardPenalty(); 
		staffRewardPenalty.setId(id); 
		List<StaffRewardPenalty> list = this.query(sql, StaffRewardPenalty.class, staffRewardPenalty); 
		if(list == null || list.isEmpty()) return null; 
		else return list.get(0);	
	}

	@Override
	public Pager<StaffRewardPenalty> queryStaffRewardPenalty(
		StaffRewardPenalty staffRewardPenalty,
		Pager<StaffRewardPenalty> pager){

		String sql = "StaffRewardPenaltyMapping.queryStaffRewardPenalty"; 
		Pager<StaffRewardPenalty> pager1 =  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql,StaffRewardPenalty.class, staffRewardPenalty); 
		
		return pager1;
	}


}