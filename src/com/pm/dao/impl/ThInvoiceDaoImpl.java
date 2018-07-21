package com.pm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.common.utils.IDKit;
import com.pm.dao.IThInvoiceDao;
import com.pm.domain.business.ThInvoice;
import com.pm.domain.system.User;
import com.pm.util.PubMethod;
import com.pm.util.ThreadLocalUser;

@Component
public class ThInvoiceDaoImpl extends BatisDao implements IThInvoiceDao  {

	@Override
	public int addThInvoice(ThInvoice thInvoice) {
		
		User sessionUser = ThreadLocalUser.getUser();		
		thInvoice.setId(IDKit.generateId());
		thInvoice.setHis_date(PubMethod.getCurrentDate());
		thInvoice.setHis_user_id(sessionUser.getUser_id());
		thInvoice.setHis_user_name(sessionUser.getUser_name());
		
		String sql = "ThInvoiceMapping.addThInvoice";
		return this.insert(sql, thInvoice);
	}

	@Override
	public ThInvoice getThInvoice(String id) {

		String sql = "ThInvoiceMapping.getThInvoice"; 
		ThInvoice thInvoice = new ThInvoice(); 
		thInvoice.setId(id); 
		List<ThInvoice> list = this.query(sql, ThInvoice.class, thInvoice); 
		if(list == null || list.isEmpty()) return null; 
		else return list.get(0);	
	}

	@Override
	public Pager<ThInvoice> queryThInvoice(
		ThInvoice thInvoice,
		Pager<ThInvoice> pager){

		String sql = "ThInvoiceMapping.queryThInvoice"; 
		Pager<ThInvoice> pager1 =  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql,ThInvoice.class, thInvoice); 
		
		return pager1;
	}


}