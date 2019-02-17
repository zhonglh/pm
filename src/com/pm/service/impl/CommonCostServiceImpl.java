package com.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;import com.pm.domain.business.CommonCost;
import com.pm.dao.ICommonCostDao;
import com.pm.service.ICommonCostService;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;

@Component
public class CommonCostServiceImpl implements  ICommonCostService {

	@Autowired ICommonCostDao commoncostDao;
	@Override
	public int addCommonCost(CommonCost commoncost) {
		return commoncostDao.addCommonCost(commoncost);
	}

	@Override
	public int updateCommonCost(CommonCost commoncost) {
		return commoncostDao.updateCommonCost(commoncost);
	}

	@Override
	public void deleteCommonCost(CommonCost[] commoncosts) {
		for(CommonCost commoncost : commoncosts){
			commoncostDao.deleteCommonCost(commoncost);
		}
	}

	@Override
	public void verifyCommonCost(CommonCost commoncost) {
		commoncostDao.verifyCommonCost(commoncost);
	}

	@Override
	public void unVerify(String id) {
		CommonCost commoncost = new CommonCost();
		commoncost.setId(id);
		commoncostDao.unVerifyCommonCost(commoncost);
	}

	@Override
	public CommonCost getCommonCost(String id) {
		return commoncostDao.getCommonCost(id);
	}

	@Override
	public Pager<CommonCost> queryCommonCost(
		CommonCost commoncost,
		UserPermit userPermit,
		Pager<CommonCost> pager){

		return commoncostDao.queryCommonCost(commoncost, userPermit, pager);
	}


	@Override
	public <T> T get(String id) {
		return (T)getCommonCost(id);
	}
}