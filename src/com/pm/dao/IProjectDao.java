package com.pm.dao;

import java.util.List;

import com.common.beans.Pager;
import com.pm.domain.business.Project;
import com.pm.domain.business.ProjectContract;
import com.pm.domain.business.ProjectStaff;
import com.pm.domain.business.StaffCost;
import com.pm.vo.UserPermit;

public interface IProjectDao {
	
	public int addProject(Project project) ;  
	
	public int updateProject(Project project) ; 
	
	public void deleteProject(Project[] projects) ; 
	
	

	public boolean isExist(Project project) ;

	public Project getProject(String project_id) ;	

	public Pager<Project> queryProject(Project project, UserPermit userPermit,Pager<Project> pager);
	
	

	public List<ProjectStaff> queryProjectStaff(Project project);

	public List<ProjectStaff> queryProjectStaff(StaffCost staffCost);

	public ProjectStaff getProjectStaff(ProjectStaff projectStaff);
	
	public void addProjectStaff(ProjectStaff projectStaff) ;  
	
	public void updateProjectStaff(ProjectStaff projectStaff) ;  

	public void doRemoveProjectStaff(ProjectStaff projectStaff) ; 
	

	public void deleteProjectStaff(ProjectStaff projectStaff) ;  
	
	public void deleteProjectStaffByStaff(StaffCost staffCost);
	

	public List<ProjectContract> queryProjectContract(Project project);
	

	public ProjectContract getProjectContract(ProjectContract projectContract);
	
	public void addProjectContract(ProjectContract projectContract) ;  

	public void deleteProjectContract(ProjectContract projectContract) ;
	
	
	
}
