package com.pm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.ISalaryDao;
import com.pm.domain.business.Salary;
import com.pm.vo.UserPermit;

@Component
public class SalaryDaoImpl extends BatisDao implements ISalaryDao {



	@Override
	public void addSalary(Salary salary) {
		String sql = "SalaryMapping.addSalary";
		this.insert(sql, salary);		
	}

	@Override
	public void updateSalary(Salary salary) {
		String sql = "SalaryMapping.updateSalary";
		this.update(sql, salary);		
	}

	@Override
	public void deleteSalary(Salary salary) {
		String sql = "SalaryMapping.deleteSalary";
		this.delete(sql, salary);	
	}
	
	

	@Override
	public int verifySalary(Salary salary) {

		String sql = "SalaryMapping.verifySalary";
		return this.update(sql, salary);	
	}
	

	@Override
	public int unVerifySalary(Salary salary) {
		String sql = "SalaryMapping.unVerifySalary";
		return this.update(sql, salary);	
	}

	@Override
	public Salary getSalary(String salary_id) {
		Salary salary = new Salary();
		salary.setSalary_id(salary_id);
		String sql = "SalaryMapping.getSalary";
		List<Salary> list = this.query(sql, Salary.class, salary);
		if(list == null || list.isEmpty()) return null;
		else return list.get(0);
	}
	

	@Override
	public List<Salary> getSalaryByProjectMonth(Salary salary) {
		String sql = "SalaryMapping.getSalaryByProjectMonth";
		return this.query(sql, Salary.class, salary);
	}	

	@Override
	public Pager<Salary> querySalary(
			Salary salary, 
			UserPermit userPermit,
			Pager<Salary> pager) {

		String sql = "SalaryMapping.querySalary";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Salary.class, salary,userPermit);
	}
	
	
	

	@Override
	public Pager<Salary> querySalaryGroup(
			Salary salary, 
			UserPermit userPermit,
			Pager<Salary> pager){

		String sql = "SalaryMapping.querySalaryGroup";
		Pager<Salary> pager1 =  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Salary.class, salary,userPermit);
		
			
		return pager1;
		
		
	}

}
