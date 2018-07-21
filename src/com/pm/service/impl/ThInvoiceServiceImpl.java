package com.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.pm.dao.IThInvoiceDao;
import com.pm.domain.business.ThInvoice;
import com.pm.service.IThInvoiceService;

@Component
public class ThInvoiceServiceImpl implements  IThInvoiceService {

	@Autowired IThInvoiceDao thInvoiceDao;
	@Override
	public int addThInvoice(ThInvoice thInvoice) {
		return thInvoiceDao.addThInvoice(thInvoice);
	}

	@Override
	public ThInvoice getThInvoice(String id) {
		return thInvoiceDao.getThInvoice(id);
	}

	@Override
	public Pager<ThInvoice> queryThInvoice(
		ThInvoice thInvoice,
		Pager<ThInvoice> pager){

		return thInvoiceDao.queryThInvoice(thInvoice,  pager);
	}


}