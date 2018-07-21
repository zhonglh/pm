package com.pm.service;

import java.util.List;

import com.common.beans.Pager;
import com.pm.domain.system.Menu;
import com.pm.domain.system.User;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;
import com.pm.vo.UserPermit;

public interface IUserService {
	

	public User getUserById(String userId) ;
	public User getUserByLoginName(String loginName) ;
	public Pager<User> queryUser(User user, UserPermit userPermit , Pager<User> pager) ;
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_USER)
	public boolean insertUser(User user, List<String> role_ids);
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_USER)
	public boolean updateUser(User user,List<String> addRole_ids,List<String> deleteRole_ids);
	
	/**
	 * 雇员名称或者部门修改后，同时修改用户信息中对应的内容
	 * @param username
	 * @param dept_id
	 * @param staff_id
	 * @return
	 */
	public boolean updateUser(String username, String dept_id, String staff_id,String staff_type);
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATEPWD,entity_type=LogConstant.ENTITY_USER)
	public boolean updatePassword(User user);
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.ENTITY_USER)
	public boolean deleteUser(User[] users);
	

	public List<Menu> queryMenusByUserId(User user) ;
	
	public boolean isExist(User user);
	

}
