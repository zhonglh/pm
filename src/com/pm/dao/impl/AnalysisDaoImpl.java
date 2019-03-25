package com.pm.dao.impl;


import com.common.daos.BatisDao;
import com.pm.dao.IAnalysisDao;
import com.pm.util.constant.EnumStaticType;
import com.pm.vo.AnalysisVo;
import com.pm.vo.AnalysisSearch;
import com.pm.vo.UserPermit;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@Component
public class AnalysisDaoImpl extends BatisDao implements IAnalysisDao{
    @Override
    public AnalysisVo queryMonthlyStatements(AnalysisSearch analysisSearch, UserPermit userPermit) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put(AnalysisSearch.class.getSimpleName() , analysisSearch);
        map.put(UserPermit.class.getSimpleName() , userPermit);
        String sql = "AnalysisMapping.queryXStaisticsAnalysis";
        analysisSearch.setX(EnumStaticType.S20.getCode());
        return this.querySingle(sql, AnalysisVo.class, map);
    }

    @Override
    public AnalysisVo queryReceivedPayments(AnalysisSearch analysisSearch, UserPermit userPermit) {

        Map<String,Object> map = new HashMap<String,Object>();
        map.put(AnalysisSearch.class.getSimpleName() , analysisSearch);
        map.put(UserPermit.class.getSimpleName() , userPermit);
        String sql = "AnalysisMapping.queryXStaisticsAnalysis";
        analysisSearch.setX(EnumStaticType.S10.getCode());
        return this.querySingle(sql, AnalysisVo.class, map);
    }


    @Override
    public AnalysisVo queryInvoices(AnalysisSearch analysisSearch, UserPermit userPermit) {

        Map<String,Object> map = new HashMap<String,Object>();
        map.put(AnalysisSearch.class.getSimpleName() , analysisSearch);
        map.put(UserPermit.class.getSimpleName() , userPermit);
        String sql = "AnalysisMapping.queryXStaisticsAnalysis";
        analysisSearch.setX(EnumStaticType.S70.getCode());
        return this.querySingle(sql, AnalysisVo.class, map);
    }

    @Override
    public AnalysisVo queryProjectStaffCosts(AnalysisSearch analysisSearch, UserPermit userPermit) {

        Map<String,Object> map = new HashMap<String,Object>();
        map.put(AnalysisSearch.class.getSimpleName() , analysisSearch);
        map.put(UserPermit.class.getSimpleName() , userPermit);
        String sql = "AnalysisMapping.queryXStaisticsAnalysis";
        analysisSearch.setX(EnumStaticType.S51.getCode());
        return this.querySingle(sql, AnalysisVo.class, map);
    }

    @Override
    public AnalysisVo queryReimburseCosts(AnalysisSearch analysisSearch, UserPermit userPermit) {

        Map<String,Object> map = new HashMap<String,Object>();
        map.put(AnalysisSearch.class.getSimpleName() , analysisSearch);
        map.put(UserPermit.class.getSimpleName() , userPermit);
        String sql = "AnalysisMapping.queryXStaisticsAnalysis";
        analysisSearch.setX(EnumStaticType.S30.getCode());
        return this.querySingle(sql, AnalysisVo.class, map);
    }

    @Override
    public AnalysisVo queryProjectExpends(AnalysisSearch analysisSearch, UserPermit userPermit) {

        Map<String,Object> map = new HashMap<String,Object>();
        map.put(AnalysisSearch.class.getSimpleName() , analysisSearch);
        map.put(UserPermit.class.getSimpleName() , userPermit);
        String sql = "AnalysisMapping.queryXStaisticsAnalysis";
        analysisSearch.setX(EnumStaticType.S40.getCode());
        return this.querySingle(sql, AnalysisVo.class, map);
    }

    @Override
    public AnalysisVo querySalseCosts(AnalysisSearch analysisSearch, UserPermit userPermit) {

        Map<String,Object> map = new HashMap<String,Object>();
        map.put(AnalysisSearch.class.getSimpleName() , analysisSearch);
        map.put(UserPermit.class.getSimpleName() , userPermit);
        String sql = "AnalysisMapping.queryXDepartCostAnalysis";
        analysisSearch.setX(EnumStaticType.S100.getCode());
        return this.querySingle(sql, AnalysisVo.class, map);
    }

    @Override
    public AnalysisVo queryDepartCosts(AnalysisSearch analysisSearch, UserPermit userPermit) {

        Map<String,Object> map = new HashMap<String,Object>();
        map.put(AnalysisSearch.class.getSimpleName() , analysisSearch);
        map.put(UserPermit.class.getSimpleName() , userPermit);
        String sql = "AnalysisMapping.queryXDepartCostAnalysis";
        analysisSearch.setX(EnumStaticType.S101.getCode());
        return this.querySingle(sql, AnalysisVo.class, map);
    }

    @Override
    public AnalysisVo queryOtherStaffCosts(AnalysisSearch analysisSearch, UserPermit userPermit) {

        Map<String,Object> map = new HashMap<String,Object>();
        map.put(AnalysisSearch.class.getSimpleName() , analysisSearch);
        map.put(UserPermit.class.getSimpleName() , userPermit);
        String sql = "AnalysisMapping.queryXDepartCostAnalysis";
        analysisSearch.setX(EnumStaticType.S201.getCode());
        return this.querySingle(sql, AnalysisVo.class, map);
    }

    @Override
    public AnalysisVo queryCommonCosts(AnalysisSearch analysisSearch, UserPermit userPermit) {

        Map<String,Object> map = new HashMap<String,Object>();
        map.put(AnalysisSearch.class.getSimpleName() , analysisSearch);
        map.put(UserPermit.class.getSimpleName() , userPermit);
        String sql = "AnalysisMapping.queryXDepartCostAnalysis";
        analysisSearch.setX(EnumStaticType.S80.getCode());
        return this.querySingle(sql, AnalysisVo.class, map);
    }

    @Override
    public List<AnalysisVo> queryDepartCostDetail(AnalysisSearch analysisSearch, UserPermit userPermit) {

        Map<String,Object> map = new HashMap<String,Object>();
        map.put(AnalysisSearch.class.getSimpleName() , analysisSearch);
        map.put(UserPermit.class.getSimpleName() , userPermit);
        String sql = "AnalysisMapping.queryXDepartCostAnalysis";
        return this.query(sql, AnalysisVo.class, map);
    }
}
