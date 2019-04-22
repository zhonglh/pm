package com.pm.service;

import java.util.List;
import java.util.Map;

import com.common.beans.Pager;
import com.pm.domain.business.StaffCost;
import com.pm.domain.business.StaffExEntity;
import com.pm.domain.business.StaffSalaryDetail;
import com.pm.domain.system.ItemDefine;
import com.pm.vo.UserPermit;
import com.pm.vo.echarts.series.Series;
import com.pm.vo.reports.StaffCharts;
import com.pm.vo.reports.StaffCountVO;

public interface IStaffChartsService {	

	public List<StaffCharts> getChilds(Map<String, Object> param);
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
	public Pager<StaffCountVO> queryStaffExEntity(StaffCountVO staffCountVO,  UserPermit userPermit,Pager<StaffCountVO> pager);
	

	
	
	

	/**
	 * 处理收入部分
	 * @param ctx
	 * @param marketMap
	 * @param staffExEntity
	 * @param staffCost
	 * @return
	 */
	public Series getOriginalModel1(double taxRate,Map<String, Object> ctx,Map<String,Object> marketMap,
			StaffExEntity staffExEntity,StaffCost staffCost) ;
	
	

	/**
	 * 处理支出部分
	 * @param ctx
	 * @param marketMap
	 * @param staffExEntity
	 * @param marketMap
	 * @return
	 */
	public Series getOriginalModel2(double taxRate,Map<String, Object> ctx,Map<String,Object> marketMap,
			StaffExEntity staffExEntity,StaffCost staffCost);
		

	/**
	 * 整体情况
	 * @param ctx
	 * @param staffExEntity
	 * @param should_salary
	 * @return
	 */
	public Series getOriginalModel3(double taxRate,Map<String, Object> ctx, 
			StaffExEntity staffExEntity,StaffCost staffCost,double should_salary);

	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 处理收入部分
	 * @param ctx
	 * @param marketMap
	 * @param staffExEntity
	 * @param staffCost
	 * @return
	 */
	public Series getMarketModel1(double taxRate,Map<String, Object> ctx,Map<String,Object> marketMap,
			StaffExEntity staffExEntity,StaffCost staffCost, List<StaffSalaryDetail>staffSalaryDetails) ;
	

	/**
	 * 处理支出部分
	 * @param ctx
	 * @param marketMap
	 * @param staffExEntity
	 * @return
	 */
	public Series getMarketModel2(double taxRate,Map<String, Object> ctx,Map<String,Object> marketMap,
			StaffExEntity staffExEntity,StaffCost staffCost,List<StaffSalaryDetail>staffSalaryDetails);
		
	
	
	/**
	 * 整体情况
	 * @param ctx
	 * @param staffExEntity
	 * @param should_salary
	 * @return
	 */
	public Series getMarketModel3(double taxRate,Map<String, Object> ctx, 
			StaffExEntity staffExEntity,StaffCost staffCost,double should_salary);
	
	

}
