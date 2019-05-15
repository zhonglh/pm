package com.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.pm.dao.IInsuranceDao;
import com.pm.dao.IStaffCostDao;
import com.pm.domain.business.Insurance;
import com.pm.domain.business.StaffCost;
import com.pm.service.IInsuranceService;
import com.pm.vo.UserPermit;

@Component
public class InsuranceServiceImpl implements  IInsuranceService {

	@Autowired
	@Qualifier("insuranceDaoImpl")
	private IInsuranceDao insuranceDao;

	@Autowired
	private IStaffCostDao staffCostDao;
	
	
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
			StaffCost staffCost = new StaffCost();
			staffCost.setStaff_id(insurance.getStaff_id());
			staffCost.setSecurty_unit(insurance.getSecurty_unit());
			staffCostDao.updateStaffCostSecurtyUnit(staffCost);
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
	public List<Insurance> queryInsuranceByWorkAttendance(Insurance insurance){
		return insuranceDao.queryInsuranceByWorkAttendance(insurance);
	}



	@Override
	public boolean isExistNotCheckByWorkAttendance(Insurance insurance){
		int size = insuranceDao.getNotCheckNumByWorkAttendance(insurance);
		return size != 0;
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