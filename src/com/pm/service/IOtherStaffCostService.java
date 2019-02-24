package com.pm.service;

import com.common.beans.Pager;
import com.pm.domain.business.OtherStaffCost;
import com.pm.domain.business.OtherSalary;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;
import com.pm.vo.UserPermit;

import java.util.List;

/**
 * @author Administrator
 */
public interface IOtherStaffCostService {
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_OTHER_STAFF_COST)
	public void addOtherStaffCost(List<OtherStaffCost> otherStaffCosts) ;

	public List<OtherStaffCost> computeOtherStaffCost(OtherSalary[] salarys) ;

	public Pager<OtherStaffCost> queryOtherStaffCost(OtherStaffCost otherStaffCost, UserPermit userPermit, Pager<OtherStaffCost> pager);	

	public OtherStaffCost getOtherStaffCost(OtherStaffCost otherStaffCost) ;
	
	
	
	
	
	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.ENTITY_OTHER_STAFF_COST)
	public void deleteOtherStaffCost(OtherSalary[] salarys) ;	

	public List<OtherStaffCost> getOtherStaffCost(OtherSalary[] salarys) ;

	
	
}
