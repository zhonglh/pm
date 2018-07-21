package com.pm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.common.daos.BatisDao;
import com.pm.dao.IDepartObjectiveDao;
import com.pm.domain.business.DepartObjective;

@Component
public class DepartObjectiveDaoImpl extends BatisDao implements IDepartObjectiveDao  {

	@Override
	public int addDepartObjective(DepartObjective departObjective) {
		String sql = "DepartObjectiveMapping.addDepartObjective";
		return this.insert(sql, departObjective);
	}

	@Override
	public int updateDepartObjective(DepartObjective departObjective) {
		String sql = "DepartObjectiveMapping.updateDepartObjective";
		return this.update(sql, departObjective);
	}

	@Override
	public void deleteDepartObjective(DepartObjective departObjective) {
		String sql = "DepartObjectiveMapping.deleteDepartObjective";
		this.delete(sql, departObjective);
	}


	@Override
	public DepartObjective getDepartObjective(String id) {

		String sql = "DepartObjectiveMapping.getDepartObjective"; 
		DepartObjective departObjective = new DepartObjective(); 
		departObjective.setId(id); 
		List<DepartObjective> list = this.query(sql, DepartObjective.class, departObjective); 
		if(list == null || list.isEmpty()) return null; 
		else return list.get(0);	
	}


	@Override
	public List<DepartObjective> getDepartObjectiveBySearch(DepartObjective departObjective) {

		String sql = "DepartObjectiveMapping.getDepartObjectiveBySearch"; 
		return this.query(sql, DepartObjective.class, departObjective);
	}

}