package com.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.pm.dao.IRecruitInfoDao;
import com.pm.domain.business.RecruitInfo;
import com.pm.service.IRecruitInfoService;
import com.pm.vo.UserPermit;

@Component
public class RecruitInfoServiceImpl implements  IRecruitInfoService {

	@Autowired IRecruitInfoDao recruitInfoDao;
	@Override
	public int addRecruitInfo(RecruitInfo recruitInfo) {
		return recruitInfoDao.addRecruitInfo(recruitInfo);
	}


	@Override
	public RecruitInfo getRecruitInfo(String id) {
		return recruitInfoDao.getRecruitInfo(id);
	}
	

	@Override
	public RecruitInfo getRecruitInfoByNoSource(RecruitInfo recruitInfo){
		return recruitInfoDao.getRecruitInfoByNoSource(recruitInfo);
	}

	@Override
	public Pager<RecruitInfo> queryRecruitInfo(
		RecruitInfo recruitInfo,
		UserPermit userPermit,
		Pager<RecruitInfo> pager){

		return recruitInfoDao.queryRecruitInfo(recruitInfo, userPermit, pager);
	}


	@Override
	public <T> T get(String id) {
		return (T)getRecruitInfo(id);
	}


}