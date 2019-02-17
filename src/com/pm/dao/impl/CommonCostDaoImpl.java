package com.pm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.pm.dao.ICommonCostDao;
import com.pm.domain.business.CommonCost;
import com.pm.vo.UserPermit;

import com.common.daos.BatisDao;
import com.common.beans.Pager;

@Component
public class CommonCostDaoImpl extends BatisDao implements ICommonCostDao  {

	@Override
	public int addCommonCost(CommonCost commoncost) {
		String sql = "CommonCostMapping.addCommonCost";
		return this.insert(sql, commoncost);
	}

	@Override
	public int updateCommonCost(CommonCost commoncost) {
		String sql = "CommonCostMapping.updateCommonCost";
		return this.update(sql, commoncost);
	}

	@Override
	public void deleteCommonCost(CommonCost commoncost) {
		String sql = "CommonCostMapping.deleteCommonCost";
		this.delete(sql, commoncost);
	}

	@Override
	public void verifyCommonCost(CommonCost commoncost) {
		String sql = "CommonCostMapping.verifyCommonCost";
		this.update(sql, commoncost);
	}

	@Override
	public void unVerifyCommonCost(CommonCost commoncost) {
		String sql = "CommonCostMapping.unVerifyCommonCost";
		this.update(sql, commoncost);
	}

	@Override
	public CommonCost getCommonCost(String id) {

		String sql = "CommonCostMapping.getCommonCost"; 
		CommonCost commoncost = new CommonCost(); 
		commoncost.setId(id); 
		List<CommonCost> list = this.query(sql, CommonCost.class, commoncost); 
		if(list == null || list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
	}

	@Override
	public Pager<CommonCost> queryCommonCost(
		CommonCost commoncost,
		UserPermit userPermit,
		Pager<CommonCost> pager){

		String sql = "CommonCostMapping.queryCommonCost"; 
		Pager<CommonCost> pager1 =  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql,CommonCost.class, commoncost,userPermit); 
		sql = "CommonCostMapping.queryCommonCostTotalAmount";
		Map<String,Object> map = new HashMap<String,Object>();
		if(commoncost != null) {
			map.put(commoncost.getClass().getSimpleName(), commoncost);
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