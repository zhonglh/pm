package com.pm.dao.impl;

import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.IReceivablesStatisticsDao;
import com.pm.domain.business.Statistics;
import com.pm.vo.UserPermit;


@Component
public class ReceivablesStatisticsDaoImpl extends BatisDao implements IReceivablesStatisticsDao {


	@Override
	public Pager<Statistics> queryByProject(Statistics statistics,
			UserPermit userPermit, 
			Pager<Statistics> pager) {
		String sql = "ReceivablesStatisticsMapping.queryByProject";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}

	@Override
	public Pager<Statistics> queryBySales(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		String sql = "ReceivablesStatisticsMapping.queryBySales";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}

	@Override
	public Pager<Statistics> queryByManager(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		String sql = "ReceivablesStatisticsMapping.queryByManager";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}
	


	public Pager<Statistics> queryByInfoSource(Statistics statistics,UserPermit userPermit,Pager<Statistics> pager){
		String sql = "ReceivablesStatisticsMapping.queryByInfoSource";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);		
	}

	public Pager<Statistics> queryByClient(Statistics statistics,UserPermit userPermit,Pager<Statistics> pager){
		String sql = "ReceivablesStatisticsMapping.queryByClient";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}

	@Override
	public Pager<Statistics> queryBySalesDept(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		String sql = "ReceivablesStatisticsMapping.queryBySalesDept";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}

	@Override
	public Pager<Statistics> queryByExecDept(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		String sql = "ReceivablesStatisticsMapping.queryByExecDept";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}

	@Override
	public Pager<Statistics> queryByDept(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		String sql = "ReceivablesStatisticsMapping.queryByDept";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}

	@Override
	public Pager<Statistics> queryByYear(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		String sql = "ReceivablesStatisticsMapping.queryByYear";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}

	@Override
	public Pager<Statistics> queryByQuarter(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		String sql = "ReceivablesStatisticsMapping.queryByQuarter";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}


	@Override
	public Pager<Statistics> queryByAll(Statistics statistics,
			UserPermit userPermit, Pager<Statistics> pager) {
		String sql = "ReceivablesStatisticsMapping.queryByAll";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, Statistics.class, userPermit,statistics);
	}

}
