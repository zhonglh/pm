package com.pm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.common.utils.IDKit;
import com.pm.dao.IThMonthlyStatementDao;
import com.pm.domain.business.ThMonthlyStatement;
import com.pm.domain.system.User;
import com.pm.util.PubMethod;
import com.pm.util.ThreadLocalUser;

@Component
public class ThMonthlyStatementDaoImpl extends BatisDao implements IThMonthlyStatementDao  {

	@Override
	public int addThMonthlyStatement(ThMonthlyStatement thMonthlyStatement) {

		User sessionUser = ThreadLocalUser.getUser();		
		thMonthlyStatement.setId(IDKit.generateId());
		thMonthlyStatement.setHis_date(PubMethod.getCurrentDate());
		thMonthlyStatement.setHis_user_id(sessionUser.getUser_id());
		thMonthlyStatement.setHis_user_name(sessionUser.getUser_name());
		
		String sql = "ThMonthlyStatementMapping.addThMonthlyStatement";
		return this.insert(sql, thMonthlyStatement);
	}

	@Override
	public ThMonthlyStatement getThMonthlyStatement(String id) {

		String sql = "ThMonthlyStatementMapping.getThMonthlyStatement"; 
		ThMonthlyStatement thMonthlyStatement = new ThMonthlyStatement(); 
		thMonthlyStatement.setId(id); 
		List<ThMonthlyStatement> list = this.query(sql, ThMonthlyStatement.class, thMonthlyStatement); 
		if(list == null || list.isEmpty()) return null; 
		else return list.get(0);	
	}

	@Override
	public Pager<ThMonthlyStatement> queryThMonthlyStatement(
		ThMonthlyStatement thMonthlyStatement,
		Pager<ThMonthlyStatement> pager){

		String sql = "ThMonthlyStatementMapping.queryThMonthlyStatement"; 
		Pager<ThMonthlyStatement> pager1 =  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql,ThMonthlyStatement.class, thMonthlyStatement); 
		
		return pager1;
	}


}