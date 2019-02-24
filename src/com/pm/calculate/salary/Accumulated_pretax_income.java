package com.pm.calculate.salary;


import com.common.utils.NumberKit;
import com.pm.calculate.ISalaryCalculate;
import com.pm.domain.business.ParamExtend;
import com.pm.domain.business.Params;
import com.pm.domain.business.AbstractSalary;

import java.util.Map;

/**
 * 累计税前收入额
 * @author Administrator
 */
public class Accumulated_pretax_income extends AbstractSalaryClculate implements ISalaryCalculate {
    @Override
    public void calculate(AbstractSalary salary, Map<String, Params> paramMap, Map<String, ParamExtend> paramExtMap) {
        double result = salary.getBefore_accumulated_pretax_income() + salary.getShould_salary();

        result = NumberKit.getNumberFormatByDouble(result);
        salary.setAccumulated_pretax_income(result);
    }
}
