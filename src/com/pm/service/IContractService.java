package com.pm.service;

import com.pm.domain.business.Contract;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;
import com.pm.service.IBaseService;

/**
 * 收款合同处理服务类
 * @author zhonglihong
 * @date 2016年5月11日 下午6:40:41
 */
public interface IContractService extends IGeneralLogService{
	
	

	public boolean isContractNoExist(Contract contract) ;
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_INSERT,entity_type=LogConstant.ENTITY_CONTRACT)
	public int addContract(Contract contract) ;

	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_CONTRACT)
	public int updateContract(Contract contract) ; 

	@LogAnnotation(operation_type=LogConstant.OPERATION_DELETE,entity_type=LogConstant.ENTITY_CONTRACT)
	public void deleteContract(Contract[] contracts) ;

	public Contract getContract(String id) ;	

	public Pager<Contract> queryContract(Contract contract,  UserPermit userPermit,Pager<Contract> pager);

}