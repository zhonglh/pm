package com.pm.service;

import com.common.beans.Pager;
import com.pm.domain.system.UserManageDept;
import com.pm.vo.UserPermit;

import java.util.List;

public interface IUserManageDeptService  {

	/**
	 * 先删除用户管理的部门， 在插入多条用户管理部门的数据
	 * @param user_id
	 * @param dept_ids
	 * @return
	 */
	public void saveUserManageDep(String user_id , List<String> dept_ids) ;

	public int addUserManageDept(UserManageDept userManageDept) ;

	public void deleteUserManageDept(UserManageDept[] userManageDepts) ;
	public UserManageDept getUserManageDept(String id) ;	

	public Pager<UserManageDept> queryUserManageDept(UserManageDept userManageDept, UserPermit userPermit, Pager<UserManageDept> pager);

}