package com.pm.dao.impl;

import com.common.daos.BatisDao;
import com.pm.dao.IAnalysisSalesDao;
import com.pm.util.constant.EnumStaticType;
import com.pm.vo.AnalysisSalesVo;
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
public class AnalysisSalesDaoImpl extends BatisDao implements IAnalysisSalesDao {

    @Override
    public List<AnalysisSalesVo> querySalesReceivedPayments(AnalysisSearch analysisSearch, UserPermit userPermit) {

        Map<String,Object> map = new HashMap<String,Object>();
        map.put(AnalysisSearch.class.getSimpleName() , analysisSearch);
        map.put(UserPermit.class.getSimpleName() , userPermit);
        String sql = "AnalysisSalesMapping.queryXStaisticsAnalysis";
        analysisSearch.setX(EnumStaticType.S10.getCode());
        analysisSearch.setGroupSelect("sales_id,sales_name");
        analysisSearch.setGroupBy("sales_id,sales_name");
        return this.query(sql, AnalysisSalesVo.class, map);
    }

    @Override
    public List<AnalysisSalesVo> querySalesAllCosts(AnalysisSearch analysisSearch, UserPermit userPermit) {

        Map<String,Object> map = new HashMap<String,Object>();
        map.put(AnalysisSearch.class.getSimpleName() , analysisSearch);
        map.put(UserPermit.class.getSimpleName() , userPermit);
        String sql = "AnalysisSalesMapping.queryAllCost";
        analysisSearch.setGroupSelect("sales_id,sales_name");
        analysisSearch.setGroupBy("sales_id,sales_name");
        return this.query(sql, AnalysisSalesVo.class, map);
    }
}
