package com.pm.dao;

import com.pm.vo.AnalysisVo;
import com.pm.vo.AnalysisSearch;
import com.pm.vo.UserPermit;

import java.util.List;

/**
 * 公司层面业务分析
 * @author Administrator
 */
public interface IAnalysisDao {



    /**
     * 结算单
     * @param analysisSearch
     * @param userPermit
     * @return
     */
    public AnalysisVo queryMonthlyStatements(AnalysisSearch analysisSearch, UserPermit userPermit);


    /**
     * 回款
     * @param analysisSearch
     * @param userPermit
     * @return
     */
    public AnalysisVo queryReceivedPayments(AnalysisSearch analysisSearch, UserPermit userPermit);



    /**
     * 应收款
     * @param analysisSearch
     * @param userPermit
     * @return
     */
    public AnalysisVo queryReceivables(AnalysisSearch analysisSearch, UserPermit userPermit);




    /**
     * 发票
     * @param analysisSearch
     * @param userPermit
     * @return
     */
    public AnalysisVo queryInvoices(AnalysisSearch analysisSearch, UserPermit userPermit);






    /**
     * 项目人员成本
     * @param analysisSearch
     * @param userPermit
     * @return
     */
    public AnalysisVo queryProjectStaffCosts(AnalysisSearch analysisSearch, UserPermit userPermit);




    /**
     * 项目报销成本
     * @param analysisSearch
     * @param userPermit
     * @return
     */
    public AnalysisVo queryReimburseCosts(AnalysisSearch analysisSearch, UserPermit userPermit);




    /**
     * 供应商付款
     * @param analysisSearch
     * @param userPermit
     * @return
     */
    public AnalysisVo queryProjectExpends(AnalysisSearch analysisSearch, UserPermit userPermit);


    /**
     * 部门销售费用
     * @param analysisSearch
     * @param userPermit
     * @return
     */
    public AnalysisVo querySalseCosts(AnalysisSearch analysisSearch, UserPermit userPermit);



    /**
     * 部门管理费用
     * @param analysisSearch
     * @param userPermit
     * @return
     */
    public AnalysisVo queryDepartCosts(AnalysisSearch analysisSearch, UserPermit userPermit);




    /**
     * 总部人员成本
     * @param analysisSearch
     * @param userPermit
     * @return
     */
    public AnalysisVo queryOtherStaffCosts(AnalysisSearch analysisSearch, UserPermit userPermit);




    /**
     * 公共费用成本
     * @param analysisSearch
     * @param userPermit
     * @return
     */
    public AnalysisVo queryCommonCosts(AnalysisSearch analysisSearch, UserPermit userPermit);


    /**
     * 管理费用明细
     * @param analysisSearch
     * @param userPermit
     * @return
     */
    public List<AnalysisVo> queryDepartCostDetail(AnalysisSearch analysisSearch, UserPermit userPermit);


}
