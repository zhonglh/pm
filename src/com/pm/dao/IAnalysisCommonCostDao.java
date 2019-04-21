package com.pm.dao;

import com.pm.vo.AnalysisCommonCostVo;
import com.pm.vo.AnalysisResult;
import com.pm.vo.AnalysisSearch;
import com.pm.vo.UserPermit;

import java.util.List;

/**
 * @author Administrator
 */
public interface IAnalysisCommonCostDao {





    /**
     * 公共成本
     * @param analysisSearch
     * @param userPermit
     * @return
     */
    public List<AnalysisCommonCostVo> queryCommonCostAllCosts(AnalysisSearch analysisSearch, UserPermit userPermit);
}
