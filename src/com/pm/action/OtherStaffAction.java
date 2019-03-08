package com.pm.action;

import java.sql.Timestamp;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.utils.DateKit;
import com.pm.domain.business.*;
import com.pm.service.*;
import com.pm.util.constant.*;
import org.apache.commons.lang.StringUtils;
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
import com.pm.domain.system.Dept;
import com.pm.domain.system.User;
import com.pm.util.Config;
import com.pm.util.PubMethod;
import com.pm.util.excel.BusinessExcel;
import com.pm.util.excel.imports.ExcelRead;
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

    @Autowired
    private IDicDataService dicDataService;

    @Autowired
    private IInsuranceGradeService insuranceGradeService;

    @Autowired
    private IStaffRaiseRecordService staffRaiseRecordService;

    @Autowired
    private IStaffRewardPenaltyService staffRewardPenaltyService;

    @Autowired
    private IStaffAssessmentService  staffAssessmentService;

    @Autowired
    private IStaffPositionsService staffPositionsService;


    @Autowired
    private IDeptStaffService deptStaffService;

	
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
			error = "该人员工号已经存在";
			return this.ajaxForwardError(request, error);
		}		
	}



    /**
     * 社保等级 同步到总部人员
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
                OtherStaff search = new OtherStaff();
                search.setInsurance_grade_id(insurance_grade_id);
                List<OtherStaff> list = otherStaffService.getOtherStaffByInsurance(search);
                if(list != null && list.size() >0){
                    for(OtherStaff staffCost1 : list){
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

                            otherStaffService.updateOtherStaff(staffCost1, null, null,null, null, null);
                        }
                    }
                }
            }
        }catch(Exception e){
            return this.ajaxForwardError(request, "数据格式错误！", false);
        }

        return this.ajaxForwardSuccess(request, rel, false);

    }




    /**
	 * 导出Excel(普通方式导出)
	 * @param searchOtherStaff
	 * @param res
	 * @param request
	 */
	@RequestMapping(params = "method=export")
	public void export(OtherStaff searchOtherStaff,HttpServletResponse res,HttpServletRequest request){
		
		
		processparam(searchOtherStaff,request);

		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.OTHERSTAFFVIEW.getId());			
		Pager<OtherStaff> pager = otherStaffService.queryOtherStaff(searchOtherStaff, userPermit, PubMethod.getPagerByAll(OtherStaff.class));
		PubMethod.setRequestPager(request, pager, OtherStaff.class);

		for(OtherStaff  otherStaff : pager.getResultList()){
            otherStaff.setCan_send_info(this.getMsg("boolean." + (otherStaff.getCan_send_info()==null?"":otherStaff.getCan_send_info()), request));

            otherStaff.setPosition_type_temp(this.getMsg("position.type." + (otherStaff.getPosition_type()==null?"":otherStaff.getPosition_type()), request));
		}
		
		try{
			BusinessExcel.export(res, null, pager.getResultList(), OtherStaff.class);
		}catch(Exception e){
			
		}
		
	}
	
	@RequestMapping(params = "method=toExcel")
	public String toExcel(HttpServletResponse res,HttpServletRequest request){
		return "headquarters/other_staff_upload";
	}
	


	@RequestMapping(params = "method=importResult")
	public String importResult(HttpServletResponse res,HttpServletRequest request) throws  Exception{
		List<OtherStaff> list = (List<OtherStaff>)request.getSession().getAttribute(sessionAttr);
		request.getSession().removeAttribute(sessionAttr);
		request.setAttribute("list", list);
		return "headquarters/other_staff_excel_list";
	}
	
	

	@RequestMapping(params = "method=downloadtemplet")
	public ModelAndView downloadtemplet(HttpServletRequest request,  HttpServletResponse response) throws Exception { 

		String sourceFile = this.getClass().getClassLoader().getResource("/templet/otherstaff.xlsx").getPath();
		DownloadBaseUtil.download(  sourceFile,  "总部人员模板.xlsx" ,response,false);
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
			if(row.length<20) {
			    return this.ajaxForwardError(request, "第"+(index+Config.startRow)+"行数据不全",true);
            }
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
                //staffMap.put(staffCost.getIdentity_card_number(), staffCost.getIdentity_card_number());
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
		
		for(OtherStaff otherStaff : otherStaffs){
			boolean b = checkOtherStaff(otherStaff,staffMap,deptMap,insuranceGradeMap,allDicData);
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
                    DeptStaff[] deptStaffs = new DeptStaff[1];
                    if(StringUtils.isNotEmpty(otherStaff.getDept_id())){

                        DeptStaff deptStaff = new DeptStaff();
                        if(otherStaff.getJoin_datetime() == null){
                            deptStaff.setJoin_dept_datetime(PubMethod.getCurrentDate());
                        }else {
                            deptStaff.setJoin_dept_datetime(otherStaff.getJoin_datetime());
                        }
                        deptStaff.setStaff_id(otherStaff.getStaff_id());
                        deptStaff.setDept_id(otherStaff.getDept_id());
                        deptStaff.setDept_name(otherStaff.getDept_name());
                        deptStaff.setDelete_flag(BusinessUtil.NOT_DELETEED);
                        deptStaff.setBuild_datetime(PubMethod.getCurrentDate());
                        deptStaff.setBuild_userid(sessionUser.getUser_id());
                        deptStaff.setBuild_username(sessionUser.getUser_name());
                        deptStaffs[0] = deptStaff;
                    }
					otherStaffService.addOtherStaff(otherStaff , null, null, null, null, deptStaffs);
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
	
	

	private boolean checkOtherStaff(OtherStaff otherStaff,
                                    Map<String,String>  staffMap,
                                    Map<String,Dept>  deptMap,
			                        Map<String,InsuranceGrade> insuranceGradeMap,
                                    Map<String, Map<String, DicData>> allDicData){
		boolean b = true;
		

		if(otherStaff.getStaff_no() == null || otherStaff.getStaff_no().isEmpty()){
			otherStaff.setErrorInfo(otherStaff.getErrorInfo() + "工号不能为空;");
			b = false;
		}else {

            if (staffMap.containsKey(otherStaff.getStaff_no())) {
                otherStaff.setErrorInfo(otherStaff.getErrorInfo() + "工号重复;");
                b = false;
            } else {
                staffMap.put(otherStaff.getStaff_no(), otherStaff.getStaff_no());
            }
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


        if(otherStaff.getIdentity_card_number() != null && otherStaff.getIdentity_card_number().length() > 0){


            if(staffMap.containsKey(otherStaff.getIdentity_card_number())){
                otherStaff.setErrorInfo(otherStaff.getErrorInfo() + "身份证号重复;");
                b = false;
            }else {

                if (otherStaff.getIdentity_card_number().length() != 18) {
                    otherStaff.setErrorInfo(otherStaff.getErrorInfo() + "身份证号码必须是18位;");
                    b = false;
                } else if (!PubMethod.match(BusinessUtil.IDCARD, otherStaff.getIdentity_card_number())) {

                    otherStaff.setErrorInfo(otherStaff.getErrorInfo() + "身份证号码错误;");
                    b = false;
                } else {

                    String idcard = otherStaff.getIdentity_card_number();

                    String sexStr = idcard.substring(16, 17);
                    if (Integer.parseInt(sexStr) % 2 == 1) {
                        otherStaff.setSex("男");
                    } else {
                        otherStaff.setSex("女");
                    }

                    String birthdayStr = idcard.substring(6, 10) + "-" + idcard.substring(10, 12) + "-" + idcard.substring(12, 14);
                    try {
                        otherStaff.setBirthday(DateKit.fmtStrToDate(birthdayStr, "yyyy-MM-dd"));
                    } catch (Exception e) {
                        otherStaff.setErrorInfo(otherStaff.getErrorInfo() + "身份证号码错误;");
                        b = false;
                    }
                }
            }

        }



        if(otherStaff.getEducational_name() != null && otherStaff.getEducational_name().length() >0){
            DicData dicData = PubMethod.getObj4Map(EnumDicType.EDUCATIONAL.name(),otherStaff.getEducational_name(),allDicData);
            if(dicData != null){
                otherStaff.setEducational(dicData.getId());
            }else {
                otherStaff.setErrorInfo(otherStaff.getErrorInfo() + "学历错误;");
                b = false;
            }
        }





        if(otherStaff.getSpecialty_name() != null && otherStaff.getSpecialty_name().length() >0){
            DicData dicData = PubMethod.getObj4Map(EnumDicType.SPECIALTY.name(),otherStaff.getSpecialty_name(),allDicData);
            if(dicData != null){
                otherStaff.setSpecialty(dicData.getId());
            }else {
                otherStaff.setErrorInfo(otherStaff.getErrorInfo() + "专业错误;");
                b = false;
            }
        }

        if(otherStaff.getWorking_life() >= 100){
            otherStaff.setErrorInfo(otherStaff.getErrorInfo() + "工作年限太大;");
            b = false;
        }

        if(otherStaff.getNationality_name() != null && otherStaff.getNationality_name().length() >0){
            DicData dicData = PubMethod.getObj4Map(EnumDicType.NATIONALITY.name(),otherStaff.getNationality_name(),allDicData);
            if(dicData != null){
                otherStaff.setNationality(dicData.getId());
            }else {
                otherStaff.setErrorInfo(otherStaff.getErrorInfo() + "民族错误;");
                b = false;
            }
        }

        if(otherStaff.getCensus_property_name() != null && otherStaff.getCensus_property_name().length() >0){
            DicData dicData = PubMethod.getObj4Map(EnumDicType.HOUSEHOLDPROPERTY.name(),otherStaff.getCensus_property_name(),allDicData);
            if(dicData != null){
                otherStaff.setCensus_property(dicData.getId());
            }else {
                otherStaff.setErrorInfo(otherStaff.getErrorInfo() + "户籍性质错误;");
                b = false;
            }
        }


        if( otherStaff.getInsurance_radix()!=null){
            InsuranceGrade insuranceGrade = insuranceGradeMap.get( otherStaff.getInsurance_radix());
            if(insuranceGrade == null) {
                 otherStaff.setErrorInfo( otherStaff.getErrorInfo() + "社保种类 错误;");
                b = false;
            }else {
                 otherStaff.setEndowment_insurance_bypersonal(insuranceGrade.getEndowment_insurance_bypersonal());
                 otherStaff.setMedical_insurance_bypersonal(insuranceGrade.getMedical_insurance_bypersonal());
                 otherStaff.setLosejob_insurance_bypersonal(insuranceGrade.getLosejob_insurance_bypersonal());
                 otherStaff.setReservefund_bypersonal(insuranceGrade.getReservefund_bypersonal());
                 otherStaff.setIncometax_bypersonal(insuranceGrade.getIncometax_bypersonal());
                 otherStaff.setEndowment_insurance_bycompany(insuranceGrade.getEndowment_insurance_bycompany());
                 otherStaff.setMedical_insurance_bycompany(insuranceGrade.getMedical_insurance_bycompany());
                 otherStaff.setLosejob_insurance_bycompany(insuranceGrade.getLosejob_insurance_bycompany());
                 otherStaff.setMaternity_insurance_bycompany(insuranceGrade.getMaternity_insurance_bycompany());
                 otherStaff.setJobharm_insurance_bycompany(insuranceGrade.getJobharm_insurance_bycompany());
                 otherStaff.setReservefund_bypcompany(insuranceGrade.getReservefund_bypcompany());
                 otherStaff.setInsurance_grade_id(insuranceGrade.getInsurance_grade_id());
                 otherStaff.setBase_cardinal(insuranceGrade.getBase_cardinal());
            }
        }



        //根据试用期工资计算 试用期的 基本 岗位 和绩效 工资
        if( otherStaff.getTryout_salary() != 0){

            if( otherStaff.getTryout_salary() >=  otherStaff.getBase_cardinal()){
                if( otherStaff.getBase_cardinal() >= BusinessUtil.DEFAULT_BASIC_SALARY){
                     otherStaff.setTryout_basic_salary(BusinessUtil.DEFAULT_BASIC_SALARY);
                     otherStaff.setTryout_post_salary( otherStaff.getBase_cardinal()-BusinessUtil.DEFAULT_BASIC_SALARY);
                     otherStaff.setTryout_performance_allowances( otherStaff.getTryout_salary() -  otherStaff.getBase_cardinal());
                }else {
                     otherStaff.setTryout_basic_salary( otherStaff.getBase_cardinal());
                     otherStaff.setTryout_post_salary(0);
                     otherStaff.setTryout_performance_allowances( otherStaff.getTryout_salary() -  otherStaff.getBase_cardinal());
                }
            }else {
                if( otherStaff.getTryout_salary() >= BusinessUtil.DEFAULT_BASIC_SALARY){
                     otherStaff.setTryout_basic_salary(BusinessUtil.DEFAULT_BASIC_SALARY);
                     otherStaff.setTryout_post_salary( otherStaff.getTryout_salary()-BusinessUtil.DEFAULT_BASIC_SALARY);
                     otherStaff.setTryout_performance_allowances(0);
                }else {
                     otherStaff.setTryout_basic_salary( otherStaff.getTryout_salary());
                     otherStaff.setTryout_post_salary(0);
                     otherStaff.setTryout_performance_allowances(0);
                }
            }

        }


        //根据正式工资计算  基本 岗位 和绩效 工资
        if( otherStaff.getOfficial_salary() > 0){

            if( otherStaff.getOfficial_salary() >=  otherStaff.getBase_cardinal()){
                if( otherStaff.getBase_cardinal() >= BusinessUtil.DEFAULT_BASIC_SALARY){
                     otherStaff.setBasic_salary(BusinessUtil.DEFAULT_BASIC_SALARY);
                     otherStaff.setPost_salary( otherStaff.getBase_cardinal()-BusinessUtil.DEFAULT_BASIC_SALARY);
                     otherStaff.setPerformance_allowances( otherStaff.getOfficial_salary() -  otherStaff.getBase_cardinal());
                }else {
                     otherStaff.setBasic_salary( otherStaff.getBase_cardinal());
                     otherStaff.setPost_salary(0);
                     otherStaff.setPerformance_allowances( otherStaff.getOfficial_salary() -  otherStaff.getBase_cardinal());
                }
            }else {
                if( otherStaff.getOfficial_salary() >= BusinessUtil.DEFAULT_BASIC_SALARY){
                     otherStaff.setBasic_salary(BusinessUtil.DEFAULT_BASIC_SALARY);
                     otherStaff.setPost_salary( otherStaff.getOfficial_salary()-BusinessUtil.DEFAULT_BASIC_SALARY);
                     otherStaff.setPerformance_allowances(0);
                }else {
                     otherStaff.setBasic_salary( otherStaff.getOfficial_salary());
                     otherStaff.setPost_salary(0);
                    otherStaff.setPerformance_allowances(0);
                }
            }
        }



        if( otherStaff.getCan_send_info() != null &&  otherStaff.getCan_send_info().length() > 0){
            if("1".equals( otherStaff.getCan_send_info()) || "是".equals( otherStaff.getCan_send_info()) || "yes".equalsIgnoreCase( otherStaff.getCan_send_info()) || "Y".equalsIgnoreCase( otherStaff.getCan_send_info())){
                if(b) {
                     otherStaff.setCan_send_info("1");
                }
            }else if("0".equals( otherStaff.getCan_send_info()) || "否".equals( otherStaff.getCan_send_info()) || "no".equalsIgnoreCase( otherStaff.getCan_send_info()) || "N".equalsIgnoreCase( otherStaff.getCan_send_info())){
                if(b) {
                     otherStaff.setCan_send_info("0");
                }
            }else {
                 otherStaff.setErrorInfo( otherStaff.getErrorInfo() + "是否允许发送信息 错误;");
                b = false;
            }
        }else {
             otherStaff.setCan_send_info(BusinessUtil.STAFF_CAN_SEND_MESSAGE);
        }

        if( otherStaff.getContract_type_name() != null &&  otherStaff.getContract_type_name().length() >0){
            DicData dicData = PubMethod.getObj4Map(EnumDicType.CONTRACT_TYPE.name(), otherStaff.getContract_type_name(),allDicData);
            if(dicData != null){
                 otherStaff.setContract_type(dicData.getId());
            }else {
                 otherStaff.setErrorInfo( otherStaff.getErrorInfo() + "合同种类错误;");
                b = false;
            }
        }

        if( otherStaff.getContract_attach_name() != null &&  otherStaff.getContract_attach_name().length() >0){
            DicData dicData = PubMethod.getObj4Map(EnumDicType.CONTRACT_ATTACH.name(), otherStaff.getContract_attach_name(),allDicData);
            if(dicData != null){
                 otherStaff.setContract_attach(dicData.getId());
            }else {
                 otherStaff.setErrorInfo( otherStaff.getErrorInfo() + "合同归属错误;");
                b = false;
            }
        }


        if( otherStaff.getSocial_security_name() != null &&  otherStaff.getSocial_security_name().length() >0){
            DicData dicData = PubMethod.getObj4Map(EnumDicType.SOCIAL_SECURITY.name(), otherStaff.getSocial_security_name(),allDicData);
            if(dicData != null){
                 otherStaff.setSocial_security(dicData.getId());
            }else {
                 otherStaff.setErrorInfo( otherStaff.getErrorInfo() + "社保说明错误;");
                b = false;
            }
        }



        if( otherStaff.getInsured_city_name() != null &&  otherStaff.getInsured_city_name().length() >0){
            DicData dicData = PubMethod.getObj4Map(EnumDicType.INSURED_CITY.name(), otherStaff.getInsured_city_name(),allDicData);
            if(dicData != null){
                 otherStaff.setInsured_city(dicData.getId());
            }else {
                 otherStaff.setErrorInfo( otherStaff.getErrorInfo() + "社保城市错误;");
                b = false;
            }
        }



        if( otherStaff.getWorking_address_name() != null &&  otherStaff.getWorking_address_name().length() >0){
            DicData dicData = PubMethod.getObj4Map(EnumDicType.WORKING_ADDRESS.name(), otherStaff.getWorking_address_name(),allDicData);
            if(dicData != null){
                 otherStaff.setWorking_address(dicData.getId());
            }else {
                 otherStaff.setErrorInfo( otherStaff.getErrorInfo() + "工作地点错误;");
                b = false;
            }
        }



        if( otherStaff.getCertificate_name() != null &&  otherStaff.getCertificate_name().length() >0){
            DicData dicData = PubMethod.getObj4Map(EnumDicType.CERTIFICATE.name(), otherStaff.getCertificate_name(),allDicData);
            if(dicData != null){
                 otherStaff.setCertificate(dicData.getId());
            }else {
                 otherStaff.setErrorInfo(otherStaff.getErrorInfo() + "证书错误;");
                b = false;
            }
        }


        if( otherStaff.getChildren_education()<0){
             otherStaff.setErrorInfo( otherStaff.getErrorInfo() + "请填写正确的子女教育;");
            b = false;
        }

        if( otherStaff.getContinuing_education()<0){
             otherStaff.setErrorInfo( otherStaff.getErrorInfo() + "请填写正确的继续教育;");
            b = false;
        }

        if( otherStaff.getHousing_loans()<0 ||  otherStaff.getHousing_loans() > 1000){
             otherStaff.setErrorInfo( otherStaff.getErrorInfo() + "请填写正确的住房贷款利息;");
            b = false;
        }

        if( otherStaff.getHousing_rent()<0 ||  otherStaff.getHousing_rent()>1500){
             otherStaff.setErrorInfo( otherStaff.getErrorInfo() + "请填写正确的住房租金;");
            b = false;
        }

        if( otherStaff.getSupport_elderly()<0 ||  otherStaff.getSupport_elderly()>2000){
             otherStaff.setErrorInfo( otherStaff.getErrorInfo() + "请填写正确的赡养老人;");
            b = false;
        }


        if( otherStaff.getErrorInfo() != null && ! otherStaff.getErrorInfo().isEmpty()) {
            b = false;
        }

        if(b){
            staffMap.put(otherStaff.getStaff_no(), otherStaff.getStaff_no());
            staffMap.put(otherStaff.getIdentity_card_number(), otherStaff.getIdentity_card_number());
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
		return "headquarters/other_staff_search";		
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
		return "headquarters/other_staff_search1";		
	}

	@RequestMapping(params = "method=list")
	public String list(OtherStaff searchOtherStaff,HttpServletResponse res,HttpServletRequest request){

		processparam(searchOtherStaff,request);

		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.OTHERSTAFFVIEW.getId());			
		Pager<OtherStaff> pager = otherStaffService.queryOtherStaff(searchOtherStaff, userPermit, PubMethod.getPager(request, OtherStaff.class));

        if(pager.getResultList() != null && !pager.getResultList().isEmpty()){

            for(OtherStaff otherStaff : pager.getResultList()){

                if(otherStaff.getConfirmation_date() != null) {
                    otherStaff.setConfirmation_month(DateKit.fmtDateToYM(otherStaff.getConfirmation_date()));
                }

                if(otherStaff.getContract_end_date() != null) {
                    otherStaff.setContract_end_month(DateKit.fmtDateToYM(otherStaff.getContract_end_date()));
                }
            }
        }

		PubMethod.setRequestPager(request, pager, OtherStaff.class);
        request.setAttribute("curr_ym", DateKit.fmtDateToYM(new Date()));

		request.setAttribute(EnumOperationType.READ.getKey(), userPermit.getPermit_id());

		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.OTHERSTAFFADD.getId());
		request.setAttribute(EnumOperationType.INSERT.getKey(), userPermit1.getPermit_id());
		
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.OTHERSTAFFUPDATE.getId());
		request.setAttribute(EnumOperationType.UPDATE.getKey(), userPermit1.getPermit_id());	

		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.OTHERSTAFFDELETE.getId());
		request.setAttribute(EnumOperationType.DELETE.getKey(), userPermit1.getPermit_id());
		
		request.setAttribute("staff", searchOtherStaff);
		
		return "headquarters/other_staff_list";
		
	}
	
	public void processparam(OtherStaff otherStaff,HttpServletRequest request){

        if(otherStaff == null) {
            otherStaff = new OtherStaff();
        }

		if(otherStaff.getDept_id() == null){
			otherStaff.setDept_id(request.getParameter("dept.dept_id"));
			otherStaff.setDept_name(request.getParameter("dept.dept_name"));
		}


         otherStaff.setInsurance_grade_id(request.getParameter("grade.insurance_grade_id"));
         otherStaff.setInsurance_radix(request.getParameter("grade.insurance_radix"));
        String base_cardinal = request.getParameter("grade.base_cardinal");
        if(StringUtils.isNotEmpty(base_cardinal)) {
             otherStaff.setBase_cardinal(Double.parseDouble(base_cardinal));
        }



         otherStaff.setNationality(request.getParameter("nation.id"));
         otherStaff.setNationality_name(request.getParameter("nation.dic_data_name"));

         otherStaff.setCensus_property(request.getParameter("hp.id"));
         otherStaff.setCensus_property_name(request.getParameter("hp.dic_data_name"));

         otherStaff.setEducational(request.getParameter("edu.id"));
         otherStaff.setEducational_name(request.getParameter("edu.dic_data_name"));


         otherStaff.setSpecialty(request.getParameter("specialty.id"));
         otherStaff.setSpecialty_name(request.getParameter("specialty.dic_data_name"));


         otherStaff.setContract_attach(request.getParameter("contract_attach.id"));
         otherStaff.setContract_attach_name(request.getParameter("contract_attach.dic_data_name"));

         otherStaff.setContract_type(request.getParameter("contract_type.id"));
         otherStaff.setContract_type_name(request.getParameter("contract_type.dic_data_name"));

         otherStaff.setSocial_security(request.getParameter("social_security.id"));
         otherStaff.setSocial_security_name(request.getParameter("social_security.dic_data_name"));



         otherStaff.setInsured_city(request.getParameter("insuredcity.id"));
         otherStaff.setInsured_city_name(request.getParameter("insuredcity.dic_data_name"));

         otherStaff.setWorking_address(request.getParameter("workingaddress.id"));
         otherStaff.setWorking_address_name(request.getParameter("workingaddress.dic_data_name"));

         otherStaff.setCertificate(request.getParameter("certificate.id"));
         otherStaff.setCertificate_name(request.getParameter("certificate.dic_data_name"));


        String value = request.getParameter("grade.endowment_insurance_bypersonal");
        try{
            double d = Double.parseDouble(value);
             otherStaff.setEndowment_insurance_bypersonal(d);
        }catch(Exception e){}

        value = request.getParameter("grade.medical_insurance_bypersonal");
        try{
            double d = Double.parseDouble(value);
             otherStaff.setMedical_insurance_bypersonal(d);
        }catch(Exception e){}

        value = request.getParameter("grade.losejob_insurance_bypersonal");
        try{
            double d = Double.parseDouble(value);
             otherStaff.setLosejob_insurance_bypersonal(d);
        }catch(Exception e){}

        value = request.getParameter("grade.reservefund_bypersonal");
        try{
            double d = Double.parseDouble(value);
             otherStaff.setReservefund_bypersonal(d);
        }catch(Exception e){}

        value = request.getParameter("grade.incometax_bypersonal");
        try{
            double d = Double.parseDouble(value);
             otherStaff.setIncometax_bypersonal(d);
        }catch(Exception e){}

        value = request.getParameter("grade.endowment_insurance_bycompany");
        try{
            double d = Double.parseDouble(value);
             otherStaff.setEndowment_insurance_bycompany(d);
        }catch(Exception e){}

        value = request.getParameter("grade.medical_insurance_bycompany");
        try{
            double d = Double.parseDouble(value);
             otherStaff.setMedical_insurance_bycompany(d);
        }catch(Exception e){}

        value = request.getParameter("grade.losejob_insurance_bycompany");
        try{
            double d = Double.parseDouble(value);
             otherStaff.setLosejob_insurance_bycompany(d);
        }catch(Exception e){}

        value = request.getParameter("grade.maternity_insurance_bycompany");
        try{
            double d = Double.parseDouble(value);
             otherStaff.setMaternity_insurance_bycompany(d);
        }catch(Exception e){}

        value = request.getParameter("grade.jobharm_insurance_bycompany");
        try{
            double d = Double.parseDouble(value);
             otherStaff.setJobharm_insurance_bycompany(d);
        }catch(Exception e){}

        value = request.getParameter("grade.reservefund_bypcompany");
        try{
            double d = Double.parseDouble(value);
             otherStaff.setReservefund_bypcompany(d);
        }catch(Exception e){}
	}




    /**
     * 获取加薪记录
     * @param otherStaff
     * @param sessionUser
     * @param request
     * @return
     */
    private StaffRaiseRecord[] getStaffRaiseRecord(OtherStaff otherStaff, User sessionUser, HttpServletRequest request){

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
                staffRaiseRecord.setStaff_id(otherStaff.getStaff_id());
                staffRaiseRecord.setStaff_name(otherStaff.getStaff_name());

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
     * @param otherStaff
     * @param sessionUser
     * @param request
     * @return
     */
    private StaffRewardPenalty[] getStaffRewardPenalty(OtherStaff otherStaff,User sessionUser,HttpServletRequest request){

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
                staffRewardPenalty.setStaff_id(otherStaff.getStaff_id());
                staffRewardPenalty.setStaff_name(otherStaff.getStaff_name());

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
     * @param otherStaff
     * @param sessionUser
     * @param request
     * @return
     */
    private StaffAssessment[] getStaffAssessment(OtherStaff otherStaff,User sessionUser,HttpServletRequest request){

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
                staffAssessment.setStaff_id(otherStaff.getStaff_id());
                staffAssessment.setStaff_name(otherStaff.getStaff_name());

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
     * @param otherStaff
     * @param sessionUser
     * @param request
     * @return
     */
    private StaffPositions[] getStaffPositions(OtherStaff otherStaff,User sessionUser,HttpServletRequest request){

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
                staffPositions.setStaff_id(otherStaff.getStaff_id());
                staffPositions.setStaff_name(otherStaff.getStaff_name());

                staffPositions.setBuild_datetime(PubMethod.getCurrentDate());
                staffPositions.setBuild_userid(sessionUser.getUser_id());
                staffPositions.setBuild_username(sessionUser.getUser_name());

                list.add(staffPositions);
            }

        }

        return list.toArray(new StaffPositions[list.size()]);

    }



    private DeptStaff[] getDeptStaffs(HttpServletRequest request,OtherStaff otherStaff,User sessionUser){

        String[] rowIndex = request.getParameterValues("index_dept_staff_table");

        List<DeptStaff> deptStaffList = new ArrayList<DeptStaff>();
        if(rowIndex != null && rowIndex.length >0){
            for(String index : rowIndex) {
                DeptStaff deptStaff = new DeptStaff();
                String dept_staff_id = request.getParameter("items["+index+"]."+"dept_staff_id");

                String dept_id = request.getParameter("items["+index+"].dept."+"dept_id");
                String dept_name = request.getParameter("items["+index+"].dept."+"dept_name");
                String join_dept_datetime= request.getParameter("items["+index+"]."+"join_dept_datetime");
                String leave_dept_datetime = request.getParameter("items["+index+"]."+"leave_dept_datetime");
                String description = request.getParameter("items["+index+"]."+"description");

                deptStaff.setDept_staff_id(StringUtils.isEmpty(dept_staff_id)?null :dept_staff_id );
                deptStaff.setDept_id(dept_id);
                deptStaff.setDept_name(dept_name);
                deptStaff.setStaff_id(otherStaff.getStaff_id());
                deptStaff.setDescription(description);

                if(join_dept_datetime != null && join_dept_datetime.length() > 0){
                    deptStaff.setJoin_dept_datetime(new Timestamp(DateKit.fmtStrToDate(join_dept_datetime).getTime()));
                }
                if(leave_dept_datetime != null && leave_dept_datetime.length() > 0){
                    deptStaff.setDelete_flag(BusinessUtil.DELETEED);

                    deptStaff.setDelete_datetime(PubMethod.getCurrentDate());
                    deptStaff.setDelete_userid(sessionUser.getUser_id());
                    deptStaff.setDelete_username(sessionUser.getUser_name());
                    deptStaff.setLeave_dept_datetime(new Timestamp(DateKit.fmtStrToDate(leave_dept_datetime).getTime()));
                }else {
                    deptStaff.setDelete_flag(BusinessUtil.NOT_DELETEED);
                }

                deptStaff.setBuild_datetime(PubMethod.getCurrentDate());
                deptStaff.setBuild_userid(sessionUser.getUser_id());
                deptStaff.setBuild_username(sessionUser.getUser_name());

                deptStaffList.add(deptStaff);
            }
        }

        DeptStaff[] deptStaffs = null;
        if(deptStaffList.size() > 0) {
            deptStaffs = new DeptStaff[deptStaffList.size()];
            PubMethod.List2Array(deptStaffList, deptStaffs, DeptStaff.class);
        }

        return deptStaffs;
    }



    @RequestMapping(params = "method=addOtherStaff")
	public String addOtherStaff(OtherStaff otherStaff,HttpServletResponse res,HttpServletRequest request){
		
		processparam(otherStaff,request);
		
		String isExist = isExist(otherStaff,res,request);
		if(isExist != null) {
		    return isExist;
        }

		User sessionUser = PubMethod.getUser(request);
		otherStaff.setStaff_id(IDKit.generateId());
		otherStaff.setBuild_datetime(PubMethod.getCurrentDate());
		otherStaff.setBuild_userid(sessionUser.getUser_id());
		otherStaff.setBuild_username(sessionUser.getUser_name());
		otherStaff.setDelete_flag(BusinessUtil.NOT_DELETEED);		
		
		
		int count = 0;
		try{

            StaffAssessment[] staffAssessments = this.getStaffAssessment(otherStaff, sessionUser, request);

            StaffPositions[] staffPositionss = this.getStaffPositions(otherStaff, sessionUser, request);

            StaffRaiseRecord[] staffRaiseRecords = this.getStaffRaiseRecord(otherStaff, sessionUser, request);

            StaffRewardPenalty[] staffRewardPenaltys = this.getStaffRewardPenalty(otherStaff, sessionUser, request);


            DeptStaff[] deptStaffs = this.getDeptStaffs(request,otherStaff,sessionUser);


            if(deptStaffs != null && deptStaffs.length > 0){
                for(DeptStaff deptStaff : deptStaffs){
                    if(deptStaff.getLeave_dept_datetime() == null){
                        otherStaff.setDept_id(deptStaff.getDept_id());
                        otherStaff.setDept_name(deptStaff.getDept_name());
                        break;
                    }
                }
            }

			count = otherStaffService.addOtherStaff(otherStaff ,staffAssessments ,staffPositionss, staffRaiseRecords,staffRewardPenaltys , deptStaffs );
		}catch(Exception e){
			
		}
		if(count == 1) {
            return this.ajaxForwardSuccess(request, rel, true);
        }
		else {
		    return this.ajaxForwardError(request, "数据格式错误！", true);
        }
		
	}
	

	@RequestMapping(params = "method=updateOtherStaff")
	public String updateOtherStaff(OtherStaff otherStaff,HttpServletResponse res,HttpServletRequest request){	

		processparam(otherStaff,request);
		String isExist = isExist(otherStaff,res,request);
		if(isExist != null) {
		    return isExist;
        }

        User sessionUser = PubMethod.getUser(request);
		
		int count = 0;
		try{

            StaffAssessment[] staffAssessments = this.getStaffAssessment(otherStaff, sessionUser, request);

            StaffPositions[] staffPositionss = this.getStaffPositions(otherStaff, sessionUser, request);

            StaffRaiseRecord[] staffRaiseRecords = this.getStaffRaiseRecord(otherStaff, sessionUser, request);

            StaffRewardPenalty[] staffRewardPenaltys = this.getStaffRewardPenalty(otherStaff, sessionUser, request);

            DeptStaff[] deptStaffs = this.getDeptStaffs(request,otherStaff,sessionUser);


            if(deptStaffs != null && deptStaffs.length > 0){
                otherStaff.setDept_id(null);
                otherStaff.setDept_name(null);
                for(DeptStaff deptStaff : deptStaffs){
                    if(deptStaff.getLeave_dept_datetime() == null){
                        otherStaff.setDept_id(deptStaff.getDept_id());
                        otherStaff.setDept_name(deptStaff.getDept_name());
                        break;
                    }
                }
            }

			count = otherStaffService.updateOtherStaff(otherStaff,staffAssessments ,staffPositionss, staffRaiseRecords,staffRewardPenaltys,deptStaffs);
		}catch(Exception e){
			
		}
		if(count == 1) 		{
		    return this.ajaxForwardSuccess(request, rel, true);
        }
		else {
		    return this.ajaxForwardError(request, "数据格式错误！", true);
        }
		
	}


	@RequestMapping(params = "method=toEdit")
	public String toEdit(OtherStaff otherStaff,HttpServletResponse res,HttpServletRequest request){
		
		OtherStaff otherStaff1 = null;
		if(otherStaff != null && otherStaff.getStaff_id() !=null){
			otherStaff1 = otherStaffService.getOtherStaff(otherStaff.getStaff_id());
            if(otherStaff1.getDelete_flag().equals(BusinessUtil.DELETEED)){
                return this.ajaxForwardError(request, "该总部人员已经删除，不能再操作！",true);
            }

            request.setAttribute("next_operation", "updateOtherStaff");

		}else {
			request.setAttribute("next_operation", "addOtherStaff");
		}		
		
		if(otherStaff1 == null) {
		    otherStaff1 = new OtherStaff();
        }
		
		request.setAttribute("otherStaff1", otherStaff1);


        if(otherStaff1 != null && StringUtils.isNotEmpty(otherStaff1.getStaff_id())) {

            StaffRaiseRecord staffRaiseRecord = new StaffRaiseRecord();
            staffRaiseRecord.setStaff_id(otherStaff1.getStaff_id());
            Pager<StaffRaiseRecord> staffRaiseRecords = staffRaiseRecordService.queryRaiseRecord(staffRaiseRecord, PubMethod.getPagerByAll(StaffRaiseRecord.class));
            request.setAttribute("raiserecords", staffRaiseRecords.getResultList());

            StaffRewardPenalty staffRewardPenalty = new StaffRewardPenalty();
            staffRewardPenalty.setStaff_id(otherStaff1.getStaff_id());
            Pager<StaffRewardPenalty> staffRewardPenaltys = staffRewardPenaltyService.queryStaffRewardPenalty(staffRewardPenalty, PubMethod.getPagerByAll(StaffRewardPenalty.class));
            request.setAttribute("rewardpenaltys", staffRewardPenaltys.getResultList());

            StaffAssessment staffAssessment = new StaffAssessment();
            staffAssessment.setStaff_id(otherStaff1.getStaff_id());
            Pager<StaffAssessment> staffAssessments = staffAssessmentService.queryStaffAssessment(staffAssessment, PubMethod.getPagerByAll(StaffAssessment.class));
            request.setAttribute("assessments", staffAssessments.getResultList());

            StaffPositions staffPositions = new StaffPositions();
            staffPositions.setStaff_id(otherStaff1.getStaff_id());
            Pager<StaffPositions> staffPositionss = staffPositionsService.queryStaffPositions(staffPositions, PubMethod.getPagerByAll(StaffPositions.class));
            request.setAttribute("positionss", staffPositionss.getResultList());


            DeptStaff searchDeptStaff = new DeptStaff();
            searchDeptStaff.setStaff_id(otherStaff.getStaff_id());
            List<DeptStaff> deptStaffs = deptStaffService.getDeptStaffs(searchDeptStaff);
            request.setAttribute("deptStaffs", deptStaffs);

        }


        request.setAttribute("DEFAULT_BASIC_SALARY", BusinessUtil.DEFAULT_BASIC_SALARY);
		
		return "headquarters/other_staff_edit";
		
	}	
	


	@RequestMapping(params = "method=toView")
	public String toView(OtherStaff otherStaff,HttpServletResponse res,HttpServletRequest request){
		
		OtherStaff  otherStaff1 = otherStaffService.getOtherStaff(otherStaff.getStaff_id());		
		
		if(otherStaff1 == null) {
		    otherStaff1 = new OtherStaff();
        }
		
		request.setAttribute("otherStaff1", otherStaff1);



        StaffRaiseRecord staffRaiseRecord = new StaffRaiseRecord();
        staffRaiseRecord.setStaff_id(otherStaff1.getStaff_id());
        Pager<StaffRaiseRecord> staffRaiseRecords = staffRaiseRecordService.queryRaiseRecord(staffRaiseRecord,PubMethod.getPagerByAll(StaffRaiseRecord.class));
        request.setAttribute("raiserecords", staffRaiseRecords.getResultList());

        StaffRewardPenalty staffRewardPenalty = new StaffRewardPenalty();
        staffRewardPenalty.setStaff_id(otherStaff1.getStaff_id());
        Pager<StaffRewardPenalty> staffRewardPenaltys = staffRewardPenaltyService.queryStaffRewardPenalty(staffRewardPenalty,PubMethod.getPagerByAll(StaffRewardPenalty.class));
        request.setAttribute("rewardpenaltys", staffRewardPenaltys.getResultList());

        StaffAssessment staffAssessment = new StaffAssessment();
        staffAssessment.setStaff_id(otherStaff1.getStaff_id());
        Pager<StaffAssessment> staffAssessments = staffAssessmentService.queryStaffAssessment(staffAssessment,PubMethod.getPagerByAll(StaffAssessment.class));
        request.setAttribute("assessments", staffAssessments.getResultList());

        StaffPositions staffPositions = new StaffPositions();
        staffPositions.setStaff_id(otherStaff1.getStaff_id());
        Pager<StaffPositions> staffPositionss = staffPositionsService.queryStaffPositions(staffPositions,PubMethod.getPagerByAll(StaffPositions.class));
        request.setAttribute("positionss", staffPositionss.getResultList());


        DeptStaff searchDeptStaff = new DeptStaff();
        searchDeptStaff.setStaff_id(otherStaff.getStaff_id());
        List<DeptStaff> deptStaffs =  deptStaffService.getDeptStaffs(searchDeptStaff);
        request.setAttribute("deptStaffs", deptStaffs);
		
		return "headquarters/other_staff_view";
		
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
			OtherStaff temp = otherStaffService.getOtherStaff(otherStaff_ids[i]);
			otherStaff.setDelete_userid(sessionUser.getUser_id());
			otherStaff.setDelete_username(sessionUser.getUser_name());
			otherStaff.setDelete_datetime(PubMethod.getCurrentDate());

            if(temp.getLeave_job_datetime() == null) {
                return this.ajaxForwardError(request, "还没有给"+temp.getStaff_name()+"("+temp.getStaff_no()+")设置离职时间！",false);
            }

			otherStaffArray[i] = otherStaff;
		}
		
		if(otherStaffArray != null && otherStaffArray.length >0) {
            otherStaffService.deleteOtherStaff(otherStaffArray);
        }

		return this.ajaxForwardSuccess(request, rel, false);		
		
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

    @RequestMapping(params = "method=deleteDeptStaff")
    public String deleteDeptStaff(DeptStaff deptStaff,HttpServletResponse res,HttpServletRequest request){

        User sessionUser = PubMethod.getUser(request);
        deptStaff.setDelete_flag(BusinessUtil.DELETEED);
        deptStaff.setDelete_userid(sessionUser.getUser_id());
        deptStaff.setDelete_username(sessionUser.getUser_name());
        deptStaff.setDelete_datetime(PubMethod.getCurrentDate());

        deptStaffService.deleteDeptStaff(new DeptStaff[]{deptStaff});
        request.setAttribute("rownum", request.getParameter("rownum"));
        request.setAttribute("other", "deptstaff_table");
        return this.ajaxForwardSuccess(request,rel,false);
    }

	
}
