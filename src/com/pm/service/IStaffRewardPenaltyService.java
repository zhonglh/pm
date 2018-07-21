package com.pm.service;

import com.common.beans.Pager;
import com.pm.domain.business.StaffRewardPenalty;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;

public interface IStaffRewardPenaltyService extends IGeneralLogService{

	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_REWARDPENALTY)
	public int addStaffRewardPenalty(StaffRewardPenalty staffRewardPenalty) ;

	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_REWARDPENALTY)
	public int updateStaffRewardPenalty(StaffRewardPenalty staffRewardPenalty) ; 

	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.ENTITY_REWARDPENALTY)
	public void deleteStaffRewardPenalty(StaffRewardPenalty[] staffRewardPenaltys) ;


	public StaffRewardPenalty getStaffRewardPenalty(String id) ;	

	public Pager<StaffRewardPenalty> queryStaffRewardPenalty(StaffRewardPenalty staffRewardPenalty,  Pager<StaffRewardPenalty> pager);

}