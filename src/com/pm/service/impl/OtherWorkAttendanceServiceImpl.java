package com.pm.service.impl;

import com.common.beans.Pager;
import com.common.utils.IDKit;
import com.pm.dao.IOtherWorkAttendanceDao;
import com.pm.domain.business.OtherWorkAttendance;
import com.pm.service.IOtherWorkAttendanceService;
import com.pm.vo.UserPermit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Administrator
 */
@Component
public class OtherWorkAttendanceServiceImpl implements IOtherWorkAttendanceService {


	@Autowired
	private IOtherWorkAttendanceDao otherWorkAttendanceDao;





	@Override
	public Pager<OtherWorkAttendance> queryWorkAttendanceGroup(OtherWorkAttendance workAttendance, UserPermit userPermit,Pager<OtherWorkAttendance> pager){
		return otherWorkAttendanceDao.queryWorkAttendanceGroup(workAttendance, userPermit, pager);
	}

	@Override
	public int addWorkAttendance(OtherWorkAttendance workAttendance) {
		return otherWorkAttendanceDao.addWorkAttendance(workAttendance);		
	}
	

	@Override
	public void addWorkAttendance(List<OtherWorkAttendance> workAttendances) {
		
		
		OtherWorkAttendance deleteWorkAttendance = new OtherWorkAttendance();
		deleteWorkAttendance.setDept_id(workAttendances.get(0).getDept_id());
		deleteWorkAttendance.setAttendance_month(workAttendances.get(0).getAttendance_month());
		otherWorkAttendanceDao.deleteWorkAttendance(deleteWorkAttendance);
		
		for(OtherWorkAttendance workAttendance : workAttendances){
			otherWorkAttendanceDao.addWorkAttendance(workAttendance);	
		}
	}

	@Override
	public int updateWorkAttendance(OtherWorkAttendance workAttendance) {
		return otherWorkAttendanceDao.updateWorkAttendance(workAttendance);	
	}

	@Override
	public void updateWorkAttendance(List<OtherWorkAttendance> workAttendances) {
		if(workAttendances == null || workAttendances.isEmpty()) {
			return ;
		}
		
		for(OtherWorkAttendance workAttendance : workAttendances){
			if(workAttendance.getAttendance_id() == null || workAttendance.getAttendance_id().isEmpty()){
				workAttendance.setAttendance_id(IDKit.generateId());
				otherWorkAttendanceDao.addWorkAttendance(workAttendance);
			}else {
				otherWorkAttendanceDao.updateWorkAttendance(workAttendance);
			}
		}
		
		
		
		
	}
	

	@Override
	public void verifyWorkAttendance(List<OtherWorkAttendance> workAttendances) {		
		for(OtherWorkAttendance workAttendance : workAttendances) {
			otherWorkAttendanceDao.verifyWorkAttendance(workAttendance);
		}
	}

	@Override
	public void deleteWorkAttendance(OtherWorkAttendance[] workAttendances) {
		for(OtherWorkAttendance workAttendance : workAttendances){
			otherWorkAttendanceDao.deleteWorkAttendance(workAttendance);
		}		
	}

	@Override
	public OtherWorkAttendance getWorkAttendance(String attendance_id) {		
		return otherWorkAttendanceDao.getWorkAttendance(attendance_id);
	}
	
	@Override
	public List<OtherWorkAttendance> getWorkAttendanceByDeptMonth(OtherWorkAttendance workAttendance) {
		return otherWorkAttendanceDao.getWorkAttendanceByDeptMonth(workAttendance);		
	}

		

	@Override
	public List<OtherWorkAttendance> getCheckedDeptMonth() {
		return otherWorkAttendanceDao.getCheckedDeptMonth();
	}

	@Override
	public Pager<OtherWorkAttendance> queryWorkAttendance(
			OtherWorkAttendance workAttendance, 
			UserPermit userPermit,
			Pager<OtherWorkAttendance> pager) {		
		return otherWorkAttendanceDao.queryWorkAttendance(workAttendance, userPermit, pager);
	}
}
