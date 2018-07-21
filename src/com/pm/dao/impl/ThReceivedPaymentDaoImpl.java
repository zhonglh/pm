package com.pm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.common.utils.IDKit;
import com.pm.dao.IThReceivedPaymentDao;
import com.pm.domain.business.ThReceivedPayment;
import com.pm.domain.system.User;
import com.pm.util.PubMethod;
import com.pm.util.ThreadLocalUser;

@Component
public class ThReceivedPaymentDaoImpl extends BatisDao implements IThReceivedPaymentDao  {

	@Override
	public int addThReceivedPayment(ThReceivedPayment thReceivedPayment) {
		

		User sessionUser = ThreadLocalUser.getUser();		
		thReceivedPayment.setId(IDKit.generateId());
		thReceivedPayment.setHis_date(PubMethod.getCurrentDate());
		thReceivedPayment.setHis_user_id(sessionUser.getUser_id());
		thReceivedPayment.setHis_user_name(sessionUser.getUser_name());
		
		String sql = "ThReceivedPaymentMapping.addThReceivedPayment";
		return this.insert(sql, thReceivedPayment);
	}
	@Override
	public ThReceivedPayment getThReceivedPayment(String id) {

		String sql = "ThReceivedPaymentMapping.getThReceivedPayment"; 
		ThReceivedPayment thReceivedPayment = new ThReceivedPayment(); 
		thReceivedPayment.setId(id); 
		List<ThReceivedPayment> list = this.query(sql, ThReceivedPayment.class, thReceivedPayment); 
		if(list == null || list.isEmpty()) return null; 
		else return list.get(0);	
	}

	@Override
	public Pager<ThReceivedPayment> queryThReceivedPayment(
		ThReceivedPayment thReceivedPayment,
		Pager<ThReceivedPayment> pager){

		String sql = "ThReceivedPaymentMapping.queryThReceivedPayment"; 
		Pager<ThReceivedPayment> pager1 =  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql,ThReceivedPayment.class, thReceivedPayment); 
		
		return pager1;
	}


}