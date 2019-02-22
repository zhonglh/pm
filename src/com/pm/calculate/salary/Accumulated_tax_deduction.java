package com.pm.calculate.salary;


import com.common.utils.NumberKit;
import com.pm.calculate.ISalaryCalculate;
import com.pm.domain.business.ParamExtend;
import com.pm.domain.business.Params;
import com.pm.domain.business.Salary;

import java.util.Map;

/**
 * 个税累计减除费用
 * @author Administrator
 */
public class Accumulated_tax_deduction extends AbstractSalaryClculate implements ISalaryCalculate {
    @Override
    public void calculate(Salary salary, Map<String, Params> paramMap, Map<String, ParamExtend> paramExtMap) {
        double result = salary.getBefore_accumulated_tax_deduction() + 5000;

        result = NumberKit.getNumberFormatByDouble(result);
        salary.setAccumulated_tax_deduction(result);
    }
}
