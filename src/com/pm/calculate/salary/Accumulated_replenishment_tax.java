package com.pm.calculate.salary;

import com.pm.calculate.ISalaryCalculate;
import com.pm.domain.business.ParamExtend;
import com.pm.domain.business.Params;
import com.pm.domain.business.Salary;

import java.util.Map;

/**
 * 累计应补（退）税额
 * @author Administrator
 */
public class Accumulated_replenishment_tax extends AbstractSalaryClculate implements ISalaryCalculate {
    @Override
    public void calculate(Salary salary, Map<String, Params> paramMap, Map<String, ParamExtend> paramExtMap) {
        double result = salary.getAccumulated_deductible_taxpaid() - salary.getAccumulated_prepaid_tax();
        salary.setAccumulated_replenishment_tax(result);
    }
}
