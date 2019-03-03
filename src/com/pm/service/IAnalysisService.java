package com.pm.service;


import com.pm.vo.AnalysisResult;
import com.pm.vo.AnalysisSearch;
import com.pm.vo.UserPermit;

import java.util.List;

/**
 * 公司层面业务分析
 * @author Administrator
 */
public interface IAnalysisService {



    /**
     * 结算单
     * @param analysisSearch
     * @param userPermit
     * @return
     */
    public AnalysisResult queryMonthlyStatements(AnalysisSearch analysisSearch, UserPermit userPermit);


    /**
     * 回款
     * @param analysisSearch
     * @param userPermit
     * @return
     */
    public AnalysisResult queryReceivedPayments(AnalysisSearch analysisSearch, UserPermit userPermit);




    /**
     * 发票
     * @param analysisSearch
     * @param userPermit
     * @return
     */
    public AnalysisResult queryInvoices(AnalysisSearch analysisSearch, UserPermit userPermit);






    /**
     * 项目人员成本
     * @param analysisSearch
     * @param userPermit
     * @return
     */
    public AnalysisResult queryProjectStaffCosts(AnalysisSearch analysisSearch, UserPermit userPermit);




    /**
     * 项目报销成本
     * @param analysisSearch
     * @param userPermit
     * @return
     */
    public AnalysisResult queryReimburseCosts(AnalysisSearch analysisSearch, UserPermit userPermit);




    /**
     * 供应商付款
     * @param analysisSearch
     * @param userPermit
     * @return
     */
    public AnalysisResult queryProjectExpends(AnalysisSearch analysisSearch, UserPermit userPermit);


    /**
     * 部门销售费用
     * @param analysisSearch
     * @param userPermit
     * @return
     */
    public AnalysisResult querySalseCosts(AnalysisSearch analysisSearch, UserPermit userPermit);



    /**
     * 部门管理费用
     * @param analysisSearch
     * @param userPermit
     * @return
     */
    public AnalysisResult queryDepartCosts(AnalysisSearch analysisSearch, UserPermit userPermit);




    /**
     * 总部人员成本
     * @param analysisSearch
     * @param userPermit
     * @return
     */
    public AnalysisResult queryOtherStaffCosts(AnalysisSearch analysisSearch, UserPermit userPermit);




    /**
     * 公共费用成本
     * @param analysisSearch
     * @param userPermit
     * @return
     */
    public AnalysisResult queryCommonCosts(AnalysisSearch analysisSearch, UserPermit userPermit);


    /**
     * 管理费用明细
     * @param analysisSearch
     * @param userPermit
     * @return
     */
    public List<AnalysisResult> queryDepartCostDetail(AnalysisSearch analysisSearch, UserPermit userPermit);

}
