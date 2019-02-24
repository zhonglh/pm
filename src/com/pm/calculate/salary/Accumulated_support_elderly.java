package com.pm.calculate.salary;

import com.common.utils.NumberKit;
import com.pm.calculate.ISalaryCalculate;
import com.pm.domain.business.ParamExtend;
import com.pm.domain.business.Params;
import com.pm.domain.business.AbstractSalary;

import java.util.Map;

/**
 * 累计赡养老人
 * @author Administrator
 */
public class Accumulated_support_elderly extends AbstractSalaryClculate implements ISalaryCalculate {
    @Override
    public void calculate(AbstractSalary salary, Map<String, Params> paramMap, Map<String, ParamExtend> paramExtMap) {
        double result = salary.getBefore_accumulated_support_elderly() + salary.getSupport_elderly();

        result = NumberKit.getNumberFormatByDouble(result);
        salary.setAccumulated_support_elderly(result);
    }
}
