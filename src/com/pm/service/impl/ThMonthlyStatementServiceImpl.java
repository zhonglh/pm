package com.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.pm.dao.IThMonthlyStatementDao;
import com.pm.domain.business.ThMonthlyStatement;
import com.pm.service.IThMonthlyStatementService;

@Component
public class ThMonthlyStatementServiceImpl implements  IThMonthlyStatementService {

	@Autowired IThMonthlyStatementDao thMonthlyStatementDao;
	@Override
	public int addThMonthlyStatement(ThMonthlyStatement thMonthlyStatement) {
		return thMonthlyStatementDao.addThMonthlyStatement(thMonthlyStatement);
	}
	@Override
	public ThMonthlyStatement getThMonthlyStatement(String id) {
		return thMonthlyStatementDao.getThMonthlyStatement(id);
	}

	@Override
	public Pager<ThMonthlyStatement> queryThMonthlyStatement(
		ThMonthlyStatement thMonthlyStatement,
		Pager<ThMonthlyStatement> pager){

		return thMonthlyStatementDao.queryThMonthlyStatement(thMonthlyStatement,  pager);
	}


}