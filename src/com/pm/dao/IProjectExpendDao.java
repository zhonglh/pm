package com.pm.dao;

import com.common.beans.Pager;
import com.pm.domain.business.ProjectExpend;
import com.pm.vo.UserPermit;

public interface IProjectExpendDao {



	public int addProjectExpend(ProjectExpend projectExpend) ;
	

	public int updateProjectExpend(ProjectExpend projectExpend) ; 
	
	public int doPay(ProjectExpend projectExpend) ; 
	

	public void deleteProjectExpend(ProjectExpend projectExpends) ;
	
	public int verifyProjectExpend(ProjectExpend projectExpend) ;	
	
	
	public int unVerifyProjectExpend(ProjectExpend projectExpend) ;
	
	
	public ProjectExpend getProjectExpend(String project_expend_id) ;	
	

	public Pager<ProjectExpend> queryProjectExpend(ProjectExpend projectExpend,  UserPermit userPermit,Pager<ProjectExpend> pager);
	
	
	
}
