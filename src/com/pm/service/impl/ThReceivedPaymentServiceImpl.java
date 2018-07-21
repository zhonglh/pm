package com.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.pm.dao.IThReceivedPaymentDao;
import com.pm.domain.business.ThReceivedPayment;
import com.pm.service.IThReceivedPaymentService;

@Component
public class ThReceivedPaymentServiceImpl implements  IThReceivedPaymentService {

	@Autowired IThReceivedPaymentDao thReceivedPaymentDao;
	@Override
	public int addThReceivedPayment(ThReceivedPayment thReceivedPayment) {
		return thReceivedPaymentDao.addThReceivedPayment(thReceivedPayment);
	}

	@Override
	public ThReceivedPayment getThReceivedPayment(String id) {
		return thReceivedPaymentDao.getThReceivedPayment(id);
	}

	@Override
	public Pager<ThReceivedPayment> queryThReceivedPayment(
		ThReceivedPayment thReceivedPayment,
		Pager<ThReceivedPayment> pager){

		return thReceivedPaymentDao.queryThReceivedPayment(thReceivedPayment,  pager);
	}


}