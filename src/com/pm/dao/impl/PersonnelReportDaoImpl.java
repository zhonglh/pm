package com.pm.dao.impl;

import com.common.daos.BatisDao;
import com.pm.dao.IPersonnelReportDao;
import com.pm.vo.personnelreport.PersonnelChangeVo;
import com.pm.vo.personnelreport.PersonnelInsuranceFundVo;
import com.pm.vo.personnelreport.PersonnelStructureVo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@Component
public class PersonnelReportDaoImpl extends BatisDao implements IPersonnelReportDao {

    @Override
    public PersonnelStructureVo getStaffBaseStructure(Map<String, Object> search) {
        String sql = "StaffStructureMapping.getStaffBaseStructure";
        return this.querySingle(sql,PersonnelStructureVo.class ,search);
    }

    @Override
    public PersonnelStructureVo getStaffChangeProjectStructure(Map<String, Object> search) {
        String sql = "StaffStructureMapping.getStaffChangeProjectStructure";
        return this.querySingle(sql,PersonnelStructureVo.class ,search);
    }

    @Override
    public PersonnelStructureVo getStaffWorkAttendanceStructure(Map<String, Object> search) {
        String sql = "StaffStructureMapping.getStaffWorkAttendanceStructure";
        return this.querySingle(sql,PersonnelStructureVo.class ,search);
    }

    @Override
    public PersonnelStructureVo getStaffSalaryStructure(Map<String, Object> search) {
        String sql = "StaffStructureMapping.getStaffSalaryStructure";
        return this.querySingle(sql,PersonnelStructureVo.class ,search);
    }

    @Override
    public PersonnelStructureVo getStaffInsuranceFundStructure(Map<String, Object> search) {
        String sql = "StaffStructureMapping.getStaffInsuranceFundStructure";
        return this.querySingle(sql,PersonnelStructureVo.class ,search);
    }







    @Override
    public List<PersonnelChangeVo> getStaffBaseChange(Map<String, Object> search) {
        String sql = "staffChangeMapping.getStaffBaseChange";
        return this.query(sql,PersonnelChangeVo.class ,search);
    }

    @Override
    public List<PersonnelChangeVo> getStaffWorkAttendanceChange(Map<String, Object> search) {
        String sql = "staffChangeMapping.getStaffWorkAttendanceChange";
        return this.query(sql,PersonnelChangeVo.class ,search);
    }

    @Override
    public List<PersonnelChangeVo> getStaffSalaryChange(Map<String, Object> search) {
        String sql = "staffChangeMapping.getStaffSalaryChange";
        return this.query(sql,PersonnelChangeVo.class ,search);
    }






    @Override
    public List<PersonnelInsuranceFundVo> getStaffInsuranceFund(Map<String, Object> search) {
        String sql = "StaffInsuranceFundMapping.getStaffInsuranceFund";
        return this.query(sql,PersonnelInsuranceFundVo.class ,search);
    }
}
