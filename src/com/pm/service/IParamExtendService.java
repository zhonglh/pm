package com.pm.service;

import java.util.List;

import com.pm.domain.business.ParamExtend;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;

public interface IParamExtendService {

	public List<ParamExtend> queryAllParamExtend(ParamExtend paramExtend);
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_PARAM)
	public void updateParamExtend(List<ParamExtend> params);
	

	public void updateParamExtend(ParamExtend paramExtend);
	
}
