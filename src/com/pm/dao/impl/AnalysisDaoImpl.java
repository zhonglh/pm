package com.pm.dao.impl;


import com.common.daos.BatisDao;
import com.pm.dao.IAnalysisDao;
import com.pm.util.constant.EnumStaticType;
import com.pm.vo.AnalysisResult;
import com.pm.vo.AnalysisSearch;
import com.pm.vo.AnalysisVo;
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
    public AnalysisResult queryMonthlyStatements(AnalysisSearch analysisSearch, UserPermit userPermit) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put(AnalysisSearch.class.getSimpleName() , analysisSearch);
        map.put(UserPermit.class.getSimpleName() , userPermit);
        String sql = "AnalysisMapping.queryXStaisticsAnalysis";
        analysisSearch.setX(EnumStaticType.S20.getCode());
        AnalysisVo analysisVo =  this.querySingle(sql, AnalysisVo.class, map);
        AnalysisResult ar = new AnalysisResult();
        ar.setCurr_statistics_amount(analysisVo.getAmount());
        return ar;
    }

    @Override
    public AnalysisResult queryReceivedPayments(AnalysisSearch analysisSearch, UserPermit userPermit) {

        Map<String,Object> map = new HashMap<String,Object>();
        map.put(AnalysisSearch.class.getSimpleName() , analysisSearch);
        map.put(UserPermit.class.getSimpleName() , userPermit);
        String sql = "AnalysisMapping.queryXStaisticsAnalysis";
        analysisSearch.setX(EnumStaticType.S10.getCode());
        return this.querySingle(sql, AnalysisResult.class, map);
    }

    @Override
    public AnalysisResult queryReceivables(AnalysisSearch analysisSearch, UserPermit userPermit) {

        Map<String,Object> map = new HashMap<String,Object>();
        map.put(AnalysisSearch.class.getSimpleName() , analysisSearch);
        map.put(UserPermit.class.getSimpleName() , userPermit);
        String sql = "AnalysisMapping.queryReceivables";
        //todo ,编写SQL
        analysisSearch.setX(EnumStaticType.S10.getCode());
        return this.querySingle(sql, AnalysisResult.class, map);
    }

    @Override
    public AnalysisResult queryInvoices(AnalysisSearch analysisSearch, UserPermit userPermit) {

        Map<String,Object> map = new HashMap<String,Object>();
        map.put(AnalysisSearch.class.getSimpleName() , analysisSearch);
        map.put(UserPermit.class.getSimpleName() , userPermit);
        String sql = "AnalysisMapping.queryXStaisticsAnalysis";
        analysisSearch.setX(EnumStaticType.S70.getCode());
        return this.querySingle(sql, AnalysisResult.class, map);
    }

    @Override
    public AnalysisResult queryProjectStaffCosts(AnalysisSearch analysisSearch, UserPermit userPermit) {

        Map<String,Object> map = new HashMap<String,Object>();
        map.put(AnalysisSearch.class.getSimpleName() , analysisSearch);
        map.put(UserPermit.class.getSimpleName() , userPermit);
        String sql = "AnalysisMapping.queryXStaisticsAnalysis";
        analysisSearch.setX(EnumStaticType.S51.getCode());
        return this.querySingle(sql, AnalysisResult.class, map);
    }

    @Override
    public AnalysisResult queryReimburseCosts(AnalysisSearch analysisSearch, UserPermit userPermit) {

        Map<String,Object> map = new HashMap<String,Object>();
        map.put(AnalysisSearch.class.getSimpleName() , analysisSearch);
        map.put(UserPermit.class.getSimpleName() , userPermit);
        String sql = "AnalysisMapping.queryXStaisticsAnalysis";
        analysisSearch.setX(EnumStaticType.S30.getCode());
        return this.querySingle(sql, AnalysisResult.class, map);
    }

    @Override
    public AnalysisResult queryProjectExpends(AnalysisSearch analysisSearch, UserPermit userPermit) {

        Map<String,Object> map = new HashMap<String,Object>();
        map.put(AnalysisSearch.class.getSimpleName() , analysisSearch);
        map.put(UserPermit.class.getSimpleName() , userPermit);
        String sql = "AnalysisMapping.queryXStaisticsAnalysis";
        analysisSearch.setX(EnumStaticType.S40.getCode());
        return this.querySingle(sql, AnalysisResult.class, map);
    }

    @Override
    public AnalysisResult querySalseCosts(AnalysisSearch analysisSearch, UserPermit userPermit) {

        Map<String,Object> map = new HashMap<String,Object>();
        map.put(AnalysisSearch.class.getSimpleName() , analysisSearch);
        map.put(UserPermit.class.getSimpleName() , userPermit);
        String sql = "AnalysisMapping.queryXStaisticsAnalysis";
        analysisSearch.setX(EnumStaticType.S40.getCode());
        return this.querySingle(sql, AnalysisResult.class, map);
    }

    @Override
    public AnalysisResult queryDepartCosts(AnalysisSearch analysisSearch, UserPermit userPermit) {

        Map<String,Object> map = new HashMap<String,Object>();
        map.put(AnalysisSearch.class.getSimpleName() , analysisSearch);
        map.put(UserPermit.class.getSimpleName() , userPermit);
        String sql = "AnalysisMapping.queryDepartCosts";
        return this.querySingle(sql, AnalysisResult.class, map);
    }

    @Override
    public AnalysisResult queryOtherStaffCosts(AnalysisSearch analysisSearch, UserPermit userPermit) {

        Map<String,Object> map = new HashMap<String,Object>();
        map.put(AnalysisSearch.class.getSimpleName() , analysisSearch);
        map.put(UserPermit.class.getSimpleName() , userPermit);
        String sql = "AnalysisMapping.queryOtherStaffCosts";
        return this.querySingle(sql, AnalysisResult.class, map);
    }

    @Override
    public AnalysisResult queryCommonCosts(AnalysisSearch analysisSearch, UserPermit userPermit) {

        Map<String,Object> map = new HashMap<String,Object>();
        map.put(AnalysisSearch.class.getSimpleName() , analysisSearch);
        map.put(UserPermit.class.getSimpleName() , userPermit);
        String sql = "AnalysisMapping.queryCommonCosts";
        return this.querySingle(sql, AnalysisResult.class, map);
    }

    @Override
    public List<AnalysisResult> queryDepartCostDetail(AnalysisSearch analysisSearch, UserPermit userPermit) {

        Map<String,Object> map = new HashMap<String,Object>();
        map.put(AnalysisSearch.class.getSimpleName() , analysisSearch);
        map.put(UserPermit.class.getSimpleName() , userPermit);
        String sql = "AnalysisMapping.queryCommonCostDetail";
        return this.query(sql, AnalysisResult.class, map);
    }
}
