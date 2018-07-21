
package com.pm.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bouncycastle.crypto.RuntimeCryptoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.actions.BaseAction;
import com.common.beans.Pager;
import com.pm.domain.business.DicData;
import com.pm.domain.business.Statistics;
import com.pm.domain.business.StatisticsDetail;
import com.pm.domain.system.Dept;
import com.pm.service.IDepartStatisticsService;
import com.pm.service.IDeptService;
import com.pm.service.IDicDataService;
import com.pm.service.IRoleService;
import com.pm.util.PubMethod;
import com.pm.util.constant.EnumDicType;
import com.pm.util.constant.EnumPermit;
import com.pm.util.excel.BusinessExcel;
import com.pm.util.excel.DepartStatisticsExcel;
import com.pm.vo.DepartStatisticsItem;
import com.pm.vo.UserPermit;

/**
 * @author zhonglihong
 * @date 2016年6月2日 下午8:39:28
 */
@Controller
@RequestMapping(value = "DepartStatisticsAction.do")
public class DepartStatisticsAction extends BaseAction {
	
	
	@Autowired
	private IDepartStatisticsService departStatisticsService;

	@Autowired
	IDeptService deptService;

	@Autowired
	private IDicDataService dicDataService;	
	
	@Autowired
	private IRoleService roleService;
	
	private static List<String> items = new ArrayList<String>();
	static{
		items.add("项目含税收入");
		items.add("项目流转税额");
		items.add("项目不含税收入");
		items.add("项目人工成本");
		items.add("剔除人工成本后项目毛利润额");
		items.add("项目人工成本占项目收入率");
		items.add("项目报销占项目收入比率");
		items.add("销售费用占项目收入比率");
		items.add("部门管理费用占项目收入比率");
		items.add("项目报销成本");	
		items.add("项目付款");				
		items.add("销售费用");			
		items.add("部门管理费用");		
		items.add("部门利润");
		items.add("部门投入产出比");
		items.add("部门利润目标");
		items.add("目标完成情况");
	}
	
	
	/**
	 * 明细界面
	 * @param statistics
	 * @param res
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=queryCostsDetail")
	public String queryCostsDetail(Statistics statistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.DEPARTSTATISTICS.getId());		
		Pager<StatisticsDetail> pager = departStatisticsService.queryCostsDetail(statistics, userPermit, PubMethod.getPager(request, StatisticsDetail.class));
		PubMethod.setRequestPager(request, pager,StatisticsDetail.class);	
		return "departstatistics/costs_detail_list";
	}
	
	/**
	 * 明细导出
	 * @param statistics
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=excelCostsDetail")
	public void excelCostsDetail(Statistics statistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.DEPARTSTATISTICS.getId());		
		Pager<StatisticsDetail> pager = departStatisticsService.queryCostsDetail(statistics, userPermit, PubMethod.getPagerByAll(StatisticsDetail.class));
		
		try{
			BusinessExcel.export(res, null, pager.getResultList(), StatisticsDetail.class);
		}catch(Exception e){
			
		}
	}
	
	
	/** 
	 * 部门费用明细
	 * @param statistics
	 * @param res
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=queryDepartCostsDetail")
	public String queryDepartCostsDetail(Statistics statistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.DEPARTSTATISTICS.getId());		
		Pager<StatisticsDetail> pager = departStatisticsService.queryDepartDetail(statistics, userPermit, PubMethod.getPager(request, StatisticsDetail.class));
		PubMethod.setRequestPager(request, pager,StatisticsDetail.class);	
		return "departstatistics/departcosts_detail_list";
	}
	
	/**
	 * 部门费用明细导出excel
	 * @param statistics
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=excelDepartCostsDetail")
	public void excelDepartCostsDetail(Statistics statistics,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.DEPARTSTATISTICS.getId());		
		Pager<StatisticsDetail> pager = departStatisticsService.queryDepartDetail(statistics, userPermit, PubMethod.getPagerByAll(StatisticsDetail.class));
		
		try{
			BusinessExcel.export(res, null, pager.getResultList(), StatisticsDetail.class);
		}catch(Exception e){
			
		}
	}
	

	@RequestMapping(params = "method=list")
	public String list(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.DEPARTSTATISTICS.getId());
		

		if(searchStatistics.getDeptId() == null || searchStatistics.getDeptId().isEmpty())
			searchStatistics.setDeptId(request.getParameter("dept.dept_id"));		
		
		if(searchStatistics.getDeptName() == null || searchStatistics.getDeptName().isEmpty())
			searchStatistics.setDeptName(request.getParameter("dept.dept_name"));	
		
		Dept dept = new Dept();
		dept.setCurr_years(0);
		dept.setStatisticsed("1");
		if(searchStatistics.getMonth1() != 0) dept.setYear1(searchStatistics.getMonth1()/100);
		if(searchStatistics.getMonth2() != 0) dept.setYear2(searchStatistics.getMonth2()/100);
		
		if(searchStatistics.getDeptId() != null && searchStatistics.getDeptId().length() >0){
			dept.setDept_id(searchStatistics.getDeptId());
		}
		List<Dept> depts = null;
		
		if(searchStatistics.getDeptId() != null && searchStatistics.getDeptId().length() >0){
			dept.setDept_id(searchStatistics.getDeptId());
		}
		
		Pager<Dept> departs = deptService.queryDept(dept, userPermit, PubMethod.getPagerByAll(Dept.class));
		depts = departs.getResultList();
		if(depts == null) depts = new ArrayList<Dept>();
		
		request.setAttribute("statistics1", searchStatistics);
		
		request.setAttribute("datass", getDepartStatisticsList(searchStatistics,depts,userPermit));
		request.setAttribute("depts", depts);
		request.setAttribute("deptsize", depts.size());
		return "departstatistics/depart_statistics_list";
	}
	


	@RequestMapping(params = "method=export")
	public void export(Statistics searchStatistics,HttpServletResponse res,HttpServletRequest request){
		
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.DEPARTSTATISTICS.getId());


		if(searchStatistics.getDeptId() == null || searchStatistics.getDeptId().isEmpty())
			searchStatistics.setDeptId(request.getParameter("dept.dept_id"));		
		
		if(searchStatistics.getDeptName() == null || searchStatistics.getDeptName().isEmpty())
			searchStatistics.setDeptName(request.getParameter("dept.dept_name"));	
		
		Dept dept = new Dept();
		dept.setCurr_years(0);
		dept.setStatisticsed("1");
		if(searchStatistics.getMonth1() != 0) dept.setYear1(searchStatistics.getMonth1()/100);
		if(searchStatistics.getMonth2() != 0) dept.setYear2(searchStatistics.getMonth2()/100);
		
		if(searchStatistics.getDeptId() != null && searchStatistics.getDeptId().length() >0){
			dept.setDept_id(searchStatistics.getDeptId());
		}
		List<Dept> depts = null;
		

		if(searchStatistics.getDeptId() != null && searchStatistics.getDeptId().length() >0){
			dept.setDept_id(searchStatistics.getDeptId());
		}
		
		Pager<Dept> departs = deptService.queryDept(dept, userPermit, PubMethod.getPagerByAll(Dept.class));
		depts = departs.getResultList();
		if(depts == null) depts = new ArrayList<Dept>();
		
		if(depts == null || depts.isEmpty()) throw new RuntimeException("没有数据!");
		
		List<DepartStatisticsItem> list = new ArrayList<DepartStatisticsItem>();
		DepartStatisticsItem tempItem = new DepartStatisticsItem();
		list.add(tempItem);
		for(Dept tempDept : depts){
			DepartStatisticsItem item = new DepartStatisticsItem();
			item.setItemName(tempDept.getDept_name());
			item.setItemFormatter("B");
			list.add(item);
		}
		if(depts.size() > 1){
			DepartStatisticsItem item = new DepartStatisticsItem();
			item.setItemName("总计");
			item.setItemFormatter("B");
			list.add(item);
		}
		

		try{
			List<List<DepartStatisticsItem>> lists = getDepartStatisticsList(searchStatistics,depts,userPermit);
			lists.add(0,list);
			DepartStatisticsExcel.export(res, null,  lists);
		}catch(Exception e){
			
		}
	}
		
		
	private List<List<DepartStatisticsItem>> getDepartStatisticsList(Statistics searchStatistics,List<Dept> depts,UserPermit userPermit){
		
		List<List<DepartStatisticsItem>> list = new ArrayList<List<DepartStatisticsItem>>();

		if(depts == null || depts.isEmpty()) return list;

		computeTaxRate(searchStatistics);
		
		String searchStr = "";
		if(searchStatistics.getMonth1() != 0){
			searchStr += "&month1="+searchStatistics.getMonth1();
		}
		if(searchStatistics.getMonth2() != 0){
			searchStr += "&month2="+searchStatistics.getMonth2();
		}
		
		//处理项目含税收入
		List<DepartStatisticsItem> list1= new ArrayList<DepartStatisticsItem>();		
		List<DepartStatisticsItem> receivedPayments = 
				departStatisticsService.queryReceivedPayments(searchStatistics, userPermit, PubMethod.getPagerByAll(DepartStatisticsItem.class)).getResultList();
		Map<String, DepartStatisticsItem> map = PubMethod.list2Map(receivedPayments);
		handleStatistics(list, depts, list1, map,0,"/DepartStatisticsAction.do?method=queryCostsDetail&x=20"+searchStr,"");
		

		//处理项目项目流转税额
		List<DepartStatisticsItem> list2= new ArrayList<DepartStatisticsItem>();	
		for(DepartStatisticsItem temp : list1){
			DepartStatisticsItem departStatisticsItem = temp.copy("", items.get(1));
			departStatisticsItem.setVal(departStatisticsItem.getVal() * searchStatistics.getTax_rate());
			departStatisticsItem.setVal(PubMethod.getNumberFormatByDouble(departStatisticsItem.getVal()));
			list2.add(departStatisticsItem);
		}
		list.add(list2);
		
		//项目不含税收入
		List<DepartStatisticsItem> list3= new ArrayList<DepartStatisticsItem>();	
		for(DepartStatisticsItem temp : list1){
			DepartStatisticsItem departStatisticsItem = temp.copy("", items.get(2));
			double val = departStatisticsItem.getVal();
			val = val - (val * searchStatistics.getTax_rate());
			departStatisticsItem.setVal(val);
			departStatisticsItem.setVal(PubMethod.getNumberFormatByDouble(departStatisticsItem.getVal()));
			list3.add(departStatisticsItem);
		}
		list.add(list3);
		
		
		//处理项目 人员成本
		List<DepartStatisticsItem> list4= new ArrayList<DepartStatisticsItem>();		
		List<DepartStatisticsItem> projectStaffCosts = 
				departStatisticsService.queryProjectStaffCosts(searchStatistics, userPermit, PubMethod.getPagerByAll(DepartStatisticsItem.class)).getResultList();
		Map<String, DepartStatisticsItem> map4 = PubMethod.list2Map(projectStaffCosts);		
		handleStatistics(list, depts, list4, map4,3,"/DepartStatisticsAction.do?method=queryCostsDetail&x=51"+searchStr,"");
		
		
		

		//剔除人工成本后项目毛利润额
		List<DepartStatisticsItem> list5= new ArrayList<DepartStatisticsItem>();	
		int index = 0;
		for(DepartStatisticsItem temp : list3){
			DepartStatisticsItem departStatisticsItem = temp.copy("", items.get(4));
			departStatisticsItem.setItemFormatter("B");
			double val = departStatisticsItem.getVal();
			val = val -  list4.get(index).getVal();
			departStatisticsItem.setVal(val);
			list5.add(departStatisticsItem);
			
			index++;
		}
		list.add(list5);
		

		//项目人工成本占项目收入率
		List<DepartStatisticsItem> list6= new ArrayList<DepartStatisticsItem>();	
		index = 0;
		for(DepartStatisticsItem temp : list3){
			DepartStatisticsItem departStatisticsItem = temp.copy("", items.get(5));
			departStatisticsItem.setItemFormatter("B");
			double val = departStatisticsItem.getVal();
			if(val != 0){
				val = list4.get(index).getVal()/val;
			}
			departStatisticsItem.setFormatter("%");
			departStatisticsItem.setVal(val*100);
			list6.add(departStatisticsItem);			
			index++;
		}
		list.add(list6);
		

		//项目报销占项目收入比率
		List<DepartStatisticsItem> list7= new ArrayList<DepartStatisticsItem>();	
		index = 0;
		for(DepartStatisticsItem temp : list3){
			DepartStatisticsItem departStatisticsItem = temp.copy("", items.get(6));	
			departStatisticsItem.setItemFormatter("B");		
			departStatisticsItem.setFormatter("%");
			list7.add(departStatisticsItem);			
			index++;
		}
		list.add(list7);
		

		//销售费用占项目收入比率
		List<DepartStatisticsItem> list8= new ArrayList<DepartStatisticsItem>();	
		index = 0;
		for(DepartStatisticsItem temp : list3){
			DepartStatisticsItem departStatisticsItem = temp.copy("", items.get(7));	
			departStatisticsItem.setItemFormatter("B");		
			departStatisticsItem.setFormatter("%");
			list8.add(departStatisticsItem);			
			index++;
		}
		list.add(list8);

		//部门管理费用占项目收入比率
		List<DepartStatisticsItem> list9= new ArrayList<DepartStatisticsItem>();	
		index = 0;
		for(DepartStatisticsItem temp : list3){
			DepartStatisticsItem departStatisticsItem = temp.copy("", items.get(8));	
			departStatisticsItem.setItemFormatter("B");		
			departStatisticsItem.setFormatter("%");
			list9.add(departStatisticsItem);			
			index++;
		}
		list.add(list9);
		

		//项目报销成本
		List<DepartStatisticsItem> list10= new ArrayList<DepartStatisticsItem>();		
		List<DepartStatisticsItem> reimburseCosts = 
				departStatisticsService.queryReimburseCosts(searchStatistics, userPermit, PubMethod.getPagerByAll(DepartStatisticsItem.class)).getResultList();
		Map<String, DepartStatisticsItem> map10 = PubMethod.list2Map(reimburseCosts);
		handleStatistics(list, depts, list10, map10,9,"/DepartStatisticsAction.do?method=queryCostsDetail&x=30"+searchStr,"B");
		
		
		//报销明细
		DicData searchDicDeta = new DicData();
		searchDicDeta.setDic_type_id(EnumDicType.REIMBURSE_ITEM.name());
		List<DicData> reimburseCostTypes = dicDataService.getDicDataByType(searchDicDeta);
		List<DepartStatisticsItem> reimburseCostDetails = 
				departStatisticsService.queryReimburseCostDetails(searchStatistics, userPermit, PubMethod.getPagerByAll(DepartStatisticsItem.class)).getResultList();
		Map<String, Map<String, DepartStatisticsItem>> maprc = PubMethod.list2Map2(reimburseCostDetails);		
		handleDetails(list, depts, reimburseCostTypes, maprc,"/DepartStatisticsAction.do?method=queryCostsDetail&x=30"+searchStr);
		

		//项目付款
		List<DepartStatisticsItem> list11= new ArrayList<DepartStatisticsItem>();		
		List<DepartStatisticsItem> projectExpends = 
				departStatisticsService.queryProjectExpends(searchStatistics, userPermit, PubMethod.getPagerByAll(DepartStatisticsItem.class)).getResultList();
		Map<String, DepartStatisticsItem> map11 = PubMethod.list2Map(projectExpends);
		handleStatistics(list, depts, list11, map11,10,"/DepartStatisticsAction.do?method=queryCostsDetail&x=40"+searchStr,"B");

		//销售费用
		List<DepartStatisticsItem> list12= new ArrayList<DepartStatisticsItem>();		
		List<DepartStatisticsItem> salseCosts = 
				departStatisticsService.querySalseCosts(searchStatistics, userPermit, PubMethod.getPagerByAll(DepartStatisticsItem.class)).getResultList();
		Map<String, DepartStatisticsItem> map12 = PubMethod.list2Map(salseCosts);
		handleStatistics(list, depts, list12, map12,11,"/DepartStatisticsAction.do?method=queryDepartCostsDetail&x=100","B");
		
		
		//处理销售费用明细
		searchDicDeta.setDic_type_id(EnumDicType.SALES_COSTS.name());
		List<DicData> salesCostTypes = dicDataService.getDicDataByType(searchDicDeta);
		List<DepartStatisticsItem> salesCostDetails = 
				departStatisticsService.querySalseCostsDetail(searchStatistics, userPermit, PubMethod.getPagerByAll(DepartStatisticsItem.class)).getResultList();
		Map<String, Map<String, DepartStatisticsItem>> mapsc = PubMethod.list2Map2(salesCostDetails);		
		handleDetails(list, depts, salesCostTypes, mapsc,"/DepartStatisticsAction.do?method=queryDepartCostsDetail&x=100"+searchStr);		
		

		//部门费用
		List<DepartStatisticsItem> list13= new ArrayList<DepartStatisticsItem>();		
		List<DepartStatisticsItem> departCosts = 
				departStatisticsService.queryDepartCosts(searchStatistics, userPermit, PubMethod.getPagerByAll(DepartStatisticsItem.class)).getResultList();
		Map<String, DepartStatisticsItem> map13 = PubMethod.list2Map(departCosts);
		handleStatistics(list, depts, list13, map13,12,"/DepartStatisticsAction.do?method=queryDepartCostsDetail&x=101","B");
		
		//部门费用明细
		searchDicDeta.setDic_type_id(EnumDicType.DEPART_MANAG_COSTS.name());
		List<DicData> departCostTypes = dicDataService.getDicDataByType(searchDicDeta);
		List<DepartStatisticsItem> departCostDetails = 
				departStatisticsService.queryDepartCostsDetail(searchStatistics, userPermit, PubMethod.getPagerByAll(DepartStatisticsItem.class)).getResultList();
		Map<String, Map<String, DepartStatisticsItem>> mapdc = PubMethod.list2Map2(departCostDetails);		
		handleDetails(list, depts, departCostTypes, mapdc,"/DepartStatisticsAction.do?method=queryDepartCostsDetail&x=101"+searchStr);
		

		//部门利润
		List<DepartStatisticsItem> list14= new ArrayList<DepartStatisticsItem>();	
		index = 0;
		for(DepartStatisticsItem temp : list3){
			DepartStatisticsItem departStatisticsItem = temp.copy("", items.get(13));	
			departStatisticsItem.setItemFormatter("B");		
			double val = departStatisticsItem.getVal();
			val = val - list4.get(index).getVal() - list10.get(index).getVal() - 
					list11.get(index).getVal() - list12.get(index).getVal() - list13.get(index).getVal() ;
			departStatisticsItem.setVal(val);
			list14.add(departStatisticsItem);			
			index++;
		}
		list.add(list14);
		
		//部门投入产出比
		List<DepartStatisticsItem> list15= new ArrayList<DepartStatisticsItem>();	
		index = 0;
		for(DepartStatisticsItem temp : list4){
			DepartStatisticsItem departStatisticsItem = temp.copy("", items.get(14));	
			double val = departStatisticsItem.getVal();
			val = val + list10.get(index).getVal() +
					list11.get(index).getVal() + list12.get(index).getVal() + list13.get(index).getVal() ;
			if(val != 0) {
				val = list14.get(index).getVal()/val * 100;
			}
			departStatisticsItem.setVal(val);
			departStatisticsItem.setFormatter("%");
			list15.add(departStatisticsItem);			
			index++;
		}
		list.add(list15);
		

		//部门利润目标
		List<DepartStatisticsItem> list16= new ArrayList<DepartStatisticsItem>();
		DepartStatisticsItem departStatisticsItem1 = new DepartStatisticsItem();
		departStatisticsItem1.setItemFormatter("B");
		departStatisticsItem1.setItemName(items.get(15));	
		list16.add(departStatisticsItem1);	
		double sum16 = 0;
		for(Dept dept1 : depts){
			DepartStatisticsItem departStatisticsItem = new DepartStatisticsItem();
			departStatisticsItem.setDeptId(dept1.getDept_id());
			departStatisticsItem.setVal(dept1.getCurr_profit_objective());			
			departStatisticsItem.setItemFormatter("B");
			departStatisticsItem.setItemId("");
			departStatisticsItem.setItemName(items.get(15));	
			sum16 += departStatisticsItem.getVal();
			list16.add(departStatisticsItem);				
		}
		if(depts.size() > 1){
			DepartStatisticsItem departStatisticsItem2 = new DepartStatisticsItem();
			departStatisticsItem2.setItemFormatter("B");
			departStatisticsItem2.setItemName(items.get(15));	
			departStatisticsItem2.setVal(sum16);	
			list16.add(departStatisticsItem2);	
		}
		list.add(list16);

		//目标完成情况
		List<DepartStatisticsItem> list17= new ArrayList<DepartStatisticsItem>();
		index = 0;
		for(DepartStatisticsItem temp : list16){
			DepartStatisticsItem departStatisticsItem = temp.copy("", items.get(16));	
			double val = departStatisticsItem.getVal();
			if(val != 0){
				val = list14.get(index).getVal()/val * 100;
			}
			departStatisticsItem.setVal(val);
			departStatisticsItem.setFormatter("%");
			list17.add(departStatisticsItem);			
			index++;
		}
		list.add(list17);
		
		//项目报销占项目收入比率
		index = 0;
		for(DepartStatisticsItem departStatisticsItem : list7){
			if(departStatisticsItem.getVal() != 0){
				double val = list10.get(index).getVal()/departStatisticsItem.getVal()*100;
				departStatisticsItem.setVal(val);
			}
			index++;
		}	
		
		//销售费用占项目收入比率
		index = 0;
		for(DepartStatisticsItem departStatisticsItem : list8){
			if(departStatisticsItem.getVal() != 0){
				double val = list12.get(index).getVal()/departStatisticsItem.getVal()*100;
				departStatisticsItem.setVal(val);
			}
			index++;
		}
		
		//部门管理费用占项目收入比率
		index = 0;
		for(DepartStatisticsItem departStatisticsItem : list9){
			if(departStatisticsItem.getVal() != 0){
				double val = list13.get(index).getVal()/departStatisticsItem.getVal()*100;
				departStatisticsItem.setVal(val);
			}
			index++;
		}
		
		return list;
		
	}


	private void handleStatistics(
			List<List<DepartStatisticsItem>> list, 
			List<Dept> depts, 
			List<DepartStatisticsItem> list1,
			Map<String, DepartStatisticsItem> map,
			int index,
			String url,
			String itemFormatter) {
		//处理第一列
		DepartStatisticsItem temp1 = new DepartStatisticsItem();
		temp1.setItemName(items.get(index));
		temp1.setItemFormatter(itemFormatter);
		list1.add(temp1);
		
		double sum1 = 0;
		for(Dept dept1 : depts){
			DepartStatisticsItem departStatisticsItem = map.get(dept1.getDept_id());
			if(departStatisticsItem == null) {
				departStatisticsItem = new DepartStatisticsItem();
				departStatisticsItem.setDeptId(dept1.getDept_id());
			}else {
				if(url != null && url.length() >0) departStatisticsItem.setUrl(url+"&deptId="+dept1.getDept_id());
			}
			departStatisticsItem.setItemId("");
			departStatisticsItem.setItemName(items.get(index));
			sum1 += departStatisticsItem.getVal();

			departStatisticsItem.setItemFormatter(itemFormatter);
			list1.add(departStatisticsItem);
		}
		
		if(depts.size() > 1){
			DepartStatisticsItem last1 = new DepartStatisticsItem();
			last1.setDeptId("");
			last1.setItemId("");
			last1.setItemName(items.get(index));
			last1.setVal(sum1);
			last1.setUrl(url);
			last1.setItemFormatter(itemFormatter);
			list1.add(last1);
		}

		list.add(list1);
	}


	private void handleDetails(
			List<List<DepartStatisticsItem>> list, 
			List<Dept> depts, 
			List<DicData> salesCostTypes,
			Map<String, Map<String, DepartStatisticsItem>> mapsc,
			String url) {
		
		for(DicData dicData : salesCostTypes){
			
			Map<String, DepartStatisticsItem> tempMap = mapsc.get(dicData.getId());
			if(tempMap == null || tempMap.isEmpty()) continue;
			
			List<DepartStatisticsItem> tempList = new ArrayList<DepartStatisticsItem>();
			
			DepartStatisticsItem temp = new DepartStatisticsItem();
			temp.setItemName(dicData.getDic_data_name());
			temp.setItemId(dicData.getId());
			tempList.add(temp);
			
			double sum = 0;
			for(Dept dept1 : depts){
				DepartStatisticsItem departStatisticsItem = tempMap.get(dept1.getDept_id());
				if(departStatisticsItem == null) {
					departStatisticsItem = new DepartStatisticsItem();
					departStatisticsItem.setDeptId(dept1.getDept_id());
				}else {
					departStatisticsItem.setUrl(url+"&deptId="+dept1.getDept_id()+"&type_id="+dicData.getId());
				}
				departStatisticsItem.setItemName(dicData.getDic_data_name());
				departStatisticsItem.setItemId(dicData.getId());
				sum += departStatisticsItem.getVal();
				tempList.add(departStatisticsItem);
			}
			

			if(depts.size() > 1){
				DepartStatisticsItem last = new DepartStatisticsItem();
				last.setItemId(dicData.getId());
				last.setItemName(dicData.getDic_data_name());
				last.setVal(sum);
				last.setUrl("");
				tempList.add(last);
			}
			list.add(tempList);				
		}
	}

}

