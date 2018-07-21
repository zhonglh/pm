package com.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.exceptions.CommonErrorConstants;
import com.common.exceptions.PMException;
import com.pm.dao.IReimburseCostsDao;
import com.pm.domain.business.ReimburseCosts;
import com.pm.service.IReimburseCostsService;
import com.pm.vo.UserPermit;

@Component
public class ReimburseCostsServiceImpl implements IReimburseCostsService {


	@Autowired IReimburseCostsDao reimburseCostsDao;
	
	@Override
	public int addReimburseCosts(ReimburseCosts reimburseCosts) {
		return reimburseCostsDao.addReimburseCosts(reimburseCosts);
	}

	@Override
	public int updateReimburseCosts(ReimburseCosts reimburseCosts) {
		return reimburseCostsDao.updateReimburseCosts(reimburseCosts);
	}

	@Override
	public void deleteReimburseCosts(ReimburseCosts[] reimburseCostss) {
		for(ReimburseCosts reimburseCosts : reimburseCostss){
			reimburseCostsDao.deleteReimburseCosts(reimburseCosts);
		}

	}

	@Override
	public ReimburseCosts getReimburseCosts(String reimburse_id) {		
		return reimburseCostsDao.getReimburseCosts(reimburse_id);
	}

	@Override
	public Pager<ReimburseCosts> queryReimburseCosts(
			ReimburseCosts reimburseCosts, UserPermit userPermit,
			Pager<ReimburseCosts> pager) {		
		return reimburseCostsDao.queryReimburseCosts(reimburseCosts, userPermit, pager);
	}

	@Override
	public void verifyReimburseCosts(ReimburseCosts reimburseCosts) {
		int size = reimburseCostsDao.verifyReimburseCosts(reimburseCosts);
		if(size == 0) throw new PMException (CommonErrorConstants.e029901);
		
	}

	@Override
	public void unVerify(String id) {
		ReimburseCosts reimburseCosts = new ReimburseCosts();
		reimburseCosts.setReimburse_id(id);
		int size = reimburseCostsDao.unVerifyReimburseCosts(reimburseCosts);
		if(size == 0) throw new PMException (CommonErrorConstants.e029902);
		
	}

}
