package com.pm.service;

import com.common.beans.Pager;
import com.pm.domain.business.ThMonthlyStatementDetail;

public interface IThMonthlyStatementDetailService {

	public int addThMonthlyStatementDetail(ThMonthlyStatementDetail thMonthlyStatementDetail) ;

	public ThMonthlyStatementDetail getThMonthlyStatementDetail(String id) ;	

	public Pager<ThMonthlyStatementDetail> queryThMonthlyStatementDetail(ThMonthlyStatementDetail thMonthlyStatementDetail,  Pager<ThMonthlyStatementDetail> pager);

}