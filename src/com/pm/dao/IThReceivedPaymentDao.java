package com.pm.dao;

import com.common.beans.Pager;
import com.pm.domain.business.ThReceivedPayment;

public interface IThReceivedPaymentDao {

	public int addThReceivedPayment(ThReceivedPayment thReceivedPayment) ;


	public ThReceivedPayment getThReceivedPayment(String id) ;	

	public Pager<ThReceivedPayment> queryThReceivedPayment(ThReceivedPayment thReceivedPayment,   Pager<ThReceivedPayment> pager);

}