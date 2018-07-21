package com.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.pm.dao.IStaffExEntityDao;
import com.pm.domain.business.StaffExEntity;
import com.pm.service.IStaffExEntityService;
import com.pm.vo.UserPermit;

@Component
public class StaffExEntityServiceImpl implements  IStaffExEntityService {

	@Autowired IStaffExEntityDao staffExEntityDao;
	
	@Override
	public int addStaffExEntity(StaffExEntity staffExEntity) {
		return staffExEntityDao.addStaffExEntity(staffExEntity);
	}

	@Override
	public int updateStaffExEntity(StaffExEntity staffExEntity) {
		return staffExEntityDao.updateStaffExEntity(staffExEntity);
	}
	

	@Override
	public void deleteStaffExEntity(StaffExEntity[] staffExEntitys) {
		for(StaffExEntity staffExEntity : staffExEntitys){
			staffExEntityDao.deleteStaffExEntity(staffExEntity);
		}
	}
	
	@Override
	public <T> T get(String id) {		
		return (T)getStaffExEntity(id);
	}

	@Override
	public StaffExEntity getStaffExEntity(String id) {
		return staffExEntityDao.getStaffExEntity(id);
	}

	@Override
	public Pager<StaffExEntity> queryStaffExEntity(
		StaffExEntity staffExEntity,
		UserPermit userPermit,
		Pager<StaffExEntity> pager){

		return staffExEntityDao.queryStaffExEntity(staffExEntity, userPermit, pager);
	}




}