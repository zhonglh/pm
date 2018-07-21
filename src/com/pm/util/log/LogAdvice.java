package com.pm.util.log;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.common.utils.spring.SpringContextUtil;
import com.pm.domain.system.Log;
import com.pm.domain.system.User;
import com.pm.service.ILogService;
import com.pm.util.PubMethod;
import com.pm.util.constant.BusinessUtil;

/**
 * 日志增强
 * @author zhonglihong
 * @date 2017年9月18日 下午2:34:01
 */
public class LogAdvice implements MethodInterceptor {
	
	private ILogService logService = null;
	
	private  ILogService getLogService(){
		
		if(logService == null) {
			synchronized(LogAdvice.class){
				logService = SpringContextUtil.getApplicationContext().getBean(ILogService.class);
			}
		}
		return logService;
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {

		Method mehtod = invocation.getMethod();
		boolean b = mehtod.isAnnotationPresent(LogAnnotation.class);
		boolean proceed = false;
		Object result = null;
		
		if(b){
			LogAnnotation methodAnnotation = mehtod.getAnnotation(LogAnnotation.class);
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			User seesionUser = PubMethod.getUser(request);
			if(seesionUser != null){
				Class<?> clz = mehtod.getDeclaringClass();
				String clzname = getLogClassName(clz);
				
				try{
					BasicLog basicLog = null;
					
					try{
						basicLog = (BasicLog)Class.forName(clzname).newInstance();
					}catch(Exception e){
						basicLog = new GeneralLog();
					}
					List<Log> logs = basicLog.calculateLog(methodAnnotation, invocation, seesionUser);
					proceed = true;
					result = invocation.proceed();
					
					try{
						basicLog.insertLog(logs, getLogService());
					}catch(Exception e){
						e.printStackTrace();
					}
					
				}catch(Exception e){
					e.printStackTrace();
					throw e;
				}
			
			}			
		}
		
		if(!proceed) result = invocation.proceed();
		
		return result;

	}

	private String getLogClassName(Class<?> clz) {
		return BusinessUtil.LOG_PACKAGE + "." +clz.getName().substring(16).replaceAll("Service", "Log");
	}
	
	

}
