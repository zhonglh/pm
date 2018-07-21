package com.pm.service;

import java.util.List;

import com.pm.domain.business.DepartObjective;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;

public interface IDepartObjectiveService  {

	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_DEPARTOBJECTIVE)
	public int addDepartObjective(DepartObjective departObjective) ;

	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_DEPARTOBJECTIVE)
	public int updateDepartObjective(DepartObjective departObjective) ; 

	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.ENTITY_DEPARTOBJECTIVE)
	public void deleteDepartObjective(DepartObjective[] departObjectives) ;

	
	public DepartObjective getDepartObjective(String id) ;	

	public List<DepartObjective> getDepartObjectiveBySearch(DepartObjective departObjective) ;
	
}