package com.pm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.IDeptDao;
import com.pm.domain.system.Dept;
import com.pm.vo.UserPermit;


@Component
public class DeptDaoImpl extends BatisDao  implements IDeptDao {


	@Override
	public int addDept(Dept dept) {
		String sql = "DeptMapping.addDept";
		return this.insert(sql, dept);

	}

	@Override
	public int updateDept(Dept dept) {

		String sql = "DeptMapping.updateDept";
		return this.update(sql, dept);

	}

	@Override
	public void deleteDept(Dept[] depts) {

		String sql = "DeptMapping.deleteDept";
		for(Dept dept : depts)
		this.update(sql, dept);

	}

	@Override
	public Dept getDept(String dept_id) {

		String sql = "DeptMapping.getDept";
		Dept dept = new Dept();
		dept.setDept_id(dept_id);
		List<Dept> list = this.query(sql, Dept.class, dept);
		if(list != null && !list.isEmpty()) return list.get(0);
		else return null;
	}
	

	@Override
	public List<Dept> getAllDept(Dept dept,UserPermit userPermit) {
		String sql = "DeptMapping.getAllDept";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(UserPermit.class.getSimpleName(), userPermit);
		map.put(Dept.class.getSimpleName(), dept);
		return this.query(sql, Dept.class,map);
	}

	@Override
	public Pager<Dept> queryDept(Dept dept, UserPermit userPermit,Pager<Dept> pager) {
		String sql = "DeptMapping.queryDept";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Dept.class, dept,userPermit);
	}
	
	

	@Override
	public boolean isExist(Dept searchDept){
		String sql = "DeptMapping.isExist";
		List<Dept> list = this.query(sql, Dept.class, searchDept);
		if(list != null && !list.isEmpty()) return true;
		else return false;
	}

}
