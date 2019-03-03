package com.pm.dao;

import com.pm.vo.AnalysisDepartVo;
import com.pm.vo.AnalysisSalesVo;
import com.pm.vo.AnalysisSearch;
import com.pm.vo.UserPermit;

import java.util.List;

/**
 * @author Administrator
 */
public interface IAnalysisSalesDao {



    /**
     * 回款
     * @param analysisSearch
     * @param userPermit
     * @return
     */
    public List<AnalysisSalesVo> querySalesReceivedPayments(AnalysisSearch analysisSearch, UserPermit userPermit);




    /**
     * 所有成本
     * @param analysisSearch
     * @param userPermit
     * @return
     */
    public List<AnalysisSalesVo> querySalesAllCosts(AnalysisSearch analysisSearch, UserPermit userPermit);
}
