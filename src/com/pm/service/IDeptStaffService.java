package com.pm.service;

import com.pm.domain.business.DeptStaff;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;
import com.pm.service.IGeneralLogService;

import java.util.List;

/**
 * @author Administrator
 */
public interface IDeptStaffService extends IGeneralLogService {

	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_DEPT_STAFF)
	public int addDeptStaff(DeptStaff deptStaff) ;

	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_DEPT_STAFF)
	public int updateDeptStaff(DeptStaff deptStaff) ; 

	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.ENTITY_DEPT_STAFF)
	public void deleteDeptStaff(DeptStaff[] deptStaffs) ;

	/**
	 * 删除人员的部门经历，仅在删除用户时调用
	 * @param deptStaff
	 */
	public void deleteDeptStaffByOtherStaff(DeptStaff deptStaff) ;

	@LogAnnotation(operation_type=LogConstant.OPERATION_CHECK,entity_type=LogConstant.ENTITY_DEPT_STAFF)
	public void verifyDeptStaff(DeptStaff deptStaff) ;	

	public DeptStaff getDeptStaff(String id) ;

	public List<DeptStaff> getDeptStaffs(DeptStaff deptStaff);

	public Pager<DeptStaff> queryDeptStaff(DeptStaff deptStaff, UserPermit userPermit, Pager<DeptStaff> pager);

}