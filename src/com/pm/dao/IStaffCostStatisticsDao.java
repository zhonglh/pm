package com.pm.dao;

import com.common.beans.Pager;
import com.pm.domain.business.StaffCostStatistics;
import com.pm.vo.UserPermit;

public interface IStaffCostStatisticsDao {


	public Pager<StaffCostStatistics> queryJoinStaffCostStatistics(StaffCostStatistics staffCostStatistics, UserPermit userPermit,Pager<StaffCostStatistics> pager);


	public Pager<StaffCostStatistics> queryLeaveStaffCostStatistics(StaffCostStatistics staffCostStatistics, UserPermit userPermit,Pager<StaffCostStatistics> pager);

	
}
