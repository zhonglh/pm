package com.pm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.common.daos.BatisDao;
import com.pm.dao.IParamExtendDao;
import com.pm.domain.business.ParamExtend;

@Component
public class ParamExtendDaoImpl extends BatisDao implements IParamExtendDao {

	@Override
	public List<ParamExtend> queryAllParamExtend(ParamExtend params) {
		String sql = "ParamExtendMapping.queryAllParamExtend";
		return this.query(sql, ParamExtend.class, params);
	}

	@Override
	public void updateParamExtend(ParamExtend params) {
		String sql = "ParamExtendMapping.updateParamExtend";
		this.update(sql, params);
	}

}
