package com.pm.service;


import com.pm.dao.IPersonnelReportDao;
import com.pm.vo.personnelreport.PersonnelChangeVo;
import com.pm.vo.personnelreport.PersonnelInsuranceFundVo;
import com.pm.vo.personnelreport.PersonnelStructureVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 * @author Administrator
 */
public interface IPersonnelReportService {




    /**
     * 获取人力资源总体结构
     * @param month
     * @param outsource_staff
     * @return
     */
    public PersonnelStructureVo getStaffStructure(int month , String outsource_staff) ;


    /**
     * 获取项目人员变动信息
     * @param month
     * @param outsource_staff
     * @return
     */
    public List<PersonnelChangeVo> getStaffChange(int month , String outsource_staff) ;


    /**
     * 获取社保基金信息
     * @param month
     * @param outsource_staff
     * @return
     */
    public List<PersonnelInsuranceFundVo> getPersonnelInsuranceFund(int month , String outsource_staff) ;

}
