package com.pm.dao;

import java.util.List;

import com.pm.domain.business.Params;

public interface IParamsDao {


	
	public List<Params> queryAllParams(Params params);
	

	public void updateParams(Params params);
}
