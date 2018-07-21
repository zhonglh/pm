package com.pm.util.log;

import java.util.ArrayList;
import java.util.List;

import org.aopalliance.intercept.MethodInvocation;

import com.common.utils.spring.SpringContextUtil;
import com.pm.domain.business.Params;
import com.pm.domain.system.Log;
import com.pm.domain.system.LogDetail;
import com.pm.domain.system.User;
import com.pm.service.IParamsService;
import com.pm.util.PubMethod;
import com.pm.util.constant.LogConstant;

public class ParamsLog extends BasicLog {

	public List<Log> calculateLog(LogAnnotation methodAnnotation,MethodInvocation invocation, User sessionUser) {
		

		IParamsService paramsService = SpringContextUtil.getApplicationContext().getBean(IParamsService.class);
		List<Log> logs = new ArrayList<Log>();

		if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_UPDATE)){
			List<Params> paramss = (List<Params>)invocation.getArguments()[0];
			for(Params params :paramss){
				Log log = super.getLog(methodAnnotation, invocation,sessionUser );
				log.setEntity_type(params.getParam_name());
				List<LogDetail> details = null;
				
				Params params1 = new Params();
				params1.setParam_id(params.getParam_id());
				List<Params> paramList = paramsService.queryAllParams(params1);	
				Params preParams = null;
				if(paramList != null && paramList.size() > 0) preParams = paramList.get(0);
				
				log.setEntity_id(params.getParam_id());
				log.setEntity_name(params.getParam_name()==null?preParams.getParam_name():params.getParam_name());
				details = PubMethod.getLogDetails(log,Params.class, preParams,params);		
				log.setDetails(details);
				logs.add(log);
			}
			
		}
	
		
		return logs;
	}

}
