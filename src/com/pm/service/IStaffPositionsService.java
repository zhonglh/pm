package com.pm.service;

import com.pm.domain.business.StaffPositions;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;
import com.pm.service.IBaseService;

public interface IStaffPositionsService extends IGeneralLogService {

	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_STAFFPOSITIONS)
	public int addStaffPositions(StaffPositions staffPositions) ;

	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_STAFFPOSITIONS)
	public int updateStaffPositions(StaffPositions staffPositions) ; 

	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.ENTITY_STAFFPOSITIONS)
	public void deleteStaffPositions(StaffPositions[] staffPositionss) ;


	public StaffPositions getStaffPositions(String id) ;	

	public Pager<StaffPositions> queryStaffPositions(StaffPositions staffPositions,  Pager<StaffPositions> pager);

}