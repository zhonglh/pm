package com.pm.dao;

import java.util.List;

import com.common.beans.Pager;
import com.pm.domain.business.Project;
import com.pm.domain.business.ProjectStaffCost;
import com.pm.domain.business.Salary;
import com.pm.vo.UserPermit;

public interface IProjectStaffCostDao {
	

	
	public void addProjectStaffCost(ProjectStaffCost projectStaffCost) ;
	
	
	public void deleteProjectStaffCost(Salary salary) ;
	
	public Pager<ProjectStaffCost> queryProjectStaffCost(ProjectStaffCost projectStaffCost, UserPermit userPermit,Pager<ProjectStaffCost> pager);
	
	public ProjectStaffCost getProjectStaffCost(ProjectStaffCost projectStaffCost) ;

	public List<ProjectStaffCost> getProjectStaffCost(Salary[] salarys) ;
	
	public List<ProjectStaffCost> computeProjectStaffCost(Salary salary) ;
	

}
