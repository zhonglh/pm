package com.pm.util;

import com.common.utils.NumberKit;
import com.pm.vo.AnalysisResult;
import com.pm.vo.AnalysisSearch;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @author Administrator
 */
public class AnalysisUtil {

    /**
     * 计算上一年时间
     * @param analysisSearch
     * @return
     */
    public static AnalysisSearch preYearSearch(AnalysisSearch analysisSearch){
        AnalysisSearch temp = new AnalysisSearch();
        BeanUtils.copyProperties(analysisSearch , temp);
        temp.setMonth1(temp.getMonth1() - 100);
        temp.setMonth2(temp.getMonth2() - 100);
        return temp;
    }

    /**
     * 计算增减额和变动比例
     * @param ar
     */
    public static void processesult(AnalysisResult ar){
        ar.setIncrease_or_decrease(NumberKit.getNumberFormatByDouble(ar.getCurr_statistics_amount() - ar.getPre_statistics_amount()));

        if(ar.getPre_statistics_amount() != 0) {
            ar.setChange_ratio(ar.getIncrease_or_decrease() / ar.getPre_statistics_amount());
        }else {
            ar.setChange_ratio(0);
        }
    }




     /**
     * 所有的数乘以负一
     * 收入为空时， 现金流为成本乘以负一
     * @param ars
     */
    public static void minus(List<AnalysisResult> ars){
        if(ars == null || ars.isEmpty()){
            return ;
        }
        for(AnalysisResult ar : ars){
            ar.setPre_statistics_amount(ar.getPre_statistics_amount() * -1);
            ar.setPre_statistics_amount(ar.getPre_statistics_amount() * -1);
            ar.setIncrease_or_decrease(ar.getIncrease_or_decrease() * -1);
            ar.setChange_ratio(ar.getChange_ratio() * -1);
        }
    }

    /**
     * 根据ID查找对应的
     * @param ars
     * @param item_id
     * @return
     */
    public static AnalysisResult getAnalysisResult(List<AnalysisResult> ars , String item_id){
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
}
