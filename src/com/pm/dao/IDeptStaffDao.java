package com.pm.dao;

import com.pm.domain.business.DeptStaff;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;

import java.util.List;

public interface IDeptStaffDao {

	public int addDeptStaff(DeptStaff deptStaff) ;

	public int updateDeptStaff(DeptStaff deptStaff) ; 

	public void deleteDeptStaff(DeptStaff deptStaff) ;

	public void verifyDeptStaff(DeptStaff deptStaff) ;	

	public void unVerifyDeptStaff(DeptStaff deptStaff) ;

	public DeptStaff getDeptStaff(String id) ;

	public List<DeptStaff> getDeptStaffs(DeptStaff deptStaff) ;

	public Pager<DeptStaff> queryDeptStaff(DeptStaff deptStaff, UserPermit userPermit, Pager<DeptStaff> pager);

}