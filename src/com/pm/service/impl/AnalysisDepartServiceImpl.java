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


    private AnalysisResult getAnalysisResult(List<AnalysisResult> ars , String item_id){
        if(ars == null || ars.isEmpty()) {
            return null;
        }

        for(AnalysisResult ar : ars){
            if(item_id ==  ar.getItem_id() ){
                return ar;
            }else if(item_id != null && item_id.equals(ar.getItem_id())){
                return ar;
            }
        }

        return null;

    }


    private List<AnalysisResult> processAnalysis( List<AnalysisDepartVo> currList, List<AnalysisDepartVo> preList) {

        List<AnalysisResult> ars = new ArrayList<AnalysisResult>();

        if( (currList == null || currList.isEmpty()) && (preList == null || preList.isEmpty()) ){
            return ars ;
        }

        if(currList == null || currList.isEmpty()){
            for(AnalysisDepartVo analysisDepartVo : currList){
                AnalysisResult ar = new AnalysisResult();
                ar.setItem_id(analysisDepartVo.getDept_id());
                ar.setItem_name(analysisDepartVo.getDept_name());
                ar.setCurr_statistics_amount(analysisDepartVo.getAmount());
                ars.add(ar);
            }
        }

        if(preList == null || preList.isEmpty()){
            for(AnalysisDepartVo analysisDepartVo : preList){

                AnalysisResult ar = getAnalysisResult(ars , analysisDepartVo.getDept_id());
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

        for(AnalysisResult ar : ars){
            AnalysisUtil.processesult(ar);
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
}
