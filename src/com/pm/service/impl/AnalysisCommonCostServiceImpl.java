package com.pm.service.impl;

import com.pm.dao.IAnalysisCommonCostDao;
import com.pm.service.IAnalysisCommonCostService;
import com.pm.util.AnalysisUtil;
import com.pm.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@Component
public class AnalysisCommonCostServiceImpl implements IAnalysisCommonCostService {


    @Autowired
    private IAnalysisCommonCostDao analysisCommonCostDao ;

    @Override
    public List<AnalysisResult> queryCommonCostCosts(AnalysisSearch analysisSearch, UserPermit userPermit) {

        

        List<AnalysisCommonCostVo> currList = analysisCommonCostDao.queryCommonCostAllCosts(analysisSearch, userPermit);
        AnalysisSearch preAnalysisSearch = AnalysisUtil.preYearSearch(analysisSearch);
        List<AnalysisCommonCostVo> preList = analysisCommonCostDao.queryCommonCostAllCosts(preAnalysisSearch, userPermit);

        return processAnalysis( currList, preList);
    }



    private List<AnalysisResult> processAnalysis(List<AnalysisCommonCostVo> currList, List<AnalysisCommonCostVo> preList) {

        List<AnalysisResult> ars = new ArrayList<AnalysisResult>();

        if( (currList == null || currList.isEmpty()) && (preList == null || preList.isEmpty()) ){
            return ars ;
        }

        if(currList != null && !currList.isEmpty()){
            for(AnalysisCommonCostVo vo : currList){
                AnalysisResult ar = new AnalysisResult();
                ar.setItem_id(vo.getItem_id());
                ar.setItem_name(vo.getItem_name());
                ar.setCurr_statistics_amount(vo.getAmount());
                ars.add(ar);
            }
        }

        if(preList != null && !preList.isEmpty()){
            for(AnalysisCommonCostVo vo : preList){

                AnalysisResult ar = AnalysisUtil.getAnalysisResult(ars , vo.getItem_id());
                if(ar == null) {
                    ar = new AnalysisResult();
                    ar.setItem_id(vo.getItem_id());
                    ar.setItem_name(vo.getItem_name());
                    ar.setPre_statistics_amount(vo.getAmount());
                    ars.add(ar);
                }else {
                    ar.setPre_statistics_amount(vo.getAmount());
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

}
