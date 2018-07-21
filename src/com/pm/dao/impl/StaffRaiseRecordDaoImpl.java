package com.pm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.IStaffRaiseRecordDao;
import com.pm.domain.business.StaffRaiseRecord;

@Component
public class StaffRaiseRecordDaoImpl extends BatisDao implements IStaffRaiseRecordDao  {

	@Override
	public int addRaiseRecord(StaffRaiseRecord raiserecord) {
		String sql = "StaffRaiseRecordMapping.addRaiseRecord";
		return this.insert(sql, raiserecord);
	}

	@Override
	public int updateRaiseRecord(StaffRaiseRecord raiserecord) {
		String sql = "StaffRaiseRecordMapping.updateRaiseRecord";
		return this.update(sql, raiserecord);
	}

	@Override
	public void deleteRaiseRecord(StaffRaiseRecord raiserecord) {
		String sql = "StaffRaiseRecordMapping.deleteRaiseRecord";
		this.delete(sql, raiserecord);
	}


	@Override
	public StaffRaiseRecord getRaiseRecord(String id) {

		String sql = "StaffRaiseRecordMapping.getRaiseRecord"; 
		StaffRaiseRecord raiserecord = new StaffRaiseRecord(); 
		raiserecord.setId(id); 
		List<StaffRaiseRecord> list = this.query(sql, StaffRaiseRecord.class, raiserecord); 
		if(list == null || list.isEmpty()) return null; 
		else return list.get(0);	
	}

	@Override
	public Pager<StaffRaiseRecord> queryRaiseRecord(
		StaffRaiseRecord raiserecord,
		Pager<StaffRaiseRecord> pager){

		String sql = "StaffRaiseRecordMapping.queryRaiseRecord"; 
		Pager<StaffRaiseRecord> pager1 =  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql,StaffRaiseRecord.class, raiserecord); 
		
		return pager1;
	}


}