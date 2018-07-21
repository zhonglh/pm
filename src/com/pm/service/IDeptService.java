package com.pm.service;

import java.util.List;

import com.common.beans.Pager;
import com.pm.domain.system.Dept;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;
import com.pm.vo.UserPermit;

public interface IDeptService {
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_DEPT)
	public int addDept(Dept dept) ;  
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_DEPT)
	public int updateDept(Dept dept) ; 
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.ENTITY_DEPT)
	public void deleteDept(Dept[] depts) ; 
	

	public Dept getDept(String dept_id) ;
	

	public List<Dept> getAllDept(Dept dept,UserPermit userPermit) ;


	public Pager<Dept> queryDept(Dept dept, UserPermit userPermit,Pager<Dept> pager);	
	
	public boolean isExist(Dept searchDept);
	

}
