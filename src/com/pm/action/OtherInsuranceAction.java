package com.pm.action;

import com.common.actions.BaseAction;
import com.common.beans.Pager;
import com.common.utils.DateKit;
import com.common.utils.IDKit;
import com.common.utils.file.download.DownloadBaseUtil;
import com.pm.domain.business.ApplyApprove;
import com.pm.domain.business.Insurance;
import com.pm.domain.business.OtherStaff;
import com.pm.domain.system.User;
import com.pm.service.IApplyApproveService;
import com.pm.service.IInsuranceService;
import com.pm.service.IRoleService;
import com.pm.service.IOtherStaffService;
import com.pm.util.PubMethod;
import com.pm.util.constant.*;
import com.pm.util.excel.exports.BusinessExcel;
import com.pm.vo.UserPermit;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 总部保险
 * @author zhonglh
 */
@Controller
@RequestMapping("OtherInsuranceAction.do")
public class OtherInsuranceAction extends BaseAction {

	private static final String sessionAttr = "Insurances";

	private static final String rel = "rel07";

	@Autowired
	private IOtherStaffService otherStaffService;

	@Autowired
	@Qualifier("otherInsuranceService")
	private IInsuranceService otherInsuranceService;


	@Autowired
	private IApplyApproveService applyApproveService;	


	@Autowired
	private IRoleService roleService;


	@RequestMapping(params = "method=list")
	public String list(Insurance insurance,HttpServletResponse res,HttpServletRequest request){

		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.OTHER_INSURANCEVIEW.getId());

		

		Pager<Insurance> pager = otherInsuranceService.queryInsurance(insurance, userPermit, PubMethod.getPager(request, Insurance.class));
		PubMethod.setRequestPager(request, pager,Insurance.class);	

		request.setAttribute("insurance", insurance);
		request.setAttribute(EnumOperationType.READ.getKey(), userPermit.getPermit_id());	
		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.OTHER_INSURANCEADD.getId());
		request.setAttribute(EnumOperationType.INSERT.getKey(), userPermit1.getPermit_id());
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.OTHER_INSURANCEUPDATE.getId());
		request.setAttribute(EnumOperationType.UPDATE.getKey(), userPermit1.getPermit_id());
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.OTHER_INSURANCEDELETE.getId());
		request.setAttribute(EnumOperationType.DELETE.getKey(), userPermit1.getPermit_id());
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.OTHER_INSURANCECHECK.getId());
		request.setAttribute(EnumOperationType.CHECK.getKey(), userPermit1.getPermit_id());

		return "insurance/insurance_list";
	}


	private void paramprocess(HttpServletRequest request,Insurance insurance){
		insurance.setStaff_id(request.getParameter("staff.staff_id"));
		insurance.setStaff_no(request.getParameter("staff.staff_no"));
		insurance.setStaff_name(request.getParameter("staff.staff_name"));
	}


	@RequestMapping(params = "method=toEdit")
	public String toEdit(Insurance searchInsurance,HttpServletResponse res,HttpServletRequest request){
		Insurance insurance = null;
		if(searchInsurance != null && searchInsurance.getId()!=null){
			request.setAttribute("next_operation", "updateInsurance");
			insurance = otherInsuranceService.getInsurance(searchInsurance.getId());	
			if(insurance.getVerify_userid() != null && insurance.getVerify_userid().length() > 0){
				return this.ajaxForwardError(request, "单据已经核实， 不能够再更改了！", true);
			}
		}else {
			
			String salary_month = DateKit.fmtDateToStr(DateKit.addMonth(new java.util.Date(), -1),"yyyyMM");			
			request.setAttribute("next_operation", "addInsurance");		
			User sessionUser = PubMethod.getUser(request);
			insurance = new Insurance();	
			insurance.setBuild_userid(sessionUser.getUser_id());
			insurance.setBuild_username(sessionUser.getUser_name());
			insurance.setBuild_datetime(PubMethod.getCurrentDate());
			insurance.setSalary_month(Integer.parseInt(salary_month));
		}
		request.setAttribute("insurance1", insurance);
		return "insurance/insurance_edit";
	}


	@RequestMapping(params = "method=toView")
	public String toView(Insurance searchInsurance,HttpServletResponse res,HttpServletRequest request){
		Insurance insurance = otherInsuranceService.getInsurance(searchInsurance.getId());
		request.setAttribute("insurance1", insurance);
		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.OTHER_INSURANCECHECK.getId());
		request.setAttribute(EnumOperationType.CHECK.getKey(), userPermit1.getPermit_id());
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.OTHER_INSURANCEUNCHECK.getId());
		request.setAttribute(EnumOperationType.UNCHECK.getKey(), userPermit1.getPermit_id());
		User sessionUser = PubMethod.getUser(request);
		List<ApplyApprove>  infos = applyApproveService.queryByDataId(EnumEntityType.OTHER_INSURANCE.name(), insurance.getId());
		ApplyApprove applyApprove = applyApproveService.needHandle(EnumEntityType.OTHER_INSURANCE.name(),  insurance.getId());
		request.setAttribute("infos", infos);
		request.setAttribute("applyApprove", applyApprove);
		request.setAttribute("sessionUser", sessionUser);
		request.setAttribute("verify_userid", insurance.getVerify_userid());
		request.setAttribute("data_id", insurance.getId());
		request.setAttribute("data_type", EnumEntityType.OTHER_INSURANCE.name());
		return "insurance/insurance_view";
	}


	@RequestMapping(params = "method=addInsurance")
	public String addInsurance(Insurance addInsurance,HttpServletResponse res,HttpServletRequest request){
		Insurance insurance = addInsurance;	
		paramprocess(request,insurance);
		User sessionUser = PubMethod.getUser(request);
		insurance.setId(IDKit.generateId());
		insurance.setBuild_datetime(PubMethod.getCurrentDate());
		insurance.setBuild_userid(sessionUser.getUser_id());
		insurance.setBuild_username(sessionUser.getUser_name());
		int count = 0;
		try{
			count = otherInsuranceService.addInsurance(insurance);
			ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.BUILD.getKey(), EnumEntityType.OTHER_INSURANCE.name(), insurance.getId(), sessionUser);
			applyApproveService.addApplyApprove(applyApprove);
		}catch(Exception e){
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "该单据已经存在！", true);
	}


	@RequestMapping(params = "method=updateInsurance")
	public String updateInsurance(Insurance updateInsurance,HttpServletResponse res,HttpServletRequest request){
		Insurance insurance = updateInsurance;	
		paramprocess(request,insurance);	
		int count = 0;
		try{
			count = otherInsuranceService.updateInsurance(insurance);	
		}catch(Exception e){
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "该单据已经存在！", true);
	}	


	@RequestMapping(params = "method=verifyInsurance")
	public String verifyInsurance(Insurance insurance,HttpServletResponse res,HttpServletRequest request){
		User sessionUser = PubMethod.getUser(request);
		insurance = this.otherInsuranceService.getInsurance(insurance.getId());
		insurance.setVerify_datetime(PubMethod.getCurrentDate());
		insurance.setVerify_userid(sessionUser.getUser_id());
		insurance.setVerify_username(sessionUser.getUser_name());
		otherInsuranceService.verifyInsurance(insurance);
		ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.CHECK.getKey(), EnumEntityType.OTHER_INSURANCE.name(), insurance.getId(), sessionUser);
		applyApproveService.addApplyApprove(applyApprove);
		return this.ajaxForwardSuccess(request, rel, true);
	}


	@RequestMapping(params = "method=batchVerifyInsurance")
	public String batchVerifyInsurance(HttpServletResponse res,HttpServletRequest request){
		User sessionUser = PubMethod.getUser(request);
		String[] ids = request.getParameterValues("ids");
		if(ids == null || ids.length == 0){
			this.ajaxForwardError(request, "请先选择单据！", false);
		}
		for(String id : ids){
			Insurance insurance = this.otherInsuranceService.getInsurance(id);
			insurance.setVerify_datetime(PubMethod.getCurrentDate());
			insurance.setVerify_userid(sessionUser.getUser_id());
			insurance.setVerify_username(sessionUser.getUser_name());
			insurance.setId(id);
			otherInsuranceService.verifyInsurance(insurance);
			ApplyApprove applyApprove = applyApproveService.buildApplyApprove(EnumApplyApproveType.CHECK.getKey(), EnumEntityType.OTHER_INSURANCE.name(), insurance.getId(), sessionUser);
			applyApproveService.addApplyApprove(applyApprove);
		}
		return this.ajaxForwardSuccess(request, rel, false);
	}


	@RequestMapping(params = "method=deleteInsurance")
	public String deleteInsurance(HttpServletResponse res,HttpServletRequest request){
		User sessionUser = PubMethod.getUser(request);
		java.sql.Timestamp deleteDate = PubMethod.getCurrentDate();
		String[] ids = request.getParameterValues("ids");
		if(ids != null && ids.length > 0){
			Insurance[] insurances = new Insurance[ids.length];
			int index = 0;
			for(String id : ids){
				Insurance insurance = new Insurance();
				insurance.setId(id);
				insurances[index] = insurance;
				index ++ ;
			}
			if(insurances != null && insurances.length > 0)
			otherInsuranceService.deleteInsurance(insurances);
		}
		return this.ajaxForwardSuccess(request,rel,false);
	}	


	@RequestMapping(params = "method=downloadtemplet")
	public ModelAndView downloadtemplet(HttpServletRequest request,  HttpServletResponse response) throws Exception {
		String sourceFile = this.getClass().getClassLoader().getResource("/templet/insurance.xlsx").getPath();
		DownloadBaseUtil.download(  sourceFile,  "总部保险.xlsx" ,response,false);
		return null;  
	}  	


	@RequestMapping(params = "method=export")
	public void export(Insurance insurance,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.OTHER_INSURANCEVIEW.getId());		
		Pager<Insurance> pager = otherInsuranceService.queryInsurance(insurance, userPermit, PubMethod.getPagerByAll(request, Insurance.class));
		try{
			BusinessExcel.export(res, null, pager.getResultList(), Insurance.class,false);
		}catch(Exception e){
			e.printStackTrace();
		}
	}	

	
	
	@RequestMapping(params = "method=toExcel")
	public String toExcel(HttpServletResponse res,HttpServletRequest request){
		String the_month = DateKit.fmtDateToStr(DateKit.addMonth(new java.util.Date(), -1),"yyyyMM");
		request.setAttribute("salary_month", the_month);
		return "insurance/insurance_upload";		
	}	


	@RequestMapping(params = "method=doExcel")
	public String doExcel(@RequestParam("image") MultipartFile file,String salary_month,HttpServletResponse res,HttpServletRequest request) throws  Exception{
		List<String[]> list = getExcel(file,10,res,request);
		List<Insurance> insurances = PubMethod.stringArray2List(list, Insurance.class);
		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);	
		
		int ths_month = Integer.parseInt(salary_month);


		Pager<OtherStaff> OtherStaffs = otherStaffService.queryOtherStaff(new OtherStaff(),  userPermit, PubMethod.getPagerByAll(OtherStaff.class));
		Map<String,OtherStaff>  OtherStaffNoMap = new HashMap<String,OtherStaff>();
		Map<String,List<OtherStaff>>  OtherStaffNameMap = new HashMap<String,List<OtherStaff>>();
		if(OtherStaffs.getResultList() != null) {
			for(OtherStaff OtherStaff : OtherStaffs.getResultList()){
				OtherStaffNoMap.put(OtherStaff.getStaff_no(), OtherStaff);	
				if(OtherStaffNameMap.containsKey(OtherStaff.getStaff_name())){
					List<OtherStaff> scs = OtherStaffNameMap.get(OtherStaff.getStaff_name());
					scs.add(OtherStaff);
				}else {
					List<OtherStaff> scs = new ArrayList<OtherStaff>();
					scs.add(OtherStaff);
					OtherStaffNameMap.put(OtherStaff.getStaff_name(), scs);	
				}
			}
		}
		
		
		for(Insurance insurance : insurances){
			insurance.setSalary_month(ths_month);
			checkInsurance(insurance,OtherStaffNoMap,OtherStaffNameMap);
		}
		User sessionUser = PubMethod.getUser(request);
		boolean isAllOK = true;
		for(Insurance insurance : insurances){
			if(insurance.getErrorInfo()==null || insurance.getErrorInfo().length() <= 0){
				try{
					insurance.setId(IDKit.generateId());
					insurance.setBuild_datetime(PubMethod.getCurrentDate());
					insurance.setBuild_userid(sessionUser.getUser_id());
					insurance.setBuild_username(sessionUser.getUser_name());
					int count = otherInsuranceService.addInsurance(insurance);
					if(count == 0){
						insurance.setErrorInfo("已经有此记录");
						isAllOK = false;
					}
				}catch(Exception e){
					if(e.getMessage() == null || e.getMessage().indexOf("Key")!=-1 || e.getMessage().indexOf("key")!=-1) 
						insurance.setErrorInfo("已经有此记录");
					else insurance.setErrorInfo(e.getMessage());
					isAllOK = false;
				}
			}else {
				isAllOK = false;
			}
		}
		if(isAllOK){
			return this.ajaxForwardSuccess(request, rel, true);
		}else {		
			request.getSession().setAttribute(sessionAttr, insurances);
			request.setAttribute("forwardUrl", request.getContextPath()+"/InsuranceAction.do?method=importResult");
			return this.ajaxForwardError(request, "导入的信息中有些问题！ ");
		}
	}	


	private boolean checkInsurance(Insurance insurance,	
			Map<String,OtherStaff>  OtherStaffNoMap , 
			Map<String,List<OtherStaff>>  OtherStaffNameMap){
		boolean b = true;
		
		
		OtherStaff OtherStaff = null;
		if(StringUtils.isEmpty(insurance.getStaff_no()) && StringUtils.isEmpty(insurance.getStaff_name())){
			insurance.setErrorInfo(insurance.getErrorInfo() + "先输入工号或者姓名;");
		}else if(!StringUtils.isEmpty(insurance.getStaff_no())){
			OtherStaff = OtherStaffNoMap.get(insurance.getStaff_no());
			if(OtherStaff == null) insurance.setErrorInfo(insurance.getErrorInfo() + "工号错误;");
		}else if(!StringUtils.isEmpty(insurance.getStaff_name())){
			List<OtherStaff> scs = OtherStaffNameMap.get(insurance.getStaff_name());
			if(scs == null || scs.isEmpty())  insurance.setErrorInfo(insurance.getErrorInfo() + "姓名错误;");
			else if(scs.size() > 1)  insurance.setErrorInfo(insurance.getErrorInfo() + "姓名有重名;");
			else OtherStaff = scs.get(0);
		}
				
		if(OtherStaff != null){
			insurance.setStaff_id(OtherStaff.getStaff_id());
			insurance.setStaff_no(OtherStaff.getStaff_no());
			insurance.setStaff_name(OtherStaff.getStaff_name());	
		}
		
		if(insurance.getErrorInfo() != null && !insurance.getErrorInfo().isEmpty())
			b = false;
		return b;
	}


	@RequestMapping(params = "method=importResult")
	public String importResult(HttpServletResponse res,HttpServletRequest request) throws  Exception{
		List<Insurance> list = (List<Insurance>)request.getSession().getAttribute(sessionAttr);
		request.getSession().removeAttribute(sessionAttr);
		request.setAttribute("list", list);
		return "insurance/insurance_excel_list";
	}



}