package com.pm.dao;

import java.util.List;

import com.common.beans.Pager;
import com.pm.domain.business.MonthlyStatement;
import com.pm.domain.business.MonthlyStatementDetail;
import com.pm.vo.UserPermit;

public interface IMonthlyStatementDao {


	public void addMonthlyStatement(MonthlyStatement monthlyStatement) ;

	public void updateMonthlyStatement(MonthlyStatement monthlyStatement) ;

	public void deleteMonthlyStatement(MonthlyStatement monthlyStatement) ;

	public int verifyMonthlyStatement(MonthlyStatement monthlyStatement) ;
	

	public int unVerifyMonthlyStatement(MonthlyStatement monthlyStatement) ;
	
	/**
	 * 求和
	 * @param monthlyStatement
	 * @param userPermit
	 * @return
	 */
	public MonthlyStatement queryMonthlyStatementSum(MonthlyStatement monthlyStatement, UserPermit userPermit);
	
	public Pager<MonthlyStatement> queryMonthlyStatement(MonthlyStatement monthlyStatement, UserPermit userPermit,Pager<MonthlyStatement> pager);
	
	public MonthlyStatement getMonthlyStatement(MonthlyStatement monthlyStatement) ;	

	public boolean isExist(MonthlyStatement monthlyStatement) ;
	
	

	public void addMonthlyStatementDetail(MonthlyStatementDetail monthlyStatementDetail) ;	

	public void updateMonthlyStatementDetail(MonthlyStatementDetail monthlyStatementDetail) ;	

	public void deleteMonthlyStatementDetail(MonthlyStatement monthlyStatement) ;
	
	public List<MonthlyStatementDetail> getMonthlyStatementDetail(MonthlyStatement monthlyStatement);
	
	public List<MonthlyStatementDetail> computeMonthlyStatementDetail(MonthlyStatement monthlyStatement) ;
	

	public Pager<MonthlyStatementDetail> queryMonthlyStatementDetail(MonthlyStatement monthlyStatement, UserPermit userPermit,Pager<MonthlyStatementDetail> pager);
	
	
}
