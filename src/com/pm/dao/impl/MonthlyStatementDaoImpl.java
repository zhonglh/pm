package com.pm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.IMonthlyStatementDao;
import com.pm.domain.business.MonthlyStatement;
import com.pm.domain.business.MonthlyStatementDetail;
import com.pm.vo.UserPermit;


@Component
public class MonthlyStatementDaoImpl extends BatisDao implements IMonthlyStatementDao {


	@Override
	public void addMonthlyStatement(MonthlyStatement monthlyStatement) {
		String sql = "MonthlyStatementMapping.addMonthlyStatement";
		this.insert(sql, monthlyStatement);
	}

	@Override
	public void updateMonthlyStatement(MonthlyStatement monthlyStatement) {
		String sql = "MonthlyStatementMapping.updateMonthlyStatement";
		this.update(sql, monthlyStatement);
	}

	@Override
	public void deleteMonthlyStatement(MonthlyStatement monthlyStatement) {
		String sql = "MonthlyStatementMapping.deleteMonthlyStatement";
		this.delete(sql, monthlyStatement);	
	}

	@Override
	public int verifyMonthlyStatement(MonthlyStatement monthlyStatement) {
		String sql = "MonthlyStatementMapping.verifyMonthlyStatement";
		return this.update(sql, monthlyStatement);
	}
	

	@Override
	public int unVerifyMonthlyStatement(MonthlyStatement monthlyStatement) {
		String sql = "MonthlyStatementMapping.unVerifyMonthlyStatement";
		return this.update(sql, monthlyStatement);
	}
	
	

	public MonthlyStatement queryMonthlyStatementSum(MonthlyStatement monthlyStatement, UserPermit userPermit){
		String sql = "MonthlyStatementMapping.queryMonthlyStatementSum";
		Map  map = new HashMap();
		if(monthlyStatement !=null) map.put(monthlyStatement.getClass().getSimpleName(), monthlyStatement);
		if(userPermit !=null) map.put(userPermit.getClass().getSimpleName(), userPermit);
	
		return this.query(sql,MonthlyStatement.class, map).get(0);
	}

	@Override
	public Pager<MonthlyStatement> queryMonthlyStatement(
			MonthlyStatement monthlyStatement, 
			UserPermit userPermit,
			Pager<MonthlyStatement> pager) {		
		String sql = "MonthlyStatementMapping.queryMonthlyStatement";
		Pager<MonthlyStatement> pager1 =  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, MonthlyStatement.class, monthlyStatement , userPermit);
		
		return pager1;
		
	}

	@Override
	public MonthlyStatement getMonthlyStatement(MonthlyStatement monthlyStatement) {
		String sql = "MonthlyStatementMapping.getMonthlyStatement";
		List<MonthlyStatement> list = this.query(sql, MonthlyStatement.class, monthlyStatement);
		if(list == null || list.isEmpty()) return null;
		return list.get(0);
	}

	@Override
	public boolean isExist(MonthlyStatement monthlyStatement) {
		String sql = "MonthlyStatementMapping.isExist";
		List<MonthlyStatement> list = this.query(sql, MonthlyStatement.class, monthlyStatement);
		if(list == null || list.isEmpty()) return false;
		return true;
	}

	
	
	@Override
	public void addMonthlyStatementDetail(MonthlyStatementDetail monthlyStatementDetail) {
		String sql = "MonthlyStatementMapping.addMonthlyStatementDetail";
		this.insert(sql, monthlyStatementDetail);

	}

	@Override
	public void updateMonthlyStatementDetail(MonthlyStatementDetail monthlyStatementDetail) {
		String sql = "MonthlyStatementMapping.updateMonthlyStatementDetail";
		this.update(sql, monthlyStatementDetail);
	}

	@Override
	public void deleteMonthlyStatementDetail(MonthlyStatement monthlyStatement) {
		String sql = "MonthlyStatementMapping.deleteMonthlyStatementDetail";
		this.delete(sql, monthlyStatement);	
	}

	@Override
	public List<MonthlyStatementDetail> getMonthlyStatementDetail(MonthlyStatement monthlyStatement) {
		String sql = "MonthlyStatementMapping.getMonthlyStatementDetail";
		List<MonthlyStatementDetail> list = this.query(sql, MonthlyStatementDetail.class, monthlyStatement);
		return list;
	}

	@Override
	public List<MonthlyStatementDetail> computeMonthlyStatementDetail(MonthlyStatement monthlyStatement) {
		String sql = "MonthlyStatementMapping.computeMonthlyStatementDetail";
		List<MonthlyStatementDetail> list = this.query(sql, MonthlyStatementDetail.class, monthlyStatement);
		return list;
	}

	@Override
	public Pager<MonthlyStatementDetail> queryMonthlyStatementDetail(MonthlyStatement monthlyStatement, UserPermit userPermit,Pager<MonthlyStatementDetail> pager){
		String sql = "MonthlyStatementMapping.queryMonthlyStatementDetail";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, MonthlyStatementDetail.class, monthlyStatement , userPermit);
	}

}
