package com.pm.dao;

import java.util.List;

import com.common.beans.Pager;
import com.pm.domain.business.Salary;
import com.pm.vo.UserPermit;

public interface ISalaryDao {

	
	public void addSalary(Salary salary) ;  
	

	
	public void updateSalary(Salary salary) ; 
	

	
	public void deleteSalary(Salary salary) ; 
	

	public int verifySalary(Salary salary) ; 
	

	public int unVerifySalary(Salary salary) ; 
	

	public Salary getSalary(String salary_id) ;
	

	public List<Salary> getSalaryByProjectMonth(Salary salary) ;


	public List<Salary> getAccumulatedSalary(int startSalaryMonth , int endSalaryMonth , List<String> staffCostIds);


	public Pager<Salary> querySalary(Salary salary, UserPermit userPermit,Pager<Salary> pager);	
	
	
	

	public Pager<Salary> querySalaryGroup(Salary salary, UserPermit userPermit,Pager<Salary> pager);	

}
