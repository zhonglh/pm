package com.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.pm.dao.IStaffCostStatisticsDao;
import com.pm.domain.business.StaffCostStatistics;
import com.pm.service.IStaffCostStatisticsService;
import com.pm.vo.UserPermit;


@Component
public class StaffCostStatisticsServiceImpl implements IStaffCostStatisticsService {
	
	@Autowired
	IStaffCostStatisticsDao staffCostStatisticsDao;

	@Override
	public Pager<StaffCostStatistics> queryJoinStaffCostStatistics(StaffCostStatistics staffCostStatistics,
			UserPermit userPermit, Pager<StaffCostStatistics> pager) {
		return staffCostStatisticsDao.queryJoinStaffCostStatistics(staffCostStatistics, userPermit, pager);
	}

	@Override
	public Pager<StaffCostStatistics> queryLeaveStaffCostStatistics(StaffCostStatistics staffCostStatistics,
			UserPermit userPermit, Pager<StaffCostStatistics> pager) {
		return staffCostStatisticsDao.queryLeaveStaffCostStatistics(staffCostStatistics, userPermit, pager);
	}

}
