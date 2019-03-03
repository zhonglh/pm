package com.pm.dao;

import com.pm.vo.*;

import java.util.List;

/**
 * @author Administrator
 */
public interface IAnalysisDepartDao {



    /**
     * 部门回款
     * @param analysisSearch
     * @param userPermit
     * @return
     */
    public List<AnalysisDepartVo> queryDepartReceivedPayments(AnalysisSearch analysisSearch, UserPermit userPermit);




    /**
     * 部门所有成本
     * @param analysisSearch
     * @param userPermit
     * @return
     */
    public List<AnalysisDepartVo> queryDepartAllCosts(AnalysisSearch analysisSearch, UserPermit userPermit);
}
