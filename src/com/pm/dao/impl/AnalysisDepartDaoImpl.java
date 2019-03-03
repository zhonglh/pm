package com.pm.dao.impl;

import com.common.daos.BatisDao;
import com.pm.dao.IAnalysisDepartDao;
import com.pm.util.constant.EnumStaticType;
import com.pm.vo.AnalysisDepartVo;
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
public class AnalysisDepartDaoImpl extends BatisDao implements IAnalysisDepartDao {

    @Override
    public List<AnalysisDepartVo> queryDepartReceivedPayments(AnalysisSearch analysisSearch, UserPermit userPermit) {

        Map<String,Object> map = new HashMap<String,Object>();
        map.put(AnalysisSearch.class.getSimpleName() , analysisSearch);
        map.put(UserPermit.class.getSimpleName() , userPermit);
        String sql = "AnalysisDepartMapping.queryXStaisticsAnalysis";
        analysisSearch.setX(EnumStaticType.S10.getCode());
        analysisSearch.setGroupSelect("dept_id,dept_name");
        analysisSearch.setGroupBy("dept_id,dept_name");
        return this.query(sql, AnalysisDepartVo.class, map);
    }

    @Override
    public List<AnalysisDepartVo> queryDepartAllCosts(AnalysisSearch analysisSearch, UserPermit userPermit) {

        Map<String,Object> map = new HashMap<String,Object>();
        map.put(AnalysisSearch.class.getSimpleName() , analysisSearch);
        map.put(UserPermit.class.getSimpleName() , userPermit);
        String sql = "AnalysisDepartMapping.queryAllCost";
        analysisSearch.setGroupSelect("dept_id,dept_name");
        analysisSearch.setGroupBy("dept_id,dept_name");
        return this.query(sql, AnalysisDepartVo.class, map);
    }
}
