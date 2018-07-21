package com.pm.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.common.actions.BaseAction;
import com.common.el.ElUtil;
import com.pm.domain.business.StaffCost;
import com.pm.domain.business.StaffExEntity;
import com.pm.domain.business.StaffSalaryDetail;
import com.pm.domain.system.MarketSets;
import com.pm.service.IStaffChartsService;
import com.pm.service.IStaffCostService;
import com.pm.service.IStaffExEntityService;
import com.pm.service.IStaffSalaryDetailService;
import com.pm.util.charts.ChartsUtil;
import com.pm.util.constant.BusinessUtil;
import com.pm.util.constant.EnumSalaryModel;
import com.pm.vo.echarts.Option;
import com.pm.vo.echarts.base.Grid;
import com.pm.vo.echarts.base.Xaxis;
import com.pm.vo.echarts.base.Yaxis;
import com.pm.vo.echarts.series.Bar;
import com.pm.vo.echarts.series.Data;
import com.pm.vo.echarts.series.Pie;
import com.pm.vo.echarts.series.Series;
import com.pm.vo.echarts.title.Title;
import com.pm.vo.echarts.tooltip.Tooltip;


@Controller
@RequestMapping("StaffChartsAction.do")
public class StaffChartsAction extends BaseAction {


	//private static final String rel = "rel19";

	@Autowired
	private IStaffExEntityService staffExEntityService;
	
	@Autowired
	private IStaffChartsService staffChartsService;
	

	@Autowired
	private IStaffCostService staffCostService;
	

	@Autowired
	private IStaffSalaryDetailService staffSalaryDetailService;

	



	/**
	 * 饼状分析图表
	 * @param staffExEntity
	 * @param res
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=toPieComparison")
	public String toPieComparison(StaffExEntity staffExEntity,HttpServletResponse res,HttpServletRequest request){
		
		staffExEntity = staffExEntityService.getStaffExEntity(staffExEntity.getId());		

		
		
		request.setAttribute("staffExEntity1", staffExEntity);
		String salary_model = staffExEntity.getSalary_model() ;
		Option option = new Option();
		//option.setBackgroundColor("#a73e5c");
		//option.setColor(new String[]{"#ffd285","#ffd285","#ffd285","#ff733f","#ff733f","#ff733f"});
		
		Title[] titles = new Title[1];
		Title title = new Title();
		title.setText("对比分析图表");
		titles[0] = title;
		option.setTitle(titles);
		
		Tooltip tooltip = new Tooltip();
		option.setTooltip(new Tooltip[]{tooltip});
		
		if(salary_model != null && salary_model.equals(EnumSalaryModel.MarketingModel.getCode())) {
			Series[] series = new Series[6];
			
			Pie pie1 = new Pie();
			pie1.setName(EnumSalaryModel.MarketingModel.getVal()+BusinessUtil.orgName[0]);
			pie1.setRadius(new String[]{"18%","30%"});
			pie1.setCenter(new String[]{"17%","30%"});
			pie1.setData(new Data[]{});
			series[0] = pie1;
			

			Pie pie2 = new Pie();
			pie2.setName(EnumSalaryModel.MarketingModel.getVal()+BusinessUtil.orgName[1]);
			pie2.setRadius(new String[]{"18%","30%"});
			pie2.setCenter(new String[]{"47%","30%"});
			pie2.setData(new Data[]{});
			series[1] = pie2;
			

			Pie pie3 = new Pie();
			pie3.setName(EnumSalaryModel.MarketingModel.getVal()+BusinessUtil.orgName[2]);
			pie3.setRadius(new String[]{"18%","30%"});
			pie3.setCenter(new String[]{"82%","30%"});
			pie3.setData(new Data[]{});
			series[2] = pie3;
			
			
			

			Pie pie4 = new Pie();
			pie4.setName(EnumSalaryModel.OriginalMode.getVal()+BusinessUtil.orgName[0]);
			pie4.setRadius(new String[]{"18%","30%"});
			pie4.setCenter(new String[]{"17%","75%"});
			pie4.setData(new Data[]{});
			series[3] = pie4;
			

			Pie pie5 = new Pie();
			pie5.setName(EnumSalaryModel.OriginalMode.getVal()+BusinessUtil.orgName[1]);
			pie5.setRadius(new String[]{"18%","30%"});
			pie5.setCenter(new String[]{"47%","75%"});
			pie5.setData(new Data[]{});
			series[4] = pie5;
			

			Pie pie6 = new Pie();
			pie6.setName(EnumSalaryModel.OriginalMode.getVal()+BusinessUtil.orgName[2]);
			pie6.setRadius(new String[]{"18%","30%"});
			pie6.setCenter(new String[]{"82%","75%"});
			pie6.setData(new Data[]{});
			series[5] = pie6;
			
			option.setSeries(series);

			request.setAttribute("data_url", "StaffChartsAction.do?method=getPieComparisonJson&id="+
						(staffExEntity.getId()!=null?staffExEntity.getId():staffExEntity.getStaff_id()));
			
		}else {
			return this.ajaxForwardError(request, EnumSalaryModel.OriginalMode.getVal()+"不能对比！", true);
		}

		request.setAttribute("option", JSON.toJSONString(option));
		request.setAttribute("salary_model", salary_model);
		return "basicdata/staffex_piecomparison";
	}
	

	@RequestMapping(params = "method=getPieComparisonJson",method = RequestMethod.GET, produces =  "json;charset=UTF-8")
	@ResponseBody
	public JSONObject getPieComparisonJson(StaffExEntity staffExEntity,HttpServletResponse res,HttpServletRequest request){
		Option option1 = this.getMarketModel(staffExEntity);
		Option option2 = this.getOriginalModel(staffExEntity);
		List<Series> seriess = new ArrayList<Series>();
		for(Series series : option1.getSeries()){
			series.setName(EnumSalaryModel.MarketingModel.getVal()+series.getName()); 
			seriess.add(series);
		}		
		
		for(Series series : option2.getSeries()){
			series.setName(EnumSalaryModel.OriginalMode.getVal()+series.getName()); 
			seriess.add(series);
		}	
		
		option2.setSeries(seriess.toArray(new Series[seriess.size()]));
		return JSONObject.parseObject(JSON.toJSONString(option2));
	}
	
	
	

	/**
	 * 柱状分析图表
	 * @param staffExEntity
	 * @param res
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=toBarComparison")
	public String toBarComparison(StaffExEntity staffExEntity,HttpServletResponse res,HttpServletRequest request){
				
		try{

			staffExEntity = staffExEntityService.getStaffExEntity(staffExEntity.getId());	
			
			request.setAttribute("staffExEntity1", staffExEntity);
			String salary_model = staffExEntity.getSalary_model() ;
			

			if(salary_model != null && salary_model.equals(EnumSalaryModel.MarketingModel.getCode())) {
				
				Option option1 = this.getMarketModel(staffExEntity);
				Option option2 = this.getOriginalModel(staffExEntity);
				
				Option option = new Option();
				Title[] titles = new Title[1];
				Title title = new Title();
				title.setText("对比分析图表");
				titles[0] = title;
				option.setTitle(titles);
				
				Tooltip tooltip = new Tooltip();
				tooltip.setFormatter("<b><font color='green' size='18px'>{b}</font></b><br/><hr> {a} <br/>  {c}");
				option.setTooltip(new Tooltip[]{tooltip});
				
				Xaxis xaxis = new Xaxis();
				xaxis.setType("category");
				xaxis.setData(new String[]{EnumSalaryModel.MarketingModel.getVal(),EnumSalaryModel.OriginalMode.getVal()});
				option.setXaxis(new Xaxis[]{xaxis});
		
				Yaxis yaxis = new Yaxis();
				yaxis.setType("value");
				option.setYaxis(new Yaxis[]{yaxis});
				
				
				Grid grid = new Grid();
				option.setGrid(new Grid[]{grid});		
				
		
				List<Series> seriess = new ArrayList<Series>();
				for(int i=0;i<option1.getSeries().length;i++){
					Pie pie = (Pie)option1.getSeries()[i];
					Data[] datas = pie.getData();
					if(datas != null && datas.length >0){
						for(int index = 0; index < datas.length; index ++ ){
							Data data = datas[index];
							Bar bar = new Bar();
							bar.setName(BusinessUtil.orgName[i] + data.getName());
							bar.setStack(BusinessUtil.orgName[i]);
							Data[] ds = new Data[2];
							ds[0] = new Data(data.getValue());
							ds[1] = new Data(0);
							bar.setData(ds);
							seriess.add(bar);
						}
					}
					
					
		
					Pie pie1 = (Pie)option2.getSeries()[i];
					Data[] datas1 = pie1.getData();
					if(datas1 != null && datas1.length >0){
						for(int index = 0; index < datas1.length; index ++ ){
							Data data = datas1[index];
							Bar bar = new Bar();
							bar.setName(BusinessUtil.orgName[i]+data.getName());
							bar.setStack(BusinessUtil.orgName[i]);
							Data[] ds = new Data[2];
							ds[0] = new Data(0);
							ds[1] = new Data(data.getValue());
							bar.setData(ds);
							seriess.add(bar);
						}
					}		
				}				

				option.setSeries(seriess.toArray(new Series[seriess.size()]));
				request.setAttribute("option", JSON.toJSONString(option).replaceAll("xaxis", "xAxis").replaceAll("yaxis", "yAxis"));
				
			}else {
				return this.ajaxForwardError(request, EnumSalaryModel.OriginalMode.getVal()+"不能对比！", true);
			}
			
			return "basicdata/staffex_barcomparison";
			
		}catch(Exception e){
				return this.ajaxForwardError(request, "该人员已经离职！", true);
		}
		
	}
	
	
	
	/**
	 * 跳转都查看人员工资图表界面
	 * @param staffExEntity
	 * @param res
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=toStaffCharts")
	public String toStaffCharts(StaffExEntity staffExEntity,HttpServletResponse res,HttpServletRequest request){
		staffExEntity = staffExEntityService.getStaffExEntity(staffExEntity.getId());
		
		request.setAttribute("staffExEntity1", staffExEntity);
		String salary_model = staffExEntity.getSalary_model() ;
		Option option = new Option();
		Title title = new Title();
		title.setText(staffExEntity.getStaff_name()+"薪资分析图表");
		option.setTitle(new Title[]{title});
		
		Tooltip tooltip = new Tooltip();
		option.setTooltip(new Tooltip[]{tooltip});
		
		if(salary_model != null && salary_model.equals(EnumSalaryModel.MarketingModel.getCode())) {
			Series[] series = new Series[3];
			
			Pie pie1 = new Pie();
			pie1.setName("收入部分");
			pie1.setRadius(new String[]{"18%","38%"});
			pie1.setCenter(new String[]{"25%","30%"});
			pie1.setData(new Data[]{});
			series[0] = pie1;
			

			Pie pie2 = new Pie();
			pie2.setName("支出部分");
			pie2.setRadius(new String[]{"18%","38%"});
			pie2.setCenter(new String[]{"75%","30%"});
			pie2.setData(new Data[]{});
			series[1] = pie2;
			

			Pie pie3 = new Pie();
			pie3.setName("整体情况");
			pie3.setRadius(new String[]{"18%","38%"});
			pie3.setCenter(new String[]{"50%","75%"});
			pie3.setData(new Data[]{});
			series[2] = pie3;
			
			option.setSeries(series);

			request.setAttribute("data_url", "StaffChartsAction.do?method=getMarketModelJson&id="+
						(staffExEntity.getId()!=null?staffExEntity.getId():staffExEntity.getStaff_id()));
			
		}
		else {
			salary_model = EnumSalaryModel.OriginalMode.getCode();
			
			Series[] series = new Series[3];
			Pie pie1 = new Pie();
			pie1.setName("收入部分");
			pie1.setRadius(new String[]{"18%","38%"});
			pie1.setCenter(new String[]{"25%","30%"});
			pie1.setData(new Data[]{});
			series[0] = pie1;
			
			Pie pie2 = new Pie();
			pie2.setName("支出部分");
			pie2.setRadius(new String[]{"18%","38%"});
			pie2.setCenter(new String[]{"75%","30%"});
			pie2.setData(new Data[]{});
			series[1] = pie2;
			

			Pie pie3 = new Pie();
			pie3.setName("整体情况");
			pie3.setRadius(new String[]{"18%","38%"});
			pie3.setCenter(new String[]{"50%","75%"});
			pie3.setData(new Data[]{});
			series[2] = pie3;
			
			option.setSeries(series);
			

			request.setAttribute("data_url", "StaffChartsAction.do?method=getOriginalModelJson&id="+
						(staffExEntity.getId()!=null?staffExEntity.getId():staffExEntity.getStaff_id()));
		}

		request.setAttribute("option", JSON.toJSONString(option));
		request.setAttribute("salary_model", salary_model);
		return "basicdata/staffex_charts";
	}
	
	

	@RequestMapping(params = "method=getOriginalModelJson",method = RequestMethod.GET, produces =  "json;charset=UTF-8")
	@ResponseBody
	public JSONObject getOriginalModelJson(StaffExEntity staffExEntity,HttpServletResponse res,HttpServletRequest request){
		Option option = this.getOriginalModel(staffExEntity);
		return JSONObject.parseObject(JSON.toJSONString(option));
	}
	public Option getOriginalModel(StaffExEntity staffExEntity){
		
		double taxRate = this.computeTaxRate();
		StaffCost staffCost = staffCostService.getStaffCost(staffExEntity.getId());
		

		List<Series> pies = new ArrayList<Series>();
		
		pies.add(staffChartsService.getOriginalModel1(taxRate, null, null, staffExEntity, staffCost));
		pies.add(staffChartsService.getOriginalModel2(taxRate, null, null, staffExEntity, staffCost));
					
		double should_salary = pies.get(0).getSum() - pies.get(1).getSum() ;		
		pies.add(staffChartsService.getOriginalModel3(taxRate, null, null, staffCost, should_salary));
		
		Option option = new Option();
		option.setSeries(pies.toArray(new Series[3]));
		return option;
	}
	
	

	@RequestMapping(params = "method=getMarketModelJson",method = RequestMethod.GET, produces =  "json;charset=UTF-8")
	@ResponseBody
	public JSONObject getMarketModelJson(StaffExEntity staffExEntity,HttpServletResponse res,HttpServletRequest request){
		Option option = getMarketModel(staffExEntity);
		return JSONObject.parseObject(JSON.toJSONString(option));
	}

	public Option getMarketModel(StaffExEntity staffExEntity){
		
		Map<String, Object> ctx = new HashMap<String,Object>();
		double taxRate = this.computeTaxRate();
		
		MarketSets marketSets = getMarketSets();
		StaffCost staffCost = staffCostService.getStaffCost(staffExEntity.getId());
		staffExEntity = staffExEntityService.getStaffExEntity(staffExEntity.getId());
		
		ElUtil elUtil = new ElUtil(ctx);
		ctx.put(ElUtil.class.getSimpleName(), elUtil);
		
		
		Map<String,Object> marketMap = new HashMap<String,Object>();
		marketMap.put("staff_id", staffExEntity.getId());
		marketMap.put("coreLevel", marketSets.getAffect_level_number());
		marketMap.put("affect_ratio", marketSets.getAffect_ratio());
		marketMap.put("affect_decline_factor", marketSets.getAffect_decline_factor());
		
		StaffSalaryDetail staffSalaryDetail = new StaffSalaryDetail();
		staffSalaryDetail.setStaff_id(staffExEntity.getId());
		List<StaffSalaryDetail>staffSalaryDetails = staffSalaryDetailService.getStaffSalaryDetail(staffSalaryDetail);

		List<Series> pies = new ArrayList<Series>();
		pies.add(staffChartsService.getMarketModel1(taxRate,ctx,marketMap,staffExEntity,staffCost,staffSalaryDetails));
		pies.add(staffChartsService.getMarketModel2(taxRate,ctx,marketMap,staffExEntity,staffCost,staffSalaryDetails));
		
		
		double should_salary =  pies.get(0).getSum() - pies.get(1).getSum()   ;
		pies.add(staffChartsService.getMarketModel3(taxRate,ctx,staffExEntity,staffCost,should_salary));
		
		Option option = new Option();
		option.setSeries(pies.toArray(new Series[3]));
		return option;
	}
	
	


	
}