package com.pm.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.IRoleDao;
import com.pm.domain.system.Permit;
import com.pm.domain.system.Role;
import com.pm.domain.system.User;
import com.pm.vo.UserPermit;


@Component
public class RoleDaoImpl extends BatisDao implements IRoleDao {
	

	@Override
	public int  addRole(Role role,List<String> permits) {
		
		String sql = "RoleMapping.addRole";
		int count = this.insert(sql, role);
		
		if(permits!=null){
			sql = "RoleMapping.addRolePermit";
			for(String permit_id : permits){
				role.setPermit_id(permit_id);
				this.insert(sql, role);
			}
		}
		return count;
	}

	@Override
	public int updateRole(Role role,List<String> addPermits,List<String> deletePermits) {
		
		String sql = "RoleMapping.updateRole";
		int count = this.update(sql, role);		

		if(addPermits!=null){
			sql = "RoleMapping.addRolePermit";
			for(String permit_id : addPermits){
				role.setPermit_id(permit_id);
				this.insert(sql, role);
			}
		}

		if(deletePermits!=null){
			sql = "RoleMapping.deleteRolePermit";
			for(String permit_id : deletePermits){
				role.setPermit_id(permit_id);
				this.delete(sql, role);
			}
		}
		
		return count;

	}

	@Override
	public void deleteRole(Role[] roles) {
		if(roles == null) return ;
		String sql = "RoleMapping.deleteRole";
		String permitSql = "RoleMapping.deleteAllRolePermit";
		for(Role role : roles){
			this.delete(permitSql, role);
			this.update(sql, role);
		}
	}
	
	

	@Override
	public boolean isExist(Role role) {
		String sql = "RoleMapping.isExist";
		List<Role> list = this.query(sql, Role.class, role);
		return (list == null || list.isEmpty()) ? false : true;
	}

	@Override
	public Role getRole(String role_id) {
		Role role = new Role();
		role.setRole_id(role_id);
		String sql = "RoleMapping.getRole";
		List<Role> list = this.query(sql, Role.class, role);
		if(list != null && !list.isEmpty()) {
			role = list.get(0);
			List<Permit> permits = this.getPermicsByRole(role);
			if(permits != null){
				role.setPermit_ids(new ArrayList<String>());
				for(Permit permit : permits){
					role.getPermit_ids().add(permit.getPermit_id());
				}
			}
			return role;
		}
		return null;
	}

	@Override
	public List<Role> queryRole(Role role, UserPermit userPermit) {
		String sql = "RoleMapping.queryRole";
		return this.query(sql, Role.class, role);
	}
	

	@Override
	public List<Role> queryRoleByUser(User user)  {
		String sql = "RoleMapping.queryRoleByUser";
		return this.query(sql, Role.class, user);
	}

	@Override
	public List<Permit> getAllPermics() {
			String sql = "RoleMapping.getAllPermics";
			return this.query(sql, Permit.class, null);		
	}

	@Override
	public List<Permit> getPermicsByRole(Role role) {
		String sql = "RoleMapping.getPermicsByRole";
		return this.query(sql, Permit.class, role);
	}
	
	@Override
	public List<UserPermit> queryPermitsByUser(User user){
		String sql = "RoleMapping.queryPermitsByUser";
		return this.query(sql, UserPermit.class, user);
	}

}
