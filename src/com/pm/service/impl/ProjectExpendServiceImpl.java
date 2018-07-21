package com.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.exceptions.CommonErrorConstants;
import com.common.exceptions.PMException;
import com.pm.dao.IProjectExpendDao;
import com.pm.domain.business.ProjectExpend;
import com.pm.service.IProjectExpendService;
import com.pm.vo.UserPermit;

@Component
public class ProjectExpendServiceImpl implements IProjectExpendService {


	@Autowired IProjectExpendDao projectExpendDao;
	
	@Override
	public int addProjectExpend(ProjectExpend projectExpend) {
		return projectExpendDao.addProjectExpend(projectExpend);
	}

	@Override
	public int updateProjectExpend(ProjectExpend projectExpend) {
		return projectExpendDao.updateProjectExpend(projectExpend);
	}
	
	
	public int doPay(ProjectExpend projectExpend) {
		return projectExpendDao.doPay(projectExpend);		
	}

	@Override
	public void deleteProjectExpend(ProjectExpend[] projectExpends) {
		for(ProjectExpend projectExpend : projectExpends){
			projectExpendDao.deleteProjectExpend(projectExpend);
		}

	}

	@Override
	public ProjectExpend getProjectExpend(String project_expend_id) {		
		return projectExpendDao.getProjectExpend(project_expend_id);
	}

	@Override
	public Pager<ProjectExpend> queryProjectExpend(
			ProjectExpend projectExpend, UserPermit userPermit,
			Pager<ProjectExpend> pager) {		
		return projectExpendDao.queryProjectExpend(projectExpend, userPermit, pager);
	}

	@Override
	public void verifyProjectExpend(ProjectExpend projectExpend) {
		int size = projectExpendDao.verifyProjectExpend(projectExpend);		
		if(size == 0) throw new PMException (CommonErrorConstants.e029901);
	}

	@Override
	public void unVerify(String id) {
		ProjectExpend projectExpend = new ProjectExpend();
		projectExpend.setProject_expend_id(id);
		int size = projectExpendDao.unVerifyProjectExpend(projectExpend);
		if(size == 0) throw new PMException (CommonErrorConstants.e029902);
	}

}
