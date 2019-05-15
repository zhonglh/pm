package com.pm.dao;

import com.common.beans.Pager;
import com.pm.domain.business.StaffPerformance;
import com.pm.vo.UserPermit;

import java.util.List;

/**
 * @author Administrator
 */
public interface IStaffPerformanceDao {

	public int addStaffPerformance(StaffPerformance staffPerformance) ;

	public int updateStaffPerformance(StaffPerformance staffPerformance) ;

	public int deleteStaffPerformance(StaffPerformance staffPerformance) ;

	public int verifyStaffPerformance(StaffPerformance staffPerformance) ;

	public int unVerifyStaffPerformance(StaffPerformance staffPerformance) ;

	public StaffPerformance getStaffPerformance(String id) ;	



	public List<StaffPerformance> getStaffPerformanceList(StaffPerformance staffPerformance);
	

	public Pager<StaffPerformance> queryStaffPerformance(StaffPerformance staffPerformance, UserPermit userPermit, Pager<StaffPerformance> pager);

}