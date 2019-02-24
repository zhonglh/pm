package com.pm.service;

import com.common.beans.Pager;
import com.pm.domain.business.PersonnelMonthlySalary;
import com.pm.domain.business.OtherSalary;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;
import com.pm.vo.UserPermit;

import java.util.List;

public interface IOtherSalaryService extends IBaseService{

	public void addSalary(OtherSalary salary) ;

	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_OTHER_SALARY)
	public void addSalary(List<OtherSalary> salarys) ;
	


	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_OTHER_SALARY)
	public void updateSalary(List<OtherSalary> salarys) ; 
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.ENTITY_OTHER_SALARY)
	public void deleteSalary(OtherSalary[] salarys) ; 
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_CHECK,entity_type=LogConstant.ENTITY_OTHER_SALARY)
	public void verifySalary(OtherSalary[] salarys) ; 
	

	public OtherSalary getSalary(String salary_id) ;

	/**
	 * 根据考勤， 人员成本信息 查出工资单
	 * 需要部门ID, 工资月份
	 * @param salary
	 * @return
	 */
	public List<OtherSalary> getSalaryByDeptMonth(OtherSalary salary) ;

	/**
	 * 获取本年（发工资时间的当年，工资时间是从上年12月份到本年11月份）累计记录
	 * @param startSalaryMonth
	 * @param endSalaryMonth
	 * @param staffCostIds
	 * @return
	 */
	public List<OtherSalary> getAccumulatedSalary(int startSalaryMonth, int endSalaryMonth, List<String> staffCostIds);
	

	/**
	 * 根据考勤的部门ID和月份 获取 转正和加减薪信息里的当月薪资和备注信息
	 * @param
	 * @return
	 */
	//public List<PersonnelMonthlySalary> getCurrSalaryByWorkAttendance(OtherSalary search);
	
	

	public Pager<OtherSalary> querySalary(OtherSalary salary, UserPermit userPermit, Pager<OtherSalary> pager);		

	public Pager<OtherSalary> querySalaryGroup(OtherSalary salary, UserPermit userPermit, Pager<OtherSalary> pager);	


}
