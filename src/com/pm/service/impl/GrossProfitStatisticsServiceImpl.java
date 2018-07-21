package com.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.common.beans.Pager;
import com.pm.dao.IGrossProfitStatisticsDao;
import com.pm.domain.business.Statistics;
import com.pm.service.IGrossProfitStatisticsService;
import com.pm.vo.UserPermit;

@Service("grossProfitStatisticsServiceImpl")
public class GrossProfitStatisticsServiceImpl implements
		IGrossProfitStatisticsService {

	@Autowired
	@Qualifier("grossProfitStatisticsDaoImpl") 
	IGrossProfitStatisticsDao grossProfitStatisticsDao;

	@Override
	public Pager<Statistics> queryByProject(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {		
		return getGrossProfitStatisticsDao().queryByProject(statistics, userPermit, pager);
	}

	@Override
	public Pager<Statistics> queryBySales(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		return getGrossProfitStatisticsDao().queryBySales(statistics, userPermit, pager);
	}

	@Override
	public Pager<Statistics> queryByManager(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		return getGrossProfitStatisticsDao().queryByManager(statistics, userPermit, pager);
	}

	@Override
	public Pager<Statistics> queryByInfoSource(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		return getGrossProfitStatisticsDao().queryByInfoSource(statistics, userPermit, pager);
	}

	@Override
	public Pager<Statistics> queryByClient(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		return getGrossProfitStatisticsDao().queryByClient(statistics, userPermit, pager);
	}

	@Override
	public Pager<Statistics> queryBySalesDept(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		return getGrossProfitStatisticsDao().queryBySalesDept(statistics, userPermit, pager);
	}

	@Override
	public Pager<Statistics> queryByExecDept(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		return getGrossProfitStatisticsDao().queryByExecDept(statistics, userPermit, pager);
	}

	@Override
	public Pager<Statistics> queryByDept(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		return getGrossProfitStatisticsDao().queryByDept(statistics, userPermit, pager);
	}

	@Override
	public Pager<Statistics> queryByYear(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		return getGrossProfitStatisticsDao().queryByYear(statistics, userPermit, pager);
	}

	@Override
	public Pager<Statistics> queryByQuarter(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		return getGrossProfitStatisticsDao().queryByQuarter(statistics, userPermit, pager);
	}


	@Override
	public Pager<Statistics> queryByAll(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		Pager<Statistics> all = getGrossProfitStatisticsDao().queryByAll(statistics, userPermit, pager);
		if(all != null && all.getResultList() != null && all.getResultList().size() == 1){
			if(all.getResultList().get(0) == null){
				all.getResultList().remove(0);
				all.setTotalPages(0);
				all.setTotalRows(0);
			}
		}
		return all;
	}

	@Override
	public String toString() {
		return String.format("GrossProfitStatisticsServiceImpl [1111111]");
	}

	public IGrossProfitStatisticsDao getGrossProfitStatisticsDao() {
		return grossProfitStatisticsDao;
	}

}
