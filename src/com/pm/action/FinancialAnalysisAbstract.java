package com.pm.action;

import com.common.actions.BaseAction;
import com.pm.vo.AnalysisDepartResult;
import com.pm.vo.AnalysisResult;
import com.pm.vo.AnalysisResultTable;

/**
 * 财务分析公共类
 * @author Administrator
 */
public abstract class FinancialAnalysisAbstract extends BaseAction {



    public static final String COUNT_STR = "总计";


    protected void processResult(AnalysisResultTable art) {


        if(art.getResult() != null) {


            AnalysisDepartResult countAdr = new AnalysisDepartResult();
            countAdr.setDept_name(COUNT_STR);

            for (AnalysisResult ar : art.getResult()) {
                ar.setAnalysis_type(art.getLabel());

                countAdr.setPre_statistics_amount(countAdr.getPre_statistics_amount() + ar.getPre_statistics_amount());
                countAdr.setCurr_statistics_amount(countAdr.getCurr_statistics_amount() + ar.getCurr_statistics_amount());

            }

            art.getResult().add(countAdr);
        }
    }

}
