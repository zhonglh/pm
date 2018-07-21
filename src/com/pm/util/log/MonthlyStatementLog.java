package com.pm.util.log;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.aopalliance.intercept.MethodInvocation;

import com.common.utils.spring.SpringContextUtil;
import com.pm.domain.business.MonthlyStatement;
import com.pm.domain.system.Log;
import com.pm.domain.system.LogDetail;
import com.pm.domain.system.User;
import com.pm.service.IMonthlyStatementService;
import com.pm.util.PubMethod;
import com.pm.util.constant.LogConstant;

public class MonthlyStatementLog extends BasicLog {

	public List<Log> calculateLog(LogAnnotation methodAnnotation,MethodInvocation invocation, User sessionUser) {
		
		IMonthlyStatementService monthlyStatementService = SpringContextUtil.getApplicationContext().getBean(IMonthlyStatementService.class);
		List<Log> logs = new ArrayList<Log>();
		
		if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_DELETE)){
			
			MonthlyStatement[] monthlyStatements = (MonthlyStatement[])invocation.getArguments()[0];
			if(monthlyStatements == null || monthlyStatements.length == 0) return null;
			for(MonthlyStatement monthlyStatement : monthlyStatements){
				Log log = super.getLog(methodAnnotation, invocation,sessionUser );
				MonthlyStatement preMonthlyStatement = monthlyStatementService.getMonthlyStatement(monthlyStatement);
				if(preMonthlyStatement == null) preMonthlyStatement = new MonthlyStatement();
				log.setEntity_id(monthlyStatement.getMonthly_statement_id());
				String entity_name = monthlyStatement.getProject_name()==null?
						preMonthlyStatement.getProject_name() + preMonthlyStatement.getStatement_month():
						monthlyStatement.getProject_name() + monthlyStatement.getStatement_month();
				log.setEntity_name(entity_name);
				log.setProject_id(monthlyStatement.getProject_id()==null?preMonthlyStatement.getProject_id() : monthlyStatement.getProject_id());
				List<LogDetail>  details = PubMethod.getLogDetails(log,MonthlyStatement.class, preMonthlyStatement,monthlyStatement);	
				special(details);
				log.setDetails(details);
				logs.add(log);
			}
			
		}else {
			
			Log log = super.getLog(methodAnnotation, invocation,sessionUser );

			List<LogDetail> details = null;
			if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_INSERT)){
				MonthlyStatement monthlyStatement = (MonthlyStatement)invocation.getArguments()[0];
				MonthlyStatement preMonthlyStatement = new MonthlyStatement();				
				log.setEntity_id(monthlyStatement.getMonthly_statement_id());
				String entity_name = monthlyStatement.getProject_name()==null?
						preMonthlyStatement.getProject_name() + preMonthlyStatement.getStatement_month():
						monthlyStatement.getProject_name() + monthlyStatement.getStatement_month();
				log.setEntity_name(entity_name);
				log.setProject_id(monthlyStatement.getProject_id()==null?preMonthlyStatement.getProject_id() : monthlyStatement.getProject_id());
				details = PubMethod.getLogDetails(log,MonthlyStatement.class, preMonthlyStatement,monthlyStatement);	
				special(details);
				log.setDetails(details);
				logs.add(log);
			}else if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_UPDATE)){
				MonthlyStatement monthlyStatement = (MonthlyStatement)invocation.getArguments()[0];
				MonthlyStatement preMonthlyStatement = monthlyStatementService.getMonthlyStatement(monthlyStatement);				
				log.setEntity_id(monthlyStatement.getMonthly_statement_id());
				String entity_name = monthlyStatement.getProject_name()==null?
						preMonthlyStatement.getProject_name() + preMonthlyStatement.getStatement_month():
						monthlyStatement.getProject_name() + monthlyStatement.getStatement_month();
				log.setEntity_name(entity_name);
				log.setProject_id(monthlyStatement.getProject_id()==null?preMonthlyStatement.getProject_id() : monthlyStatement.getProject_id());
				details = PubMethod.getLogDetails(log,MonthlyStatement.class, preMonthlyStatement,monthlyStatement);				
				special(details);
				log.setDetails(details);
				logs.add(log);
			}else if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_CHECK)){
				MonthlyStatement monthlyStatement = (MonthlyStatement)invocation.getArguments()[0];
				MonthlyStatement preMonthlyStatement = monthlyStatementService.getMonthlyStatement(monthlyStatement);				
				log.setEntity_id(monthlyStatement.getMonthly_statement_id());
				String entity_name = monthlyStatement.getProject_name()==null?
						preMonthlyStatement.getProject_name() + preMonthlyStatement.getStatement_month():
						monthlyStatement.getProject_name() + monthlyStatement.getStatement_month();
				log.setEntity_name(entity_name);
				log.setProject_id(monthlyStatement.getProject_id()==null?preMonthlyStatement.getProject_id() : monthlyStatement.getProject_id());
				logs.add(log);
			}
			
			
		}
		
		return logs;
	}
	


	private void special(List<LogDetail> details){
		if(details == null || details.isEmpty()) return ;
		
		for(LogDetail detail : details){
			
			if(detail.getData_item_code().equals("statement_type")){
				if(detail.getOperation_after() != null && !detail.getOperation_after().isEmpty()){
					detail.setOperation_after(SpringContextUtil.getApplicationContext ().getMessage ("statement.type."+detail.getOperation_after(), null, Locale.CHINA));
				}
				if(detail.getOperation_before() != null && !detail.getOperation_before().isEmpty()){
					detail.setOperation_before(SpringContextUtil.getApplicationContext ().getMessage ("statement.type."+detail.getOperation_before(), null, Locale.CHINA));
				}
				
				
			}	
					
		}
	}	
	

}
