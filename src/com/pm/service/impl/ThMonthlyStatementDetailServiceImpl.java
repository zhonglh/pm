package com.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.pm.dao.IThMonthlyStatementDetailDao;
import com.pm.domain.business.ThMonthlyStatementDetail;
import com.pm.service.IThMonthlyStatementDetailService;

@Component
public class ThMonthlyStatementDetailServiceImpl implements  IThMonthlyStatementDetailService {

	@Autowired IThMonthlyStatementDetailDao thMonthlyStatementDetailDao;
	@Override
	public int addThMonthlyStatementDetail(ThMonthlyStatementDetail thMonthlyStatementDetail) {
		return thMonthlyStatementDetailDao.addThMonthlyStatementDetail(thMonthlyStatementDetail);
	}

	@Override
	public ThMonthlyStatementDetail getThMonthlyStatementDetail(String id) {
		return thMonthlyStatementDetailDao.getThMonthlyStatementDetail(id);
	}

	@Override
	public Pager<ThMonthlyStatementDetail> queryThMonthlyStatementDetail(
		ThMonthlyStatementDetail thMonthlyStatementDetail,
		Pager<ThMonthlyStatementDetail> pager){

		return thMonthlyStatementDetailDao.queryThMonthlyStatementDetail(thMonthlyStatementDetail,  pager);
	}


}