package com.pm.dao.impl;

import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.IDepartStatisticsDao;
import com.pm.domain.business.Statistics;
import com.pm.domain.business.StatisticsDetail;
import com.pm.vo.DepartStatisticsItem;
import com.pm.vo.UserPermit;

/**
 * 部门统计明细
 * @author zhonglihong
 * @date 2016年6月9日 上午11:30:39
 */
@Component
public class DepartStatisticsDaoImpl extends BatisDao implements IDepartStatisticsDao {


	@Override
	public Pager<DepartStatisticsItem> queryMonthlyStatement20(Statistics statistics, UserPermit userPermit,Pager<DepartStatisticsItem> pager) {
		String sql = "DepartStatisticsDetailMapping.queryXStaistics";
		statistics.setX("20");
		statistics.setGroupBy("dept_id");
		statistics.setGroupSelect("dept_id as deptId");
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, DepartStatisticsItem.class, statistics,userPermit);
	}


	@Override
	public Pager<DepartStatisticsItem> queryMonthlyStatement22(Statistics statistics, UserPermit userPermit,Pager<DepartStatisticsItem> pager) {
		String sql = "DepartStatisticsDetailMapping.queryXStaistics";
		statistics.setX("22");
		statistics.setGroupBy("dept_id");
		statistics.setGroupSelect("dept_id as deptId");
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, DepartStatisticsItem.class, statistics,userPermit);
	}



	@Override
	public Pager<DepartStatisticsItem> queryReceivedPayments10(Statistics statistics, UserPermit userPermit,Pager<DepartStatisticsItem> pager) {
		String sql = "DepartStatisticsDetailMapping.queryXStaistics";
		statistics.setX("10");
		statistics.setGroupBy("dept_id");
		statistics.setGroupSelect("dept_id as deptId");
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, DepartStatisticsItem.class, statistics,userPermit);
	}

	@Override
	public Pager<DepartStatisticsItem> queryReceivedPayments12(Statistics statistics, UserPermit userPermit,Pager<DepartStatisticsItem> pager) {
		String sql = "DepartStatisticsDetailMapping.queryXStaistics";
		statistics.setX("12");
		statistics.setGroupBy("dept_id");
		statistics.setGroupSelect("dept_id as deptId");
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, DepartStatisticsItem.class, statistics,userPermit);
	}




	@Override
	public Pager<DepartStatisticsItem> queryProjectStaffCosts(Statistics statistics, UserPermit userPermit,Pager<DepartStatisticsItem> pager) {
		String sql = "DepartStatisticsDetailMapping.queryXStaistics";
		statistics.setX("51");
		statistics.setGroupBy("dept_id");
		statistics.setGroupSelect("dept_id as deptId");
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, DepartStatisticsItem.class, statistics,userPermit);
	}

	@Override
	public Pager<DepartStatisticsItem> queryReimburseCosts(Statistics statistics, UserPermit userPermit,Pager<DepartStatisticsItem> pager) {
		String sql = "DepartStatisticsDetailMapping.queryXStaistics";
		statistics.setX("30");
		statistics.setGroupBy("dept_id");
		statistics.setGroupSelect("dept_id as deptId");
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, DepartStatisticsItem.class, statistics,userPermit);
	}

	@Override
	public Pager<DepartStatisticsItem> queryReimburseCostDetails(Statistics statistics, UserPermit userPermit,Pager<DepartStatisticsItem> pager) {
		String sql = "DepartStatisticsDetailMapping.queryXStaistics";
		statistics.setX("30");
		statistics.setGroupBy("dept_id,type_id");
		statistics.setGroupSelect("dept_id as deptId,type_id as itemId");
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, DepartStatisticsItem.class, statistics,userPermit);
	}

	@Override
	public Pager<DepartStatisticsItem> queryProjectExpends40(Statistics statistics, UserPermit userPermit,Pager<DepartStatisticsItem> pager) {
		String sql = "DepartStatisticsDetailMapping.queryXStaistics";
		statistics.setX("40");
		statistics.setGroupBy("dept_id");
		statistics.setGroupSelect("dept_id as deptId");
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, DepartStatisticsItem.class, statistics,userPermit);
	}

	@Override
	public Pager<DepartStatisticsItem> queryProjectExpends41(Statistics statistics, UserPermit userPermit,Pager<DepartStatisticsItem> pager) {
		String sql = "DepartStatisticsDetailMapping.queryXStaistics";
		statistics.setX("41");
		statistics.setGroupBy("dept_id");
		statistics.setGroupSelect("dept_id as deptId");
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, DepartStatisticsItem.class, statistics,userPermit);
	}
	@Override
	public Pager<DepartStatisticsItem> queryProjectExpends42(Statistics statistics, UserPermit userPermit,Pager<DepartStatisticsItem> pager) {
		String sql = "DepartStatisticsDetailMapping.queryXStaistics";
		statistics.setX("42");
		statistics.setGroupBy("dept_id");
		statistics.setGroupSelect("dept_id as deptId");
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, DepartStatisticsItem.class, statistics,userPermit);
	}
	
	
	
	@Override
	public Pager<DepartStatisticsItem> querySalseCosts(Statistics statistics, UserPermit userPermit,Pager<DepartStatisticsItem> pager) {
		String sql = "DepartStatisticsDetailMapping.queryDepartCostStaistics";
		statistics.setX("100");
		statistics.setGroupBy("dept_id");
		statistics.setGroupSelect("dept_id as deptId");
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, DepartStatisticsItem.class, statistics,userPermit);
	}

	@Override
	public Pager<DepartStatisticsItem> querySalseCostsDetail(Statistics statistics, UserPermit userPermit,Pager<DepartStatisticsItem> pager) {
		String sql = "DepartStatisticsDetailMapping.queryDepartCostStaistics";
		statistics.setX("100");
		statistics.setGroupBy("dept_id,type_id");
		statistics.setGroupSelect("dept_id as deptId,type_id as itemId");
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, DepartStatisticsItem.class, statistics,userPermit);
	}

	@Override
	public Pager<DepartStatisticsItem> queryDepartCosts(Statistics statistics, UserPermit userPermit,Pager<DepartStatisticsItem> pager) {
		String sql = "DepartStatisticsDetailMapping.queryDepartCostStaistics";
		statistics.setX("101");
		statistics.setGroupBy("dept_id");
		statistics.setGroupSelect("dept_id as deptId");
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, DepartStatisticsItem.class, statistics,userPermit);
	}

	@Override
	public Pager<DepartStatisticsItem> queryDepartCostsDetail(Statistics statistics, UserPermit userPermit,Pager<DepartStatisticsItem> pager) {
		String sql = "DepartStatisticsDetailMapping.queryDepartCostStaistics";
		statistics.setX("101");
		statistics.setGroupBy("dept_id,type_id");
		statistics.setGroupSelect("dept_id as deptId,type_id as itemId");
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, DepartStatisticsItem.class, statistics,userPermit);
	}
	
	
	
	
	

	@Override
	public Pager<StatisticsDetail> queryCostsDetail(Statistics statistics, UserPermit userPermit,Pager<StatisticsDetail> pager) {
		String sql = "DepartStatisticsDetailMapping.queryXDetail";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, StatisticsDetail.class, statistics,userPermit);
	}

	@Override
	public Pager<StatisticsDetail> queryDepartDetail(Statistics statistics, UserPermit userPermit,Pager<StatisticsDetail> pager) {
		String sql = "DepartStatisticsDetailMapping.queryDepartCostDetail";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, StatisticsDetail.class, statistics,userPermit);
	}



	@Override
	public Pager<DepartStatisticsItem> queryOtherStaffCosts(Statistics statistics, UserPermit userPermit, Pager<DepartStatisticsItem> pager){

		String sql = "DepartStatisticsDetailMapping.queryOtherStaffCostStaistics";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, DepartStatisticsItem.class, statistics,userPermit);

	}



	@Override
	public Pager<StatisticsDetail> queryOtherStaffCostDetail(Statistics statistics, UserPermit userPermit, Pager<StatisticsDetail> pager) {
		String sql = "DepartStatisticsDetailMapping.queryOtherStaffCostDetail";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, StatisticsDetail.class, statistics,userPermit);
	}

}
