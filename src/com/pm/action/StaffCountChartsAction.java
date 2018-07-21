package com.pm.action;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.common.actions.BaseAction;
import com.common.beans.Pager;
import com.pm.domain.business.StaffExEntity;
import com.pm.service.IStaffChartsService;
import com.pm.util.PubMethod;
import com.pm.util.constant.BusinessUtil;
import com.pm.util.constant.EnumPermit;
import com.pm.vo.UserPermit;
import com.pm.vo.echarts.Option;
import com.pm.vo.echarts.base.Grid;
import com.pm.vo.echarts.base.InRange;
import com.pm.vo.echarts.base.OutRange;
import com.pm.vo.echarts.base.SplitLine;
import com.pm.vo.echarts.base.TextStyle;
import com.pm.vo.echarts.base.VisualMap;
import com.pm.vo.echarts.base.VisualMapByContinuous;
import com.pm.vo.echarts.base.VisualMapByPiecewise;
import com.pm.vo.echarts.base.Xaxis;
import com.pm.vo.echarts.base.Yaxis;
import com.pm.vo.echarts.series.Scatter;
import com.pm.vo.echarts.title.Title;
import com.pm.vo.echarts.tooltip.Tooltip;
import com.pm.vo.reports.StaffCountVO;

/**
 * 人员统计图表
 * @author zhonglihong
 * @date 2016年12月14日 下午4:55:32
 */
@Controller
@RequestMapping("StaffCountChartsAction.do")
public class StaffCountChartsAction extends BaseAction {


	//private static final String rel = "rel19";

	
	@Autowired
	private IStaffChartsService staffChartsService;
	


	@RequestMapping(params = "method=list")
	public String list(StaffCountVO staffCountVO ,HttpServletResponse res,HttpServletRequest request){

		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);
		Pager<StaffCountVO> pager = staffChartsService.queryStaffExEntity(staffCountVO, userPermit, PubMethod.getPager(request, StaffCountVO.class));
		PubMethod.setRequestPager(request, pager,StaffCountVO.class);	

		return "basicdata/staffex_count_incomepay_list";	
		
	}
	


	/**
	 * 人员收支统计图表
	 * @param staffExEntity
	 * @param res
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=toStaffSalaryCatalogChart")
	public String toStaffSalaryCatalogChart(HttpServletResponse res,HttpServletRequest request){
		
	
		List<StaffCountVO> list = staffChartsService.getStaffSalaryCatalog();
		
		Option option = new Option();
		option.setBackgroundColor("#cdd0d5");
		option.setColor(new String[]{"#dd4444", "#fec42c", "#80F1BE"});
		
		Title[] titles = new Title[1];
		Title title = new Title();
		title.setText("人员收支情况");
		titles[0] = title;
		option.setTitle(titles);
		
		Grid grid = new Grid();
		grid.setTop("10%");
		grid.setRight("10%");
		grid.setLeft("10%");
		grid.setBottom("12%");
		option.setGrid(new Grid[]{grid});	
		
		Tooltip tooltip = new Tooltip();
		tooltip.setTrigger(null);
		tooltip.setPadding(10);
		tooltip.setBackgroundColor("#222");
		tooltip.setBorderColor("#777");
		tooltip.setBorderWidth(1);
		option.setTooltip(new Tooltip[]{tooltip});
		
		Xaxis xaxis = new Xaxis();
		xaxis.setSplitLine(new SplitLine());		
		option.setXaxis(new Xaxis[]{xaxis});		

		Yaxis yaxis = new Yaxis();
		yaxis.setSplitLine(new SplitLine());		
		option.setYaxis(new Yaxis[]{yaxis});
		

		Scatter scatter = new Scatter();
		scatter.setName("all");
		scatter.setData(getStaffSalaryCatalogChartData(list));
		option.setSeries(new Scatter[]{scatter});
		
		double min = 100000;
		double max = 0;
		for(String[] dt : scatter.getData()){
			String m = dt[5];
			double mm = Double.parseDouble(m);
			if(mm < min ) min = mm;
			if(mm > max ) max = mm;
		}
		
		int iMin = new Double(min).intValue();
		int iMax = new Double(max).intValue();
		int range = ((iMax -  iMin)/5/1000 +1) * 1000 ;
		if(range == 0) range = 100;

		if(iMin%range !=0){
			iMin = iMin/range * range ;
		}
		
		if(iMax%range !=0){
			iMax = iMax/range * range + range;
		}
				
		VisualMap visualMap = new VisualMapByPiecewise();
		visualMap.setLeft("center");
		visualMap.setBottom("3%");
		visualMap.setItemHeight(12);
		visualMap.setItemWidth(20);
		visualMap.setMin(iMin);
		visualMap.setMax(iMax);
		visualMap.setTextGap(2);
		visualMap.setColor(new String[]{"#d94e5d","#eac736","#50a3ba"});
		TextStyle textStyle = new TextStyle();
		textStyle.setColor("#fff");
		visualMap.setTextStyle(textStyle);
		visualMap.setDimension(5);
		visualMap.setPrecision(0);
		
		option.setVisualMap(new VisualMap[]{visualMap});
		
		
		String optionStr = JSON.toJSONString(option).replaceAll("xaxis", "xAxis").replaceAll("yaxis", "yAxis");
		request.setAttribute("option", optionStr);
		request.setAttribute("iMin", iMin);
		request.setAttribute("iMax", iMax);
		return "basicdata/staffex_count_incomepay_charts";
	}

	private String[][] getStaffSalaryCatalogChartData(List<StaffCountVO> list){
		
		if(list != null && list.size() >0){
			String[][] sss = new String[list.size()][7];
			
			int index = 0;
			for(StaffCountVO staffCountVO : list){
				double x = Math.random() * 10 ;
				double y = Math.random() * 10 ;
				String staff_name = staffCountVO.getStaff_name();
				double income_ = staffCountVO.getIncome_();
				double pay_ = staffCountVO.getPay_();
				double tax = staffCountVO.getTax();
				double actual_salary = staffCountVO.getActual_salary();			
				String[] ary = new String[]{String.valueOf(x), String.valueOf(y),String.valueOf(income_),
						String.valueOf(pay_),String.valueOf(tax),String.valueOf(actual_salary), staff_name};
				sss[index] = ary;
				index ++ ;
			}
			return sss;
		}else {
			return new String[][]{};
		}
		
	}
	



	/**
	 * 人员下级统计图表
	 * @param staffExEntity
	 * @param res
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=toStaffChildChart")
	public String toStaffChildChart(HttpServletResponse res,HttpServletRequest request){
		
	
		List<StaffCountVO> list = staffChartsService.getStaffChilds();
		
		Option option = new Option();
		option.setBackgroundColor("#cdd0d5");
		option.setColor(new String[]{"#dd4444", "#fec42c", "#80F1BE"});
		
		Title[] titles = new Title[1];
		Title title = new Title();
		title.setText("下线数量");
		titles[0] = title;
		option.setTitle(titles);
		
		Grid grid = new Grid();
		grid.setTop("10%");
		grid.setRight("10%");
		option.setGrid(new Grid[]{grid});
		
		Tooltip tooltip = new Tooltip();
		tooltip.setTrigger(null);
		tooltip.setPadding(10);
		tooltip.setBackgroundColor("#222");
		tooltip.setBorderColor("#777");
		tooltip.setBorderWidth(1);
		option.setTooltip(new Tooltip[]{tooltip});
		
		Xaxis xaxis = new Xaxis();
		xaxis.setSplitLine(new SplitLine());		
		option.setXaxis(new Xaxis[]{xaxis});		

		Yaxis yaxis = new Yaxis();
		yaxis.setSplitLine(new SplitLine());		
		option.setYaxis(new Yaxis[]{yaxis});
		

		Scatter scatter = new Scatter();
		scatter.setName("all");
		scatter.setData(getStaffChildChartData(list));
		option.setSeries(new Scatter[]{scatter});
		
		int max = 0;
		for(String[] dt : scatter.getData()){
			String m = dt[2];
			int mm = Integer.parseInt(m);
			if(mm > max ) max = mm;
		}
		
		VisualMap visualMap = new VisualMapByContinuous();
		visualMap.setLeft("right");
		visualMap.setBottom("5%");
		visualMap.setMin(0);
		visualMap.setMax(max);
		visualMap.setItemHeight(120);
		visualMap.setItemWidth(30);
		InRange inRange = new InRange();		
		inRange.setColor(null);
		inRange.setColorAlpha(0.9);
		visualMap.setInRange(inRange);
		OutRange outRange = new OutRange();		
		visualMap.setOutRange(outRange);
		com.pm.vo.echarts.base.Controller controller  = new com.pm.vo.echarts.base.Controller();
		InRange inRange1 = new InRange();		
		inRange1.setColor(null);
		inRange1.setSymbolSize(null);
		inRange1.setColor(new String[]{"#c23531"});
		OutRange outRange1 = new OutRange();		
		outRange1.setColor(null);
		outRange1.setSymbolSize(null);
		outRange1.setColor(new String[]{"#444"});
		controller.setInRange(inRange1);
		controller.setOutRange(outRange1);
		visualMap.setController(controller);
		
		visualMap.setText(new String[]{"下线个数"});
		option.setVisualMap(new VisualMap[]{visualMap});
		
		
		String optionStr = JSON.toJSONString(option).replaceAll("xaxis", "xAxis").replaceAll("yaxis", "yAxis");
		request.setAttribute("option", optionStr);
		return "basicdata/staffex_count_childs_charts";
	}
	
	private String[][] getStaffChildChartData(List<StaffCountVO> list){
		
		if(list != null && list.size() >0){
			String[][] sss = new String[list.size()][5];
			
			int index = 0;
			for(StaffCountVO staffCountVO : list){
				double x = Math.random() * 10 ;
				double y = Math.random() * 10 ;
				String staff_name = staffCountVO.getStaff_name();
				int allChilds = staffCountVO.getAllChilds().intValue();
				int coreChilds =staffCountVO.getCoreChilds().intValue();
				String[] ary = new String[]{String.valueOf(x), String.valueOf(y),String.valueOf(allChilds),String.valueOf(coreChilds), staff_name};
				sss[index] = ary;
				index ++ ;
			}
			return sss;
		}else {
			return new String[][]{};
		}
		
	}
	
	/**
	private int getLength(int size){
		if(size == 0) return 0;
		
		double seq = Math.sqrt(size);
		String str = String.valueOf(seq);
		int length = 0;
		String[] ss = str.split("\\.");
		if(ss.length == 1){
			length = Integer.parseInt(ss[0]);
		}else {
			length = Integer.parseInt(ss[0]);
			if(Double.parseDouble(ss[1]) > 0){
				length = length + 1;
			}
		}
		return length;
	}
	*/
	


	
}