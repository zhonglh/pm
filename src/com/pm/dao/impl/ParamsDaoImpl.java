package com.pm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.common.daos.BatisDao;
import com.pm.dao.IParamsDao;
import com.pm.domain.business.Params;

@Component
public class ParamsDaoImpl extends BatisDao implements IParamsDao {



	@Override
	public List<Params> queryAllParams(Params params) {
		String sql = "ParamsMapping.queryAllParams";
		return this.query(sql, Params.class, params);
	}

	@Override
	public void updateParams(Params params) {
		String sql = "ParamsMapping.updateParams";
		this.update(sql, params);
	}

}
