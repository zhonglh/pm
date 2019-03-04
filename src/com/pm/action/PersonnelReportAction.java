package com.pm.action;

import com.common.actions.BaseAction;
import com.common.utils.DateKit;
import com.pm.vo.personnelreport.PersonnelChangeVo;
import com.pm.vo.personnelreport.PersonnelInsuranceFundVo;
import com.pm.vo.personnelreport.PersonnelStructureVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * 月度人事报表
 * @author Administrator
 */
@Controller
@RequestMapping(value = "PersonnelReportAction.do")
public class PersonnelReportAction extends BaseAction {


    /**
     * 列表界面
     * @param month
     * @param outsource_staff
     * @param res
     * @param request
     * @return
     */
    @RequestMapping(params = "method=list")
    public String list(int month , String outsource_staff ,HttpServletResponse res, HttpServletRequest request){
        if(month == 0){
            String monthStr = DateKit.fmtDateToStr( DateKit.addMonth(new Date() , -1) ,"yyyyMM");
            month = Integer.parseInt(monthStr);
        }

        request.setAttribute("month" , month);
        request.setAttribute("outsource_staff" , outsource_staff);

        return "statistics/monthly_personnel_report";
    }


    @RequestMapping(params = "method=export")
    public void export(int month , String outsource_staff , HttpServletResponse res, HttpServletRequest request){

        PersonnelStructureVo personnelStructureVo = null;

        List<PersonnelChangeVo> personnelChangeVos = null;

        List<PersonnelInsuranceFundVo> personnelInsuranceFundVos = null;




    }



}
