package com.pm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pm.domain.business.MonthlyStatement;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.IProjectStaffCostDao;
import com.pm.domain.business.ProjectStaffCost;
import com.pm.domain.business.Salary;
import com.pm.vo.UserPermit;

@Component
public class ProjectStaffCostDaoImpl extends BatisDao implements	IProjectStaffCostDao {


	@Override
	public void addProjectStaffCost(ProjectStaffCost projectStaffCost) {
		String sql = "ProjectStaffCostMapping.addProjectStaffCost";
		this.insert(sql,projectStaffCost);

	}

	@Override
	public void deleteProjectStaffCost(Salary salary) {
		String sql = "ProjectStaffCostMapping.deleteProjectStaffCost";
		this.delete(sql,salary);
	}

	@Override
	public Pager<ProjectStaffCost> queryProjectStaffCost(
			ProjectStaffCost projectStaffCost, 
			UserPermit userPermit,
			Pager<ProjectStaffCost> pager) {

		String sql = "ProjectStaffCostMapping.queryProjectStaffCost";
		Pager<ProjectStaffCost> pager1 =  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, ProjectStaffCost.class, projectStaffCost,userPermit);
		
		return pager1;
		
		
	}


	@Override
	public ProjectStaffCost queryProjectStaffCostSum(ProjectStaffCost projectStaffCost, UserPermit userPermit ){
		String sql = "ProjectStaffCostMapping.queryProjectStaffCostSum";
		Map  map = new HashMap();
		if(projectStaffCost !=null) {
			map.put(projectStaffCost.getClass().getSimpleName(), projectStaffCost);
		}
		if(userPermit !=null) {
			map.put(userPermit.getClass().getSimpleName(), userPermit);
		}

		return this.query(sql,ProjectStaffCost.class, map).get(0);
	}

	@Override
	public ProjectStaffCost getProjectStaffCost(ProjectStaffCost projectStaffCost) {
		String sql = "ProjectStaffCostMapping.getProjectStaffCost";	
		List<ProjectStaffCost> list = this.query(sql, ProjectStaffCost.class, projectStaffCost);
		if(list == null || list.isEmpty()) return null;
		else return list.get(0);		
	}

	@Override
	public List<ProjectStaffCost> getProjectStaffCost(Salary[] salarys) {
		String sql = "ProjectStaffCostMapping.getProjectStaffCostBySalary";
		Map<String,Salary[]> map = new HashMap<String,Salary[]>();
		map.put("list", salarys);
		return this.query(sql, ProjectStaffCost.class, map);
	}

	@Override
	public List<ProjectStaffCost> computeProjectStaffCost(Salary salary) {
		String sql = "ProjectStaffCostMapping.computeProjectStaffCost";
		return this.query(sql, ProjectStaffCost.class, salary);
	}
	

}
