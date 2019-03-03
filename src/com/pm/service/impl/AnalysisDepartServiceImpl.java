package com.pm.service.impl;

import com.pm.dao.IAnalysisDao;
import com.pm.dao.IAnalysisDepartDao;
import com.pm.service.IAnalysisDepartService;
import com.pm.util.AnalysisUtil;
import com.pm.vo.AnalysisDepartVo;
import com.pm.vo.AnalysisResult;
import com.pm.vo.AnalysisSearch;
import com.pm.vo.UserPermit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@Component
public class AnalysisDepartServiceImpl implements IAnalysisDepartService {

    @Autowired
    private IAnalysisDepartDao analysisDepartDao ;



    private List<AnalysisResult> processAnalysis( List<AnalysisDepartVo> currList, List<AnalysisDepartVo> preList) {

        List<AnalysisResult> ars = new ArrayList<AnalysisResult>();

        if( (currList == null || currList.isEmpty()) && (preList == null || preList.isEmpty()) ){
            return ars ;
        }

        if(currList != null && !currList.isEmpty()){
            for(AnalysisDepartVo analysisDepartVo : currList){
                AnalysisResult ar = new AnalysisResult();
                ar.setItem_id(analysisDepartVo.getDept_id());
                ar.setItem_name(analysisDepartVo.getDept_name());
                ar.setCurr_statistics_amount(analysisDepartVo.getAmount());
                ars.add(ar);
            }
        }

        if(preList != null && !preList.isEmpty()){
            for(AnalysisDepartVo analysisDepartVo : preList){

                AnalysisResult ar = AnalysisUtil.getAnalysisResult(ars , analysisDepartVo.getDept_id());
                if(ar == null) {
                    ar = new AnalysisResult();
                    ar.setItem_id(analysisDepartVo.getDept_id());
                    ar.setItem_name(analysisDepartVo.getDept_name());
                    ar.setPre_statistics_amount(analysisDepartVo.getAmount());
                    ars.add(ar);
                }else {
                    ar.setPre_statistics_amount(analysisDepartVo.getAmount());
                }
            }
        }

        if(ars != null && !ars.isEmpty()) {
            for (AnalysisResult ar : ars) {
                AnalysisUtil.processesult(ar);
            }
        }

        return  ars ;
    }



    @Override
    public List<AnalysisResult> queryDepartReceivedPayments(AnalysisSearch analysisSearch, UserPermit userPermit) {


        List<AnalysisDepartVo> currList = analysisDepartDao.queryDepartReceivedPayments(analysisSearch, userPermit);
        AnalysisSearch preAnalysisSearch = AnalysisUtil.preYearSearch(analysisSearch);
        List<AnalysisDepartVo> preList = analysisDepartDao.queryDepartReceivedPayments(preAnalysisSearch, userPermit);

        return processAnalysis( currList, preList);

    }


    @Override
    public List<AnalysisResult> queryDepartAllCosts(AnalysisSearch analysisSearch, UserPermit userPermit) {

        List<AnalysisDepartVo> currList = analysisDepartDao.queryDepartAllCosts(analysisSearch, userPermit);
        AnalysisSearch preAnalysisSearch = AnalysisUtil.preYearSearch(analysisSearch);
        List<AnalysisDepartVo> preList = analysisDepartDao.queryDepartAllCosts(preAnalysisSearch, userPermit);

        return processAnalysis( currList, preList);
    }



    @Override
    public List<AnalysisResult> queryCashFlow(List<AnalysisResult> ars1, List<AnalysisResult> ars2){
        List<AnalysisResult> ars = null;
        if( (ars1 == null || ars1.isEmpty() ) && ( ars2 == null || ars2.isEmpty())){
            return new ArrayList<AnalysisResult>();
        }

        if(ars1 == null || ars1.isEmpty()){
            ars = ars2;
            AnalysisUtil.minus(ars);
        }else {
            ars = ars1;

            if(ars2 != null && !ars2.isEmpty()) {

                for (AnalysisResult ar : ars) {
                    AnalysisResult costResult = AnalysisUtil.getAnalysisResult(ars2, ar.getItem_id());
                    if (costResult != null) {
                        ar.setCurr_statistics_amount(ar.getCurr_statistics_amount() - costResult.getCurr_statistics_amount());
                        ar.setPre_statistics_amount(ar.getPre_statistics_amount() - costResult.getPre_statistics_amount());
                    }
                }


                for (AnalysisResult ar : ars2) {
                    AnalysisResult result = AnalysisUtil.getAnalysisResult(ars, ar.getItem_id());
                    if(result == null){
                        AnalysisResult temp = new AnalysisResult();
                        temp.setItem_id(ar.getItem_id());
                        temp.setItem_name(ar.getItem_name());
                        temp.setPre_statistics_amount(0-ar.getPre_statistics_amount());
                        temp.setCurr_statistics_amount(0-ar.getCurr_statistics_amount());
                        ars.add(temp);
                    }
                }


                for (AnalysisResult ar : ars) {
                    AnalysisUtil.processesult(ar);
                }

            }


        }

        return ars;


    }
}
