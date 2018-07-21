package com.pm.service;

import com.common.beans.Pager;
import com.pm.domain.business.ThReceivedPayment;

public interface IThReceivedPaymentService  {

	public int addThReceivedPayment(ThReceivedPayment thReceivedPayment) ;


	public ThReceivedPayment getThReceivedPayment(String id) ;	

	public Pager<ThReceivedPayment> queryThReceivedPayment(ThReceivedPayment thReceivedPayment,  Pager<ThReceivedPayment> pager);

}