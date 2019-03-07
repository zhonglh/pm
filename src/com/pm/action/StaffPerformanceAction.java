package com.pm.action;

import com.common.actions.BaseAction;
import com.common.beans.Pager;
import com.common.utils.DateKit;
import com.common.utils.IDKit;
import com.common.utils.file.FileKit;
import com.common.utils.file.download.DownloadBaseUtil;
import com.pm.domain.business.*;
import com.pm.domain.system.User;
import com.pm.service.*;
import com.pm.util.Config;
import com.pm.util.PubMethod;
import com.pm.util.ThreadLocalUser;
import com.pm.util.constant.*;
import com.pm.util.excel.BusinessExcel;
import com.pm.util.excel.ExcelRead;
import com.pm.vo.UserPermit;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 人员绩效管理
 * @author zhonglh
 *
 */
@Controller
@RequestMapping(value = "StaffPerformanceAction.do")
public class StaffPerformanceAction extends BaseAction {
	
	


	private static final String sessionAttr = "StaffPerformances";

	private static final String rel = "rel05";
	

	@Autowired
	private IApplyApproveService applyApproveService;

	
	@Autowired
	private IRoleService roleService;
	

	@Autowired
	private IStaffPerformanceService staffPerformanceService;

	@Autowired
	private IOtherStaffService otherStaffService;
	



	@RequestMapping(params = "method=export")
	public void export(StaffPerformance staffPerformance,HttpServletResponse res,HttpServletRequest request){

		paramprocess(request,staffPerformance);

		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.STAFFPERFORMANCEVIEW.getId());
		
		Pager<StaffPerformance> pager = staffPerformanceService.queryStaffPerformance(staffPerformance, userPermit, PubMethod.getPagerByAll(request, StaffPerformance.class));

		
		try{
			BusinessExcel.export(res, null, pager.getResultList(), StaffPerformance.class,false);
		}catch(Exception e){
			e.printStackTrace();
		}	
		
	}	
	

	@RequestMapping(params = "method=toExcel")
	public String toExcel(HttpServletResponse res,HttpServletRequest request){
		return "headquarters/staff_performance_upload";	
	}	
	

	@RequestMapping(params = "method=downloadtemplet")
	public ModelAndView downloadtemplet(HttpServletRequest request,  HttpServletResponse response) throws Exception { 

		String sourceFile = this.getClass().getClassLoader().getResource("/templet/staffPerformance.xlsx").getPath();
		DownloadBaseUtil.download(  sourceFile,  "人员绩效模板.xlsx" ,response,false);
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
			if(!BusinessUtil.EXCEL_TYPE.contains(fileType)) {
				return this.ajaxForwardError(request, "请输入Excel文件！", true);
			}
		}catch(Exception e){
		}
		
		try{			
			list = ExcelRead.readExcel(file.getInputStream(), FileKit.isXlsx(fileType),  Config.startRow);
		}catch(Exception e){
			e.printStackTrace();
			return this.ajaxForwardError(request, "该文件无法解析！",true);
		}
		
		if(list == null || list.size() == 0) {
			return this.ajaxForwardError(request, "该文件内容为空！",true);
		}
		int index = 0;
		for(String[] row : list){
			if(row.length<4) {
				return this.ajaxForwardError(request, "第"+(index+Config.startRow)+"行数据不全",true);
			}
			index ++;
		}
		
		List<StaffPerformance> staffPerformances = PubMethod.stringArray2List(list, StaffPerformance.class);
		

		
		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);		

		
		
		OtherStaff searchOtherStaff = new OtherStaff();
		searchOtherStaff.setDelete_flag(BusinessUtil.NOT_DELETEED);		
		searchOtherStaff.setPosition_type(null);
		Pager<OtherStaff> pager = otherStaffService.queryOtherStaff(searchOtherStaff, userPermit, PubMethod.getPagerByAll(request, OtherStaff.class));
		Map<String,OtherStaff>  otherStaffMap = new HashMap<String,OtherStaff>();
		if(pager.getResultList() != null) {
			for(OtherStaff otherStaff : pager.getResultList()){
				otherStaffMap.put(otherStaff.getStaff_name(), otherStaff);
				otherStaffMap.put(otherStaff.getStaff_no(), otherStaff);
			}
		}
		
		for(StaffPerformance staffPerformance : staffPerformances){
			boolean b = checkStaffPerformance(staffPerformance,otherStaffMap);
		}
		
		

		User sessionUser = PubMethod.getUser(request);
		
		boolean isAllOK = true;
		for(StaffPerformance staffPerformance : staffPerformances){
			if(staffPerformance.getErrorInfo()==null || staffPerformance.getErrorInfo().length() <= 0){
				try{

					staffPerformance.setId(IDKit.generateId());
					staffPerformance.setBuild_datetime(PubMethod.getCurrentDate());
					staffPerformance.setBuild_userid(sessionUser.getUser_id());
					staffPerformance.setBuild_username(sessionUser.getUser_name());								
					
					int count = staffPerformanceService.addStaffPerformance(staffPerformance);
					
					if(count == 0){
						staffPerformance.setErrorInfo("数据重复");
						isAllOK = false;
						continue;
					}

					ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.BUILD.getKey(), EnumEntityType.STAFF_PERFORMANCE.name(), staffPerformance.getId(), sessionUser);
					applyApproveService.addApplyApprove(applyApprove);
					
				}catch(Exception e){
					if(e.getMessage() == null || e.getMessage().indexOf("Key")!=-1 || e.getMessage().indexOf("key")!=-1) {
						staffPerformance.setErrorInfo("数据重复");
					}else {
						staffPerformance.setErrorInfo(e.getMessage());
					}
					isAllOK = false;
				}
			}else {
				isAllOK = false;
			}
		}
		
		if(isAllOK){
			return this.ajaxForwardSuccess(request, rel, true);
		}else {		
			request.getSession().setAttribute(sessionAttr, staffPerformances);
			request.setAttribute("forwardUrl", request.getContextPath()+"/StaffPerformanceAction.do?method=importResult");
			return this.ajaxForwardError(request, "导入的人员绩效信息中有些问题！ ");
		}
		
	}	
	
	

	@RequestMapping(params = "method=importResult")
	public String importResult(HttpServletResponse res,HttpServletRequest request) throws  Exception{
		List<StaffPerformance> list = (List<StaffPerformance>)request.getSession().getAttribute(sessionAttr);
		request.getSession().removeAttribute(sessionAttr);
		request.setAttribute("list", list);
		return "headquarters/staff_performance_excel_list";
	}

	private boolean checkStaffPerformance(StaffPerformance staffPerformance,
			Map<String,OtherStaff>  otherStaffMap){
		
		boolean b =true;


		Date date1 = null;
		if(staffPerformance.getThe_month() == 0){
			staffPerformance.setErrorInfo(staffPerformance.getErrorInfo() + "月份不能为空;");
			b = false;
		}else {

			date1 = DateKit.fmtStrToDate(String.valueOf(staffPerformance.getThe_month())+"01","yyyyMMdd");
			if(date1 == null) {
				staffPerformance.setErrorInfo(staffPerformance.getErrorInfo() + "月份格式错误;");
				b = false;
			}

		}
		
		if(staffPerformance.getStaff_no() != null && staffPerformance.getStaff_no().length() > 0){
			OtherStaff otherStaff = otherStaffMap.get(staffPerformance.getStaff_no());
			if(otherStaff == null){
				staffPerformance.setErrorInfo(staffPerformance.getErrorInfo() + "人员工号错误;");
				b = false;
			}else {
				staffPerformance.setStaff_id(otherStaff.getStaff_id());
				staffPerformance.setStaff_name(otherStaff.getStaff_name());
				staffPerformance.setStaff_no(otherStaff.getStaff_no());
				staffPerformance.setDept_id(otherStaff.getDept_id());
				staffPerformance.setDept_name(otherStaff.getDept_name());
			}
		}

		
		
		if(staffPerformance.getErrorInfo() != null && !staffPerformance.getErrorInfo().isEmpty()) {
			b = false;
		}
		
		return b;
	}
	
	
	
	
	


	@RequestMapping(params = "method=list")
	public String list(StaffPerformance staffPerformance,HttpServletResponse res,HttpServletRequest request){

		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.STAFFPERFORMANCEVIEW.getId());

		paramprocess(request,staffPerformance);
		
		Pager<StaffPerformance> pager = staffPerformanceService.queryStaffPerformance(staffPerformance, userPermit, PubMethod.getPager(request, StaffPerformance.class));

		PubMethod.setRequestPager(request, pager,StaffPerformance.class);

		
		request.setAttribute("staffPerformance", staffPerformance);

		request.setAttribute(EnumOperationType.READ.getKey(), userPermit.getPermit_id());		

		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.STAFFPERFORMANCEADD.getId());
		request.setAttribute(EnumOperationType.INSERT.getKey(), userPermit1.getPermit_id());
		
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.STAFFPERFORMANCEUPDATE.getId());
		request.setAttribute(EnumOperationType.UPDATE.getKey(), userPermit1.getPermit_id());	

		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.STAFFPERFORMANCEDELETE.getId());
		request.setAttribute(EnumOperationType.DELETE.getKey(), userPermit1.getPermit_id());	
		

		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.STAFFPERFORMANCECHECK.getId());
		request.setAttribute(EnumOperationType.CHECK.getKey(), userPermit1.getPermit_id());
		
		return "headquarters/staff_performance_list";
	}		
	
	

	

	private void paramprocess(HttpServletRequest request,StaffPerformance staffPerformance){		
		

		staffPerformance.setDept_id(request.getParameter("dept.dept_id"));
		staffPerformance.setDept_name(request.getParameter("dept.dept_name"));
		

		staffPerformance.setStaff_id(request.getParameter("staff.staff_id"));
        staffPerformance.setStaff_no(request.getParameter("staff.staff_no"));
        staffPerformance.setStaff_name(request.getParameter("staff.staff_name"));
	}	
	
	

	@RequestMapping(params = "method=toEdit")
	public String toEdit(StaffPerformance searchStaffPerformance,HttpServletResponse res,HttpServletRequest request){
		StaffPerformance staffPerformance = null;
		if(searchStaffPerformance != null && searchStaffPerformance.getId()!=null){
			request.setAttribute("next_operation", "updateStaffPerformance");
			staffPerformance = staffPerformanceService.getStaffPerformance(searchStaffPerformance.getId());	
			if(staffPerformance.getVerify_userid() != null && staffPerformance.getVerify_userid().length() > 0){
				return this.ajaxForwardError(request, "单据已经核实， 不能够再更改了！", true);
			}			
			
		}else {
			request.setAttribute("next_operation", "addStaffPerformance");			
			User sessionUser = PubMethod.getUser(request);
			staffPerformance = new StaffPerformance();
			staffPerformance.setBuild_userid(sessionUser.getUser_id());
			staffPerformance.setBuild_username(sessionUser.getUser_name());
			staffPerformance.setBuild_datetime(PubMethod.getCurrentDate());
			
		}

		request.setAttribute("staffPerformance1", staffPerformance);
		return "headquarters/staff_performance_edit";
		
	}
	

	@RequestMapping(params = "method=toView")
	public String toView(StaffPerformance searchStaffPerformance,HttpServletResponse res,HttpServletRequest request){
		
		StaffPerformance staffPerformance = staffPerformanceService.getStaffPerformance(searchStaffPerformance.getId());

		
		request.setAttribute("staffPerformance1", staffPerformance);


		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.STAFFPERFORMANCECHECK.getId());
		request.setAttribute(EnumOperationType.CHECK.getKey(), userPermit1.getPermit_id());
		

		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.STAFFPERFORMANCEUNCHECK.getId());
		request.setAttribute(EnumOperationType.UNCHECK.getKey(), userPermit1.getPermit_id());		

		User sessionUser = PubMethod.getUser(request);
		List<ApplyApprove>  infos = applyApproveService.queryByDataId(EnumEntityType.STAFF_PERFORMANCE.name(), staffPerformance.getId());
		ApplyApprove applyApprove = applyApproveService.needHandle(EnumEntityType.STAFF_PERFORMANCE.name(), staffPerformance.getId());
		
		request.setAttribute("infos", infos);
		request.setAttribute("applyApprove", applyApprove);
		request.setAttribute("sessionUser", sessionUser);

		request.setAttribute("verify_userid", staffPerformance.getVerify_userid());
		request.setAttribute("data_id", staffPerformance.getId());
		request.setAttribute("data_type", EnumEntityType.STAFF_PERFORMANCE.name());
		
		return "headquarters/staff_performance_view";
		
	}	
	

	@RequestMapping(params = "method=addStaffPerformance")
	public String addStaffPerformance(StaffPerformance addStaffPerformance,HttpServletResponse res,HttpServletRequest request){
		
		StaffPerformance staffPerformance = addStaffPerformance;	
		paramprocess(request,staffPerformance);

		User sessionUser = PubMethod.getUser(request);
		staffPerformance.setId(IDKit.generateId());
		staffPerformance.setBuild_datetime(PubMethod.getCurrentDate());
		staffPerformance.setBuild_userid(sessionUser.getUser_id());
		staffPerformance.setBuild_username(sessionUser.getUser_name());	
		
		
		int count = 0;
		try{
			ThreadLocalUser.setUser(sessionUser);
			count = staffPerformanceService.addStaffPerformance(staffPerformance);		
			ThreadLocalUser.setUser(null);
			ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.BUILD.getKey(), EnumEntityType.STAFF_PERFORMANCE.name(), staffPerformance.getId(), sessionUser);
			applyApproveService.addApplyApprove(applyApprove);
		}catch(Exception e){
			ThreadLocalUser.setUser(null);
		}
		if(count == 1) 		{
		    return this.ajaxForwardSuccess(request, rel, true);
        }
		else {
		    return this.ajaxForwardError(request, "该单据已经存在！", true);
        }
		
	}
	

	

	@RequestMapping(params = "method=updateStaffPerformance")
	public String updateStaffPerformance(StaffPerformance updateStaffPerformance,HttpServletResponse res,HttpServletRequest request){
		
		StaffPerformance staffPerformance = updateStaffPerformance;	
		paramprocess(request,staffPerformance);		

		User sessionUser = PubMethod.getUser(request);

		int count = 0;
		try{
			ThreadLocalUser.setUser(sessionUser);
			count = staffPerformanceService.updateStaffPerformance(staffPerformance);			
			ThreadLocalUser.setUser(null);	
		}catch(Exception e){
			ThreadLocalUser.setUser(null);
		}
		if(count == 1) 		{
		    return this.ajaxForwardSuccess(request, rel, true);
        }
		else {
		    return this.ajaxForwardError(request, "该单据已经存在！", true);
        }
		
	}	
	
	

	@RequestMapping(params = "method=batchVerifyStaffPerformance")
	public String batchVerifyStaffPerformance(HttpServletResponse res,HttpServletRequest request){

		String[] ids = request.getParameterValues("ids");
		if(ids == null || ids.length == 0){
			return this.ajaxForwardError(request, "请先选择单据！", false);
		}
		
		User sessionUser = PubMethod.getUser(request);

		try{
			ThreadLocalUser.setUser(sessionUser);
			for(String id : ids){
				StaffPerformance staffPerformance = staffPerformanceService.getStaffPerformance(id);
				staffPerformance.setVerify_datetime(PubMethod.getCurrentDate());
				staffPerformance.setVerify_userid(sessionUser.getUser_id());
				staffPerformance.setVerify_username(sessionUser.getUser_name());
				staffPerformanceService.verifyStaffPerformance(staffPerformance);
	
				ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.CHECK.getKey(), EnumEntityType.STAFF_PERFORMANCE.name(), staffPerformance.getId(), sessionUser);
				applyApproveService.addApplyApprove(applyApprove);
			}
			ThreadLocalUser.setUser(null);
		}catch(Exception e){
			ThreadLocalUser.setUser(null);
			throw e;
		}
		return this.ajaxForwardSuccess(request, rel, false);
	}

	@RequestMapping(params = "method=verifyStaffPerformance")
	public String verifyStaffPerformance(StaffPerformance staffPerformance,HttpServletResponse res,HttpServletRequest request){
		staffPerformance = staffPerformanceService.getStaffPerformance(staffPerformance.getId());
		User sessionUser = PubMethod.getUser(request);
		staffPerformance.setVerify_datetime(PubMethod.getCurrentDate());
		staffPerformance.setVerify_userid(sessionUser.getUser_id());
		staffPerformance.setVerify_username(sessionUser.getUser_name());
		
		try{
			ThreadLocalUser.setUser(sessionUser);
			staffPerformanceService.verifyStaffPerformance(staffPerformance);
			ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.CHECK.getKey(), EnumEntityType.STAFF_PERFORMANCE.name(), staffPerformance.getId(), sessionUser);
			applyApproveService.addApplyApprove(applyApprove);
			ThreadLocalUser.setUser(null);
		}catch(Exception e){
			ThreadLocalUser.setUser(null);
		}
		return this.ajaxForwardSuccess(request, rel, true);
	}

	@RequestMapping(params = "method=deleteStaffPerformance")
	public String deleteStaffPerformance(HttpServletResponse res,HttpServletRequest request){
		
		
		String[] ids = request.getParameterValues("ids");
		if(ids != null && ids.length > 0){
			StaffPerformance[] staffPerformances = new StaffPerformance[ids.length];
			int index = 0;
			for(String id : ids){
				StaffPerformance staffPerformance = new StaffPerformance();
				staffPerformance.setId(id);
				staffPerformances[index] = staffPerformance;
				index ++ ;
			}
			
			if(staffPerformances != null && staffPerformances.length > 0) {
                staffPerformanceService.deleteStaffPerformance(staffPerformances);
            }
		}
		return this.ajaxForwardSuccess(request,rel,false);
	}		
	
	
}
