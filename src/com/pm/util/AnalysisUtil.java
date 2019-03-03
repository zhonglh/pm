package com.pm.util;

import com.common.utils.NumberKit;
import com.pm.vo.AnalysisResult;
import com.pm.vo.AnalysisSearch;
import org.springframework.beans.BeanUtils;

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
        }
    }
}
