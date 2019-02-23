package com.pm.service;

import com.common.beans.Pager;
import com.pm.domain.business.OtherWorkAttendance;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;
import com.pm.vo.UserPermit;

import java.util.List;

public interface IOtherWorkAttendanceService {

	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_OTHER_WORK_ATTENDANCE)
	public int addWorkAttendance(OtherWorkAttendance workAttendance) ;

	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_OTHER_WORK_ATTENDANCE)
	public void addWorkAttendance(List<OtherWorkAttendance> workAttendances) ;
	

	public int updateWorkAttendance(OtherWorkAttendance workAttendance) ; 
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_CHECK,entity_type=LogConstant.ENTITY_OTHER_WORK_ATTENDANCE)
	public void verifyWorkAttendance(List<OtherWorkAttendance> workAttendances) ; 

	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_OTHER_WORK_ATTENDANCE)
	public void updateWorkAttendance(List<OtherWorkAttendance> workAttendances) ; 
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.ENTITY_OTHER_WORK_ATTENDANCE)
	public void deleteWorkAttendance(OtherWorkAttendance[] workAttendances) ; 
	

	public OtherWorkAttendance getWorkAttendance(String attendance_id) ;

	public List<OtherWorkAttendance> getWorkAttendanceByDeptMonth(OtherWorkAttendance workAttendance) ;
	
	

	/**
	 * 获得已经核单的 部门和月份信息
	 * @return
	 */
	public List<OtherWorkAttendance> getCheckedDeptMonth() ;
	

	public Pager<OtherWorkAttendance> queryWorkAttendance(OtherWorkAttendance workAttendance, UserPermit userPermit, Pager<OtherWorkAttendance> pager);		

	public Pager<OtherWorkAttendance> queryWorkAttendanceGroup(OtherWorkAttendance workAttendance, UserPermit userPermit, Pager<OtherWorkAttendance> pager);	


}
