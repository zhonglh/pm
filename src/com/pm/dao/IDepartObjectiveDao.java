package com.pm.dao;

import java.util.List;

import com.pm.domain.business.DepartObjective;

public interface IDepartObjectiveDao {

	public int addDepartObjective(DepartObjective departObjective) ;

	public int updateDepartObjective(DepartObjective departObjective) ; 

	public void deleteDepartObjective(DepartObjective departObjective) ;


	public DepartObjective getDepartObjective(String id) ;	


	public List<DepartObjective> getDepartObjectiveBySearch(DepartObjective departObjective) ;
}