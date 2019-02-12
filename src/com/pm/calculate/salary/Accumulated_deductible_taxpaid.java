package com.pm.calculate.salary;

import com.common.utils.NumberKit;
import com.pm.calculate.ISalaryCalculate;
import com.pm.domain.business.ParamExtend;
import com.pm.domain.business.Params;
import com.pm.domain.business.Salary;

import java.util.Map;

/**
 * 累计应扣缴税额
 * @author Administrator
 */
public class Accumulated_deductible_taxpaid extends AbstractSalaryClculate implements ISalaryCalculate {
    @Override
    public void calculate(Salary salary, Map<String, Params> paramMap, Map<String, ParamExtend> paramExtMap) {
        double result = 0.0;


        double accumulated_taxable_income = salary.getAccumulated_taxable_income();
        



        if(accumulated_taxable_income > 960000){
            result = accumulated_taxable_income * 0.45 - 181920;
        }else if(accumulated_taxable_income > 660000){
            result = accumulated_taxable_income * 0.35 - 85920;
        }else if(accumulated_taxable_income > 420000){
            result = accumulated_taxable_income * 0.30 - 52920;
        }else if(accumulated_taxable_income > 300000){
            result = accumulated_taxable_income * 0.25 - 31920;
        }else if(accumulated_taxable_income > 144000){
            result = accumulated_taxable_income * 0.20 - 16920;
        }else if(accumulated_taxable_income > 36000){
            result = accumulated_taxable_income * 0.10 - 2520;
        }else if(accumulated_taxable_income > 0){
            result = accumulated_taxable_income * 0.03;
        }else {
            result = 0;
        }

        if(result < 0) {result = 0;}
        result = NumberKit.getNumberFormatByDouble(result);

        salary.setAccumulated_deductible_taxpaid(result);
    }
}
