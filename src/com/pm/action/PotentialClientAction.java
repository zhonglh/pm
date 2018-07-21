package com.pm.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.common.actions.BaseAction;
import com.common.beans.Pager;
import com.common.utils.DateKit;
import com.common.utils.IDKit;
import com.common.utils.file.download.DownloadBaseUtil;
import com.pm.domain.business.DicData;
import com.pm.domain.business.OtherStaff;
import com.pm.domain.business.PotentialClient;
import com.pm.domain.business.PotentialClientFollowup;
import com.pm.domain.system.User;
import com.pm.service.IDicDataService;
import com.pm.service.IOtherStaffService;
import com.pm.service.IPotentialClientFollowupService;
import com.pm.service.IPotentialClientService;
import com.pm.service.IRoleService;
import com.pm.util.PubMethod;
import com.pm.util.constant.BusinessUtil;
import com.pm.util.constant.EnumDicType;
import com.pm.util.constant.EnumOperationType;
import com.pm.util.constant.EnumPermit;
import com.pm.util.excel.BusinessExcel;
import com.pm.vo.UserPermit;


@Controller
@RequestMapping("PotentialClientAction.do")
public class PotentialClientAction extends BaseAction {

	private static final String sessionAttr = "PotentialClients";

	private static final String rel = "rel16";

	@Autowired
	private IOtherStaffService otherStaffService;
	
	@Autowired
	private IDicDataService dicDataService;
	
	@Autowired
	private IPotentialClientService potentialClientService;
	
	@Autowired
	private IPotentialClientFollowupService potentialClientFollowupService;



	@Autowired
	private IRoleService roleService;
	
	
	/**
	 * 获取跟进记录
	 * @param request
	 * @param project
	 * @param sessionUser
	 * @return
	 */
	private PotentialClientFollowup[] getFollowups(PotentialClient potentialClient,User sessionUser,HttpServletRequest request){

		String[] rowIndex = request.getParameterValues("index_followups_table");
		
		List<PotentialClientFollowup> list = new ArrayList<PotentialClientFollowup>();
		if(rowIndex != null && rowIndex.length >0){
			for(String index : rowIndex) {
				PotentialClientFollowup followup = new PotentialClientFollowup();

				String id = request.getParameter("items["+index+"]."+"id");
				String link_time = request.getParameter("items["+index+"]."+"link_time");
				if(link_time != null && link_time.length() > 0){
					followup.setLink_time(new Timestamp(DateKit.fmtStrToDate(link_time).getTime()));
				}
				String link_type = request.getParameter("items["+index+"]."+"link_type");
				String link_content = request.getParameter("items["+index+"]."+"link_content");
				String description = request.getParameter("items["+index+"]."+"description");
				followup.setId(id);
				followup.setLink_type(link_type);
				followup.setLink_content(link_content);
				followup.setDescription(description);
				followup.setPotential_client_id(potentialClient.getId());
				followup.setPotential_client_name(potentialClient.getClient_name());
				followup.setBuild_datetime(PubMethod.getCurrentDate());
				followup.setBuild_userid(sessionUser.getUser_id());
				followup.setBuild_username(sessionUser.getUser_name());		
				list.add(followup);
			}
		}
		
		if(list.isEmpty()) return null;
		
		return list.toArray(new PotentialClientFollowup[list.size()]);
	}
	

	@RequestMapping(params = "method=deleteFollowup")
	public String deleteFollowup(PotentialClientFollowup potentialClientFollowup,HttpServletResponse res,HttpServletRequest request){
			
		//potentialClientFollowup = potentialClientFollowupService.getPotentialClientFollowup(potentialClientFollowup.getId());	
		potentialClientFollowupService.deletePotentialClientFollowup(new PotentialClientFollowup[]{potentialClientFollowup});
		request.setAttribute("rownum", request.getParameter("rownum"));
		request.setAttribute("other", "followups_table");
		return this.ajaxForwardSuccess(request,rel,false);
	}
	
	

	@RequestMapping(params = "method=isExist")
	public String isExist(HttpServletResponse res,HttpServletRequest request){

		String error = null;
		PotentialClient potentialClient = new PotentialClient();
		potentialClient.setClient_no(request.getParameter("client_no"));
		potentialClient.setId(request.getParameter("id"));		
		
		boolean b = potentialClientService.isExist(potentialClient);
		if(!b){
			potentialClient.setClient_name(request.getParameter("client_name"));
			potentialClient.setClient_no(null);
			b = potentialClientService.isExist(potentialClient);
			if(b) error = "该客户名称已经存在";
		}else {
			error = "该客户编号已经存在";
		}
		
		
		if(!b){
			return this.ajaxForwardSuccess(request);
		}else {
			return this.ajaxForwardError(request, error);
		}
	}	


	@RequestMapping(params = "method=list")
	public String list(PotentialClient potentialClient,HttpServletResponse res,HttpServletRequest request){

		paramprocess(request,potentialClient);
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.POTENTIALCLIENTADD.getId());
		if(userPermit.getPermit_id() == null || userPermit.getPermit_id().isEmpty()){
			User sessionUser = PubMethod.getUser(request);
			if(sessionUser.getStaff_id() != null && sessionUser.getStaff_id().length() > 0)
				potentialClient.setSales_userid(sessionUser.getStaff_id());
			else {
				potentialClient.setSales_userid("X-");
			}
		}
		Pager<PotentialClient> pager = potentialClientService.queryPotentialClient(potentialClient,  PubMethod.getPager(request, PotentialClient.class));
		PubMethod.setRequestPager(request, pager,PotentialClient.class);

		request.setAttribute("potentialClient1", potentialClient);
		request.setAttribute(EnumOperationType.READ.getKey(), userPermit.getPermit_id());
		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.POTENTIALCLIENTADD.getId());
		request.setAttribute(EnumOperationType.INSERT.getKey(), userPermit1.getPermit_id());
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.POTENTIALCLIENTUPDATE.getId());
		request.setAttribute(EnumOperationType.UPDATE.getKey(), userPermit1.getPermit_id());
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.POTENTIALCLIENTDELETE.getId());
		request.setAttribute(EnumOperationType.DELETE.getKey(), userPermit1.getPermit_id());

		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.POTENTIALCLIENTDALLOT.getId());
		request.setAttribute(EnumOperationType.DALLOT.getKey(), userPermit1.getPermit_id());
		

		return "basicdata/potentialclient_list";
	}

	
	
	/**
	 * 分配给销售负责人
	 * @param searchPotentialClient
	 * @param res
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=dallotSales")
	public String dallotSales(PotentialClient searchPotentialClient,String staff_id,HttpServletResponse res,HttpServletRequest request){
		
		PotentialClient potentialClient = potentialClientService.getPotentialClient(searchPotentialClient.getId());
		OtherStaff otherStaff = otherStaffService.getOtherStaff(staff_id);
		if(otherStaff != null){
			potentialClient.setSales_userid(otherStaff.getStaff_id());
			potentialClient.setSales_username(otherStaff.getStaff_name());
		}
		
		int count = potentialClientService.doDallotSales(potentialClient);

		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "分配失败！", true);
	}


	@RequestMapping(params = "method=toEdit")
	public String toEdit(PotentialClient searchPotentialClient,HttpServletResponse res,HttpServletRequest request){
		PotentialClient potentialClient = null;
		boolean haveFollwup = false;

		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.POTENTIALCLIENTFOLLWUPADD.getId());
		request.setAttribute(EnumOperationType.INSERT.getKey(), userPermit1.getPermit_id());
		if(!haveFollwup) haveFollwup = (userPermit1.getPermit_id() != null && userPermit1.getPermit_id().length() > 0);
		
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.POTENTIALCLIENTFOLLWUPUPDATE.getId());
		request.setAttribute(EnumOperationType.UPDATE.getKey(), userPermit1.getPermit_id());
		if(!haveFollwup) haveFollwup = (userPermit1.getPermit_id() != null && userPermit1.getPermit_id().length() > 0);
		
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.POTENTIALCLIENTFOLLWUPDELETE.getId());
		request.setAttribute(EnumOperationType.DELETE.getKey(), userPermit1.getPermit_id());
		if(!haveFollwup) haveFollwup = (userPermit1.getPermit_id() != null && userPermit1.getPermit_id().length() > 0);
		
		
		
		if(searchPotentialClient != null && searchPotentialClient.getId()!=null){
			request.setAttribute("next_operation", "updatePotentialClient");	
			potentialClient = potentialClientService.getPotentialClient(searchPotentialClient.getId());	
			
			
			if(!haveFollwup && potentialClient.getSales_userid() != null && potentialClient.getSales_userid().length() >0){				
				return this.ajaxForwardError(request, "该潜在客户已经分配给销售，您不能够在修改了！", true);
			}		
			
			List<PotentialClientFollowup> potentialClientFollowups = potentialClientFollowupService.getPotentialClientFollowupByClient(potentialClient.getId());
			request.setAttribute("followups", potentialClientFollowups);
			
		}else {
			request.setAttribute("next_operation", "addPotentialClient");		
			User sessionUser = PubMethod.getUser(request);
			potentialClient = new PotentialClient();	
			potentialClient.setBuild_userid(sessionUser.getUser_id());
			potentialClient.setBuild_username(sessionUser.getUser_name());
			potentialClient.setBuild_datetime(PubMethod.getCurrentDate());
		}
		
		
		request.setAttribute("potentialClient1", potentialClient);
		return "basicdata/potentialclient_edit";
		
	}


	@RequestMapping(params = "method=toView")
	public String toView(PotentialClient searchPotentialClient,HttpServletResponse res,HttpServletRequest request){
		PotentialClient potentialClient = potentialClientService.getPotentialClient(searchPotentialClient.getId());
		request.setAttribute("potentialClient1", potentialClient);

		List<PotentialClientFollowup> potentialClientFollowups = potentialClientFollowupService.getPotentialClientFollowupByClient(potentialClient.getId());
		request.setAttribute("followups", potentialClientFollowups);
		
		return "basicdata/potentialclient_view";
	}


	@RequestMapping(params = "method=addPotentialClient")
	public String addPotentialClient(PotentialClient addPotentialClient,HttpServletResponse res,HttpServletRequest request){
		PotentialClient potentialClient = addPotentialClient;	

		paramprocess(request,potentialClient);
		
		User sessionUser = PubMethod.getUser(request);
		potentialClient.setId(IDKit.generateId());
		potentialClient.setBuild_datetime(PubMethod.getCurrentDate());
		potentialClient.setBuild_userid(sessionUser.getUser_id());
		potentialClient.setBuild_username(sessionUser.getUser_name());
		int count = 0;
		try{
			
			PotentialClientFollowup[] followups = getFollowups(potentialClient,sessionUser,request);
			count = potentialClientService.addPotentialClient(potentialClient);
			if(followups != null && followups.length >0){
				potentialClientFollowupService.addPotentialClientFollowup(followups);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "该单据已经存在！", true);
	}


	@RequestMapping(params = "method=updatePotentialClient")
	public String updatePotentialClient(PotentialClient updatePotentialClient,HttpServletResponse res,HttpServletRequest request){
		
		PotentialClient potentialClient = updatePotentialClient;	
		
		paramprocess(request,potentialClient);
		User sessionUser = PubMethod.getUser(request);
		
		int count = 0;
		try{
			count = potentialClientService.updatePotentialClient(potentialClient);	

			PotentialClientFollowup[] followups = getFollowups(potentialClient,sessionUser,request);
			if(followups != null) potentialClientFollowupService.updatePotentialClientFollowup(followups);
			
		}catch(Exception e){
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "该单据已经存在！", true);
	}	
	
	private void paramprocess(HttpServletRequest request,PotentialClient updatePotentialClient){
		
		updatePotentialClient.setProject_cycle(request.getParameter("pc.id"));
		updatePotentialClient.setProject_cycle_name(request.getParameter("pc.dic_data_name"));		

		updatePotentialClient.setClient_worth(request.getParameter("cw.id"));
		updatePotentialClient.setClient_worth_name(request.getParameter("cw.dic_data_name"));
		
		updatePotentialClient.setStatus(request.getParameter("sn.id"));
		updatePotentialClient.setStatus_name(request.getParameter("sn.dic_data_name"));
		
		
		updatePotentialClient.setSales_userid(request.getParameter("salesuser.user_id"));
		updatePotentialClient.setSales_username(request.getParameter("salesuser.user_name"));
		
		
	}



	@RequestMapping(params = "method=deletePotentialClient")
	public String deletePotentialClient(HttpServletResponse res,HttpServletRequest request){
		

		boolean haveFollwup = false;

		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.POTENTIALCLIENTFOLLWUPADD.getId());
		request.setAttribute(EnumOperationType.INSERT.getKey(), userPermit1.getPermit_id());
		if(!haveFollwup) haveFollwup = (userPermit1.getPermit_id() != null && userPermit1.getPermit_id().length() > 0);
		
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.POTENTIALCLIENTFOLLWUPUPDATE.getId());
		request.setAttribute(EnumOperationType.UPDATE.getKey(), userPermit1.getPermit_id());
		if(!haveFollwup) haveFollwup = (userPermit1.getPermit_id() != null && userPermit1.getPermit_id().length() > 0);
		
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.POTENTIALCLIENTFOLLWUPDELETE.getId());
		request.setAttribute(EnumOperationType.DELETE.getKey(), userPermit1.getPermit_id());
		if(!haveFollwup) haveFollwup = (userPermit1.getPermit_id() != null && userPermit1.getPermit_id().length() > 0);
		
		String[] ids = request.getParameterValues("ids");
		if(ids != null && ids.length > 0){
			PotentialClient[] potentialClients = new PotentialClient[ids.length];
			int index = 0;
			for(String id : ids){
				PotentialClient potentialClient = new PotentialClient();
				potentialClient.setId(id);
				potentialClients[index] = potentialClient;
				index ++ ;
				
				PotentialClient temp = potentialClientService.getPotentialClient(id);
				if(!haveFollwup && temp.getSales_userid() != null && temp.getSales_userid().length() >0){				
					return this.ajaxForwardError(request, "潜在客户("+temp.getClient_name()+")已经分配给销售，您不能够在修改了！", true);
				}	
			}
			if(potentialClients != null && potentialClients.length > 0)
			potentialClientService.deletePotentialClient(potentialClients);
		}
		return this.ajaxForwardSuccess(request,rel,false);
	}	


	@RequestMapping(params = "method=downloadtemplet")
	public ModelAndView downloadtemplet(HttpServletRequest request,  HttpServletResponse response) throws Exception { 
		DownloadBaseUtil downloadBaseUtil = new DownloadBaseUtil();
		String sourceFile = this.getClass().getClassLoader().getResource("/templet/potentialclient.xlsx").getPath();		
		downloadBaseUtil.download(  sourceFile,  "潜在客户模板.xlsx" ,response,false); 
		return null;  
	}  	


	@RequestMapping(params = "method=export")
	public void export(PotentialClient potentialClient,HttpServletResponse res,HttpServletRequest request){

		paramprocess(request,potentialClient);
		Pager<PotentialClient> pager = potentialClientService.queryPotentialClient(potentialClient, PubMethod.getPagerByAll(request, PotentialClient.class));
		try{
			BusinessExcel.export(res, null, pager.getResultList(), PotentialClient.class,false);
		}catch(Exception e){
			e.printStackTrace();
		}
	}	


	@RequestMapping(params = "method=toExcel")
	public String toExcel(HttpServletResponse res,HttpServletRequest request){
		return "basicdata/potentialclient_upload";		
	}	


	@RequestMapping(params = "method=doExcel")
	public String doExcel(@RequestParam("image") MultipartFile file,HttpServletResponse res,HttpServletRequest request) throws  Exception{
		List<String[]> list = getExcel(file,16,res,request);
		List<PotentialClient> potentialClients = PubMethod.stringArray2List(list, PotentialClient.class);
		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);	
		
		Map<String, Map<String, DicData>> allDicData = dicDataService.queryAllDicData();
		
		
		for(PotentialClient potentialClient : potentialClients){
			 checkPotentialClient(potentialClient,allDicData);
		}
		
		User sessionUser = PubMethod.getUser(request);
		boolean isAllOK = true;
		for(PotentialClient potentialClient : potentialClients){
			if(potentialClient.getErrorInfo()==null || potentialClient.getErrorInfo().length() <= 0){
				try{
					potentialClient.setId(IDKit.generateId());
					potentialClient.setBuild_datetime(PubMethod.getCurrentDate());
					potentialClient.setBuild_userid(sessionUser.getUser_id());
					potentialClient.setBuild_username(sessionUser.getUser_name());
					int count = potentialClientService.addPotentialClient(potentialClient);
					if(count == 0){
						potentialClient.setErrorInfo("已经有此记录");
						isAllOK = false;
					}
				}catch(Exception e){
					if(e.getMessage() == null || e.getMessage().indexOf("Key")!=-1 || e.getMessage().indexOf("key")!=-1) 
						potentialClient.setErrorInfo("已经有此记录");
					else potentialClient.setErrorInfo(e.getMessage());
					isAllOK = false;
				}
			}else {
				isAllOK = false;
			}
		}
		if(isAllOK){
			return this.ajaxForwardSuccess(request, rel, true);
		}else {		
			request.getSession().setAttribute(sessionAttr, potentialClients);
			request.setAttribute("forwardUrl", request.getContextPath()+"/PotentialClientAction.do?method=importResult");
			return this.ajaxForwardError(request, "导入的信息中有些问题！ ");
		}
	}	


	private boolean checkPotentialClient(PotentialClient potentialClient,Map<String, Map<String, DicData>> allDicData){
		boolean b = true;
		
		if(potentialClient.getClient_no() == null || potentialClient.getClient_no().isEmpty()){
			potentialClient.setErrorInfo(potentialClient.getErrorInfo() + "客户编号不能为空;");
			b = false;
		}else {
			PotentialClient temp = new PotentialClient();
			temp.setClient_no(potentialClient.getClient_no());
			boolean isExist = potentialClientService.isExist(temp);
			if(isExist) {
				potentialClient.setErrorInfo(potentialClient.getErrorInfo() + "该客户编号已经存在;");
				b = false;
			}
		}
		

		if(potentialClient.getClient_name() == null || potentialClient.getClient_name().isEmpty()){
			potentialClient.setErrorInfo(potentialClient.getErrorInfo() + "客户名称不能为空;");
			b = false;
		}else {
			PotentialClient temp = new PotentialClient();
			temp.setClient_name(potentialClient.getClient_name());
			boolean isExist = potentialClientService.isExist(temp);
			if(isExist) {
				potentialClient.setErrorInfo(potentialClient.getErrorInfo() + "该客户名称已经存在;");
				b = false;
			}
		}
		
		if(potentialClient.getProject_cycle_name() != null && potentialClient.getProject_cycle_name().length() >0){
			DicData dicData = PubMethod.getObj4Map(EnumDicType.PROJ_PERIOD.name(),potentialClient.getProject_cycle_name(),allDicData);
			if(dicData != null){
				potentialClient.setProject_cycle(dicData.getId());
			}else {
				potentialClient.setErrorInfo(potentialClient.getErrorInfo() + "项目周期错误;");
				b = false;
			}
		}
		
		if(potentialClient.getClient_worth_name() != null && potentialClient.getClient_worth_name().length() >0){
			DicData dicData = PubMethod.getObj4Map(EnumDicType.CLIENT_VAL.name(),potentialClient.getClient_worth_name(),allDicData);
			if(dicData != null){
				potentialClient.setClient_worth(dicData.getId());
			}else {
				potentialClient.setErrorInfo(potentialClient.getErrorInfo() + "客户价值错误;");
				b = false;
			}
		}
		if(potentialClient.getStatus_name() != null && potentialClient.getStatus_name().length() >0){
			DicData dicData = PubMethod.getObj4Map(EnumDicType.CLIENT_STATUS.name(),potentialClient.getStatus_name(),allDicData);
			if(dicData != null){
				potentialClient.setStatus(dicData.getId());
			}else {
				potentialClient.setErrorInfo(potentialClient.getErrorInfo() + "客户状态错误;");
				b = false;
			}
		}
		
		

		if(potentialClient.getErrorInfo() != null && !potentialClient.getErrorInfo().isEmpty())
			b = false;
		return b;
	}


	@RequestMapping(params = "method=importResult")
	public String importResult(HttpServletResponse res,HttpServletRequest request) throws  Exception{
		List<PotentialClient> list = (List<PotentialClient>)request.getSession().getAttribute(sessionAttr);
		request.getSession().removeAttribute(sessionAttr);
		request.setAttribute("list", list);
		return "basicdata/potentialclient_excel_list";
	}
	



}