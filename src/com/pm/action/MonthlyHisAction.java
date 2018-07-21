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

import com.common.actions.BaseAction;
import com.common.beans.Pager;
import com.pm.domain.business.ThMonthlyStatement;
import com.pm.domain.business.ThMonthlyStatementDetail;
import com.pm.service.IThMonthlyStatementDetailService;
import com.pm.service.IThMonthlyStatementService;
import com.pm.util.PubMethod;


@Controller
@RequestMapping(value = "MonthlyHisAction.do")
public class MonthlyHisAction extends BaseAction {
	


	@Autowired
	private IThMonthlyStatementService thMonthlyStatementService;


	@Autowired
	private IThMonthlyStatementDetailService thMonthlyStatementDetailService;
	
	


	@RequestMapping(params = "method=list")
	public String list(ThMonthlyStatement searchMonthlyStatement,HttpServletResponse res,HttpServletRequest request){

		
		Pager<ThMonthlyStatement> pager = thMonthlyStatementService.queryThMonthlyStatement(searchMonthlyStatement ,PubMethod.getPagerByAll(request, ThMonthlyStatement.class));
		PubMethod.setRequestPager(request, pager,ThMonthlyStatement.class);	

		if(pager.getResultList() == null || pager.getResultList().size() <= 1 ){
			return this.ajaxForwardError(request, "该单据没有历史记录！", true);
		}
		
		return "projectincome/monthly_his_list";
	}		
	


	@RequestMapping(params = "method=toView")
	public String toView(ThMonthlyStatement searchMonthlyStatement,HttpServletResponse res,HttpServletRequest request){
		
		ThMonthlyStatement thMonthlyStatement = thMonthlyStatementService.getThMonthlyStatement(searchMonthlyStatement.getId());
		
		ThMonthlyStatementDetail thMonthlyStatementDetail = new ThMonthlyStatementDetail();
		thMonthlyStatementDetail.setMonthly_statement_his_id(thMonthlyStatement.getId());
		Pager<ThMonthlyStatementDetail> pager = thMonthlyStatementDetailService.queryThMonthlyStatementDetail(thMonthlyStatementDetail, PubMethod.getPagerByAll(request, ThMonthlyStatementDetail.class));
		
		if(pager.getResultList() != null && pager.getResultList().size() >0 ){

			ThMonthlyStatementDetail totalMonthlyStatementDetail = new ThMonthlyStatementDetail();
			for(ThMonthlyStatementDetail monthlyStatementDetail : pager.getResultList()){
				totalMonthlyStatementDetail.setTechnical_cost(totalMonthlyStatementDetail.getTechnical_cost()+monthlyStatementDetail.getTechnical_cost());
				totalMonthlyStatementDetail.setTotal_cost(totalMonthlyStatementDetail.getTotal_cost()+monthlyStatementDetail.getTotal_cost());
				totalMonthlyStatementDetail.setDaily_overtime(totalMonthlyStatementDetail.getDaily_overtime()+monthlyStatementDetail.getDaily_overtime());
				totalMonthlyStatementDetail.setMan_month(totalMonthlyStatementDetail.getMan_month()+monthlyStatementDetail.getMan_month());
				totalMonthlyStatementDetail.setOther_cost(totalMonthlyStatementDetail.getOther_cost()+monthlyStatementDetail.getOther_cost());
				totalMonthlyStatementDetail.setOvertime_cost(totalMonthlyStatementDetail.getOvertime_cost()+monthlyStatementDetail.getOvertime_cost());
				totalMonthlyStatementDetail.setShould_work_days(totalMonthlyStatementDetail.getShould_work_days()+monthlyStatementDetail.getShould_work_days());
				totalMonthlyStatementDetail.setWeekend_overtime(totalMonthlyStatementDetail.getWeekend_overtime()+monthlyStatementDetail.getWeekend_overtime());
				totalMonthlyStatementDetail.setBusiness_trip_cost(totalMonthlyStatementDetail.getBusiness_trip_cost()+monthlyStatementDetail.getBusiness_trip_cost());
				totalMonthlyStatementDetail.setBusiness_trip_days(totalMonthlyStatementDetail.getBusiness_trip_days()+monthlyStatementDetail.getBusiness_trip_days());
				totalMonthlyStatementDetail.setWork_days(totalMonthlyStatementDetail.getWork_days()+monthlyStatementDetail.getWork_days());			
			}

			request.setAttribute("totalMonthlyStatementDetail", totalMonthlyStatementDetail);
		}
		
		
		request.setAttribute("monthlyStatement1", thMonthlyStatement);
		request.setAttribute("list", pager.getResultList());
		
		return "projectincome/monthly_his_view";
		
	}	
	
	
	
	


	@RequestMapping(params = "method=compare")
	public String compare(String ids,HttpServletResponse res,HttpServletRequest request){

		String[] idArray = ids.split(",");		
		ThMonthlyStatement thMonthlyStatement1 = thMonthlyStatementService.getThMonthlyStatement(idArray[1]);		
		ThMonthlyStatementDetail thMonthlyStatementDetail1 = new ThMonthlyStatementDetail();
		thMonthlyStatementDetail1.setMonthly_statement_his_id(thMonthlyStatement1.getId());
		Pager<ThMonthlyStatementDetail> pager1 = thMonthlyStatementDetailService.queryThMonthlyStatementDetail(thMonthlyStatementDetail1, PubMethod.getPagerByAll(request, ThMonthlyStatementDetail.class));
		
		ThMonthlyStatementDetail totalMonthlyStatementDetail1 = null;
		if(pager1.getResultList() != null && pager1.getResultList().size() >0 ){

			totalMonthlyStatementDetail1 = new ThMonthlyStatementDetail();
			for(ThMonthlyStatementDetail monthlyStatementDetail : pager1.getResultList()){
				totalMonthlyStatementDetail1.setTechnical_cost(totalMonthlyStatementDetail1.getTechnical_cost()+monthlyStatementDetail.getTechnical_cost());
				totalMonthlyStatementDetail1.setTotal_cost(totalMonthlyStatementDetail1.getTotal_cost()+monthlyStatementDetail.getTotal_cost());
				totalMonthlyStatementDetail1.setDaily_overtime(totalMonthlyStatementDetail1.getDaily_overtime()+monthlyStatementDetail.getDaily_overtime());
				totalMonthlyStatementDetail1.setMan_month(totalMonthlyStatementDetail1.getMan_month()+monthlyStatementDetail.getMan_month());
				totalMonthlyStatementDetail1.setOther_cost(totalMonthlyStatementDetail1.getOther_cost()+monthlyStatementDetail.getOther_cost());
				totalMonthlyStatementDetail1.setOvertime_cost(totalMonthlyStatementDetail1.getOvertime_cost()+monthlyStatementDetail.getOvertime_cost());
				totalMonthlyStatementDetail1.setShould_work_days(totalMonthlyStatementDetail1.getShould_work_days()+monthlyStatementDetail.getShould_work_days());
				totalMonthlyStatementDetail1.setWeekend_overtime(totalMonthlyStatementDetail1.getWeekend_overtime()+monthlyStatementDetail.getWeekend_overtime());
				totalMonthlyStatementDetail1.setWork_days(totalMonthlyStatementDetail1.getWork_days()+monthlyStatementDetail.getWork_days());	
				totalMonthlyStatementDetail1.setBusiness_trip_cost(totalMonthlyStatementDetail1.getBusiness_trip_cost()+monthlyStatementDetail.getBusiness_trip_cost());
				totalMonthlyStatementDetail1.setBusiness_trip_days(totalMonthlyStatementDetail1.getBusiness_trip_days()+monthlyStatementDetail.getBusiness_trip_days());		
			}
			request.setAttribute("totalMonthlyStatementDetail1", totalMonthlyStatementDetail1);
		}			
		request.setAttribute("monthlyStatement1", thMonthlyStatement1);
		request.setAttribute("list1", pager1.getResultList());
		
		
		
		
		

		ThMonthlyStatement thMonthlyStatement2 = thMonthlyStatementService.getThMonthlyStatement(idArray[0]);		
		ThMonthlyStatementDetail thMonthlyStatementDetail2 = new ThMonthlyStatementDetail();
		thMonthlyStatementDetail2.setMonthly_statement_his_id(thMonthlyStatement2.getId());
		Pager<ThMonthlyStatementDetail> pager2 = thMonthlyStatementDetailService.queryThMonthlyStatementDetail(thMonthlyStatementDetail2, PubMethod.getPagerByAll(request, ThMonthlyStatementDetail.class));
		ThMonthlyStatementDetail totalMonthlyStatementDetail2 = null;
		if(pager2.getResultList() != null && pager2.getResultList().size() >0 ){
			totalMonthlyStatementDetail2 = new ThMonthlyStatementDetail();
			for(ThMonthlyStatementDetail monthlyStatementDetail : pager2.getResultList()){
				totalMonthlyStatementDetail2.setTechnical_cost(totalMonthlyStatementDetail2.getTechnical_cost()+monthlyStatementDetail.getTechnical_cost());
				totalMonthlyStatementDetail2.setTotal_cost(totalMonthlyStatementDetail2.getTotal_cost()+monthlyStatementDetail.getTotal_cost());
				totalMonthlyStatementDetail2.setDaily_overtime(totalMonthlyStatementDetail2.getDaily_overtime()+monthlyStatementDetail.getDaily_overtime());
				totalMonthlyStatementDetail2.setMan_month(totalMonthlyStatementDetail2.getMan_month()+monthlyStatementDetail.getMan_month());
				totalMonthlyStatementDetail2.setOther_cost(totalMonthlyStatementDetail2.getOther_cost()+monthlyStatementDetail.getOther_cost());
				totalMonthlyStatementDetail2.setOvertime_cost(totalMonthlyStatementDetail2.getOvertime_cost()+monthlyStatementDetail.getOvertime_cost());
				totalMonthlyStatementDetail2.setShould_work_days(totalMonthlyStatementDetail2.getShould_work_days()+monthlyStatementDetail.getShould_work_days());
				totalMonthlyStatementDetail2.setWeekend_overtime(totalMonthlyStatementDetail2.getWeekend_overtime()+monthlyStatementDetail.getWeekend_overtime());
				totalMonthlyStatementDetail2.setWork_days(totalMonthlyStatementDetail2.getWork_days()+monthlyStatementDetail.getWork_days());	
				totalMonthlyStatementDetail2.setBusiness_trip_cost(totalMonthlyStatementDetail2.getBusiness_trip_cost()+monthlyStatementDetail.getBusiness_trip_cost());
				totalMonthlyStatementDetail2.setBusiness_trip_days(totalMonthlyStatementDetail2.getBusiness_trip_days()+monthlyStatementDetail.getBusiness_trip_days());			
			}
			request.setAttribute("totalMonthlyStatementDetail2", totalMonthlyStatementDetail2);
		}
		request.setAttribute("monthlyStatement2", thMonthlyStatement2);
		request.setAttribute("list2", pager2.getResultList());		
		
		
		List<String> diffs = compareMonthlyStatement(thMonthlyStatement1,thMonthlyStatement2);		
		StringBuilder sb = new StringBuilder("");
		if(diffs != null && diffs.size() >0){
			for(String diff : diffs )
			sb.append(",").append(diff).append(",");
		}		
		request.setAttribute("diffs", sb.toString());	
		
		
		StringBuilder detailSB = new StringBuilder("");
		if(pager1.getResultList().size() == pager2.getResultList().size()){
			List<String> details = new ArrayList<String>();
			if(pager1.getResultList().size() > 0){
				int index = 0 ;
				for( ;index < pager1.getResultList().size() ; index ++){
					List<String> list = compareMonthlyStatementDetail(pager1.getResultList().get(index),pager2.getResultList().get(index));
					if(list != null && list.size() > 0){
						for(String item : list){
							details.add(String.valueOf(index+1)+"___"+item);
							detailSB.append(",").append(String.valueOf(index)).append("___").append(item).append(",");
						}
					}
				}
				List<String> list = compareMonthlyStatementDetail(totalMonthlyStatementDetail1,totalMonthlyStatementDetail2);
				if(list != null && list.size() > 0){
					for(String item : list){
						details.add(String.valueOf(index+1)+"___"+item);
						detailSB.append(",").append(String.valueOf(index)).append("___").append(item).append(",");
					}
				}				
				
			}
		}else if(pager1.getResultList().size() != 0 && pager2.getResultList().size() != 0){
			//比较复杂了, 应该不存在这种情况
		}		

		request.setAttribute("detaildiffs",detailSB.toString());
		

		
		String[] numbers = new String[10000];
		for(int i=0;i<10000;i++){
			numbers[i] = String.valueOf(i);
		}
		request.setAttribute("numbers",numbers);
		
		return "projectincome/monthly_his_compare";
	}		
	
	
	
	private List<String> compareMonthlyStatement(ThMonthlyStatement thMonthlyStatement1 , ThMonthlyStatement thMonthlyStatement2){
		List<String> list = new ArrayList<String>();
		if(thMonthlyStatement1 == null || thMonthlyStatement2 == null) return list;
		if(thMonthlyStatement1.getStatement_month() != thMonthlyStatement2.getStatement_month()){
			list.add("statement_month");
		}
		
		if(thMonthlyStatement1.getAmount() != thMonthlyStatement2.getAmount()){
			list.add("jsje");
			list.add("amount");
		}
		

		String project_name = thMonthlyStatement1.getProject_name();
		if(project_name == null) project_name = "";
		if(! project_name.equals( thMonthlyStatement2.getProject_name())){
			list.add("project_name");
		}
		
		String statement_type = thMonthlyStatement1.getStatement_type();
		if(statement_type == null) statement_type = "";
		if(! statement_type.equals( thMonthlyStatement2.getStatement_type())){
			list.add("statement_type");
		}
		return list;
	}
	
	

	
	private List<String> compareMonthlyStatementDetail(ThMonthlyStatementDetail thMonthlyStatementDetail1 , ThMonthlyStatementDetail thMonthlyStatementDetail2){
		List<String> list = new ArrayList<String>();
		if(thMonthlyStatementDetail1 == null || thMonthlyStatementDetail2 == null) return list;
		if(thMonthlyStatementDetail1.getTechnical_cost() != thMonthlyStatementDetail2.getTechnical_cost()){
			list.add("technical_cost");
		}
		
		if(thMonthlyStatementDetail1.getShould_work_days() != thMonthlyStatementDetail2.getShould_work_days()){
			list.add("should_work_days");
		}
		
		if(thMonthlyStatementDetail1.getWork_days() != thMonthlyStatementDetail2.getWork_days()){
			list.add("work_days");
		}
		
		if(thMonthlyStatementDetail1.getDaily_overtime() != thMonthlyStatementDetail2.getDaily_overtime()){
			list.add("daily_overtime");
		}
		
		
		if(thMonthlyStatementDetail1.getWeekend_overtime() != thMonthlyStatementDetail2.getWeekend_overtime()){
			list.add("weekend_overtime");
		}
		
		if(thMonthlyStatementDetail1.getOvertime_cost() != thMonthlyStatementDetail2.getOvertime_cost()){
			list.add("overtime_cost");
		}
		
		if(thMonthlyStatementDetail1.getBusiness_trip_days() != thMonthlyStatementDetail2.getBusiness_trip_days()){
			list.add("business_trip_days");
		}
		
		if(thMonthlyStatementDetail1.getBusiness_trip_cost() != thMonthlyStatementDetail2.getBusiness_trip_cost()){
			list.add("business_trip_cost");
		}
		
		if(thMonthlyStatementDetail1.getOther_cost() != thMonthlyStatementDetail2.getOther_cost()){
			list.add("other_cost");
		}
		
		if(thMonthlyStatementDetail1.getMan_month() != thMonthlyStatementDetail2.getMan_month()){
			list.add("man_month");
		}
		
		if(thMonthlyStatementDetail1.getTotal_cost() != thMonthlyStatementDetail2.getTotal_cost()){
			list.add("total_cost");
		}
		
		
		String client_dept = thMonthlyStatementDetail1.getClient_dept();
		if(client_dept == null) client_dept = "";
		if(!client_dept.equals(thMonthlyStatementDetail2.getClient_dept())){
			list.add("client_dept");
		}
		
		String description = thMonthlyStatementDetail1.getDescription();
		if(description == null) description = "";
		if(!description.equals(thMonthlyStatementDetail2.getDescription())){
			list.add("description");
		}
		
		return list;
	}
	
	
	
}
