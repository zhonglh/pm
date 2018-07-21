
package com.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.pm.dao.IDepartStatisticsDao;
import com.pm.domain.business.Statistics;
import com.pm.domain.business.StatisticsDetail;
import com.pm.service.IDepartStatisticsService;
import com.pm.vo.DepartStatisticsItem;
import com.pm.vo.UserPermit;

/**
 * @author zhonglihong
 * @date 2016年6月9日 上午11:25:25
 */
@Component
public class DepartStatisticsServiceImpl implements IDepartStatisticsService {
	
	@Autowired
	private IDepartStatisticsDao departStatisticsDao;

	@Override
	public Pager<DepartStatisticsItem> queryReceivedPayments(Statistics statistics, UserPermit userPermit,Pager<DepartStatisticsItem> pager) {
		return departStatisticsDao.queryReceivedPayments(statistics, userPermit, pager);
	}

	@Override
	public Pager<DepartStatisticsItem> queryProjectStaffCosts(Statistics statistics, UserPermit userPermit,	Pager<DepartStatisticsItem> pager) {
		return departStatisticsDao.queryProjectStaffCosts(statistics, userPermit, pager);
	}

	@Override
	public Pager<DepartStatisticsItem> queryReimburseCosts(Statistics statistics, UserPermit userPermit,Pager<DepartStatisticsItem> pager) {
		return departStatisticsDao.queryReimburseCosts(statistics, userPermit, pager);
	}

	@Override
	public Pager<DepartStatisticsItem> queryReimburseCostDetails(Statistics statistics, UserPermit userPermit,Pager<DepartStatisticsItem> pager) {
		return departStatisticsDao.queryReimburseCostDetails(statistics, userPermit, pager);
	}

	@Override
	public Pager<DepartStatisticsItem> queryProjectExpends(Statistics statistics, UserPermit userPermit,Pager<DepartStatisticsItem> pager) {
		return departStatisticsDao.queryProjectExpends(statistics, userPermit, pager);
	}

	@Override
	public Pager<DepartStatisticsItem> querySalseCosts(Statistics statistics, UserPermit userPermit,Pager<DepartStatisticsItem> pager) {
		return departStatisticsDao.querySalseCosts(statistics, userPermit, pager);
	}

	@Override
	public Pager<DepartStatisticsItem> querySalseCostsDetail(Statistics statistics, UserPermit userPermit,Pager<DepartStatisticsItem> pager) {
		return departStatisticsDao.querySalseCostsDetail(statistics, userPermit, pager);
	}

	@Override
	public Pager<DepartStatisticsItem> queryDepartCosts(Statistics statistics, UserPermit userPermit,Pager<DepartStatisticsItem> pager) {
		return departStatisticsDao.queryDepartCosts(statistics, userPermit, pager);
	}

	@Override
	public Pager<DepartStatisticsItem> queryDepartCostsDetail(Statistics statistics, UserPermit userPermit,Pager<DepartStatisticsItem> pager) {
		return departStatisticsDao.queryDepartCostsDetail(statistics, userPermit, pager);
	}

	@Override
	public Pager<StatisticsDetail> queryCostsDetail(Statistics statistics, UserPermit userPermit,Pager<StatisticsDetail> pager) {
		return departStatisticsDao.queryCostsDetail(statistics, userPermit, pager);
	}

	@Override
	public Pager<StatisticsDetail> queryDepartDetail(Statistics statistics, UserPermit userPermit, Pager<StatisticsDetail> pager) {
		return departStatisticsDao.queryDepartDetail(statistics, userPermit, pager);
	}
	
	
	
}
