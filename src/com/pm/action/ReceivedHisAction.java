package com.pm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.actions.BaseAction;
import com.common.beans.Pager;
import com.pm.domain.business.ThReceivedPayment;
import com.pm.service.IThReceivedPaymentService;
import com.pm.util.PubMethod;

/**
 * 到款历史记录
 * @author zhonglh
 *
 */
@Controller
@RequestMapping(value = "ReceivedHisAction.do")
public class ReceivedHisAction extends BaseAction {
	

	@Autowired
	private IThReceivedPaymentService thReceivedPaymentService;


	@RequestMapping(params = "method=list")
	public String list(ThReceivedPayment searchReceivedPayment,HttpServletResponse res,HttpServletRequest request){
		
		Pager<ThReceivedPayment> pager = thReceivedPaymentService.queryThReceivedPayment(searchReceivedPayment, PubMethod.getPagerByAll(request, ThReceivedPayment.class));
		PubMethod.setRequestPager(request, pager,ThReceivedPayment.class);	
		if(pager.getResultList() == null || pager.getResultList().size() <= 1 ){
			return this.ajaxForwardError(request, "该单据没有历史记录！", true);
		}
		
		return "projectincome/received_his_list";
	
	}
	
	



	@RequestMapping(params = "method=toView")
	public String toView(ThReceivedPayment searchReceivedPayment,HttpServletResponse res,HttpServletRequest request){
		
		ThReceivedPayment thReceivedPayment = thReceivedPaymentService.getThReceivedPayment(searchReceivedPayment.getId());
		request.setAttribute("receivedPayment1", thReceivedPayment);		
		return "projectincome/received_his_view";
		
	}	
	
}
