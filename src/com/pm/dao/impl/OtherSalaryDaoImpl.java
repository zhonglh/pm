package com.pm.dao.impl;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.IOtherSalaryDao;
import com.pm.dao.ISalaryDao;
import com.pm.domain.business.OtherSalary;
import com.pm.vo.UserPermit;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OtherSalaryDaoImpl extends BatisDao implements IOtherSalaryDao {



	@Override
	public void addSalary(OtherSalary salary) {
		String sql = "OtherSalaryMapping.addSalary";
		this.insert(sql, salary);		
	}

	@Override
	public void updateSalary(OtherSalary salary) {
		String sql = "OtherSalaryMapping.updateSalary";
		this.update(sql, salary);		
	}

	@Override
	public void deleteSalary(OtherSalary salary) {
		String sql = "OtherSalaryMapping.deleteSalary";
		this.delete(sql, salary);	
	}
	
	

	@Override
	public int verifySalary(OtherSalary salary) {

		String sql = "OtherSalaryMapping.verifySalary";
		return this.update(sql, salary);	
	}
	

	@Override
	public int unVerifySalary(OtherSalary salary) {
		String sql = "OtherSalaryMapping.unVerifySalary";
		return this.update(sql, salary);
	}

	@Override
	public OtherSalary getSalary(String salary_id) {
		OtherSalary salary = new OtherSalary();
		salary.setSalary_id(salary_id);
		String sql = "OtherSalaryMapping.getSalary";
		List<OtherSalary> list = this.query(sql, OtherSalary.class, salary);
		if(list == null || list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
	}
	

	@Override
	public List<OtherSalary> getSalaryByDeptMonth(OtherSalary salary) {
		String sql = "OtherSalaryMapping.getSalaryByDeptMonth";
		return this.query(sql, OtherSalary.class, salary);
	}



	@Override
	public List<OtherSalary> getAccumulatedSalary(int startSalaryMonth , int endSalaryMonth , List<String> staffCostIds){
		String sql = "OtherSalaryMapping.getAccumulatedSalary";
		OtherSalary salary = new OtherSalary();
		salary.setStartSalaryMonth(startSalaryMonth);
		salary.setEndSalaryMonth(endSalaryMonth);
		salary.setStaffCostIds(staffCostIds);
		return this.query(sql, OtherSalary.class, salary);
	}

	@Override
	public Pager<OtherSalary> querySalary(
			OtherSalary salary, 
			UserPermit userPermit,
			Pager<OtherSalary> pager) {

		String sql = "OtherSalaryMapping.querySalary";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, OtherSalary.class, salary,userPermit);
	}
	
	
	

	@Override
	public Pager<OtherSalary> querySalaryGroup(
			OtherSalary salary, 
			UserPermit userPermit,
			Pager<OtherSalary> pager){

		String sql = "OtherSalaryMapping.querySalaryGroup";
		Pager<OtherSalary> pager1 =  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, OtherSalary.class, salary,userPermit);
		
			
		return pager1;
		
		
	}

}
