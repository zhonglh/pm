package com.pm.service;

import com.pm.domain.business.ShareToUser;
import com.pm.vo.UserPermit;

import java.util.List;

import com.common.beans.Pager;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;
import com.pm.service.IGeneralLogService;

public interface IShareToUserService {


	public ShareToUser getShareToUser(String id) ;	

	public List<ShareToUser> getShareToUserByShare(String share_id) ;

	public Pager<ShareToUser> queryShareToUser(ShareToUser shareToUser,  UserPermit userPermit,Pager<ShareToUser> pager);

}