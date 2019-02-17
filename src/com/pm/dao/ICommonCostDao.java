package com.pm.dao;

import com.pm.domain.business.CommonCost;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;

public interface ICommonCostDao {

	public int addCommonCost(CommonCost commoncost) ;

	public int updateCommonCost(CommonCost commoncost) ; 

	public void deleteCommonCost(CommonCost commoncost) ;

	public void verifyCommonCost(CommonCost commoncost) ;	

	public void unVerifyCommonCost(CommonCost commoncost) ;

	public CommonCost getCommonCost(String id) ;	

	public Pager<CommonCost> queryCommonCost(CommonCost commoncost, UserPermit userPermit, Pager<CommonCost> pager);

}