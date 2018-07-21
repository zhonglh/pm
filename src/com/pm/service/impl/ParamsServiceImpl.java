package com.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pm.dao.IParamsDao;
import com.pm.domain.business.Params;
import com.pm.service.IParamsService;

@Component
public class ParamsServiceImpl implements IParamsService {

	@Autowired IParamsDao paramsDao; 

	@Override
	public List<Params> queryAllParams(Params params) {
		return paramsDao.queryAllParams(params);
	}
	
	public void updateParams(List<Params> paramss){
		for(Params params : paramss)
			this.updateParams(params);
	}

	@Override
	public void updateParams(Params params) {
		paramsDao.updateParams(params);
	}

}
