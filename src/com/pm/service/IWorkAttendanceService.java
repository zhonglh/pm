package com.pm.service;

import java.util.List;

import com.common.beans.Pager;
import com.pm.domain.business.WorkAttendance;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;
import com.pm.vo.UserPermit;

public interface IWorkAttendanceService {

	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_WORK_ATTENDANCE)
	public int addWorkAttendance(WorkAttendance workAttendance) ;

	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_WORK_ATTENDANCE)
	public void addWorkAttendance(List<WorkAttendance> workAttendances) ;
	

	public int updateWorkAttendance(WorkAttendance workAttendance) ; 
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_CHECK,entity_type=LogConstant.ENTITY_WORK_ATTENDANCE)
	public void verifyWorkAttendance(List<WorkAttendance> workAttendances) ; 

	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_WORK_ATTENDANCE)
	public void updateWorkAttendance(List<WorkAttendance> workAttendances) ; 
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.ENTITY_WORK_ATTENDANCE)
	public void deleteWorkAttendance(WorkAttendance[] workAttendances) ; 
	

	public WorkAttendance getWorkAttendance(String attendance_id) ;

	public List<WorkAttendance> getWorkAttendanceByProjectMonth(WorkAttendance workAttendance) ;
	
	

	/**
	 * 获得已经核单的 项目和月份信息
	 * @return
	 */
	public List<WorkAttendance> getCheckedProjectMonth() ;
	

	public Pager<WorkAttendance> queryWorkAttendance(WorkAttendance workAttendance, UserPermit userPermit,Pager<WorkAttendance> pager);		

	public Pager<WorkAttendance> queryWorkAttendanceGroup(WorkAttendance workAttendance, UserPermit userPermit,Pager<WorkAttendance> pager);	


}
