package com.pm.service;

import com.common.beans.Pager;
import com.pm.domain.business.ProjectExpend;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;
import com.pm.vo.UserPermit;

public interface IProjectExpendService extends IBaseService {
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_PROJECT_EXPEND)
	public int addProjectExpend(ProjectExpend projectExpend) ;
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_PROJECT_EXPEND)
	public int updateProjectExpend(ProjectExpend projectExpend) ; 
	
	
	
	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_PROJECT_EXPEND)
	public int doPay(ProjectExpend projectExpend) ; 
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.ENTITY_PROJECT_EXPEND)
	public void deleteProjectExpend(ProjectExpend[] projectExpends) ;


	@LogAnnotation(operation_type=LogConstant.OPERATION_CHECK,entity_type=LogConstant.ENTITY_PROJECT_EXPEND)
	public void verifyProjectExpend(ProjectExpend projectExpend) ;
	
	
	
	public ProjectExpend getProjectExpend(String project_expend_id) ;	
	

	public Pager<ProjectExpend> queryProjectExpend(ProjectExpend projectExpend,  UserPermit userPermit,Pager<ProjectExpend> pager);
	
	
}
