
package com.pm.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.StringUtil;
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
import com.common.utils.file.FileKit;
import com.common.utils.file.download.DownloadBaseUtil;
import com.pm.domain.business.ApplyApprove;
import com.pm.domain.business.DepartCosts;
import com.pm.domain.business.DicData;
import com.pm.domain.business.OtherStaff;
import com.pm.domain.business.Project;
import com.pm.domain.system.Dept;
import com.pm.domain.system.User;
import com.pm.service.IApplyApproveService;
import com.pm.service.IDepartCostsService;
import com.pm.service.IDeptService;
import com.pm.service.IDicDataService;
import com.pm.service.IOtherStaffService;
import com.pm.service.IProjectService;
import com.pm.service.IRoleService;
import com.pm.util.Config;
import com.pm.util.PubMethod;
import com.pm.util.constant.BusinessUtil;
import com.pm.util.constant.EnumApplyApproveType;
import com.pm.util.constant.EnumDicType;
import com.pm.util.constant.EnumEntityType;
import com.pm.util.constant.EnumOperationType;
import com.pm.util.constant.EnumPermit;
import com.pm.util.excel.BusinessExcel;
import com.pm.util.excel.ExcelRead;
import com.pm.vo.UserPermit;

/**
 * @author zhonglihong
 * @date 2016年6月2日 下午8:39:28
 */
@Controller
@RequestMapping(value = "DepartCostsAction.do")
public class DepartCostsAction extends BaseAction {

	

	private static final String sessionAttr = "DepartCostss";

	private static final String rel = "rel62";
	
	@Autowired
	private IProjectService projectService;
	
	@Autowired
	private IDepartCostsService departCostsService;

	@Autowired
	private IApplyApproveService applyApproveService;	
	


	@Autowired
	IDeptService deptService;
	

	@Autowired
	private IDicDataService dicDataService;
	
	

	@Autowired
	private IOtherStaffService otherStaffService;
	
	@Autowired
	private IRoleService roleService;
	

	@RequestMapping(params = "method=list")
	public String list(DepartCosts departCosts,HttpServletResponse res,HttpServletRequest request){

		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.DEPARTCOSTVIEW.getId());

		paramprocess(request,departCosts);
		

		if(departCosts.getDept_id() == null || departCosts.getDept_id().isEmpty())
		departCosts.setDept_id(request.getParameter("dept.dept_id"));		
		
		if(departCosts.getDept_name() == null || departCosts.getDept_name().isEmpty())
		departCosts.setDept_name(request.getParameter("dept.dept_name"));	
		
		
		Pager<DepartCosts> pager = departCostsService.queryDepartCosts(departCosts, userPermit, PubMethod.getPager(request, DepartCosts.class));
		PubMethod.setRequestPager(request, pager,DepartCosts.class);	
		
		request.setAttribute("departCosts1", departCosts);

		request.setAttribute(EnumOperationType.READ.getKey(), userPermit.getPermit_id());		

		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.DEPARTCOSTADD.getId());
		request.setAttribute(EnumOperationType.INSERT.getKey(), userPermit1.getPermit_id());
		
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.DEPARTCOSTUPDATE.getId());
		request.setAttribute(EnumOperationType.UPDATE.getKey(), userPermit1.getPermit_id());	

		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.DEPARTCOSTDELETE.getId());
		request.setAttribute(EnumOperationType.DELETE.getKey(), userPermit1.getPermit_id());	
		

		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.DEPARTCOSTCHECK.getId());
		request.setAttribute(EnumOperationType.CHECK.getKey(), userPermit1.getPermit_id());
		
		return "departstatistics/depart_costs_list";
	}		
	

	private void paramprocess(HttpServletRequest request,DepartCosts departCosts){		
		departCosts.setProject_id(request.getParameter("project.project_id"));	
		
		if(departCosts.getProject_name() == null || departCosts.getProject_name().isEmpty() )
		departCosts.setProject_name(request.getParameter("project.project_name"));		
		departCosts.setProject_no(request.getParameter("project.project_no"));
		
		departCosts.setStaff_id(request.getParameter("staff.staff_id"));		
		if(departCosts.getStaff_name() == null || departCosts.getStaff_name().isEmpty() )
		departCosts.setStaff_name(request.getParameter("staff.staff_name"));		
		departCosts.setStaff_no(request.getParameter("staff.staff_no"));
		
		
	
		
		
		if(departCosts.getPay_item_id() == null || departCosts.getPay_item_id().isEmpty())
		departCosts.setPay_item_id(request.getParameter("dc.id"));
		if(departCosts.getPay_item_name() == null || departCosts.getPay_item_name().isEmpty())
		departCosts.setPay_item_name(request.getParameter("dc.dic_data_name"));
			
	}	
	
	

	@RequestMapping(params = "method=toExcel")
	public String toExcel(HttpServletResponse res,HttpServletRequest request){
		return "departstatistics/depart_costs_upload";		
	}	
	
	

	@RequestMapping(params = "method=downloadtemplet")
	public ModelAndView downloadtemplet(HttpServletRequest request,  HttpServletResponse response) throws Exception { 
		DownloadBaseUtil downloadBaseUtil = new DownloadBaseUtil();
		String sourceFile = this.getClass().getClassLoader().getResource("/templet/departcost.xlsx").getPath();		
		downloadBaseUtil.download(  sourceFile,  "部门费用模板.xlsx" ,response,false);  		
		return null;  
	}  	
	
	
	

	/**
	 * 导入Excel
	 * @param file
	 * @param res
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=doExcel")
	public String doExcel(@RequestParam("image") MultipartFile file,HttpServletResponse res,HttpServletRequest request) throws  Exception{
		
		
		List<String[]> list = null;
		String fileType = null;
		try{
			fileType = FileKit.getFileNameType(file.getOriginalFilename());
			if(!BusinessUtil.EXCEL_TYPE.contains(fileType)) 
				return this.ajaxForwardError(request, "请输入Excel文件！",true);
		}catch(Exception e){			
		}
		
		try{			
			list = ExcelRead.readExcel(file.getInputStream(), FileKit.isXlsx(fileType),  Config.startRow);
		}catch(Exception e){
			e.printStackTrace();
			return this.ajaxForwardError(request, "该文件无法解析！",true);
		}
		
		if(list == null || list.size() == 0) return this.ajaxForwardError(request, "该文件内容为空！",true);
		int index = 0;
		for(String[] row : list){
			if(row.length<7) return this.ajaxForwardError(request, "第"+(index+Config.startRow)+"行数据不全",true);
			index ++;
		}
		
		List<DepartCosts> departCostss = PubMethod.stringArray2List(list, DepartCosts.class);
		
		
		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);		


		

		Map<String, Map<String, DicData>> allDicData = dicDataService.queryAllDicData();
		

		Project searchProject = new Project();
		searchProject.setDelete_flag(BusinessUtil.NOT_DELETEED);
		Pager<Project> projects = projectService.queryProject(searchProject, userPermit, PubMethod.getPagerByAll(request, Project.class));
		Map<String,Project>  projectMap = new HashMap<String,Project>();
		if(projects.getResultList() != null){
			for(Project project : projects.getResultList()){
				projectMap.put(project.getProject_name(), project);		
				projectMap.put(project.getProject_no(), project);	
			}
		}
		
		Pager<Dept> depts = deptService.queryDept(new Dept(), userPermit, PubMethod.getPagerByAll(request, Dept.class));
		Map<String,Dept>  deptMap = new HashMap<String,Dept>();
		if(depts.getResultList() != null){
			for(Dept dept : depts.getResultList()){
				deptMap.put(dept.getDept_name(), dept);	
				deptMap.put(dept.getDept_no(), dept);		
			}
		}

		
		Pager<OtherStaff> staffCosts = otherStaffService.queryOtherStaff(new OtherStaff(), userPermit, PubMethod.getPagerByAll(OtherStaff.class));
		Map<String,OtherStaff>  staffCostMap = new HashMap<String,OtherStaff>();
		if(staffCosts.getResultList() != null) {
			for(OtherStaff staffCost : staffCosts.getResultList()){
				staffCostMap.put(staffCost.getStaff_name(), staffCost);	
				staffCostMap.put(staffCost.getStaff_no(), staffCost);		
			}
		}
		
		for(DepartCosts departCosts : departCostss){
			boolean b = checkDepartCosts(departCosts,allDicData,projectMap,deptMap,staffCostMap);
		}
		
		

		User sessionUser = PubMethod.getUser(request);
		
		boolean isAllOK = true;
		index = 0;
		for(DepartCosts departCosts : departCostss){
			if(departCosts.getErrorInfo()==null || departCosts.getErrorInfo().length() <= 0){
				try{
					departCosts.setImport_order(index);
					departCosts.setId(IDKit.generateId());

					departCosts.setBuild_datetime(PubMethod.getCurrentDate());
					departCosts.setBuild_userid(sessionUser.getUser_id());
					departCosts.setBuild_username(sessionUser.getUser_name());
					departCosts.setDelete_flag(BusinessUtil.NOT_DELETEED);
								
					
					departCostsService.addDepartCosts(departCosts);
					ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.BUILD.getKey(), EnumEntityType.DEPART_COSTS.name(), departCosts.getId(), sessionUser);
					applyApproveService.addApplyApprove(applyApprove);
					index ++;
				}catch(Exception e){
					departCosts.setErrorInfo(e.getMessage());
					isAllOK = false;
				}
			}else {
				isAllOK = false;
			}
		}
		
		if(isAllOK){
			return this.ajaxForwardSuccess(request, rel, true);
		}else {		
			request.getSession().setAttribute(sessionAttr, departCostss);
			request.setAttribute("forwardUrl", request.getContextPath()+"/DepartCostsAction.do?method=importResult");
			return this.ajaxForwardError(request, "导入的报销信息中有些问题！ ");
		}
		
	}	
	
	

	@RequestMapping(params = "method=importResult")
	public String importResult(HttpServletResponse res,HttpServletRequest request) throws  Exception{
		List<DepartCosts> list = (List<DepartCosts>)request.getSession().getAttribute(sessionAttr);
		request.getSession().removeAttribute(sessionAttr);
		request.setAttribute("list", list);
		return "departstatistics/depart_costs_excel_list";
	}

	private boolean checkDepartCosts(DepartCosts departCosts,
			Map<String, Map<String, DicData>> allDicData,
			Map<String,Project>  projectMap,
			Map<String,Dept>  deptMap,
			Map<String,OtherStaff>  staffCostMap){
		boolean b = true;
		

		String deptName = departCosts.getDept_name();
		
		Date date1 = null;
		if(departCosts.getStr_month() == null ||  departCosts.getStr_month().isEmpty()){
			departCosts.setErrorInfo(departCosts.getErrorInfo() + "报销月份不能为空;");
			b = false;
		}else {
			if(departCosts.getStr_month().length() != 6) {
				departCosts.setErrorInfo(departCosts.getErrorInfo() + "报销月份格式错误;");
				b = false;
			}else{
				date1 = DateKit.fmtStrToDate(departCosts.getStr_month()+"01","yyyyMMdd");
				if(date1 == null){
					departCosts.setErrorInfo(departCosts.getErrorInfo() + "报销月份格式错误;");
					b = false;
				}else {
					departCosts.setUse_month(Integer.parseInt(departCosts.getStr_month()));
				}
			}
		}
		

		if(departCosts.getPay_date() == null){
			departCosts.setPay_date(new Timestamp(date1.getTime()));
		}
		
		if(departCosts.getAmount() == 0){
			departCosts.setErrorInfo(departCosts.getErrorInfo() + "报销金额不能为空;");
			b = false;
		}
		
		if(departCosts.getProject_name() != null &&  !departCosts.getProject_name().isEmpty()) {
			Project project = projectMap.get(departCosts.getProject_name().trim());
			if(project == null ){
				departCosts.setErrorInfo(departCosts.getErrorInfo() + "项目名称错误;");
				b = false;
			}else {
				departCosts.setProject_id(project.getProject_id());
				departCosts.setProject_name(project.getProject_name());
				departCosts.setProject_no(project.getProject_no());
			}
		}else {
			departCosts.setProject_id("");
		}
		
		if(departCosts.getPay_item_name() == null ||  departCosts.getPay_item_name().isEmpty()){
			departCosts.setErrorInfo(departCosts.getErrorInfo() + "报销类别不能为空;");
			b = false;
		}else {			
			if(departCosts.getPay_item_name() != null && departCosts.getPay_item_name().length() >0){
				
				DicData dicData = PubMethod.getObj4Map(EnumDicType.DEPART_MANAG_COSTS.name(),departCosts.getPay_item_name(),allDicData);
				if(dicData == null ){
					dicData = PubMethod.getObj4Map(EnumDicType.SALES_COSTS.name(),departCosts.getPay_item_name(),allDicData);
				}
				
				if(dicData != null){
					departCosts.setPay_item_id(dicData.getId());
				}else {
					departCosts.setErrorInfo(departCosts.getErrorInfo() + "报销类别错误;");
					b = false;
				}
			}
		}
		

		
		if(departCosts.getStaff_name() != null && departCosts.getStaff_name().length() > 0){
			OtherStaff staffCost = staffCostMap.get(departCosts.getStaff_name());
			if(staffCost == null){
				departCosts.setStaff_id(departCosts.getStaff_name());
			}else{				
					departCosts.setStaff_id(staffCost.getStaff_id());
					departCosts.setStaff_name(staffCost.getStaff_name());
					departCosts.setStaff_no(staffCost.getStaff_no());
					departCosts.setDept_id(staffCost.getDept_id());
					departCosts.setDept_name(staffCost.getDept_name());				
			}
		}
		
		
		if(deptName != null && deptName.length() > 0){
			Dept dept = deptMap.get(deptName);
			if(dept == null){
				departCosts.setDept_name(deptName);
				departCosts.setErrorInfo(departCosts.getErrorInfo() + "所属部门错误;");
				b = false;
			}else{
					departCosts.setDept_id(dept.getDept_id());
					departCosts.setDept_name(dept.getDept_name());				
			}
		}else {		
			if(StringUtils.isEmpty(departCosts.getDept_id() ) ){
				departCosts.setErrorInfo(departCosts.getErrorInfo() + "所属部门没有设置;");
				b = false;
			}
		}
		
		
		
		
		if(departCosts.getErrorInfo() != null && !departCosts.getErrorInfo().isEmpty())
			b = false;
		
		return b;
	}
	
	
	/**
	 * 导出Excel(普通方式导出)
	 * @param searchStaffCost
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=export")
	public void export(DepartCosts departCosts,HttpServletResponse res,HttpServletRequest request){
		
		
		paramprocess(request,departCosts);
		
		if(departCosts.getDept_id() == null || departCosts.getDept_id().isEmpty())
		departCosts.setDept_id(request.getParameter("dept.dept_id"));		
		
		if(departCosts.getDept_name() == null || departCosts.getDept_name().isEmpty())
		departCosts.setDept_name(request.getParameter("dept.dept_name"));	
		
		
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.DEPARTCOSTVIEW.getId());		

		Pager<DepartCosts> pager = departCostsService.queryDepartCosts(departCosts, userPermit, PubMethod.getPagerByAll(request, DepartCosts.class));
				

		
		
		if(pager.getResultList() != null){
			for(DepartCosts temp : pager.getResultList()){
				temp.setStr_month(String.valueOf(temp.getUse_month()));
			}
		}		
		
		
		try{
			BusinessExcel.export(res, null, pager.getResultList(), DepartCosts.class,false);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}	
	

	@RequestMapping(params = "method=toEdit")
	public String toEdit(DepartCosts searchDepartCosts,HttpServletResponse res,HttpServletRequest request){
		DepartCosts departCosts = null;
		if(searchDepartCosts != null && searchDepartCosts.getId()!=null){
			request.setAttribute("next_operation", "updateDepartCosts");
			departCosts = departCostsService.getDepartCosts(searchDepartCosts.getId());	
			if(departCosts.getVerify_userid() != null && departCosts.getVerify_userid().length() > 0){
				return this.ajaxForwardError(request, "单据已经核实， 不能够再更改了！", true);
			}
			
			
		}else {
			request.setAttribute("next_operation", "addDepartCosts");
			
			User sessionUser = PubMethod.getUser(request);
			departCosts = new DepartCosts();	
			departCosts.setBuild_userid(sessionUser.getUser_id());
			departCosts.setBuild_username(sessionUser.getUser_name());
			departCosts.setBuild_datetime(PubMethod.getCurrentDate());

			String month = DateKit.fmtDateToStr(new Date(),"yyyyMM");
			departCosts.setUse_month(Integer.parseInt(month));
			
		}

		request.setAttribute("departCosts1", departCosts);
		return "departstatistics/depart_costs_edit";
		
	}
	

	@RequestMapping(params = "method=toView")
	public String toView(DepartCosts searchDepartCosts,HttpServletResponse res,HttpServletRequest request){
		
		DepartCosts departCosts = departCostsService.getDepartCosts(searchDepartCosts.getId());
		request.setAttribute("departCosts1", departCosts);

		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.DEPARTCOSTCHECK.getId());
		request.setAttribute(EnumOperationType.CHECK.getKey(), userPermit1.getPermit_id());

		
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.DEPARTCOSTUNCHECK.getId());
		request.setAttribute(EnumOperationType.UNCHECK.getKey(), userPermit1.getPermit_id());		

		User sessionUser = PubMethod.getUser(request);
		Project project = projectService.getProject( departCosts.getProject_id());
		List<ApplyApprove>  infos = applyApproveService.queryByDataId(EnumEntityType.DEPART_COSTS.name(), departCosts.getId());
		ApplyApprove applyApprove = applyApproveService.needHandle(EnumEntityType.DEPART_COSTS.name(),   departCosts.getId());
		
		Dept dept = null;
		if(StringUtils.isNotEmpty(departCosts.getDept_id())){
			dept = deptService.getDept(departCosts.getDept_id());
		}
		request.setAttribute("dept", dept);
		
		request.setAttribute("infos", infos);
		request.setAttribute("applyApprove", applyApprove);
		request.setAttribute("project", project);
		request.setAttribute("sessionUser", sessionUser);

		request.setAttribute("verify_userid", departCosts.getVerify_userid());
		request.setAttribute("data_id",  departCosts.getId());
		request.setAttribute("data_type", EnumEntityType.DEPART_COSTS.name());			
		
		return "departstatistics/depart_costs_view";
		
	}	
	

	@RequestMapping(params = "method=addDepartCosts")
	public String addDepartCosts(DepartCosts addDepartCosts,HttpServletResponse res,HttpServletRequest request){
		
		DepartCosts departCosts = addDepartCosts;	
		paramprocess(request,departCosts);
		

		if(departCosts.getDept_id() == null || departCosts.getDept_id().isEmpty())
		departCosts.setDept_id(request.getParameter("staff.dept_id"));		
		
		if(departCosts.getDept_name() == null || departCosts.getDept_name().isEmpty())
		departCosts.setDept_name(request.getParameter("staff.dept_name"));	
		
		if(StringUtils.isNotEmpty(departCosts.getStaff_id())){
			OtherStaff os = otherStaffService.getOtherStaff(departCosts.getStaff_id());
			if(!os.getStaff_name().equals(departCosts.getStaff_name())){
				departCosts.setStaff_id(departCosts.getStaff_name());
				departCosts.setStaff_no(null);
			}
		}else {
			departCosts.setStaff_id(departCosts.getStaff_name());
		}
		
		String month = DateKit.fmtDateToStr(new Date(),"yyyyMM");

		User sessionUser = PubMethod.getUser(request);
		departCosts.setId(IDKit.generateId());
		departCosts.setBuild_datetime(PubMethod.getCurrentDate());
		departCosts.setBuild_userid(sessionUser.getUser_id());
		departCosts.setBuild_username(sessionUser.getUser_name());
		departCosts.setDelete_flag(BusinessUtil.NOT_DELETEED);	
		

		int count = 0;
		try{
			count = departCostsService.addDepartCosts(departCosts);		

			ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.BUILD.getKey(), EnumEntityType.DEPART_COSTS.name(), departCosts.getId(), sessionUser);
			applyApproveService.addApplyApprove(applyApprove);
		}catch(Exception e){
			
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "数据格式错误！", true);
		
	}
	

	

	@RequestMapping(params = "method=updateDepartCosts")
	public String updateDepartCosts(DepartCosts updateDepartCosts,HttpServletResponse res,HttpServletRequest request){
		
		DepartCosts departCosts = updateDepartCosts;	
		paramprocess(request,departCosts);		

		if(departCosts.getDept_id() == null || departCosts.getDept_id().isEmpty())
		departCosts.setDept_id(request.getParameter("staff.dept_id"));		
		
		if(departCosts.getDept_name() == null || departCosts.getDept_name().isEmpty())
		departCosts.setDept_name(request.getParameter("staff.dept_name"));
		
		if(StringUtils.isNotEmpty(departCosts.getStaff_id())){
			OtherStaff os = otherStaffService.getOtherStaff(departCosts.getStaff_id());
			if(!os.getStaff_name().equals(departCosts.getStaff_name())){
				departCosts.setStaff_id(departCosts.getStaff_name());
				departCosts.setStaff_no(null);
			}
		}else {
			departCosts.setStaff_id(departCosts.getStaff_name());
		}
		
		int count = 0;
		try{
			count = departCostsService.updateDepartCosts(departCosts);			
		}catch(Exception e){
			
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "数据格式错误！", true);
		
	}	
	
	


	@RequestMapping(params = "method=batchVerifyDepartCosts")
	public String batchVerifyDepartCosts(HttpServletResponse res,HttpServletRequest request){

		User sessionUser = PubMethod.getUser(request);	
		
		String[] departCosts_ids = request.getParameterValues("ids");
		if(departCosts_ids == null || departCosts_ids.length ==0){
			return this.ajaxForwardError(request, "请先选择报销单！", false);
		}
		
		for(String departCosts_id : departCosts_ids){
			DepartCosts departCosts = new DepartCosts();
			departCosts.setId(departCosts_id);
			departCosts.setVerify_datetime(PubMethod.getCurrentDate());
			departCosts.setVerify_userid(sessionUser.getUser_id());
			departCosts.setVerify_username(sessionUser.getUser_name());
			departCostsService.verifyDepartCosts(departCosts);	
			ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.CHECK.getKey(), EnumEntityType.DEPART_COSTS.name(), departCosts.getId(), sessionUser);
			applyApproveService.addApplyApprove(applyApprove);
		}

		return this.ajaxForwardSuccess(request,rel,false);
		
	}

	@RequestMapping(params = "method=verifyDepartCosts")
	public String verifyDepartCosts(DepartCosts departCosts,HttpServletResponse res,HttpServletRequest request){
		User sessionUser = PubMethod.getUser(request);
		departCosts.setVerify_datetime(PubMethod.getCurrentDate());
		departCosts.setVerify_userid(sessionUser.getUser_id());
		departCosts.setVerify_username(sessionUser.getUser_name());
		departCostsService.verifyDepartCosts(departCosts);	
		ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.CHECK.getKey(), EnumEntityType.DEPART_COSTS.name(), departCosts.getId(), sessionUser);
		applyApproveService.addApplyApprove(applyApprove);
		return this.ajaxForwardSuccess(request, rel, true);
	}

	@RequestMapping(params = "method=deleteDepartCosts")
	public String deleteDepartCosts(HttpServletResponse res,HttpServletRequest request){
		
		User sessionUser = PubMethod.getUser(request);
		java.sql.Timestamp deleteDate = PubMethod.getCurrentDate();		
		
		String[] departCosts_ids = request.getParameterValues("ids");
		if(departCosts_ids != null && departCosts_ids.length > 0){
			DepartCosts[] departCostss = new DepartCosts[departCosts_ids.length];
			int index = 0;
			for(String departCosts_id : departCosts_ids){
				DepartCosts departCosts = new DepartCosts();
				departCosts.setId(departCosts_id);
				departCosts.setDelete_userid(sessionUser.getUser_id());
				departCosts.setDelete_username(sessionUser.getUser_name());
				departCosts.setDelete_datetime(deleteDate);
				departCostss[index] = departCosts;
				index ++ ;
			}
			
			if(departCostss != null && departCostss.length > 0)
			departCostsService.deleteDepartCosts(departCostss);
		}
		return this.ajaxForwardSuccess(request,rel,false);
	}		
	
	
	


}

