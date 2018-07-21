package com.pm.dao;

import java.util.List;

import com.common.beans.Pager;
import com.pm.domain.system.Menu;
import com.pm.domain.system.User;
import com.pm.vo.UserPermit;

public interface IUserDao {
	

	public User getUserById(String userId) ;
	public User getUserByLoginName(String loginName) ;
	public Pager<User> queryUser(User user, UserPermit userPermit , Pager<User> pager) ;
	public boolean insertUser(User user,List<String> role_ids);
	public boolean updateUser(User user,List<String> addRole_ids,List<String> deleteRole_ids);
	public boolean updateUser(String username, String dept_id, String staff_id,String staff_type);
	public boolean updatePassword(User user);
	public boolean deleteUser(User User); 
	public boolean deleteUser(String staff_id,String staff_type,String deleteUserId, String deletUserName);
	

	public List<Menu> queryMenusByUserId(User user) ;

	public boolean isExist(User user);

}
