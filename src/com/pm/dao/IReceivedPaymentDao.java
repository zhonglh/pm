package com.pm.dao;

import com.common.beans.Pager;
import com.pm.domain.business.Invoice;
import com.pm.domain.business.ReceivedPayment;
import com.pm.vo.UserPermit;

public interface IReceivedPaymentDao {

	public int addReceivedPayment(ReceivedPayment receivedPayment) ;
	

	public int updateReceivedPayment(ReceivedPayment receivedPayment) ; 
	

	public void deleteReceivedPayment(ReceivedPayment receivedPayment) ;


	public int verifyReceivedPayment(ReceivedPayment receivedPayment) ;

	public int unVerifyReceivedPayment(ReceivedPayment receivedPayment) ;
	
	
	public void deleteInvoice(Invoice invoice);
	
	
	
	public ReceivedPayment getReceivedPayment(String receivedPayment_id) ;	
	

	public Pager<ReceivedPayment> queryReceivedPayment(ReceivedPayment receivedPayment,  UserPermit userPermit,Pager<ReceivedPayment> pager);

}
