package com.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;import com.pm.domain.business.ProjectExpendPay;
import com.pm.dao.IProjectExpendPayDao;
import com.pm.service.IProjectExpendPayService;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;

@Component
public class ProjectExpendPayServiceImpl implements  IProjectExpendPayService {

	@Autowired IProjectExpendPayDao projectExpendpayDao;
	@Override
	public int addProjectExpendPay(ProjectExpendPay projectExpendpay) {
		return projectExpendpayDao.addProjectExpendPay(projectExpendpay);
	}

	@Override
	public int updateProjectExpendPay(ProjectExpendPay projectExpendpay) {
		return projectExpendpayDao.updateProjectExpendPay(projectExpendpay);
	}

	@Override
	public void deleteProjectExpendPay(ProjectExpendPay[] projectExpendpays) {
		for(ProjectExpendPay projectExpendpay : projectExpendpays){
			projectExpendpayDao.deleteProjectExpendPay(projectExpendpay);
		}
	}

	@Override
	public void verifyProjectExpendPay(ProjectExpendPay projectExpendpay) {
		projectExpendpayDao.verifyProjectExpendPay(projectExpendpay);
	}

	@Override
	public void unVerify(String id) {
		ProjectExpendPay projectExpendpay = new ProjectExpendPay();
		projectExpendpay.setId(id);
		projectExpendpayDao.unVerifyProjectExpendPay(projectExpendpay);
	}

	@Override
	public ProjectExpendPay getProjectExpendPay(String id) {
		return projectExpendpayDao.getProjectExpendPay(id);
	}

	@Override
	public Pager<ProjectExpendPay> queryProjectExpendPay(
		ProjectExpendPay projectExpendpay,
		UserPermit userPermit,
		Pager<ProjectExpendPay> pager){

		return projectExpendpayDao.queryProjectExpendPay(projectExpendpay, userPermit, pager);
	}


	@Override
	public <T> T get(String id) {
		return (T)this.getProjectExpendPay(id);
	}
}