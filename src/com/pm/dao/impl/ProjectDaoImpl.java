package com.pm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.IProjectDao;
import com.pm.domain.business.Project;
import com.pm.domain.business.ProjectContract;
import com.pm.domain.business.ProjectStaff;
import com.pm.domain.business.StaffCost;
import com.pm.vo.UserPermit;


/**
 * @author Administrator
 */
@Component
public class ProjectDaoImpl extends BatisDao implements IProjectDao {



	@Override
	public int addProject(Project project) {
		String sql = "ProjectMapping.addProject";
		return this.insert(sql, project);
	}

	@Override
	public int updateProject(Project project) {
		String sql = "ProjectMapping.updateProject";
		return this.update(sql, project);

	}

	@Override
	public void deleteProject(Project[] projects) {
		if(projects == null) {
			return ;
		}
		String sql = "ProjectMapping.deleteProject";
		for(Project project : projects){
			deleteProjectStaffByProject(project);
			this.update(sql, project);
		}

	}
	
	
	public void deleteProjectStaffByProject(Project project){
		String sql = "ProjectMapping.deleteProjectStaffByProject";
		this.update(sql, project);
	}

	@Override
	public Project getProject(String project_id) {
		Project project = new Project();
		project.setProject_id(project_id);
		Map<String,Project> map = new HashMap<String,Project>();
		map.put(Project.class.getSimpleName(), project);
		String sql = "ProjectMapping.getProject";
		List<Project> list = this.query(sql, Project.class ,map);
		if(list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);
	}
	
	

	@Override
	public boolean isExist(Project project){
		String sql = "ProjectMapping.isExist";
		List<Project> list = this.query(sql, Project.class ,project);
		return (list == null || list.isEmpty()) ? false : true; 
	}

	@Override
	public Pager<Project> queryProject(Project project,
			UserPermit userPermit, Pager<Project> pager) {
		String sql = "ProjectMapping.queryProject";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Project.class, project,userPermit);
	}
	
	
	
	
	


	@Override
	public List<ProjectStaff> queryProjectStaff(Project project) {
		String sql = "ProjectMapping.queryProjectStaff";
		List<ProjectStaff> list = this.query(sql, ProjectStaff.class ,project);
		return list;
	}
	

	@Override
	public List<ProjectStaff> queryProjectStaff(StaffCost staffCost){
		String sql = "ProjectMapping.queryProjectStaffByStaff";
		List<ProjectStaff> list = this.query(sql, ProjectStaff.class ,staffCost);
		return list;
	}
	

	@Override
	public ProjectStaff getProjectStaff(ProjectStaff projectStaff){
		String sql = "ProjectMapping.getProjectStaff";
		List<ProjectStaff> list = this.query(sql, ProjectStaff.class ,projectStaff);
		if(list != null && list.size() >0) {
			return list.get(0);
		}
		else {
			return null;
		}
	}

	@Override
	public void addProjectStaff(ProjectStaff projectStaff) {
		String sql = "ProjectMapping.addProjectStaff";
		this.insert(sql, projectStaff);
		
	}
	

	@Override
	public void updateProjectStaff(ProjectStaff projectStaff) {
		String sql = "ProjectMapping.updateProjectStaff";
		this.update(sql, projectStaff);
		
	}
	

	@Override
	public void doRemoveProjectStaff(ProjectStaff projectStaff) {
		String sql = "ProjectMapping.doRemoveProjectStaff";
		this.delete(sql, projectStaff);
	}
	

	@Override
	public void deleteProjectStaff(ProjectStaff projectStaff) {
		String sql = "ProjectMapping.deleteProjectStaff";
		this.update(sql, projectStaff);		
	}
	

	@Override
	public void deleteProjectStaffByStaff(StaffCost staffCost) {
		String sql = "ProjectMapping.deleteProjectStaffByStaff";
		this.update(sql, staffCost);		
	}
	
	
	
	

	@Override
	public List<ProjectContract> queryProjectContract(Project project) {
		String sql = "ProjectMapping.queryProjectContract";
		List<ProjectContract> list = this.query(sql, ProjectContract.class ,project);
		return list;
	}
	

	@Override
	public ProjectContract getProjectContract(ProjectContract projectContract){
		String sql = "ProjectMapping.getProjectContract";
		List<ProjectContract> list = this.query(sql, ProjectContract.class,projectContract);
		if(list == null || list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
	}

	@Override
	public void addProjectContract(ProjectContract projectContract) {
		String sql = "ProjectMapping.addProjectContract";
		this.insert(sql, projectContract);				
	}

	@Override
	public void deleteProjectContract(ProjectContract projectContract) {
		String sql = "ProjectMapping.deleteProjectContract";
		this.delete(sql, projectContract);		
		
	}


}
