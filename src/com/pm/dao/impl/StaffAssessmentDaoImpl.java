package com.pm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.IStaffAssessmentDao;
import com.pm.domain.business.StaffAssessment;

@Component
public class StaffAssessmentDaoImpl extends BatisDao implements IStaffAssessmentDao  {

	@Override
	public int addStaffAssessment(StaffAssessment staffAssessment) {
		String sql = "StaffAssessmentMapping.addStaffAssessment";
		return this.insert(sql, staffAssessment);
	}

	@Override
	public int updateStaffAssessment(StaffAssessment staffAssessment) {
		String sql = "StaffAssessmentMapping.updateStaffAssessment";
		return this.update(sql, staffAssessment);
	}

	@Override
	public void deleteStaffAssessment(StaffAssessment staffAssessment) {
		String sql = "StaffAssessmentMapping.deleteStaffAssessment";
		this.delete(sql, staffAssessment);
	}


	@Override
	public StaffAssessment getStaffAssessment(String id) {

		String sql = "StaffAssessmentMapping.getStaffAssessment"; 
		StaffAssessment staffAssessment = new StaffAssessment(); 
		staffAssessment.setId(id); 
		List<StaffAssessment> list = this.query(sql, StaffAssessment.class, staffAssessment); 
		if(list == null || list.isEmpty()) return null; 
		else return list.get(0);	
	}

	@Override
	public Pager<StaffAssessment> queryStaffAssessment(
		StaffAssessment staffAssessment,
		Pager<StaffAssessment> pager){

		String sql = "StaffAssessmentMapping.queryStaffAssessment"; 
		Pager<StaffAssessment> pager1 =  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql,StaffAssessment.class, staffAssessment); 
		
		return pager1;
	}


}