package com.pm.service;

import java.util.List;

import com.common.beans.Pager;
import com.pm.domain.business.ShareInfo;
import com.pm.domain.business.ShareToUser;
import com.pm.vo.UserPermit;

public interface IShareInfoService extends IGeneralLogService {

	public int addShareInfo(ShareInfo shareInfo,List<ShareToUser> toUsers) ;
	

	public int updateShareInfo(ShareInfo shareInfo,List<ShareToUser> toUsers);

	public void deleteShareInfo(ShareInfo[] shareInfos) ;


	public ShareInfo getShareInfo(String id) ;	

	public Pager<ShareInfo> queryShareInfo(ShareInfo shareInfo,  UserPermit userPermit,Pager<ShareInfo> pager);

}