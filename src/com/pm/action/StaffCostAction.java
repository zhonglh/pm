package com.pm.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
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
import com.pm.domain.business.DicData;
import com.pm.domain.business.InsuranceGrade;
import com.pm.domain.business.OtherStaff;
import com.pm.domain.business.Params;
import com.pm.domain.business.ProjectStaff;
import com.pm.domain.business.StaffAssessment;
import com.pm.domain.business.StaffCost;
import com.pm.domain.business.StaffCostAnalysis;
import com.pm.domain.business.StaffCostExcel;
import com.pm.domain.business.StaffPositions;
import com.pm.domain.business.StaffRaiseRecord;
import com.pm.domain.business.StaffRewardPenalty;
import com.pm.domain.business.Statistics;
import com.pm.domain.business.StatisticsDetail;
import com.pm.domain.system.User;
import com.pm.engine.BusinessSend;
import com.pm.service.IDicDataService;
import com.pm.service.IInsuranceGradeService;
import com.pm.service.IOtherStaffService;
import com.pm.service.IParamsService;
import com.pm.service.IProjectService;
import com.pm.service.IRoleService;
import com.pm.service.IStaffAssessmentService;
import com.pm.service.IStaffCostService;
import com.pm.service.IStaffPositionsService;
import com.pm.service.IStaffRaiseRecordService;
import com.pm.service.IStaffRewardPenaltyService;
import com.pm.util.Config;
import com.pm.util.PubMethod;
import com.pm.util.constant.BusinessUtil;
import com.pm.util.constant.EnumDicType;
import com.pm.util.constant.EnumOperationType;
import com.pm.util.constant.EnumPermit;
import com.pm.util.excel.BusinessExcel;
import com.pm.util.excel.ExcelRead;
import com.pm.vo.ConditionStaffCost;
import com.pm.vo.UserPermit;


/**
 * @author Administrator
 */
@Controller
@RequestMapping(value = "StaffCostAction.do")
public class StaffCostAction extends BaseAction {
	
	
	private static final String sessionAttr = "StaffCosts";

	private static final String rel = "rel10";
	
	@Autowired
	private IStaffCostService staffCostService;
	
	@Autowired
	private IRoleService roleService;

	@Autowired
	private IDicDataService dicDataService;
	

	@Autowired
	private IParamsService paramsService;
	

	@Autowired
	private IOtherStaffService otherStaffService;
	

	@Autowired
	private IInsuranceGradeService insuranceGradeService;
	
	@Autowired
	private IProjectService projectService;
	

	@Autowired
	private IStaffRaiseRecordService staffRaiseRecordService;
	
	@Autowired
	private IStaffRewardPenaltyService staffRewardPenaltyService;
	
	@Autowired
	private IStaffAssessmentService  staffAssessmentService;
	
	@Autowired
	private IStaffPositionsService staffPositionsService;
		



	@RequestMapping(params = "method=isExist")
	public String isExist(StaffCost staffCost,HttpServletResponse res,HttpServletRequest request){

		String error = null;	
		boolean b = staffCostService.isExist(staffCost);
		if(b){
			error = "该员工工号已经存在";
		}		
		
		if(!b){
			return this.ajaxForwardSuccess(request);
		}else {
			return this.ajaxForwardError(request, error);
		}
	}
	

	@RequestMapping(params = "method=toExcel")
	public String toExcel(HttpServletResponse res,HttpServletRequest request){
		return "basicdata/staff_cost_upload";
	}

	


	@RequestMapping(params = "method=importResult")
	public String importResult(HttpServletResponse res,HttpServletRequest request) throws  Exception{
		List<StaffCost> list = (List<StaffCost>)request.getSession().getAttribute(sessionAttr);
		request.getSession().removeAttribute(sessionAttr);
		request.setAttribute("list", list);
		return "basicdata/staff_cost_excel_list";
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
		
		if(list == null || list.size() == 0) {
			return this.ajaxForwardError(request, "该文件内容为空！",true);
		}
		int index = 0;
		for(String[] row : list){
			if(row.length<50) {
				return this.ajaxForwardError(request, "第"+(index+Config.startRow)+"行数据不全",true);
			}
			if(row.length>55) {
				return this.ajaxForwardError(request, "第"+(index+Config.startRow)+"行数据太长，请按照模板填写！",true);
			}
			index ++;
		}
		
		List<StaffCost> staffCosts = PubMethod.stringArray2List(list, StaffCost.class);
		
		
		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);
		List<StaffCost> allStaffs = staffCostService.getAllStaff();
		Map<String,StaffCost>  staffCostMap = new HashMap<String,StaffCost>();
		if(allStaffs!=null){
			for(StaffCost staffCost : allStaffs){
				staffCostMap.put(staffCost.getStaff_no(), staffCost);
				staffCostMap.put(staffCost.getStaff_name(), staffCost);
				//staffCostMap.put(staffCost.getIdentity_card_number(), staffCost);
			}
		}
		
		
		Pager<OtherStaff> otherStaffpager = otherStaffService.queryOtherStaff(new OtherStaff(), userPermit, PubMethod.getPagerByAll(request, OtherStaff.class));
		Map<String,OtherStaff>  otherStaffMap = new HashMap<String,OtherStaff>();
		if(otherStaffpager.getResultList()!=null){
			for(OtherStaff otherStaff : otherStaffpager.getResultList()){
				otherStaffMap.put(otherStaff.getStaff_name(), otherStaff);
				otherStaffMap.put(otherStaff.getStaff_no(), otherStaff);
			}
		}
		
		
		InsuranceGrade searchInsuranceGrade = new InsuranceGrade();
		searchInsuranceGrade.setDelete_flag(BusinessUtil.NOT_DELETEED);
					
		List<InsuranceGrade> insuranceGrades = insuranceGradeService.queryInsuranceGrade(searchInsuranceGrade);	
		Map<String,InsuranceGrade> insuranceGradeMap = new HashMap<String,InsuranceGrade>();	
		if(insuranceGrades != null && insuranceGrades.size() > 0){
			for(InsuranceGrade insuranceGrade : insuranceGrades){
				insuranceGradeMap.put(insuranceGrade.getInsurance_radix(), insuranceGrade);
			}
		}
		
		

		Map<String, Map<String, DicData>> allDicData = dicDataService.queryAllDicData();
		
		
		
		for(StaffCost staffCost : staffCosts){
			boolean b = checkStaffCost(staffCost,staffCostMap,otherStaffMap,insuranceGradeMap,allDicData);				
		}

		

		User sessionUser = PubMethod.getUser(request);

		for(StaffCost staffCost : staffCosts){
			if(staffCost.getErrorInfo() == null || staffCost.getErrorInfo().trim().isEmpty()){
				staffCost.setStaff_id(IDKit.generateId());
				staffCost.setQustomerquotes(staffCost.getFirstquotes());
				staffCost.setBuild_datetime(PubMethod.getCurrentDate());
				staffCost.setBuild_userid(sessionUser.getUser_id());
				staffCost.setBuild_username(sessionUser.getUser_name());
				staffCost.setDelete_flag(BusinessUtil.NOT_DELETEED);
				
				if(staffCost.getLeave_job_datetime() != null){
					staffCost.setDelete_flag(BusinessUtil.DELETEED);
				}
			}
		}		

		for(StaffCost staffCost : staffCosts){
			boolean b = checkStaffCostLead(staffCost,staffCostMap,otherStaffMap);			
		}
		

		boolean isAllOK = true;
		index = 0;
		for(StaffCost staffCost : staffCosts){
			if(staffCost.getErrorInfo()==null || staffCost.getErrorInfo().length() <= 0){
				try{
					staffCost.setImport_order(index);
					staffCostService.addStaffCost(staffCost,null,null,null,null);
					index ++;
				}catch(Exception e){
					staffCost.setErrorInfo(e.getMessage());
					isAllOK = false;
				}
			}else {
				isAllOK = false;
			}
		}
		
		if(isAllOK){
			return this.ajaxForwardSuccess(request, rel, true);
		}else {		
			request.getSession().setAttribute(sessionAttr, staffCosts);
			request.setAttribute("forwardUrl", request.getContextPath()+"/StaffCostAction.do?method=importResult");
			return this.ajaxForwardError(request, "导入的人员信息中有些问题！ ");
		}
		
	}
	

	private boolean checkStaffCostLead(
			StaffCost staffCost,
			Map<String,	StaffCost>  staffCostMap,
			Map<String,OtherStaff>  otherStaffMap){
		boolean b = true;
		if(staffCost.getLead_no() == null || staffCost.getLead_no().isEmpty()) {
			return b;
		}
		StaffCost staffCostLead = staffCostMap.get(staffCost.getLead_no());
		if(staffCostLead != null && (staffCostLead.getErrorInfo() == null || staffCostLead.getErrorInfo().trim().isEmpty())) {
			staffCost.setLead_id(staffCostLead.getStaff_id());
		}else {
			OtherStaff otherStaff = otherStaffMap.get(staffCost.getLead_no());
			if(otherStaff != null){
				staffCost.setLead_id(otherStaff.getStaff_id());
			}else {
				staffCost.setErrorInfo(staffCost.getErrorInfo() + "上级主管设置有错误;");
				b = false;
			}
		}
		return b;
	}
	
	private boolean checkStaffCost(
			StaffCost staffCost,
			Map<String,StaffCost>  staffCostMap,
			Map<String,OtherStaff>  otherStaffMap,
			Map<String,InsuranceGrade> insuranceGradeMap,
			Map<String, Map<String, DicData>> allDicData){
		

		boolean b = true;
		if(staffCost.getStaff_no() == null || staffCost.getStaff_no().isEmpty()){
			staffCost.setErrorInfo(staffCost.getErrorInfo() + "工号不能为空;");
			b = false;
		}else {
			if(staffCostMap.containsKey(staffCost.getStaff_no())){
				staffCost.setErrorInfo(staffCost.getErrorInfo() + "工号重复;");
				b = false;
			}else {
				staffCostMap.put(staffCost.getStaff_no(), staffCost);
			}
			
			if(staffCost.getStaff_no().equals(staffCost.getLead_no())){
				staffCost.setErrorInfo(staffCost.getErrorInfo() + "和上级主管是同一个人;");
				b = false;
			}
		}
		
		if(staffCost.getStaff_name() == null || staffCost.getStaff_name().isEmpty()){
			staffCost.setErrorInfo(staffCost.getErrorInfo() + "名称不能为空;");
			b = false;
		}

		
		if(staffCost.getRecruiter_name() != null && staffCost.getRecruiter_name().length() > 0){
			OtherStaff otherStaff = otherStaffMap.get(staffCost.getRecruiter_name());
			if(otherStaff == null || otherStaff.getStaff_id().length() <= 0) {
				staffCost.setErrorInfo(staffCost.getErrorInfo() + "招聘专员名称错误;");
				b = false;
			}else {
				staffCost.setRecruiter(otherStaff.getStaff_id());
				staffCost.setRecruiter_name(otherStaff.getStaff_name());
			}	
		}
		
		if(staffCost.getIdentity_card_number() != null && staffCost.getIdentity_card_number().length() > 0){

			if(staffCostMap.containsKey(staffCost.getIdentity_card_number())){
				staffCost.setErrorInfo(staffCost.getErrorInfo() + "身份证号重复;");
				b = false;
			}else {


				if (staffCost.getIdentity_card_number().length() != 18) {
					staffCost.setErrorInfo(staffCost.getErrorInfo() + "身份证号码必须是18位;");
					b = false;
				} else if (!PubMethod.match(BusinessUtil.IDCARD, staffCost.getIdentity_card_number())) {

					staffCost.setErrorInfo(staffCost.getErrorInfo() + "身份证号码错误;");
					b = false;
				} else {

					String idcard = staffCost.getIdentity_card_number();

					String sexStr = idcard.substring(16, 17);
					if (Integer.parseInt(sexStr) % 2 == 1) {
						staffCost.setSex("男");
					} else {
						staffCost.setSex("女");
					}

					String birthdayStr = idcard.substring(6, 10) + "-" + idcard.substring(10, 12) + "-" + idcard.substring(12, 14);
					try {
						staffCost.setBirthday(DateKit.fmtStrToDate(birthdayStr, "yyyy-MM-dd"));
					} catch (Exception e) {
						staffCost.setErrorInfo(staffCost.getErrorInfo() + "身份证号码错误;");
						b = false;
					}
				}
			}
			
		}
		
		
		if(staffCost.getEducational_name() != null && staffCost.getEducational_name().length() >0){
			DicData dicData = PubMethod.getObj4Map(EnumDicType.EDUCATIONAL.name(),staffCost.getEducational_name(),allDicData);
			if(dicData != null){
				staffCost.setEducational(dicData.getId());
			}else {
				staffCost.setErrorInfo(staffCost.getErrorInfo() + "学历错误;");
				b = false;
			}
		}
		
		
		


		if(staffCost.getSpecialty_name() != null && staffCost.getSpecialty_name().length() >0){
			DicData dicData = PubMethod.getObj4Map(EnumDicType.SPECIALTY.name(),staffCost.getSpecialty_name(),allDicData);
			if(dicData != null){
				staffCost.setSpecialty(dicData.getId());
			}else {
				staffCost.setErrorInfo(staffCost.getErrorInfo() + "专业错误;");
				b = false;
			}
		}
		
		if(staffCost.getWorking_life() >= 100){
			staffCost.setErrorInfo(staffCost.getErrorInfo() + "工作年限太大;");
			b = false;
		}
		
		if(staffCost.getNationality_name() != null && staffCost.getNationality_name().length() >0){
			DicData dicData = PubMethod.getObj4Map(EnumDicType.NATIONALITY.name(),staffCost.getNationality_name(),allDicData);
			if(dicData != null){
				staffCost.setNationality(dicData.getId());
			}else {
				staffCost.setErrorInfo(staffCost.getErrorInfo() + "民族错误;");
				b = false;
			}
		}
		
		if(staffCost.getCensus_property_name() != null && staffCost.getCensus_property_name().length() >0){
			DicData dicData = PubMethod.getObj4Map(EnumDicType.HOUSEHOLDPROPERTY.name(),staffCost.getCensus_property_name(),allDicData);
			if(dicData != null){
				staffCost.setCensus_property(dicData.getId());
			}else {
				staffCost.setErrorInfo(staffCost.getErrorInfo() + "户籍性质错误;");
				b = false;
			}
		}
		
		
		
		if(staffCost.getInsurance_radix()!=null){
			InsuranceGrade insuranceGrade = insuranceGradeMap.get(staffCost.getInsurance_radix());
			if(insuranceGrade == null) {
				staffCost.setErrorInfo(staffCost.getErrorInfo() + "社保种类 错误;");
				b = false;
			}else {
				staffCost.setEndowment_insurance_bypersonal(insuranceGrade.getEndowment_insurance_bypersonal());
				staffCost.setMedical_insurance_bypersonal(insuranceGrade.getMedical_insurance_bypersonal());
				staffCost.setLosejob_insurance_bypersonal(insuranceGrade.getLosejob_insurance_bypersonal());
				staffCost.setReservefund_bypersonal(insuranceGrade.getReservefund_bypersonal());
				staffCost.setIncometax_bypersonal(insuranceGrade.getIncometax_bypersonal());
				staffCost.setEndowment_insurance_bycompany(insuranceGrade.getEndowment_insurance_bycompany());
				staffCost.setMedical_insurance_bycompany(insuranceGrade.getMedical_insurance_bycompany());
				staffCost.setLosejob_insurance_bycompany(insuranceGrade.getLosejob_insurance_bycompany());
				staffCost.setMaternity_insurance_bycompany(insuranceGrade.getMaternity_insurance_bycompany());
				staffCost.setJobharm_insurance_bycompany(insuranceGrade.getJobharm_insurance_bycompany());
				staffCost.setReservefund_bypcompany(insuranceGrade.getReservefund_bypcompany());				
				staffCost.setInsurance_grade_id(insuranceGrade.getInsurance_grade_id());			
				staffCost.setBase_cardinal(insuranceGrade.getBase_cardinal());
			}
		}
		
		
		
		//根据试用期工资计算 试用期的 基本 岗位 和绩效 工资
		if(staffCost.getTryout_salary() != 0){
			
			if(staffCost.getTryout_salary() >= staffCost.getBase_cardinal()){
				if(staffCost.getBase_cardinal() >= BusinessUtil.DEFAULT_BASIC_SALARY){
					staffCost.setTryout_basic_salary(BusinessUtil.DEFAULT_BASIC_SALARY);
					staffCost.setTryout_post_salary(staffCost.getBase_cardinal()-BusinessUtil.DEFAULT_BASIC_SALARY);
					staffCost.setTryout_performance_allowances(staffCost.getTryout_salary() - staffCost.getBase_cardinal());
				}else {
					staffCost.setTryout_basic_salary(staffCost.getBase_cardinal());
					staffCost.setTryout_post_salary(0);
					staffCost.setTryout_performance_allowances(staffCost.getTryout_salary() - staffCost.getBase_cardinal());
				}
			}else {
				if(staffCost.getTryout_salary() >= BusinessUtil.DEFAULT_BASIC_SALARY){
					staffCost.setTryout_basic_salary(BusinessUtil.DEFAULT_BASIC_SALARY);
					staffCost.setTryout_post_salary(staffCost.getTryout_salary()-BusinessUtil.DEFAULT_BASIC_SALARY);
					staffCost.setTryout_performance_allowances(0);
				}else {
					staffCost.setTryout_basic_salary(staffCost.getTryout_salary());
					staffCost.setTryout_post_salary(0);
					staffCost.setTryout_performance_allowances(0);
				}
			}
			
		}
		

		//根据正式工资计算  基本 岗位 和绩效 工资
		if(staffCost.getOfficial_salary() > 0){
			
			if(staffCost.getOfficial_salary() >= staffCost.getBase_cardinal()){
				if(staffCost.getBase_cardinal() >= BusinessUtil.DEFAULT_BASIC_SALARY){
					staffCost.setBasic_salary(BusinessUtil.DEFAULT_BASIC_SALARY);
					staffCost.setPost_salary(staffCost.getBase_cardinal()-BusinessUtil.DEFAULT_BASIC_SALARY);
					staffCost.setPerformance_allowances(staffCost.getOfficial_salary() - staffCost.getBase_cardinal());
				}else {
					staffCost.setBasic_salary(staffCost.getBase_cardinal());
					staffCost.setPost_salary(0);
					staffCost.setPerformance_allowances(staffCost.getOfficial_salary() - staffCost.getBase_cardinal());
				}
			}else {
				if(staffCost.getOfficial_salary() >= BusinessUtil.DEFAULT_BASIC_SALARY){
					staffCost.setBasic_salary(BusinessUtil.DEFAULT_BASIC_SALARY);
					staffCost.setPost_salary(staffCost.getOfficial_salary()-BusinessUtil.DEFAULT_BASIC_SALARY);
					staffCost.setPerformance_allowances(0);
				}else {
					staffCost.setBasic_salary(staffCost.getOfficial_salary());
					staffCost.setPost_salary(0);
					staffCost.setPerformance_allowances(0);
				}
			}
		}
		
		
		
		
		

		if(staffCost.getOutsource_staff() != null && staffCost.getOutsource_staff().length() > 0){
			if("1".equals(staffCost.getOutsource_staff()) || "是".equals(staffCost.getOutsource_staff()) || "yes".equals(staffCost.getOutsource_staff()) || "YES".equals(staffCost.getOutsource_staff())){
				if(b) {
					staffCost.setOutsource_staff("1");
				}
			}else if("0".equals(staffCost.getOutsource_staff()) || "否".equals(staffCost.getOutsource_staff()) || "no".equals(staffCost.getOutsource_staff()) || "NO".equals(staffCost.getOutsource_staff())){
				if(b) {
					staffCost.setOutsource_staff("0");
				}
			}else {
				staffCost.setErrorInfo(staffCost.getErrorInfo() + "是否外协人员 错误;");
				b = false;
			}
		}else {
			staffCost.setOutsource_staff("0");
		}

		if(staffCost.getCan_send_info() != null && staffCost.getCan_send_info().length() > 0){
			if("1".equals(staffCost.getCan_send_info()) || "是".equals(staffCost.getCan_send_info()) || "yes".equalsIgnoreCase(staffCost.getCan_send_info()) || "Y".equalsIgnoreCase(staffCost.getCan_send_info())){
				if(b) {
					staffCost.setCan_send_info("1");
				}
			}else if("0".equals(staffCost.getCan_send_info()) || "否".equals(staffCost.getCan_send_info()) || "no".equalsIgnoreCase(staffCost.getCan_send_info()) || "N".equalsIgnoreCase(staffCost.getCan_send_info())){
				if(b) {
					staffCost.setCan_send_info("0");
				}
			}else {
				staffCost.setErrorInfo(staffCost.getErrorInfo() + "是否允许发送信息 错误;");
				b = false;
			}
		}else {
			staffCost.setCan_send_info(BusinessUtil.STAFF_CAN_SEND_MESSAGE);
		}

		if(staffCost.getContract_type_name() != null && staffCost.getContract_type_name().length() >0){
			DicData dicData = PubMethod.getObj4Map(EnumDicType.CONTRACT_TYPE.name(),staffCost.getContract_type_name(),allDicData);
			if(dicData != null){
				staffCost.setContract_type(dicData.getId());
			}else {
				staffCost.setErrorInfo(staffCost.getErrorInfo() + "合同种类错误;");
				b = false;
			}
		}
		
		if(staffCost.getContract_attach_name() != null && staffCost.getContract_attach_name().length() >0){
			DicData dicData = PubMethod.getObj4Map(EnumDicType.CONTRACT_ATTACH.name(),staffCost.getContract_attach_name(),allDicData);
			if(dicData != null){
				staffCost.setContract_attach(dicData.getId());
			}else {
				staffCost.setErrorInfo(staffCost.getErrorInfo() + "合同归属错误;");
				b = false;
			}
		}		

		
		if(staffCost.getSocial_security_name() != null && staffCost.getSocial_security_name().length() >0){
			DicData dicData = PubMethod.getObj4Map(EnumDicType.SOCIAL_SECURITY.name(),staffCost.getSocial_security_name(),allDicData);
			if(dicData != null){
				staffCost.setSocial_security(dicData.getId());
			}else {
				staffCost.setErrorInfo(staffCost.getErrorInfo() + "社保说明错误;");
				b = false;
			}
		}
		
		

		if(staffCost.getInsured_city_name() != null && staffCost.getInsured_city_name().length() >0){
			DicData dicData = PubMethod.getObj4Map(EnumDicType.INSURED_CITY.name(),staffCost.getInsured_city_name(),allDicData);
			if(dicData != null){
				staffCost.setInsured_city(dicData.getId());
			}else {
				staffCost.setErrorInfo(staffCost.getErrorInfo() + "社保城市错误;");
				b = false;
			}
		}
		


		if(staffCost.getWorking_address_name() != null && staffCost.getWorking_address_name().length() >0){
			DicData dicData = PubMethod.getObj4Map(EnumDicType.WORKING_ADDRESS.name(),staffCost.getWorking_address_name(),allDicData);
			if(dicData != null){
				staffCost.setWorking_address(dicData.getId());
			}else {
				staffCost.setErrorInfo(staffCost.getErrorInfo() + "工作地点错误;");
				b = false;
			}
		}
		


		if(staffCost.getCertificate_name() != null && staffCost.getCertificate_name().length() >0){
			DicData dicData = PubMethod.getObj4Map(EnumDicType.CERTIFICATE.name(),staffCost.getCertificate_name(),allDicData);
			if(dicData != null){
				staffCost.setCertificate(dicData.getId());
			}else {
				staffCost.setErrorInfo(staffCost.getErrorInfo() + "证书错误;");
				b = false;
			}
		}




		if(staffCost.getChildren_education()<0){
			staffCost.setErrorInfo(staffCost.getErrorInfo() + "请填写正确的子女教育;");
			b = false;
		}

		if(staffCost.getContinuing_education()<0){
			staffCost.setErrorInfo(staffCost.getErrorInfo() + "请填写正确的继续教育;");
			b = false;
		}

		if(staffCost.getHousing_loans()<0 || staffCost.getHousing_loans() > 1000){
			staffCost.setErrorInfo(staffCost.getErrorInfo() + "请填写正确的住房贷款利息;");
			b = false;
		}

		if(staffCost.getHousing_rent()<0 || staffCost.getHousing_rent()>1500){
			staffCost.setErrorInfo(staffCost.getErrorInfo() + "请填写正确的住房租金;");
			b = false;
		}

		if(staffCost.getSupport_elderly()<0 || staffCost.getSupport_elderly()>2000){
			staffCost.setErrorInfo(staffCost.getErrorInfo() + "请填写正确的赡养老人;");
			b = false;
		}

		
		if(staffCost.getErrorInfo() != null && !staffCost.getErrorInfo().isEmpty()) {
			b = false;
		}

		if(b){
			staffCostMap.put(staffCost.getStaff_no(), staffCost);
			staffCostMap.put(staffCost.getStaff_name(), staffCost);
			staffCostMap.put(staffCost.getIdentity_card_number(), staffCost);
		}
		
		return b;
	}
	
	
	

	@RequestMapping(params = "method=downloadtemplet")
	public ModelAndView downloadtemplet(HttpServletRequest request,  HttpServletResponse response) throws Exception { 

		String sourceFile = this.getClass().getClassLoader().getResource("/templet/staffcost.xlsx").getPath();
		DownloadBaseUtil.download(  sourceFile,  "人员成本模板.xlsx" ,response,false);
		return null;  
	}  
	
	
	/**
	 * 导出Excel(普通方式导出)
	 * @param searchStaffCost
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=export")
	public void export(StaffCost searchStaffCost,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.STAFFVIEW.getId());		
		
		if(searchStaffCost.getDept_id() == null || searchStaffCost.getDept_id().isEmpty()){
			searchStaffCost.setDept_id(request.getParameter("dept.dept_id"));
		}
		if(searchStaffCost.getDept_name() == null || searchStaffCost.getDept_name().isEmpty()){
			searchStaffCost.setDept_name(request.getParameter("dept.dept_name"));
		}
		
		Pager<StaffCost> pager = staffCostService.queryStaffCost(searchStaffCost, null,userPermit, PubMethod.getPagerByAll(request, StaffCost.class));
		
		
		if(pager.getResultList() != null && !pager.getResultList().isEmpty()){

			double tax_rate = 0;
			double staff_costs_threshold = 0;
			
			Params params = new Params();
			params.setParam_key("tax.rate");
			List<Params> paramList = paramsService.queryAllParams(params);
			if(paramList != null && paramList.size() > 0){
				tax_rate = Double.parseDouble(paramList.get(0).getParam_value());
			}

			params.setParam_key("staff.costs.threshold");
			paramList = paramsService.queryAllParams(params);
			if(paramList != null && paramList.size() > 0){
				staff_costs_threshold = Double.parseDouble(paramList.get(0).getParam_value());
			}

			request.setAttribute("staff_costs_threshold", staff_costs_threshold);

			for(StaffCost staffCost : pager.getResultList()){
				staffCost.setDifference(staffCost.getQustomerquotes() * (1-tax_rate) - staffCost.getTotalcost());
				staffCost.setCan_send_info(this.getMsg("boolean." + (staffCost.getCan_send_info()==null?"":staffCost.getCan_send_info()), request));
				staffCost.setOutsource_staff(this.getMsg("boolean." + (staffCost.getOutsource_staff()==null?"":staffCost.getOutsource_staff()), request));
			}
			
		}
		
		
		try{
			BusinessExcel.export(res, null, pager.getResultList(), StaffCost.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	

	/**
	 * 导出Excel(按照招聘导出)
	 * @param searchStaffCost
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=exportByRecruiter")
	public void exportByRecruiter(StaffCost searchStaffCost,HttpServletResponse res,HttpServletRequest request){
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.STAFFVIEW.getId());		

		searchStaffCost.setOrderby("staff.recruiter desc, staff.join_datetime, staff.leave_job_datetime");
		
		Pager<StaffCost> pager = staffCostService.queryStaffCost(searchStaffCost, null,userPermit, PubMethod.getPagerByAll(request, StaffCost.class));
		
		StaffCost staffCostSum = staffCostService.getStaffCostNum(searchStaffCost, null, userPermit);
		
		List<StaffCostExcel> excels = new ArrayList<StaffCostExcel>();
		
		if(pager.getResultList() != null){
			
			double tax_rate = 0;
			
			Params params = new Params();
			params.setParam_key("tax.rate");
			List<Params> paramList = paramsService.queryAllParams(params);
			if(paramList != null && paramList.size() > 0){
				tax_rate = Double.parseDouble(paramList.get(0).getParam_value());
			}
			
			
			
			String oldRecruiter = null;
			for(StaffCost staffCost : pager.getResultList()){
				StaffCostExcel excel = new StaffCostExcel();
				
				BeanUtils.copyProperties(staffCost, excel);
				excel.setDiff(excel.getFirstquotes() * (1 - tax_rate) - excel.getTotalcost());
				if(null == excel.getRecruiter_name() || excel.getRecruiter_name().isEmpty()) {
					excel.setRecruiter_name("未知招聘人");
				}
				
				if(!excel.getRecruiter_name().equals(oldRecruiter)){
					oldRecruiter = excel.getRecruiter_name();
				}else {
					excel.setRecruiter_name("");
				}
				
				excels.add(excel);
			}
		}
		
		try{
			List<String> heads = new ArrayList<String>();
			heads.add("招聘统计表");
			if(searchStaffCost.getDate1() == null && searchStaffCost.getDate2() == null ){
				heads.add("统计时段：");
			}else {
				heads.add("统计时段："+DateKit.dateToStringByChinese("yyyy-MM-dd", searchStaffCost.getDate1())+"至"+DateKit.dateToStringByChinese("yyyy-MM-dd", searchStaffCost.getDate2()));
			}
			heads.add("入职人数："+staffCostSum.getJoinnum()+"人      离职人数："+staffCostSum.getLeavenum()+"人  ");
			BusinessExcel.export(res,heads, excels, StaffCostExcel.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}	
	
	
	@RequestMapping(params = "method=lookup")
	public String lookup(StaffCost searchStaffCost,ConditionStaffCost staffCostCondition,HttpServletResponse res,HttpServletRequest request){
		
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.STAFFVIEW.getId());	
		if(searchStaffCost.getDept_id() == null || searchStaffCost.getDept_id().isEmpty()){
			searchStaffCost.setDept_id(request.getParameter("dept.dept_id"));
		}
		if(searchStaffCost.getDept_name() == null || searchStaffCost.getDept_name().isEmpty()){
			searchStaffCost.setDept_name(request.getParameter("dept.dept_name"));
		}
		
		Pager<StaffCost> pager = staffCostService.queryStaffCost(searchStaffCost,staffCostCondition, userPermit, PubMethod.getPager(request, StaffCost.class));

		if(pager.getResultList() != null && !pager.getResultList().isEmpty()){

			double tax_rate = 0;
			double staff_costs_threshold = 0;
			
			Params params = new Params();
			params.setParam_key("tax.rate");
			List<Params> paramList = paramsService.queryAllParams(params);
			if(paramList != null && paramList.size() > 0){
				tax_rate = Double.parseDouble(paramList.get(0).getParam_value());
			}

			params.setParam_key("staff.costs.threshold");
			paramList = paramsService.queryAllParams(params);
			if(paramList != null && paramList.size() > 0){
				staff_costs_threshold = Double.parseDouble(paramList.get(0).getParam_value());
			}	

			request.setAttribute("staff_costs_threshold", staff_costs_threshold);

			for(StaffCost staffCost : pager.getResultList()){
				staffCost.setDifference(staffCost.getQustomerquotes() * (1-tax_rate) - staffCost.getTotalcost());
			}
			
		}				
		
		PubMethod.setRequestPager(request, pager,StaffCost.class);		

		return "basicdata/staff_cost_search";
	}
	
	
	/**
	 * 人员利润分析
	 * @param searchStaffCost
	 * @param staffCostCondition
	 * @param res
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=profitAnalysis")
	public String profitAnalysis(StaffCost searchStaffCost,ConditionStaffCost staffCostCondition,HttpServletResponse res,HttpServletRequest request){
		
				
		
		double tax_rate = this.computeTaxRate();		
		searchStaffCost.setTax_rate(tax_rate);		
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.STAFFPROFITVIEW.getId());	
		
		Pager<StaffCostAnalysis> pager = staffCostService.queryProfitAnalysis(searchStaffCost,null, userPermit, PubMethod.getPager(request, StaffCostAnalysis.class));
		PubMethod.setRequestPager(request, pager,StaffCostAnalysis.class);		
		
		
		return "basicdata/staff_cost_profit_analysis";
	}
	

	@RequestMapping(params = "method=exportByAnalysis")
	public void exportByAnalysis(StaffCost searchStaffCost,HttpServletResponse res,HttpServletRequest request){
		
		double tax_rate = this.computeTaxRate();		
		searchStaffCost.setTax_rate(tax_rate);		
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.STAFFPROFITVIEW.getId());	
		
		Pager<StaffCostAnalysis> pager = staffCostService.queryProfitAnalysis(searchStaffCost,null, userPermit, PubMethod.getPagerByAll( StaffCostAnalysis.class));
		
		try{
			BusinessExcel.export(res, null, pager.getResultList(), StaffCostAnalysis.class);
		}catch(Exception e){
			
		}
	
	}
	

	/**
	 * 明细界面
	 * @param statistics
	 * @param res
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=queryAnalysisDetails")
	public String queryAnalysisDetails(Statistics statistics,HttpServletResponse res,HttpServletRequest request){
		
		double tax_rate = this.computeTaxRate();
		statistics.setTax_rate(tax_rate);
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.STAFFPROFITVIEW.getId());		
		
		Pager<StatisticsDetail> pager = staffCostService.queryAnalysisDetails(statistics, userPermit, PubMethod.getPager(request, StatisticsDetail.class));
		PubMethod.setRequestPager(request, pager,StatisticsDetail.class);	
		return "basicdata/staff_cost_profit_analysis_detaillist";
	}
	

	@RequestMapping(params = "method=exportByAnalysisDetails")
	public void exportByAnalysisDetails(Statistics statistics,HttpServletResponse res,HttpServletRequest request){
		double tax_rate = this.computeTaxRate();
		statistics.setTax_rate(tax_rate);
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.STAFFPROFITVIEW.getId());		
		
		Pager<StatisticsDetail> pager = staffCostService.queryAnalysisDetails(statistics, userPermit, PubMethod.getPagerByAll(StatisticsDetail.class));
		try{
			BusinessExcel.export(res, null, pager.getResultList(), StatisticsDetail.class);
		}catch(Exception e){
			
		}
	
	}
	
	
	
	
	/**
	 * 为查询上级主管, 要包括人员成本和总部人员
	 * @param searchStaffCost
	 * @param staffCostCondition
	 * @param res
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=lookup2")
	public String lookup2(StaffCost searchStaffCost,ConditionStaffCost staffCostCondition,HttpServletResponse res,HttpServletRequest request){
		
		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.STAFFVIEW.getId());			
		Pager<StaffCost> pager = staffCostService.queryAllStaffCost(searchStaffCost,staffCostCondition, userPermit, PubMethod.getPager(request, StaffCost.class));
		PubMethod.setRequestPager(request, pager,StaffCost.class);
		return "basicdata/staff_cost_search2";
	}

	@RequestMapping(params = "method=list")
	public String list(StaffCost searchStaffCost,HttpServletResponse res,HttpServletRequest request){

		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.STAFFVIEW.getId());	
		
		if(searchStaffCost.getDept_id() == null || searchStaffCost.getDept_id().isEmpty()){
			searchStaffCost.setDept_id(request.getParameter("dept.dept_id"));
		}
		if(searchStaffCost.getDept_name() == null || searchStaffCost.getDept_name().isEmpty()){
			searchStaffCost.setDept_name(request.getParameter("dept.dept_name"));
		}

		
		Pager<StaffCost> pager = staffCostService.queryStaffCost(searchStaffCost, null, userPermit, PubMethod.getPager(request, StaffCost.class));
		

		if(pager.getResultList() != null && !pager.getResultList().isEmpty()){

			double tax_rate = 0;
			double staff_costs_threshold = 0;
			
			Params params = new Params();
			params.setParam_key("tax.rate");
			List<Params> paramList = paramsService.queryAllParams(params);
			if(paramList != null && paramList.size() > 0){
				tax_rate = Double.parseDouble(paramList.get(0).getParam_value());
			}

			params.setParam_key("staff.costs.threshold");
			paramList = paramsService.queryAllParams(params);
			if(paramList != null && paramList.size() > 0){
				staff_costs_threshold = Double.parseDouble(paramList.get(0).getParam_value());
			}	

			request.setAttribute("staff_costs_threshold", staff_costs_threshold);

			for(StaffCost staffCost : pager.getResultList()){
				
				staffCost.setDifference(staffCost.getQustomerquotes() * (1-tax_rate) - staffCost.getTotalcost());
				
				if(staffCost.getConfirmation_date() != null) {
					staffCost.setConfirmation_month(DateKit.fmtDateToYM(staffCost.getConfirmation_date()));
				}

				if(staffCost.getContract_end_date() != null) {
					staffCost.setContract_end_month(DateKit.fmtDateToYM(staffCost.getContract_end_date()));
				}
			}
			
		}
		
		
		PubMethod.setRequestPager(request, pager,StaffCost.class);
		
		request.setAttribute("staffCost", searchStaffCost);		

		request.setAttribute("curr_ym", DateKit.fmtDateToYM(new Date()));

		request.setAttribute(EnumOperationType.READ.getKey(), userPermit.getPermit_id());

		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.STAFFADD.getId());
		request.setAttribute(EnumOperationType.INSERT.getKey(), userPermit1.getPermit_id());
		
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.STAFFUPDATE.getId());
		request.setAttribute(EnumOperationType.UPDATE.getKey(), userPermit1.getPermit_id());	

		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.STAFFDELETE.getId());
		request.setAttribute(EnumOperationType.DELETE.getKey(), userPermit1.getPermit_id());
		
		return "basicdata/staff_cost_list";
	}	
	
	private void paramprocess(HttpServletRequest request,StaffCost staffCost){	
		
		if(staffCost == null) {
			staffCost = new StaffCost();
		}
		
		staffCost.setLead_id(request.getParameter("lead.staff_id"));
		staffCost.setLead_name(request.getParameter("lead.staff_name"));
		staffCost.setLead_no(request.getParameter("lead.staff_no"));
		
		
		staffCost.setRecruiter(request.getParameter("job.user_id"));
		staffCost.setRecruiter_name(request.getParameter("job.user_name"));
		
		staffCost.setInsurance_grade_id(request.getParameter("grade.insurance_grade_id"));
		staffCost.setInsurance_radix(request.getParameter("grade.insurance_radix"));
		String base_cardinal = request.getParameter("grade.base_cardinal");
		if(StringUtils.isNotEmpty(base_cardinal)) {
			staffCost.setBase_cardinal(Double.parseDouble(base_cardinal));
		}

		

		staffCost.setNationality(request.getParameter("nation.id"));
		staffCost.setNationality_name(request.getParameter("nation.dic_data_name"));
		
		staffCost.setCensus_property(request.getParameter("hp.id"));
		staffCost.setCensus_property_name(request.getParameter("hp.dic_data_name"));
		
		staffCost.setEducational(request.getParameter("edu.id"));
		staffCost.setEducational_name(request.getParameter("edu.dic_data_name"));
		

		staffCost.setSpecialty(request.getParameter("specialty.id"));
		staffCost.setSpecialty_name(request.getParameter("specialty.dic_data_name"));
		
		
		staffCost.setContract_attach(request.getParameter("contract_attach.id"));
		staffCost.setContract_attach_name(request.getParameter("contract_attach.dic_data_name"));
		
		staffCost.setContract_type(request.getParameter("contract_type.id"));
		staffCost.setContract_type_name(request.getParameter("contract_type.dic_data_name"));

		staffCost.setSocial_security(request.getParameter("social_security.id"));
		staffCost.setSocial_security_name(request.getParameter("social_security.dic_data_name"));
		


		staffCost.setInsured_city(request.getParameter("insuredcity.id"));
		staffCost.setInsured_city_name(request.getParameter("insuredcity.dic_data_name"));

		staffCost.setWorking_address(request.getParameter("workingaddress.id"));
		staffCost.setWorking_address_name(request.getParameter("workingaddress.dic_data_name"));

		staffCost.setCertificate(request.getParameter("certificate.id"));
		staffCost.setCertificate_name(request.getParameter("certificate.dic_data_name"));
		
		
		String value = request.getParameter("grade.endowment_insurance_bypersonal");
		try{
			double d = Double.parseDouble(value);
			staffCost.setEndowment_insurance_bypersonal(d);
		}catch(Exception e){}		

		value = request.getParameter("grade.medical_insurance_bypersonal");
		try{
			double d = Double.parseDouble(value);
			staffCost.setMedical_insurance_bypersonal(d);
		}catch(Exception e){}		

		value = request.getParameter("grade.losejob_insurance_bypersonal");
		try{
			double d = Double.parseDouble(value);
			staffCost.setLosejob_insurance_bypersonal(d);
		}catch(Exception e){}		

		value = request.getParameter("grade.reservefund_bypersonal");
		try{
			double d = Double.parseDouble(value);
			staffCost.setReservefund_bypersonal(d);
		}catch(Exception e){}		

		value = request.getParameter("grade.incometax_bypersonal");
		try{
			double d = Double.parseDouble(value);
			staffCost.setIncometax_bypersonal(d);
		}catch(Exception e){}		

		value = request.getParameter("grade.endowment_insurance_bycompany");
		try{
			double d = Double.parseDouble(value);
			staffCost.setEndowment_insurance_bycompany(d);
		}catch(Exception e){}		

		value = request.getParameter("grade.medical_insurance_bycompany");
		try{
			double d = Double.parseDouble(value);
			staffCost.setMedical_insurance_bycompany(d);
		}catch(Exception e){}		

		value = request.getParameter("grade.losejob_insurance_bycompany");
		try{
			double d = Double.parseDouble(value);
			staffCost.setLosejob_insurance_bycompany(d);
		}catch(Exception e){}		

		value = request.getParameter("grade.maternity_insurance_bycompany");
		try{
			double d = Double.parseDouble(value);
			staffCost.setMaternity_insurance_bycompany(d);
		}catch(Exception e){}		

		value = request.getParameter("grade.jobharm_insurance_bycompany");
		try{
			double d = Double.parseDouble(value);
			staffCost.setJobharm_insurance_bycompany(d);
		}catch(Exception e){}		

		value = request.getParameter("grade.reservefund_bypcompany");
		try{
			double d = Double.parseDouble(value);
			staffCost.setReservefund_bypcompany(d);
		}catch(Exception e){}
		
	}
	

	@RequestMapping(params = "method=toEdit")
	public String toEdit(StaffCost searchStaffCost,HttpServletResponse res,HttpServletRequest request){
		StaffCost staffCost = null;
		if(searchStaffCost != null && searchStaffCost.getStaff_id()!=null){
			request.setAttribute("next_operation", "updateStaffCost");
			staffCost = staffCostService.getStaffCost(searchStaffCost.getStaff_id());	
			if(staffCost.getDelete_flag().equals(BusinessUtil.DELETEED)){
				return this.ajaxForwardError(request, "该人员已经删除，不能再操作！",true);
			}
			
			StaffRaiseRecord staffRaiseRecord = new StaffRaiseRecord(); 
			staffRaiseRecord.setStaff_id(searchStaffCost.getStaff_id());
			Pager<StaffRaiseRecord> staffRaiseRecords = staffRaiseRecordService.queryRaiseRecord(staffRaiseRecord,PubMethod.getPagerByAll(StaffRaiseRecord.class));
			request.setAttribute("raiserecords", staffRaiseRecords.getResultList());

			StaffRewardPenalty staffRewardPenalty = new StaffRewardPenalty(); 
			staffRewardPenalty.setStaff_id(searchStaffCost.getStaff_id());
			Pager<StaffRewardPenalty> staffRewardPenaltys = staffRewardPenaltyService.queryStaffRewardPenalty(staffRewardPenalty,PubMethod.getPagerByAll(StaffRewardPenalty.class));
			request.setAttribute("rewardpenaltys", staffRewardPenaltys.getResultList());

			StaffAssessment staffAssessment = new StaffAssessment(); 
			staffAssessment.setStaff_id(searchStaffCost.getStaff_id());
			Pager<StaffAssessment> staffAssessments = staffAssessmentService.queryStaffAssessment(staffAssessment,PubMethod.getPagerByAll(StaffAssessment.class));
			request.setAttribute("assessments", staffAssessments.getResultList());

			StaffPositions staffPositions = new StaffPositions(); 
			staffPositions.setStaff_id(searchStaffCost.getStaff_id());
			Pager<StaffPositions> staffPositionss = staffPositionsService.queryStaffPositions(staffPositions,PubMethod.getPagerByAll(StaffPositions.class));
			request.setAttribute("positionss", staffPositionss.getResultList());
			
			
			List<ProjectStaff> projectStaffs = projectService.queryProjectStaff(searchStaffCost);
			if(projectStaffs != null){
				for(ProjectStaff projectStaff : projectStaffs){
					projectStaff.setStart_end_dates(PubMethod.twoDate2Str(projectStaff.getJoin_project_datetime(), projectStaff.getLeave_project_datetime()));
				}
			}
			request.setAttribute("project_staffs", projectStaffs);
			
			
		}else {
			request.setAttribute("next_operation", "addStaffCost");
			
			User sessionUser = PubMethod.getUser(request);
			staffCost = new StaffCost();	
			staffCost.setBuild_userid(sessionUser.getUser_id());
			staffCost.setBuild_username(sessionUser.getUser_name());
			staffCost.setBuild_datetime(PubMethod.getCurrentDate());
			
		}

		request.setAttribute("staffCost1", staffCost);
		request.setAttribute("DEFAULT_BASIC_SALARY", BusinessUtil.DEFAULT_BASIC_SALARY);
		return "basicdata/staff_cost_edit";
		
	}
	

	@RequestMapping(params = "method=toView")
	public String toView(StaffCost searchStaffCost,HttpServletResponse res,HttpServletRequest request){
		
		StaffCost staffCost = staffCostService.getStaffCost(searchStaffCost.getStaff_id());
		request.setAttribute("staffCost1", staffCost);

		StaffRaiseRecord staffRaiseRecord = new StaffRaiseRecord(); 
		staffRaiseRecord.setStaff_id(searchStaffCost.getStaff_id());
		Pager<StaffRaiseRecord> staffRaiseRecords = staffRaiseRecordService.queryRaiseRecord(staffRaiseRecord,PubMethod.getPagerByAll(StaffRaiseRecord.class));
		request.setAttribute("raiserecords", staffRaiseRecords.getResultList());

		StaffRewardPenalty staffRewardPenalty = new StaffRewardPenalty(); 
		staffRewardPenalty.setStaff_id(searchStaffCost.getStaff_id());
		Pager<StaffRewardPenalty> staffRewardPenaltys = staffRewardPenaltyService.queryStaffRewardPenalty(staffRewardPenalty,PubMethod.getPagerByAll(StaffRewardPenalty.class));
		request.setAttribute("rewardpenaltys", staffRewardPenaltys.getResultList());

		StaffAssessment staffAssessment = new StaffAssessment(); 
		staffAssessment.setStaff_id(searchStaffCost.getStaff_id());
		Pager<StaffAssessment> staffAssessments = staffAssessmentService.queryStaffAssessment(staffAssessment,PubMethod.getPagerByAll(StaffAssessment.class));
		request.setAttribute("assessments", staffAssessments.getResultList());

		StaffPositions staffPositions = new StaffPositions(); 
		staffPositions.setStaff_id(searchStaffCost.getStaff_id());
		Pager<StaffPositions> staffPositionss = staffPositionsService.queryStaffPositions(staffPositions,PubMethod.getPagerByAll(StaffPositions.class));
		request.setAttribute("positionss", staffPositionss.getResultList());
		
		
		List<ProjectStaff> projectStaffs = projectService.queryProjectStaff(searchStaffCost);
		if(projectStaffs != null){
			for(ProjectStaff projectStaff : projectStaffs){
				projectStaff.setStart_end_dates(PubMethod.twoDate2Str(projectStaff.getJoin_project_datetime(), projectStaff.getLeave_project_datetime()));
			}
		}
		request.setAttribute("project_staffs", projectStaffs);
		
		
		return "basicdata/staff_cost_view";
		
	}	
	
	
	@RequestMapping(params = "method=toProjectStaffView")
	public String toProjectStaffView(StaffCost searchStaffCost,HttpServletResponse res,HttpServletRequest request){
		
		List<ProjectStaff> projectStaffs = projectService.queryProjectStaff(searchStaffCost);
		request.setAttribute("projectStaffs", projectStaffs);		
		return "basicdata/staff_projectcost_view";
		
	}
	
	
	/**
	 * 获取加薪记录
	 * @param staffCost
	 * @param sessionUser
	 * @param request
	 * @return
	 */
	private StaffRaiseRecord[] getStaffRaiseRecord(StaffCost staffCost,User sessionUser,HttpServletRequest request){

		String[] rowIndex = request.getParameterValues("index_raise_record_table");

		List<StaffRaiseRecord> list = new ArrayList<StaffRaiseRecord>();
		if(rowIndex != null && rowIndex.length >0){
			
			for(String index : rowIndex) {
				StaffRaiseRecord staffRaiseRecord = new StaffRaiseRecord();	
				String id = request.getParameter("items["+index+"]."+"id_1");
				String raise_time = request.getParameter("items["+index+"]."+"raise_time_1");
				String amount = request.getParameter("items["+index+"]."+"amount_1");
				String description = request.getParameter("items["+index+"]."+"description_1");
				
				staffRaiseRecord.setId(id);
				staffRaiseRecord.setRaise_time(DateKit.fmtStrToDate(raise_time));
				staffRaiseRecord.setAmount(Double.parseDouble(amount));
				staffRaiseRecord.setDescription(description);
				staffRaiseRecord.setStaff_id(staffCost.getStaff_id());
				staffRaiseRecord.setStaff_name(staffCost.getStaff_name());
				
				staffRaiseRecord.setBuild_datetime(PubMethod.getCurrentDate());
				staffRaiseRecord.setBuild_userid(sessionUser.getUser_id());
				staffRaiseRecord.setBuild_username(sessionUser.getUser_name());		
				
				list.add(staffRaiseRecord);
			}
			
		}
		
		return list.toArray(new StaffRaiseRecord[list.size()]);
		
	}
	
	
	/**
	 * 获取奖惩
	 * @param staffCost
	 * @param sessionUser
	 * @param request
	 * @return
	 */
	private StaffRewardPenalty[] getStaffRewardPenalty(StaffCost staffCost,User sessionUser,HttpServletRequest request){

		String[] rowIndex = request.getParameterValues("index_reward_penalty_table");

		List<StaffRewardPenalty> list = new ArrayList<StaffRewardPenalty>();
		if(rowIndex != null && rowIndex.length >0){
			
			for(String index : rowIndex) {
				StaffRewardPenalty staffRewardPenalty = new StaffRewardPenalty();	
				String id = request.getParameter("items["+index+"]."+"id_2");
				String rp_time = request.getParameter("items["+index+"]."+"rp_time_2");
				String amount = request.getParameter("items["+index+"]."+"amount_2");
				String description = request.getParameter("items["+index+"]."+"description_2");
				
				staffRewardPenalty.setId(id);
				staffRewardPenalty.setRp_time(DateKit.fmtStrToDate(rp_time));
				staffRewardPenalty.setAmount(Double.parseDouble(amount));
				staffRewardPenalty.setDescription(description);
				staffRewardPenalty.setStaff_id(staffCost.getStaff_id());
				staffRewardPenalty.setStaff_name(staffCost.getStaff_name());

				staffRewardPenalty.setBuild_datetime(PubMethod.getCurrentDate());
				staffRewardPenalty.setBuild_userid(sessionUser.getUser_id());
				staffRewardPenalty.setBuild_username(sessionUser.getUser_name());
				
				list.add(staffRewardPenalty);
			}
			
		}
		
		return list.toArray(new StaffRewardPenalty[list.size()]);
		
	}

	/**
	 * 获取绩效考核
	 * @param staffCost
	 * @param sessionUser
	 * @param request
	 * @return
	 */
	private StaffAssessment[] getStaffAssessment(StaffCost staffCost,User sessionUser,HttpServletRequest request){

		String[] rowIndex = request.getParameterValues("index_assessment_table");

		List<StaffAssessment> list = new ArrayList<StaffAssessment>();
		if(rowIndex != null && rowIndex.length >0){
			
			for(String index : rowIndex) {
				StaffAssessment staffAssessment = new StaffAssessment();	
				String id = request.getParameter("items["+index+"]."+"id_3");
				String assessment_time = request.getParameter("items["+index+"]."+"assessment_time_3");
				String attendance_rate = request.getParameter("items["+index+"]."+"attendance_rate_3");
				String score = request.getParameter("items["+index+"]."+"score_3");
				String description = request.getParameter("items["+index+"]."+"description_3");
				
				staffAssessment.setId(id);
				staffAssessment.setAssessment_time(DateKit.fmtStrToDate(assessment_time));
				staffAssessment.setAttendance_rate(Double.parseDouble(attendance_rate));
				staffAssessment.setScore(Integer.parseInt(score));
				staffAssessment.setDescription(description);
				staffAssessment.setStaff_id(staffCost.getStaff_id());
				staffAssessment.setStaff_name(staffCost.getStaff_name());

				staffAssessment.setBuild_datetime(PubMethod.getCurrentDate());
				staffAssessment.setBuild_userid(sessionUser.getUser_id());
				staffAssessment.setBuild_username(sessionUser.getUser_name());
				
				list.add(staffAssessment);
			}
			
		}
		
		return list.toArray(new StaffAssessment[list.size()]);
		
	}

	
	/**
	 * 获取晋升记录
	 * @param staffCost
	 * @param sessionUser
	 * @param request
	 * @return
	 */
	private StaffPositions[] getStaffPositions(StaffCost staffCost,User sessionUser,HttpServletRequest request){

		String[] rowIndex = request.getParameterValues("index_positions_table");

		List<StaffPositions> list = new ArrayList<StaffPositions>();
		if(rowIndex != null && rowIndex.length >0){
			
			for(String index : rowIndex) {
				StaffPositions staffPositions = new StaffPositions();	
				String id = request.getParameter("items["+index+"]."+"id_4");
				String positions_time = request.getParameter("items["+index+"]."+"positions_time_4");
				String level = request.getParameter("items["+index+"]."+"level_4");
				String description = request.getParameter("items["+index+"]."+"description_4");
				
				staffPositions.setId(id);
				staffPositions.setPositions_time(DateKit.fmtStrToDate(positions_time));
				staffPositions.setLevel(level);
				staffPositions.setDescription(description);
				staffPositions.setStaff_id(staffCost.getStaff_id());
				staffPositions.setStaff_name(staffCost.getStaff_name());

				staffPositions.setBuild_datetime(PubMethod.getCurrentDate());
				staffPositions.setBuild_userid(sessionUser.getUser_id());
				staffPositions.setBuild_username(sessionUser.getUser_name());
				
				list.add(staffPositions);
			}
			
		}
		
		return list.toArray(new StaffPositions[list.size()]);
		
	}



	

	@RequestMapping(params = "method=addStaffCost")
	public String addStaffCost(StaffCost addStaffCost,HttpServletResponse res,HttpServletRequest request){
		
		StaffCost staffCost = addStaffCost;	
		paramprocess(request,staffCost);
		
		staffCost.setQustomerquotes(staffCost.getFirstquotes());
		

		User sessionUser = PubMethod.getUser(request);
		staffCost.setStaff_id(IDKit.generateId());
		staffCost.setBuild_datetime(PubMethod.getCurrentDate());
		staffCost.setBuild_userid(sessionUser.getUser_id());
		staffCost.setBuild_username(sessionUser.getUser_name());
		staffCost.setDelete_flag(BusinessUtil.NOT_DELETEED);
		

		int count = 0;
		try{
			

			StaffAssessment[] staffAssessments = this.getStaffAssessment(staffCost, sessionUser, request);

			StaffPositions[] staffPositionss = this.getStaffPositions(staffCost, sessionUser, request);
			
			StaffRaiseRecord[] staffRaiseRecords = this.getStaffRaiseRecord(staffCost, sessionUser, request);
			
			StaffRewardPenalty[] staffRewardPenaltys = this.getStaffRewardPenalty(staffCost, sessionUser, request);
			
			count = staffCostService.addStaffCost(staffCost,staffAssessments,staffPositionss,staffRaiseRecords,staffRewardPenaltys);	
			
			
			
			
				
		}catch(Exception e){
			
		}
		if(count == 1) 		{
			return this.ajaxForwardSuccess(request, rel, true);
		}
		else {
			return this.ajaxForwardError(request, "数据格式错误！", true);
		}
		
	}
	

	

	@RequestMapping(params = "method=updateStaffCost")
	public String updateStaffCost(StaffCost updateStaffCost,HttpServletResponse res,HttpServletRequest request){
		
		StaffCost staffCost = updateStaffCost;	
		paramprocess(request,staffCost);		

		User sessionUser = PubMethod.getUser(request);
		int count = 0;
		try{

			StaffAssessment[] staffAssessments = this.getStaffAssessment(staffCost, sessionUser, request);

			StaffPositions[] staffPositionss = this.getStaffPositions(staffCost, sessionUser, request);
			
			StaffRaiseRecord[] staffRaiseRecords = this.getStaffRaiseRecord(staffCost, sessionUser, request);
			
			StaffRewardPenalty[] staffRewardPenaltys = this.getStaffRewardPenalty(staffCost, sessionUser, request);
			
			count = staffCostService.updateStaffCost(staffCost,staffAssessments,staffPositionss,staffRaiseRecords,staffRewardPenaltys);
				
			//BusinessSend.send(staffCost.getClass().getSimpleName(), staffCost.getStaff_id());
		}catch(Exception e){
			throw e;
		}
		if(count == 1) 		{
			return this.ajaxForwardSuccess(request, rel, true);
		}
		else {
			return this.ajaxForwardError(request, "数据格式错误！", true);
		}
		
	}	
	
	
	
	
	/**
	 * 社保等级 同步到人员成本
	 * @param res
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=synchroInsuranceGrade")
	public String synchroInsuranceGrade(HttpServletResponse res,HttpServletRequest request){
		
		try{

			String[] insurance_grade_ids = request.getParameterValues("ids");
			
			for(String insurance_grade_id : insurance_grade_ids) {
				
				InsuranceGrade insuranceGrade =insuranceGradeService.getInsuranceGrade(insurance_grade_id);
				StaffCost staffCost = new StaffCost();
				staffCost.setInsurance_grade_id(insurance_grade_id);
				List<StaffCost> list = staffCostService.getAllStaffBySearch(staffCost);
				if(list != null && list.size() >0){
					for(StaffCost staffCost1 : list){
						if(
							insuranceGrade.getEndowment_insurance_bypersonal() != staffCost1.getEndowment_insurance_bypersonal() ||
							insuranceGrade.getMedical_insurance_bypersonal() != staffCost1.getMedical_insurance_bypersonal() ||
							insuranceGrade.getLosejob_insurance_bypersonal() != staffCost1.getLosejob_insurance_bypersonal() ||
							insuranceGrade.getReservefund_bypersonal() != staffCost1.getReservefund_bypersonal() ||
							
							insuranceGrade.getEndowment_insurance_bycompany() != staffCost1.getEndowment_insurance_bycompany() ||
							insuranceGrade.getMedical_insurance_bycompany() != staffCost1.getMedical_insurance_bycompany() ||
							insuranceGrade.getLosejob_insurance_bycompany() != staffCost1.getLosejob_insurance_bycompany() ||
							insuranceGrade.getMaternity_insurance_bycompany() != staffCost1.getMaternity_insurance_bycompany() ||
							insuranceGrade.getJobharm_insurance_bycompany() != staffCost1.getJobharm_insurance_bycompany() ||
							insuranceGrade.getReservefund_bypcompany() != staffCost1.getReservefund_bypcompany() 
						){
							staffCost1.setEndowment_insurance_bypersonal(insuranceGrade.getEndowment_insurance_bypersonal());
							staffCost1.setMedical_insurance_bypersonal(insuranceGrade.getMedical_insurance_bypersonal());
							staffCost1.setLosejob_insurance_bypersonal(insuranceGrade.getLosejob_insurance_bypersonal());
							staffCost1.setReservefund_bypersonal(insuranceGrade.getReservefund_bypersonal());
	
							staffCost1.setEndowment_insurance_bycompany(insuranceGrade.getEndowment_insurance_bycompany());
							staffCost1.setMedical_insurance_bycompany(insuranceGrade.getMedical_insurance_bycompany());
							staffCost1.setLosejob_insurance_bycompany(insuranceGrade.getLosejob_insurance_bycompany());
							staffCost1.setMaternity_insurance_bycompany(insuranceGrade.getMaternity_insurance_bycompany());
							staffCost1.setJobharm_insurance_bycompany(insuranceGrade.getJobharm_insurance_bycompany());
							staffCost1.setReservefund_bypcompany(insuranceGrade.getReservefund_bypcompany());
							
							staffCostService.updateStaffCost(staffCost1, null, null, null, null);
						}
					}
				}
			}
		}catch(Exception e){
			return this.ajaxForwardError(request, "数据格式错误！", false);
		}

		return this.ajaxForwardSuccess(request, rel, false);
		
	}	
	

	@RequestMapping(params = "method=deleteStaffCost")
	public String deleteStaffCost(HttpServletResponse res,HttpServletRequest request){
		
		User sessionUser = PubMethod.getUser(request);
		java.sql.Timestamp deleteDate = PubMethod.getCurrentDate();		
		
		String[] staffCost_ids = request.getParameterValues("ids");
		if(staffCost_ids != null && staffCost_ids.length > 0){
			StaffCost[] staffCosts = new StaffCost[staffCost_ids.length];
			int index = 0;
			for(String staffCost_id : staffCost_ids){
				StaffCost staffCost = new StaffCost();
				StaffCost temp = staffCostService.getStaffCost(staffCost_id);
				staffCost.setStaff_id(staffCost_id);
				staffCost.setDelete_userid(sessionUser.getUser_id());
				staffCost.setDelete_username(sessionUser.getUser_name());
				staffCost.setDelete_datetime(deleteDate);
				
				if(temp.getLeave_job_datetime() == null){
					Timestamp leave_job_datetime = null;
					List<ProjectStaff> projectStaffs = projectService.queryProjectStaff(temp);
					if(projectStaffs != null && !projectStaffs.isEmpty()){
						ProjectStaff projectStaff = projectStaffs.get(0);
						if(projectStaff.getLeave_project_datetime() != null){
							leave_job_datetime = projectStaff.getLeave_project_datetime();
						}
					}
					
					if(leave_job_datetime == null) {
						return this.ajaxForwardError(request, "还没有给"+temp.getStaff_name()+"("+temp.getStaff_no()+")设置离职时间！",false);
					}else {
						staffCost.setLeave_job_datetime(leave_job_datetime);
					}
				}else {				
					staffCost.setLeave_job_datetime(temp.getLeave_job_datetime());
				}
				staffCosts[index] = staffCost;
				index ++ ;
			}
			
			if(staffCosts != null && staffCosts.length > 0) {
				staffCostService.deleteStaffCost(staffCosts);
			}
			
			//发送事件
			//if(staffCost_ids.length == 1) BusinessSend.send(StaffCost.class.getSimpleName() , staffCost_ids [0]);
			//else BusinessSend.send(StaffCost.class.getSimpleName());
			
		}
		return this.ajaxForwardSuccess(request,rel,false);
	}	
	
	
	@RequestMapping(params = "method=deleteStaffRaiseRecord")
	public String deleteStaffRaiseRecord(StaffRaiseRecord staffRaiseRecord,HttpServletResponse res,HttpServletRequest request){			
		staffRaiseRecordService.deleteRaiseRecord(new StaffRaiseRecord[]{staffRaiseRecord});
		request.setAttribute("rownum", request.getParameter("rownum"));
		request.setAttribute("other", "raise_record_table");
		return this.ajaxForwardSuccess(request,rel,false);
	}
	

	@RequestMapping(params = "method=deleteStaffRewardPenalty")
	public String deleteStaffRewardPenalty(StaffRewardPenalty staffRewardPenalty,HttpServletResponse res,HttpServletRequest request){			
		staffRewardPenaltyService.deleteStaffRewardPenalty(new StaffRewardPenalty[]{staffRewardPenalty});
		request.setAttribute("rownum", request.getParameter("rownum"));
		request.setAttribute("other", "reward_penalty_table");
		return this.ajaxForwardSuccess(request,rel,false);
	}
	

	@RequestMapping(params = "method=deleteStaffAssessment")
	public String deleteStaffAssessment(StaffAssessment staffAssessment,HttpServletResponse res,HttpServletRequest request){			
		staffAssessmentService.deleteStaffAssessment(new StaffAssessment[]{staffAssessment});
		request.setAttribute("rownum", request.getParameter("rownum"));
		request.setAttribute("other", "assessment_table");
		return this.ajaxForwardSuccess(request,rel,false);
	}

	
	@RequestMapping(params = "method=deleteStaffPositions")
	public String deleteStaffPositions(StaffPositions staffPositions,HttpServletResponse res,HttpServletRequest request){			
		staffPositionsService.deleteStaffPositions(new StaffPositions[]{staffPositions});
		request.setAttribute("rownum", request.getParameter("rownum"));
		request.setAttribute("other", "positions_table");
		return this.ajaxForwardSuccess(request,rel,false);
	}

}
