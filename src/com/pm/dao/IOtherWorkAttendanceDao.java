package com.pm.dao;

import com.common.beans.Pager;
import com.pm.domain.business.OtherWorkAttendance;
import com.pm.vo.UserPermit;

import java.util.List;

/**
 * @author Administrator
 */
public interface IOtherWorkAttendanceDao {

	
	public int addWorkAttendance(OtherWorkAttendance workAttendance) ;  
	

	
	public int updateWorkAttendance(OtherWorkAttendance workAttendance) ; 
	
	public void verifyWorkAttendance(OtherWorkAttendance workAttendance) ;
	
	public void deleteWorkAttendance(OtherWorkAttendance workAttendance) ; 
	

	public OtherWorkAttendance getWorkAttendance(String attendance_id) ;
	

	public List<OtherWorkAttendance> getWorkAttendanceByDeptMonth(OtherWorkAttendance workAttendance) ;
	
	

	public List<OtherWorkAttendance> getCheckedDeptMonth() ;


	public Pager<OtherWorkAttendance> queryWorkAttendance(OtherWorkAttendance workAttendance, UserPermit userPermit, Pager<OtherWorkAttendance> pager);	
	
	
	

	public Pager<OtherWorkAttendance> queryWorkAttendanceGroup(OtherWorkAttendance workAttendance, UserPermit userPermit, Pager<OtherWorkAttendance> pager);	

}
