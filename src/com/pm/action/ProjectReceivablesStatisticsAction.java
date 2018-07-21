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
import com.pm.service.IReceivablesStatisticsService;
import com.pm.service.IRoleService;
import com.pm.util.PubMethod;
import com.pm.util.constant.EnumPermit;
import com.pm.util.excel.BusinessExcel;
import com.pm.vo.ReceivablesStatisticsAll;
import com.pm.vo.ReceivablesStatisticsClient;
import com.pm.vo.ReceivablesStatisticsDept;
import com.pm.vo.ReceivablesStatisticsExecDept;
import com.pm.vo.ReceivablesStatisticsInfoSourcer;
import com.pm.vo.ReceivablesStatisticsManager;
import com.pm.vo.ReceivablesStatisticsProject;
import com.pm.vo.ReceivablesStatisticsQuarter;
import com.pm.vo.ReceivablesStatisticsSalesDept;
import com.pm.vo.ReceivablesStatisticsSalesUser;
import com.pm.vo.ReceivablesStatisticsYear;
import com.pm.vo.UserPermit;

/**
 * 项目应收款统计
 * @author zhonglh
 *
 */
@Controller
@RequestMapping(value = "ProjectReceivablesStatisticsAction.do")
public class ProjectReceivablesStatisticsAction extends BaseAction {


	private static final String rel = "rel42";
	
	@Autowired
	private IRoleService roleService;

	@Autowired
	private IReceivablesStatisticsService receivablesStatisticsService;
	

	@RequestMapping(params = "method=list")
	public String list(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.RECEIVABLESVIEW.getId());
		
		Pager<Statistics> pager = receivablesStatisticsService.queryByProject(searchStatistics, userPermit, PubMethod.getPager(request, Statistics.class));
			

		Pager<Statistics> all = receivablesStatisticsService.queryByAll(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		if(all.getResultList().size() > 0){
			Statistics first = all.getResultList().get(0);
			double total_amount = 0;
			if(first != null ) total_amount = first.getStatistics_amount();
			pager.setTotal_amount(total_amount);
		}
		PubMethod.setRequestPager(request, pager,Statistics.class);	
		return "statistics/receivables_list";
	}
	

	/**
	 * 导出Excel
	 * @param searchStatistics
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=export")
	public void export(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.RECEIVABLESVIEW.getId());
		
		Pager<Statistics> pager = receivablesStatisticsService.queryByProject(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		List<ReceivablesStatisticsProject> list = new ArrayList<ReceivablesStatisticsProject>();
		if(pager.getResultList() != null){
			int index = 1;
			for(Statistics statistics : pager.getResultList()){
				ReceivablesStatisticsProject statisticsTarget = new ReceivablesStatisticsProject();
				BeanUtils.copyProperties(statistics, statisticsTarget);
				index++;
				list.add(statisticsTarget);
			}
		}
		try{
			BusinessExcel.export(res, list , ReceivablesStatisticsProject.class);
		}catch(Exception e){
			
		}
		
	}	
	
	

	@RequestMapping(params = "method=salesUserList")
	public String salesUserList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.RECEIVABLESVIEW.getId());
		Pager<Statistics> pager = receivablesStatisticsService.queryBySales(searchStatistics, userPermit, PubMethod.getPager(request, Statistics.class));
			

		Pager<Statistics> all = receivablesStatisticsService.queryByAll(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		if(all.getResultList().size() > 0){
			Statistics first = all.getResultList().get(0);
			double total_amount = 0;
			if(first != null ) total_amount = first.getStatistics_amount();
			pager.setTotal_amount(total_amount);
		}
		PubMethod.setRequestPager(request, pager,Statistics.class);	
		return "statistics/receivables_salesuser_list";
	}
	

	/**
	 * 导出Excel
	 * @param searchStatistics
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=exportSalesUserList")
	public void exportSalesUserList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.RECEIVABLESVIEW.getId());
		
		Pager<Statistics> pager = receivablesStatisticsService.queryBySales(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		List<ReceivablesStatisticsSalesUser> list = new ArrayList<ReceivablesStatisticsSalesUser>();
		if(pager.getResultList() != null){
			for(Statistics statistics : pager.getResultList()){
				ReceivablesStatisticsSalesUser statisticsTarget = new ReceivablesStatisticsSalesUser();
				BeanUtils.copyProperties(statistics, statisticsTarget);
				list.add(statisticsTarget);
			}
		}
		try{
			BusinessExcel.export(res, list , ReceivablesStatisticsSalesUser.class);
		}catch(Exception e){
			
		}
		
	}	


	@RequestMapping(params = "method=managerList")
	public String managerList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.RECEIVABLESVIEW.getId());
		Pager<Statistics> pager = receivablesStatisticsService.queryByManager(searchStatistics, userPermit, PubMethod.getPager(request, Statistics.class));
			

		Pager<Statistics> all = receivablesStatisticsService.queryByAll(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		if(all.getResultList().size() > 0){
			Statistics first = all.getResultList().get(0);
			double total_amount = 0;
			if(first != null ) total_amount = first.getStatistics_amount();
			pager.setTotal_amount(total_amount);
		}
		PubMethod.setRequestPager(request, pager,Statistics.class);	
		return "statistics/receivables_manager_list";
	}
	

	/**
	 * 导出Excel
	 * @param searchStatistics
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=exportManagerList")
	public void exportManagerList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.RECEIVABLESVIEW.getId());
		
		Pager<Statistics> pager = receivablesStatisticsService.queryByManager(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		List<ReceivablesStatisticsManager> list = new ArrayList<ReceivablesStatisticsManager>();
		if(pager.getResultList() != null){
			for(Statistics statistics : pager.getResultList()){
				ReceivablesStatisticsManager statisticsTarget = new ReceivablesStatisticsManager();
				BeanUtils.copyProperties(statistics, statisticsTarget);
				list.add(statisticsTarget);
			}
		}
		try{
			BusinessExcel.export(res, list , ReceivablesStatisticsManager.class);
		}catch(Exception e){
			
		}
		
	}
	

	@RequestMapping(params = "method=infoSourceList")
	public String infoSourceList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.RECEIVABLESVIEW.getId());
		Pager<Statistics> pager = receivablesStatisticsService.queryByInfoSource(searchStatistics, userPermit, PubMethod.getPager(request, Statistics.class));
			

		Pager<Statistics> all = receivablesStatisticsService.queryByAll(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		if(all.getResultList().size() > 0){
			Statistics first = all.getResultList().get(0);
			double total_amount = 0;
			if(first != null ) total_amount = first.getStatistics_amount();
			pager.setTotal_amount(total_amount);
		}
		PubMethod.setRequestPager(request, pager,Statistics.class);	
		return "statistics/receivables_infosource_list";
	}
	

	/**
	 * 导出Excel
	 * @param searchStatistics
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=exportInfoSourceList")
	public void exportInfoSourceList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.RECEIVABLESVIEW.getId());
		
		Pager<Statistics> pager = receivablesStatisticsService.queryByInfoSource(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		List<ReceivablesStatisticsInfoSourcer> list = new ArrayList<ReceivablesStatisticsInfoSourcer>();
		if(pager.getResultList() != null){
			for(Statistics statistics : pager.getResultList()){
				ReceivablesStatisticsInfoSourcer statisticsTarget = new ReceivablesStatisticsInfoSourcer();
				BeanUtils.copyProperties(statistics, statisticsTarget);
				list.add(statisticsTarget);
			}
		}
		try{
			BusinessExcel.export(res, list , ReceivablesStatisticsInfoSourcer.class);
		}catch(Exception e){
			
		}
		
	}
		

	@RequestMapping(params = "method=clientList")
	public String clientList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.RECEIVABLESVIEW.getId());
		Pager<Statistics> pager = receivablesStatisticsService.queryByClient(searchStatistics, userPermit, PubMethod.getPager(request, Statistics.class));
			

		Pager<Statistics> all = receivablesStatisticsService.queryByAll(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		if(all.getResultList().size() > 0){
			Statistics first = all.getResultList().get(0);
			double total_amount = 0;
			if(first != null ) total_amount = first.getStatistics_amount();
			pager.setTotal_amount(total_amount);
		}
		PubMethod.setRequestPager(request, pager,Statistics.class);	
		return "statistics/receivables_client_list";
	}
	

	/**
	 * 导出Excel
	 * @param searchStatistics
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=exportClientList")
	public void exportClientList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.RECEIVABLESVIEW.getId());		
		Pager<Statistics> pager = receivablesStatisticsService.queryByClient(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		List<ReceivablesStatisticsClient> list = new ArrayList<ReceivablesStatisticsClient>();
		if(pager.getResultList() != null){
			for(Statistics statistics : pager.getResultList()){
				ReceivablesStatisticsClient statisticsTarget = new ReceivablesStatisticsClient();
				BeanUtils.copyProperties(statistics, statisticsTarget);
				list.add(statisticsTarget);
			}
		}
		try{
			BusinessExcel.export(res, list , ReceivablesStatisticsClient.class);
		}catch(Exception e){
			
		}
		
	}	
	
	

	@RequestMapping(params = "method=salesDeptList")
	public String salesDeptList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.RECEIVABLESVIEW.getId());
		Pager<Statistics> pager = receivablesStatisticsService.queryBySalesDept(searchStatistics, userPermit, PubMethod.getPager(request, Statistics.class));
			

		Pager<Statistics> all = receivablesStatisticsService.queryByAll(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		if(all.getResultList().size() > 0){
			Statistics first = all.getResultList().get(0);
			double total_amount = 0;
			if(first != null ) total_amount = first.getStatistics_amount();
			pager.setTotal_amount(total_amount);
		}
		PubMethod.setRequestPager(request, pager,Statistics.class);	
		return "statistics/receivables_salesdept_list";
	}
	

	/**
	 * 导出Excel
	 * @param searchStatistics
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=exportSalesDeptList")
	public void exportSalesDeptList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.RECEIVABLESVIEW.getId());		
		Pager<Statistics> pager = receivablesStatisticsService.queryBySalesDept(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		List<ReceivablesStatisticsSalesDept> list = new ArrayList<ReceivablesStatisticsSalesDept>();
		if(pager.getResultList() != null){
			for(Statistics statistics : pager.getResultList()){
				ReceivablesStatisticsSalesDept statisticsTarget = new ReceivablesStatisticsSalesDept();
				BeanUtils.copyProperties(statistics, statisticsTarget);
				list.add(statisticsTarget);
			}
		}
		try{
			BusinessExcel.export(res, list , ReceivablesStatisticsSalesDept.class);
		}catch(Exception e){
			
		}
		
	}		
	

	@RequestMapping(params = "method=execDeptList")
	public String execDeptList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.RECEIVABLESVIEW.getId());
		Pager<Statistics> pager = receivablesStatisticsService.queryByExecDept(searchStatistics, userPermit, PubMethod.getPager(request, Statistics.class));
			

		Pager<Statistics> all = receivablesStatisticsService.queryByAll(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		if(all.getResultList().size() > 0){
			Statistics first = all.getResultList().get(0);
			double total_amount = 0;
			if(first != null ) total_amount = first.getStatistics_amount();
			pager.setTotal_amount(total_amount);
		}
		PubMethod.setRequestPager(request, pager,Statistics.class);	
		return "statistics/receivables_execdept_list";
	}
	

	/**
	 * 导出Excel
	 * @param searchStatistics
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=exportExecDeptList")
	public void exportExecDeptList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.RECEIVABLESVIEW.getId());		
		Pager<Statistics> pager = receivablesStatisticsService.queryByExecDept(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		List<ReceivablesStatisticsExecDept> list = new ArrayList<ReceivablesStatisticsExecDept>();
		if(pager.getResultList() != null){
			for(Statistics statistics : pager.getResultList()){
				ReceivablesStatisticsExecDept statisticsTarget = new ReceivablesStatisticsExecDept();
				BeanUtils.copyProperties(statistics, statisticsTarget);
				list.add(statisticsTarget);
			}
		}
		try{
			BusinessExcel.export(res, list , ReceivablesStatisticsExecDept.class);
		}catch(Exception e){
			
		}
		
	}		
	

	@RequestMapping(params = "method=deptList")
	public String deptList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.RECEIVABLESVIEW.getId());
		Pager<Statistics> pager = receivablesStatisticsService.queryByDept(searchStatistics, userPermit, PubMethod.getPager(request, Statistics.class));
			

		Pager<Statistics> all = receivablesStatisticsService.queryByAll(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		if(all.getResultList().size() > 0){
			Statistics first = all.getResultList().get(0);
			double total_amount = 0;
			if(first != null ) total_amount = first.getStatistics_amount();
			pager.setTotal_amount(total_amount);
		}
		PubMethod.setRequestPager(request, pager,Statistics.class);	
		return "statistics/receivables_dept_list";
	}
	

	/**
	 * 导出Excel
	 * @param searchStatistics
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=exportDeptList")
	public void exportDeptList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.RECEIVABLESVIEW.getId());		
		Pager<Statistics> pager = receivablesStatisticsService.queryByDept(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		List<ReceivablesStatisticsDept> list = new ArrayList<ReceivablesStatisticsDept>();
		if(pager.getResultList() != null){
			for(Statistics statistics : pager.getResultList()){
				ReceivablesStatisticsDept statisticsTarget = new ReceivablesStatisticsDept();
				BeanUtils.copyProperties(statistics, statisticsTarget);
				list.add(statisticsTarget);
			}
		}
		try{
			BusinessExcel.export(res, list , ReceivablesStatisticsDept.class);
		}catch(Exception e){
			
		}
		
	}		
	

	@RequestMapping(params = "method=yearList")
	public String yearList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.RECEIVABLESVIEW.getId());
		Pager<Statistics> pager = receivablesStatisticsService.queryByYear(searchStatistics, userPermit, PubMethod.getPager(request, Statistics.class));
			

		Pager<Statistics> all = receivablesStatisticsService.queryByAll(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		if(all.getResultList().size() > 0){
			Statistics first = all.getResultList().get(0);
			double total_amount = 0;
			if(first != null ) total_amount = first.getStatistics_amount();
			pager.setTotal_amount(total_amount);
		}
		PubMethod.setRequestPager(request, pager,Statistics.class);	
		return "statistics/receivables_year_list";
	}
	

	/**
	 * 导出Excel
	 * @param searchStatistics
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=exportYearList")
	public void exportYearList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.RECEIVABLESVIEW.getId());		
		Pager<Statistics> pager = receivablesStatisticsService.queryByYear(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		List<ReceivablesStatisticsYear> list = new ArrayList<ReceivablesStatisticsYear>();
		if(pager.getResultList() != null){
			for(Statistics statistics : pager.getResultList()){
				ReceivablesStatisticsYear statisticsTarget = new ReceivablesStatisticsYear();
				//statisticsTarget.setProject_name(statistics.getProject_name());
				statisticsTarget.setYear(String.valueOf(statistics.getYear1()));
				statisticsTarget.setStatistics_amount(statistics.getStatistics_amount());
				list.add(statisticsTarget);
			}
		}
		try{
			BusinessExcel.export(res, list , ReceivablesStatisticsYear.class);
		}catch(Exception e){
			
		}
		
	}		
	

	@RequestMapping(params = "method=quarterList")
	public String quarterList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.RECEIVABLESVIEW.getId());
		Pager<Statistics> pager = receivablesStatisticsService.queryByQuarter(searchStatistics, userPermit, PubMethod.getPager(request, Statistics.class));
		if(pager.getResultList() != null){
			for(Statistics statistics : pager.getResultList()){
				statistics.setQuarter_name(PubMethod.changeQuarterView(String.valueOf(statistics.getQuarter())));
			}
		}		
			

		Pager<Statistics> all = receivablesStatisticsService.queryByAll(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		if(all.getResultList().size() > 0){
			Statistics first = all.getResultList().get(0);
			double total_amount = 0;
			if(first != null ) total_amount = first.getStatistics_amount();
			pager.setTotal_amount(total_amount);
		}
		PubMethod.setRequestPager(request, pager,Statistics.class);	
		return "statistics/receivables_quarter_list";
	}
	

	/**
	 * 导出Excel
	 * @param searchStatistics
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=exportQuarterList")
	public void exportQuarterList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.RECEIVABLESVIEW.getId());		
		Pager<Statistics> pager = receivablesStatisticsService.queryByQuarter(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		List<ReceivablesStatisticsQuarter> list = new ArrayList<ReceivablesStatisticsQuarter>();
		if(pager.getResultList() != null){
			for(Statistics statistics : pager.getResultList()){
				ReceivablesStatisticsQuarter statisticsTarget = new ReceivablesStatisticsQuarter();
				statisticsTarget.setQuarter(PubMethod.changeQuarterView(String.valueOf(statistics.getQuarter())));
				statisticsTarget.setStatistics_amount(statistics.getStatistics_amount());
				list.add(statisticsTarget);
			}
		}
		try{
			BusinessExcel.export(res, list , ReceivablesStatisticsQuarter.class);
		}catch(Exception e){
			
		}
		
	}
	

	@RequestMapping(params = "method=allList")
	public String allList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.RECEIVABLESVIEW.getId());
		Pager<Statistics> pager = receivablesStatisticsService.queryByAll(searchStatistics, userPermit, PubMethod.getPager(request, Statistics.class));
		if(pager.getResultList() != null){
			for(Statistics statistics : pager.getResultList()){
				statistics.setProject_name("所有项目");
			}
		}		
		PubMethod.setRequestPager(request, pager,Statistics.class);	
		return "statistics/receivables_all_list";
	}
	

	/**
	 * 导出Excel
	 * @param searchStatistics
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=exportAllList")
	public void exportAllList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.RECEIVABLESVIEW.getId());		
		Pager<Statistics> pager = receivablesStatisticsService.queryByAll(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		List<ReceivablesStatisticsAll> list = new ArrayList<ReceivablesStatisticsAll>();
		if(pager.getResultList() != null){
			for(Statistics statistics : pager.getResultList()){
				ReceivablesStatisticsAll statisticsTarget = new ReceivablesStatisticsAll();
				BeanUtils.copyProperties(statistics, statisticsTarget);
				statisticsTarget.setProject_name("所有项目");
				list.add(statisticsTarget);
			}
		}
		try{
			BusinessExcel.export(res, list , ReceivablesStatisticsAll.class);
		}catch(Exception e){
			
		}
		
	}	
}
