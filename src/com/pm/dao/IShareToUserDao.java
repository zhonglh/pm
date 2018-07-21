package com.pm.dao;

import com.pm.domain.business.ShareToUser;
import com.pm.vo.UserPermit;

import java.util.List;

import com.common.beans.Pager;

public interface IShareToUserDao {

	public int addShareToUser(ShareToUser shareToUser) ;

	public int updateShareToUser(ShareToUser shareToUser) ; 

	public void deleteShareToUser(ShareToUser shareToUser) ;

	public void deleteShareToUserByShare(ShareToUser shareToUser) ;
	

	public ShareToUser getShareToUser(String id) ;	

	public List<ShareToUser> getShareToUserByShare(String share_id) ;	
	

	public Pager<ShareToUser> queryShareToUser(ShareToUser shareToUser,  UserPermit userPermit,Pager<ShareToUser> pager);

}