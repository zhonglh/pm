package com.pm.action;

import com.common.actions.BaseAction;
import com.common.beans.Pager;
import com.common.utils.DateKit;
import com.common.utils.IDKit;
import com.common.utils.file.FileKit;
import com.common.utils.file.download.DownloadBaseUtil;
import com.common.utils.sorts.SortComparator;
import com.pm.domain.business.OtherStaff;
import com.pm.domain.business.OtherWorkAttendance;
import com.pm.domain.business.ReimburseCosts;
import com.pm.domain.system.Dept;
import com.pm.domain.system.User;
import com.pm.service.*;
import com.pm.util.Config;
import com.pm.util.PubMethod;
import com.pm.util.constant.BusinessUtil;
import com.pm.util.constant.EnumOperationType;
import com.pm.util.constant.EnumPermit;
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
 * @author Administrator
 */
@Controller
@RequestMapping(value = "OtherWorkAttendanceGroupAction.do")
public class OtherWorkAttendanceGroupAction extends BaseAction {


	private static final String sessionAttr = "OtherWorkAttendances";


	private static final String rel = "rel02";

	@Autowired
	private IOtherWorkAttendanceService otherWorkAttendanceService;


	@Autowired
	private IOtherStaffService otherStaffService;



	@Autowired
	IApplyApproveService applyApproveService;

	@Autowired
	IDeptService deptService;




	@Autowired
	private IRoleService roleService;


	@RequestMapping(params = "method=isExist")
	public String isExist(OtherWorkAttendance searchWorkAttendance,HttpServletResponse res,HttpServletRequest request){
		paramprocess(request,searchWorkAttendance);
		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);

		Pager<OtherWorkAttendance> pager= otherWorkAttendanceService.queryWorkAttendanceGroup(searchWorkAttendance, userPermit,PubMethod.getPager(request, OtherWorkAttendance.class));
		if(pager.getResultList() == null || pager.getResultList().isEmpty()){
			return this.ajaxForwardSuccess(request);
		}else {
			return this.ajaxForwardError(request, searchWorkAttendance.getDept_name() + "在 "+searchWorkAttendance.getAttendance_month()+ " 的考勤记录已经制作，如果继续将会覆盖之前的考勤记录！");
		}
	}

	@RequestMapping(params = "method=list")
	public String list(OtherWorkAttendance searchWorkAttendance,HttpServletResponse res,HttpServletRequest request){


		String dept_id = request.getParameter("dept.dept_id");
		if(StringUtils.isNotEmpty(dept_id)){
			searchWorkAttendance.setDept_id(dept_id);
		}
		String dept_name = request.getParameter("dept.dept_name");
		if(StringUtils.isNotEmpty(dept_name)){
			searchWorkAttendance.setDept_name(dept_name);
		}




		request.setAttribute("workAttendance1", searchWorkAttendance);

		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.OTHERWORKATTENDANCEVIEW.getId());
		Pager<OtherWorkAttendance> pager= otherWorkAttendanceService.queryWorkAttendanceGroup(searchWorkAttendance, userPermit,PubMethod.getPager(request, OtherWorkAttendance.class));
		PubMethod.setRequestPager(request, pager,OtherWorkAttendance.class);

		request.setAttribute(EnumOperationType.READ.getKey(), userPermit.getPermit_id());
		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.OTHERWORKATTENDANCEADD.getId());
		request.setAttribute(EnumOperationType.INSERT.getKey(), userPermit1.getPermit_id());

		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.OTHERWORKATTENDANCEUPDATE.getId());
		request.setAttribute(EnumOperationType.UPDATE.getKey(), userPermit1.getPermit_id());

		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.OTHERWORKATTENDANCEDELETE.getId());
		request.setAttribute(EnumOperationType.DELETE.getKey(), userPermit1.getPermit_id());

		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.OTHERWORKATTENDANCECHECK.getId());
		request.setAttribute(EnumOperationType.CHECK.getKey(), userPermit1.getPermit_id());

		return "headquarters/other_work_attendance_group_list";

	}



	@RequestMapping(params = "method=export")
	public void export(OtherWorkAttendance searchWorkAttendance,HttpServletResponse res,HttpServletRequest request){

		String dept_id = request.getParameter("dept.dept_id");
		if(StringUtils.isNotEmpty(dept_id)){
			searchWorkAttendance.setDept_id(dept_id);
		}

		String dept_name = request.getParameter("dept.dept_name");
		if(StringUtils.isNotEmpty(dept_name)){
			searchWorkAttendance.setDept_name(dept_name);
		}


		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.OTHERWORKATTENDANCEVIEW.getId());
		Pager<OtherWorkAttendance> pager= otherWorkAttendanceService.queryWorkAttendance(searchWorkAttendance, userPermit,PubMethod.getPagerByAll(request, OtherWorkAttendance.class));


		if(pager.getResultList() != null){
			for(OtherWorkAttendance temp : pager.getResultList()){
				temp.setStr_month(String.valueOf(temp.getAttendance_month()));
			}
		}

		try{
			BusinessExcel.export(res, null, pager.getResultList(), OtherWorkAttendance.class,false);
		}catch(Exception e){
			e.printStackTrace();
		}

	}



	@RequestMapping(params = "method=toExcel")
	public String toExcel(HttpServletResponse res,HttpServletRequest request){
		return "headquarters/other_work_attendance_upload";
	}


	@RequestMapping(params = "method=downloadtemplet")
	public ModelAndView downloadtemplet(HttpServletRequest request,  HttpServletResponse response) throws Exception {

		String sourceFile = this.getClass().getClassLoader().getResource("/templet/otherWorkAttendance.xlsx").getPath();
		DownloadBaseUtil.download(  sourceFile,  "总部人员考勤模板.xlsx" ,response,false);
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
			if(row.length<18) {
				return this.ajaxForwardError(request, "第"+(index+Config.startRow)+"行数据不全",true);
			}
			index ++;
		}

		List<OtherWorkAttendance> workAttendances = PubMethod.stringArray2List(list, OtherWorkAttendance.class);


		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);


		//查找所有的部门
		Pager<Dept> depts = deptService.queryDept(new Dept() , userPermit ,PubMethod.getPagerByAll(request, Dept.class));
		Map<String,Dept>  deptMap = new HashMap<String,Dept>();
		if(depts.getResultList() != null) {
			for(Dept dept : depts.getResultList()){
				deptMap.put(dept.getDept_no(), dept);
				deptMap.put(dept.getDept_name(), dept);
			}
		}


		//查找所有人员
		Pager<OtherStaff> staffCosts = otherStaffService.queryOtherStaff(new OtherStaff(),  userPermit, PubMethod.getPagerByAll(request, OtherStaff.class));
		Map<String,OtherStaff>  otherStaffMap = new HashMap<String,OtherStaff>();
		if(staffCosts.getResultList() != null) {
			for(OtherStaff staffCost : staffCosts.getResultList()){
				otherStaffMap.put(staffCost.getStaff_no(), staffCost);
			}
		}


		//查找已核单过的部门+月份
		List<OtherWorkAttendance> checkedWorkAttendances = otherWorkAttendanceService.getCheckedDeptMonth();
		Map<String,OtherWorkAttendance>  checkedWorkAttendanceMap = new HashMap<String,OtherWorkAttendance>();
		if(checkedWorkAttendances != null){
			for(OtherWorkAttendance  workAttendance : checkedWorkAttendances){
				checkedWorkAttendanceMap.put(workAttendance.getDept_id()+String.valueOf(workAttendance.getAttendance_month()), workAttendance);
			}
		}

		Map<String,OtherWorkAttendance> monthDeptMap = new HashMap<String,OtherWorkAttendance>();
		if(workAttendances != null) {
			for(OtherWorkAttendance workAttendance : workAttendances){
				boolean b = checkWorkAttendance(workAttendance,deptMap,otherStaffMap,checkedWorkAttendanceMap);
				if(b) {
					monthDeptMap.put(workAttendance.getDept_id()+String.valueOf(workAttendance.getAttendance_month()), workAttendance);
				}
			}
		}



		Map<String, OtherWorkAttendance> keyMap = new HashMap<String, OtherWorkAttendance>();
		for(OtherWorkAttendance workAttendance : workAttendances){
			keyMap.put(workAttendance.getBusinessKey(), workAttendance);
		}


		User sessionUser = PubMethod.getUser(request);

		boolean isAllOK = true;
		index = 0;
		for(OtherWorkAttendance workAttendance : workAttendances){
			if(workAttendance.getErrorInfo()==null || workAttendance.getErrorInfo().length() <= 0){
				try{
					workAttendance.setAttendance_id(IDKit.generateId());
					workAttendance.setBuild_datetime(PubMethod.getCurrentDate());
					workAttendance.setBuild_userid(sessionUser.getUser_id());
					workAttendance.setBuild_username(sessionUser.getUser_name());
					workAttendance.setDelete_flag(BusinessUtil.NOT_DELETEED);

					OtherWorkAttendance tmp = keyMap.get(workAttendance.getBusinessKey());
					if(tmp == null) {
						workAttendance.setErrorInfo("请先检查该人员是否在此部门中 ");
						isAllOK = false;
						continue;
					}

					int count = otherWorkAttendanceService.addWorkAttendance(workAttendance);

					if(count == 0){
						workAttendance.setErrorInfo("已经有此考勤记录");
						isAllOK = false;
					}

				}catch(Exception e){
					if(e.getMessage() == null || e.getMessage().indexOf("Key_2")!=-1 || e.getMessage().indexOf("key 2")!=-1) {
						workAttendance.setErrorInfo("已经有此考勤记录");
					}
					else {
						workAttendance.setErrorInfo(e.getMessage());
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
			request.getSession().setAttribute(sessionAttr, workAttendances);
			request.setAttribute("forwardUrl", request.getContextPath()+"/OtherWorkAttendanceGroupAction.do?method=importResult");
			return this.ajaxForwardError(request, "导入的总部人员考勤信息中有些问题！ ");
		}

	}


	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=importResult")
	public String importResult(HttpServletResponse res,HttpServletRequest request) throws  Exception{
		List<ReimburseCosts> list = (List<ReimburseCosts>)request.getSession().getAttribute(sessionAttr);
		request.getSession().removeAttribute(sessionAttr);
		request.setAttribute("list", list);
		return "headquarters/other_work_attendance_excel_list";
	}


	private boolean checkWorkAttendance(OtherWorkAttendance workAttendance,
			Map<String,Dept>  deptMap,
			Map<String,OtherStaff>  otherStaffMap,
			Map<String,OtherWorkAttendance>  checkedWorkAttendanceMap){
		boolean b = true;
		if(workAttendance.getStr_month() == null ||  workAttendance.getStr_month().isEmpty()){
			workAttendance.setErrorInfo(workAttendance.getErrorInfo() + "考勤月份不能为空;");
			b = false;
		}else {
			if(workAttendance.getStr_month().length() != 6) {
				workAttendance.setErrorInfo(workAttendance.getErrorInfo() + "考勤月份格式错误;");
				b = false;
			}else{
				Date date1 = DateKit.fmtStrToDate(workAttendance.getStr_month()+"01","yyyyMMdd");
				if(date1 == null){
					workAttendance.setErrorInfo(workAttendance.getErrorInfo() + "考勤月份格式错误;");
					b = false;
				}else {
					workAttendance.setAttendance_month(Integer.parseInt(workAttendance.getStr_month()));
				}
			}
		}


		if(workAttendance.getDept_name() == null ||  workAttendance.getDept_name().isEmpty()){
			workAttendance.setErrorInfo(workAttendance.getErrorInfo() + "部门名称不能为空;");
			b = false;
		}else {
			Dept dept = deptMap.get(workAttendance.getDept_name().trim());
			if(dept == null ){
				workAttendance.setErrorInfo(workAttendance.getErrorInfo() + "\"部门名称错误;");
				b = false;
			}else {
				workAttendance.setDept_id(dept.getDept_id());
				workAttendance.setDept_name(dept.getDept_name());
			}
		}



		if(workAttendance.getStaff_no() == null || workAttendance.getStaff_no().length() == 0){
			workAttendance.setErrorInfo(workAttendance.getErrorInfo() + "人员工号不能为空;");
			b = false;

		}else {
			OtherStaff staffCost = otherStaffMap.get(workAttendance.getStaff_no());
			if(staffCost == null){
				workAttendance.setErrorInfo(workAttendance.getErrorInfo() + "人员工号错误;");
				b = false;
			}else {
				workAttendance.setStaff_id(staffCost.getStaff_id());
				workAttendance.setStaff_no(staffCost.getStaff_no());
				workAttendance.setStaff_name(staffCost.getStaff_name());
			}
		}



		if(b){
			String key = workAttendance.getDept_id() + String.valueOf(workAttendance.getAttendance_month());
			if(checkedWorkAttendanceMap.containsKey(key)) {
				workAttendance.setErrorInfo(workAttendance.getErrorInfo() + "该部门该月份的考勤已完成核单，不能够再导入了;");
				b = false;
			}
		}

		if(workAttendance.getErrorInfo() != null && !workAttendance.getErrorInfo().isEmpty()) {
			b = false;
		}
		return b;
	}




	@RequestMapping(params = "method=batchVerifyWorkAttendance")
	public String batchVerifyWorkAttendance(HttpServletResponse res,HttpServletRequest request){

		String[] ids = request.getParameterValues("ids");
		if(ids == null || ids.length == 0) {
			return this.ajaxForwardSuccess(request, rel, false);
		}


		User sessionUser = PubMethod.getUser(request);

		OtherWorkAttendance[] workAttendanceArray = new OtherWorkAttendance[ids.length];
		int index = 0;
		for(String id : ids){
			OtherWorkAttendance workAttendance = new OtherWorkAttendance();
			String[] array = id.split(BusinessUtil.SPLITSTRING);
			workAttendance.setAttendance_month(Integer.parseInt(array[0]));
			workAttendance.setDept_id(array[1]);
			workAttendanceArray[index] = workAttendance;
			index ++ ;
		}

		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);



		synchronized(this){

			for(OtherWorkAttendance workAttendance : workAttendanceArray){
				Pager<OtherWorkAttendance> pager = otherWorkAttendanceService.queryWorkAttendance(workAttendance, userPermit, PubMethod.getPagerByAll(request, OtherWorkAttendance.class));
				if(pager.getResultList() == null || pager.getResultList().isEmpty()){
					continue;
				}
				OtherWorkAttendance workAttendance1 = pager.getResultList().get(0);
				if(workAttendance1.getVerify_userid() != null && workAttendance1.getVerify_userid().length() > 0){
					continue;
				}
				String[] workAttendanceIds = new String[pager.getResultList().size()];
				int i = 0;
				for(OtherWorkAttendance tmp : pager.getResultList()){
					workAttendanceIds[i] = tmp.getAttendance_id();
					i++;
				}
				try{
					verifyWorkAttendance(sessionUser, workAttendanceIds, workAttendance1);
				}catch(Exception e){
					e.printStackTrace();
				}

			}

		}


		return this.ajaxForwardSuccess(request, rel, false);



	}

	@RequestMapping(params = "method=verifyWorkAttendance")
	public String verifyWorkAttendance(HttpServletResponse res,HttpServletRequest request){

		User sessionUser = PubMethod.getUser(request);
		String[] ids = request.getParameterValues("attendance_id");
		if(ids == null || ids.length == 0) {
			return this.ajaxForwardError(request, "考勤记录已经核实过！",true);
		}

		synchronized(this){


			OtherWorkAttendance workAttendance1 = otherWorkAttendanceService.getWorkAttendance(ids[0]);
			if(workAttendance1.getVerify_userid() != null && workAttendance1.getVerify_userid().length() > 0){
				return this.ajaxForwardError(request, "考勤记录已经核实过！",true);
			}


			verifyWorkAttendance(sessionUser, ids, workAttendance1);
		}

		return this.ajaxForwardSuccess(request, rel, true);
	}

	private void verifyWorkAttendance(User sessionUser, String[] ids, OtherWorkAttendance workAttendance1) {

		List<OtherWorkAttendance> workAttendances = new ArrayList<OtherWorkAttendance>();
		for(String attendance_id : ids){
			OtherWorkAttendance workAttendance= new OtherWorkAttendance();
			workAttendance.setAttendance_id(attendance_id);
			workAttendance.setVerify_datetime(PubMethod.getCurrentDate());
			workAttendance.setVerify_userid(sessionUser.getUser_id());
			workAttendance.setVerify_username(sessionUser.getUser_name());
			workAttendances.add(workAttendance);

		}

		if(workAttendances != null && workAttendances.size() > 0){
			otherWorkAttendanceService.verifyWorkAttendance( workAttendances );
		}
	}

	@RequestMapping(params = "method=addWorkAttendanceGroup")
	public String addWorkAttendanceGroup(OtherWorkAttendance addWorkAttendance,HttpServletResponse res,HttpServletRequest request){

		User sessionUser = PubMethod.getUser(request);
		String[] ids = request.getParameterValues("id");

		if(ids == null || ids.length ==0){
			return this.ajaxForwardError(request, "没有添加任何考勤记录！",true);
		}

		List<OtherWorkAttendance> list = new ArrayList<OtherWorkAttendance>();
		for(String index : ids){
			OtherWorkAttendance workAttendance = new OtherWorkAttendance();
			workAttendance.setAttendance_month(addWorkAttendance.getAttendance_month());
			workAttendance.setDept_id(addWorkAttendance.getDept_id());

			workAttendance.setStaff_name(request.getParameter("staff_name"+index));
			workAttendance.setStaff_id(request.getParameter("staff_id"+index));

			workAttendance.setShould_work_days(Double.parseDouble(request.getParameter("should_work_days"+index)));
			workAttendance.setWork_days(Double.parseDouble(request.getParameter("work_days"+index)));
			workAttendance.setLegal_holidays(Double.parseDouble(request.getParameter("legal_holidays"+index)));
			workAttendance.setSwopped_holidays(Double.parseDouble(request.getParameter("swopped_holidays"+index)));
			workAttendance.setAnnual_leave_days(Double.parseDouble(request.getParameter("annual_leave_days"+index)));
			workAttendance.setBusiness_trip_days(Double.parseDouble(request.getParameter("business_trip_days"+index)));
			workAttendance.setPersonal_leave_days(Double.parseDouble(request.getParameter("personal_leave_days"+index)));
			workAttendance.setSick_leave_days(Double.parseDouble(request.getParameter("sick_leave_days"+index)));

			workAttendance.setWaiting_post_days(Double.parseDouble(request.getParameter("waiting_post_days"+index)));
			workAttendance.setMaternity_leave_days(Double.parseDouble(request.getParameter("maternity_leave_days"+index)));
			//workAttendance.setMedical_days(Double.parseDouble(request.getParameter("medical_days"+index)));


			workAttendance.setNeglect_work_days(Double.parseDouble(request.getParameter("neglect_work_days"+index)));
			workAttendance.setLate_days(Double.parseDouble(request.getParameter("late_days"+index)));
			workAttendance.setWeekend_overtime_days(Double.parseDouble(request.getParameter("weekend_overtime_days"+index)));

			workAttendance.setEvery_overtime(Double.parseDouble(request.getParameter("every_overtime"+index)));
			workAttendance.setRemark(request.getParameter("remark"+index));

			workAttendance.setAttendance_id(IDKit.generateId());
			workAttendance.setBuild_datetime(PubMethod.getCurrentDate());
			workAttendance.setBuild_userid(sessionUser.getUser_id());
			workAttendance.setBuild_username(sessionUser.getUser_name());
			workAttendance.setDelete_flag(BusinessUtil.NOT_DELETEED);
			list.add(workAttendance);
		}


		if(list != null && !list.isEmpty())
			otherWorkAttendanceService.addWorkAttendance(list);
		return this.ajaxForwardSuccess(request, rel, true);

	}


	@RequestMapping(params = "method=updateWorkAttendanceGroup")
	public String updateWorkAttendanceGroup(OtherWorkAttendance updateWorkAttendance,HttpServletResponse res,HttpServletRequest request){

		User sessionUser = PubMethod.getUser(request);
		String[] ids = request.getParameterValues("id");

		if(ids == null || ids.length ==0){
			return this.ajaxForwardSuccess(request);
		}


		//todo , 打开注释
		/**
		Salary searchSalary = new Salary();
		searchSalary.setProject_id(updateWorkAttendance.getDept_id());
		searchSalary.setSalary_month(updateWorkAttendance.getAttendance_month());
		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);
		Pager<Salary> salarys = salaryService.querySalary(searchSalary, userPermit, PubMethod.getPagerByAll(request, Salary.class));
		if(salarys.getResultList() != null && !salarys.getResultList().isEmpty()){
			return this.ajaxForwardError(request, "该考勤已经生成工资单， 需要删除工资单后才能够修改！",true);
		}
		 */

		List<OtherWorkAttendance> list = new ArrayList<OtherWorkAttendance>();

		List<OtherWorkAttendance> delList = new ArrayList<OtherWorkAttendance>();

		for(String index : ids){

			if(index.equals("0")) {
				continue;
			}




			OtherWorkAttendance workAttendance = new OtherWorkAttendance();
			workAttendance.setAttendance_id(request.getParameter("attendance_id"+index));
			workAttendance.setOperationType(request.getParameter("operationType"+index));

			if(EnumOperationType.DELETE.getValue().equals(workAttendance.getOperationType())){
				delList.add(workAttendance);
				continue;
			}


			workAttendance.setAttendance_month(updateWorkAttendance.getAttendance_month());
			workAttendance.setDept_id(updateWorkAttendance.getDept_id());

			workAttendance.setStaff_name(request.getParameter("staff_name"+index));
			workAttendance.setStaff_id(request.getParameter("staff_id"+index));

			workAttendance.setShould_work_days(Double.parseDouble(request.getParameter("should_work_days"+index)));
			workAttendance.setWork_days(Double.parseDouble(request.getParameter("work_days"+index)));
			workAttendance.setLegal_holidays(Double.parseDouble(request.getParameter("legal_holidays"+index)));
			workAttendance.setSwopped_holidays(Double.parseDouble(request.getParameter("swopped_holidays"+index)));
			workAttendance.setAnnual_leave_days(Double.parseDouble(request.getParameter("annual_leave_days"+index)));
			workAttendance.setBusiness_trip_days(Double.parseDouble(request.getParameter("business_trip_days"+index)));
			workAttendance.setPersonal_leave_days(Double.parseDouble(request.getParameter("personal_leave_days"+index)));
			workAttendance.setSick_leave_days(Double.parseDouble(request.getParameter("sick_leave_days"+index)));

			workAttendance.setWaiting_post_days(Double.parseDouble(request.getParameter("waiting_post_days"+index)));
			workAttendance.setMaternity_leave_days(Double.parseDouble(request.getParameter("maternity_leave_days"+index)));
			//workAttendance.setMedical_days(Double.parseDouble(request.getParameter("medical_days"+index)));

			workAttendance.setNeglect_work_days(Double.parseDouble(request.getParameter("neglect_work_days"+index)));
			workAttendance.setLate_days(Double.parseDouble(request.getParameter("late_days"+index)));
			workAttendance.setWeekend_overtime_days(Double.parseDouble(request.getParameter("weekend_overtime_days"+index)));


			workAttendance.setEvery_overtime(Double.parseDouble(request.getParameter("every_overtime"+index)));
			workAttendance.setRemark(request.getParameter("remark"+index));



			if(workAttendance.getAttendance_id() == null || workAttendance.getAttendance_id().isEmpty()){
				workAttendance.setBuild_datetime(PubMethod.getCurrentDate());
				workAttendance.setBuild_userid(sessionUser.getUser_id());
				workAttendance.setBuild_username(sessionUser.getUser_name());
				workAttendance.setDelete_flag(BusinessUtil.NOT_DELETEED);
			}

			workAttendance.setVerify_datetime(PubMethod.getCurrentDate());
			workAttendance.setVerify_userid(sessionUser.getUser_id());
			workAttendance.setVerify_username(sessionUser.getUser_name());

			list.add(workAttendance);
		}


		if(list.isEmpty() && delList.isEmpty()){
			return this.ajaxForwardError(request, "没有修改过的考勤单！",true);
		}

		if(!delList.isEmpty()){
			otherWorkAttendanceService.deleteWorkAttendance(delList.toArray(new OtherWorkAttendance[delList.size()]));
		}

		otherWorkAttendanceService.updateWorkAttendance(list);


		if("1".equals(request.getParameter("checked"))){
				otherWorkAttendanceService.verifyWorkAttendance( list );

		}

		return this.ajaxForwardSuccess(request, rel, true);
	}



	private void paramprocess(HttpServletRequest request,OtherWorkAttendance workAttendance){
		workAttendance.setDept_id(request.getParameter("dept.dept_id"));

		if(workAttendance.getDept_name() == null || workAttendance.getDept_name().isEmpty()) {
			workAttendance.setDept_name(request.getParameter("dept.dept_name"));
		}
	}


	@RequestMapping(params = "method=toEditFirst")
	public String toEditFirst(HttpServletResponse res,HttpServletRequest request){

		String attendance_month = DateKit.fmtDateToStr(DateKit.addMonth(new Date(), -1),"yyyyMM");
		OtherWorkAttendance workAttendance = new OtherWorkAttendance();
		workAttendance.setAttendance_month(Integer.parseInt(attendance_month));
		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);
		
		Pager<OtherWorkAttendance> pager = otherWorkAttendanceService.queryWorkAttendance(workAttendance, userPermit, PubMethod.getPager(request, OtherWorkAttendance.class));
		if(pager.getResultList() != null && pager.getResultList().size() > 0){
			OtherWorkAttendance workAttendance1 = pager.getResultList().get(0);
			request.setAttribute("should_work_days", workAttendance1.getShould_work_days());
			request.setAttribute("legal_holidays", workAttendance1.getLegal_holidays());
		}
		
		request.setAttribute("attendance_month", attendance_month);		
		
		return "headquarters/other_work_attendance_group_edit1";		
	}
	
	
	private List<OtherWorkAttendance> computeWorkAttendance(OtherWorkAttendance workAttendance1){
		
		
		Date date1 = DateKit.fmtStrToDate(workAttendance1.getAttendance_month()+"01","yyyyMMdd");
		Date date2 = DateKit.addMonth(date1, 1);
		workAttendance1.setAttendance_day1(DateKit.fmtDateToStr(date1, BusinessUtil.DT_FORMAT));
		workAttendance1.setAttendance_day2(DateKit.fmtDateToStr(date2, BusinessUtil.DT_FORMAT));
		
		List<OtherWorkAttendance> list = otherWorkAttendanceService.getWorkAttendanceByDeptMonth(workAttendance1);
		
		return list;
	}
	

	@RequestMapping(params = "method=toEditNext")
	public String toEditNext(OtherWorkAttendance workAttendance,HttpServletResponse res,HttpServletRequest request){	
		
		OtherWorkAttendance workAttendance1 = workAttendance;
		paramprocess(request,workAttendance1);
		
		User sessionUser = PubMethod.getUser(request);
		
		List<OtherWorkAttendance> list = computeWorkAttendance(workAttendance1);
		
		if(list == null) {
			list = new ArrayList<OtherWorkAttendance>();
		}
		
		for(OtherWorkAttendance workAttendanceTmp : list){
			workAttendanceTmp.setShould_work_days(workAttendance.getShould_work_days());
			workAttendanceTmp.setLegal_holidays(workAttendance.getLegal_holidays());
		}

		workAttendance1.setBuild_username(sessionUser.getUser_name());
		workAttendance1.setBuild_datetime(PubMethod.getCurrentDate());

		request.setAttribute("list", list);
		request.setAttribute("otherWorkAttendance1", workAttendance1);
		request.setAttribute("next_operation", "addWorkAttendanceGroup");
		request.setAttribute("totayDays", workAttendance.getShould_work_days()+workAttendance.getLegal_holidays());
		
		return "headquarters/other_work_attendance_group_edit2";
	}
	
	

	@RequestMapping(params = "method=toEdit")
	public String toEdit(HttpServletResponse res,HttpServletRequest request){
		
		String id= request.getParameter("id");
		String[] array = id.split(BusinessUtil.SPLITSTRING);
		
		OtherWorkAttendance workAttendance = new OtherWorkAttendance();
		workAttendance.setDept_id(array[1]);
		workAttendance.setAttendance_month(Integer.parseInt(array[0]));

		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);
		
		Pager<OtherWorkAttendance> pager = otherWorkAttendanceService.queryWorkAttendance(workAttendance, userPermit, PubMethod.getPagerByAll(request, OtherWorkAttendance.class));
		if(pager.getResultList() == null || pager.getResultList().isEmpty()){
			return this.ajaxForwardError(request, "没有任何考勤记录！",true);
		}


		//todo , 打开注释
		/**
		Salary searchSalary = new Salary();
		searchSalary.setProject_id(workAttendance.getProject_id());
		searchSalary.setSalary_month(workAttendance.getAttendance_month());
		Pager<Salary> salarys = salaryService.querySalary(searchSalary, userPermit, PubMethod.getPagerByAll(request, Salary.class));
		if(salarys.getResultList() != null && !salarys.getResultList().isEmpty()){
			return this.ajaxForwardError(request, "该考勤已经生成工资单， 需要删除工资单后才能够修改！",true);
		}
		 */

		request.setAttribute("list", pager.getResultList());
		request.setAttribute("workAttendance1", pager.getResultList().get(0));
		request.setAttribute("next_operation", "updateWorkAttendanceGroup");
		
		String checked = pager.getResultList().get(0).getVerify_userid();
		checked = ( checked == null || checked.isEmpty()) ? "0" : "1" ;
		request.setAttribute("checked", checked);

		return "headquarters/other_work_attendance_group_edit";
		
	}	
	
	
	
	/**
	 * 根据项目和人员关系，重新计算考勤中项目人员
	 * @param res
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=toReCompute")
	public String toReCompute(HttpServletResponse res,HttpServletRequest request){
		
		
		OtherWorkAttendance workAttendance = new OtherWorkAttendance();
		workAttendance.setDept_id(request.getParameter("dept_id"));
		workAttendance.setAttendance_month(Integer.parseInt(request.getParameter("attendance_month")));

		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);
		
		Pager<OtherWorkAttendance> pager = otherWorkAttendanceService.queryWorkAttendance(workAttendance, userPermit, PubMethod.getPagerByAll(request, OtherWorkAttendance.class));
		if(pager.getResultList() == null || pager.getResultList().isEmpty()){
			return this.ajaxForwardError(request, "没有任何考勤记录！",true);
		}
		List<OtherWorkAttendance> list = pager.getResultList();



		//todo , 打开注释
		/**
		Salary searchSalary = new Salary();
		searchSalary.setProject_id(workAttendance.getProject_id());
		searchSalary.setSalary_month(workAttendance.getAttendance_month());
		Pager<Salary> salarys = salaryService.querySalary(searchSalary, userPermit, PubMethod.getPagerByAll(request, Salary.class));
		if(salarys.getResultList() != null && !salarys.getResultList().isEmpty()){
			return this.ajaxForwardError(request, "该考勤已经生成工资单， 需要删除工资单后才能够修改！",true);
		}
		*/
		

		List<OtherWorkAttendance> newlist = computeWorkAttendance(workAttendance);
		if(newlist == null) {
			newlist = new ArrayList<OtherWorkAttendance>();
		}
		for(OtherWorkAttendance wa : list){
			boolean have = false;
			for(OtherWorkAttendance nwa :  newlist){
				if(wa.getStaff_id().equals(nwa.getStaff_id())) {
					have = true;
					break;
				}
			}
			if(have) {
				wa.setOperationType(EnumOperationType.UPDATE.getValue());
			}
			else {
				wa.setOperationType(EnumOperationType.DELETE.getValue());
			}
		}
		
		List<OtherWorkAttendance> adds = new ArrayList<OtherWorkAttendance>();
		for(OtherWorkAttendance nwa : newlist){
			boolean have = false;
			for(OtherWorkAttendance wa :  list){
				if(wa.getStaff_id().equals(nwa.getStaff_id())) {
					have = true;
					break;
				}
			}
			if(!have) {
				nwa.setShould_work_days(list.get(0).getShould_work_days());
				nwa.setLegal_holidays(list.get(0).getLegal_holidays());
				nwa.setOperationType(EnumOperationType.INSERT.getValue());
				adds.add(nwa);
			}
		}
		
		if(adds.size() > 0) {
			list.addAll(adds);
		}
		Collections.sort(list,new SortComparator());		
		
		

		request.setAttribute("list", list);
		request.setAttribute("workAttendance1", pager.getResultList().get(0));
		request.setAttribute("next_operation", "updateWorkAttendanceGroup");
		
		String checked = pager.getResultList().get(0).getVerify_userid();
		checked = ( checked == null || checked.isEmpty()) ? "0" : "1" ;
		request.setAttribute("checked", checked);
		request.setAttribute("reCompute", "1");

		return "headquarters/other_work_attendance_group_edit";
		
	}		
	


	@RequestMapping(params = "method=toView")
	public String toView(HttpServletResponse res,HttpServletRequest request){
		

		String id= request.getParameter("id");
		String[] array = id.split(BusinessUtil.SPLITSTRING);
		
		OtherWorkAttendance workAttendance = new OtherWorkAttendance();
		workAttendance.setDept_id(array[1]);
		workAttendance.setAttendance_month(Integer.parseInt(array[0]));

		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);
		
		Pager<OtherWorkAttendance> pager = otherWorkAttendanceService.queryWorkAttendance(workAttendance, userPermit, PubMethod.getPagerByAll(request, OtherWorkAttendance.class));
		if(pager.getResultList() == null || pager.getResultList().isEmpty()){
			return this.ajaxForwardError(request, "没有任何考勤记录！",true);
		}

		request.setAttribute("list", pager.getResultList());
		request.setAttribute("otherWorkAttendance1", pager.getResultList().get(0));
		request.setAttribute("next_operation", "updateWorkAttendanceGroup");	
		
		

		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.WORKATTENDANCECHECK.getId());
		request.setAttribute(EnumOperationType.CHECK.getKey(), userPermit1.getPermit_id());
		
		return "headquarters/other_work_attendance_group_view";
		
	}		
	

	@RequestMapping(params = "method=deleteWorkAttendanceGroup")
	public String deleteWorkAttendanceGroup(HttpServletResponse res,HttpServletRequest request){
		
		String[] ids = request.getParameterValues("ids");
		if(ids == null || ids.length == 0) {
			return this.ajaxForwardSuccess(request, rel, false);		
		}
		
		OtherWorkAttendance[] workAttendanceArray = new OtherWorkAttendance[ids.length];
		int index = 0;
		for(String id : ids){
			OtherWorkAttendance workAttendance = new OtherWorkAttendance();
			String[] array = id.split(BusinessUtil.SPLITSTRING);
			workAttendance.setAttendance_month(Integer.parseInt(array[0]));
			workAttendance.setDept_id(array[1]);
			workAttendanceArray[index] = workAttendance;
			index ++ ;
		}
		
		if(workAttendanceArray != null && workAttendanceArray.length > 0) {
			otherWorkAttendanceService.deleteWorkAttendance(workAttendanceArray);
		}

		return this.ajaxForwardSuccess(request, rel, false);		
		
	}
	
	
}
