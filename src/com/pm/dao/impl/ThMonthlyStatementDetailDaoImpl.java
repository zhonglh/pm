package com.pm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.common.utils.IDKit;
import com.pm.dao.IThMonthlyStatementDetailDao;
import com.pm.domain.business.ThMonthlyStatementDetail;
import com.pm.domain.system.User;
import com.pm.util.PubMethod;
import com.pm.util.ThreadLocalUser;

@Component
public class ThMonthlyStatementDetailDaoImpl extends BatisDao implements IThMonthlyStatementDetailDao  {

	@Override
	public int addThMonthlyStatementDetail(ThMonthlyStatementDetail thMonthlyStatementDetail) {
	
		thMonthlyStatementDetail.setId(IDKit.generateId());
		
		String sql = "ThMonthlyStatementDetailMapping.addThMonthlyStatementDetail";
		return this.insert(sql, thMonthlyStatementDetail);
	}


	@Override
	public ThMonthlyStatementDetail getThMonthlyStatementDetail(String id) {

		String sql = "ThMonthlyStatementDetailMapping.getThMonthlyStatementDetail"; 
		ThMonthlyStatementDetail thMonthlyStatementDetail = new ThMonthlyStatementDetail(); 
		thMonthlyStatementDetail.setId(id); 
		List<ThMonthlyStatementDetail> list = this.query(sql, ThMonthlyStatementDetail.class, thMonthlyStatementDetail); 
		if(list == null || list.isEmpty()) return null; 
		else return list.get(0);	
	}

	@Override
	public Pager<ThMonthlyStatementDetail> queryThMonthlyStatementDetail(
		ThMonthlyStatementDetail thMonthlyStatementDetail,
		Pager<ThMonthlyStatementDetail> pager){

		String sql = "ThMonthlyStatementDetailMapping.queryThMonthlyStatementDetail"; 
		Pager<ThMonthlyStatementDetail> pager1 =  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql,ThMonthlyStatementDetail.class, thMonthlyStatementDetail); 
	
		return pager1;
	}


}