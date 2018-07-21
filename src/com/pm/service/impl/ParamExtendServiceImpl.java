package com.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pm.dao.IParamExtendDao;
import com.pm.domain.business.ParamExtend;
import com.pm.service.IParamExtendService;

@Component
public class ParamExtendServiceImpl implements IParamExtendService {
	

	@Autowired IParamExtendDao paramsDao; 

	@Override
	public List<ParamExtend> queryAllParamExtend(ParamExtend params) {
		return paramsDao.queryAllParamExtend(params);
	}

	@Override
	public void updateParamExtend(List<ParamExtend> params) {
		if(params != null && !params.isEmpty()) {
			for(ParamExtend pe : params){
				updateParamExtend(pe);
			}
		}
	}

	@Override
	public void updateParamExtend(ParamExtend params) {
		paramsDao.updateParamExtend(params);
	}

}
