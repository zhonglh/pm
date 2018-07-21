package com.pm.util.log;

import java.util.ArrayList;
import java.util.List;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.common.utils.IDKit;
import com.common.utils.spring.SpringContextUtil;
import com.pm.domain.system.Log;
import com.pm.domain.system.LogDetail;
import com.pm.domain.system.Role;
import com.pm.domain.system.User;
import com.pm.service.IRoleService;
import com.pm.service.IUserService;
import com.pm.util.PubMethod;
import com.pm.util.constant.LogConstant;

public class UserLog extends BasicLog {

	public List<Log> calculateLog(LogAnnotation methodAnnotation,MethodInvocation invocation, User sessionUser) {
		
		IUserService userService = SpringContextUtil.getApplicationContext().getBean(IUserService.class);
		IRoleService roleService = SpringContextUtil.getApplicationContext().getBean(IRoleService.class);
		List<Log> logs = new ArrayList<Log>();
		
		if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_DELETE)){
			
			User[] users = (User[])invocation.getArguments()[0];
			if(users == null || users.length == 0) return null;
			for(User user : users){
				Log log = super.getLog(methodAnnotation, invocation,sessionUser );
				User preUser = userService.getUserById(user.getUser_id());
				if(preUser == null) preUser = new User();
				log.setEntity_id(user.getUser_id());
				log.setEntity_name(user.getUser_name()==null?preUser.getUser_name():user.getUser_name());
				
				List<LogDetail> details = PubMethod.getLogDetails(log,User.class, preUser,user);				
				log.setDetails(details);
				logs.add(log);
			}
			
		}else {
			
			Log log = super.getLog(methodAnnotation, invocation,sessionUser );
			User user = (User)invocation.getArguments()[0];
			
			User preUser = userService.getUserById(user.getUser_id());
			if(preUser == null) preUser = new User();
			log.setEntity_id(user.getUser_id());
			log.setEntity_name(user.getUser_name()==null?preUser.getUser_name():user.getUser_name());
			
			
			List<LogDetail> details = null;
			
			if(!LogConstant.OPERATION_UPDATEPWD.equals(methodAnnotation.operation_type()))
				details = PubMethod.getLogDetails(log,User.class, preUser,user);
			

			if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_INSERT)){
				List<String> roleIds = (List<String>)invocation.getArguments()[1];
				if(roleIds != null){
					for(String roleId : roleIds){
						LogDetail logDetail = new LogDetail();
						logDetail.setLog_detail_id(IDKit.generateId());
						logDetail.setLog_id(log.getLog_id());
						logDetail.setData_item_code("add_role");
						logDetail.setData_item_i18n("增加角色");
						Role role = roleService.getRole(roleId);
						if(role != null) logDetail.setOperation_after(role.getRole_name());
						details.add(logDetail);
					}
				}
			}else if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_UPDATE)){
				List<String> roleIds = (List<String>)invocation.getArguments()[1];
				if(roleIds != null){
					for(String roleId : roleIds){
						LogDetail logDetail = new LogDetail();
						logDetail.setLog_detail_id(IDKit.generateId());
						logDetail.setLog_id(log.getLog_id());
						logDetail.setData_item_code("add_role");
						logDetail.setData_item_i18n("增加角色");
						Role role = roleService.getRole(roleId);
						if(role != null) logDetail.setOperation_after(role.getRole_name());
						details.add(logDetail);
					}
				}
				

				roleIds = (List<String>)invocation.getArguments()[2];
				if(roleIds != null){
					for(String roleId : roleIds){
						LogDetail logDetail = new LogDetail();
						logDetail.setLog_detail_id(IDKit.generateId());
						logDetail.setLog_id(log.getLog_id());
						logDetail.setData_item_code("delete_role");
						logDetail.setData_item_i18n("删除角色");
						Role role = roleService.getRole(roleId);
						if(role != null) logDetail.setOperation_before(role.getRole_name());
						details.add(logDetail);
					}
				}				
			}
			
			log.setDetails(details);
			logs.add(log);
		}
		
		return logs;
	}
	
	
	
	
	

}
