package com.pm.dao;

import java.util.List;

import com.common.beans.Pager;
import com.pm.domain.system.Dept;
import com.pm.vo.UserPermit;

public interface IDeptDao {
	


	
	public int addDept(Dept dept) ;  
	

	public int updateDept(Dept dept) ; 
	

	public void deleteDept(Dept[] depts) ; 
	

	public Dept getDept(String dept_id) ;
	

	public List<Dept> getAllDept(Dept dept,UserPermit userPermit) ;	
	
	
	public Pager<Dept> queryDept(Dept dept, UserPermit userPermit,Pager<Dept> pager);
	

	public boolean isExist(Dept searchDept);

}
