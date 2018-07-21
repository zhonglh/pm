package com.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pm.dao.IDepartObjectiveDao;
import com.pm.domain.business.DepartObjective;
import com.pm.service.IDepartObjectiveService;

@Component
public class DepartObjectiveServiceImpl implements  IDepartObjectiveService {

	@Autowired IDepartObjectiveDao departObjectiveDao;
	
	@Override
	public int addDepartObjective(DepartObjective departObjective) {
		return departObjectiveDao.addDepartObjective(departObjective);
	}

	@Override
	public int updateDepartObjective(DepartObjective departObjective) {
		return departObjectiveDao.updateDepartObjective(departObjective);
	}

	@Override
	public void deleteDepartObjective(DepartObjective[] departObjectives) {
		for(DepartObjective departObjective : departObjectives){
			departObjectiveDao.deleteDepartObjective(departObjective);
		}
	}


	@Override
	public DepartObjective getDepartObjective(String id) {
		return departObjectiveDao.getDepartObjective(id);
	}

	@Override
	public List<DepartObjective> getDepartObjectiveBySearch(DepartObjective departObjective) {
		return departObjectiveDao.getDepartObjectiveBySearch(departObjective);
	}


}