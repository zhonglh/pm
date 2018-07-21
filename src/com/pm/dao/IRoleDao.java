package com.pm.dao;

import java.util.List;

import com.pm.domain.system.Permit;
import com.pm.domain.system.Role;
import com.pm.domain.system.User;
import com.pm.vo.UserPermit;

public interface IRoleDao {



	public int addRole(Role role,List<String> permits) ;
	

	public int updateRole(Role role,List<String> addPermits,List<String> deletePermits) ;
	

	public void deleteRole(Role[] roles) ;
	

	public boolean isExist(Role role) ; 
	

	public Role getRole(String role_id) ;
	public List<Role> queryRole(Role role, UserPermit userPermit) ;	
	
	public List<Role> queryRoleByUser(User user)  ;
	
	
	public List<Permit> getAllPermics();
	

	public List<Permit> getPermicsByRole(Role role);


	public List<UserPermit> queryPermitsByUser(User user);
	
}
