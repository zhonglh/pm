package com.pm.action;

import java.util.HashMap;
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
import com.common.utils.IDKit;
import com.common.utils.file.FileKit;
import com.common.utils.file.download.DownloadBaseUtil;
import com.pm.domain.business.OtherStaff;
import com.pm.domain.business.StaffCost;
import com.pm.domain.system.Dept;
import com.pm.domain.system.User;
import com.pm.service.IDeptService;
import com.pm.service.IOtherStaffService;
import com.pm.service.IRoleService;
import com.pm.service.IStaffCostService;
import com.pm.util.Config;
import com.pm.util.PubMethod;
import com.pm.util.constant.BusinessUtil;
import com.pm.util.constant.EnumOperationType;
import com.pm.util.constant.EnumPermit;
import com.pm.util.constant.EnumStaffType;
import com.pm.util.excel.BusinessExcel;
import com.pm.util.excel.ExcelRead;
import com.pm.vo.UserPermit;



@Controller
@RequestMapping(value = "OtherStaffAction.do")
public class OtherStaffAction extends BaseAction{



	private static final String sessionAttr = "OtherStaffs";

	private static final String rel = "rel14";
	
	@Autowired
	private IOtherStaffService otherStaffService;

	@Autowired
	private IStaffCostService staffCostService;

	@Autowired
	private IDeptService deptService;
	
	@Autowired
	private IRoleService roleService;
	
	public static Map<String,String> position_type_Map = new HashMap<String,String>();
	
	static{
		position_type_Map.put("招聘", "1");
		position_type_Map.put("销售", "2");
		position_type_Map.put("售前", "3");
		position_type_Map.put("售后", "4");
		position_type_Map.put("项目管理", "5");
		position_type_Map.put("信息来源", "6");
		position_type_Map.put("行政", "7");
		position_type_Map.put("财务", "8");
		position_type_Map.put("领导", "9");
		position_type_Map.put("其他", "10");		
	}
	


	public String isExist(OtherStaff searchOtherStaff,HttpServletResponse res,HttpServletRequest request){

		String error = null;		
		boolean b = otherStaffService.isExist(searchOtherStaff);
		if(!b){
			return null;
		}else {
			error = "该人员(工号)已经存在";
			return this.ajaxForwardError(request, error);
		}		
	}
	
	
	

	/**
	 * 导出Excel(普通方式导出)
	 * @param searchStaffCost
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=export")
	public void export(OtherStaff searchOtherStaff,HttpServletResponse res,HttpServletRequest request){
		
		
		processparam(searchOtherStaff,request);
		
		searchOtherStaff.setDelete_flag(BusinessUtil.NOT_DELETEED);
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.OTHERSTAFFVIEW.getId());			
		Pager<OtherStaff> pager = otherStaffService.queryOtherStaff(searchOtherStaff, userPermit, PubMethod.getPagerByAll(OtherStaff.class));
		PubMethod.setRequestPager(request, pager, OtherStaff.class);
		
		try{
			
			for(OtherStaff  otherStaff : pager.getResultList()){
				otherStaff.setPosition_type_temp(this.getMsg("position.type." + (otherStaff.getPosition_type()==null?"":otherStaff.getPosition_type()), request));
								
			}
			BusinessExcel.export(res, null, pager.getResultList(), OtherStaff.class);
		}catch(Exception e){
			
		}
		
	}
	
	@RequestMapping(params = "method=toExcel")
	public String toExcel(HttpServletResponse res,HttpServletRequest request){
		return "basicdata/other_staff_upload";
	}
	


	@RequestMapping(params = "method=importResult")
	public String importResult(HttpServletResponse res,HttpServletRequest request) throws  Exception{
		List<OtherStaff> list = (List<OtherStaff>)request.getSession().getAttribute(sessionAttr);
		request.getSession().removeAttribute(sessionAttr);
		request.setAttribute("list", list);
		return "basicdata/other_staff_excel_list";
	}
	
	

	@RequestMapping(params = "method=downloadtemplet")
	public ModelAndView downloadtemplet(HttpServletRequest request,  HttpServletResponse response) throws Exception { 
		DownloadBaseUtil downloadBaseUtil = new DownloadBaseUtil();
		String sourceFile = this.getClass().getClassLoader().getResource("/templet/otherstaff.xlsx").getPath();		
		downloadBaseUtil.download(  sourceFile,  "行政人员模板.xlsx" ,response,false);  		
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
			if(row.length<2) return this.ajaxForwardError(request, "第"+(index+Config.startRow)+"行数据不全",true);
			index ++;
		}
		
		List<OtherStaff> otherStaffs = PubMethod.stringArray2List(list, OtherStaff.class);		
		
		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);
		List<StaffCost> allStaffs = staffCostService.getAllStaff();
		Map<String,String>  staffMap = new HashMap<String,String>();
		if(allStaffs!=null){
			for(StaffCost staffCost : allStaffs){
				staffMap.put(staffCost.getStaff_no(), staffCost.getStaff_no());
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
		
		for(OtherStaff otherStaff : otherStaffs){
			boolean b = checkOtherStaff(otherStaff,staffMap,deptMap);
		}

		

		User sessionUser = PubMethod.getUser(request);

		for(OtherStaff otherStaff : otherStaffs){
			if(otherStaff.getErrorInfo() == null || otherStaff.getErrorInfo().trim().isEmpty()){
				otherStaff.setStaff_id(IDKit.generateId());
				otherStaff.setBuild_datetime(PubMethod.getCurrentDate());
				otherStaff.setBuild_userid(sessionUser.getUser_id());
				otherStaff.setBuild_username(sessionUser.getUser_name());
				otherStaff.setDelete_flag(BusinessUtil.NOT_DELETEED);				
			}
		}		

		

		boolean isAllOK = true;
		index = 0;
		for(OtherStaff otherStaff : otherStaffs){
			if(otherStaff.getErrorInfo()==null || otherStaff.getErrorInfo().length() <= 0){
				try{
					otherStaffService.addOtherStaff(otherStaff);
					index ++;
				}catch(Exception e){
					otherStaff.setErrorInfo(e.getMessage());
					isAllOK = false;
				}
			}else {
				isAllOK = false;
			}
		}
		
		if(isAllOK){
			return this.ajaxForwardSuccess(request, rel, true);
		}else {		
			request.getSession().setAttribute(sessionAttr, otherStaffs);
			request.setAttribute("forwardUrl", request.getContextPath()+"/OtherStaffAction.do?method=importResult");
			return this.ajaxForwardError(request, "导入的信息中有些问题！ ");
		}
		
	}	
	
	

	private boolean checkOtherStaff(OtherStaff otherStaff,Map<String,String>  staffMap, Map<String,Dept>  deptMap){
		boolean b = true;
		

		if(otherStaff.getStaff_no() == null || otherStaff.getStaff_no().isEmpty()){
			otherStaff.setErrorInfo(otherStaff.getErrorInfo() + "工号不能为空;");
			b = false;
		}
		
		if(staffMap.containsKey(otherStaff.getStaff_no())){
			otherStaff.setErrorInfo(otherStaff.getErrorInfo() + "工号重复;");
			b = false;
		}else {
			staffMap.put(otherStaff.getStaff_no(), otherStaff.getStaff_no());
		}
		

		if(otherStaff.getStaff_name() == null || otherStaff.getStaff_name().isEmpty()){
			otherStaff.setErrorInfo(otherStaff.getErrorInfo() + "姓名不能为空;");
			b = false;
		}
		
		if(otherStaff.getDept_name() != null && otherStaff.getDept_name().length() > 0){
			Dept dept = deptMap.get(otherStaff.getDept_name());
			if(dept == null){
				otherStaff.setErrorInfo(otherStaff.getErrorInfo() + "部门名称错误;");
				b = false;
			}else {
				otherStaff.setDept_id(dept.getDept_id());
				otherStaff.setDept_name(dept.getDept_name());
			}
		}
		
		if(otherStaff.getPosition_type_temp() != null && otherStaff.getPosition_type_temp().length() >0){
			if(position_type_Map.containsKey(otherStaff.getPosition_type_temp())){
				otherStaff.setPosition_type(position_type_Map.get(otherStaff.getPosition_type_temp()));
			}else {
				otherStaff.setErrorInfo(otherStaff.getErrorInfo() + "职位类型错误;");
				b = false;
			}
		}
		
		
		return b;
	}	
	
	
	@RequestMapping(params = "method=lookup")
	public String lookup(OtherStaff searchOtherStaff,HttpServletResponse res,HttpServletRequest request){	

		processparam(searchOtherStaff,request);
		searchOtherStaff.setDelete_flag(BusinessUtil.NOT_DELETEED);
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.OTHERSTAFFVIEW.getId());
		Pager<OtherStaff> pager = otherStaffService.queryOtherStaff(searchOtherStaff, userPermit, PubMethod.getPager(request, OtherStaff.class));
		PubMethod.setRequestPager(request, pager, OtherStaff.class);
		request.setAttribute("staff_type", EnumStaffType.AdminStaff.name());
		request.setAttribute("staff", searchOtherStaff);
		return "basicdata/other_staff_search";		
	}
	
	/**
	 * 选择完后直接执行
	 * @param searchOtherStaff
	 * @param toDoUrl
	 * @param res
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=lookup1")
	public String lookup1(OtherStaff searchOtherStaff,String toDoUrl,String id,HttpServletResponse res,HttpServletRequest request){	

		processparam(searchOtherStaff,request);
		searchOtherStaff.setDelete_flag(BusinessUtil.NOT_DELETEED);
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.OTHERSTAFFVIEW.getId());
		Pager<OtherStaff> pager = otherStaffService.queryOtherStaff(searchOtherStaff, userPermit, PubMethod.getPager(request, OtherStaff.class));
		PubMethod.setRequestPager(request, pager, OtherStaff.class);
		request.setAttribute("staff_type", EnumStaffType.AdminStaff.name());
		request.setAttribute("staff", searchOtherStaff);
		request.setAttribute("toDoUrl", toDoUrl);
		request.setAttribute("id", id);
		return "basicdata/other_staff_search1";		
	}

	@RequestMapping(params = "method=list")
	public String list(OtherStaff searchOtherStaff,HttpServletResponse res,HttpServletRequest request){

		processparam(searchOtherStaff,request);
		
		searchOtherStaff.setDelete_flag(BusinessUtil.NOT_DELETEED);
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.OTHERSTAFFVIEW.getId());			
		Pager<OtherStaff> pager = otherStaffService.queryOtherStaff(searchOtherStaff, userPermit, PubMethod.getPager(request, OtherStaff.class));
		PubMethod.setRequestPager(request, pager, OtherStaff.class);

		request.setAttribute(EnumOperationType.READ.getKey(), userPermit.getPermit_id());

		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.OTHERSTAFFADD.getId());
		request.setAttribute(EnumOperationType.INSERT.getKey(), userPermit1.getPermit_id());
		
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.OTHERSTAFFUPDATE.getId());
		request.setAttribute(EnumOperationType.UPDATE.getKey(), userPermit1.getPermit_id());	

		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.OTHERSTAFFDELETE.getId());
		request.setAttribute(EnumOperationType.DELETE.getKey(), userPermit1.getPermit_id());
		
		request.setAttribute("staff", searchOtherStaff);
		
		return "basicdata/other_staff_list";
		
	}
	
	public void processparam(OtherStaff otherStaff,HttpServletRequest request){
		if(otherStaff.getDept_id() == null){
			otherStaff.setDept_id(request.getParameter("dept.dept_id"));
			otherStaff.setDept_name(request.getParameter("dept.dept_name"));
		}
	}
	

	@RequestMapping(params = "method=addOtherStaff")
	public String addOtherStaff(OtherStaff otherStaff,HttpServletResponse res,HttpServletRequest request){
		
		processparam(otherStaff,request);
		
		String isExist = isExist(otherStaff,res,request);
		if(isExist != null) return isExist;

		User sessionUser = PubMethod.getUser(request);
		if(otherStaff == null) otherStaff = new OtherStaff();
		otherStaff.setStaff_id(IDKit.generateId());
		

		otherStaff.setBuild_datetime(PubMethod.getCurrentDate());
		otherStaff.setBuild_userid(sessionUser.getUser_id());
		otherStaff.setBuild_username(sessionUser.getUser_name());
		otherStaff.setDelete_flag(BusinessUtil.NOT_DELETEED);		
		
		
		int count = 0;
		try{
			count = otherStaffService.addOtherStaff(otherStaff);	
		}catch(Exception e){
			
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "数据格式错误！", true);
		
	}
	

	@RequestMapping(params = "method=updateOtherStaff")
	public String updateOtherStaff(OtherStaff otherStaff,HttpServletResponse res,HttpServletRequest request){	

		processparam(otherStaff,request);
		String isExist = isExist(otherStaff,res,request);
		if(isExist != null) return isExist;
		
		int count = 0;
		try{
			count = otherStaffService.updateOtherStaff(otherStaff);			
		}catch(Exception e){
			
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "数据格式错误！", true);
		
	}


	@RequestMapping(params = "method=toEdit")
	public String toEdit(OtherStaff otherStaff,HttpServletResponse res,HttpServletRequest request){
		
		OtherStaff otherStaff1 = null;
		if(otherStaff != null && otherStaff.getStaff_id() !=null){
			otherStaff1 = otherStaffService.getOtherStaff(otherStaff.getStaff_id());
			request.setAttribute("next_operation", "updateOtherStaff");
		}else {
			request.setAttribute("next_operation", "addOtherStaff");
		}		
		
		if(otherStaff1 == null) otherStaff1 = new OtherStaff();
		
		request.setAttribute("otherStaff1", otherStaff1);		
		
		return "basicdata/other_staff_edit";
		
	}	
	


	@RequestMapping(params = "method=toView")
	public String toView(OtherStaff otherStaff,HttpServletResponse res,HttpServletRequest request){
		
		OtherStaff  otherStaff1 = otherStaffService.getOtherStaff(otherStaff.getStaff_id());		
		
		if(otherStaff1 == null) otherStaff1 = new OtherStaff();
		
		request.setAttribute("otherStaff1", otherStaff1);		
		
		return "basicdata/other_staff_view";
		
	}		
	

	@RequestMapping(params = "method=deleteOtherStaff")
	public String deleteOtherStaff(HttpServletResponse res,HttpServletRequest request){
		
		String[] otherStaff_ids = request.getParameterValues("ids");
		int size = otherStaff_ids.length;
		
		OtherStaff[] otherStaffArray = new OtherStaff[size];

		User sessionUser = PubMethod.getUser(request);
		for(int i=0;i<size;i++){
			OtherStaff otherStaff = new OtherStaff();
			otherStaff.setStaff_id(otherStaff_ids[i]);			
			otherStaff.setDelete_userid(sessionUser.getUser_id());
			otherStaff.setDelete_username(sessionUser.getUser_name());
			otherStaff.setDelete_datetime(PubMethod.getCurrentDate());			
			otherStaffArray[i] = otherStaff;
		}
		
		if(otherStaffArray != null && otherStaffArray.length >0)
		otherStaffService.deleteOtherStaff( otherStaffArray );

		return this.ajaxForwardSuccess(request, rel, false);		
		
	}
	
}
