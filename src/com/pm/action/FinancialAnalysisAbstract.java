package com.pm.action;

import com.common.actions.BaseAction;
import com.common.utils.DateKit;
import com.pm.vo.AnalysisResult;
import com.pm.vo.AnalysisResultTable;
import com.pm.vo.AnalysisSearch;

/**
 * 财务分析公共类
 * @author Administrator
 */
public abstract class FinancialAnalysisAbstract extends BaseAction {



    public static final String COUNT_STR = "总计";


    protected void putSearchDate(AnalysisSearch analysisSearch, int month1, int month2) {
        analysisSearch.setMonth1(0);
        analysisSearch.setMonth2(0);
        String strDate1 = String.valueOf(month1)+"01";
        analysisSearch.setDate1(  DateKit.fmtShortYMTStrToDate(strDate1) );
        String strDate2 = String.valueOf(month2)+"01";
        analysisSearch.setDate2( DateKit.getLastDayOfMonth(DateKit.fmtShortYMTStrToDate(strDate2)) );
    }


    protected void processResult(AnalysisResultTable art) {


        if(art.getResult() != null) {


            AnalysisResult countAdr = new AnalysisResult();
            countAdr.setItem_name(COUNT_STR);

            for (AnalysisResult ar : art.getResult()) {
                ar.setAnalysis_type(art.getLabel());

                countAdr.setPre_statistics_amount(countAdr.getPre_statistics_amount() + ar.getPre_statistics_amount());
                countAdr.setCurr_statistics_amount(countAdr.getCurr_statistics_amount() + ar.getCurr_statistics_amount());


                countAdr.setChange_ratio(countAdr.getChange_ratio() + ar.getChange_ratio());
                countAdr.setIncrease_or_decrease(countAdr.getIncrease_or_decrease() + ar.getIncrease_or_decrease());

            }

            art.getResult().add(countAdr);
        }
    }

}
