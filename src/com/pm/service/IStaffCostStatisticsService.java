package com.pm.service;

import com.common.beans.Pager;
import com.pm.domain.business.StaffCostStatistics;
import com.pm.vo.UserPermit;


/**
 * 入离职报表
 * @author zhonglihong
 * @date 2016年6月2日 下午8:47:15
 */
public interface IStaffCostStatisticsService {
	
	public Pager<StaffCostStatistics> queryJoinStaffCostStatistics(StaffCostStatistics staffCostStatistics, UserPermit userPermit,Pager<StaffCostStatistics> pager);


	public Pager<StaffCostStatistics> queryLeaveStaffCostStatistics(StaffCostStatistics staffCostStatistics, UserPermit userPermit,Pager<StaffCostStatistics> pager);

}
