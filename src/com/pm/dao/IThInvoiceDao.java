package com.pm.dao;

import com.pm.domain.business.ThInvoice;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;

public interface IThInvoiceDao {

	public int addThInvoice(ThInvoice thInvoice) ;
	public ThInvoice getThInvoice(String id) ;	
	public Pager<ThInvoice> queryThInvoice(ThInvoice thInvoice,  Pager<ThInvoice> pager);

}