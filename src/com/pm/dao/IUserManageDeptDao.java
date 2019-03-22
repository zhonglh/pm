package com.pm.dao;

import com.pm.domain.system.UserManageDept;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;

public interface IUserManageDeptDao {

	public int addUserManageDept(UserManageDept userManageDept) ;

	public int updateUserManageDept(UserManageDept userManageDept) ; 

	public void deleteUserManageDept(UserManageDept userManageDept) ;

	public void deleteUserManageDeptByUser(String user_id) ;

	public UserManageDept getUserManageDept(String id) ;	

	public Pager<UserManageDept> queryUserManageDept(UserManageDept userManageDept, UserPermit userPermit, Pager<UserManageDept> pager);

}