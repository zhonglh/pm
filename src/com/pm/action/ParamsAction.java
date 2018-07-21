package com.pm.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cache.CacheManager;
import com.common.actions.BaseAction;
import com.pm.domain.business.Params;
import com.pm.engine.BusinessSend;
import com.pm.engine.StaffProcessEngine;
import com.pm.service.IParamsService;


@Controller
@RequestMapping(value = "ParamsAction.do")
public class ParamsAction extends BaseAction {

	@Autowired
	private IParamsService paramsService;

	@RequestMapping(params = "method=list")
	public String list(HttpServletResponse res,HttpServletRequest request){
		List<Params> list = paramsService.queryAllParams(new Params());

		request.setAttribute("list", list);
		return "setting/params";
	}
	

	@RequestMapping(params = "method=save")
	public String save(HttpServletResponse res,HttpServletRequest request){
		List<Params> list = paramsService.queryAllParams(new Params());
		
		int index = 0;
		for(Params params :list){
			params.setParam_id(request.getParameter("param_id"+index));
			params.setParam_value(request.getParameter("param_value"+index));
			index ++;
		}

		paramsService.updateParams(list);
		
		BusinessSend.send(Params.class.getSimpleName());
		
		//处理缓存
		CacheManager.setTaxRate(0);
		this.computeTaxRate();
		
		return this.ajaxForwardSuccess(request, "", true);
	}
	
	

}
