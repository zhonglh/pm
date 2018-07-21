
package com.pm.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.actions.BaseAction;
import com.common.beans.Pager;
import com.pm.domain.business.Statistics;
import com.pm.service.ISalesStatisticsService;
import com.pm.service.IRoleService;
import com.pm.util.PubMethod;
import com.pm.util.constant.EnumPermit;
import com.pm.util.excel.BusinessExcel;
import com.pm.vo.SalesStatisticsAll;
import com.pm.vo.SalesStatisticsClient;
import com.pm.vo.SalesStatisticsDept;
import com.pm.vo.SalesStatisticsExecDept;
import com.pm.vo.SalesStatisticsInfoSourcer;
import com.pm.vo.SalesStatisticsManager;
import com.pm.vo.SalesStatisticsProject;
import com.pm.vo.SalesStatisticsQuarter;
import com.pm.vo.SalesStatisticsSalesDept;
import com.pm.vo.SalesStatisticsSalesUser;
import com.pm.vo.SalesStatisticsYear;
import com.pm.vo.UserPermit;

/**
 * 项目销售统计
 * @author zhonglh
 *
 */
@Controller
@RequestMapping(value = "ProjectSalesStatisticsAction.do")
public class ProjectSalesStatisticsAction extends BaseAction {



	private static final String rel = "rel41";
	
	@Autowired
	private IRoleService roleService;

	@Autowired
	private ISalesStatisticsService salesStatisticsService;
	

	@RequestMapping(params = "method=list")
	public String list(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.SALESVIEW.getId());
		
		Pager<Statistics> pager = salesStatisticsService.queryByProject(searchStatistics, userPermit, PubMethod.getPager(request, Statistics.class));
			
		
		Pager<Statistics> all = salesStatisticsService.queryByAll(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		if(all.getResultList().size() > 0){
			Statistics first = all.getResultList().get(0);
			double total_amount = 0;
			if(first != null ) total_amount = first.getStatistics_amount();
			pager.setTotal_amount(total_amount);
		}
		PubMethod.setRequestPager(request, pager,Statistics.class);	
		return "statistics/sales_list";
	}
	

	/**
	 * 导出Excel
	 * @param searchStatistics
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=export")
	public void export(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.SALESVIEW.getId());
		
		Pager<Statistics> pager = salesStatisticsService.queryByProject(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		List<SalesStatisticsProject> list = new ArrayList<SalesStatisticsProject>();
		if(pager.getResultList() != null){
			for(Statistics statistics : pager.getResultList()){
				SalesStatisticsProject statisticsTarget = new SalesStatisticsProject();
				BeanUtils.copyProperties(statistics, statisticsTarget);
				list.add(statisticsTarget);
			}
		}
		try{
			BusinessExcel.export(res, list , SalesStatisticsProject.class);
		}catch(Exception e){
			
		}
		
	}	
	
	

	@RequestMapping(params = "method=salesUserList")
	public String salesUserList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.SALESVIEW.getId());
		Pager<Statistics> pager = salesStatisticsService.queryBySales(searchStatistics, userPermit, PubMethod.getPager(request, Statistics.class));
			
		
		Pager<Statistics> all = salesStatisticsService.queryByAll(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		if(all.getResultList().size() > 0){
			Statistics first = all.getResultList().get(0);
			double total_amount = 0;
			if(first != null ) total_amount = first.getStatistics_amount();
			pager.setTotal_amount(total_amount);
		}
		PubMethod.setRequestPager(request, pager,Statistics.class);	
		return "statistics/sales_salesuser_list";
	}
	

	/**
	 * 导出Excel
	 * @param searchStatistics
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=exportSalesUserList")
	public void exportSalesUserList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.SALESVIEW.getId());
		
		Pager<Statistics> pager = salesStatisticsService.queryBySales(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		List<SalesStatisticsSalesUser> list = new ArrayList<SalesStatisticsSalesUser>();
		if(pager.getResultList() != null){
			for(Statistics statistics : pager.getResultList()){
				SalesStatisticsSalesUser statisticsTarget = new SalesStatisticsSalesUser();
				BeanUtils.copyProperties(statistics, statisticsTarget);
				list.add(statisticsTarget);
			}
		}
		try{
			BusinessExcel.export(res, list , SalesStatisticsSalesUser.class);
		}catch(Exception e){
			
		}
		
	}	


	@RequestMapping(params = "method=managerList")
	public String managerList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.SALESVIEW.getId());
		Pager<Statistics> pager = salesStatisticsService.queryByManager(searchStatistics, userPermit, PubMethod.getPager(request, Statistics.class));
			
		
		Pager<Statistics> all = salesStatisticsService.queryByAll(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		if(all.getResultList().size() > 0){
			Statistics first = all.getResultList().get(0);
			double total_amount = 0;
			if(first != null ) total_amount = first.getStatistics_amount();
			pager.setTotal_amount(total_amount);
		}
		PubMethod.setRequestPager(request, pager,Statistics.class);	
		return "statistics/sales_manager_list";
	}
	

	/**
	 * 导出Excel
	 * @param searchStatistics
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=exportManagerList")
	public void exportManagerList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.SALESVIEW.getId());
		
		Pager<Statistics> pager = salesStatisticsService.queryByManager(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		List<SalesStatisticsManager> list = new ArrayList<SalesStatisticsManager>();
		if(pager.getResultList() != null){
			for(Statistics statistics : pager.getResultList()){
				SalesStatisticsManager statisticsTarget = new SalesStatisticsManager();
				BeanUtils.copyProperties(statistics, statisticsTarget);
				list.add(statisticsTarget);
			}
		}
		try{
			BusinessExcel.export(res, list , SalesStatisticsManager.class);
		}catch(Exception e){
			
		}
		
	}
	

	@RequestMapping(params = "method=infoSourceList")
	public String infoSourceList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.SALESVIEW.getId());
		Pager<Statistics> pager = salesStatisticsService.queryByInfoSource(searchStatistics, userPermit, PubMethod.getPager(request, Statistics.class));
			
		
		Pager<Statistics> all = salesStatisticsService.queryByAll(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		if(all.getResultList().size() > 0){
			Statistics first = all.getResultList().get(0);
			double total_amount = 0;
			if(first != null ) total_amount = first.getStatistics_amount();
			pager.setTotal_amount(total_amount);
		}
		PubMethod.setRequestPager(request, pager,Statistics.class);	
		return "statistics/sales_infosource_list";
	}
	

	/**
	 * 导出Excel
	 * @param searchStatistics
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=exportInfoSourceList")
	public void exportInfoSourceList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.SALESVIEW.getId());
		
		Pager<Statistics> pager = salesStatisticsService.queryByInfoSource(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		List<SalesStatisticsInfoSourcer> list = new ArrayList<SalesStatisticsInfoSourcer>();
		if(pager.getResultList() != null){
			for(Statistics statistics : pager.getResultList()){
				SalesStatisticsInfoSourcer statisticsTarget = new SalesStatisticsInfoSourcer();
				BeanUtils.copyProperties(statistics, statisticsTarget);
				list.add(statisticsTarget);
			}
		}
		try{
			BusinessExcel.export(res, list , SalesStatisticsInfoSourcer.class);
		}catch(Exception e){
			
		}
		
	}
		

	@RequestMapping(params = "method=clientList")
	public String clientList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.SALESVIEW.getId());
		Pager<Statistics> pager = salesStatisticsService.queryByClient(searchStatistics, userPermit, PubMethod.getPager(request, Statistics.class));
			
		
		Pager<Statistics> all = salesStatisticsService.queryByAll(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		if(all.getResultList().size() > 0){
			Statistics first = all.getResultList().get(0);
			double total_amount = 0;
			if(first != null ) total_amount = first.getStatistics_amount();
			pager.setTotal_amount(total_amount);
		}
		PubMethod.setRequestPager(request, pager,Statistics.class);	
		return "statistics/sales_client_list";
	}
	

	/**
	 * 导出Excel
	 * @param searchStatistics
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=exportClientList")
	public void exportClientList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.SALESVIEW.getId());		
		Pager<Statistics> pager = salesStatisticsService.queryByClient(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		List<SalesStatisticsClient> list = new ArrayList<SalesStatisticsClient>();
		if(pager.getResultList() != null){
			for(Statistics statistics : pager.getResultList()){
				SalesStatisticsClient statisticsTarget = new SalesStatisticsClient();
				BeanUtils.copyProperties(statistics, statisticsTarget);
				list.add(statisticsTarget);
			}
		}
		try{
			BusinessExcel.export(res, list , SalesStatisticsClient.class);
		}catch(Exception e){
			
		}
		
	}	
	
	

	@RequestMapping(params = "method=salesDeptList")
	public String salesDeptList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.SALESVIEW.getId());
		Pager<Statistics> pager = salesStatisticsService.queryBySalesDept(searchStatistics, userPermit, PubMethod.getPager(request, Statistics.class));
			
		
		Pager<Statistics> all = salesStatisticsService.queryByAll(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		if(all.getResultList().size() > 0){
			Statistics first = all.getResultList().get(0);
			double total_amount = 0;
			if(first != null ) total_amount = first.getStatistics_amount();
			pager.setTotal_amount(total_amount);
		}
		PubMethod.setRequestPager(request, pager,Statistics.class);	
		return "statistics/sales_salesdept_list";
	}
	

	/**
	 * 导出Excel
	 * @param searchStatistics
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=exportSalesDeptList")
	public void exportSalesDeptList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.SALESVIEW.getId());		
		Pager<Statistics> pager = salesStatisticsService.queryBySalesDept(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		List<SalesStatisticsSalesDept> list = new ArrayList<SalesStatisticsSalesDept>();
		if(pager.getResultList() != null){
			for(Statistics statistics : pager.getResultList()){
				SalesStatisticsSalesDept statisticsTarget = new SalesStatisticsSalesDept();
				BeanUtils.copyProperties(statistics, statisticsTarget);
				list.add(statisticsTarget);
			}
		}
		try{
			BusinessExcel.export(res, list , SalesStatisticsSalesDept.class);
		}catch(Exception e){
			
		}
		
	}		
	

	@RequestMapping(params = "method=execDeptList")
	public String execDeptList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.SALESVIEW.getId());
		Pager<Statistics> pager = salesStatisticsService.queryByExecDept(searchStatistics, userPermit, PubMethod.getPager(request, Statistics.class));
			
		
		Pager<Statistics> all = salesStatisticsService.queryByAll(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		if(all.getResultList().size() > 0){
			Statistics first = all.getResultList().get(0);
			double total_amount = 0;
			if(first != null ) total_amount = first.getStatistics_amount();
			pager.setTotal_amount(total_amount);
		}
		PubMethod.setRequestPager(request, pager,Statistics.class);	
		return "statistics/sales_execdept_list";
	}
	

	/**
	 * 导出Excel
	 * @param searchStatistics
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=exportExecDeptList")
	public void exportExecDeptList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.SALESVIEW.getId());		
		Pager<Statistics> pager = salesStatisticsService.queryByExecDept(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		List<SalesStatisticsExecDept> list = new ArrayList<SalesStatisticsExecDept>();
		if(pager.getResultList() != null){
			for(Statistics statistics : pager.getResultList()){
				SalesStatisticsExecDept statisticsTarget = new SalesStatisticsExecDept();
				BeanUtils.copyProperties(statistics, statisticsTarget);
				list.add(statisticsTarget);
			}
		}
		try{
			BusinessExcel.export(res, list , SalesStatisticsExecDept.class);
		}catch(Exception e){
			
		}
		
	}		
	

	@RequestMapping(params = "method=deptList")
	public String deptList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.SALESVIEW.getId());
		Pager<Statistics> pager = salesStatisticsService.queryByDept(searchStatistics, userPermit, PubMethod.getPager(request, Statistics.class));
			
		
		Pager<Statistics> all = salesStatisticsService.queryByAll(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		if(all.getResultList().size() > 0){
			Statistics first = all.getResultList().get(0);
			double total_amount = 0;
			if(first != null ) total_amount = first.getStatistics_amount();
			pager.setTotal_amount(total_amount);
		}
		PubMethod.setRequestPager(request, pager,Statistics.class);	
		return "statistics/sales_dept_list";
	}
	

	/**
	 * 导出Excel
	 * @param searchStatistics
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=exportDeptList")
	public void exportDeptList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.SALESVIEW.getId());		
		Pager<Statistics> pager = salesStatisticsService.queryByDept(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		List<SalesStatisticsDept> list = new ArrayList<SalesStatisticsDept>();
		if(pager.getResultList() != null){
			for(Statistics statistics : pager.getResultList()){
				SalesStatisticsDept statisticsTarget = new SalesStatisticsDept();
				BeanUtils.copyProperties(statistics, statisticsTarget);
				list.add(statisticsTarget);
			}
		}
		try{
			BusinessExcel.export(res, list , SalesStatisticsDept.class);
		}catch(Exception e){
			
		}
		
	}		
	

	@RequestMapping(params = "method=yearList")
	public String yearList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.SALESVIEW.getId());
		Pager<Statistics> pager = salesStatisticsService.queryByYear(searchStatistics, userPermit, PubMethod.getPager(request, Statistics.class));
			
		
		Pager<Statistics> all = salesStatisticsService.queryByAll(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		if(all.getResultList().size() > 0){
			Statistics first = all.getResultList().get(0);
			double total_amount = 0;
			if(first != null ) total_amount = first.getStatistics_amount();
			pager.setTotal_amount(total_amount);
		}
		PubMethod.setRequestPager(request, pager,Statistics.class);	
		return "statistics/sales_year_list";
	}
	

	/**
	 * 导出Excel
	 * @param searchStatistics
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=exportYearList")
	public void exportYearList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.SALESVIEW.getId());		
		Pager<Statistics> pager = salesStatisticsService.queryByYear(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		List<SalesStatisticsYear> list = new ArrayList<SalesStatisticsYear>();
		if(pager.getResultList() != null){
			for(Statistics statistics : pager.getResultList()){
				SalesStatisticsYear statisticsTarget = new SalesStatisticsYear();
				//statisticsTarget.setProject_name(statistics.getProject_name());
				statisticsTarget.setYear(String.valueOf(statistics.getYear1()));
				statisticsTarget.setStatistics_amount(statistics.getStatistics_amount());
				list.add(statisticsTarget);
			}
		}
		try{
			BusinessExcel.export(res, list , SalesStatisticsYear.class);
		}catch(Exception e){
			
		}
		
	}		
	
	
	
	

	@RequestMapping(params = "method=quarterList")
	public String quarterList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.SALESVIEW.getId());
		Pager<Statistics> pager = salesStatisticsService.queryByQuarter(searchStatistics, userPermit, PubMethod.getPager(request, Statistics.class));
		if(pager.getResultList() != null){
			for(Statistics statistics : pager.getResultList()){
				statistics.setQuarter_name(PubMethod.changeQuarterView(String.valueOf(statistics.getQuarter())));
			}
		}		
			

		
		Pager<Statistics> all = salesStatisticsService.queryByAll(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		if(all.getResultList().size() > 0){
			Statistics first = all.getResultList().get(0);
			double total_amount = 0;
			if(first != null ) total_amount = first.getStatistics_amount();
			pager.setTotal_amount(total_amount);
		}
		PubMethod.setRequestPager(request, pager,Statistics.class);	
		return "statistics/sales_quarter_list";
	}
	

	/**
	 * 导出Excel
	 * @param searchStatistics
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=exportQuarterList")
	public void exportQuarterList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.SALESVIEW.getId());		
		Pager<Statistics> pager = salesStatisticsService.queryByQuarter(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		List<SalesStatisticsQuarter> list = new ArrayList<SalesStatisticsQuarter>();
		if(pager.getResultList() != null){
			for(Statistics statistics : pager.getResultList()){
				SalesStatisticsQuarter statisticsTarget = new SalesStatisticsQuarter();
				statisticsTarget.setQuarter(PubMethod.changeQuarterView(String.valueOf(statistics.getQuarter())));
				statisticsTarget.setStatistics_amount(statistics.getStatistics_amount());
				list.add(statisticsTarget);
			}
		}
		try{
			BusinessExcel.export(res, list , SalesStatisticsQuarter.class);
		}catch(Exception e){
			
		}
		
	}		
	
	
	@RequestMapping(params = "method=allList")
	public String allList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.SALESVIEW.getId());
		Pager<Statistics> pager = salesStatisticsService.queryByAll(searchStatistics, userPermit, PubMethod.getPager(request, Statistics.class));
		if(pager.getResultList() != null){
			for(Statistics statistics : pager.getResultList()){
				statistics.setProject_name("所有项目");
			}
		}		
		PubMethod.setRequestPager(request, pager,Statistics.class);	
		return "statistics/sales_all_list";
	}
	

	/**
	 * 导出Excel
	 * @param searchStatistics
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=exportAllList")
	public void exportAllList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.SALESVIEW.getId());		
		Pager<Statistics> pager = salesStatisticsService.queryByAll(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		List<SalesStatisticsAll> list = new ArrayList<SalesStatisticsAll>();
		if(pager.getResultList() != null){
			for(Statistics statistics : pager.getResultList()){
				SalesStatisticsAll statisticsTarget = new SalesStatisticsAll();
				BeanUtils.copyProperties(statistics, statisticsTarget);
				statisticsTarget.setProject_name("所有项目");
				list.add(statisticsTarget);
			}
		}
		try{
			BusinessExcel.export(res, list , SalesStatisticsAll.class);
		}catch(Exception e){
			
		}
		
	}		
	
}
