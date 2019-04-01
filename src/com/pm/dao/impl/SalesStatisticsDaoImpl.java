package com.pm.dao.impl;

import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.ISalesStatisticsDao;
import com.pm.domain.business.Statistics;
import com.pm.vo.UserPermit;


@Component
public class SalesStatisticsDaoImpl extends BatisDao implements ISalesStatisticsDao {


	@Override
	public Pager<Statistics> queryByProject(Statistics statistics,
			UserPermit userPermit, 
			Pager<Statistics> pager) {
		String sql = "SalesStatisticsMapping.queryByProject";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}

	@Override
	public Pager<Statistics> queryBySales(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		String sql = "SalesStatisticsMapping.queryBySales";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}

	@Override
	public Pager<Statistics> queryByManager(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		String sql = "SalesStatisticsMapping.queryByManager";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}
	


	@Override
    public Pager<Statistics> queryByInfoSource(Statistics statistics, UserPermit userPermit, Pager<Statistics> pager){
		String sql = "SalesStatisticsMapping.queryByInfoSource";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);		
	}

	@Override
	public Pager<Statistics> queryByClient(Statistics statistics, UserPermit userPermit, Pager<Statistics> pager){
		String sql = "SalesStatisticsMapping.queryByClient";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}

	@Override
	public Pager<Statistics> queryBySalesDept(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		String sql = "SalesStatisticsMapping.queryBySalesDept";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}

	@Override
	public Pager<Statistics> queryByExecDept(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		String sql = "SalesStatisticsMapping.queryByExecDept";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}

	@Override
	public Pager<Statistics> queryByDept(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		String sql = "SalesStatisticsMapping.queryByDept";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}

	@Override
	public Pager<Statistics> queryByYear(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		String sql = "SalesStatisticsMapping.queryByYear";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}

	@Override
	public Pager<Statistics> queryByQuarter(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		String sql = "SalesStatisticsMapping.queryByQuarter";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}


	@Override
	public Pager<Statistics> queryByAll(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		String sql = "SalesStatisticsMapping.queryByAll";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}

}
