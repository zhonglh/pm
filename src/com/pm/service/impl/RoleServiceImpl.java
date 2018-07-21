package com.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.pm.dao.IRoleDao;
import com.pm.domain.system.Permit;
import com.pm.domain.system.Role;
import com.pm.domain.system.User;
import com.pm.service.IRoleService;
import com.pm.vo.UserPermit;

@Component
public class RoleServiceImpl implements IRoleService {

	@Autowired IRoleDao roleDao;

	@Override
	public int addRole(Role role,List<String> permits) {
		
		return roleDao.addRole(role,permits);
		
	}

	@Override
	public int updateRole(Role role,List<String> addPermits,List<String> deletePermits) {
		return roleDao.updateRole(role,addPermits,deletePermits); 
	}

	@Override
	public void deleteRole(Role[] roles) {	
		roleDao.deleteRole(roles); 
	}

	@Override
	public Role getRole(String role_id) {
		
		return roleDao.getRole(role_id);
	}

	@Override
	public List<Role> queryRole(Role role, UserPermit userPermit){
		
		return roleDao.queryRole(role, userPermit);
	}
	

	public boolean isExist(Role role) {
		return roleDao.isExist(role);
	}
	

	@Override
	public List<Role> queryRoleByUser(User user) 	{
		return roleDao.queryRoleByUser(user);
	}

	@Override
	public List<Permit> getAllPermics() {		
		return roleDao.getAllPermics();
	}

	@Override
	public List<Permit> getPermicsByRole(Role role) {		
		return roleDao.getPermicsByRole(role);
	}
	

	@Override
	public List<UserPermit> queryPermitsByUser(User user){
		return roleDao.queryPermitsByUser(user);
	}

}
