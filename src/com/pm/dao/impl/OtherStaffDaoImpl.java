package com.pm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.IOtherStaffDao;
import com.pm.domain.business.OtherStaff;
import com.pm.vo.UserPermit;

@Component
public class OtherStaffDaoImpl extends BatisDao implements IOtherStaffDao {

	@Override
	public int addOtherStaff(OtherStaff otherStaff) {
		String sql = "OtherStaffMapping.addOtherStaff";
		return this.insert(sql, otherStaff);
	}

	@Override
	public int updateOtherStaff(OtherStaff otherStaff) {
		String sql = "OtherStaffMapping.updateOtherStaff";
		return this.update(sql, otherStaff);
	}

	@Override
	public void deleteOtherStaff(OtherStaff otherStaff) {
		String sql = "OtherStaffMapping.deleteOtherStaff";
		this.update(sql, otherStaff);
	}

	@Override
	public OtherStaff getOtherStaff(String staff_id) {
		OtherStaff otherStaff = new OtherStaff();
		otherStaff.setStaff_id(staff_id);
		String sql = "OtherStaffMapping.getOtherStaff";
		List<OtherStaff> list = this.query(sql, OtherStaff.class, otherStaff);
		if(list == null || list.isEmpty()) return null;
		else return list.get(0);
	}



	@Override
	public List<OtherStaff> getOtherStaffByInsurance(OtherStaff otherStaff){
		String sql = "OtherStaffMapping.getOtherStaffByInsurance";
		return this.query(sql, OtherStaff.class, otherStaff);
	}

	@Override
	public boolean isExist(OtherStaff otherStaff) {
		String sql = "OtherStaffMapping.isExist";
		List<OtherStaff> list =  this.query(sql, OtherStaff.class, otherStaff);
		if(list == null || list.isEmpty()) return false;
		else return true;
	}

	@Override
	public Pager<OtherStaff> queryOtherStaff(OtherStaff otherStaff, UserPermit userPermit,Pager<OtherStaff> pager){
		String sql = "OtherStaffMapping.queryOtherStaff";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, OtherStaff.class, userPermit,otherStaff);
	}
	
	

	@Override
	public List<OtherStaff> queryOtherStaffByProjectSales(OtherStaff otherStaff,UserPermit userPermit){
		String sql = "OtherStaffMapping.queryOtherStaffByProjectSales";
		Map<String, Object> map = new HashMap<String,Object>();
		map.put(OtherStaff.class.getSimpleName(), otherStaff);
		map.put(UserPermit.class.getSimpleName(), userPermit);
		return this.query(sql, OtherStaff.class,map);
	}

}
