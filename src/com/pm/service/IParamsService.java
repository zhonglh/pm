package com.pm.service;

import java.util.List;

import com.pm.domain.business.Params;
import com.pm.util.constant.LogConstant;
import com.pm.util.log.LogAnnotation;

public interface IParamsService {

	public List<Params> queryAllParams(Params params);
	

	@LogAnnotation(operation_type=LogConstant.OPERATION_UPDATE,entity_type=LogConstant.ENTITY_PARAM)
	public void updateParams(List<Params> params);
	

	public void updateParams(Params params);
	
}
