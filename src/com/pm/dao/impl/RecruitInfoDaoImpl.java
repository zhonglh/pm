package com.pm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.pm.dao.IRecruitInfoDao;
import com.pm.domain.business.RecruitInfo;
import com.pm.vo.UserPermit;

import com.common.daos.BatisDao;
import com.common.beans.Pager;

@Component
public class RecruitInfoDaoImpl extends BatisDao implements IRecruitInfoDao  {

	@Override
	public int addRecruitInfo(RecruitInfo recruitInfo) {
		String sql = "RecruitInfoMapping.addRecruitInfo";
		return this.insert(sql, recruitInfo);
	}


	@Override
	public RecruitInfo getRecruitInfo(String id) {
		String sql = "RecruitInfoMapping.getRecruitInfo"; 
		RecruitInfo recruitInfo = new RecruitInfo(); 
		recruitInfo.setId(id); 
		List<RecruitInfo> list = this.query(sql, RecruitInfo.class, recruitInfo); 
		if(list == null || list.isEmpty()) return null; 
		else return list.get(0);	
	}
	
	

	@Override
	public RecruitInfo getRecruitInfoByNoSource(RecruitInfo recruitInfo){
		String sql = "RecruitInfoMapping.getRecruitInfoByNoSource"; 
		List<RecruitInfo> list = this.query(sql, RecruitInfo.class, recruitInfo); 
		if(list == null || list.isEmpty()) return null; 
		else return list.get(0);		
	}

	@Override
	public Pager<RecruitInfo> queryRecruitInfo(
		RecruitInfo recruitInfo,
		UserPermit userPermit,
		Pager<RecruitInfo> pager){
		String sql = "RecruitInfoMapping.queryRecruitInfo"; 
		Pager<RecruitInfo> pager1 =  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql,RecruitInfo.class, recruitInfo,userPermit); 
		return pager1;
	}


}