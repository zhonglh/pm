package com.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.pm.dao.IStatisticsDetailDao;
import com.pm.domain.business.Statistics;
import com.pm.domain.business.StatisticsDetail;
import com.pm.service.IStatisticsDetailService;
import com.pm.vo.UserPermit;

@Component
public class StatisticsDetailServiceImpl implements IStatisticsDetailService{

	@Autowired  private IStatisticsDetailDao statisticsDetailDao;

	@Override
	public Pager<StatisticsDetail> queryGrossProfit1Detail(
			Statistics statistics, UserPermit userPermit,
			Pager<StatisticsDetail> pager) {
		return statisticsDetailDao.queryGrossProfit1Detail(statistics, userPermit, pager);
	}

	@Override
	public Pager<StatisticsDetail> queryGrossProfit2Detail(
			Statistics statistics, UserPermit userPermit,
			Pager<StatisticsDetail> pager) {
		return statisticsDetailDao.queryGrossProfit2Detail(statistics, userPermit, pager);
	}

	@Override
	public Pager<StatisticsDetail> queryGrossProfit3Detail(
			Statistics statistics, UserPermit userPermit,
			Pager<StatisticsDetail> pager) {
		return statisticsDetailDao.queryGrossProfit3Detail(statistics, userPermit, pager);
	}






	@Override
	public Pager<StatisticsDetail> querySalesDetail(Statistics statistics,
			UserPermit userPermit, Pager<StatisticsDetail> pager) {
		return statisticsDetailDao.querySalesDetail(statistics, userPermit, pager);
	}

	@Override
	public Pager<StatisticsDetail> queryReceivablesDetail(
			Statistics statistics, UserPermit userPermit,
			Pager<StatisticsDetail> pager) {
		return statisticsDetailDao.queryReceivablesDetail(statistics, userPermit, pager);
	}

}
