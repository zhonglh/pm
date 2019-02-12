package com.pm.service;

import java.util.List;

import com.common.beans.Pager;
import com.pm.domain.business.PersonnelMonthlySalary;
import com.pm.domain.business.Salary;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;
import com.pm.vo.UserPermit;

public interface  ISalaryService extends IBaseService{

	public void addSalary(Salary salary) ;

	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_SALARY)
	public void addSalary(List<Salary> salarys) ;
	


	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_SALARY)
	public void updateSalary(List<Salary> salarys) ; 
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.ENTITY_SALARY)
	public void deleteSalary(Salary[] salarys) ; 
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_CHECK,entity_type=LogConstant.ENTITY_SALARY)
	public void verifySalary(Salary[] salarys) ; 
	

	public Salary getSalary(String salary_id) ;

	/**
	 * 根据考勤， 人员成本信息 查出工资单
	 * 需要项目ID, 工资月份
	 * @param salary
	 * @return
	 */
	public List<Salary> getSalaryByProjectMonth(Salary salary) ;

	/**
	 * 获取本年（发工资时间的当年，工资时间是从上年12月份到本年11月份）累计记录
	 * @param startSalaryMonth
	 * @param endSalaryMonth
	 * @param staffCostIds
	 * @return
	 */
	public List<Salary> getAccumulatedSalary(int startSalaryMonth , int endSalaryMonth , List<String> staffCostIds);
	

	/**
	 * 根据考勤的项目ID和月份 获取 转正和加减薪信息里的当月薪资和备注信息
	 * @param search
	 * @return
	 */
	public List<PersonnelMonthlySalary> getCurrSalaryByWorkAttendance(Salary search);
	
	

	public Pager<Salary> querySalary(Salary salary, UserPermit userPermit,Pager<Salary> pager);		

	public Pager<Salary> querySalaryGroup(Salary salary, UserPermit userPermit,Pager<Salary> pager);	


}
