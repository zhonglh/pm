package com.pm.service;

import java.util.List;

import com.common.beans.Pager;
import com.pm.domain.business.ProjectStaffCost;
import com.pm.domain.business.Salary;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;
import com.pm.vo.UserPermit;

public interface IProjectStaffCostService {
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_PROJECT_STAFF_COST)
	public void addProjectStaffCost(List<ProjectStaffCost> projectStaffCosts) ;

	public List<ProjectStaffCost> computeProjectStaffCost(Salary[] salarys) ;

	public Pager<ProjectStaffCost> queryProjectStaffCost(ProjectStaffCost projectStaffCost, UserPermit userPermit,Pager<ProjectStaffCost> pager);	

	public ProjectStaffCost getProjectStaffCost(ProjectStaffCost projectStaffCost) ;
	
	
	
	
	
	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.ENTITY_PROJECT_STAFF_COST)
	public void deleteProjectStaffCost(Salary[] salarys) ;	

	public List<ProjectStaffCost> getProjectStaffCost(Salary[] salarys) ;

	
	
}
