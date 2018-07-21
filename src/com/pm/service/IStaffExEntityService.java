package com.pm.service;

import com.common.beans.Pager;
import com.pm.domain.business.StaffExEntity;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;
import com.pm.vo.UserPermit;

public interface IStaffExEntityService extends IGeneralLogService {

	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_STAFFEXENTITY)
	public int addStaffExEntity(StaffExEntity staffExEntity) ;

	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_STAFFEXENTITY)
	public int updateStaffExEntity(StaffExEntity staffExEntity) ; 
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.ENTITY_STAFFEXENTITY)
	public void deleteStaffExEntity(StaffExEntity[] staffExEntitys) ;

		

	public StaffExEntity getStaffExEntity(String id) ;	

	public Pager<StaffExEntity> queryStaffExEntity(StaffExEntity staffExEntity,  UserPermit userPermit,Pager<StaffExEntity> pager);

}