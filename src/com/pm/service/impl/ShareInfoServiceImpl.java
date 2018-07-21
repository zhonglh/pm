package com.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;import com.pm.domain.business.ShareInfo;
import com.pm.domain.business.ShareToUser;
import com.pm.dao.IShareInfoDao;
import com.pm.dao.IShareToUserDao;
import com.pm.service.IShareInfoService;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;

@Component
public class ShareInfoServiceImpl implements  IShareInfoService {

	@Autowired IShareInfoDao shareInfoDao;
	@Autowired IShareToUserDao shareToUserDao;
	@Override
	public int addShareInfo(ShareInfo shareInfo,List<ShareToUser> toUsers) {
		int count = shareInfoDao.addShareInfo(shareInfo);
		if(toUsers != null && toUsers.size() >0){
			for(ShareToUser toUser : toUsers){
				shareToUserDao.addShareToUser(toUser);
			}
		}
		return count;
	}

	@Override
	public int updateShareInfo(ShareInfo shareInfo,List<ShareToUser> toUsers) {
		ShareToUser shareToUser = new ShareToUser();
		shareToUser.setShare_id(shareInfo.getId());
		shareToUserDao.deleteShareToUserByShare(shareToUser);
		
		if(toUsers != null && toUsers.size() >0){
			for(ShareToUser toUser : toUsers){
				shareToUserDao.addShareToUser(toUser);
			}
		}
		return shareInfoDao.updateShareInfo(shareInfo);
	}

	@Override
	public void deleteShareInfo(ShareInfo[] shareInfos) {
		for(ShareInfo shareInfo : shareInfos){
			shareInfoDao.deleteShareInfo(shareInfo);
		}
	}


	@Override
	public ShareInfo getShareInfo(String id) {
		return shareInfoDao.getShareInfo(id);
	}

	@Override
	public Pager<ShareInfo> queryShareInfo(
		ShareInfo shareInfo,
		UserPermit userPermit,
		Pager<ShareInfo> pager){

		return shareInfoDao.queryShareInfo(shareInfo, userPermit, pager);
	}

	@Override
	public <T> T get(String id) {
		return (T)getShareInfo(id);
	}
	
	
	


}