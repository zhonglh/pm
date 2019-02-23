package com.pm.dao.impl;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.IOtherWorkAttendanceDao;
import com.pm.dao.IWorkAttendanceDao;
import com.pm.domain.business.OtherWorkAttendance;
import com.pm.vo.UserPermit;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Administrator
 */
@Component
public class OtherWorkAttendanceDaoImpl extends BatisDao implements IOtherWorkAttendanceDao {



	@Override
	public int addWorkAttendance(OtherWorkAttendance workAttendance) {
		String sql = "OtherWorkAttendanceMapping.addWorkAttendance";
		return this.insert(sql, workAttendance);		
	}

	@Override
	public int updateWorkAttendance(OtherWorkAttendance workAttendance) {
		String sql = "OtherWorkAttendanceMapping.updateWorkAttendance";
		return this.update(sql, workAttendance);		
	}
	

	@Override
	public void verifyWorkAttendance(OtherWorkAttendance workAttendance) {
		String sql = "OtherWorkAttendanceMapping.verifyWorkAttendance";
		this.update(sql, workAttendance);		
	}

	@Override
	public void deleteWorkAttendance(OtherWorkAttendance workAttendance) {
		String sql = "OtherWorkAttendanceMapping.deleteWorkAttendance";
		this.delete(sql, workAttendance);		

	}

	@Override
	public OtherWorkAttendance getWorkAttendance(String attendance_id) {
		OtherWorkAttendance workAttendance = new OtherWorkAttendance();
		workAttendance.setAttendance_id(attendance_id);
		String sql = "OtherWorkAttendanceMapping.getWorkAttendance";
		List<OtherWorkAttendance> list = this.query(sql, OtherWorkAttendance.class, workAttendance);
		if(list == null || list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
	}
	

	@Override
	public List<OtherWorkAttendance> getWorkAttendanceByDeptMonth(OtherWorkAttendance workAttendance) {
		String sql = "OtherWorkAttendanceMapping.getWorkAttendanceByDeptMonth";
		return this.query(sql, OtherWorkAttendance.class, workAttendance);
	}	
	
	

	@Override
	public List<OtherWorkAttendance> getCheckedDeptMonth() {
		String sql = "OtherWorkAttendanceMapping.getCheckedDeptMonth";
		return this.query(sql, OtherWorkAttendance.class, null);
	}

	@Override
	public Pager<OtherWorkAttendance> queryWorkAttendance(
			OtherWorkAttendance workAttendance, 
			UserPermit userPermit,
			Pager<OtherWorkAttendance> pager) {

		String sql = "OtherWorkAttendanceMapping.queryWorkAttendance";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, OtherWorkAttendance.class, workAttendance,userPermit);
	}
	
	
	

	@Override
	public Pager<OtherWorkAttendance> queryWorkAttendanceGroup(
			OtherWorkAttendance workAttendance, 
			UserPermit userPermit,
			Pager<OtherWorkAttendance> pager){

		String sql = "OtherWorkAttendanceMapping.queryWorkAttendanceGroup";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, OtherWorkAttendance.class, workAttendance,userPermit);
	}

}
