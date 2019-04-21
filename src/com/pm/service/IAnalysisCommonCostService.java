package com.pm.service;

import com.pm.vo.AnalysisResult;
import com.pm.vo.AnalysisSearch;
import com.pm.vo.UserPermit;

import java.util.List;

/**
 * @author zhonglh
 */
public interface IAnalysisCommonCostService {





    /**
     * 公共费用所有成本
     * @param analysisSearch
     * @param userPermit
     * @return
     */
    public List<AnalysisResult> queryCommonCostCosts(AnalysisSearch analysisSearch, UserPermit userPermit);


}
