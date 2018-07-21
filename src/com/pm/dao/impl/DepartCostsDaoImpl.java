package com.pm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.IDepartCostsDao;
import com.pm.domain.business.DepartCosts;
import com.pm.vo.UserPermit;


@Component
public class DepartCostsDaoImpl extends BatisDao implements IDepartCostsDao {

	

	@Override
	public int addDepartCosts(DepartCosts departCosts) {
		String sql = "DepartCostsMapping.addDepartCosts";
		return this.insert(sql,departCosts);

	}

	@Override
	public int updateDepartCosts(DepartCosts departCosts) {
		String sql = "DepartCostsMapping.updateDepartCosts";
		return this.update(sql,departCosts);
	}

	@Override
	public void deleteDepartCosts(DepartCosts departCosts) {
		String sql = "DepartCostsMapping.deleteDepartCosts";
		this.delete(sql,departCosts);

	}
	

	@Override
	public int verifyDepartCosts(DepartCosts departCosts){
		String sql = "DepartCostsMapping.verifyDepartCosts";
		return this.update(sql,departCosts);		
	}
		

	@Override
	public int unVerifyDepartCosts(DepartCosts departCosts){
		String sql = "DepartCostsMapping.unVerifyDepartCosts";
		return this.update(sql,departCosts);		
	}

	@Override
	public DepartCosts getDepartCosts(String id) {
		String sql = "DepartCostsMapping.getDepartCosts";
		DepartCosts departCosts = new DepartCosts();
		departCosts.setId(id);
		List<DepartCosts> list = this.query(sql, DepartCosts.class, departCosts);
		if(list == null || list.isEmpty()) return null;
		else return list.get(0);
	}

	@Override
	public Pager<DepartCosts> queryDepartCosts(
			DepartCosts departCosts, UserPermit userPermit,
			Pager<DepartCosts> pager) {
		String sql = "DepartCostsMapping.queryDepartCosts";
		Pager<DepartCosts> pager1 = this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, DepartCosts.class, departCosts,userPermit);
		return pager1;
	}

}
