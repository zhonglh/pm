package com.pm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.pm.dao.IStaffPositionsDao;
import com.pm.domain.business.StaffPositions;
import com.pm.vo.UserPermit;

import com.common.daos.BatisDao;
import com.common.beans.Pager;

@Component
public class StaffPositionsDaoImpl extends BatisDao implements IStaffPositionsDao  {

	@Override
	public int addStaffPositions(StaffPositions staffPositions) {
		String sql = "StaffPositionsMapping.addStaffPositions";
		return this.insert(sql, staffPositions);
	}

	@Override
	public int updateStaffPositions(StaffPositions staffPositions) {
		String sql = "StaffPositionsMapping.updateStaffPositions";
		return this.update(sql, staffPositions);
	}

	@Override
	public void deleteStaffPositions(StaffPositions staffPositions) {
		String sql = "StaffPositionsMapping.deleteStaffPositions";
		this.delete(sql, staffPositions);
	}


	@Override
	public StaffPositions getStaffPositions(String id) {

		String sql = "StaffPositionsMapping.getStaffPositions"; 
		StaffPositions staffPositions = new StaffPositions(); 
		staffPositions.setId(id); 
		List<StaffPositions> list = this.query(sql, StaffPositions.class, staffPositions); 
		if(list == null || list.isEmpty()) return null; 
		else return list.get(0);	
	}

	@Override
	public Pager<StaffPositions> queryStaffPositions(
		StaffPositions staffPositions,
		Pager<StaffPositions> pager){

		String sql = "StaffPositionsMapping.queryStaffPositions"; 
		Pager<StaffPositions> pager1 =  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql,StaffPositions.class, staffPositions); 
		
		return pager1;
	}


}