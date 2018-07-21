package com.pm.dao;

import com.common.beans.Pager;
import com.pm.domain.business.DepartCosts;
import com.pm.vo.UserPermit;

public interface IDepartCostsDao {


	public int addDepartCosts(DepartCosts departCosts) ;
	

	public int updateDepartCosts(DepartCosts departCosts) ; 
	

	public void deleteDepartCosts(DepartCosts departCostss) ;
	
	public int verifyDepartCosts(DepartCosts departCosts);
	
	public int unVerifyDepartCosts(DepartCosts departCosts);
	
	
	public DepartCosts getDepartCosts(String id) ;	
	

	public Pager<DepartCosts> queryDepartCosts(DepartCosts departCosts,  UserPermit userPermit,Pager<DepartCosts> pager);

	
	
}
