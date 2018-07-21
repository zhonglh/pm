package com.pm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.common.daos.BatisDao;
import com.pm.dao.IApplyApproveDao;
import com.pm.domain.business.ApplyApprove;



@Component
public class ApplyApproveDaoImpl extends BatisDao  implements IApplyApproveDao {

	@Override
	public List<ApplyApprove> queryByDataId(String dataType, String dataId) {
		String sql = "ApplyApproveMapping.queryByDataId";
		ApplyApprove applyApprove = new ApplyApprove();
		applyApprove.setData_id(dataId);
		applyApprove.setData_type(dataType);
		return this.query(sql, ApplyApprove.class, applyApprove);
	}

	@Override
	public int addApplyApprove(ApplyApprove applyApprove) {
		String sql = "ApplyApproveMapping.addApplyApprove";
		return this.insert(sql, applyApprove);
		
	}

	@Override
	public int updateApplyApprove(ApplyApprove applyApprove) {
		String sql = "ApplyApproveMapping.updateApplyApprove";
		return this.insert(sql, applyApprove);
	}

	@Override
	public void deleteApplyApprove(ApplyApprove applyApprove) {
		String sql = "ApplyApproveMapping.deleteApplyApprove";
		this.insert(sql, applyApprove);
	}

	@Override
	public ApplyApprove needHandle(String dataType, String dataId) {
		String sql = "ApplyApproveMapping.needHandle";
		ApplyApprove applyApprove = new ApplyApprove();
		applyApprove.setData_id(dataId);
		applyApprove.setData_type(dataType);
		List<ApplyApprove> list =  this.query(sql, ApplyApprove.class, applyApprove);
		if(list != null && list.size() >0) return list.get(0);
		else return null;
	}


}
