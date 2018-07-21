package com.pm.dao;

import com.pm.domain.business.RecruitInfo;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;

public interface IRecruitInfoDao {

	public int addRecruitInfo(RecruitInfo recruitInfo) ;

	public RecruitInfo getRecruitInfo(String id) ;	
	

	public RecruitInfo getRecruitInfoByNoSource(RecruitInfo recruitInfo);

	public Pager<RecruitInfo> queryRecruitInfo(RecruitInfo recruitInfo,  UserPermit userPermit,Pager<RecruitInfo> pager);

}