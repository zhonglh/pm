package com.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.pm.dao.IReceivablesStatisticsDao;
import com.pm.domain.business.Statistics;
import com.pm.service.IReceivablesStatisticsService;
import com.pm.vo.UserPermit;

@Component
public class ReceivablesStatisticsServiceImpl implements IReceivablesStatisticsService {

	@Autowired IReceivablesStatisticsDao receivablesStatisticsDao;

	@Override
	public Pager<Statistics> queryByProject(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {		
		return receivablesStatisticsDao.queryByProject(statistics, userPermit, pager);
	}

	@Override
	public Pager<Statistics> queryBySales(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		return receivablesStatisticsDao.queryBySales(statistics, userPermit, pager);
	}

	@Override
	public Pager<Statistics> queryByManager(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		return receivablesStatisticsDao.queryByManager(statistics, userPermit, pager);
	}

	@Override
	public Pager<Statistics> queryByInfoSource(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		return receivablesStatisticsDao.queryByInfoSource(statistics, userPermit, pager);
	}

	@Override
	public Pager<Statistics> queryByClient(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		return receivablesStatisticsDao.queryByClient(statistics, userPermit, pager);
	}

	@Override
	public Pager<Statistics> queryBySalesDept(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		return receivablesStatisticsDao.queryBySalesDept(statistics, userPermit, pager);
	}

	@Override
	public Pager<Statistics> queryByExecDept(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		return receivablesStatisticsDao.queryByExecDept(statistics, userPermit, pager);
	}

	@Override
	public Pager<Statistics> queryByDept(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		return receivablesStatisticsDao.queryByDept(statistics, userPermit, pager);
	}

	@Override
	public Pager<Statistics> queryByYear(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		return receivablesStatisticsDao.queryByYear(statistics, userPermit, pager);
	}

	@Override
	public Pager<Statistics> queryByQuarter(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		return receivablesStatisticsDao.queryByQuarter(statistics, userPermit, pager);
	}


	@Override
	public Pager<Statistics> queryByAll(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		Pager<Statistics> all = receivablesStatisticsDao.queryByAll(statistics, userPermit, pager);
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
