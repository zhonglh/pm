package com.pm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.pm.dao.IShareInfoDao;
import com.pm.domain.business.ShareInfo;
import com.pm.vo.UserPermit;

import com.common.daos.BatisDao;
import com.common.beans.Pager;

@Component
public class ShareInfoDaoImpl extends BatisDao implements IShareInfoDao  {

	@Override
	public int addShareInfo(ShareInfo shareInfo) {
		String sql = "ShareInfoMapping.addShareInfo";
		return this.insert(sql, shareInfo);
	}

	@Override
	public int updateShareInfo(ShareInfo shareInfo) {
		String sql = "ShareInfoMapping.updateShareInfo";
		return this.update(sql, shareInfo);
	}

	@Override
	public void deleteShareInfo(ShareInfo shareInfo) {
		String sql = "ShareInfoMapping.deleteShareInfo";
		this.delete(sql, shareInfo);
	}


	@Override
	public ShareInfo getShareInfo(String id) {

		String sql = "ShareInfoMapping.getShareInfo"; 
		ShareInfo shareInfo = new ShareInfo(); 
		shareInfo.setId(id); 
		List<ShareInfo> list = this.query(sql, ShareInfo.class, shareInfo); 
		if(list == null || list.isEmpty()) return null; 
		else return list.get(0);	
	}

	@Override
	public Pager<ShareInfo> queryShareInfo(
		ShareInfo shareInfo,
		UserPermit userPermit,
		Pager<ShareInfo> pager){

		String sql = "ShareInfoMapping.queryShareInfo"; 
		Pager<ShareInfo> pager1 =  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql,ShareInfo.class, shareInfo,userPermit); 
		
		return pager1;
	}


}