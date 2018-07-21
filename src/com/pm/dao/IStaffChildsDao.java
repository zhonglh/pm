package com.pm.dao;

import com.pm.domain.business.StaffChilds;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;

public interface IStaffChildsDao {


	public void deleteStaffChilds(StaffChilds staffChilds) ;

	public void processStaffChilds(String staff_id) ;	

}