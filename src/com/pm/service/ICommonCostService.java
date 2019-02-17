package com.pm.service;

import com.pm.domain.business.CommonCost;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;
import com.pm.service.IGeneralLogService;

/**
 * 公共费用
 * @author Administrator
 */
public interface ICommonCostService extends IGeneralLogService ,  IBaseService {

	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_COMMONCOST)
	public int addCommonCost(CommonCost commoncost) ;

	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_COMMONCOST)
	public int updateCommonCost(CommonCost commoncost) ; 

	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.ENTITY_COMMONCOST)
	public void deleteCommonCost(CommonCost[] commoncosts) ;

	@LogAnnotation(operation_type=LogConstant.OPERATION_CHECK,entity_type=LogConstant.ENTITY_COMMONCOST)
	public void verifyCommonCost(CommonCost commoncost) ;	

	public CommonCost getCommonCost(String id) ;	

	public Pager<CommonCost> queryCommonCost(CommonCost commoncost, UserPermit userPermit, Pager<CommonCost> pager);

}