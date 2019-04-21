package com.pm.dao.impl;

import com.common.daos.BatisDao;
import com.pm.dao.IAnalysisCommonCostDao;
import com.pm.util.constant.EnumStaticType;
import com.pm.vo.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author zhonglh
 */
@Component
public class AnalysisCommonCostDaoImpl extends BatisDao implements IAnalysisCommonCostDao {
    @Override
    public List<AnalysisCommonCostVo> queryCommonCostAllCosts(AnalysisSearch analysisSearch, UserPermit userPermit) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put(AnalysisSearch.class.getSimpleName() , analysisSearch);
        String sql = "AnalysisCommonCostMapping.queryCommonCost";
        return query(sql, AnalysisCommonCostVo.class, map);
    }
}
