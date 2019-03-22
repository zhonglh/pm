package com.pm.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.enums.EnumYesNo;
import com.pm.domain.system.UserManageDept;
import com.pm.service.IDeptService;
import com.pm.service.IUserManageDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.actions.BaseAction;
import com.common.beans.Pager;
import com.common.utils.IDKit;
import com.common.utils.encrypt.MD5Kit;
import com.pm.domain.system.Dept;
import com.pm.domain.system.Role;
import com.pm.domain.system.User;
import com.pm.service.IRoleService;
import com.pm.service.IUserService;
import com.pm.util.PubMethod;
import com.pm.util.constant.BusinessUtil;
import com.pm.util.constant.EnumOperationType;
import com.pm.util.constant.EnumPermit;
import com.pm.vo.UserPermit;

@Controller
@RequestMapping(value = "UserAction.do")
public class UserAction extends BaseAction {
	
	
	private static final String rel = "rel50";
	
	@Autowired
	private IUserService userService;


	@Autowired
	private IRoleService roleService;

	@Autowired
	private IUserManageDeptService userManageDeptService;

	@Autowired
	private IDeptService deptService;
	


	@RequestMapping(params = "method=isExist")
	public String isExist(User user,HttpServletResponse res,HttpServletRequest request){

		String error = null;	
		boolean b = userService.isExist(user);
		if(b){
			error = "该登陆名称已经存在";
		}		
		
		if(!b){
			return this.ajaxForwardSuccess(request);
		}else {
			return this.ajaxForwardError(request, error);
		}
	}

	@RequestMapping(params = "method=search")
	public String search(HttpServletResponse res,HttpServletRequest request){
		
		User searchUser = new User();
		searchUser.setUser_deptid(request.getParameter("dept.dept_id"));
		searchUser.setUser_name(request.getParameter("user_name"));
		
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.USERVIEW.getId());		
		
		Pager<User> pager = userService.queryUser(searchUser, userPermit, PubMethod.getPager(request,User.class));
		PubMethod.setRequestPager(request, pager,User.class);
		request.setAttribute("dept_id", request.getParameter("dept.dept_id"));
		request.setAttribute("dept_name", request.getParameter("dept.dept_name"));
		return "system/user_search";
	}
	
		
	@RequestMapping(params = "method=list")
	public String list(HttpServletResponse res,HttpServletRequest request){
		
		User searchUser = new User();
		searchUser.setUser_deptid(request.getParameter("dept.dept_id"));
		searchUser.setUser_name(request.getParameter("user_name"));
		
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.USERVIEW.getId());
		
		Pager<User> pager = userService.queryUser(searchUser, userPermit, PubMethod.getPager(request,User.class));
		PubMethod.setRequestPager(request, pager,User.class);
		
		request.setAttribute("dept_id", request.getParameter("dept.dept_id"));
		request.setAttribute("dept_name", request.getParameter("dept.dept_name"));
		

		request.setAttribute(EnumOperationType.READ.getKey(), userPermit.getPermit_id());

		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.USERADD.getId());
		request.setAttribute(EnumOperationType.INSERT.getKey(), userPermit1.getPermit_id());
		
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.USERUPDATE.getId());
		request.setAttribute(EnumOperationType.UPDATE.getKey(), userPermit1.getPermit_id());	

		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.USERDELETE.getId());
		request.setAttribute(EnumOperationType.DELETE.getKey(), userPermit1.getPermit_id());

		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.USERCHANGEPWD.getId());
		request.setAttribute(EnumOperationType.UPDATEPWD.getKey(), userPermit1.getPermit_id());
		
		
		return "system/user_list";
		
	}
	

	private void paramprocess(User user,HttpServletRequest request){
		if(user.getUser_deptid() == null){
			user.setUser_deptid(request.getParameter("staff.dept_id"));
			user.setUser_deptname(request.getParameter("staff.dept_name"));
		}
		if(user.getUser_name() == null){
			user.setUser_name(request.getParameter("staff.user_name"));
		}
		
		if(user.getStaff_id() == null){
			user.setStaff_id(request.getParameter("staff.staff_id"));
			user.setStaff_type(request.getParameter("staff.staff_type"));
		}
	}

	@RequestMapping(params = "method=add")
	public String add(User user,HttpServletResponse res,HttpServletRequest request){
		
		User sessionUser = PubMethod.getUser(request);
		user.setUser_id(IDKit.generateId());
		user.setBuild_time(PubMethod.getCurrentDate());
		user.setBuild_userid(sessionUser.getUser_id());
		user.setBuild_username(sessionUser.getUser_name());
		user.setDelete_flag(BusinessUtil.NOT_DELETEED);

		
		paramprocess(user,request);
		

		
		if(user.getUser_password() != null) user.setUser_password(MD5Kit.md5(user.getUser_password()));

		List<String> role_ids = PubMethod.getList(request.getParameterValues("role_id"), String.class); 
		
		

		boolean b = false;
		try{
			b = userService.insertUser(user,role_ids);
				
		}catch(Exception e){
			
		}
		if(b) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "用户登录名重复或数据格式错误！", true);
		
		
	}
	

	@RequestMapping(params = "method=update")
	public String update(User user,HttpServletResponse res,HttpServletRequest request){		

		paramprocess(user,request);
		
		List<String> role_ids = PubMethod.getList(request.getParameterValues("role_id"), String.class); 
		
		List<Role> userRoles = roleService.queryRoleByUser(user);
		List<String> userRoleIds = new ArrayList<String>();
		if(userRoles != null){
			for(Role role : userRoles){
				userRoleIds.add(role.getRole_id());
			}
		}
		
		
		
		boolean b = false;
		try{
			b = userService.updateUser(user, PubMethod.getDifferenceSets(userRoleIds, role_ids), PubMethod.getDifferenceSets(role_ids,userRoleIds ));
				
		}catch(Exception e){
			
		}
		if(b) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "用户登录名重复或数据格式错误！", true);
		
		
	}



	@RequestMapping(params = "method=toEdit")
	public String toEdit(User user , HttpServletResponse res,HttpServletRequest request){
		
		User user1 = null;
		List<Role> roles = roleService.queryRole(null, null);
		if(roles == null) roles = new ArrayList<Role>();
		
		if(user != null && user.getUser_id()!=null){
			request.setAttribute("next_operation", "update");
			user1 = userService.getUserById(user.getUser_id());
			
			if("1".equals(user1.getUser_id())){
				return this.ajaxForwardError(request, "系统用户不允许修改！",true);
			}else if("1".equals(user1.getDelete_flag())){
				return this.ajaxForwardError(request, "该用户已经删除，不能再操作！",true);
			}
			
		}else {
			request.setAttribute("next_operation", "add");
		}
		if(user1 == null) user1 = new User();
		
		if(user1.getUser_id() != null){
			List<Role> userRoles = roleService.queryRoleByUser(user1);			
			if(userRoles != null && !userRoles.isEmpty()){
				Map<String,Role> userRoleMap = new HashMap<String,Role>();
				for(Role role : userRoles){
					userRoleMap.put(role.getRole_id(), role);
				}
				for(Role role : roles){
					if(userRoleMap.containsKey(role.getRole_id())) role.setSelected(true);
				}
			}
		}
		
		request.setAttribute("user1", user1);
		request.setAttribute("roles", roles);
		return "system/user_edit";
	}
	
	

	@RequestMapping(params = "method=toView")
	public String toView(User user , HttpServletResponse res,HttpServletRequest request){
		
		User user1 = userService.getUserById(user.getUser_id());
		List<Role> userRoles = roleService.queryRoleByUser(user1);	

		request.setAttribute("user1", user1);
		request.setAttribute("roles", userRoles);
		return "system/user_view";
	}
		

	

	@RequestMapping(params = "method=toUpdatePassword")
	public String toEditPassword(User user,HttpServletResponse res,HttpServletRequest request){
		return "system/change_pwd";		
	}			
	
	@RequestMapping(params = "method=updatePassword")
	public String updatePassword(User user,HttpServletResponse res,HttpServletRequest request){
		
		String oldPassword = request.getParameter("oldPassword");
		String md5Password = null;
		if(oldPassword != null) md5Password = MD5Kit.md5(oldPassword);
		
		
		User sessionUser = PubMethod.getUser(request);
		if(!md5Password.equals(sessionUser.getUser_password()))
			return this.ajaxForwardError(request, "操作失败,用户旧密码错误！");
		
		user.setUser_id(sessionUser.getUser_id());
		user.setUser_password(MD5Kit.md5(user.getUser_password()));
		
		userService.updatePassword(user);

		return this.ajaxForwardSuccess(request);
	}			
	

	@RequestMapping(params = "method=toAdminUpdatePassword")
	public String toAdminUpdatePassword(User user,HttpServletResponse res,HttpServletRequest request){
		

		User user1 = userService.getUserById(user.getUser_id());
		String permission = permission(request,user1);
		if(permission != null) return permission;		
		
		return "system/admin_change_pwd";
		
	}
	
	@RequestMapping(params = "method=adminUpdatePassword")
	public String adminUpdatePassword(User user,HttpServletResponse res,HttpServletRequest request){
		

		User user1 = userService.getUserById(user.getUser_id());
		String permission = permission(request,user1);
		if(permission != null) return permission;		

		user.setUser_password(MD5Kit.md5(user.getUser_password()));
		userService.updatePassword(user);

		return this.ajaxForwardSuccess(request);
	}			
	

	@RequestMapping(params = "method=delete")
	public String delete(HttpServletResponse res,HttpServletRequest request){
		
		User sessionUser = PubMethod.getUser(request);
		java.sql.Timestamp deleteDate = PubMethod.getCurrentDate();
		
		
		String[] user_ids = request.getParameterValues("ids");
		if(user_ids != null && user_ids.length > 0){
			User[] users = new User[user_ids.length];
			int index = 0;
			for(String user_id : user_ids){
				User user = new User();
				user.setUser_id(user_id);
				user.setDelete_userid(sessionUser.getUser_id());
				user.setDelete_username(sessionUser.getUser_name());
				user.setDelete_datetime(deleteDate);
				users[index] = user;
				index ++ ;
			}

			userService.deleteUser(users);
		}
		return this.ajaxForwardSuccess(request,rel,false);
	}






	/**
	 * 到管理部门页面
	 * @param user
	 * @param res
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=toManageDept")
	public String toManageDept(User user , HttpServletResponse res,HttpServletRequest request){

		User user1 = null;


		if(user != null && user.getUser_id()!=null){
			request.setAttribute("next_operation", "update");
			user1 = userService.getUserById(user.getUser_id());

			if("1".equals(user1.getUser_id())){
				return this.ajaxForwardError(request, "系统用户不允许修改！",true);
			}else if("1".equals(user1.getDelete_flag())){
				return this.ajaxForwardError(request, "该用户已经删除，不能再操作！",true);
			}

		}else {
			return this.ajaxForwardError(request, "系统出现错误！",true);
		}


		UserPermit userPermit = this.getAllPermit();
		Dept dept = new Dept();
		dept.setDelete_flag(EnumYesNo.No.getCode());
		List<Dept> depts = deptService.getAllDept(dept, userPermit);

		if(depts == null) depts = new ArrayList<Dept>();

		UserManageDept userManageDept = new UserManageDept();
		userManageDept.setUser_id(user.getUser_id());
		Pager<UserManageDept> pager = userManageDeptService.queryUserManageDept(userManageDept , null  , PubMethod.getPagerByAll(UserManageDept.class));

		if(pager != null && pager.getResultList() != null && !pager.getResultList().isEmpty()){
			Map<String,UserManageDept> map = new HashMap<String,UserManageDept>();
			for(UserManageDept umd : pager.getResultList()){
				map.put(umd.getDept_id(), umd);
			}
			for(Dept d : depts){
				if(map.containsKey(d.getDept_id())){
					d.setSelected(true);
				}
			}
		}

		request.setAttribute("user1", user1);
		request.setAttribute("depts", depts);
		return "system/manage_dept_edit";
	}


	@RequestMapping(params = "method=saveManageDept")
	public String saveManageDept(User user,HttpServletResponse res,HttpServletRequest request){


		paramprocess(user,request);

		List<String> dept_ids = PubMethod.getList(request.getParameterValues("dept_id"), String.class);




		try{
			userManageDeptService.saveUserManageDep(user.getUser_id() , dept_ids);
			return this.ajaxForwardSuccess(request, rel, true);
		}catch(Exception e){
			return this.ajaxForwardError(request, "出现错误！", true);
		}
	}


	
	
	private String permission(HttpServletRequest request,User user1){
		if(user1 == null) return null;
		if("1".equals(user1.getDelete_flag())){
			return this.ajaxForwardError(request, "该用户已经删除，不能再操作！");
		}
		
		return null;
	}	
	

}
