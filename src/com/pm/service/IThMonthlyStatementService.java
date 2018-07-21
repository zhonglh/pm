package com.pm.service;

import com.common.beans.Pager;
import com.pm.domain.business.ThMonthlyStatement;

public interface IThMonthlyStatementService  {

	public int addThMonthlyStatement(ThMonthlyStatement thMonthlyStatement) ;
	public ThMonthlyStatement getThMonthlyStatement(String id) ;	

	public Pager<ThMonthlyStatement> queryThMonthlyStatement(ThMonthlyStatement thMonthlyStatement,   Pager<ThMonthlyStatement> pager);

}