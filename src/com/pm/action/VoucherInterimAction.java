package com.pm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.actions.BaseAction;
import com.common.beans.Pager;
import com.pm.domain.business.VoucherInterim;
import com.pm.domain.system.User;
import com.pm.service.IVoucherInterimService;
import com.pm.util.PubMethod;
import com.pm.util.constant.EnumDataType;
import com.pm.util.constant.EnumOperationType;
import com.pm.util.excel.exports.BusinessExcel;



@Controller
@RequestMapping(value = "VoucherInterimAction.do")
public  class VoucherInterimAction extends BaseAction {
	


	@Autowired
	protected IVoucherInterimService voucherInterimService;
	
	
	protected  void setType(VoucherInterim voucherInterim) {
		
	}


	@RequestMapping(params = "method=list")
	public String list(VoucherInterim voucherInterim,HttpServletResponse res,HttpServletRequest request){


		setType(voucherInterim);

		Pager<VoucherInterim> pager = voucherInterimService.queryVoucherInterim(voucherInterim, null , PubMethod.getPager(request, VoucherInterim.class));
		PubMethod.setRequestPager(request, pager,VoucherInterim.class);	

		request.setAttribute("voucherInterim", voucherInterim);
		request.setAttribute("action", this.getClass().getSimpleName());
		request.setAttribute(EnumOperationType.READ.getKey(), "1");	

		return "projectincome/voucherinterim_list";
	}



	@RequestMapping(params = "method=export")
	public synchronized void export(VoucherInterim voucherInterim,HttpServletResponse res,HttpServletRequest request){
		

		setType(voucherInterim);
		
		
		
		voucherInterim.setExported("0");
		Pager<VoucherInterim> pager = voucherInterimService.queryVoucherInterim(voucherInterim, null, PubMethod.getPagerByAll(request, VoucherInterim.class));
		try{

			User sessionUser = PubMethod.getUser(request);
			voucherInterimService.doExport(pager.getResultList(), sessionUser);
			if(pager.getResultList() != null && pager.getResultList().size() > 0){
				int index = 0;
				String lastDateId = "";
				for(VoucherInterim temp : pager.getResultList()){

					if(!lastDateId.equals(temp.getData_id())){
						index ++ ;
						lastDateId = temp.getData_id();
					}
					
					temp.setVoucher_id(String.valueOf(index));
					temp.setVoucher_no(String.valueOf(index));
				}
			}
			BusinessExcel.exportPopular(res, null, pager.getResultList(), VoucherInterim.class);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}	
	
	
	
	@RequestMapping(params = "method=toView")
	public String toView(VoucherInterim voucherInterim,HttpServletResponse res,HttpServletRequest request){
		VoucherInterim temp = voucherInterimService.getVoucherInterim(voucherInterim.getId());
		if(EnumDataType.monthly_statement.getId().equals(temp.getData_type())){
			return "forward:/MonthlyStatementAction.do?method=toView&monthly_statement_id="+temp.getData_id();
		}else if(EnumDataType.invoice.getId().equals(temp.getData_type())){
			return "forward:/InvoiceManageAction.do?method=toView&invoice_id="+temp.getData_id();
		}if(EnumDataType.received_payment.getId().equals(temp.getData_type())){
			return "forward:/ReceivedPaymentAction.do?method=toView&receive_payment_id="+temp.getData_id();
		}
		return null;
	}



}