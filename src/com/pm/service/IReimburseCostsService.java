package com.pm.service;

import com.common.beans.Pager;
import com.pm.domain.business.ReimburseCosts;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;
import com.pm.vo.UserPermit;

public interface IReimburseCostsService extends IBaseService {
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_REIMBURSE_COSTS)
	public int addReimburseCosts(ReimburseCosts reimburseCosts) ;
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_REIMBURSE_COSTS)
	public int updateReimburseCosts(ReimburseCosts reimburseCosts) ; 
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.ENTITY_REIMBURSE_COSTS)
	public void deleteReimburseCosts(ReimburseCosts[] reimburseCostss) ;

	@LogAnnotation(operation_type=LogConstant.OPERATION_CHECK,entity_type=LogConstant.ENTITY_REIMBURSE_COSTS)
	public void verifyReimburseCosts(ReimburseCosts reimburseCosts);
	
	
	public ReimburseCosts getReimburseCosts(String reimburse_id) ;	
	

	public Pager<ReimburseCosts> queryReimburseCosts(ReimburseCosts reimburseCosts,  UserPermit userPermit,Pager<ReimburseCosts> pager);

}
