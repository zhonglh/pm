package com.pm.service;

import java.util.List;

import com.common.beans.Pager;
import com.pm.domain.business.MonthlyStatement;
import com.pm.domain.business.MonthlyStatementDetail;
import com.pm.domain.system.User;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;
import com.pm.vo.UserPermit;

public interface IMonthlyStatementService extends IBaseService{
	
	
	public MonthlyStatement autoAddMonthlyStatement(String project_id, int use_month,User sessionUser);
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_MONTHLY_STATEMENT)
	public void addMonthlyStatement(MonthlyStatement monthlyStatement,List<MonthlyStatementDetail> monthlyStatementDetails) ;

	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_MONTHLY_STATEMENT)
	public void updateMonthlyStatement(MonthlyStatement monthlyStatement,List<MonthlyStatementDetail> monthlyStatementDetails) ;

	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.ENTITY_MONTHLY_STATEMENT)
	public void deleteMonthlyStatement(MonthlyStatement[] monthlyStatement) ;

	@LogAnnotation(operation_type=LogConstant.OPERATION_CHECK,entity_type=LogConstant.ENTITY_MONTHLY_STATEMENT)
	public void verifyMonthlyStatement(MonthlyStatement monthlyStatement) ;
	
	
	
	public Pager<MonthlyStatement> queryMonthlyStatement(MonthlyStatement monthlyStatement, UserPermit userPermit,Pager<MonthlyStatement> pager);
	
	public MonthlyStatement getMonthlyStatement(MonthlyStatement monthlyStatement) ;	

	public boolean isExist(MonthlyStatement monthlyStatement) ;
	
	

	//@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_MONTHLY_STATEMENT)
	//public void addMonthlyStatementDetail(MonthlyStatementDetail[] monthlyStatementDetails) ;
	

	//@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE_MONTHLY_STATEMENT_DETAIL,entity_type=LogConstant.ENTITY_MONTHLY_STATEMENT)
	//public void updateMonthlyStatementDetail(MonthlyStatementDetail[] monthlyStatementDetails) ;	


	//@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE_MONTHLY_STATEMENT_DETAIL,entity_type=LogConstant.ENTITY_MONTHLY_STATEMENT)
	//public void deleteMonthlyStatementDetail(MonthlyStatement monthlyStatement) ;
	

	public List<MonthlyStatementDetail> computeMonthlyStatementDetail(MonthlyStatement monthlyStatement);
	
	public List<MonthlyStatementDetail> getMonthlyStatementDetail(MonthlyStatement monthlyStatement);
	
	public Pager<MonthlyStatementDetail> queryMonthlyStatementDetail(MonthlyStatement monthlyStatement, UserPermit userPermit,Pager<MonthlyStatementDetail> pager);
	
}
