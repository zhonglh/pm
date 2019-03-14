
package com.pm.action;

import com.pm.domain.business.Statistics;
import com.pm.domain.system.Dept;
import com.pm.util.constant.EnumPermit;
import com.pm.vo.DepartStatisticsItem;
import com.pm.vo.UserPermit;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 现金盈余统计
 * @author zhonglihong
 * @date 2016年6月2日 下午8:39:28
 */
@Controller
@RequestMapping(value = "CashStatisticsAction.do")
public class CashStatisticsAction extends DepartStatisticsAction {

	private final String PERMIT_ID = EnumPermit.CASHSTATISTICS.getId();


	@Override
	protected String getPermitId() {
		return PERMIT_ID;
	}



	/**
	 * 项目总收入
	 *
	 * @param searchStatistics
	 * @param depts
	 * @param userPermit
	 * @param searchStr
	 * @return
	 */
	@Override
	protected List<DepartStatisticsItem> getGeneralIncome(Statistics searchStatistics, List<Dept> depts, UserPermit userPermit, String searchStr) {
		return this.getReceivedPayments10(searchStatistics, depts, userPermit, searchStr);
	}


	/**
	 * 未确认收入
	 *
	 * @param searchStatistics
	 * @param depts
	 * @param userPermit
	 * @param searchStr
	 * @return
	 */
	@Override
	protected List<DepartStatisticsItem> getUnconfirmedIncome(Statistics searchStatistics, List<Dept> depts, UserPermit userPermit, String searchStr) {
		return super.getReceivedPayments12(searchStatistics, depts, userPermit, searchStr);
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
