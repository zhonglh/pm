package com.pm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.IUserDao;
import com.pm.domain.system.Menu;
import com.pm.domain.system.User;
import com.pm.util.PubMethod;
import com.pm.vo.UserPermit;


@Component
public class UserDaoImpl extends BatisDao implements IUserDao{

	@Override
	public User getUserById(String userId) {
	      String sql = "UserMapping.getUserById";
	      User user = new User();
	      user.setUser_id(userId);
	      List<User> list = this.query (sql, User.class, user);
		if(list != null && list.size()>0) return list.get(0);
		else return null;
	}

	@Override
	public User getUserByLoginName(String loginName) {
	      String sql = "UserMapping.getUserByLoginName";
	      User user = new User();
	      user.setUser_loginname(loginName);
	      List<User> list = this.query (sql, User.class, user);
		if(list != null && list.size()>0) return list.get(0);
		else return null;
	}

	@Override
	public Pager<User> queryUser(User user, UserPermit userPermit, Pager<User> pager) {
	      String sql = "UserMapping.queryUser";	      
	      Pager<User> pager1 = this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, User.class, user,userPermit);	      
	      return pager1;
	}

	@Override
	public boolean insertUser(User user,List<String> role_ids) {
	      String sql = "UserMapping.insertUser";
	      this.insert(sql, user);
	      
	      if(role_ids != null){
	      	sql = "UserMapping.insertUserRole";
	      	for(String role_id : role_ids){
	      		user.setRole_id(role_id);
	      		this.insert(sql, user);
	      	}
	      }
	      
		return true;
	}

	@Override
	public boolean updateUser(User user,List<String> addRole_ids,List<String> deleteRole_ids) {
	      String sql = "UserMapping.updateUser";
	      this.update(sql, user);
	      
	      if(addRole_ids != null){
	      	sql = "UserMapping.insertUserRole";
	      	for(String role_id : addRole_ids){
	      		user.setRole_id(role_id);
	      		this.insert(sql, user);
	      	}
	      }

	      if(deleteRole_ids != null){
	      	sql = "UserMapping.deleteUserRole";
	      	for(String role_id : deleteRole_ids){
	      		user.setRole_id(role_id);
	      		this.delete(sql, user);
	      	}
	      }	      
	    
		return true;
	}
	

	@Override
	public boolean updateUser(String username, String dept_id, String staff_id,String staff_type){
		String sql = "UserMapping.updateUserByStaff";
		User user = new User();
		user.setUser_name(username);
		user.setUser_deptid(dept_id);
		user.setStaff_id(staff_id);
		user.setStaff_type(staff_type);
		this.update(sql, user);
		return true;
	}

	@Override
	public boolean updatePassword(User user) {
      String sql = "UserMapping.updatePassword";
      this.update(sql, user);
		return true;
	}

	@Override
	public boolean deleteUser(User user) {
	      String sql = "UserMapping.deleteUser";
	      this.update(sql, user);
		return true;
	}
	

	@Override
	public boolean deleteUser(String staff_id, String staff_type, String deleteUserId, String deletUserName){
		User user = new User();
		user.setStaff_id(staff_id);
		user.setStaff_type(staff_type);
		user.setDelete_userid(deleteUserId);
		user.setDelete_username(deletUserName);
		user.setDelete_datetime(PubMethod.getCurrentDate());
		String sql = "UserMapping.deleteUserByStaff";
		this.update(sql, user);
		return true;
	}
	
	

	@Override
	public List<Menu> queryMenusByUserId(User user) {

	      String sql = "MenuMapping.queryMenusByUserId";
	      List<Menu> list = this.query (sql, Menu.class, user);
	      return list;
	      
	}
	
	

	@Override
	public boolean isExist(User user) {
	      String sql = "UserMapping.isExist";
	      List<User> list = this.query (sql, User.class, user);
	      return (list == null || list.isEmpty()) ? false : true;
	}

	
	

}
