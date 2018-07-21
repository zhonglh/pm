package com.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.pm.dao.IUserDao;
import com.pm.domain.system.Menu;
import com.pm.domain.system.User;
import com.pm.service.IUserService;
import com.pm.vo.UserPermit;

@Component
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private IUserDao userDao;


	public User getUserById(String userId){
		return userDao.getUserById(userId);
	}
	public User getUserByLoginName(String loginName) {
		return userDao.getUserByLoginName(loginName);
	}
	public Pager<User> queryUser(User user, UserPermit userPermit , Pager<User> pager) {
		return userDao.queryUser(user, userPermit, pager);
	}
	
	public boolean insertUser(User user,List<String> role_ids){
		return userDao.insertUser(user,role_ids);
	}


	public boolean updateUser(User user,List<String> addRole_ids,List<String> deleteRole_ids){
		return userDao.updateUser(user,addRole_ids,deleteRole_ids);		
	}
	

	public boolean updateUser(String username, String dept_id, String staff_id,String staff_type){
		return userDao.updateUser(username,dept_id,staff_id,staff_type);	
	}

	public boolean updatePassword(User user){
		return userDao.updatePassword(user);
	}
	

	public boolean deleteUser(User[] users){
		for(User user : users)
			userDao.deleteUser(user);
		return true;
	}
	

	public List<Menu> queryMenusByUserId(User user) {
		return userDao.queryMenusByUserId(user);
	}
	
	@Override
	public boolean isExist(User user) {
		return userDao.isExist(user);
	}

}
