package com.pm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.IWorkAttendanceDao;
import com.pm.domain.business.WorkAttendance;
import com.pm.vo.UserPermit;

@Component
public class WorkAttendanceDaoImpl extends BatisDao implements IWorkAttendanceDao {



	@Override
	public int addWorkAttendance(WorkAttendance workAttendance) {
		String sql = "WorkAttendanceMapping.addWorkAttendance";
		return this.insert(sql, workAttendance);		
	}

	@Override
	public int updateWorkAttendance(WorkAttendance workAttendance) {
		String sql = "WorkAttendanceMapping.updateWorkAttendance";
		return this.update(sql, workAttendance);		
	}
	

	@Override
	public void verifyWorkAttendance(WorkAttendance workAttendance) {
		String sql = "WorkAttendanceMapping.verifyWorkAttendance";
		this.update(sql, workAttendance);		
	}

	@Override
	public void deleteWorkAttendance(WorkAttendance workAttendance) {
		String sql = "WorkAttendanceMapping.deleteWorkAttendance";
		this.delete(sql, workAttendance);		

	}

	@Override
	public WorkAttendance getWorkAttendance(String attendance_id) {
		WorkAttendance workAttendance = new WorkAttendance();
		workAttendance.setAttendance_id(attendance_id);
		String sql = "WorkAttendanceMapping.getWorkAttendance";
		List<WorkAttendance> list = this.query(sql, WorkAttendance.class, workAttendance);
		if(list == null || list.isEmpty()) return null;
		else return list.get(0);
	}
	

	@Override
	public List<WorkAttendance> getWorkAttendanceByProjectMonth(WorkAttendance workAttendance) {
		String sql = "WorkAttendanceMapping.getWorkAttendanceByProjectMonth";
		return this.query(sql, WorkAttendance.class, workAttendance);
	}	
	
	

	@Override
	public List<WorkAttendance> getCheckedProjectMonth() {
		String sql = "WorkAttendanceMapping.getCheckedProjectMonth";
		return this.query(sql, WorkAttendance.class, null);
	}

	@Override
	public Pager<WorkAttendance> queryWorkAttendance(
			WorkAttendance workAttendance, 
			UserPermit userPermit,
			Pager<WorkAttendance> pager) {

		String sql = "WorkAttendanceMapping.queryWorkAttendance";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, WorkAttendance.class, workAttendance,userPermit);
	}
	
	
	

	@Override
	public Pager<WorkAttendance> queryWorkAttendanceGroup(
			WorkAttendance workAttendance, 
			UserPermit userPermit,
			Pager<WorkAttendance> pager){

		String sql = "WorkAttendanceMapping.queryWorkAttendanceGroup";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, WorkAttendance.class, workAttendance,userPermit);
	}

}
