package com.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.exceptions.PMException;
import com.common.utils.IDKit;
import com.pm.dao.IApplyApproveDao;
import com.pm.domain.business.ApplyApprove;
import com.pm.domain.system.User;
import com.pm.service.IApplyApproveService;
import com.pm.service.IBaseService;
import com.pm.service.ICommonService;
import com.pm.util.PubMethod;
import com.pm.util.constant.BusinessUtil;
import com.pm.util.constant.EnumApplyApproveType;

@Component
public class ApplyApproveServiceImpl implements IApplyApproveService {

	@Autowired IApplyApproveDao applyApproveDao;
	

	@Autowired ICommonService commonService;
	

	@Override
	public ApplyApprove buildApplyApprove(String operation_type,String data_type,String data_id,User sessionUser) {
		ApplyApprove applyApprove =new ApplyApprove();
		

		applyApprove.setId(IDKit.generateId());
		applyApprove.setUser_id(sessionUser.getUser_id());
		applyApprove.setUser_name(sessionUser.getUser_name());
		applyApprove.setOperatioin_time(PubMethod.getCurrentDate());		
		applyApprove.setDelete_flag(BusinessUtil.NOT_DELETEED);
		
		applyApprove.setOperation_type(operation_type);
		applyApprove.setData_type(data_type);
		applyApprove.setData_id(data_id);
		
		applyApprove.setNeed_approve("0");
		
		return applyApprove;
	}
	
	

	/**
	 * 处理后续的业务，  删除核单信息
	 * @param applyApprove
	 */
	@Override
	public void afterBusiness(ApplyApprove applyApprove){
		IBaseService baseService = commonService.getBusinessService(applyApprove.getData_type());
		if(baseService != null) {
			baseService.unVerify(applyApprove.getData_id());
		}
	}
	
	
	
	

	@Override
	public List<ApplyApprove> queryByDataId(String dataType, String dataId) {
		return applyApproveDao.queryByDataId(dataType, dataId);
	}
	
	
	@Override
	public void doApprove(ApplyApprove oldApplyApprove, ApplyApprove newApplyApprove){
		
		int count = addApplyApprove(newApplyApprove);
		if(count != 1){
			throw new PMException("11111","输入的数据太长");
		}
		if(EnumApplyApproveType.APPROVE.getKey().equals(newApplyApprove.getOperation_type())){
			
			if("1".equals(newApplyApprove.getApprove_result())){
				//真正取消核单处理
				afterBusiness(newApplyApprove);
			}
			
			oldApplyApprove.setNeed_approve("0");
			updateApplyApprove(oldApplyApprove);
		}
	}

	@Override
	public int addApplyApprove(ApplyApprove applyApprove) {
		return applyApproveDao.addApplyApprove(applyApprove);
	}

	@Override
	public int updateApplyApprove(ApplyApprove applyApprove) {
		return applyApproveDao.updateApplyApprove(applyApprove);
	}

	@Override
	public void deleteApplyApprove(ApplyApprove applyApprove) {
		applyApproveDao.deleteApplyApprove(applyApprove);

	}

	@Override
	public ApplyApprove needHandle(String dataType, String dataId) {		
		return applyApproveDao.needHandle(dataType, dataId);
	}

}
