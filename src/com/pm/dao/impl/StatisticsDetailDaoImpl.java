package com.pm.dao.impl;

import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.IStatisticsDetailDao;
import com.pm.domain.business.Statistics;
import com.pm.domain.business.StatisticsDetail;
import com.pm.vo.UserPermit;

@Component
public class StatisticsDetailDaoImpl extends BatisDao implements
		IStatisticsDetailDao {

	@Override
	public Pager<StatisticsDetail> queryGrossProfit1Detail(
			Statistics statistics, UserPermit userPermit,
			Pager<StatisticsDetail> pager) {
		String sql = "StatisticsDetailMapping.queryGrossProfit1Detail";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, StatisticsDetail.class, statistics,userPermit);
	}

	@Override
	public Pager<StatisticsDetail> queryGrossProfit2Detail(
			Statistics statistics, UserPermit userPermit,
			Pager<StatisticsDetail> pager) {
		String sql = "StatisticsDetailMapping.queryGrossProfit2Detail";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, StatisticsDetail.class, statistics,userPermit);
	}


	@Override
	public Pager<StatisticsDetail> queryGrossProfit3Detail(
			Statistics statistics, UserPermit userPermit,
			Pager<StatisticsDetail> pager) {
		String sql = "StatisticsDetailMapping.queryGrossProfit3Detail";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, StatisticsDetail.class, statistics,userPermit);
	}

	@Override
	public Pager<StatisticsDetail> querySalesDetail(Statistics statistics,
			UserPermit userPermit, Pager<StatisticsDetail> pager) {
		String sql = "StatisticsDetailMapping.querySalesDetail";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, StatisticsDetail.class, statistics,userPermit);
	}

	@Override
	public Pager<StatisticsDetail> queryReceivablesDetail(
			Statistics statistics, UserPermit userPermit,
			Pager<StatisticsDetail> pager) {
		String sql = "StatisticsDetailMapping.queryReceivablesDetail";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, StatisticsDetail.class, statistics,userPermit);
	}

}
