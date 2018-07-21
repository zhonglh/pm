package com.pm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.IShareToUserDao;
import com.pm.domain.business.ShareToUser;
import com.pm.vo.UserPermit;

@Component
public class ShareToUserDaoImpl extends BatisDao implements IShareToUserDao  {

	@Override
	public int addShareToUser(ShareToUser shareToUser) {
		String sql = "ShareToUserMapping.addShareToUser";
		return this.insert(sql, shareToUser);
	}

	@Override
	public int updateShareToUser(ShareToUser shareToUser) {
		String sql = "ShareToUserMapping.updateShareToUser";
		return this.update(sql, shareToUser);
	}

	@Override
	public void deleteShareToUser(ShareToUser shareToUser) {
		String sql = "ShareToUserMapping.deleteShareToUser";
		this.delete(sql, shareToUser);
	}
	

	@Override
	public void deleteShareToUserByShare(ShareToUser shareToUser) {
		String sql = "ShareToUserMapping.deleteShareToUserByShare";
		this.delete(sql, shareToUser);
	}


	@Override
	public ShareToUser getShareToUser(String id) {

		String sql = "ShareToUserMapping.getShareToUser"; 
		ShareToUser shareToUser = new ShareToUser(); 
		shareToUser.setId(id); 
		List<ShareToUser> list = this.query(sql, ShareToUser.class, shareToUser); 
		if(list == null || list.isEmpty()) return null; 
		else return list.get(0);	
	}
	
	

	public List<ShareToUser> getShareToUserByShare(String share_id) {

		String sql = "ShareToUserMapping.getShareToUserByShare"; 
		ShareToUser shareToUser = new ShareToUser(); 
		shareToUser.setShare_id(share_id);
		return this.query(sql, ShareToUser.class, shareToUser); 
	}

	@Override
	public Pager<ShareToUser> queryShareToUser(
		ShareToUser shareToUser,
		UserPermit userPermit,
		Pager<ShareToUser> pager){

		String sql = "ShareToUserMapping.queryShareToUser"; 
		Pager<ShareToUser> pager1 =  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql,ShareToUser.class, shareToUser,userPermit); 
		
		return pager1;
	}


}