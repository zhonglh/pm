package com.pm.dao;

import java.util.List;

import com.pm.domain.business.ParamExtend;

public interface IParamExtendDao {
	
	
	public List<ParamExtend> queryAllParamExtend(ParamExtend params);
		

	public void updateParamExtend(ParamExtend params);

}
