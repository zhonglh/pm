package com.pm.dao;

import com.pm.domain.business.ThMonthlyStatement;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;

public interface IThMonthlyStatementDao {

	public int addThMonthlyStatement(ThMonthlyStatement thMonthlyStatement) ;

	public ThMonthlyStatement getThMonthlyStatement(String id) ;	

	public Pager<ThMonthlyStatement> queryThMonthlyStatement(ThMonthlyStatement thMonthlyStatement,  Pager<ThMonthlyStatement> pager);

}