package com.pm.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.actions.BaseAction;
import com.common.exceptions.CommonErrorConstants;
import com.common.exceptions.PMException;
import com.pm.domain.business.ParamExtend;
import com.pm.service.IParamExtendService;
import com.pm.util.constant.EnumSalary;


@Controller
@RequestMapping(value = "ParamExtendAction.do")
public class ParamExtendAction extends BaseAction {

	@Autowired
	private IParamExtendService paramExtendService;

	@RequestMapping(params = "method=list")
	public String list(String group1,HttpServletResponse res,HttpServletRequest request){
		ParamExtend paramExtend = new ParamExtend();
		paramExtend.setGroup1(group1);
		List<ParamExtend> list = paramExtendService.queryAllParamExtend(paramExtend);
		request.setAttribute("list", list);
		return "setting/paramExtends";
	}
	

	@RequestMapping(params = "method=paramExtendSets")
	public String toParamExtend(ParamExtend paramExtend,HttpServletResponse res,HttpServletRequest request){		
		List<ParamExtend> list = paramExtendService.queryAllParamExtend(paramExtend);
		if(list == null || list.isEmpty()) throw new PMException(CommonErrorConstants.e999999);
		request.setAttribute("paramExtend1", list.get(0));
		return "setting/paramExtendSets";
	}
	
	
	@RequestMapping(params = "method=save")
	public String save(ParamExtend paramExtend, HttpServletResponse res,HttpServletRequest request){
		
		if(EnumSalary.sick_leave_salary.getId().equals(paramExtend.getGroup2())){
			//处理病假工资
			String exp = "";
			paramExtend.setProcessor(String.valueOf((Double.parseDouble(paramExtend.getProcessor())/100)));
			if("2".equals(paramExtend.getType1())){
				exp = paramExtend.getRealVal()+"/field.should_work_days*field.sick_leave_days*"+paramExtend.getProcessor();
			}else {
				exp = "getCountSalary(field)/field.should_work_days*field.sick_leave_days*"+paramExtend.getProcessor();				
			}			
			paramExtend.setExpression(exp);	
		}
		
		List<ParamExtend> list = new ArrayList<ParamExtend>();
		list.add(paramExtend);		
		paramExtendService.updateParamExtend(list);		
		return this.ajaxForwardSuccess(request, "", true);
	}
	
	

}
