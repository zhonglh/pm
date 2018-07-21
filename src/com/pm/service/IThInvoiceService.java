package com.pm.service;

import com.pm.domain.business.ThInvoice;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;
import com.pm.service.IGeneralLogService;

public interface IThInvoiceService {

	public int addThInvoice(ThInvoice thInvoice) ;

	public ThInvoice getThInvoice(String id) ;	

	public Pager<ThInvoice> queryThInvoice(ThInvoice thInvoice,  Pager<ThInvoice> pager);

}