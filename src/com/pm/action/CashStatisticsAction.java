
package com.pm.action;

import com.common.utils.DateKit;
import com.pm.domain.business.Statistics;
import com.pm.domain.system.Dept;
import com.pm.util.PubMethod;
import com.pm.util.constant.EnumPermit;
import com.pm.vo.DepartStatisticsItem;
import com.pm.vo.UserPermit;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 现金盈余统计
 * @author zhonglihong
 * @date 2016年6月2日 下午8:39:28
 */
@Controller
@RequestMapping(value = "CashStatisticsAction.do")
public class CashStatisticsAction extends DepartStatisticsBaseAction {


	protected static List<String> items = new ArrayList<String>();
	static{
		items.add("项目含税回款");
		items.add("项目流转税额");
		items.add("项目不含税回款");
		items.add("项目人工成本");
		items.add("剔除人工成本后项目毛利润额");
		items.add("项目人工成本占项目回款比率");
		items.add("项目报销占项目回款比率");
		items.add("销售费用占项目回款比率");
		items.add("部门管理费用占项目回款比率");
		items.add("项目报销成本");
		items.add("项目付款");
		items.add("销售费用");
		items.add("部门管理费用");
		items.add("总部人员成本");
		items.add("部门现金盈余");
		items.add("部门投入产出比");
	}


	@Override
	protected  List<String> getTitles() {
		return items;
	}


	private final String PERMIT_ID = EnumPermit.CASHSTATISTICS.getId();


	@Override
	protected String getPermitId() {
		return PERMIT_ID;
	}




	@Override
	public List<List<DepartStatisticsItem>> getDepartStatisticsList(Statistics searchStatistics, List<Dept> depts, UserPermit userPermit) {

		List<List<DepartStatisticsItem>> list = new ArrayList<List<DepartStatisticsItem>>();

		if (depts == null || depts.isEmpty()) {
			return list;
		}

		computeTaxRate(searchStatistics);

		String searchStr = "";
		if (searchStatistics.getMonth1() != 0) {
			Date date1 = DateKit.fmtShortYMTStrToDate(searchStatistics.getMonth1()+"01");
			searchStatistics.setMonth1(0);
			searchStatistics.setDate1(date1);
			searchStr += "&date1=" + DateKit.fmtDateToYMD(date1);
		}
		if (searchStatistics.getMonth2() != 0) {
			Date date2 = DateKit.fmtShortYMTStrToDate(searchStatistics.getMonth2()+"01");
			date2 = DateKit.addMonth(date2,1);
			date2 = DateKit.addDay(date2 , -1);searchStatistics.setMonth1(0);
			searchStatistics.setMonth2(0);
			searchStatistics.setDate2(date2);
			searchStr += "&date2=" + DateKit.fmtDateToYMD(date2);
		}



		//未核实收入
		List<DepartStatisticsItem> listA = getUnconfirmedIncome(searchStatistics, depts, userPermit, searchStr);
		list.add(listA);

		//处理项目含税总收入 ,  回款总计
		List<DepartStatisticsItem> list0 = getGeneralIncome(searchStatistics, depts, userPermit, searchStr);
		list.add(list0);

		//处理项目项目流转税额
		List<DepartStatisticsItem> list10 = new ArrayList<DepartStatisticsItem>();
		for (DepartStatisticsItem temp : list0) {
			DepartStatisticsItem departStatisticsItem = temp.copy("", getTitles().get(1));
			departStatisticsItem.setVal(departStatisticsItem.getVal() * searchStatistics.getTax_rate());
			departStatisticsItem.setVal(PubMethod.getNumberFormatByDouble(departStatisticsItem.getVal()));
			list10.add(departStatisticsItem);
		}
		list.add(list10);

		//项目不含税收入
		List<DepartStatisticsItem> list20 = new ArrayList<DepartStatisticsItem>();
		for (DepartStatisticsItem temp : list0) {
			DepartStatisticsItem departStatisticsItem = temp.copy("", getTitles().get(2));
			double val = departStatisticsItem.getVal();
			val = val * (1 - searchStatistics.getTax_rate());
			departStatisticsItem.setVal(val);
			departStatisticsItem.setVal(PubMethod.getNumberFormatByDouble(departStatisticsItem.getVal()));
			list20.add(departStatisticsItem);
		}
		list.add(list20);


		//处理项目人员成本, 所有成本  工资+税+社保
		List<DepartStatisticsItem> list30 = getProjectStaffCosts(searchStatistics, depts, userPermit, searchStr);
		list.add(list30);


		//剔除人工成本后项目毛利润额
		List<DepartStatisticsItem> list40 = new ArrayList<DepartStatisticsItem>();
		int index = 0;
		for (DepartStatisticsItem temp : list20) {
			DepartStatisticsItem departStatisticsItem = temp.copy("", getTitles().get(4));
			departStatisticsItem.setItemFormatter("B");
			double val = departStatisticsItem.getVal();
			val = val - list30.get(index).getVal();
			departStatisticsItem.setVal(val);
			list40.add(departStatisticsItem);

			index++;
		}
		list.add(list40);


		//项目人工成本占项目收入率
		List<DepartStatisticsItem> list50 = new ArrayList<DepartStatisticsItem>();
		index = 0;
		for (DepartStatisticsItem temp : list20) {
			DepartStatisticsItem departStatisticsItem = temp.copy("", getTitles().get(5));
			departStatisticsItem.setItemFormatter("B");
			double val = departStatisticsItem.getVal();
			if (val != 0) {
				val = list30.get(index).getVal() / val;
			}
			departStatisticsItem.setFormatter("%");
			departStatisticsItem.setVal(val * 100);
			list50.add(departStatisticsItem);
			index++;
		}
		list.add(list50);


		//项目报销占项目收入比率
		List<DepartStatisticsItem> list60 = new ArrayList<DepartStatisticsItem>();
		index = 0;
		for (DepartStatisticsItem temp : list20) {
			DepartStatisticsItem departStatisticsItem = temp.copy("", getTitles().get(6));
			departStatisticsItem.setItemFormatter("B");
			departStatisticsItem.setFormatter("%");
			list60.add(departStatisticsItem);
			index++;
		}
		list.add(list60);


		//销售费用占项目收入比率
		List<DepartStatisticsItem> list70 = new ArrayList<DepartStatisticsItem>();
		index = 0;
		for (DepartStatisticsItem temp : list20) {
			DepartStatisticsItem departStatisticsItem = temp.copy("", getTitles().get(7));
			departStatisticsItem.setItemFormatter("B");
			departStatisticsItem.setFormatter("%");
			list70.add(departStatisticsItem);
			index++;
		}
		list.add(list70);

		//部门管理费用占项目收入比率
		List<DepartStatisticsItem> list80 = new ArrayList<DepartStatisticsItem>();
		index = 0;
		for (DepartStatisticsItem temp : list20) {
			DepartStatisticsItem departStatisticsItem = temp.copy("", getTitles().get(8));
			departStatisticsItem.setItemFormatter("B");
			departStatisticsItem.setFormatter("%");
			list80.add(departStatisticsItem);
			index++;
		}
		list.add(list80);


		//项目报销成本 , 所有的报销
		List<DepartStatisticsItem> list90 = getReimburseCosts(searchStatistics, depts, userPermit, searchStr);
		list.add(list90);

		//报销明细
		List<List<DepartStatisticsItem>> reimburseCostDetails = getReimburseCostDetails(searchStatistics, depts, userPermit, searchStr);
		list.addAll(reimburseCostDetails);


		//项目付款信息 , 付款金额为实际付款，如果没有付款，付款金额为应付款
		List<DepartStatisticsItem> list100 = getProjectExpends(searchStatistics, depts, userPermit, searchStr);
		list.add(list100);


		//销售费用
		List<DepartStatisticsItem> list110 = getSalseCosts(searchStatistics, depts, userPermit, searchStr);
		list.add(list110);

		//处理部门销售费用明细
		List<List<DepartStatisticsItem>> salseCostDetails = this.getSalesCostDetails(searchStatistics, depts, userPermit, searchStr);
		List<DepartStatisticsItem> salesStaffDetails = this.getSalesStaffCosts(searchStatistics, depts, userPermit, searchStr);
		salseCostDetails.add(salesStaffDetails);
		list.addAll(salseCostDetails);

		//部门费用
		List<DepartStatisticsItem> list120 = getDepartCosts(searchStatistics, depts, userPermit, searchStr);
		list.add(list120);


		//部门费用明细
		List<List<DepartStatisticsItem>> departCostDetails = getDepartCostDetails(searchStatistics, depts, userPermit, searchStr);
		List<DepartStatisticsItem> manageStaffDetails = this.getManageStaffCosts(searchStatistics, depts, userPermit, searchStr);
		departCostDetails.add(manageStaffDetails);
		list.addAll(departCostDetails);

		//总部人员成本
		List<DepartStatisticsItem> list130 = getOtherStaffCosts(searchStatistics, depts, userPermit, searchStr);
		list.add(list130);


		//部门利润
		List<DepartStatisticsItem> list140 = new ArrayList<DepartStatisticsItem>();
		index = 0;
		for (DepartStatisticsItem temp : list20) {
			DepartStatisticsItem departStatisticsItem = temp.copy("", getTitles().get(14));
			departStatisticsItem.setItemFormatter("B");
			double val = departStatisticsItem.getVal();
			val = val - list30.get(index).getVal() - list90.get(index).getVal() -
					list100.get(index).getVal() - list110.get(index).getVal() - list120.get(index).getVal() - list130.get(index).getVal();
			departStatisticsItem.setVal(val);
			list140.add(departStatisticsItem);
			index++;
		}
		list.add(list140);

		//部门投入产出比
		List<DepartStatisticsItem> list150 = new ArrayList<DepartStatisticsItem>();
		index = 0;
		for (DepartStatisticsItem temp : list30) {
			DepartStatisticsItem departStatisticsItem = temp.copy("", getTitles().get(15));
			double val = departStatisticsItem.getVal();
			val = val + list90.get(index).getVal() +
					list100.get(index).getVal() + list110.get(index).getVal() + list120.get(index).getVal() + list130.get(index).getVal();
			if (val != 0) {
				val = list140.get(index).getVal() / val * 100;
			}
			departStatisticsItem.setVal(val);
			departStatisticsItem.setFormatter("%");
			list150.add(departStatisticsItem);
			index++;
		}
		list.add(list150);


		if(this.getPermitId().equals(EnumPermit.DEPARTSTATISTICS.getId())) {

			//部门利润目标
			List<DepartStatisticsItem> list160 = new ArrayList<DepartStatisticsItem>();
			DepartStatisticsItem departStatisticsItem1 = new DepartStatisticsItem();
			departStatisticsItem1.setItemFormatter("B");
			departStatisticsItem1.setItemName(getTitles().get(16));
			list160.add(departStatisticsItem1);
			double sum16 = 0;
			for (Dept dept1 : depts) {
				DepartStatisticsItem departStatisticsItem = new DepartStatisticsItem();
				departStatisticsItem.setDeptId(dept1.getDept_id());
				departStatisticsItem.setVal(dept1.getCurr_profit_objective());
				departStatisticsItem.setItemFormatter("B");
				departStatisticsItem.setItemId("");
				departStatisticsItem.setItemName(getTitles().get(16));
				sum16 += departStatisticsItem.getVal();
				list160.add(departStatisticsItem);
			}
			if (depts.size() > 1) {
				DepartStatisticsItem departStatisticsItem2 = new DepartStatisticsItem();
				departStatisticsItem2.setItemFormatter("B");
				departStatisticsItem2.setItemName(getTitles().get(16));
				departStatisticsItem2.setVal(sum16);
				list160.add(departStatisticsItem2);
			}
			list.add(list160);

			//目标完成情况
			List<DepartStatisticsItem> list170 = new ArrayList<DepartStatisticsItem>();
			index = 0;
			for (DepartStatisticsItem temp : list160) {
				DepartStatisticsItem departStatisticsItem = temp.copy("", getTitles().get(17));
				double val = departStatisticsItem.getVal();
				if (val != 0) {
					val = list140.get(index).getVal() / val * 100;
				}
				departStatisticsItem.setVal(val);
				departStatisticsItem.setFormatter("%");
				list170.add(departStatisticsItem);
				index++;
			}
			list.add(list170);

		}





		//项目报销占项目收入比率
		index = 0;
		for (DepartStatisticsItem departStatisticsItem : list60) {
			if (departStatisticsItem.getVal() != 0) {
				double val = list90.get(index).getVal() / departStatisticsItem.getVal() * 100;
				departStatisticsItem.setVal(val);
			}
			index++;
		}

		//销售费用占项目收入比率
		index = 0;
		for (DepartStatisticsItem departStatisticsItem : list70) {
			if (departStatisticsItem.getVal() != 0) {
				double val = list110.get(index).getVal() / departStatisticsItem.getVal() * 100;
				departStatisticsItem.setVal(val);
			}
			index++;
		}

		//部门管理费用占项目收入比率
		index = 0;
		for (DepartStatisticsItem departStatisticsItem : list80) {
			if (departStatisticsItem.getVal() != 0) {
				double val = list120.get(index).getVal() / departStatisticsItem.getVal() * 100;
				departStatisticsItem.setVal(val);
			}
			index++;
		}

		return list;

	}




	/**
	 * 项目总回款
	 *
	 * @param searchStatistics
	 * @param depts
	 * @param userPermit
	 * @param searchStr
	 * @return
	 */
	protected List<DepartStatisticsItem> getGeneralIncome(Statistics searchStatistics, List<Dept> depts, UserPermit userPermit, String searchStr) {
		return this.getReceivedPayments13(searchStatistics, depts, userPermit, searchStr);
	}


	/**
	 * 未确认回款
	 *
	 * @param searchStatistics
	 * @param depts
	 * @param userPermit
	 * @param searchStr
	 * @return
	 */
	protected List<DepartStatisticsItem> getUnconfirmedIncome(Statistics searchStatistics, List<Dept> depts, UserPermit userPermit, String searchStr) {
		return super.getReceivedPayments14(searchStatistics, depts, userPermit, searchStr);
	}


	/**
	 * 项目付款信息
	 *
	 * @param searchStatistics
	 * @param depts
	 * @param userPermit
	 * @param searchStr
	 * @return
	 */
	protected List<DepartStatisticsItem> getProjectExpends(Statistics searchStatistics, List<Dept> depts, UserPermit userPermit, String searchStr) {
		return super.getProjectExpends42(searchStatistics, depts, userPermit, searchStr);
	}


}

