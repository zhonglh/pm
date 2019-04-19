package com.pm.dao;

import java.util.List;

import com.common.beans.Pager;
import com.pm.domain.business.OtherStaff;
import com.pm.vo.UserPermit;

public interface IOtherStaffDao {
	

	public int addOtherStaff(OtherStaff otherStaff) ;
	

	public int updateOtherStaff(OtherStaff otherStaff) ;


	public int updateStaffCostSecurtyUnit(OtherStaff otherStaff) ;

	

	public void deleteOtherStaff(OtherStaff otherStaff) ;
	
	
	public OtherStaff getOtherStaff(String staff_id) ;


	public List<OtherStaff> getOtherStaffByInsurance(OtherStaff otherStaff);
	
	
	public boolean isExist(OtherStaff otherStaff);
	

	public Pager<OtherStaff> queryOtherStaff(OtherStaff otherStaff, UserPermit userPermit,Pager<OtherStaff> pager);
	

	public List<OtherStaff> queryOtherStaffByProjectSales(OtherStaff otherStaff,UserPermit userPermit);

}
