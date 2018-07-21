package com.pm.util.log;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.common.utils.IDKit;
import com.common.utils.spring.SpringContextUtil;
import com.pm.domain.system.Log;
import com.pm.domain.system.LogDetail;
import com.pm.domain.system.Permit;
import com.pm.domain.system.Role;
import com.pm.domain.system.User;
import com.pm.service.IRoleService;
import com.pm.util.PubMethod;
import com.pm.util.constant.LogConstant;

public class RoleLog extends BasicLog {

	public List<Log> calculateLog(LogAnnotation methodAnnotation,MethodInvocation invocation, User sessionUser) {
		
		IRoleService roleService = SpringContextUtil.getApplicationContext().getBean(IRoleService.class);
		List<Log> logs = new ArrayList<Log>();
		
		if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_DELETE)){
			
			Role[] roles = (Role[])invocation.getArguments()[0];
			if(roles == null || roles.length == 0) return null;
			for(Role role : roles){
				Log log = super.getLog(methodAnnotation, invocation,sessionUser );
				Role preRole = roleService.getRole(role.getRole_id());
				if(preRole == null) preRole = new Role();
				log.setEntity_id(role.getRole_id());
				log.setEntity_name(role.getRole_name()==null?preRole.getRole_name():role.getRole_name());
				List<LogDetail> details = PubMethod.getLogDetails(log,Role.class, preRole,role);
				if(details == null) details = new ArrayList<LogDetail>();
				List<Permit> permits = roleService.getPermicsByRole(role);
				if(permits != null){
					for(Permit permit : permits){
						LogDetail logDetail = new LogDetail();
						logDetail.setLog_detail_id(IDKit.generateId());
						logDetail.setLog_id(log.getLog_id());
						logDetail.setData_item_code("add_permit");
						logDetail.setData_item_i18n("删除许可");
						if(permit != null) logDetail.setOperation_before(
								SpringContextUtil.getApplicationContext().getMessage(permit.getPermit_name_i18n(), null, 
								((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getLocale()));
						details.add(logDetail);
					}
				}
				log.setDetails(details);
				logs.add(log);
			}
			
		}else {
			
			Log log = super.getLog(methodAnnotation, invocation,sessionUser );

			List<LogDetail> details = null;
			if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_INSERT)){
				Role role = (Role)invocation.getArguments()[0];
				Role preRole = new Role();				
				log.setEntity_id(role.getRole_id());
				log.setEntity_name(role.getRole_name()==null?preRole.getRole_name():role.getRole_name());
				details = PubMethod.getLogDetails(log,Role.class, preRole,role);
				List<String> permitIds = (List<String>)invocation.getArguments()[1];
				if(permitIds != null){
					for(String permitId : permitIds){
						LogDetail logDetail = new LogDetail();
						logDetail.setLog_detail_id(IDKit.generateId());
						logDetail.setLog_id(log.getLog_id());
						logDetail.setData_item_code("add_permit");
						logDetail.setData_item_i18n("增加许可");
						Permit permit = PubMethod.getPermit(roleService.getAllPermics(), permitId);
						if(permit != null) logDetail.setOperation_after(
								SpringContextUtil.getApplicationContext().getMessage(permit.getPermit_name_i18n(), null, 
								((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getLocale()));
						details.add(logDetail);
					}
				}
				
				log.setDetails(details);
				logs.add(log);
			}else if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_UPDATE)){
				Role role = (Role)invocation.getArguments()[0];
				Role preRole = roleService.getRole(role.getRole_id());				
				log.setEntity_id(role.getRole_id());
				log.setEntity_name(role.getRole_name()==null?preRole.getRole_name():role.getRole_name());
				details = PubMethod.getLogDetails(log,Role.class, preRole,role);

				List<String> addPermitIds = (List<String>)invocation.getArguments()[1];
				List<String> deletePermitIds = (List<String>)invocation.getArguments()[2];
				
				if(addPermitIds != null){
					for(String permitId : addPermitIds){
						LogDetail logDetail = new LogDetail();
						logDetail.setLog_detail_id(IDKit.generateId());
						logDetail.setLog_id(log.getLog_id());
						logDetail.setData_item_code("add_permit");
						logDetail.setData_item_i18n("增加许可");
						Permit permit = PubMethod.getPermit(roleService.getAllPermics(), permitId);
						if(permit != null) logDetail.setOperation_after(
								SpringContextUtil.getApplicationContext().getMessage(permit.getPermit_name_i18n(), null, 
								((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getLocale()));
						details.add(logDetail);
					}
				}
				

				if(deletePermitIds != null){
					for(String permitId : deletePermitIds){
						LogDetail logDetail = new LogDetail();
						logDetail.setLog_detail_id(IDKit.generateId());
						logDetail.setLog_id(log.getLog_id());
						logDetail.setData_item_code("delete_permit");
						logDetail.setData_item_i18n("删除许可");
						Permit permit = PubMethod.getPermit(roleService.getAllPermics(), permitId);
						if(permit != null) logDetail.setOperation_before(
								SpringContextUtil.getApplicationContext().getMessage(permit.getPermit_name_i18n(), null, 
								((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getLocale()));
						details.add(logDetail);
					}
				}				
				
				log.setDetails(details);
				logs.add(log);
			}
			
			
		}
		
		return logs;
	}
	
	
	
	
	

}
