package com.pm.service;

import java.util.List;

import com.common.beans.Pager;
import com.pm.domain.business.Insurance;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;
import com.pm.vo.UserPermit;

public interface IInsuranceService extends IGeneralLogService , IBaseService {

	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_INSURANCE)
	public int addInsurance(Insurance insurance) ;

	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_INSURANCE)
	public int updateInsurance(Insurance insurance) ; 

	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.ENTITY_INSURANCE)
	public void deleteInsurance(Insurance[] insurances) ;

	@LogAnnotation(operation_type=LogConstant.OPERATION_CHECK,entity_type=LogConstant.ENTITY_INSURANCE)
	public void verifyInsurance(Insurance insurance) ;	

	public Insurance getInsurance(String id) ;


	public boolean isExistNotCheckByWorkAttendance(Insurance insurance);

	public List<Insurance> queryInsuranceByWorkAttendance(Insurance insurance);

	public Pager<Insurance> queryInsurance(Insurance insurance,  UserPermit userPermit,Pager<Insurance> pager);

}