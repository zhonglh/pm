package com.pm.service.impl;

import com.pm.dao.IAnalysisDao;
import com.pm.service.IAnalysisService;
import com.pm.vo.AnalysisResult;
import com.pm.vo.AnalysisSearch;
import com.pm.vo.AnalysisVo;
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

    private void preYearSearch(AnalysisSearch analysisSearch){
        analysisSearch.setMonth1(analysisSearch.getMonth1() - 100);
        analysisSearch.setMonth2(analysisSearch.getMonth2() - 100);
    }

    private void processesult(AnalysisResult ar){
        ar.setIncrease_or_decrease(ar.getCurr_statistics_amount() - ar.getPre_statistics_amount());
        ar.setChange_ratio(ar.getIncrease_or_decrease()/ar.getPre_statistics_amount());
    }



    @Override
    public AnalysisResult queryMonthlyStatements(AnalysisSearch analysisSearch, UserPermit userPermit) {
        AnalysisResult ar = new AnalysisResult();
        AnalysisVo av = analysisDao.queryMonthlyStatements(analysisSearch,userPermit);
        ar.setCurr_statistics_amount(av.getAmount());
        preYearSearch(analysisSearch);
        av = analysisDao.queryMonthlyStatements(analysisSearch,userPermit);
        ar.setPre_statistics_amount(av.getAmount());
        processesult(ar);
        return ar;
    }

    @Override
    public AnalysisResult queryReceivedPayments(AnalysisSearch analysisSearch, UserPermit userPermit) {
        return new AnalysisResult();
    }

    @Override
    public AnalysisResult queryReceivables(AnalysisSearch analysisSearch, UserPermit userPermit) {
        return new AnalysisResult();
    }

    @Override
    public AnalysisResult queryInvoices(AnalysisSearch analysisSearch, UserPermit userPermit) {
        return new AnalysisResult();
    }

    @Override
    public AnalysisResult queryProjectStaffCosts(AnalysisSearch analysisSearch, UserPermit userPermit) {
        return new AnalysisResult();
    }

    @Override
    public AnalysisResult queryReimburseCosts(AnalysisSearch analysisSearch, UserPermit userPermit) {
        return new AnalysisResult();
    }

    @Override
    public AnalysisResult queryProjectExpends(AnalysisSearch analysisSearch, UserPermit userPermit) {
        return new AnalysisResult();
    }

    @Override
    public AnalysisResult querySalseCosts(AnalysisSearch analysisSearch, UserPermit userPermit) {
        return new AnalysisResult();
    }

    @Override
    public AnalysisResult queryDepartCosts(AnalysisSearch analysisSearch, UserPermit userPermit) {
        return new AnalysisResult();
    }

    @Override
    public AnalysisResult queryOtherStaffCosts(AnalysisSearch analysisSearch, UserPermit userPermit) {
        return new AnalysisResult();
    }

    @Override
    public AnalysisResult queryCommonCosts(AnalysisSearch analysisSearch, UserPermit userPermit) {
        return new AnalysisResult();
    }

    @Override
    public List<AnalysisResult> queryDepartCostDetail(AnalysisSearch analysisSearch, UserPermit userPermit) {
        return null;
    }
}
