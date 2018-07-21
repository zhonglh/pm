package com.pm.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.actions.BaseAction;
import com.common.utils.IDKit;
import com.pm.domain.business.Client;
import com.pm.domain.system.User;
import com.pm.service.IItemsService;
import com.pm.service.IRoleService;
import com.pm.util.PubMethod;
import com.pm.util.constant.BusinessUtil;
import com.pm.util.constant.EnumOperationType;
import com.pm.util.constant.EnumPermit;
import com.pm.vo.UserPermit;



@Controller
@RequestMapping(value = "ItemsAction.do")
public class ItemsAction extends BaseAction {

	@Autowired
	private IItemsService itemsService;

	@Autowired
	private IRoleService roleService;
	

	@RequestMapping(params = "method=lookupClient")
	public String lookupClient(HttpServletResponse res,HttpServletRequest request){	
		Client searchClient = new Client();
		searchClient.setDelete_flag(BusinessUtil.NOT_DELETEED);
		searchClient.setClient_name(request.getParameter("client_name"));
		List<Client> list = itemsService.queryClient(searchClient);
		if(list == null) list = new ArrayList<Client>();

		request.setAttribute("list", list);
		
		String use = request.getParameter("use");
		
		if(use != null && use.equals("search")) {
			Client client = new Client();
			client.setClient_id("");
			client.setClient_name("");
			list.add(0,client);
		}		

		return "setting/client_search";	
		
	}
	
	

	@RequestMapping(params = "method=toEditClient")
	public String toEditClient(Client client , HttpServletResponse res,HttpServletRequest request){
		Client client1 = client;
		if(client.getClient_id() == null || client.getClient_id().isEmpty()){
			request.setAttribute("next_operation", "addClient");
		}else {
			List<Client> list = itemsService.queryClient(client);
			if(list != null && !list.isEmpty()) client1 = list.get(0);
			request.setAttribute("next_operation", "updateClient");
		}
		request.setAttribute("client1", client1);
		
		return "setting/client_edit";
	}

	@RequestMapping(params = "method=addClient")
	public String addClient(Client client , HttpServletResponse res,HttpServletRequest request){
		User sessionUser = PubMethod.getUser(request);
		client.setClient_id(IDKit.generateId());
		client.setBuild_datetime(PubMethod.getCurrentDate());
		client.setBuild_userid(sessionUser.getUser_id());
		client.setBuild_username(sessionUser.getUser_name());
		client.setDelete_flag(BusinessUtil.NOT_DELETEED);		
		

		int count = 0;
		try{
			count = itemsService.addClient(client);			
		}catch(Exception e){
			
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, "rel14", true);
		else return this.ajaxForwardError(request, "数据格式错误！", true);
		
	}
	
	@RequestMapping(params = "method=updateClient")
	public String updateClient(Client client , HttpServletResponse res,HttpServletRequest request){			
		

		int count = 0;
		try{
			count = itemsService.updateClient(client);	
		}catch(Exception e){
			
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, "rel14", true);
		else return this.ajaxForwardError(request, "数据格式错误！", true);
	}

	@RequestMapping(params = "method=deleteClient")
	public String deleteClient( HttpServletResponse res,HttpServletRequest request){	

		String[] ids = request.getParameterValues("ids");
		if(ids == null || ids.length == 0)
			return this.ajaxForwardSuccess(request,  "rel14", true);	
		
		User sessionUser = PubMethod.getUser(request);
		Client[] clients = new Client[ids.length];
		int index = 0;
		for(String client_id : ids){
			Client client = new Client();
			client.setClient_id(client_id);
			client.setDelete_datetime(PubMethod.getCurrentDate());
			client.setDelete_userid(sessionUser.getUser_id());
			client.setDelete_username(sessionUser.getUser_name());
			clients[index] = client;
			index ++ ;
		}
		
		if(clients !=null && clients.length >0)
		itemsService.deleteClient(clients);
		return this.ajaxForwardSuccess(request,  "rel14", false);			
	}
	
	
	@RequestMapping(params = "method=isExistClient")
	public String isExistClient(Client client,HttpServletResponse res,HttpServletRequest request){

		StringBuilder error = new StringBuilder("");	
		Client search = new Client();
		search.setClient_id(client.getClient_id());
		search.setClient_name(client.getClient_name());
		boolean b = itemsService.isExistClient(client);
		if(b){
			error = error.append("  该客户名称重复!");
		}
		
		search.setClient_name(null);
		search.setClient_no(client.getClient_no());
		b = itemsService.isExistClient(client);
		if(b){
			error = error.append("  该客户编号重复!");
		}
		
		if(StringUtils.isEmpty(error.toString())){
			return this.ajaxForwardSuccess(request);
		}else {
			return this.ajaxForwardError(request, error.toString());
		}
	}
	

	@RequestMapping(params = "method=clientList")
	public String clientList(Client searchClient , HttpServletResponse res,HttpServletRequest request){
		
		searchClient.setDelete_flag(BusinessUtil.NOT_DELETEED);
		List<Client> list = itemsService.queryClient(searchClient);
		request.setAttribute("list", list);
		

		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.CLIENTADD.getId());
		request.setAttribute(EnumOperationType.INSERT.getKey(), userPermit1.getPermit_id());
		
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.CLIENTUPDATE.getId());
		request.setAttribute(EnumOperationType.UPDATE.getKey(), userPermit1.getPermit_id());	

		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.CLIENTDELETE.getId());
		request.setAttribute(EnumOperationType.DELETE.getKey(), userPermit1.getPermit_id());			

		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.CLIENTVIEW.getId());
		request.setAttribute(EnumOperationType.READ.getKey(), userPermit1.getPermit_id());		
		
		return "setting/client_list";
	}	
	
	

		
	
}
