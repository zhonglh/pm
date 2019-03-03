package com.pm.service.impl;

import com.common.utils.NumberKit;
import com.pm.dao.IAnalysisDao;
import com.pm.service.IAnalysisService;
import com.pm.util.AnalysisUtil;
import com.pm.vo.AnalysisResult;
import com.pm.vo.AnalysisSearch;
import com.pm.vo.AnalysisVo;
import com.pm.vo.UserPermit;
import org.springframework.beans.BeanUtils;
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
        AnalysisResult ar = new AnalysisResult();
        AnalysisVo av = analysisDao.queryMonthlyStatements(analysisSearch,userPermit);
        if(av != null) {
            ar.setCurr_statistics_amount(NumberKit.getNumberFormatByDouble(av.getAmount()));
        }
        AnalysisSearch preAnalysisSearch = AnalysisUtil.preYearSearch(analysisSearch);
        av = analysisDao.queryMonthlyStatements(preAnalysisSearch,userPermit);
        if(av != null) {
            ar.setPre_statistics_amount(NumberKit.getNumberFormatByDouble(av.getAmount()));
        }
        AnalysisUtil.processesult(ar);
        return ar;
    }

    @Override
    public AnalysisResult queryReceivedPayments(AnalysisSearch analysisSearch, UserPermit userPermit) {
        AnalysisResult ar = new AnalysisResult();
        AnalysisVo av = analysisDao.queryReceivedPayments(analysisSearch,userPermit);
        if(av != null) {
            ar.setCurr_statistics_amount(NumberKit.getNumberFormatByDouble(av.getAmount()));
        }
        AnalysisSearch preAnalysisSearch = AnalysisUtil.preYearSearch(analysisSearch);
        av = analysisDao.queryReceivedPayments(preAnalysisSearch,userPermit);
        if(av != null) {
            ar.setPre_statistics_amount(NumberKit.getNumberFormatByDouble(av.getAmount()));
        }
        AnalysisUtil.processesult(ar);
        return ar;
    }


    @Override
    public AnalysisResult queryInvoices(AnalysisSearch analysisSearch, UserPermit userPermit) {
        AnalysisResult ar = new AnalysisResult();
        AnalysisVo av = analysisDao.queryInvoices(analysisSearch,userPermit);
        if(av != null) {
            ar.setCurr_statistics_amount(NumberKit.getNumberFormatByDouble(av.getAmount()));
        }
        AnalysisSearch preAnalysisSearch = AnalysisUtil.preYearSearch(analysisSearch);
        av = analysisDao.queryInvoices(preAnalysisSearch,userPermit);
        if(av != null) {
            ar.setPre_statistics_amount(NumberKit.getNumberFormatByDouble(av.getAmount()));
        }
        AnalysisUtil.processesult(ar);
        return ar;
    }

    @Override
    public AnalysisResult queryProjectStaffCosts(AnalysisSearch analysisSearch, UserPermit userPermit) {
        AnalysisResult ar = new AnalysisResult();
        AnalysisVo av = analysisDao.queryProjectStaffCosts(analysisSearch,userPermit);
        if(av != null) {
            ar.setCurr_statistics_amount(NumberKit.getNumberFormatByDouble(av.getAmount()));
        }
        AnalysisSearch preAnalysisSearch = AnalysisUtil.preYearSearch(analysisSearch);
        av = analysisDao.queryProjectStaffCosts(preAnalysisSearch,userPermit);
        if(av != null) {
            ar.setPre_statistics_amount(NumberKit.getNumberFormatByDouble(av.getAmount()));
        }
        AnalysisUtil.processesult(ar);
        return ar;
    }

    @Override
    public AnalysisResult queryReimburseCosts(AnalysisSearch analysisSearch, UserPermit userPermit) {
        AnalysisResult ar = new AnalysisResult();
        AnalysisVo av = analysisDao.queryReimburseCosts(analysisSearch,userPermit);
        if(av != null) {
            ar.setCurr_statistics_amount(NumberKit.getNumberFormatByDouble(av.getAmount()));
        }
        AnalysisSearch preAnalysisSearch = AnalysisUtil.preYearSearch(analysisSearch);
        av = analysisDao.queryReimburseCosts(preAnalysisSearch,userPermit);
        if(av != null) {
            ar.setPre_statistics_amount(NumberKit.getNumberFormatByDouble(av.getAmount()));
        }
        AnalysisUtil.processesult(ar);
        return ar;
    }

    @Override
    public AnalysisResult queryProjectExpends(AnalysisSearch analysisSearch, UserPermit userPermit) {
        AnalysisResult ar = new AnalysisResult();
        AnalysisVo av = analysisDao.queryProjectExpends(analysisSearch,userPermit);
        if(av != null) {
            ar.setCurr_statistics_amount(NumberKit.getNumberFormatByDouble(av.getAmount()));
        }
        AnalysisSearch preAnalysisSearch = AnalysisUtil.preYearSearch(analysisSearch);
        av = analysisDao.queryProjectExpends(preAnalysisSearch,userPermit);
        if(av != null) {
            ar.setPre_statistics_amount(NumberKit.getNumberFormatByDouble(av.getAmount()));
        }
        AnalysisUtil.processesult(ar);
        return ar;
    }

    @Override
    public AnalysisResult querySalseCosts(AnalysisSearch analysisSearch, UserPermit userPermit) {
        AnalysisResult ar = new AnalysisResult();
        AnalysisVo av = analysisDao.querySalseCosts(analysisSearch,userPermit);
        if(av != null) {
            ar.setCurr_statistics_amount(NumberKit.getNumberFormatByDouble(av.getAmount()));
        }
        AnalysisSearch preAnalysisSearch = AnalysisUtil.preYearSearch(analysisSearch);
        av = analysisDao.querySalseCosts(preAnalysisSearch,userPermit);
        if(av != null) {
            ar.setPre_statistics_amount(NumberKit.getNumberFormatByDouble(av.getAmount()));
        }
        AnalysisUtil.processesult(ar);
        return ar;
    }

    @Override
    public AnalysisResult queryDepartCosts(AnalysisSearch analysisSearch, UserPermit userPermit) {
        AnalysisResult ar = new AnalysisResult();
        AnalysisVo av = analysisDao.queryDepartCosts(analysisSearch,userPermit);
        if(av != null) {
            ar.setCurr_statistics_amount(NumberKit.getNumberFormatByDouble(av.getAmount()));
        }
        AnalysisSearch preAnalysisSearch = AnalysisUtil.preYearSearch(analysisSearch);
        av = analysisDao.queryDepartCosts(preAnalysisSearch,userPermit);
        if(av != null) {
            ar.setPre_statistics_amount(NumberKit.getNumberFormatByDouble(av.getAmount()));
        }
        AnalysisUtil.processesult(ar);
        return ar;
    }

    @Override
    public AnalysisResult queryOtherStaffCosts(AnalysisSearch analysisSearch, UserPermit userPermit) {
        AnalysisResult ar = new AnalysisResult();
        AnalysisVo av = analysisDao.queryOtherStaffCosts(analysisSearch,userPermit);
        if(av != null) {
            ar.setCurr_statistics_amount(NumberKit.getNumberFormatByDouble(av.getAmount()));
        }
        AnalysisSearch preAnalysisSearch = AnalysisUtil.preYearSearch(analysisSearch);
        av = analysisDao.queryOtherStaffCosts(preAnalysisSearch,userPermit);
        if(av != null) {
            ar.setPre_statistics_amount(NumberKit.getNumberFormatByDouble(av.getAmount()));
        }
        AnalysisUtil.processesult(ar);
        return ar;
    }

    @Override
    public AnalysisResult queryCommonCosts(AnalysisSearch analysisSearch, UserPermit userPermit) {
        AnalysisResult ar = new AnalysisResult();
        AnalysisVo av = analysisDao.queryCommonCosts(analysisSearch,userPermit);
        if(av != null) {
            ar.setCurr_statistics_amount(NumberKit.getNumberFormatByDouble(av.getAmount()));
        }
        AnalysisSearch preAnalysisSearch = AnalysisUtil.preYearSearch(analysisSearch);
        av = analysisDao.queryCommonCosts(preAnalysisSearch,userPermit);
        if(av != null) {
            ar.setPre_statistics_amount(NumberKit.getNumberFormatByDouble(av.getAmount()));
        }
        AnalysisUtil.processesult(ar);
        return ar;
    }

    @Override
    public List<AnalysisResult> queryDepartCostDetail(AnalysisSearch analysisSearch, UserPermit userPermit) {
        return null;
    }
}
