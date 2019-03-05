package com.pm.action;

import com.common.actions.BaseAction;
import com.common.utils.DateKit;
import com.common.utils.DateUtils;
import com.pm.service.IPersonnelReportService;
import com.pm.util.excel.BusinessExExcel;
import com.pm.vo.personnelreport.PersonnelChangeVo;
import com.pm.vo.personnelreport.PersonnelInsuranceFundVo;
import com.pm.vo.personnelreport.PersonnelStructureVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 月度人事报表
 * @author Administrator
 */
@Controller
@RequestMapping(value = "PersonnelReportAction.do")
public class PersonnelReportAction extends BaseAction {


    @Autowired
    private IPersonnelReportService personnelReportService;


    /**
     * 列表界面
     * @param month
     * @param outsource_staff
     * @param res
     * @param request
     * @return
     */
    @RequestMapping(params = "method=list")
    public String list(String month , String outsource_staff ,HttpServletResponse res, HttpServletRequest request){


        int imonth = 0;
        if(StringUtils.isEmpty(month)){
            month = DateKit.fmtDateToStr( DateKit.addMonth(new Date() , -1) ,"yyyyMM");
        }
        imonth = Integer.parseInt(month);

        PersonnelStructureVo personnelStructureVo = personnelReportService.getStaffStructure(imonth , outsource_staff);

        List<PersonnelChangeVo> personnelChangeVos = personnelReportService.getStaffChange(imonth , outsource_staff);

        List<PersonnelInsuranceFundVo> personnelInsuranceFundVos = personnelReportService.getPersonnelInsuranceFund(imonth , outsource_staff);

        request.setAttribute("month" , month);
        request.setAttribute("outsource_staff" , outsource_staff);


        request.setAttribute("personnelStructureVo",personnelStructureVo);
        request.setAttribute("personnelChangeVos",personnelChangeVos);
        request.setAttribute("personnelInsuranceFundVos",personnelInsuranceFundVos);




        return "statistics/monthly_personnel_report";
    }


    @RequestMapping(params = "method=export")
    public void export(String month , String outsource_staff , HttpServletResponse res, HttpServletRequest request){

        int imonth = 0;
        if(StringUtils.isEmpty(month)){
            month = DateKit.fmtDateToStr( DateKit.addMonth(new Date() , -1) ,"yyyyMM");
        }
        imonth = Integer.parseInt(month);

        PersonnelStructureVo personnelStructureVo = personnelReportService.getStaffStructure(imonth , outsource_staff);

        List<PersonnelChangeVo> personnelChangeVos = personnelReportService.getStaffChange(imonth , outsource_staff);

        List<PersonnelInsuranceFundVo> personnelInsuranceFundVos = personnelReportService.getPersonnelInsuranceFund(imonth , outsource_staff);




        try{
            String header = DateUtils.ymTitle(imonth) +"人事报表";
            List<String> headers = new ArrayList<String>(1);
            headers.add(header);

            List<PersonnelStructureVo> list1 = new ArrayList<PersonnelStructureVo>();
            list1.add(personnelStructureVo);


            List<List<?>> lists = new ArrayList<>();
            lists.add(list1);
            lists.add(personnelChangeVos);
            lists.add(personnelInsuranceFundVos);
            BusinessExExcel.export(res, headers , lists,false);
        }catch(Exception e){
            e.printStackTrace();
        }



    }



}
