package com.pm.service;

import java.util.List;

import com.pm.domain.business.ApplyApprove;
import com.pm.domain.system.User;

public interface IApplyApproveService {
	
	ApplyApprove buildApplyApprove(String operation_type,String data_type,String data_id,User sessionUser) ;
	
	
	/**
	 * 查出一个单据的所有申请 批复信息
	 * @param dataType
	 * @param dataId
	 * @return
	 */
	List<ApplyApprove> queryByDataId(String dataType, String dataId);
	
	
	void doApprove(ApplyApprove oldApplyApprove , ApplyApprove newApplyApprove);
	
	/**
	 * 增加一条信息
	 * @param applyApprove
	 */
	int addApplyApprove(ApplyApprove applyApprove);
	
	/**
	 * 修改信息
	 * @param applyApprove
	 */
	int updateApplyApprove(ApplyApprove applyApprove);

	/**
	 * 撤回申请信息
	 * @param applyApprove
	 */
	void deleteApplyApprove(ApplyApprove applyApprove);
	
	/**
	 * 需要处理的申请
	 * @param dataType
	 * @param dataId
	 * @return ApplyApprove
	 */
	ApplyApprove needHandle(String dataType, String dataId);
	
	
	/**
	 * 处理后续的业务，  删除核单信息
	 * @param applyApprove
	 */
	void afterBusiness(ApplyApprove applyApprove);

}
