package com.pm.action;


import com.common.actions.BaseAction;
import com.common.beans.Pager;
import com.common.enums.EnumYesNo;
import com.common.utils.file.FileKit;
import com.pm.domain.business.StaffCost;
import com.pm.service.IRoleService;
import com.pm.service.IStaffCostService;
import com.pm.util.Config;
import com.pm.util.PubMethod;
import com.pm.util.constant.BusinessUtil;
import com.pm.util.constant.EnumPermit;
import com.pm.util.excel.BusinessExcel;
import com.pm.util.excel.ExcelRead;
import com.pm.vo.StaffAdditionalDeduction;
import com.pm.vo.UserPermit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 人员成本的专项附加扣除
 * @author Administrator
 */
@Controller
@RequestMapping(value = "StaffAdditionalDeductionAction.do")
public class StaffAdditionalDeductionAction extends BaseAction {

    //和 StaffCostAction 的 rel 一样， 是同一个菜单
    private static final String rel = "rel10";

    private static final String sessionAttr = "StaffAdditionalDeductions";

    @Autowired
    private IStaffCostService staffCostService;

    @Autowired
    private IRoleService roleService;




    @RequestMapping(params = "method=toExcel")
    public String toExcel(HttpServletResponse res,HttpServletRequest request){
        return "basicdata/staff_ad_upload";
    }




    @RequestMapping(params = "method=importResult")
    public String importResult(HttpServletResponse res,HttpServletRequest request) throws  Exception{
        List<StaffCost> list = (List<StaffCost>)request.getSession().getAttribute(sessionAttr);
        request.getSession().removeAttribute(sessionAttr);
        request.setAttribute("list", list);
        return "basicdata/staff_ad_excel_list";
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
    public String doExcel(@RequestParam("image") MultipartFile file, HttpServletResponse res, HttpServletRequest request) throws  Exception{


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
            if(row.length<8) {
                return this.ajaxForwardError(request, "第"+(index+Config.startRow)+"行数据不全",true);
            }
            index ++;
        }

        List<StaffAdditionalDeduction> staffAdditionalDeductions = PubMethod.stringArray2List(list, StaffAdditionalDeduction.class);


        UserPermit userPermit = new UserPermit();
        userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);
        List<StaffCost> allStaffs = staffCostService.getAllStaffBySearch(new StaffCost());
        Map<String,StaffCost> staffCostMap = new HashMap<String,StaffCost>();
        Map<String,List<StaffCost>> staffCostNameMap = new HashMap<String,List<StaffCost>>();
        if(allStaffs!=null){
            for(StaffCost staffCost : allStaffs){
                staffCostMap.put(staffCost.getStaff_no().trim(), staffCost);

                if(staffCostNameMap.containsKey(staffCost.getStaff_name().trim())){
                    List<StaffCost> staffCosts = staffCostNameMap.get(staffCost.getStaff_name().trim());
                    staffCosts.add(staffCost);
                    staffCostNameMap.put(staffCost.getStaff_name().trim(), staffCosts);
                }else {
                    List<StaffCost> staffCosts = new ArrayList<StaffCost>();
                    staffCosts.add(staffCost);
                    staffCostNameMap.put(staffCost.getStaff_name().trim(), staffCosts);
                }

            }
        }

        for(StaffAdditionalDeduction sad : staffAdditionalDeductions){
            boolean b = checkStaffAdditionalDeduction(sad,staffCostMap,staffCostNameMap);
        }


        boolean isAllOK = true;
        index = 0;
        for(StaffAdditionalDeduction sad : staffAdditionalDeductions){
            if(sad.getErrorInfo()==null || sad.getErrorInfo().trim().length() <= 0){
                try{
                    if(sad.getStaffCost() != null) {
                        staffCostService.updateStaffCostAdditionalDeduction(toStaffCost(sad.getStaffCost(), sad));
                        index++;
                    }else {
                        sad.setErrorInfo("人员工号，人员名称错误");
                        isAllOK = false;
                    }
                }catch(Exception e){
                    sad.setErrorInfo(e.getMessage());
                    isAllOK = false;
                }
            }else {
                isAllOK = false;
            }
        }

        if(isAllOK){
            return this.ajaxForwardSuccess(request, rel, true);
        }else {
            request.getSession().setAttribute(sessionAttr, staffAdditionalDeductions);
            request.setAttribute("forwardUrl", request.getContextPath()+"/StaffAdditionalDeductionAction.do?method=importResult");
            return this.ajaxForwardError(request, "导入的人员信息中有些问题！ ");
        }

    }

    /**
     * 检查导入的数据正确性
     * @param sad
     * @param staffCostMap
     * @return
     */
    private boolean checkStaffAdditionalDeduction(StaffAdditionalDeduction sad, Map<String, StaffCost> staffCostMap,Map<String,List<StaffCost>> staffCostNameMap) {


        boolean b = true;
        if(sad.getStaff_no() == null || sad.getStaff_no().isEmpty()){
            if(sad.getStaff_name() != null && !sad.getStaff_name().isEmpty()) {
                if (staffCostNameMap.containsKey(sad.getStaff_name())){
                    List<StaffCost> staffCosts = staffCostNameMap.get(sad.getStaff_name());
                    if(staffCosts.size() != 1){
                        sad.setErrorInfo(sad.getErrorInfo() + "该名称的人员有多个，请填写工号;");
                        b = false;
                    }else {
                        StaffCost staffCost = staffCosts.get(0);
                        sad.setStaffCost(staffCost);
                        sad.setStaff_id(staffCost.getStaff_id());
                        sad.setStaff_no(staffCost.getStaff_no());
                        sad.setStaff_name(staffCost.getStaff_name());
                    }
                }
            }else {
                sad.setErrorInfo(sad.getErrorInfo() + "请填写工号或者名称;");
                b = false;
            }
        }else {
            if(staffCostMap.containsKey(sad.getStaff_no())){
                StaffCost staffCost = staffCostMap.get(sad.getStaff_no());
                if(sad.getStaff_name() != null && !sad.getStaff_name().isEmpty()){
                    if(!sad.getStaff_name().equals(staffCost.getStaff_name().trim())){
                        sad.setErrorInfo(sad.getErrorInfo() + "工号和名称不匹配;");
                        b = false;
                    }
                }
                sad.setStaffCost(staffCost);
                sad.setStaff_id(staffCost.getStaff_id());
                sad.setStaff_no(staffCost.getStaff_no());
                sad.setStaff_name(staffCost.getStaff_name());
            }else {
                sad.setErrorInfo(sad.getErrorInfo() + "工号错误;");
                b = false;
            }
        }

        if(sad.getChildren_education()<0){
            sad.setErrorInfo(sad.getErrorInfo() + "请填写正确的子女教育;");
            b = false;
        }

        if(sad.getContinuing_education()<0){
            sad.setErrorInfo(sad.getErrorInfo() + "请填写正确的继续教育;");
            b = false;
        }

        if(sad.getHousing_loans()<0 || sad.getHousing_loans() > 1000){
            sad.setErrorInfo(sad.getErrorInfo() + "请填写正确的住房贷款利息;");
            b = false;
        }

        if(sad.getHousing_rent()<0 || sad.getHousing_rent()>1500){
            sad.setErrorInfo(sad.getErrorInfo() + "请填写正确的住房租金;");
            b = false;
        }

        if(sad.getSupport_elderly()<0 || sad.getSupport_elderly()>2000){
            sad.setErrorInfo(sad.getErrorInfo() + "请填写正确的赡养老人;");
            b = false;
        }


        if(sad.getErrorInfo() != null && !sad.getErrorInfo().isEmpty()) {
            b = false;
        }

        return b;
    }


    @RequestMapping(params = "method=downloadtemplet")
    public void downloadtemplet(StaffCost searchStaffCost,HttpServletRequest request, HttpServletResponse response) throws Exception {

        UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.STAFFVIEW.getId());

        if(searchStaffCost.getDept_id() == null || searchStaffCost.getDept_id().isEmpty()){
            searchStaffCost.setDept_id(request.getParameter("dept.dept_id"));
        }
        if(searchStaffCost.getDept_name() == null || searchStaffCost.getDept_name().isEmpty()){
            searchStaffCost.setDept_name(request.getParameter("dept.dept_name"));
        }

        searchStaffCost.setDelete_flag(EnumYesNo.No.getCode());
        Pager<StaffCost> pager = staffCostService.queryStaffCost(searchStaffCost, null,userPermit, PubMethod.getPagerByAll(request, StaffCost.class));


        List<StaffAdditionalDeduction> list = new ArrayList<StaffAdditionalDeduction>();
        if(pager.getResultList() != null && !pager.getResultList().isEmpty()){
            for(StaffCost staffCost : pager.getResultList()){
                list.add(toStaffAdditionalDeduction(staffCost));
            }
        }

        try{
            BusinessExcel.export(response, null, list, StaffAdditionalDeduction.class , false);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    private StaffAdditionalDeduction toStaffAdditionalDeduction(StaffCost staffCost) {
        StaffAdditionalDeduction sad = new StaffAdditionalDeduction();
        sad.setStaff_id(staffCost.getStaff_id());
        sad.setStaff_name(staffCost.getStaff_name());
        sad.setStaff_no(staffCost.getStaff_no());
        sad.setIdentity_card_number(staffCost.getIdentity_card_number());
        sad.setChildren_education(staffCost.getChildren_education());
        sad.setContinuing_education(staffCost.getContinuing_education());
        sad.setHousing_loans(staffCost.getHousing_loans());
        sad.setHousing_rent(staffCost.getHousing_rent());
        sad.setSupport_elderly(staffCost.getSupport_elderly());
        return  sad;
    }


    private StaffCost toStaffCost(StaffCost staffCost ,StaffAdditionalDeduction sad) {
        staffCost.setStaff_id(sad.getStaff_id());
        staffCost.setStaff_name(sad.getStaff_name());
        staffCost.setStaff_no(sad.getStaff_no());
        staffCost.setChildren_education(sad.getChildren_education());
        staffCost.setContinuing_education(sad.getContinuing_education());
        staffCost.setHousing_loans(sad.getHousing_loans());
        staffCost.setHousing_rent(sad.getHousing_rent());
        staffCost.setSupport_elderly(sad.getSupport_elderly());
        return staffCost;
    }


}
