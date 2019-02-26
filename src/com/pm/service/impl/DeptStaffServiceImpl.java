package com.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;import com.pm.domain.business.DeptStaff;
import com.pm.dao.IDeptStaffDao;
import com.pm.service.IDeptStaffService;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;

import java.util.List;

/**
 * @author Administrator
 */
@Component
public class DeptStaffServiceImpl implements  IDeptStaffService {

	@Autowired IDeptStaffDao deptStaffDao;
	@Override
	public int addDeptStaff(DeptStaff deptStaff) {
		return deptStaffDao.addDeptStaff(deptStaff);
	}

	@Override
	public int updateDeptStaff(DeptStaff deptStaff) {
		return deptStaffDao.updateDeptStaff(deptStaff);
	}

	@Override
	public void deleteDeptStaff(DeptStaff[] deptStaffs) {
		for(DeptStaff deptStaff : deptStaffs){
			deptStaffDao.deleteDeptStaff(deptStaff);
		}
	}

	@Override
	public void verifyDeptStaff(DeptStaff deptStaff) {
		deptStaffDao.verifyDeptStaff(deptStaff);
	}


	@Override
	public DeptStaff getDeptStaff(String id) {
		return deptStaffDao.getDeptStaff(id);
	}

	@Override
	public List<DeptStaff> getDeptStaffs(DeptStaff deptStaff){
		return deptStaffDao.getDeptStaffs(deptStaff);
	}

	@Override
	public Pager<DeptStaff> queryDeptStaff(
		DeptStaff deptStaff,
		UserPermit userPermit,
		Pager<DeptStaff> pager){

		return deptStaffDao.queryDeptStaff(deptStaff, userPermit, pager);
	}


	@Override
	public <T> T get(String id) {
		return (T)getDeptStaff(id);
	}
}