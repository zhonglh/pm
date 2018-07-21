package com.pm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.actions.BaseAction;
import com.common.beans.Pager;
import com.pm.domain.business.ThInvoice;
import com.pm.service.IThInvoiceService;
import com.pm.util.PubMethod;

/**
 * 发票历史管理
 * @author zhonglh
 *
 */
@Controller
@RequestMapping(value = "InvoiceHisAction.do")
public class InvoiceHisAction extends BaseAction {
	

	@Autowired
	private IThInvoiceService thInvoiceService;
	
	


	@RequestMapping(params = "method=list")
	public String list(ThInvoice searchInvoice,HttpServletResponse res,HttpServletRequest request){

		
		Pager<ThInvoice> pager = thInvoiceService.queryThInvoice(searchInvoice, PubMethod.getPagerByAll(request, ThInvoice.class));
		PubMethod.setRequestPager(request, pager,ThInvoice.class);	

		if(pager.getResultList() == null || pager.getResultList().size()  <= 1 ){
			return this.ajaxForwardError(request, "该单据没有历史记录！", true);
		}
		
		return "projectincome/invoice_his_list";
	}		
	


	@RequestMapping(params = "method=toView")
	public String toView(ThInvoice searchInvoice,HttpServletResponse res,HttpServletRequest request){
		
		ThInvoice thInvoice = thInvoiceService.getThInvoice(searchInvoice.getId());
		request.setAttribute("invoice1", thInvoice);
		
		return "projectincome/invoice_his_view";
		
	}	
	
	
	
}
