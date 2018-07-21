package com.pm.dao;

import com.pm.domain.business.StaffExEntity;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;

public interface IStaffExEntityDao {

	public int addStaffExEntity(StaffExEntity staffExEntity) ;

	public int updateStaffExEntity(StaffExEntity staffExEntity) ; 

	public void deleteStaffExEntity(StaffExEntity staffExEntity) ;

	public StaffExEntity getStaffExEntity(String id) ;	

	public Pager<StaffExEntity> queryStaffExEntity(StaffExEntity staffExEntity,  UserPermit userPermit,Pager<StaffExEntity> pager);

}