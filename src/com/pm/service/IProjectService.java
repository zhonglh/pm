package com.pm.service;

import java.sql.Timestamp;
import java.util.List;

import com.common.beans.Pager;
import com.pm.domain.business.Project;
import com.pm.domain.business.ProjectContract;
import com.pm.domain.business.ProjectStaff;
import com.pm.domain.business.StaffCost;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;
import com.pm.vo.UserPermit;

public interface IProjectService {	


	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_PROJECT)
	public int addProject(Project project,ProjectStaff[] projectStaffs,ProjectContract[] projectContracts) ;
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_PROJECT)
	public int updateProject(Project project,ProjectStaff[] addProjectStaffs,ProjectContract[] projectContracts) ; 
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.ENTITY_PROJECT)
	public void deleteProject(Project[] projects) ;
	
	
	public boolean isExist(Project project);
	
	public Project getProject(String project_id) ;

	public Pager<Project> queryProject(Project project, UserPermit userPermit,Pager<Project> pager);	
	
	
	
	
	public List<ProjectStaff> queryProjectStaff(Project project);
	public List<ProjectStaff> queryProjectStaff(StaffCost staffCost);
	public ProjectStaff getProjectStaff(ProjectStaff projectStaff);
	
	/**
	 * 项目人员离场， 可能再进场
	 * @param projectStaff
	 * @param project_id
	 * @param technical_cost
	 * @param join_project_datetime
	 */
	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_PROJECT)
	public void exchangeProjectStaff(ProjectStaff projectStaff, String project_id, double technical_cost, Timestamp join_project_datetime) ;
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_PROJECT)
	public void doRemoveProjectStaff(ProjectStaff projectStaff);
	
	/**
	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT_PROJECTSTAFF,entity_type=LogConstant.ENTITY_PROJECT)
	public void addProjectStaff(ProjectStaff projectStaff);
	
	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE_PROJECTSTAFF,entity_type=LogConstant.ENTITY_PROJECT)
	public void deleteProjectStaff(ProjectStaff projectStaff);
	*/
	
	

	public List<ProjectContract> queryProjectContract(Project project);
	
	public ProjectContract getProjectContract(ProjectContract projectContract);

	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_PROJECT)
	public void addProjectContract(ProjectContract projectContract);

	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_PROJECT)
	public void deleteProjectContract(ProjectContract projectContract);

	
	
	

}
