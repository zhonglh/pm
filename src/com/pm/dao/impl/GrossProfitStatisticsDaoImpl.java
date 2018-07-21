package com.pm.dao.impl;

import org.springframework.stereotype.Repository;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.IGrossProfitStatisticsDao;
import com.pm.domain.business.Statistics;
import com.pm.vo.UserPermit;


@Repository(value="grossProfitStatisticsDaoImpl")
public class GrossProfitStatisticsDaoImpl extends BatisDao implements IGrossProfitStatisticsDao {


	@Override
	public Pager<Statistics> queryByProject(Statistics statistics,
			UserPermit userPermit, 
			Pager<Statistics> pager) {
		String sql = "GrossProfitStatisticsMapping.queryByProject";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}

	@Override
	public Pager<Statistics> queryBySales(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		String sql = "GrossProfitStatisticsMapping.queryBySales";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}

	@Override
	public Pager<Statistics> queryByManager(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		String sql = "GrossProfitStatisticsMapping.queryByManager";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}
	


	public Pager<Statistics> queryByInfoSource(Statistics statistics,UserPermit userPermit,Pager<Statistics> pager){
		String sql = "GrossProfitStatisticsMapping.queryByInfoSource";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);		
	}

	public Pager<Statistics> queryByClient(Statistics statistics,UserPermit userPermit,Pager<Statistics> pager){
		String sql = "GrossProfitStatisticsMapping.queryByClient";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}

	@Override
	public Pager<Statistics> queryBySalesDept(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		String sql = "GrossProfitStatisticsMapping.queryBySalesDept";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}

	@Override
	public Pager<Statistics> queryByExecDept(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		String sql = "GrossProfitStatisticsMapping.queryByExecDept";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}

	@Override
	public Pager<Statistics> queryByDept(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		String sql = "GrossProfitStatisticsMapping.queryByDept";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}

	@Override
	public Pager<Statistics> queryByYear(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		String sql = "GrossProfitStatisticsMapping.queryByYear";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}

	@Override
	public Pager<Statistics> queryByQuarter(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		String sql = "GrossProfitStatisticsMapping.queryByQuarter";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}


	@Override
	public Pager<Statistics> queryByAll(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		String sql = "GrossProfitStatisticsMapping.queryByAll";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}

}
