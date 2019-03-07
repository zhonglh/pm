package com.pm.dao.impl;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.IStaffPerformanceDao;
import com.pm.domain.business.StaffPerformance;
import com.pm.vo.UserPermit;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@Component
public class StaffPerformanceDaoImpl extends BatisDao implements IStaffPerformanceDao  {

	@Override
	public int addStaffPerformance(StaffPerformance staffPerformance) {
		String sql = "StaffPerformanceMapping.addStaffPerformance";
		return this.insert(sql, staffPerformance);
	}

	@Override
	public int updateStaffPerformance(StaffPerformance staffPerformance) {
		String sql = "StaffPerformanceMapping.updateStaffPerformance";
		return this.update(sql, staffPerformance);
	}

	@Override
	public int deleteStaffPerformance(StaffPerformance staffPerformance) {
		String sql = "StaffPerformanceMapping.deleteStaffPerformance";
		return this.delete(sql, staffPerformance);
	}

	@Override
	public int verifyStaffPerformance(StaffPerformance staffPerformance) {
		String sql = "StaffPerformanceMapping.verifyStaffPerformance";
		return this.update(sql, staffPerformance);
	}

	@Override
	public int unVerifyStaffPerformance(StaffPerformance staffPerformance) {
		String sql = "StaffPerformanceMapping.unVerifyStaffPerformance";
		return this.update(sql, staffPerformance);
	}

	@Override
	public StaffPerformance getStaffPerformance(String id) {

		String sql = "StaffPerformanceMapping.getStaffPerformance"; 
		StaffPerformance staffPerformance = new StaffPerformance(); 
		staffPerformance.setId(id); 
		List<StaffPerformance> list = this.query(sql, StaffPerformance.class, staffPerformance); 
		if(list == null || list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
	}
	
	

	@Override
	public int getNotCheckNumByWorkAttendance(StaffPerformance staffPerformance) {
		String sql = "StaffPerformanceMapping.getNotCheckNumByWorkAttendance"; 
		return this.query4Int(sql, staffPerformance);
	}

	@Override
	public Pager<StaffPerformance> queryStaffPerformance(
		StaffPerformance staffPerformance,
		UserPermit userPermit,
		Pager<StaffPerformance> pager){

		String sql = "StaffPerformanceMapping.queryStaffPerformance"; 
		Pager<StaffPerformance> pager1 =  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql,StaffPerformance.class, staffPerformance,userPermit); 
		sql = "StaffPerformanceMapping.queryStaffPerformanceTotalAmount";
		Map<String,Object> map = new HashMap<String,Object>();
		if(staffPerformance != null) {
			map.put(staffPerformance.getClass().getSimpleName(), staffPerformance);
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