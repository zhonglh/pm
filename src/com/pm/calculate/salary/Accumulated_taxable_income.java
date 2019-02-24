package com.pm.calculate.salary;

import com.common.utils.NumberKit;
import com.pm.calculate.ISalaryCalculate;
import com.pm.domain.business.ParamExtend;
import com.pm.domain.business.Params;
import com.pm.domain.business.AbstractSalary;

import java.util.Map;

/**
 * 累计应纳税所得额
 * @author Administrator
 */
public class Accumulated_taxable_income extends AbstractSalaryClculate implements ISalaryCalculate {
    @Override
    public void calculate(AbstractSalary salary, Map<String, Params> paramMap, Map<String, ParamExtend> paramExtMap) {
        double result = salary.getAccumulated_pretax_income() - salary.getAccumulated_tax_deduction() -
                salary.getAccumulated_children_education() - salary.getAccumulated_continuing_education() -
                salary.getAccumulated_housing_loans()  - salary.getAccumulated_housing_rent() -
                salary.getAccumulated_support_elderly() - salary.getAccumulated_deductions_cost();
        if(result < 0){
            result = 0;
        }
        result = NumberKit.getNumberFormatByDouble(result);
        salary.setAccumulated_taxable_income(result);
    }
}
