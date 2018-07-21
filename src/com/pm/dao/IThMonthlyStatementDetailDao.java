package com.pm.dao;

import com.pm.domain.business.ThMonthlyStatementDetail;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;

public interface IThMonthlyStatementDetailDao {

	public int addThMonthlyStatementDetail(ThMonthlyStatementDetail thMonthlyStatementDetail) ;


	public ThMonthlyStatementDetail getThMonthlyStatementDetail(String id) ;	

	public Pager<ThMonthlyStatementDetail> queryThMonthlyStatementDetail(ThMonthlyStatementDetail thMonthlyStatementDetail,  Pager<ThMonthlyStatementDetail> pager);

}