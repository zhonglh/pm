package com.pm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.pm.dao.IProjectExpendPayDao;
import com.pm.domain.business.ProjectExpendPay;
import com.pm.vo.UserPermit;

import com.common.daos.BatisDao;
import com.common.beans.Pager;

@Component
public class ProjectExpendPayDaoImpl extends BatisDao implements IProjectExpendPayDao  {

	@Override
	public int addProjectExpendPay(ProjectExpendPay projectExpendpay) {
		String sql = "ProjectExpendPayMapping.addProjectExpendPay";
		return this.insert(sql, projectExpendpay);
	}

	@Override
	public int updateProjectExpendPay(ProjectExpendPay projectExpendpay) {
		String sql = "ProjectExpendPayMapping.updateProjectExpendPay";
		return this.update(sql, projectExpendpay);
	}

	@Override
	public void deleteProjectExpendPay(ProjectExpendPay projectExpendpay) {
		String sql = "ProjectExpendPayMapping.deleteProjectExpendPay";
		this.delete(sql, projectExpendpay);
	}

	@Override
	public void verifyProjectExpendPay(ProjectExpendPay projectExpendpay) {
		String sql = "ProjectExpendPayMapping.verifyProjectExpendPay";
		this.update(sql, projectExpendpay);
	}

	@Override
	public void unVerifyProjectExpendPay(ProjectExpendPay projectExpendpay) {
		String sql = "ProjectExpendPayMapping.unVerifyProjectExpendPay";
		this.update(sql, projectExpendpay);
	}

	@Override
	public ProjectExpendPay getProjectExpendPay(String id) {

		String sql = "ProjectExpendPayMapping.getProjectExpendPay"; 
		ProjectExpendPay projectExpendpay = new ProjectExpendPay(); 
		projectExpendpay.setId(id); 
		List<ProjectExpendPay> list = this.query(sql, ProjectExpendPay.class, projectExpendpay); 
		if(list == null || list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
	}

	@Override
	public Pager<ProjectExpendPay> queryProjectExpendPay(
		ProjectExpendPay projectExpendpay,
		UserPermit userPermit,
		Pager<ProjectExpendPay> pager){

		String sql = "ProjectExpendPayMapping.queryProjectExpendPay"; 
		Pager<ProjectExpendPay> pager1 =  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql,ProjectExpendPay.class, projectExpendpay,userPermit); 
		sql = "ProjectExpendPayMapping.queryProjectExpendPayTotalAmount";
		Map<String,Object> map = new HashMap<String,Object>();
		if(projectExpendpay != null) {
			map.put(projectExpendpay.getClass().getSimpleName(), projectExpendpay);
		}
		if(userPermit != null) {
			map.put(userPermit.getClass().getSimpleName(), userPermit);
		}
		Double amount = getSqlSession().selectOne(sql,map);
		if(amount != null) {
			double total_amount = amount.doubleValue();
			pager1.setTotal_amount(total_amount);
		}
		return pager1;
	}


}