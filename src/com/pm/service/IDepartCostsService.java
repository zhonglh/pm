package com.pm.service;

import com.common.beans.Pager;
import com.pm.domain.business.DepartCosts;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;
import com.pm.vo.UserPermit;

/**
 * 部门费用
 * @author zhonglihong
 * @date 2016年6月2日 下午8:50:23
 */
public interface IDepartCostsService extends IBaseService,IGeneralLogService {
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_DEPART_COSTS)
	public int addDepartCosts(DepartCosts departCosts) ;
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_DEPART_COSTS)
	public int updateDepartCosts(DepartCosts departCosts) ; 
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.ENTITY_DEPART_COSTS)
	public void deleteDepartCosts(DepartCosts[] departCostss) ;

	@LogAnnotation(operation_type=LogConstant.OPERATION_CHECK,entity_type=LogConstant.ENTITY_DEPART_COSTS)
	public void verifyDepartCosts(DepartCosts departCosts);
	
	
	public DepartCosts getDepartCosts(String reimburse_id) ;	
	

	public Pager<DepartCosts> queryDepartCosts(DepartCosts departCosts,  UserPermit userPermit,Pager<DepartCosts> pager);

}
