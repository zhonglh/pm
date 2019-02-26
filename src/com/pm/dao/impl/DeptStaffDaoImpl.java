package com.pm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.pm.dao.IDeptStaffDao;
import com.pm.domain.business.DeptStaff;
import com.pm.vo.UserPermit;

import com.common.daos.BatisDao;
import com.common.beans.Pager;

@Component
public class DeptStaffDaoImpl extends BatisDao implements IDeptStaffDao  {

	@Override
	public int addDeptStaff(DeptStaff deptStaff) {
		String sql = "DeptStaffMapping.addDeptStaff";
		return this.insert(sql, deptStaff);
	}

	@Override
	public int updateDeptStaff(DeptStaff deptStaff) {
		String sql = "DeptStaffMapping.updateDeptStaff";
		return this.update(sql, deptStaff);
	}

	@Override
	public void deleteDeptStaff(DeptStaff deptStaff) {
		String sql = "DeptStaffMapping.deleteDeptStaff";
		this.delete(sql, deptStaff);
	}

	@Override
	public void verifyDeptStaff(DeptStaff deptStaff) {
		String sql = "DeptStaffMapping.verifyDeptStaff";
		this.update(sql, deptStaff);
	}

	@Override
	public void unVerifyDeptStaff(DeptStaff deptStaff) {
		String sql = "DeptStaffMapping.unVerifyDeptStaff";
		this.update(sql, deptStaff);
	}

	@Override
	public DeptStaff getDeptStaff(String id) {

		String sql = "DeptStaffMapping.getDeptStaff"; 
		DeptStaff deptStaff = new DeptStaff(); 
		deptStaff.setDept_staff_id(id);
		List<DeptStaff> list = this.query(sql, DeptStaff.class, deptStaff); 
		if(list == null || list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
	}


	@Override
	public List<DeptStaff> getDeptStaffs(DeptStaff deptStaff) {

		String sql = "DeptStaffMapping.getDeptStaffs";
		List<DeptStaff> list = this.query(sql, DeptStaff.class, deptStaff);
		return list;
	}

	@Override
	public Pager<DeptStaff> queryDeptStaff(
		DeptStaff deptStaff,
		UserPermit userPermit,
		Pager<DeptStaff> pager){

		String sql = "DeptStaffMapping.queryDeptStaff"; 
		Pager<DeptStaff> pager1 =  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql,DeptStaff.class, deptStaff,userPermit); 
		sql = "DeptStaffMapping.queryDeptStaffTotalAmount";
		Map<String,Object> map = new HashMap<String,Object>();
		if(deptStaff != null) {
			map.put(deptStaff.getClass().getSimpleName(), deptStaff);
		}
		if(userPermit != null) {
			map.put(userPermit.getClass().getSimpleName(), userPermit);
		}
		Double amount = getSqlSession().selectOne(sql,map);
		if(amount != null) {
			double total_amount = amount.doubleValue();
			pager1.setTotal_amount(total_amount);
		}
		return pager1;
	}


}