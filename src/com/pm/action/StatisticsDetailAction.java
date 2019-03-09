package com.pm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.actions.BaseAction;
import com.common.beans.Pager;
import com.pm.domain.business.Statistics;
import com.pm.domain.business.StatisticsDetail;
import com.pm.service.IRoleService;
import com.pm.service.IStatisticsDetailService;
import com.pm.util.PubMethod;
import com.pm.util.constant.EnumPermit;
import com.pm.util.excel.exports.BusinessExcel;
import com.pm.vo.UserPermit;

/**
 * 统计明细
 * @author zhonglh
 *
 */
@Controller
@RequestMapping(value = "StatisticsDetailAction.do")
public class StatisticsDetailAction extends BaseAction {


	@Autowired
	private IRoleService roleService;

	@Autowired
	private IStatisticsDetailService statisticsDetailService;

	@RequestMapping(params = "method=queryGrossProfit1Detail")
	public String queryGrossProfit1Detail(Statistics statistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.GROSSPROFITVIEW.getId());		
		Pager<StatisticsDetail> pager = statisticsDetailService.queryGrossProfit1Detail(statistics, userPermit, PubMethod.getPager(request, StatisticsDetail.class));
		PubMethod.setRequestPager(request, pager,StatisticsDetail.class);	
		request.setAttribute("excelMethod", "excelGrossProfit1Detail");
		request.setAttribute("themethod", "queryGrossProfit1Detail");
		return "statistics/statistics_detail_list";
	}
	

	@RequestMapping(params = "method=excelGrossProfit1Detail")
	public void excelGrossProfit1Detail(Statistics statistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.GROSSPROFITVIEW.getId());		
		Pager<StatisticsDetail> pager = statisticsDetailService.queryGrossProfit1Detail(statistics, userPermit, PubMethod.getPagerByAll(request, StatisticsDetail.class));
		PubMethod.setRequestPager(request, pager,StatisticsDetail.class);	

		try{
			BusinessExcel.export(res, null, pager.getResultList(), StatisticsDetail.class);
		}catch(Exception e){
			
		}
	}
	

	@RequestMapping(params = "method=queryGrossProfit2Detail")
	public String queryGrossProfit2Detail(Statistics statistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.GROSSPROFIT2VIEW.getId());
		Pager<StatisticsDetail> pager = statisticsDetailService.queryGrossProfit2Detail(statistics, userPermit, PubMethod.getPager(request, StatisticsDetail.class));
		PubMethod.setRequestPager(request, pager,StatisticsDetail.class);	
		request.setAttribute("excelMethod", "excelGrossProfit2Detail");
		request.setAttribute("themethod", "queryGrossProfit2Detail");
		return "statistics/statistics_detail_list";
	}
	

	@RequestMapping(params = "method=excelGrossProfit2Detail")
	public void excelGrossProfit2Detail(Statistics statistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.GROSSPROFIT2VIEW.getId());
		Pager<StatisticsDetail> pager = statisticsDetailService.queryGrossProfit2Detail(statistics, userPermit, PubMethod.getPagerByAll(request, StatisticsDetail.class));
		PubMethod.setRequestPager(request, pager,StatisticsDetail.class);	

		try{
			BusinessExcel.export(res, null, pager.getResultList(), StatisticsDetail.class);
		}catch(Exception e){
			
		}
	}






	@RequestMapping(params = "method=queryGrossProfit3Detail")
	public String queryGrossProfit3Detail(Statistics statistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.GROSSPROFIT3VIEW.getId());
		Pager<StatisticsDetail> pager = statisticsDetailService.queryGrossProfit3Detail(statistics, userPermit, PubMethod.getPager(request, StatisticsDetail.class));
		PubMethod.setRequestPager(request, pager,StatisticsDetail.class);
		request.setAttribute("excelMethod", "excelGrossProfit3Detail");
		request.setAttribute("themethod", "queryGrossProfit3Detail");
		return "statistics/statistics_detail_list";
	}


	@RequestMapping(params = "method=excelGrossProfit3Detail")
	public void excelGrossProfit3Detail(Statistics statistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.GROSSPROFIT3VIEW.getId());
		Pager<StatisticsDetail> pager = statisticsDetailService.queryGrossProfit3Detail(statistics, userPermit, PubMethod.getPagerByAll(request, StatisticsDetail.class));
		PubMethod.setRequestPager(request, pager,StatisticsDetail.class);

		try{
			BusinessExcel.export(res, null, pager.getResultList(), StatisticsDetail.class);
		}catch(Exception e){

		}
	}






	@RequestMapping(params = "method=querySalesDetail")
	public String querySalesDetail(Statistics statistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.GROSSPROFITVIEW.getId());		
		Pager<StatisticsDetail> pager = statisticsDetailService.querySalesDetail(statistics, userPermit, PubMethod.getPager(request, StatisticsDetail.class));
		PubMethod.setRequestPager(request, pager,StatisticsDetail.class);	
		request.setAttribute("excelMethod", "excelSalesDetail");
		request.setAttribute("themethod", "querySalesDetail");
		return "statistics/statistics_detail_list";
	}
	

	@RequestMapping(params = "method=excelSalesDetail")
	public void excelSalesDetail(Statistics statistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.GROSSPROFITVIEW.getId());		
		Pager<StatisticsDetail> pager = statisticsDetailService.querySalesDetail(statistics, userPermit, PubMethod.getPagerByAll(request, StatisticsDetail.class));
		PubMethod.setRequestPager(request, pager,StatisticsDetail.class);	

		try{
			BusinessExcel.export(res, null, pager.getResultList(), StatisticsDetail.class);
		}catch(Exception e){
			
		}
	}
	
	
	
	


	@RequestMapping(params = "method=queryReceivablesDetail")
	public String queryReceivablesDetail(Statistics statistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.GROSSPROFITVIEW.getId());		
		Pager<StatisticsDetail> pager = statisticsDetailService.queryReceivablesDetail(statistics, userPermit, PubMethod.getPager(request, StatisticsDetail.class));
		PubMethod.setRequestPager(request, pager,StatisticsDetail.class);	
		request.setAttribute("excelMethod", "excelReceivablesDetail");
		request.setAttribute("themethod", "queryReceivablesDetail");
		return "statistics/statistics_detail_list";
	}
	

	@RequestMapping(params = "method=excelReceivablesDetail")
	public void excelReceivablesDetail(Statistics statistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.GROSSPROFITVIEW.getId());		
		Pager<StatisticsDetail> pager = statisticsDetailService.queryReceivablesDetail(statistics, userPermit, PubMethod.getPagerByAll(request, StatisticsDetail.class));
		PubMethod.setRequestPager(request, pager,StatisticsDetail.class);	

		try{
			BusinessExcel.export(res, null, pager.getResultList(), StatisticsDetail.class);
		}catch(Exception e){
			
		}
	}	
	
	

}
