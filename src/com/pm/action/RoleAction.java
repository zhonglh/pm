package com.pm.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.actions.BaseAction;
import com.common.utils.IDKit;
import com.pm.domain.system.Permit;
import com.pm.domain.system.Role;
import com.pm.domain.system.User;
import com.pm.service.IRoleService;
import com.pm.util.PermitComparator;
import com.pm.util.PubMethod;
import com.pm.util.constant.BusinessUtil;
import com.pm.util.constant.EnumOperationType;
import com.pm.util.constant.EnumPermit;
import com.pm.vo.UserPermit;


@Controller
@RequestMapping(value = "RoleAction.do")
public class RoleAction extends BaseAction {
	

	private static final String rel = "rel52";
	
	@Autowired
	private IRoleService roleService;
	
	

	@RequestMapping(params = "method=isExist")
	public String isExist(Role role,HttpServletResponse res,HttpServletRequest request){

		String error = null;	
		boolean b = roleService.isExist(role);
		if(b){
			error = "该角色名称已经存在";
		}		
		
		if(!b){
			return this.ajaxForwardSuccess(request);
		}else {
			return this.ajaxForwardError(request, error);
		}
	}	
	

	@RequestMapping(params = "method=list")
	public String list(HttpServletResponse res,HttpServletRequest request){
		
		Role searchRole = new Role();
		searchRole.setRole_name(request.getParameter("role_name"));
		
		List<Role> list = roleService.queryRole(searchRole, null);
		request.setAttribute("list", list);
		


		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.ROLEADD.getId());
		request.setAttribute(EnumOperationType.INSERT.getKey(), userPermit1.getPermit_id());
		
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.ROLEUPDATE.getId());
		request.setAttribute(EnumOperationType.UPDATE.getKey(), userPermit1.getPermit_id());	

		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.ROLEDELETE.getId());
		request.setAttribute(EnumOperationType.DELETE.getKey(), userPermit1.getPermit_id());			

		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.ROLEVIEW.getId());
		request.setAttribute(EnumOperationType.READ.getKey(), userPermit1.getPermit_id());
		
		return "system/role_list";
		
	}
	

	@RequestMapping(params = "method=add")
	public String add(Role role,HttpServletResponse res,HttpServletRequest request){
		
		User sessionUser = PubMethod.getUser(request);
		if(role == null) role = new Role();
		role.setRole_id(IDKit.generateId());
		role.setRole_type(BusinessUtil.ROLE_TYPE_GENERAL);
		role.setBuild_time(PubMethod.getCurrentDate());
		role.setBuild_userid(sessionUser.getUser_id());
		role.setBuild_username(sessionUser.getUser_name());
		role.setDelete_flag(BusinessUtil.NOT_DELETEED);
		
		String[] permit_ids = request.getParameterValues("permit_id");
		List<String> permitList = PubMethod.getList(permit_ids, String.class);
		
		int count = 0;
		try{
			count = roleService.addRole(role, permitList);		
		}catch(Exception e){
			
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "数据格式错误！", true);
		
	}
	

	@RequestMapping(params = "method=update")
	public String update(Role role,HttpServletResponse res,HttpServletRequest request){
		String[] permit_ids = request.getParameterValues("permit_id");
		List<String> permitIdList = PubMethod.getList(permit_ids, String.class);
		
		List<Permit> rolePermits = roleService.getPermicsByRole(role);
		List<String> dbPermitIdList = null;
		if(rolePermits != null && !rolePermits.isEmpty()){
			dbPermitIdList = new ArrayList<String>();
			for(Permit permit : rolePermits){
				dbPermitIdList.add(permit.getPermit_id());
			}
		}
		
		int count = 0;
		try{
			count = roleService.updateRole(role, PubMethod.getDifferenceSets(dbPermitIdList, permitIdList), PubMethod.getDifferenceSets(permitIdList,dbPermitIdList ));
				
		}catch(Exception e){
			
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "数据格式错误！", true);
	}


	@RequestMapping(params = "method=toEdit")
	public String toEdit(Role role,HttpServletResponse res,HttpServletRequest request){
		Role role1 = null;
		if(role != null && role.getRole_id() !=null){
			role1 = roleService.getRole(role.getRole_id());
			request.setAttribute("next_operation", "update");
		}else {
			request.setAttribute("next_operation", "add");
		}
		
		if(role1 != null && role1.getRole_id() != null){
			if(BusinessUtil.ROLE_TYPE_SYSTEM.equals( role1.getRole_type()))
			return this.ajaxForwardError(request, "系统角色不允许操作！",true);
		}
		
		if(role1 == null) role1 = new Role();
		if(role1.getRole_id() != null){
			List<Permit> list = roleService.getPermicsByRole(role1);
			if(list!=null){
				role1.setPermit_ids(new ArrayList<String>());
				for(Permit permit : list){
					role1.getPermit_ids().add(permit.getPermit_id());
				}
			}
		}
		
		List<Permit> permits = roleService.getAllPermics();
		Map<String,List<Permit>> permitsMap = new HashMap<String,List<Permit>>();
		for(Permit permit : permits){
			if(!permitsMap.containsKey(permit.getGroup_code_i18n())){
				List<Permit> list = new ArrayList<Permit>();
				permitsMap.put(permit.getGroup_code_i18n(), list);
			}
			if(!permitsMap.get(permit.getGroup_code_i18n()).isEmpty()){
				String permitFirstCode = permit.getPermit_id().substring(0,2);
				String permitEndCode = permitsMap.get(permit.getGroup_code_i18n()).
						get(permitsMap.get(permit.getGroup_code_i18n()).size()-1).getPermit_id().substring(0,2);
				if(!permitFirstCode.equals(permitEndCode))
					permitsMap.get(permit.getGroup_code_i18n()).add(null);
			}
			if(role1.getPermit_ids() != null && role1.getPermit_ids().contains(permit.getPermit_id()))
				permit.setSelected(true);
			permitsMap.get(permit.getGroup_code_i18n()).add(permit);
		}
		
		List<List<Permit>> groups = new ArrayList<List<Permit>>();
		for(Entry<String,List<Permit>> entry : permitsMap.entrySet()){
			groups.add(entry.getValue());
		}
		
		Collections.sort(groups,new PermitComparator());
		
		request.setAttribute("groups", groups);
		request.setAttribute("role1", role1);
		
		
		return "system/role_edit";
		
	}	
	
	
	

	@RequestMapping(params = "method=toView")
	public String toView(Role role,HttpServletResponse res,HttpServletRequest request){
		Role role1 = roleService.getRole(role.getRole_id());
		

		List<Permit> permits = roleService.getPermicsByRole(role1);
		
		Map<String,List<Permit>> permitsMap = new HashMap<String,List<Permit>>();
		for(Permit permit : permits){
			if(!permitsMap.containsKey(permit.getGroup_code_i18n())){
				List<Permit> list = new ArrayList<Permit>();
				permitsMap.put(permit.getGroup_code_i18n(), list);
			}
			if(!permitsMap.get(permit.getGroup_code_i18n()).isEmpty()){
				String permitFirstCode = permit.getPermit_id().substring(0,2);
				String permitEndCode = permitsMap.get(permit.getGroup_code_i18n()).
						get(permitsMap.get(permit.getGroup_code_i18n()).size()-1).getPermit_id().substring(0,2);
				if(!permitFirstCode.equals(permitEndCode))
					permitsMap.get(permit.getGroup_code_i18n()).add(null);
			}
			permitsMap.get(permit.getGroup_code_i18n()).add(permit);
		}
		
		List<List<Permit>> groups = new ArrayList<List<Permit>>();
		for(Entry<String,List<Permit>> entry : permitsMap.entrySet()){
			groups.add(entry.getValue());
		}
		
		Collections.sort(groups,new PermitComparator());
		
		request.setAttribute("groups", groups);
		request.setAttribute("role1", role1);
		
		
		return "system/role_view";
		
	}		
	

	@RequestMapping(params = "method=deleteRole")
	public String deleteRole(HttpServletResponse res,HttpServletRequest request){
		String[] role_ids = request.getParameterValues("ids");
		List<Role> roles = new ArrayList<Role>();
		for(String role_id : role_ids){
			Role role = new Role();
			role.setRole_id(role_id);
			roles.add(role);
		}
		Role[] roleArray = new Role[roles.size()];
		for(int i=0;i<roles.size();i++){
			roleArray[i] = roles.get(i);
		}
		
		roleService.deleteRole( roleArray );

		return this.ajaxForwardSuccess(request, rel, false);
		
		
	}
	
}
