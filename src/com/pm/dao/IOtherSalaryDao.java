package com.pm.dao;

import com.common.beans.Pager;
import com.pm.domain.business.OtherSalary;
import com.pm.vo.UserPermit;

import java.util.List;

public interface IOtherSalaryDao {

	
	public void addSalary(OtherSalary salary) ;  
	

	
	public void updateSalary(OtherSalary salary) ; 
	

	
	public void deleteSalary(OtherSalary salary) ; 
	

	public int verifySalary(OtherSalary salary) ; 
	

	public int unVerifySalary(OtherSalary salary) ; 
	

	public OtherSalary getSalary(String salary_id) ;
	

	public List<OtherSalary> getSalaryByDeptMonth(OtherSalary salary) ;


	public List<OtherSalary> getAccumulatedSalary(int startSalaryMonth, int endSalaryMonth, List<String> staffCostIds);


	public Pager<OtherSalary> querySalary(OtherSalary salary, UserPermit userPermit, Pager<OtherSalary> pager);	
	
	
	

	public Pager<OtherSalary> querySalaryGroup(OtherSalary salary, UserPermit userPermit, Pager<OtherSalary> pager);	

}
