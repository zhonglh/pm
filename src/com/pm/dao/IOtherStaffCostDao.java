package com.pm.dao;

import com.common.beans.Pager;
import com.pm.domain.business.OtherStaffCost;
import com.pm.domain.business.OtherSalary;
import com.pm.vo.UserPermit;

import java.util.List;

public interface IOtherStaffCostDao {
	

	
	public void addOtherStaffCost(OtherStaffCost otherStaffCost) ;
	
	
	public void deleteOtherStaffCost(OtherSalary salary) ;
	
	public Pager<OtherStaffCost> queryOtherStaffCost(OtherStaffCost otherStaffCost, UserPermit userPermit, Pager<OtherStaffCost> pager);

	public OtherStaffCost queryOtherStaffCostSum(OtherStaffCost otherStaffCost, UserPermit userPermit);
	
	public OtherStaffCost getOtherStaffCost(OtherStaffCost otherStaffCost) ;

	public List<OtherStaffCost> getOtherStaffCost(OtherSalary[] salarys) ;
	
	public List<OtherStaffCost> computeOtherStaffCost(OtherSalary salary) ;
	

}
