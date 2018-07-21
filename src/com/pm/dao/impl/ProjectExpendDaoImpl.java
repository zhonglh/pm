package com.pm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.IProjectExpendDao;
import com.pm.domain.business.ProjectExpend;
import com.pm.vo.UserPermit;

@Component
public class ProjectExpendDaoImpl extends BatisDao implements IProjectExpendDao {


	@Override
	public int addProjectExpend(ProjectExpend projectExpend) {
		String sql = "ProjectExpendMapping.addProjectExpend";
		return this.insert(sql, projectExpend);

	}

	@Override
	public int  updateProjectExpend(ProjectExpend projectExpend) {
		String sql = "ProjectExpendMapping.updateProjectExpend";
		return this.update(sql, projectExpend);
	}
	

	public int doPay(ProjectExpend projectExpend) {
		String sql = "ProjectExpendMapping.doPay";
		return this.update(sql, projectExpend);
		
	}

	@Override
	public void deleteProjectExpend(ProjectExpend projectExpend) {
		String sql = "ProjectExpendMapping.deleteProjectExpend";
		this.delete(sql, projectExpend);
	}	
	

	@Override
	public int verifyProjectExpend(ProjectExpend projectExpend){
		String sql = "ProjectExpendMapping.verifyProjectExpend";
		return this.update(sql, projectExpend);
	}
	

	@Override
	public int unVerifyProjectExpend(ProjectExpend projectExpend) {

		String sql = "ProjectExpendMapping.unVerifyProjectExpend";
		return this.update(sql, projectExpend);
	}
	

	@Override
	public ProjectExpend getProjectExpend(String project_expend_id) {
		String sql = "ProjectExpendMapping.getProjectExpend";
		ProjectExpend projectExpend = new ProjectExpend();
		projectExpend.setProject_expend_id(project_expend_id);
		List<ProjectExpend> list = this.query(sql, ProjectExpend.class, projectExpend);
		if(list == null || list.isEmpty()) return null;
		else return list.get(0);		
	}

	@Override
	public Pager<ProjectExpend> queryProjectExpend(
			ProjectExpend projectExpend, 
			UserPermit userPermit,
			Pager<ProjectExpend> pager) {
		String sql = "ProjectExpendMapping.queryProjectExpend";		
		Pager<ProjectExpend> pager1 =  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, ProjectExpend.class, projectExpend,userPermit);
		
		return pager1;
		
	}

}
