package com.pm.action;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.actions.BaseAction;
import com.common.beans.Pager;
import com.common.utils.IDKit;
import com.pm.domain.business.StaffSalaryDetail;
import com.pm.domain.system.ItemDefine;
import com.pm.service.IItemDefineService;
import com.pm.service.IStaffSalaryDetailService;
import com.pm.util.PubMethod;
import com.pm.util.constant.EnumAjaxDone;
import com.pm.util.constant.EnumEnable;
import com.pm.util.constant.EnumItemDirection;
import com.pm.util.constant.EnumSalaryItem;


@Controller
@RequestMapping("ItemDefineAction.do")
public class ItemDefineAction extends BaseAction {


	private static final String rel = "rel120002";

	@Autowired
	private IItemDefineService itemDefineService;

	@Autowired
	private IStaffSalaryDetailService staffSalaryDetailService;


	@RequestMapping(params = "method=list")
	public String list(ItemDefine itemDefine,HttpServletResponse res,HttpServletRequest request){

		Pager<ItemDefine> pager = itemDefineService.queryItemDefine(itemDefine,  PubMethod.getPager(request, ItemDefine.class));
		PubMethod.setRequestPager(request, pager,ItemDefine.class);	

		request.setAttribute("itemDefine", itemDefine);

		return "setting/itemdefine_list";
	}


	private void paramprocess(HttpServletRequest request,ItemDefine itemDefine){
		
		EnumItemDirection enum1 = EnumItemDirection.getEnumByCode(itemDefine.getItem_direction());
		if(enum1 != null){
			itemDefine.setItem_direction_name(enum1.getVal());
		}		
		EnumEnable enum2 = EnumEnable.getEnumByCode(itemDefine.getEnable_state());
		if(enum2 != null){
			itemDefine.setEnable_state_name(enum2.getVal());
		}
		
	}


	@RequestMapping(params = "method=toEdit")
	public String toEdit(ItemDefine searchItemDefine,HttpServletResponse res,HttpServletRequest request){
		ItemDefine itemDefine = null;
		if(searchItemDefine != null && searchItemDefine.getId()!=null){
			request.setAttribute("next_operation", "updateItemDefine");
			itemDefine = itemDefineService.getItemDefine(searchItemDefine.getId());	
			
		}else {
			request.setAttribute("next_operation", "addItemDefine");		
			itemDefine = new ItemDefine();	
			itemDefine.setItem_direction(EnumItemDirection.Pay.getCode());
		}
		request.setAttribute("itemDefine1", itemDefine);
		return "setting/itemdefine_edit";
	}
	

	@RequestMapping(params = "method=toEditFormula")
	public String toEditFormula(ItemDefine searchItemDefine,HttpServletResponse res,HttpServletRequest request){
		ItemDefine itemDefine = itemDefineService.getItemDefine(searchItemDefine.getId());	 
		request.setAttribute("next_operation", "updateFormula");
		request.setAttribute("itemDefine1", itemDefine);
		
		List<String[]> list = new ArrayList<String[]>();
		
		for(EnumSalaryItem salaryItem : EnumSalaryItem.values()){
			String temp[] = new String[]{salaryItem.getKey(),salaryItem.getShowName(),salaryItem.getExplain()};
			list.add(temp);
		}

		request.setAttribute("list", list);
		return "setting/item_formula_edit";
	}
	


	@RequestMapping(params = "method=addItemDefine")
	public String addItemDefine(ItemDefine addItemDefine,HttpServletResponse res,HttpServletRequest request){
		ItemDefine itemDefine = addItemDefine;	
		paramprocess(request,itemDefine);
		
		itemDefine.setId(IDKit.generateId());	
		
		int count = 0;
		try{
			itemDefine.setEnable_state(EnumEnable.Enable.getCode());
			itemDefine.setEnable_state_name(EnumEnable.Enable.getVal());
			count = itemDefineService.addItemDefine(itemDefine);			
			request.setAttribute(EnumAjaxDone.forwardUrl.name(), request.getContextPath()+"/ItemDefineAction.do?method=list");
		}catch(Exception e){
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "该单据已经存在！", true);
	}


	@RequestMapping(params = "method=updateItemDefine")
	public String updateItemDefine(ItemDefine updateItemDefine,HttpServletResponse res,HttpServletRequest request){
		ItemDefine itemDefine = updateItemDefine;	
		paramprocess(request,itemDefine);	
		int count = 0;
		try{
			ItemDefine temp = itemDefineService.get(itemDefine.getId());
			temp.setDescription(itemDefine.getDescription());
			temp.setItem_direction(itemDefine.getItem_direction());
			EnumItemDirection enumItemDirection = EnumItemDirection.getEnumByCode(itemDefine.getItem_direction());
			if(enumItemDirection != null) temp.setItem_direction_name(enumItemDirection.getVal());
			temp.setItem_name(itemDefine.getItem_name());
			count = itemDefineService.updateItemDefine(temp);	
			request.setAttribute(EnumAjaxDone.forwardUrl.name(), request.getContextPath()+"/ItemDefineAction.do?method=list");
		}catch(Exception e){
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "修改定义错误！", true);
	}	

	

	@RequestMapping(params = "method=updateEnableState")
	public String updateEnableState(ItemDefine updateItemDefine,HttpServletResponse res,HttpServletRequest request){
		ItemDefine itemDefine = updateItemDefine;	
		paramprocess(request,itemDefine);	
		int count = 0;
		try{

			request.setAttribute(EnumAjaxDone.forwardUrl.name(), request.getContextPath()+"/ItemDefineAction.do?method=list");
			
			ItemDefine temp = itemDefineService.get(itemDefine.getId());			
			if(temp.getEnable_state().equals(itemDefine.getEnable_state())){
				return this.ajaxForwardSuccess(request, rel, true);
			}
			temp.setEnable_state(itemDefine.getEnable_state());
			temp.setEnable_state_name(itemDefine.getEnable_state_name());
			count = itemDefineService.updateItemDefine(temp);	
		}catch(Exception e){
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, false);
		else return this.ajaxForwardError(request, "更新状态错误！", true);
	}	
	
	



	@RequestMapping(params = "method=updateFormula")
	public String updateFormula(ItemDefine updateItemDefine,HttpServletResponse res,HttpServletRequest request){
		ItemDefine itemDefine = updateItemDefine;	
		int count = 0;
		try{
			
			if(itemDefine.getComputational_formula_text() == null || itemDefine.getComputational_formula_text().trim().isEmpty()){
				return this.ajaxForwardError(request, "请先填写公式！", false);
			}
			
			String msg = validateFormula(itemDefine);
			if(msg != null && msg.length() >0){
				return this.ajaxForwardError(request, msg , false);
			}

			
			ItemDefine temp = itemDefineService.get(itemDefine.getId());			
			if(
					(temp.getComputational_formula() == null && itemDefine.getComputational_formula() == null) ||
					(temp.getComputational_formula() != null && itemDefine.getComputational_formula() != null 
						&& temp.getComputational_formula().equals(itemDefine.getComputational_formula()))){
				return this.ajaxForwardSuccess(request, rel, true);
			}
			temp.setComputational_formula(itemDefine.getComputational_formula());
			temp.setComputational_formula_text(itemDefine.getComputational_formula_text());
			count = itemDefineService.updateItemDefine(temp);	
			

			request.setAttribute(EnumAjaxDone.forwardUrl.name(), request.getContextPath()+"/ItemDefineAction.do?method=list");
			
		}catch(Exception e){
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "更新公式错误！", true);
	}	
	
	private String validateFormula(ItemDefine itemDefine){
		
		try{
			if(
					itemDefine.getComputational_formula_text().toLowerCase().indexOf("insert")!=-1 ||
					itemDefine.getComputational_formula_text().toLowerCase().indexOf("update")!=-1 ||
					itemDefine.getComputational_formula_text().toLowerCase().indexOf("select")!=-1 ||
					itemDefine.getComputational_formula_text().toLowerCase().indexOf("delete")!=-1 ||
					itemDefine.getComputational_formula_text().toLowerCase().indexOf("truncate")!=-1 
			){
				return "公式格式错误，请重新填写！";
			}
			

			String temp = new String(itemDefine.getComputational_formula_text());	
			for(EnumSalaryItem salaryItem : EnumSalaryItem.values()){
				temp = temp.replaceAll(salaryItem.getShowName(), "");
			}			

			temp = temp.replaceAll(" ", "");
			temp = temp.replaceAll("\\(", "");
			temp = temp.replaceAll("\\)", "");
			temp = temp.replaceAll("\\+", "");
			temp = temp.replaceAll("-", "");
			temp = temp.replaceAll("\\*", "");
			temp = temp.replaceAll("/", "");
			temp = temp.replaceAll("\\.", "");
			
			String pat="\\d+";
			Pattern p=Pattern.compile(pat);
			Matcher m=p.matcher(temp);
			temp=m.replaceAll("");
			
			if(!temp.isEmpty())  return "公式格式错误，请重新填写！";
			
			
			
			String computational_formula = getFormula(itemDefine);
			List<StaffSalaryDetail> ssd = staffSalaryDetailService.queryVirtualStaffSalaryDetail(itemDefine.getId(), computational_formula);
			itemDefine.setComputational_formula(computational_formula);
			return null;
		}catch(Exception e){
			String msg = e.getMessage();
			if(msg.indexOf("Unknown column '")!= -1){
				msg = msg.substring(msg.indexOf("Unknown column '")+"Unknown column '".length());
				msg = msg.substring(0, msg.indexOf("'"))+"  设置错误";
			}else {
				msg = "公式格式错误，请重新填写！";
			}
			return msg;
		}
	}
	
	private String getFormula(ItemDefine itemDefine){
		String computational_formula = new String(itemDefine.getComputational_formula_text());			
		
		for(EnumSalaryItem salaryItem : EnumSalaryItem.values()){
			computational_formula = computational_formula.replaceAll(salaryItem.getShowName(), salaryItem.getKey());
		}
		return computational_formula;
	}





	@RequestMapping(params = "method=deleteItemDefine")
	public String deleteItemDefine(HttpServletResponse res,HttpServletRequest request){
		String[] ids = request.getParameterValues("ids");
		if(ids != null && ids.length > 0){
			ItemDefine[] itemDefines = new ItemDefine[ids.length];
			int index = 0;
			for(String id : ids){
				ItemDefine itemDefine = new ItemDefine();
				itemDefine.setId(id);
				itemDefines[index] = itemDefine;
				index ++ ;
			}
			if(itemDefines != null && itemDefines.length > 0)
			itemDefineService.deleteItemDefine(itemDefines);
		}
		return this.ajaxForwardSuccess(request,rel,false);
	}	





}