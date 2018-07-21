package com.pm.service;

import com.common.beans.Pager;
import com.pm.domain.business.StaffAssessment;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;

public interface IStaffAssessmentService extends IGeneralLogService{


	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_ASSESSMEN)
	public int addStaffAssessment(StaffAssessment staffAssessment) ;


	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_ASSESSMEN)
	public int updateStaffAssessment(StaffAssessment staffAssessment) ; 


	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.ENTITY_ASSESSMEN)
	public void deleteStaffAssessment(StaffAssessment[] staffAssessments) ;


	public StaffAssessment getStaffAssessment(String id) ;	

	public Pager<StaffAssessment> queryStaffAssessment(StaffAssessment staffAssessment,  Pager<StaffAssessment> pager);

}