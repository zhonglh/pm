package com.pm.dao;

import com.pm.domain.business.StaffRewardPenalty;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;

public interface IStaffRewardPenaltyDao {

	public int addStaffRewardPenalty(StaffRewardPenalty staffRewardPenalty) ;

	public int updateStaffRewardPenalty(StaffRewardPenalty staffRewardPenalty) ; 

	public void deleteStaffRewardPenalty(StaffRewardPenalty staffRewardPenalty) ;

	public StaffRewardPenalty getStaffRewardPenalty(String id) ;	

	public Pager<StaffRewardPenalty> queryStaffRewardPenalty(StaffRewardPenalty staffRewardPenalty,  Pager<StaffRewardPenalty> pager);

}