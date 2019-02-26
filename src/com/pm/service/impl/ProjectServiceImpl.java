package com.pm.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.utils.IDKit;
import com.pm.dao.IProjectDao;
import com.pm.dao.IStaffCostDao;
import com.pm.domain.business.Project;
import com.pm.domain.business.ProjectContract;
import com.pm.domain.business.ProjectStaff;
import com.pm.domain.business.StaffCost;
import com.pm.service.IProjectService;
import com.pm.util.PubMethod;
import com.pm.util.constant.BusinessUtil;
import com.pm.vo.UserPermit;


@Component
public class ProjectServiceImpl implements IProjectService {


	@Autowired IProjectDao projectDao;

	@Autowired IStaffCostDao staffCostDao;
	
	@Override
	public int addProject(Project project ,ProjectStaff[] projectStaffs,ProjectContract[] projectContracts) {
		int count = projectDao.addProject(project);
		if(projectStaffs != null && projectStaffs.length > 0){
			for(ProjectStaff projectStaff : projectStaffs){
				if(StringUtils.isEmpty(projectStaff.getProject_staff_id())){
					projectStaff.setProject_staff_id(IDKit.generateId());
				}
				projectDao.addProjectStaff(projectStaff);
				
				StaffCost staffCost = new StaffCost();
				staffCost.setStaff_id(projectStaff.getStaff_id());
				staffCost.setQustomerquotes(projectStaff.getTechnical_cost());
				staffCostDao.updateStaffQustomerQuotes(staffCost);
			}
		}
		

		if(projectContracts != null && projectContracts.length > 0){
			for(ProjectContract projectContract : projectContracts) {
				projectDao.addProjectContract(projectContract);
			}
		}	
		return count;
	}

	@Override
	public int updateProject(Project project,ProjectStaff[] addProjectStaffs,ProjectContract[] projectContracts) {
		int count = projectDao.updateProject(project);


		if(addProjectStaffs != null && addProjectStaffs.length > 0){
			for(ProjectStaff projectStaff : addProjectStaffs){
				if(StringUtils.isEmpty(projectStaff.getProject_staff_id())){
					projectStaff.setProject_staff_id(IDKit.generateId());
					projectDao.addProjectStaff(projectStaff);
				}else {
					projectDao.updateProjectStaff(projectStaff);
				}
				

				if(StringUtils.isEmpty(projectStaff.getStaff_id())){
					ProjectStaff temp = projectDao.getProjectStaff(projectStaff);
					if(temp != null ) {
						projectStaff.setStaff_id(temp.getStaff_id());
					}
				}
				
				StaffCost staffCost = new StaffCost();
				staffCost.setStaff_id(projectStaff.getStaff_id());
				staffCost.setQustomerquotes(projectStaff.getTechnical_cost());
				staffCost.setFirstquotes(projectStaff.getTechnical_cost());
				staffCostDao.updateStaffQustomerQuotes(staffCost);		
				staffCostDao.updateStaffFirstquotes(staffCost);
			}
			
		}		

		if(projectContracts != null && projectContracts.length > 0){
			for(ProjectContract projectContract : projectContracts) {
				projectDao.addProjectContract(projectContract);
			}
		}
		return count;
	}

	@Override
	public void deleteProject(Project[] projects) {
		projectDao.deleteProject(projects);
	}
	
	

	@Override
	public boolean isExist(Project project){
		return projectDao.isExist(project);
	}

	@Override
	public Project getProject(String project_id) {
		return projectDao.getProject(project_id);
	}

	@Override
	public Pager<Project> queryProject(Project project,
			UserPermit userPermit, Pager<Project> pager) {
		return projectDao.queryProject(project, userPermit, pager);
	}


	@Override
	public List<ProjectStaff> queryProjectStaff(Project project) {
		return projectDao.queryProjectStaff(project);
	}
	


	@Override
	public List<ProjectStaff> queryProjectStaff(StaffCost staffCost){
		return projectDao.queryProjectStaff(staffCost);		
	}
	

	@Override
	public ProjectStaff getProjectStaff(ProjectStaff projectStaff){
		return projectDao.getProjectStaff(projectStaff);
	}
	
	

	@Override
	public void doRemoveProjectStaff(ProjectStaff projectStaff) {		
		projectDao.doRemoveProjectStaff(projectStaff);
	}
	

	@Override
	public void exchangeProjectStaff(ProjectStaff projectStaff, String project_id, double technical_cost, Timestamp join_project_datetime) {
		
		projectDao.deleteProjectStaff(projectStaff);
		
		if(project_id != null && project_id.length() > 0){
			ProjectStaff newProjectStaff = new ProjectStaff();
			newProjectStaff.setProject_staff_id(IDKit.generateId());
			newProjectStaff.setStaff_id(projectStaff.getStaff_id());
			newProjectStaff.setStaff_name(projectStaff.getStaff_name());
			newProjectStaff.setProject_id(project_id);
			newProjectStaff.setJoin_project_datetime(join_project_datetime);

			newProjectStaff.setBuild_datetime(PubMethod.getCurrentDate());
			newProjectStaff.setBuild_userid(projectStaff.getDelete_userid());
			newProjectStaff.setBuild_username(projectStaff.getDelete_username());
			newProjectStaff.setDelete_flag(BusinessUtil.NOT_DELETEED);
			newProjectStaff.setTechnical_cost(technical_cost);
			
			projectDao.addProjectStaff(newProjectStaff);
			
			StaffCost staffCost = new StaffCost();
			staffCost.setStaff_id(newProjectStaff.getStaff_id());
			staffCost.setQustomerquotes(newProjectStaff.getTechnical_cost());
			staffCostDao.updateStaffQustomerQuotes(staffCost);
		}
	}

	@Override
	public void addProjectContract(ProjectContract projectContract) {
		projectDao.addProjectContract(projectContract);		
	}

	@Override
	public void deleteProjectContract(ProjectContract projectContract) {
		projectDao.deleteProjectContract(projectContract);		
	}

	@Override
	public List<ProjectContract> queryProjectContract(Project project) {
		return projectDao.queryProjectContract(project);
	}
	


	@Override
	public ProjectContract getProjectContract(ProjectContract projectContract){
		return projectDao.getProjectContract(projectContract);
	}


}
