package com.pm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.IStaffExEntityDao;
import com.pm.domain.business.StaffExEntity;
import com.pm.vo.UserPermit;

@Component
public class StaffExEntityDaoImpl extends BatisDao implements IStaffExEntityDao  {

	@Override
	public int addStaffExEntity(StaffExEntity staffExEntity) {
		String sql = "StaffExEntityMapping.addStaffExEntity";
		return this.insert(sql, staffExEntity);
	}

	@Override
	public int updateStaffExEntity(StaffExEntity staffExEntity) {
		String sql = "StaffExEntityMapping.updateStaffExEntity";
		return this.update(sql, staffExEntity);
	}

	@Override
	public void deleteStaffExEntity(StaffExEntity staffExEntity) {
		String sql = "StaffExEntityMapping.deleteStaffExEntity";
		this.delete(sql, staffExEntity);
	}


	@Override
	public StaffExEntity getStaffExEntity(String id) {

		String sql = "StaffExEntityMapping.getStaffExEntity"; 
		StaffExEntity staffExEntity = new StaffExEntity(); 
		staffExEntity.setId(id); 
		List<StaffExEntity> list = this.query(sql, StaffExEntity.class, staffExEntity); 
		if(list == null || list.isEmpty()) return null; 
		else return list.get(0);	
	}

	@Override
	public Pager<StaffExEntity> queryStaffExEntity(
		StaffExEntity staffExEntity,
		UserPermit userPermit,
		Pager<StaffExEntity> pager){

		String sql = "StaffExEntityMapping.queryStaffExEntity"; 
		Pager<StaffExEntity> pager1 =  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql,StaffExEntity.class, staffExEntity,userPermit); 
		return pager1;
	}


}