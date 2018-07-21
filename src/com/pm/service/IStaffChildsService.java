package com.pm.service;

import com.pm.domain.business.StaffChilds;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;
import com.pm.service.IGeneralLogService;

public interface IStaffChildsService  {
	


	public void doProcessStaffChilds(String staff_id) ;	


	public void deleteStaffChilds(StaffChilds[] staffChildss) ;


}