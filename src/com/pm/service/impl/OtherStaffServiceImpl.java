package com.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.pm.dao.IOtherStaffDao;
import com.pm.dao.IUserDao;
import com.pm.domain.business.OtherStaff;
import com.pm.service.IOtherStaffService;
import com.pm.util.constant.EnumStaffType;
import com.pm.vo.UserPermit;

@Component
public class OtherStaffServiceImpl implements IOtherStaffService {

	@Autowired IUserDao userDao;
	@Autowired IOtherStaffDao otherStaffDao; 
	
	@Override
	public int addOtherStaff(OtherStaff otherStaff) {
		return otherStaffDao.addOtherStaff(otherStaff);
	}

	@Override
	public int updateOtherStaff(OtherStaff otherStaff) {
		userDao.updateUser(otherStaff.getStaff_name(), otherStaff.getDept_id(), otherStaff.getStaff_id(), EnumStaffType.AdminStaff.name());
		return otherStaffDao.updateOtherStaff(otherStaff);
	}

	@Override
	public void deleteOtherStaff(OtherStaff[] otherStaffs) {
		for(OtherStaff otherStaff : otherStaffs){	
				
				userDao.deleteUser(
						otherStaff.getStaff_id(),
						EnumStaffType.AdminStaff.name(),
						otherStaff.getDelete_userid(),
						otherStaff.getDelete_username()
				);
				
				otherStaffDao.deleteOtherStaff(otherStaff);
		}
	}

	@Override
	public OtherStaff getOtherStaff(String staff_id) {
		
		return otherStaffDao.getOtherStaff(staff_id);
	}

	@Override
	public boolean isExist(OtherStaff otherStaff) {		
		return otherStaffDao.isExist(otherStaff);
	}

	@Override
	public Pager<OtherStaff> queryOtherStaff(OtherStaff otherStaff, UserPermit userPermit,Pager<OtherStaff> pager){
		return otherStaffDao.queryOtherStaff(otherStaff, userPermit, pager);
	}
	
	

	@Override
	public List<OtherStaff> queryOtherStaffByProjectSales(OtherStaff otherStaff,UserPermit userPermit){
		return otherStaffDao.queryOtherStaffByProjectSales(otherStaff,userPermit);
	}

}
