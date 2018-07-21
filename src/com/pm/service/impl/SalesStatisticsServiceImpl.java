package com.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.pm.dao.ISalesStatisticsDao;
import com.pm.domain.business.Statistics;
import com.pm.service.ISalesStatisticsService;
import com.pm.vo.UserPermit;

@Component
public class SalesStatisticsServiceImpl implements ISalesStatisticsService {

	@Autowired ISalesStatisticsDao salesStatisticsDao;

	@Override
	public Pager<Statistics> queryByProject(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {		
		return salesStatisticsDao.queryByProject(statistics, userPermit, pager);
	}

	@Override
	public Pager<Statistics> queryBySales(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		return salesStatisticsDao.queryBySales(statistics, userPermit, pager);
	}

	@Override
	public Pager<Statistics> queryByManager(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		return salesStatisticsDao.queryByManager(statistics, userPermit, pager);
	}

	@Override
	public Pager<Statistics> queryByInfoSource(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		return salesStatisticsDao.queryByInfoSource(statistics, userPermit, pager);
	}

	@Override
	public Pager<Statistics> queryByClient(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		return salesStatisticsDao.queryByClient(statistics, userPermit, pager);
	}

	@Override
	public Pager<Statistics> queryBySalesDept(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		return salesStatisticsDao.queryBySalesDept(statistics, userPermit, pager);
	}

	@Override
	public Pager<Statistics> queryByExecDept(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		return salesStatisticsDao.queryByExecDept(statistics, userPermit, pager);
	}

	@Override
	public Pager<Statistics> queryByDept(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		return salesStatisticsDao.queryByDept(statistics, userPermit, pager);
	}

	@Override
	public Pager<Statistics> queryByYear(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		return salesStatisticsDao.queryByYear(statistics, userPermit, pager);
	}

	@Override
	public Pager<Statistics> queryByQuarter(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		return salesStatisticsDao.queryByQuarter(statistics, userPermit, pager);
	}


	@Override
	public Pager<Statistics> queryByAll(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		Pager<Statistics> all = salesStatisticsDao.queryByAll(statistics, userPermit, pager);
		if(all != null && all.getResultList() != null && all.getResultList().size() == 1){
			if(all.getResultList().get(0) == null){
				all.getResultList().remove(0);
				all.setTotalPages(0);
				all.setTotalRows(0);
			}
		}
		return all;
	}

}
