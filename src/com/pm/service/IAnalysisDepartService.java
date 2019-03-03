package com.pm.service;

import com.pm.vo.AnalysisResult;
import com.pm.vo.AnalysisSearch;
import com.pm.vo.UserPermit;

import java.util.List;

/**
 * @author Administrator
 */
public interface IAnalysisDepartService {




    /**
     * 部门回款
     * @param analysisSearch
     * @param userPermit
     * @return
     */
    public List<AnalysisResult> queryDepartReceivedPayments(AnalysisSearch analysisSearch, UserPermit userPermit);




    /**
     * 部门所有成本
     * @param analysisSearch
     * @param userPermit
     * @return
     */
    public List<AnalysisResult> queryDepartAllCosts(AnalysisSearch analysisSearch, UserPermit userPermit);


    /**
     * 现金流(回款-成本)
     * @param ars1
     * @param ars2
     * @return
     */
    public List<AnalysisResult> queryCashFlow(List<AnalysisResult> ars1, List<AnalysisResult> ars2);


}
