package com.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.pm.dao.IDeptDao;
import com.pm.domain.system.Dept;
import com.pm.service.IDeptService;
import com.pm.vo.UserPermit;


@Component
public class DeptServiceImpl implements IDeptService {

	@Autowired IDeptDao deptDao;
	
	@Override
	public int addDept(Dept dept) {
		return deptDao.addDept(dept);

	}

	@Override
	public int  updateDept(Dept dept) {
		return deptDao.updateDept(dept);

	}

	@Override
	public void deleteDept(Dept[] depts) {
		deptDao.deleteDept(depts);

	}

	@Override
	public Dept getDept(String dept_id) {
		return deptDao.getDept(dept_id);
	}
	

	@Override
	public List<Dept> getAllDept(Dept dept,UserPermit userPermit) {
		return deptDao.getAllDept(dept,userPermit);
	}

	@Override
	public Pager<Dept> queryDept(Dept dept, UserPermit userPermit,Pager<Dept> pager) {
		return deptDao.queryDept(dept, userPermit,pager);
	}

	@Override
	public boolean isExist(Dept searchDept) {
		return deptDao.isExist(searchDept);
	}

}
