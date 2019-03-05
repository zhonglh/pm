package com.pm.service.impl;

import com.common.utils.DateKit;
import com.pm.dao.IPersonnelReportDao;
import com.pm.service.IPersonnelReportService;
import com.pm.vo.personnelreport.PersonnelChangeVo;
import com.pm.vo.personnelreport.PersonnelInsuranceFundVo;
import com.pm.vo.personnelreport.PersonnelStructureVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author Administrator
 */
@Component
public class PersonnelReportServiceImpl implements IPersonnelReportService {


    @Autowired
    private IPersonnelReportDao personnelReportDao;


    private Map getSearchMap(int month, String outsource_staff){

        Map<String,Object> search = new HashMap<String,Object>();
        search.put("month" , month);
        search.put("outsource_staff" , outsource_staff);
        Date date1 = DateKit.fmtShortYMTStrToDate(String.valueOf(month)+"01" );
        Date date2 = DateKit.addDay(DateKit.addMonth(date1,1) , -1);
        search.put("date1" , date1);
        search.put("date2" , date2);

        return search ;
    }


    private PersonnelChangeVo getPersonnelChangeByProject(List<PersonnelChangeVo> changeVos , String project_id){
        for(PersonnelChangeVo changeVo : changeVos){
            if(StringUtils.isEmpty(changeVo.getProject_id()) && StringUtils.isEmpty(project_id)  ){
                return changeVo;
            }
            if(changeVo.getProject_id().equals(project_id)){
                return changeVo;
            }
        }
        return null;
    }

    @Override
    public PersonnelStructureVo getStaffStructure(int month, String outsource_staff) {

        Map search = getSearchMap( month,  outsource_staff);
        PersonnelStructureVo ps = personnelReportDao.getStaffBaseStructure(search);
        if(ps != null) {

            PersonnelStructureVo temp = personnelReportDao.getStaffChangeProjectStructure(search);
            if (temp != null) {
                ps.setMonth_change_project_peoples(temp.getMonth_change_project_peoples());
            }

            temp = personnelReportDao.getStaffWorkAttendanceStructure(search);
            if (temp != null) {
                ps.setMonth_work_attendance_peoples(temp.getMonth_work_attendance_peoples());
            }

            temp = personnelReportDao.getStaffSalaryStructure(search);
            if (temp != null) {
                ps.setMonth_salary_peoples(temp.getMonth_salary_peoples());
                ps.setMonth_salary_amount(temp.getMonth_salary_amount());
                ps.setMonth_average_salary_amount(temp.getMonth_average_salary_amount());
            }

            temp = personnelReportDao.getStaffInsuranceFundStructure(search);
            if (temp != null) {
                ps.setMonth_insurance_fund_peoples(temp.getMonth_insurance_fund_peoples());
            }
        }else {
            ps = new PersonnelStructureVo();
        }

        return ps;


    }

    @Override
    public List<PersonnelChangeVo> getStaffChange(int month, String outsource_staff) {

        Map search = getSearchMap( month,  outsource_staff);
        List<PersonnelChangeVo> baseChanges = this.personnelReportDao.getStaffBaseChange(search);
        if(baseChanges != null && !baseChanges.isEmpty()){
            List<PersonnelChangeVo> tempChanges = this.personnelReportDao.getStaffWorkAttendanceChange(search);
            if(tempChanges != null && !tempChanges.isEmpty()){
                for(PersonnelChangeVo pc : tempChanges){
                    PersonnelChangeVo baseChange = getPersonnelChangeByProject(baseChanges , pc.getProject_id());
                    if(baseChange != null){
                        baseChange.setMonth_work_attendance_peoples(pc.getMonth_work_attendance_peoples());
                    }
                }
            }

            tempChanges = this.personnelReportDao.getStaffSalaryChange(search);
            if(tempChanges != null && !tempChanges.isEmpty()){
                for(PersonnelChangeVo pc : tempChanges){
                    PersonnelChangeVo baseChange = getPersonnelChangeByProject(baseChanges , pc.getProject_id());
                    if(baseChange != null){
                        baseChange.setMonth_salary_amount(pc.getMonth_salary_amount());
                        baseChange.setMonth_salary_peoples(pc.getMonth_salary_peoples());
                        baseChange.setMonth_average_salary_amount(pc.getMonth_average_salary_amount());
                    }
                }
            }
        }else {
            baseChanges = new ArrayList<PersonnelChangeVo>();
        }

        return baseChanges;
    }

    @Override
    public List<PersonnelInsuranceFundVo> getPersonnelInsuranceFund(int month, String outsource_staff) {
        Map search = getSearchMap( month,  outsource_staff);
        return personnelReportDao.getStaffInsuranceFund(search);
    }
}
