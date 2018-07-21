package com.pm.service;

import com.common.beans.Pager;
import com.pm.domain.business.StaffRaiseRecord;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;

public interface IStaffRaiseRecordService extends IGeneralLogService{


	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_STAFFPOSITIONS)
	public int addRaiseRecord(StaffRaiseRecord raiserecord) ;


	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_STAFFPOSITIONS)
	public int updateRaiseRecord(StaffRaiseRecord raiserecord) ; 


	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.ENTITY_STAFFPOSITIONS)
	public void deleteRaiseRecord(StaffRaiseRecord[] raiserecords) ;
	

	public StaffRaiseRecord getRaiseRecord(String id) ;	

	public Pager<StaffRaiseRecord> queryRaiseRecord(StaffRaiseRecord raiserecord,  Pager<StaffRaiseRecord> pager);

}