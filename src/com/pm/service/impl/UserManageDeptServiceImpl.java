package com.pm.service.impl;

import com.common.utils.IDKit;
import com.pm.domain.system.UserManageDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.pm.dao.IUserManageDeptDao;
import com.pm.service.IUserManageDeptService;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;

import java.util.List;

@Component
public class UserManageDeptServiceImpl implements  IUserManageDeptService {

	@Autowired IUserManageDeptDao userManageDeptDao;



	public void saveUserManageDep(String user_id , List<String> dept_ids) {
		userManageDeptDao.deleteUserManageDeptByUser(user_id);
		if(dept_ids != null && !dept_ids.isEmpty()){
			for(String dept_id : dept_ids){
				UserManageDept userManageDept = new UserManageDept();
				userManageDept.setId(IDKit.generateId());
				userManageDept.setUser_id(user_id);
				userManageDept.setDept_id(dept_id);
				userManageDeptDao.addUserManageDept(userManageDept);
			}
		}
	}

	@Override
	public int addUserManageDept(UserManageDept userManageDept) {
		return userManageDeptDao.addUserManageDept(userManageDept);
	}


	@Override
	public void deleteUserManageDept(UserManageDept[] userManageDepts) {
		for(UserManageDept userManageDept : userManageDepts){
			userManageDeptDao.deleteUserManageDept(userManageDept);
		}
	}


	@Override
	public UserManageDept getUserManageDept(String id) {
		return userManageDeptDao.getUserManageDept(id);
	}

	@Override
	public Pager<UserManageDept> queryUserManageDept(
		UserManageDept userManageDept,
		UserPermit userPermit,
		Pager<UserManageDept> pager){

		return userManageDeptDao.queryUserManageDept(userManageDept, userPermit, pager);
	}


}