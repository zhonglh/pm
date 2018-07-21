package com.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.utils.IDKit;
import com.pm.dao.IWorkAttendanceDao;
import com.pm.domain.business.WorkAttendance;
import com.pm.service.IMonthlyStatementService;
import com.pm.service.IWorkAttendanceService;
import com.pm.vo.UserPermit;

@Component
public class WorkAttendanceServiceImpl implements IWorkAttendanceService{


	@Autowired
	private IWorkAttendanceDao workAttendanceDao;
	

	
	

	public Pager<WorkAttendance> queryWorkAttendanceGroup(WorkAttendance workAttendance, UserPermit userPermit,Pager<WorkAttendance> pager){
		return workAttendanceDao.queryWorkAttendanceGroup(workAttendance, userPermit, pager);
	}

	@Override
	public int addWorkAttendance(WorkAttendance workAttendance) {
		return workAttendanceDao.addWorkAttendance(workAttendance);		
	}
	

	@Override
	public void addWorkAttendance(List<WorkAttendance> workAttendances) {
		
		
		WorkAttendance deleteWorkAttendance = new WorkAttendance();
		deleteWorkAttendance.setProject_id(workAttendances.get(0).getProject_id());
		deleteWorkAttendance.setAttendance_month(workAttendances.get(0).getAttendance_month());
		workAttendanceDao.deleteWorkAttendance(deleteWorkAttendance);
		
		for(WorkAttendance workAttendance : workAttendances){
			workAttendanceDao.addWorkAttendance(workAttendance);	
		}
	}

	@Override
	public int updateWorkAttendance(WorkAttendance workAttendance) {
		return workAttendanceDao.updateWorkAttendance(workAttendance);	
	}

	@Override
	public void updateWorkAttendance(List<WorkAttendance> workAttendances) {
		if(workAttendances == null || workAttendances.isEmpty()) return ;
		
		for(WorkAttendance workAttendance : workAttendances){
			if(workAttendance.getAttendance_id() == null || workAttendance.getAttendance_id().isEmpty()){
				workAttendance.setAttendance_id(IDKit.generateId());
				workAttendanceDao.addWorkAttendance(workAttendance);
			}else {
				workAttendanceDao.updateWorkAttendance(workAttendance);
			}
		}
		
		
		
		
	}
	

	@Override
	public void verifyWorkAttendance(List<WorkAttendance> workAttendances) {		
		for(WorkAttendance workAttendance : workAttendances)
		workAttendanceDao.verifyWorkAttendance(workAttendance);		
	}

	@Override
	public void deleteWorkAttendance(WorkAttendance[] workAttendances) {
		for(WorkAttendance workAttendance : workAttendances){
			workAttendanceDao.deleteWorkAttendance(workAttendance);
		}		
	}

	@Override
	public WorkAttendance getWorkAttendance(String attendance_id) {		
		return workAttendanceDao.getWorkAttendance(attendance_id);
	}
	
	@Override
	public List<WorkAttendance> getWorkAttendanceByProjectMonth(WorkAttendance workAttendance) {
		return workAttendanceDao.getWorkAttendanceByProjectMonth(workAttendance);		
	}

		

	@Override
	public List<WorkAttendance> getCheckedProjectMonth() {
		return workAttendanceDao.getCheckedProjectMonth();
	}

	@Override
	public Pager<WorkAttendance> queryWorkAttendance(
			WorkAttendance workAttendance, 
			UserPermit userPermit,
			Pager<WorkAttendance> pager) {		
		return workAttendanceDao.queryWorkAttendance(workAttendance, userPermit, pager);
	}
}
