package com.pm.service.impl;

import com.pm.dao.IProjectExpendDao;
import com.pm.domain.business.ProjectExpend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;import com.pm.domain.business.ProjectExpendPay;
import com.pm.dao.IProjectExpendPayDao;
import com.pm.service.IProjectExpendPayService;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;

/**
 * @author Administrator
 */
@Component
public class ProjectExpendPayServiceImpl implements  IProjectExpendPayService {

	@Autowired
	IProjectExpendPayDao projectExpendpayDao;

	@Autowired
	IProjectExpendDao projectExpendDao;

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


		doProcessProjectExtend4Verify(projectExpendpay.getProject_expend_id());
	}

	@Override
	public void unVerify(String id) {
		ProjectExpendPay projectExpendpay = new ProjectExpendPay();
		projectExpendpay.setId(id);
		projectExpendpayDao.unVerifyProjectExpendPay(projectExpendpay);

		doProcessProjectExtend4Verify(id);
	}


	private void doProcessProjectExtend4Verify(String id){
		ProjectExpendPay projectExpendpay = projectExpendpayDao.getProjectExpendPay(id);
		String project_expend_id = projectExpendpay.getProject_expend_id();
		projectExpendpay = getSumByProjectExpend(projectExpendpay);

		ProjectExpend projectExpend = new ProjectExpend();
		if(projectExpendpay != null ){
			projectExpend.setPay_amount(projectExpendpay.getPay_amount());
			projectExpend.setPay_date(projectExpendpay.getPay_date());
		}
		projectExpend.setProject_expend_id(project_expend_id);
		projectExpendDao.doPay(projectExpend);

	}

	@Override
	public ProjectExpendPay getProjectExpendPay(String id) {
		return projectExpendpayDao.getProjectExpendPay(id);
	}


	@Override
	public ProjectExpendPay getSumByProjectExpend(ProjectExpendPay projectExpendpay) {
		return projectExpendpayDao.getSumByProjectExpend(projectExpendpay);
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