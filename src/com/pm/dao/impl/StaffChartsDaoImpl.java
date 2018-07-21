package com.pm.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.IStaffChartsDao;
import com.pm.vo.UserPermit;
import com.pm.vo.reports.StaffCharts;
import com.pm.vo.reports.StaffCountVO;


@Component
public class StaffChartsDaoImpl extends BatisDao implements IStaffChartsDao {

	@Override
	public List<StaffCharts> getChilds(Map<String, Object> param) {
		String sql = "StaffChartsMapping.getChilds";
		return this.execProc4ListObject(sql, param);
	}

	@Override
	public List<StaffCharts> getParents(Map<String, Object> param) {
		String sql = "StaffChartsMapping.getParents";
		return this.execProc4ListObject(sql, param);
	}
	

	@Override
	public List<StaffCountVO> getStaffSalaryCatalog(){
		String sql = "StaffSalaryCount.getStaffSalaryCatalog";
		return this.query(sql,StaffCountVO.class);
	}
	
	

	@Override
	public List<StaffCountVO> getStaffChilds(){
		String sql = "StaffSalaryCount.getStaffChilds";
		return this.query(sql ,StaffCountVO.class);
	}

	@Override
	public Pager<StaffCountVO> queryStaffExEntity(StaffCountVO staffCountVO, UserPermit userPermit,
			Pager<StaffCountVO> pager) {


		String sql = "StaffSalaryCount.queryStaffCount"; 
		Pager<StaffCountVO> pager1 =  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql,StaffCountVO.class, staffCountVO,userPermit); 
		return pager1;
	}
	
	

}
