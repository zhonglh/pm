
package com.pm.action;

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
import com.pm.util.excel.exports.BusinessExcel;
import com.pm.util.excel.exports.DepartStatisticsExcel;
import com.pm.vo.DepartStatisticsItem;
import com.pm.vo.UserPermit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 现金盈余统计
 * @author zhonglihong
 * @date 2016年6月2日 下午8:39:28
 */
@Controller
@RequestMapping(value = "CashStatisticsAction.do")
public class CashStatisticsAction extends DepartStatisticsAction {

	private final String PERMIT_ID = EnumPermit.DEPARTSTATISTICS.getId();


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
		return this.getReceivedPayments(searchStatistics, depts, userPermit, searchStr);
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
		return super.getProjectExpends41(searchStatistics, depts, userPermit, searchStr);
	}


}

