package com.pm.service.impl;

import com.pm.dao.IAnalysisDao;
import com.pm.service.IAnalysisService;
import com.pm.vo.AnalysisResult;
import com.pm.vo.AnalysisSearch;
import com.pm.vo.UserPermit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 公司层面业务分析
 * @author Administrator
 */
@Component
public class AnalysisServiceImpl implements IAnalysisService {
    
    
    @Autowired
    private IAnalysisDao analysisDao;


    @Override
    public AnalysisResult queryMonthlyStatements(AnalysisSearch analysisSearch, UserPermit userPermit) {
        return analysisDao.queryMonthlyStatements(analysisSearch,userPermit);
    }

    @Override
    public AnalysisResult queryReceivedPayments(AnalysisSearch analysisSearch, UserPermit userPermit) {
        return analysisDao.queryReceivedPayments(analysisSearch,userPermit);
    }

    @Override
    public AnalysisResult queryReceivables(AnalysisSearch analysisSearch, UserPermit userPermit) {
        return analysisDao.queryReceivables(analysisSearch,userPermit);
    }

    @Override
    public AnalysisResult queryInvoices(AnalysisSearch analysisSearch, UserPermit userPermit) {
        return analysisDao.queryInvoices(analysisSearch,userPermit);
    }

    @Override
    public AnalysisResult queryProjectStaffCosts(AnalysisSearch analysisSearch, UserPermit userPermit) {
        return analysisDao.queryProjectStaffCosts(analysisSearch,userPermit);
    }

    @Override
    public AnalysisResult queryReimburseCosts(AnalysisSearch analysisSearch, UserPermit userPermit) {
        return analysisDao.queryReimburseCosts(analysisSearch,userPermit);
    }

    @Override
    public AnalysisResult queryProjectExpends(AnalysisSearch analysisSearch, UserPermit userPermit) {
        return analysisDao.queryProjectExpends(analysisSearch,userPermit);
    }

    @Override
    public AnalysisResult querySalseCosts(AnalysisSearch analysisSearch, UserPermit userPermit) {
        return analysisDao.querySalseCosts(analysisSearch,userPermit);
    }

    @Override
    public AnalysisResult queryDepartCosts(AnalysisSearch analysisSearch, UserPermit userPermit) {
        return analysisDao.queryDepartCosts(analysisSearch,userPermit);
    }

    @Override
    public AnalysisResult queryOtherStaffCosts(AnalysisSearch analysisSearch, UserPermit userPermit) {
        return analysisDao.queryOtherStaffCosts(analysisSearch,userPermit);
    }

    @Override
    public AnalysisResult queryCommonCosts(AnalysisSearch analysisSearch, UserPermit userPermit) {
        return analysisDao.queryCommonCosts(analysisSearch,userPermit);
    }

    @Override
    public List<AnalysisResult> queryDepartCostDetail(AnalysisSearch analysisSearch, UserPermit userPermit) {
        return analysisDao.queryDepartCostDetail(analysisSearch,userPermit);
    }
}
