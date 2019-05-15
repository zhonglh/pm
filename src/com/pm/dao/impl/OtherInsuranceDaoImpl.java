package com.pm.dao.impl;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.IInsuranceDao;
import com.pm.domain.business.Insurance;
import com.pm.vo.UserPermit;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class OtherInsuranceDaoImpl extends BatisDao implements IInsuranceDao {



    @Override
    public int addInsurance(Insurance insurance) {
        String sql = "OtherInsuranceMapping.addInsurance";
        return this.insert(sql, insurance);
    }

    @Override
    public int updateInsurance(Insurance insurance) {
        String sql = "OtherInsuranceMapping.updateInsurance";
        return this.update(sql, insurance);
    }

    @Override
    public void deleteInsurance(Insurance insurance) {
        String sql = "OtherInsuranceMapping.deleteInsurance";
        this.delete(sql, insurance);
    }

    @Override
    public void verifyInsurance(Insurance insurance) {
        String sql = "OtherInsuranceMapping.verifyInsurance";
        this.update(sql, insurance);
    }

    @Override
    public void unVerifyInsurance(Insurance insurance) {
        String sql = "OtherInsuranceMapping.unVerifyInsurance";
        this.update(sql, insurance);
    }

    @Override
    public Insurance getInsurance(String id) {

        String sql = "OtherInsuranceMapping.getInsurance";
        Insurance insurance = new Insurance();
        insurance.setId(id);
        List<Insurance> list = this.query(sql, Insurance.class, insurance);
        if(list == null || list.isEmpty()) return null;
        else return list.get(0);
    }


    @Override
    public int getNotCheckNumByWorkAttendance(Insurance insurance){
        String sql = "OtherInsuranceMapping.getNotCheckNumByWorkAttendance";
        return this.query4Int(sql , insurance);
    }


    @Override
    public List<Insurance> queryInsuranceByWorkAttendance(Insurance insurance){
        String sql = "OtherInsuranceMapping.queryInsuranceByWorkAttendance";
        return this.query(sql, Insurance.class, insurance);
    }



    @Override
    public Insurance queryInsuranceSum(Insurance insurance,  UserPermit userPermit) {
        String sql = "OtherInsuranceMapping.queryInsuranceSum";
        Map<String , Object> map = new HashMap<String , Object>();
        if(insurance !=null) map.put(insurance.getClass().getSimpleName(), insurance);
        if(userPermit !=null) map.put(userPermit.getClass().getSimpleName(), userPermit);
        return this.query(sql, Insurance.class, map).get(0);
    }


    @Override
    public Pager<Insurance> queryInsurance(
            Insurance insurance,
            UserPermit userPermit,
            Pager<Insurance> pager){

        String sql = "OtherInsuranceMapping.queryInsurance";
        Pager<Insurance> pager1 =  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql,Insurance.class, insurance,userPermit);

        return pager1;
    }
}
