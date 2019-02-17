package com.pm.service;

import com.pm.domain.business.PayContract;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;
import com.pm.service.IGeneralLogService;

/**
 * 付款合同
 * @author Administrator
 */
public interface IPayContractService extends IGeneralLogService , IBaseService {

	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_PAYCONTRACT)
	public int addPayContract(PayContract payContract) ;

	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_PAYCONTRACT)
	public int updatePayContract(PayContract payContract) ; 

	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.ENTITY_PAYCONTRACT)
	public void deletePayContract(PayContract[] payContracts) ;

	@LogAnnotation(operation_type=LogConstant.OPERATION_CHECK,entity_type=LogConstant.ENTITY_PAYCONTRACT)
	public void verifyPayContract(PayContract payContract) ;	

	public PayContract getPayContract(String id) ;	

	public Pager<PayContract> queryPayContract(PayContract payContract, UserPermit userPermit, Pager<PayContract> pager);

}