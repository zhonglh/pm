package com.pm.dao;

import com.pm.vo.personnelreport.PersonnelChangeVo;
import com.pm.vo.personnelreport.PersonnelInsuranceFundVo;
import com.pm.vo.personnelreport.PersonnelStructureVo;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public interface IPersonnelReportDao {


    public PersonnelStructureVo getStaffBaseStructure(Map<String,Object> search);

    public PersonnelStructureVo getStaffChangeProjectStructure(Map<String,Object> search);

    public PersonnelStructureVo getStaffWorkAttendanceStructure(Map<String,Object> search);

    public PersonnelStructureVo getStaffSalaryStructure(Map<String,Object> search);

    public PersonnelStructureVo getStaffInsuranceFundStructure(Map<String,Object> search);




    public List<PersonnelChangeVo> getStaffBaseChange(Map<String,Object> search);

    public List<PersonnelChangeVo> getStaffWorkAttendanceChange(Map<String,Object> search);

    public List<PersonnelChangeVo> getStaffSalaryChange(Map<String,Object> search);




    public List<PersonnelInsuranceFundVo> getStaffInsuranceFund(Map<String,Object> search);


}
