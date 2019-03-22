package com.pm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pm.domain.system.UserManageDept;
import org.springframework.stereotype.Component;

import com.pm.dao.IUserManageDeptDao;
import com.pm.vo.UserPermit;

import com.common.daos.BatisDao;
import com.common.beans.Pager;

@Component
public class UserManageDeptDaoImpl extends BatisDao implements IUserManageDeptDao  {

	@Override
	public int addUserManageDept(UserManageDept userManageDept) {
		String sql = "UserManageDeptMapping.addUserManageDept";
		return this.insert(sql, userManageDept);
	}

	@Override
	public int updateUserManageDept(UserManageDept userManageDept) {
		String sql = "UserManageDeptMapping.updateUserManageDept";
		return this.update(sql, userManageDept);
	}

	@Override
	public void deleteUserManageDept(UserManageDept userManageDept) {
		String sql = "UserManageDeptMapping.deleteUserManageDept";
		this.delete(sql, userManageDept);
	}


	public void deleteUserManageDeptByUser(String user_id) {
		UserManageDept userManageDept = new UserManageDept();
		userManageDept.setUser_id(user_id);
		String sql = "UserManageDeptMapping.deleteUserManageDeptByUser";
		this.delete(sql, userManageDept);
	}


	@Override
	public UserManageDept getUserManageDept(String id) {

		String sql = "UserManageDeptMapping.getUserManageDept"; 
		UserManageDept userManageDept = new UserManageDept(); 
		userManageDept.setId(id); 
		List<UserManageDept> list = this.query(sql, UserManageDept.class, userManageDept); 
		if(list == null || list.isEmpty()) return null; 
		else return list.get(0);	
	}

	@Override
	public Pager<UserManageDept> queryUserManageDept(
		UserManageDept userManageDept,
		UserPermit userPermit,
		Pager<UserManageDept> pager){

		String sql = "UserManageDeptMapping.queryUserManageDept"; 
		Pager<UserManageDept> pager1 =  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql,UserManageDept.class, userManageDept,userPermit); 

		return pager1;
	}


}