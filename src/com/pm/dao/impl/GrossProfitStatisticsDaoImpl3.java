package com.pm.dao.impl;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.IGrossProfitStatisticsDao;
import com.pm.domain.business.Statistics;
import com.pm.vo.UserPermit;
import org.springframework.stereotype.Repository;


/**
 * @author Administrator
 */
@Repository(value="grossProfitStatisticsDaoImpl3")
public class GrossProfitStatisticsDaoImpl3 extends BatisDao implements IGrossProfitStatisticsDao {


	@Override
	public Pager<Statistics> queryByProject(Statistics statistics,
			UserPermit userPermit, 
			Pager<Statistics> pager) {
		String sql = "GrossProfitStatisticsMapping3.queryByProject";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}

	@Override
	public Pager<Statistics> queryBySales(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		String sql = "GrossProfitStatisticsMapping3.queryBySales";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}

	@Override
	public Pager<Statistics> queryByManager(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		String sql = "GrossProfitStatisticsMapping3.queryByManager";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}
	


	@Override
	public Pager<Statistics> queryByInfoSource(Statistics statistics, UserPermit userPermit, Pager<Statistics> pager){
		String sql = "GrossProfitStatisticsMapping3.queryByInfoSource";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);		
	}

	@Override
	public Pager<Statistics> queryByClient(Statistics statistics, UserPermit userPermit, Pager<Statistics> pager){
		String sql = "GrossProfitStatisticsMapping3.queryByClient";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}

	@Override
	public Pager<Statistics> queryBySalesDept(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		String sql = "GrossProfitStatisticsMapping3.queryBySalesDept";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}

	@Override
	public Pager<Statistics> queryByExecDept(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		String sql = "GrossProfitStatisticsMapping3.queryByExecDept";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}

	@Override
	public Pager<Statistics> queryByDept(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		String sql = "GrossProfitStatisticsMapping3.queryByDept";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}

	@Override
	public Pager<Statistics> queryByYear(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		String sql = "GrossProfitStatisticsMapping3.queryByYear";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}

	@Override
	public Pager<Statistics> queryByQuarter(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		String sql = "GrossProfitStatisticsMapping3.queryByQuarter";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}


	@Override
	public Pager<Statistics> queryByAll(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		String sql = "GrossProfitStatisticsMapping3.queryByAll";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}

}
