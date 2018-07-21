package com.pm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cache.CacheManager;
import com.common.actions.BaseAction;
import com.pm.domain.system.MarketSets;
import com.pm.engine.BusinessSend;
import com.pm.engine.StaffProcessEngine;
import com.pm.service.IMarketSetsService;
import com.pm.util.constant.BusinessUtil;
import com.pm.util.constant.EnumAffectStrategy;


@Controller
@RequestMapping("MarketSetsAction.do")
public class MarketSetsAction extends BaseAction {

	@Autowired
	private IMarketSetsService marketSetsService;
	


	private void paramprocess(HttpServletRequest request,MarketSets marketSets){
		EnumAffectStrategy enum1 = EnumAffectStrategy.getEnumByCode(marketSets.getAffect_strategy());
		if(enum1 != null) marketSets.setAffect_strategy_name(enum1.getVal());
		
		if(enum1 == EnumAffectStrategy.fixed){
			marketSets.setAffect_decline_factor(1.00);
		}
	}


	@RequestMapping(params = "method=toEdit")
	public String toEdit(MarketSets searchMarketSets,HttpServletResponse res,HttpServletRequest request){
		
		MarketSets marketSets = null;
		request.setAttribute("next_operation", "updateMarketSets");
		marketSets = getMarketSets();	
		

		request.setAttribute("MAX_LEVEL", BusinessUtil.MAX_LEVEL);
		
		request.setAttribute("marketSets1", marketSets);
		return "setting/marketsets_edit";
	}


	@RequestMapping(params = "method=updateMarketSets")
	public String updateMarketSets(MarketSets updateMarketSets,HttpServletResponse res,HttpServletRequest request){
		MarketSets marketSets = updateMarketSets;
		paramprocess(request,marketSets);	
		int count = 0;
		try{			
			count = marketSetsService.updateMarketSets(marketSets);	
			CacheManager.setMarketSets(marketSets);
			BusinessSend.send("marketSets");
		}catch(Exception e){
		}
		
		if(count == 1) 	return this.ajaxForwardSuccess(request, "", true);
		else return this.ajaxForwardError(request, "该单据已经存在！", true);
	}	



}