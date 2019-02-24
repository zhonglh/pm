package com.pm.dao.impl;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.IOtherStaffCostDao;
import com.pm.dao.IOtherStaffCostDao;
import com.pm.domain.business.OtherStaffCost;
import com.pm.domain.business.OtherSalary;
import com.pm.vo.UserPermit;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OtherStaffCostDaoImpl extends BatisDao implements IOtherStaffCostDao {


	@Override
	public void addOtherStaffCost(OtherStaffCost otherStaffCost) {
		String sql = "OtherStaffCostMapping.addOtherStaffCost";
		this.insert(sql,otherStaffCost);

	}

	@Override
	public void deleteOtherStaffCost(OtherSalary salary) {
		String sql = "OtherStaffCostMapping.deleteOtherStaffCost";
		this.delete(sql,salary);
	}

	@Override
	public Pager<OtherStaffCost> queryOtherStaffCost(
			OtherStaffCost otherStaffCost, 
			UserPermit userPermit,
			Pager<OtherStaffCost> pager) {

		String sql = "OtherStaffCostMapping.queryOtherStaffCost";
		Pager<OtherStaffCost> pager1 =  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql, OtherStaffCost.class, otherStaffCost,userPermit);
		
		return pager1;
		
		
	}


	@Override
	public OtherStaffCost queryOtherStaffCostSum(OtherStaffCost otherStaffCost, UserPermit userPermit ){
		String sql = "OtherStaffCostMapping.queryOtherStaffCostSum";
		Map  map = new HashMap();
		if(otherStaffCost !=null) {
			map.put(otherStaffCost.getClass().getSimpleName(), otherStaffCost);
		}
		if(userPermit !=null) {
			map.put(userPermit.getClass().getSimpleName(), userPermit);
		}

		return this.query(sql,OtherStaffCost.class, map).get(0);
	}

	@Override
	public OtherStaffCost getOtherStaffCost(OtherStaffCost otherStaffCost) {
		String sql = "OtherStaffCostMapping.getOtherStaffCost";	
		List<OtherStaffCost> list = this.query(sql, OtherStaffCost.class, otherStaffCost);
		if(list == null || list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}		
	}

	@Override
	public List<OtherStaffCost> getOtherStaffCost(OtherSalary[] salarys) {
		String sql = "OtherStaffCostMapping.getOtherStaffCostByOtherSalary";
		Map<String,OtherSalary[]> map = new HashMap<String,OtherSalary[]>();
		map.put("list", salarys);
		return this.query(sql, OtherStaffCost.class, map);
	}

	@Override
	public List<OtherStaffCost> computeOtherStaffCost(OtherSalary salary) {
		String sql = "OtherStaffCostMapping.computeOtherStaffCost";
		return this.query(sql, OtherStaffCost.class, salary);
	}
	

}
