package com.pm.util.log;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.poi.util.StringUtil;

import com.pm.domain.business.IdEntity;
import com.pm.domain.system.Dept;
import com.pm.domain.system.Log;
import com.pm.domain.system.LogDetail;
import com.pm.domain.system.User;
import com.pm.service.IGeneralLogService;
import com.pm.util.GenericsHelper;
import com.pm.util.PubMethod;
import com.pm.util.constant.LogConstant;

/**
 * 通用日志处理, 只处理第一个参数
 * @author zhonglihong
 * @date 2016年5月12日 下午12:41:08
 */
public class GeneralLog extends BasicLog {

	@Override
	public List<Log> calculateLog(LogAnnotation methodAnnotation, MethodInvocation invocation, User seesionUser) {
		
		if(invocation.getArguments().length == 0) {
			return null;
		}
		Object arg = invocation.getArguments()[0];
		if(arg == null) {
			return null;
		}
		
		
		Method method =  invocation.getMethod();
		List<Class> list = GenericsHelper.getMethodAllParameterTypes(method, 0);
		if (list.isEmpty()) {
			return null;
		}

		Class clz = method.getParameterTypes()[0];
		Class actualClz = list.get(0);


		Collection<Object> args = GenericsHelper.processParams(arg,clz,actualClz);
		
		if(args == null || args.isEmpty()) {
			return null;
		}
		
		IGeneralLogService generalLogService = (IGeneralLogService)invocation.getThis();

		List<Log> logs = new ArrayList<Log>();
		

		if (methodAnnotation.operation_type().equals(LogConstant.OPERATION_DELETE)) {
			for (Object obj : args) {
				if(obj == null) {
					continue;
				}
				if (obj instanceof IdEntity) {
					Log log = super.getLog(methodAnnotation, invocation,seesionUser );
					IdEntity idEntity = (IdEntity) obj;
					Object preObj = generalLogService.get(idEntity.getId());
					log.setEntity_id(idEntity.getId());
					log.setEntity_name(preObj.toString());
					
					List<LogDetail>  details = PubMethod.getLogDetails(log,actualClz, preObj,obj);	
					log.setDetails(details);
					logs.add(log);					
				}

			}
		}else if (methodAnnotation.operation_type().equals(LogConstant.OPERATION_RECOVER)) {			
			for (Object obj : args) {
				if (obj instanceof IdEntity) {
					Log log = super.getLog(methodAnnotation, invocation,seesionUser );
					IdEntity idEntity = (IdEntity) obj;
					Object preObj = generalLogService.get(idEntity.getId());
					log.setEntity_id(idEntity.getId());
					log.setEntity_name(preObj.toString());					
					logs.add(log);
				}

			}		
		
		}else if (methodAnnotation.operation_type().equals(LogConstant.OPERATION_INSERT)) {
			for (Object obj : args) {
				if (obj instanceof IdEntity) {
					Log log = super.getLog(methodAnnotation, invocation,seesionUser );
					IdEntity idEntity = (IdEntity) obj;
					log.setEntity_id(idEntity.getId());
					log.setEntity_name(obj.toString());
					
					List<LogDetail>  details = PubMethod.getLogDetails(log,actualClz, null ,obj);	
					log.setDetails(details);
					logs.add(log);					
				}

			}			

		
		} else if (methodAnnotation.operation_type().equals(LogConstant.OPERATION_UPDATE)) {
			for (Object obj : args) {
				if (obj instanceof IdEntity) {
					
					Log log = super.getLog(methodAnnotation, invocation,seesionUser );
					IdEntity idEntity = (IdEntity) obj;
					if(idEntity.getId() != null && idEntity.getId().length() >0){
						Object preObj = generalLogService.get(idEntity.getId());
						log.setEntity_id(idEntity.getId());
						log.setEntity_name(obj.toString());						
						List<LogDetail>  details = PubMethod.getLogDetails(log,actualClz, preObj ,obj);	
						log.setDetails(details);
					}else {
						log.setOperation_type(LogConstant.OPERATION_INSERT);
						log.setEntity_id(idEntity.getId());
						log.setEntity_name(obj.toString());						
						List<LogDetail>  details = PubMethod.getLogDetails(log,actualClz, null ,obj);	
						log.setDetails(details);
					}
					logs.add(log);					
				}

			}
		
		}else if (methodAnnotation.operation_type().equals(LogConstant.OPERATION_DALLOT)) {
			for (Object obj : args) {
				if (obj instanceof IdEntity) {
					Log log = super.getLog(methodAnnotation, invocation,seesionUser );
					IdEntity idEntity = (IdEntity) obj;
					Object preObj = generalLogService.get(idEntity.getId());
					log.setEntity_id(idEntity.getId());
					log.setEntity_name(obj.toString());
					
					List<LogDetail>  details = PubMethod.getLogDetails(log,actualClz, preObj ,obj);	
					log.setDetails(details);
					logs.add(log);					
				}

			}
		
		}else if(
				methodAnnotation.operation_type().equals(LogConstant.OPERATION_CHECK) ||
				methodAnnotation.operation_type().equals(LogConstant.OPERATION_UNCHECK)
		) {

			//核单和取消核单是在明细中查看， 不在日志中显示

			/*for (Object obj : args) {
				if (obj instanceof IdEntity) {
					Log log = super.getLog(methodAnnotation, invocation,seesionUser );
					IdEntity idEntity = (IdEntity) obj;
					Object preObj = generalLogService.get(idEntity.getId());
					log.setEntity_id(idEntity.getId());
					log.setEntity_name(obj.toString());
					logs.add(log);
				}

			}*/

		}
		return logs;
		
		
	}

}
