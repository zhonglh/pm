package com.pm.dao.impl;

import org.springframework.stereotype.Repository;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.IGrossProfitStatisticsDao;
import com.pm.domain.business.Statistics;
import com.pm.vo.UserPermit;


/**
 * @author Administrator
 */
@Repository(value="grossProfitStatisticsDaoImpl2")
public class GrossProfitStatisticsDaoImpl2 extends BatisDao implements IGrossProfitStatisticsDao {


	@Override
	public Pager<Statistics> queryByProject(Statistics statistics,
			UserPermit userPermit, 
			Pager<Statistics> pager) {
		String sql = "GrossProfitStatisticsMapping2.queryByProject";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}

	@Override
	public Pager<Statistics> queryBySales(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		String sql = "GrossProfitStatisticsMapping2.queryBySales";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}

	@Override
	public Pager<Statistics> queryByManager(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		String sql = "GrossProfitStatisticsMapping2.queryByManager";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}
	


	@Override
	public Pager<Statistics> queryByInfoSource(Statistics statistics, UserPermit userPermit, Pager<Statistics> pager){
		String sql = "GrossProfitStatisticsMapping2.queryByInfoSource";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);		
	}

	@Override
	public Pager<Statistics> queryByClient(Statistics statistics, UserPermit userPermit, Pager<Statistics> pager){
		String sql = "GrossProfitStatisticsMapping2.queryByClient";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}

	@Override
	public Pager<Statistics> queryBySalesDept(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		String sql = "GrossProfitStatisticsMapping2.queryBySalesDept";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}

	@Override
	public Pager<Statistics> queryByExecDept(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		String sql = "GrossProfitStatisticsMapping2.queryByExecDept";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}

	@Override
	public Pager<Statistics> queryByDept(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		String sql = "GrossProfitStatisticsMapping2.queryByDept";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}

	@Override
	public Pager<Statistics> queryByYear(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		String sql = "GrossProfitStatisticsMapping2.queryByYear";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}

	@Override
	public Pager<Statistics> queryByQuarter(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		String sql = "GrossProfitStatisticsMapping2.queryByQuarter";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}


	@Override
	public Pager<Statistics> queryByAll(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		String sql = "GrossProfitStatisticsMapping2.queryByAll";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}

}
