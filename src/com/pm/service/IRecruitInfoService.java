package com.pm.service;

import com.pm.domain.business.RecruitInfo;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;
import com.pm.service.IBaseService;

public interface IRecruitInfoService  extends IGeneralLogService{

	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_RECRUITINFO)
	public int addRecruitInfo(RecruitInfo recruitInfo) ;


	public RecruitInfo getRecruitInfo(String id) ;	
	
	public RecruitInfo getRecruitInfoByNoSource(RecruitInfo recruitInfo);

	public Pager<RecruitInfo> queryRecruitInfo(RecruitInfo recruitInfo,  UserPermit userPermit,Pager<RecruitInfo> pager);

}