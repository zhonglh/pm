package com.pm.dao;

import java.util.List;
import java.util.Map;

import com.common.beans.Pager;
import com.pm.vo.UserPermit;
import com.pm.vo.reports.StaffCharts;
import com.pm.vo.reports.StaffCountVO;

public interface IStaffChartsDao {
	
	/**
	 * 获取核心下级
	 * @param param
	 * @return
	 */
	public List<StaffCharts> getChilds(Map<String, Object> param);
	
	/**
	 * 获取核心上级
	 * @param param
	 * @return
	 */
	public List<StaffCharts> getParents(Map<String, Object> param);
	
	
	/**
	 * 获取人员工资大类
	 * @return
	 */
	public List<StaffCountVO> getStaffSalaryCatalog();
	
	

	/**
	 * 获取人员下级
	 * @return
	 */
	public List<StaffCountVO> getStaffChilds();
	

	/**
	 * 统计人员收支内容
	 * @param staffCountVO
	 * @param userPermit
	 * @param pager
	 * @return
	 */
	public Pager<StaffCountVO> queryStaffExEntity(StaffCountVO staffCountVO,  UserPermit userPermit,Pager<StaffCountVO> pager) ;

}
