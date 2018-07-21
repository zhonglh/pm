package com.pm.util.log;

import java.util.ArrayList;
import java.util.List;

import org.aopalliance.intercept.MethodInvocation;

import com.common.utils.spring.SpringContextUtil;
import com.pm.domain.business.ParamExtend;
import com.pm.domain.business.Params;
import com.pm.domain.system.Log;
import com.pm.domain.system.LogDetail;
import com.pm.domain.system.User;
import com.pm.service.IParamExtendService;
import com.pm.util.PubMethod;
import com.pm.util.constant.LogConstant;

public class ParamExtendLog extends BasicLog {

	public List<Log> calculateLog(LogAnnotation methodAnnotation,MethodInvocation invocation, User sessionUser) {
		

		IParamExtendService paramsService = SpringContextUtil.getApplicationContext().getBean(IParamExtendService.class);
		
		List<Log> logs = new ArrayList<Log>();

		if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_UPDATE)){
			List<ParamExtend> paramss = (List<ParamExtend>)invocation.getArguments()[0];
			for(ParamExtend params :paramss){
				Log log = super.getLog(methodAnnotation, invocation,sessionUser );
				log.setEntity_type(params.getGroup2());
				List<LogDetail> details = null;
				
				List<ParamExtend> paramList = paramsService.queryAllParamExtend(params);
				ParamExtend preParams = null;
				if(paramList != null && paramList.size() > 0) preParams = paramList.get(0);
				
				log.setEntity_id(params.getParam_id());
				log.setEntity_name("");
				details = PubMethod.getLogDetails(log,ParamExtend.class, preParams,params);		
				log.setDetails(details);
				logs.add(log);
			}
			
		}
	
		
		return logs;
	}

}
