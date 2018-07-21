package com.pm.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.actions.BaseAction;
import com.pm.domain.business.DicType;
import com.pm.domain.business.ParamExtend;
import com.pm.domain.system.Menu;
import com.pm.domain.system.User;
import com.pm.service.IDicDataService;
import com.pm.service.IParamExtendService;
import com.pm.service.IUserService;
import com.pm.util.PubMethod;
import com.pm.util.constant.BusinessUtil;

@Controller
@RequestMapping(value = "IndexAction.do")
public class IndexAction extends BaseAction {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IParamExtendService paramExtendService;
	

	@Autowired
	private IDicDataService dicDataService;
	
		
	@RequestMapping(params = "method=index")
	public String index(HttpServletResponse res,HttpServletRequest request){
		
		User user = PubMethod.getUser(request);
		
		List<Menu> allmenu = userService.queryMenusByUserId(user);
		
		List<Menu> menus = new ArrayList<Menu>();
		
		if(allmenu != null){
			for(Menu menu : allmenu){
				if(menu == null) continue;
				if(menu.getMenulevel() == 1)
					menus.add(menu);
			}
			
	
			for(Menu menu : allmenu){
				if(menu == null) continue;
				if(menu.getMenulevel() == 2){
					getFirstMenu(menu,menus).getChilds().add(menu);		
				}
				
				if(menu.getMenu_url() != null && menu.getMenu_url().toLowerCase().startsWith("http")){
					menu.setExternal(true);
				}
			}
		}
		
		
		ParamExtend paramExtend = new ParamExtend();
		paramExtend.setGroup1(BusinessUtil.PARAM_GROUP_SALARY);			
		List<ParamExtend> paramExtends = paramExtendService.queryAllParamExtend(paramExtend);
		request.setAttribute("paramExtends", paramExtends);			
		
	
		List<DicType> allDicType = dicDataService.getAllDicType();
		request.setAttribute("dicTypes", allDicType);			
		
		
		request.setAttribute("menus", menus);
		request.setAttribute("user", user);		

		
		return "index";
	}	
	
	
	private Menu getFirstMenu(Menu menu,List<Menu> menus){
		for(Menu temp : menus){
			if(temp.getMenu_id().equals(menu.getPmenu_id()))
				return temp;
		}
		return null;
	}

}
