package com.pm.action;

import com.common.actions.BaseAction;
import com.common.beans.Pager;
import com.pm.domain.business.Statistics;
import com.pm.service.IGrossProfitStatisticsService;
import com.pm.service.IRoleService;
import com.pm.util.PubMethod;
import com.pm.util.constant.EnumPermit;
import com.pm.util.excel.exports.BusinessExcel;
import com.pm.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


/**
 * 项目回款毛利统计
 * @author zhonglh
 *
 */
@Controller
@RequestMapping(value = "ProjectGrossProfitStatisticsAction3.do")
public class ProjectGrossProfitStatisticsAction3 extends BaseAction {


	private static String detailMethod = "queryGrossProfit3Detail";

	private static final String aciont = "ProjectGrossProfitStatisticsAction3.do";
	private static final String urls = "gross_profit3_urls";

	private static final String rel = "rel46";
	
	@Autowired
	private IRoleService roleService;

	//@Autowired
	//private IGrossProfitStatisticsService grossProfitStatisticsService;
	

	@Autowired
	@Qualifier("grossProfitStatisticsServiceImpl3")
	private IGrossProfitStatisticsService grossProfitStatisticsService;

	
	


	@RequestMapping(params = "method=list")
	public String list(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		computeTaxRate(searchStatistics);
		
		
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.GROSSPROFIT3VIEW.getId());
		
		Pager<Statistics> pager = getGrossProfitStatisticsService().queryByProject(searchStatistics, userPermit, PubMethod.getPager(request, Statistics.class));
		
		request.setAttribute("action", aciont);
		request.setAttribute("urls", urls);

		request.setAttribute("detailMethod", detailMethod);
		
		Pager<Statistics> all = getGrossProfitStatisticsService().queryByAll(searchStatistics, userPermit, PubMethod.getPagerByAll(Statistics.class));
		if(all.getResultList().size() > 0){
			Statistics first = all.getResultList().get(0);
			double total_amount = 0;
			if(first != null ) total_amount = first.getStatistics_amount();
			pager.setTotal_amount(total_amount);
		}
		PubMethod.setRequestPager(request, pager,Statistics.class);	
		return "statistics/gross_profit_list";
	}


	

	/**
	 * 导出Excel
	 * @param searchStatistics
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=export")
	public void export(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.GROSSPROFIT3VIEW.getId());
		computeTaxRate(searchStatistics);
		Pager<Statistics> pager = getGrossProfitStatisticsService().queryByProject(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		List<GrossProfitStatisticsProject> list = new ArrayList<GrossProfitStatisticsProject>();
		if(pager.getResultList() != null){
			for(Statistics statistics : pager.getResultList()){
				GrossProfitStatisticsProject statisticsTarget = new GrossProfitStatisticsProject();
				BeanUtils.copyProperties(statistics, statisticsTarget);
				
				list.add(statisticsTarget);
			}
		}
		try{
			BusinessExcel.export(res, list , GrossProfitStatisticsProject.class);
		}catch(Exception e){
			
		}
		
	}	
	
	

	@RequestMapping(params = "method=salesUserList")
	public String salesUserList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.GROSSPROFIT3VIEW.getId());
		computeTaxRate(searchStatistics);
		Pager<Statistics> pager = getGrossProfitStatisticsService().queryBySales(searchStatistics, userPermit, PubMethod.getPager(request, Statistics.class));
		
		request.setAttribute("action", aciont);
		request.setAttribute("urls", urls);

		Pager<Statistics> all = getGrossProfitStatisticsService().queryByAll(searchStatistics, userPermit, PubMethod.getPagerByAll(Statistics.class));
		if(all.getResultList().size() > 0){
			Statistics first = all.getResultList().get(0);
			double total_amount = 0;
			if(first != null ) total_amount = first.getStatistics_amount();
			pager.setTotal_amount(total_amount);
		}
		PubMethod.setRequestPager(request, pager,Statistics.class);	
		return "statistics/gross_profit_salesuser_list";
	}
	

	/**
	 * 导出Excel
	 * @param searchStatistics
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=exportSalesUserList")
	public void exportSalesUserList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.GROSSPROFIT3VIEW.getId());
		computeTaxRate(searchStatistics);
		Pager<Statistics> pager = getGrossProfitStatisticsService().queryBySales(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		List<GrossProfitStatisticsSalesUser> list = new ArrayList<GrossProfitStatisticsSalesUser>();
		if(pager.getResultList() != null){
			for(Statistics statistics : pager.getResultList()){
				GrossProfitStatisticsSalesUser statisticsTarget = new GrossProfitStatisticsSalesUser();
				BeanUtils.copyProperties(statistics, statisticsTarget);
				list.add(statisticsTarget);
			}
		}
		try{
			BusinessExcel.export(res, list , GrossProfitStatisticsSalesUser.class);
		}catch(Exception e){
			
		}
		
	}	


	@RequestMapping(params = "method=managerList")
	public String managerList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.GROSSPROFIT3VIEW.getId());
		computeTaxRate(searchStatistics);
		Pager<Statistics> pager = getGrossProfitStatisticsService().queryByManager(searchStatistics, userPermit, PubMethod.getPager(request, Statistics.class));
		
		request.setAttribute("action", aciont);
		request.setAttribute("urls", urls);
		
		Pager<Statistics> all = getGrossProfitStatisticsService().queryByAll(searchStatistics, userPermit, PubMethod.getPagerByAll(Statistics.class));
		if(all.getResultList().size() > 0){
			Statistics first = all.getResultList().get(0);
			double total_amount = 0;
			if(first != null ) total_amount = first.getStatistics_amount();
			pager.setTotal_amount(total_amount);
		}
		PubMethod.setRequestPager(request, pager,Statistics.class);	
		return "statistics/gross_profit_manager_list";
	}
	

	/**
	 * 导出Excel
	 * @param searchStatistics
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=exportManagerList")
	public void exportManagerList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.GROSSPROFIT3VIEW.getId());
		computeTaxRate(searchStatistics);
		Pager<Statistics> pager = getGrossProfitStatisticsService().queryByManager(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		List<GrossProfitStatisticsManager> list = new ArrayList<GrossProfitStatisticsManager>();
		if(pager.getResultList() != null){
			for(Statistics statistics : pager.getResultList()){
				GrossProfitStatisticsManager statisticsTarget = new GrossProfitStatisticsManager();
				BeanUtils.copyProperties(statistics, statisticsTarget);
				list.add(statisticsTarget);
			}
		}
		try{
			BusinessExcel.export(res, list , GrossProfitStatisticsManager.class);
		}catch(Exception e){
			
		}
		
	}
	

	@RequestMapping(params = "method=infoSourceList")
	public String infoSourceList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.GROSSPROFIT3VIEW.getId());
		computeTaxRate(searchStatistics);
		Pager<Statistics> pager = getGrossProfitStatisticsService().queryByInfoSource(searchStatistics, userPermit, PubMethod.getPager(request, Statistics.class));
		
		request.setAttribute("action", aciont);
		request.setAttribute("urls", urls);
		
		Pager<Statistics> all = getGrossProfitStatisticsService().queryByAll(searchStatistics, userPermit, PubMethod.getPagerByAll(Statistics.class));
		if(all.getResultList().size() > 0){
			Statistics first = all.getResultList().get(0);
			double total_amount = 0;
			if(first != null ) total_amount = first.getStatistics_amount();
			pager.setTotal_amount(total_amount);
		}
		PubMethod.setRequestPager(request, pager,Statistics.class);	
		return "statistics/gross_profit_infosource_list";
	}
	

	/**
	 * 导出Excel
	 * @param searchStatistics
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=exportInfoSourceList")
	public void exportInfoSourceList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.GROSSPROFIT3VIEW.getId());
		computeTaxRate(searchStatistics);
		Pager<Statistics> pager = getGrossProfitStatisticsService().queryByInfoSource(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		List<GrossProfitStatisticsInfoSourcer> list = new ArrayList<GrossProfitStatisticsInfoSourcer>();
		if(pager.getResultList() != null){
			for(Statistics statistics : pager.getResultList()){
				GrossProfitStatisticsInfoSourcer statisticsTarget = new GrossProfitStatisticsInfoSourcer();
				BeanUtils.copyProperties(statistics, statisticsTarget);
				list.add(statisticsTarget);
			}
		}
		try{
			BusinessExcel.export(res, list , GrossProfitStatisticsInfoSourcer.class);
		}catch(Exception e){
			
		}
		
	}
		

	@RequestMapping(params = "method=clientList")
	public String clientList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.GROSSPROFIT3VIEW.getId());
		computeTaxRate(searchStatistics);
		Pager<Statistics> pager = getGrossProfitStatisticsService().queryByClient(searchStatistics, userPermit, PubMethod.getPager(request, Statistics.class));
		
		request.setAttribute("action", aciont);
		request.setAttribute("urls", urls);
		
		Pager<Statistics> all = getGrossProfitStatisticsService().queryByAll(searchStatistics, userPermit, PubMethod.getPagerByAll(Statistics.class));
		if(all.getResultList().size() > 0){
			Statistics first = all.getResultList().get(0);
			double total_amount = 0;
			if(first != null ) total_amount = first.getStatistics_amount();
			pager.setTotal_amount(total_amount);
		}
		PubMethod.setRequestPager(request, pager,Statistics.class);	
		return "statistics/gross_profit_client_list";
	}
	

	/**
	 * 导出Excel
	 * @param searchStatistics
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=exportClientList")
	public void exportClientList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.GROSSPROFIT3VIEW.getId());		
		computeTaxRate(searchStatistics);
		Pager<Statistics> pager = getGrossProfitStatisticsService().queryByClient(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		List<GrossProfitStatisticsClient> list = new ArrayList<GrossProfitStatisticsClient>();
		if(pager.getResultList() != null){
			for(Statistics statistics : pager.getResultList()){
				GrossProfitStatisticsClient statisticsTarget = new GrossProfitStatisticsClient();
				BeanUtils.copyProperties(statistics, statisticsTarget);
				list.add(statisticsTarget);
			}
		}
		try{
			BusinessExcel.export(res, list , GrossProfitStatisticsClient.class);
		}catch(Exception e){
			
		}
		
	}	
	
	

	@RequestMapping(params = "method=salesDeptList")
	public String salesDeptList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.GROSSPROFIT3VIEW.getId());
		computeTaxRate(searchStatistics);
		Pager<Statistics> pager = getGrossProfitStatisticsService().queryBySalesDept(searchStatistics, userPermit, PubMethod.getPager(request, Statistics.class));
		
		request.setAttribute("action", aciont);
		request.setAttribute("urls", urls);
		
		Pager<Statistics> all = getGrossProfitStatisticsService().queryByAll(searchStatistics, userPermit, PubMethod.getPagerByAll(Statistics.class));
		if(all.getResultList().size() > 0){
			Statistics first = all.getResultList().get(0);
			double total_amount = 0;
			if(first != null ) total_amount = first.getStatistics_amount();
			pager.setTotal_amount(total_amount);
		}

		PubMethod.setRequestPager(request, pager,Statistics.class);	
		return "statistics/gross_profit_salesdept_list";
	}
	

	/**
	 * 导出Excel
	 * @param searchStatistics
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=exportSalesDeptList")
	public void exportSalesDeptList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.GROSSPROFIT3VIEW.getId());	
		computeTaxRate(searchStatistics);
		Pager<Statistics> pager = getGrossProfitStatisticsService().queryBySalesDept(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		List<GrossProfitStatisticsSalesDept> list = new ArrayList<GrossProfitStatisticsSalesDept>();
		if(pager.getResultList() != null){
			for(Statistics statistics : pager.getResultList()){
				GrossProfitStatisticsSalesDept statisticsTarget = new GrossProfitStatisticsSalesDept();
				BeanUtils.copyProperties(statistics, statisticsTarget);
				list.add(statisticsTarget);
			}
		}
		try{
			BusinessExcel.export(res, list , GrossProfitStatisticsSalesDept.class);
		}catch(Exception e){
			
		}
		
	}		
	

	@RequestMapping(params = "method=execDeptList")
	public String execDeptList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.GROSSPROFIT3VIEW.getId());
		computeTaxRate(searchStatistics);
		Pager<Statistics> pager = getGrossProfitStatisticsService().queryByExecDept(searchStatistics, userPermit, PubMethod.getPager(request, Statistics.class));
		
		request.setAttribute("action", aciont);
		request.setAttribute("urls", urls);
		
		Pager<Statistics> all = getGrossProfitStatisticsService().queryByAll(searchStatistics, userPermit, PubMethod.getPagerByAll(Statistics.class));
		if(all.getResultList().size() > 0){
			Statistics first = all.getResultList().get(0);
			double total_amount = 0;
			if(first != null ) total_amount = first.getStatistics_amount();
			pager.setTotal_amount(total_amount);
		}
		PubMethod.setRequestPager(request, pager,Statistics.class);	
		return "statistics/gross_profit_execdept_list";
	}
	

	/**
	 * 导出Excel
	 * @param searchStatistics
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=exportExecDeptList")
	public void exportExecDeptList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.GROSSPROFIT3VIEW.getId());	
		computeTaxRate(searchStatistics);
		Pager<Statistics> pager = getGrossProfitStatisticsService().queryByExecDept(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		List<GrossProfitStatisticsExecDept> list = new ArrayList<GrossProfitStatisticsExecDept>();
		if(pager.getResultList() != null){
			for(Statistics statistics : pager.getResultList()){
				GrossProfitStatisticsExecDept statisticsTarget = new GrossProfitStatisticsExecDept();
				BeanUtils.copyProperties(statistics, statisticsTarget);
				list.add(statisticsTarget);
			}
		}
		try{
			BusinessExcel.export(res, list , GrossProfitStatisticsExecDept.class);
		}catch(Exception e){
			
		}
		
	}		
	

	@RequestMapping(params = "method=deptList")
	public String deptList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.GROSSPROFIT3VIEW.getId());
		computeTaxRate(searchStatistics);
		Pager<Statistics> pager = getGrossProfitStatisticsService().queryByDept(searchStatistics, userPermit, PubMethod.getPager(request, Statistics.class));
		
		request.setAttribute("action", aciont);
		request.setAttribute("urls", urls);
		
		Pager<Statistics> all = getGrossProfitStatisticsService().queryByAll(searchStatistics, userPermit, PubMethod.getPagerByAll(Statistics.class));
		if(all.getResultList().size() > 0){
			Statistics first = all.getResultList().get(0);
			double total_amount = 0;
			if(first != null ) total_amount = first.getStatistics_amount();
			pager.setTotal_amount(total_amount);
		}
		PubMethod.setRequestPager(request, pager,Statistics.class);	
		return "statistics/gross_profit_dept_list";
	}
	

	/**
	 * 导出Excel
	 * @param searchStatistics
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=exportDeptList")
	public void exportDeptList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.GROSSPROFIT3VIEW.getId());	
		computeTaxRate(searchStatistics);
		Pager<Statistics> pager = getGrossProfitStatisticsService().queryByDept(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		List<GrossProfitStatisticsDept> list = new ArrayList<GrossProfitStatisticsDept>();
		if(pager.getResultList() != null){
			for(Statistics statistics : pager.getResultList()){
				GrossProfitStatisticsDept statisticsTarget = new GrossProfitStatisticsDept();
				BeanUtils.copyProperties(statistics, statisticsTarget);
				list.add(statisticsTarget);
			}
		}
		try{
			BusinessExcel.export(res, list , GrossProfitStatisticsDept.class);
		}catch(Exception e){
			
		}
		
	}		
	

	@RequestMapping(params = "method=yearList")
	public String yearList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.GROSSPROFIT3VIEW.getId());
		computeTaxRate(searchStatistics);
		Pager<Statistics> pager = getGrossProfitStatisticsService().queryByYear(searchStatistics, userPermit, PubMethod.getPager(request, Statistics.class));
		
		request.setAttribute("action", aciont);
		request.setAttribute("urls", urls);
		
		Pager<Statistics> all = getGrossProfitStatisticsService().queryByAll(searchStatistics, userPermit, PubMethod.getPagerByAll(Statistics.class));
		if(all.getResultList().size() > 0){
			Statistics first = all.getResultList().get(0);
			double total_amount = 0;
			if(first != null ) total_amount = first.getStatistics_amount();
			pager.setTotal_amount(total_amount);
		}
		PubMethod.setRequestPager(request, pager,Statistics.class);	
		return "statistics/gross_profit_year_list";
	}
	

	/**
	 * 导出Excel
	 * @param searchStatistics
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=exportYearList")
	public void exportYearList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.GROSSPROFIT3VIEW.getId());		
		computeTaxRate(searchStatistics);
		Pager<Statistics> pager = getGrossProfitStatisticsService().queryByYear(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		List<GrossProfitStatisticsYear> list = new ArrayList<GrossProfitStatisticsYear>();
		if(pager.getResultList() != null){
			for(Statistics statistics : pager.getResultList()){
				GrossProfitStatisticsYear statisticsTarget = new GrossProfitStatisticsYear();
				statisticsTarget.setYear(String.valueOf(statistics.getYear1()));
				statisticsTarget.setStatistics_amount(statistics.getStatistics_amount());
				statisticsTarget.setStatistics_rate(statistics.getStatistics_rate());
				list.add(statisticsTarget);
			}
		}
		try{
			BusinessExcel.export(res, list , GrossProfitStatisticsYear.class);
		}catch(Exception e){
			
		}
		
	}		
	
	
	
	

	@RequestMapping(params = "method=quarterList")
	public String quarterList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.GROSSPROFIT3VIEW.getId());
		computeTaxRate(searchStatistics);
		Pager<Statistics> pager = getGrossProfitStatisticsService().queryByQuarter(searchStatistics, userPermit, PubMethod.getPager(request, Statistics.class));
		if(pager.getResultList() != null){
			for(Statistics statistics : pager.getResultList()){
				statistics.setQuarter_name(PubMethod.changeQuarterView(String.valueOf(statistics.getQuarter())));
			}
		}		
		
		request.setAttribute("action", aciont);
		request.setAttribute("urls", urls);
		
		Pager<Statistics> all = getGrossProfitStatisticsService().queryByAll(searchStatistics, userPermit, PubMethod.getPagerByAll(Statistics.class));
		if(all.getResultList().size() > 0){
			Statistics first = all.getResultList().get(0);
			double total_amount = 0;
			if(first != null ) total_amount = first.getStatistics_amount();
			pager.setTotal_amount(total_amount);
		}
		PubMethod.setRequestPager(request, pager,Statistics.class);	
		return "statistics/gross_profit_quarter_list";
	}
	

	/**
	 * 导出Excel
	 * @param searchStatistics
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=exportQuarterList")
	public void exportQuarterList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.GROSSPROFIT3VIEW.getId());		
		computeTaxRate(searchStatistics);
		Pager<Statistics> pager = getGrossProfitStatisticsService().queryByQuarter(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		List<GrossProfitStatisticsQuarter> list = new ArrayList<GrossProfitStatisticsQuarter>();
		if(pager.getResultList() != null){
			for(Statistics statistics : pager.getResultList()){
				GrossProfitStatisticsQuarter statisticsTarget = new GrossProfitStatisticsQuarter();
				statisticsTarget.setQuarter(PubMethod.changeQuarterView(String.valueOf(statistics.getQuarter())));
				statisticsTarget.setStatistics_amount(statistics.getStatistics_amount());
				statisticsTarget.setStatistics_rate(statistics.getStatistics_rate());
				list.add(statisticsTarget);
			}
		}
		try{
			BusinessExcel.export(res, list , GrossProfitStatisticsQuarter.class);
		}catch(Exception e){
			
		}
		
	}		
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@RequestMapping(params = "method=allList")
	public String allList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.GROSSPROFIT3VIEW.getId());
		computeTaxRate(searchStatistics);
		Pager<Statistics> pager = getGrossProfitStatisticsService().queryByAll(searchStatistics, userPermit, PubMethod.getPager(request, Statistics.class));
		if(pager.getResultList() != null){
			for(Statistics statistics : pager.getResultList()){
				statistics.setProject_name("所有项目");
			}
		}		
		PubMethod.setRequestPager(request, pager,Statistics.class);	
		request.setAttribute("action", aciont);
		request.setAttribute("urls", urls);
		return "statistics/gross_profit_all_list";
	}
	

	/**
	 * 导出Excel
	 * @param searchStatistics
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=exportAllList")
	public void exportAllList(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.GROSSPROFIT3VIEW.getId());	
		computeTaxRate(searchStatistics);
		Pager<Statistics> pager = getGrossProfitStatisticsService().queryByAll(searchStatistics, userPermit, PubMethod.getPagerByAll(request, Statistics.class));
		List<GrossProfitStatisticsAll> list = new ArrayList<GrossProfitStatisticsAll>();
		if(pager.getResultList() != null){
			for(Statistics statistics : pager.getResultList()){
				GrossProfitStatisticsAll statisticsTarget = new GrossProfitStatisticsAll();
				BeanUtils.copyProperties(statistics, statisticsTarget);
				statisticsTarget.setProject_name("所有项目");
				list.add(statisticsTarget);
			}
		}
		try{
			BusinessExcel.export(res, list , GrossProfitStatisticsAll.class);
		}catch(Exception e){
			
		}
		
	}

	public IGrossProfitStatisticsService getGrossProfitStatisticsService() {
		return grossProfitStatisticsService;
	}

	
}
