package com.pm.service;

import com.common.beans.Pager;
import com.pm.domain.business.StaffPerformance;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;
import com.pm.vo.UserPermit;

import java.util.List;

/**
 * @author Administrator
 */
public interface IStaffPerformanceService extends  IBaseService,IGeneralLogService {


	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_STAFF_PERFORMANCE)
	public int addStaffPerformance(StaffPerformance staffPerformance) ;


	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_STAFF_PERFORMANCE)
	public int updateStaffPerformance(StaffPerformance staffPerformance) ;


	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.ENTITY_STAFF_PERFORMANCE)
	public void deleteStaffPerformance(StaffPerformance[] staffPerformances) ;

	@LogAnnotation(operation_type=LogConstant.OPERATION_CHECK,entity_type=LogConstant.ENTITY_STAFF_PERFORMANCE)
	public void verifyStaffPerformance(StaffPerformance staffPerformance) ;	

	public StaffPerformance getStaffPerformance(String id) ;	
	



	public List<StaffPerformance> getStaffPerformanceList(StaffPerformance staffPerformance);

	public Pager<StaffPerformance> queryStaffPerformance(StaffPerformance staffPerformance, UserPermit userPermit, Pager<StaffPerformance> pager);

}