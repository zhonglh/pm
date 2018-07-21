package com.pm.dao.impl;

import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.IStaffCostStatisticsDao;
import com.pm.domain.business.StaffCostStatistics;
import com.pm.vo.UserPermit;


@Component
public class StaffCostStatisticsDaoImpl extends BatisDao implements IStaffCostStatisticsDao {

	@Override
	public Pager<StaffCostStatistics> queryJoinStaffCostStatistics(StaffCostStatistics staffCostStatistics,
			UserPermit userPermit, Pager<StaffCostStatistics> pager) {
		String sql = "StaffCostStatisticsMapping.queryJoinStaffCostStatistics";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, StaffCostStatistics.class, staffCostStatistics,userPermit);
	}

	@Override
	public Pager<StaffCostStatistics> queryLeaveStaffCostStatistics(StaffCostStatistics staffCostStatistics,
			UserPermit userPermit, Pager<StaffCostStatistics> pager) {

		String sql = "StaffCostStatisticsMapping.queryLeaveStaffCostStatistics";
		return this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, StaffCostStatistics.class, staffCostStatistics,userPermit);
	}

}
