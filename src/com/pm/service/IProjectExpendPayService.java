package com.pm.service;

import com.pm.domain.business.ProjectExpendPay;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;
import com.pm.service.IGeneralLogService;

public interface IProjectExpendPayService extends IGeneralLogService , IBaseService {

	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_PROJECTEXPENDPAY)
	public int addProjectExpendPay(ProjectExpendPay projectExpendpay) ;

	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_PROJECTEXPENDPAY)
	public int updateProjectExpendPay(ProjectExpendPay projectExpendpay) ; 

	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.ENTITY_PROJECTEXPENDPAY)
	public void deleteProjectExpendPay(ProjectExpendPay[] projectExpendpays) ;

	@LogAnnotation(operation_type=LogConstant.OPERATION_CHECK,entity_type=LogConstant.ENTITY_PROJECTEXPENDPAY)
	public void verifyProjectExpendPay(ProjectExpendPay projectExpendpay) ;	

	public ProjectExpendPay getProjectExpendPay(String id) ;


	public ProjectExpendPay getSumByProjectExpend(ProjectExpendPay projectExpendpay) ;

	public Pager<ProjectExpendPay> queryProjectExpendPay(ProjectExpendPay projectExpendpay, UserPermit userPermit, Pager<ProjectExpendPay> pager);

}