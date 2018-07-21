package com.pm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.IReimburseCostsDao;
import com.pm.domain.business.ReimburseCosts;
import com.pm.vo.UserPermit;


@Component
public class ReimburseCostsDaoImpl extends BatisDao implements IReimburseCostsDao {

	

	@Override
	public int addReimburseCosts(ReimburseCosts reimburseCosts) {
		String sql = "ReimburseCostsMapping.addReimburseCosts";
		return this.insert(sql,reimburseCosts);

	}

	@Override
	public int updateReimburseCosts(ReimburseCosts reimburseCosts) {
		String sql = "ReimburseCostsMapping.updateReimburseCosts";
		return this.update(sql,reimburseCosts);
	}

	@Override
	public void deleteReimburseCosts(ReimburseCosts reimburseCosts) {
		String sql = "ReimburseCostsMapping.deleteReimburseCosts";
		this.delete(sql,reimburseCosts);

	}
	

	@Override
	public int verifyReimburseCosts(ReimburseCosts reimburseCosts){
		String sql = "ReimburseCostsMapping.verifyReimburseCosts";
		return this.update(sql,reimburseCosts);		
	}
		

	@Override
	public int unVerifyReimburseCosts(ReimburseCosts reimburseCosts){
		String sql = "ReimburseCostsMapping.unVerifyReimburseCosts";
		return this.update(sql,reimburseCosts);		
	}

	@Override
	public ReimburseCosts getReimburseCosts(String reimburse_id) {
		String sql = "ReimburseCostsMapping.getReimburseCosts";
		ReimburseCosts reimburseCosts = new ReimburseCosts();
		reimburseCosts.setReimburse_id(reimburse_id);
		List<ReimburseCosts> list = this.query(sql, ReimburseCosts.class, reimburseCosts);
		if(list == null || list.isEmpty()) return null;
		else return list.get(0);
	}

	@Override
	public Pager<ReimburseCosts> queryReimburseCosts(
			ReimburseCosts reimburseCosts, UserPermit userPermit,
			Pager<ReimburseCosts> pager) {
		String sql = "ReimburseCostsMapping.queryReimburseCosts";
		Pager<ReimburseCosts> pager1 = this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, ReimburseCosts.class, reimburseCosts,userPermit);

		return pager1;
	}

}
