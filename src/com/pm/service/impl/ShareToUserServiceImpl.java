package com.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;import com.pm.domain.business.ShareToUser;
import com.pm.dao.IShareToUserDao;
import com.pm.service.IShareToUserService;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;

@Component
public class ShareToUserServiceImpl implements  IShareToUserService {

	@Autowired IShareToUserDao shareToUserDao;

	@Override
	public ShareToUser getShareToUser(String id) {
		return shareToUserDao.getShareToUser(id);
	}
	

	@Override
	public List<ShareToUser> getShareToUserByShare(String share_id) {
		return shareToUserDao.getShareToUserByShare(share_id);
	}

	@Override
	public Pager<ShareToUser> queryShareToUser(
		ShareToUser shareToUser,
		UserPermit userPermit,
		Pager<ShareToUser> pager){

		return shareToUserDao.queryShareToUser(shareToUser, userPermit, pager);
	}


}