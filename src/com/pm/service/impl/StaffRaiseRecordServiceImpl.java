package com.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.utils.IDKit;
import com.pm.dao.IStaffRaiseRecordDao;
import com.pm.domain.business.StaffRaiseRecord;
import com.pm.service.IStaffRaiseRecordService;

@Component
public class StaffRaiseRecordServiceImpl implements  IStaffRaiseRecordService {

	@Autowired IStaffRaiseRecordDao raiserecordDao;
	@Override
	public int addRaiseRecord(StaffRaiseRecord raiserecord) {
		raiserecord.setId(IDKit.generateId());
		return raiserecordDao.addRaiseRecord(raiserecord);
	}

	@Override
	public int updateRaiseRecord(StaffRaiseRecord raiserecord) {
		return raiserecordDao.updateRaiseRecord(raiserecord);
	}

	@Override
	public void deleteRaiseRecord(StaffRaiseRecord[] raiserecords) {
		for(StaffRaiseRecord raiserecord : raiserecords){
			raiserecordDao.deleteRaiseRecord(raiserecord);
		}
	}

	@Override
	public StaffRaiseRecord getRaiseRecord(String id) {
		return raiserecordDao.getRaiseRecord(id);
	}

	@Override
	public Pager<StaffRaiseRecord> queryRaiseRecord(
		StaffRaiseRecord raiserecord,
		Pager<StaffRaiseRecord> pager){

		return raiserecordDao.queryRaiseRecord(raiserecord,  pager);
	}
	
	@Override
	public <T> T get(String id) {
		return (T)getRaiseRecord(id);
	}


}