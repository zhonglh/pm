package com.pm.service;

import java.util.List;

import com.pm.domain.system.Permit;
import com.pm.domain.system.Role;
import com.pm.domain.system.User;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;
import com.pm.vo.UserPermit;

public interface IRoleService {
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_ROLE)
	public int addRole(Role role, List<String> permits) ;  
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_ROLE)
	public int updateRole(Role role,List<String> addPermits, List<String> deletePermits) ; 
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.ENTITY_ROLE)
	public void deleteRole(Role[] roles) ; 
	

	public Role getRole(String role_id) ;
	public List<Role> queryRole(Role role, UserPermit userPermit) ;	
	
	
	public boolean isExist(Role role) ; 
	
	
	public List<Role> queryRoleByUser(User user) ;	
	
	public List<Permit> getAllPermics();
	

	public List<Permit> getPermicsByRole(Role role);
	
	
	public List<UserPermit> queryPermitsByUser(User user);

}
