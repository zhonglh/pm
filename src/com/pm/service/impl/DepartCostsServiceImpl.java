package com.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.exceptions.CommonErrorConstants;
import com.common.exceptions.PMException;
import com.pm.dao.IDepartCostsDao;
import com.pm.domain.business.DepartCosts;
import com.pm.service.IDepartCostsService;
import com.pm.vo.UserPermit;

@Component
public class DepartCostsServiceImpl implements IDepartCostsService {


	@Autowired IDepartCostsDao departCostsDao;
	
	@Override
	public int addDepartCosts(DepartCosts departCosts) {
		return departCostsDao.addDepartCosts(departCosts);
	}

	@Override
	public int updateDepartCosts(DepartCosts departCosts) {
		return departCostsDao.updateDepartCosts(departCosts);
	}

	@Override
	public void deleteDepartCosts(DepartCosts[] departCostss) {
		for(DepartCosts departCosts : departCostss){
			departCostsDao.deleteDepartCosts(departCosts);
		}

	}

	@Override
	public DepartCosts getDepartCosts(String id) {		
		return departCostsDao.getDepartCosts(id);
	}

	@Override
	public Pager<DepartCosts> queryDepartCosts(
			DepartCosts departCosts, UserPermit userPermit,
			Pager<DepartCosts> pager) {		
		return departCostsDao.queryDepartCosts(departCosts, userPermit, pager);
	}

	@Override
	public void verifyDepartCosts(DepartCosts departCosts) {
		int size = departCostsDao.verifyDepartCosts(departCosts);
		if(size == 0) throw new PMException (CommonErrorConstants.e029901);
	}

	@Override
	public void unVerify(String id) {
		DepartCosts departCosts = new DepartCosts();
		departCosts.setId(id);
		int size = departCostsDao.unVerifyDepartCosts(departCosts);
		if(size == 0) throw new PMException (CommonErrorConstants.e029902);
		
	}

	@Override
	public <T> T get(String id) {
		return (T)departCostsDao.getDepartCosts(id);
	}

}
