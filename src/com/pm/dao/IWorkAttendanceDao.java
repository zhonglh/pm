package com.pm.dao;

import java.util.List;

import com.common.beans.Pager;
import com.pm.domain.business.WorkAttendance;
import com.pm.vo.UserPermit;

public interface IWorkAttendanceDao {

	
	public int addWorkAttendance(WorkAttendance workAttendance) ;  
	

	
	public int updateWorkAttendance(WorkAttendance workAttendance) ; 
	
	public void verifyWorkAttendance(WorkAttendance workAttendance) ;
	
	public void deleteWorkAttendance(WorkAttendance workAttendance) ; 
	

	public WorkAttendance getWorkAttendance(String attendance_id) ;
	

	public List<WorkAttendance> getWorkAttendanceByProjectMonth(WorkAttendance workAttendance) ;
	
	

	public List<WorkAttendance> getCheckedProjectMonth() ;


	public Pager<WorkAttendance> queryWorkAttendance(WorkAttendance workAttendance, UserPermit userPermit,Pager<WorkAttendance> pager);	
	
	
	

	public Pager<WorkAttendance> queryWorkAttendanceGroup(WorkAttendance workAttendance, UserPermit userPermit,Pager<WorkAttendance> pager);	

}
