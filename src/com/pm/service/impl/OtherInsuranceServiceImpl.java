package com.pm.service.impl;

import com.common.beans.Pager;
import com.pm.dao.IInsuranceDao;
import com.pm.dao.IOtherStaffDao;
import com.pm.domain.business.Insurance;
import com.pm.domain.business.OtherStaff;
import com.pm.service.IInsuranceService;
import com.pm.vo.UserPermit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OtherInsuranceServiceImpl implements  IInsuranceService {

	@Autowired
	@Qualifier("otherInsuranceDaoImpl")
	private IInsuranceDao insuranceDao;

	@Autowired
	private IOtherStaffDao otherStaffDao;
	
	
	@Override
	public int addInsurance(Insurance insurance) {
		return insuranceDao.addInsurance(insurance);
	}

	@Override
	public int updateInsurance(Insurance insurance) {
		return insuranceDao.updateInsurance(insurance);
	}

	@Override
	public void deleteInsurance(Insurance[] insurances) {
		for(Insurance insurance : insurances){
			insuranceDao.deleteInsurance(insurance);
		}
	}

	@Override
	public void verifyInsurance(Insurance insurance) {
		insuranceDao.verifyInsurance(insurance);
		if(insurance.getSecurty_unit() != null && insurance.getSecurty_unit().length()>0){
			OtherStaff otherStaff = new OtherStaff();
			otherStaff.setStaff_id(insurance.getStaff_id());
			otherStaff.setSecurty_unit(insurance.getSecurty_unit());
			otherStaffDao.updateStaffCostSecurtyUnit(otherStaff);
		}
	}

	@Override
	public void unVerify(String id) {
		Insurance insurance = new Insurance();
		insurance.setId(id);
		insuranceDao.unVerifyInsurance(insurance);
	}

	@Override
	public Insurance getInsurance(String id) {
		return insuranceDao.getInsurance(id);
	}



	@Override
	public boolean isExistNotCheckByWorkAttendance(Insurance insurance){
		int size = insuranceDao.getNotCheckNumByWorkAttendance(insurance);
		return size != 0;
	}
	@Override
	public List<Insurance> queryInsuranceByWorkAttendance(Insurance insurance){
		return insuranceDao.queryInsuranceByWorkAttendance(insurance);
	}

	@Override
	public Pager<Insurance> queryInsurance(
		Insurance insurance,
		UserPermit userPermit,
		Pager<Insurance> pager){

		Pager<Insurance> insurancePage =  insuranceDao.queryInsurance(insurance, userPermit, pager);
		Insurance insuranceSum = this.insuranceDao.queryInsuranceSum(insurance, userPermit);
		insurancePage.setResultObj(insuranceSum);
		
		return insurancePage;
	}

	@Override
	public <T> T get(String id) {
		return (T)getInsurance(id);
	}


}