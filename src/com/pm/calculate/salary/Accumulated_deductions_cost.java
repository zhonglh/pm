package com.pm.calculate.salary;

import com.common.utils.NumberKit;
import com.pm.calculate.ISalaryCalculate;
import com.pm.domain.business.ParamExtend;
import com.pm.domain.business.Params;
import com.pm.domain.business.Salary;

import java.util.Map;

/**
 * 累计五险一金代扣款
 * @author Administrator
 */
public class Accumulated_deductions_cost  extends AbstractSalaryClculate implements ISalaryCalculate {
    @Override
    public void calculate(Salary salary, Map<String, Params> paramMap, Map<String, ParamExtend> paramExtMap) {
        double result = salary.getBefore_accumulated_deductions_cost() + salary.getDeductions_cost();

        result = NumberKit.getNumberFormatByDouble(result);
        salary.setAccumulated_deductions_cost(result);
    }
}
