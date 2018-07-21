package com.pm.service;

import com.common.beans.Pager;
import com.pm.domain.business.ReceivedPayment;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;
import com.pm.vo.UserPermit;

/**
 * 到账记录
 * @author zhonglh
 *
 */
public interface IReceivedPaymentService extends IBaseService{
	


	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_RECEIVED_PAYMENT)
	public int addReceivedPayment(ReceivedPayment receivedPayment) ;
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_RECEIVED_PAYMENT)
	public int updateReceivedPayment(ReceivedPayment receivedPayment) ; 
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.ENTITY_RECEIVED_PAYMENT)
	public void deleteReceivedPayment(ReceivedPayment[] receivedPayments) ;


	@LogAnnotation(operation_type=LogConstant.OPERATION_CHECK,entity_type=LogConstant.ENTITY_RECEIVED_PAYMENT)
	public void verifyReceivedPayment(ReceivedPayment receivedPayment) ;
	
	
	
	public ReceivedPayment getReceivedPayment(String receivedPayment_id) ;	
	

	public Pager<ReceivedPayment> queryReceivedPayment(ReceivedPayment receivedPayment,  UserPermit userPermit,Pager<ReceivedPayment> pager);
}
