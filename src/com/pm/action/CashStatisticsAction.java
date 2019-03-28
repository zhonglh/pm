
package com.pm.action;

import com.pm.domain.business.Statistics;
import com.pm.domain.system.Dept;
import com.pm.util.constant.EnumPermit;
import com.pm.vo.DepartStatisticsItem;
import com.pm.vo.UserPermit;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * 现金盈余统计
 * @author zhonglihong
 * @date 2016年6月2日 下午8:39:28
 */
@Controller
@RequestMapping(value = "CashStatisticsAction.do")
public class CashStatisticsAction extends DepartStatisticsAction {


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



	/**
	 * 项目总回款
	 *
	 * @param searchStatistics
	 * @param depts
	 * @param userPermit
	 * @param searchStr
	 * @return
	 */
	@Override
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
	@Override
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
	@Override
	protected List<DepartStatisticsItem> getProjectExpends(Statistics searchStatistics, List<Dept> depts, UserPermit userPermit, String searchStr) {
		return super.getProjectExpends42(searchStatistics, depts, userPermit, searchStr);
	}


}

