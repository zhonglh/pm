package com.pm.dao;

import com.pm.domain.business.ShareInfo;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;

public interface IShareInfoDao {

	public int addShareInfo(ShareInfo shareInfo) ;

	public int updateShareInfo(ShareInfo shareInfo) ; 

	public void deleteShareInfo(ShareInfo shareInfo) ;


	public ShareInfo getShareInfo(String id) ;	

	public Pager<ShareInfo> queryShareInfo(ShareInfo shareInfo,  UserPermit userPermit,Pager<ShareInfo> pager);

}