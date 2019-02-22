package com.pm.calculate.salary;

import com.common.utils.NumberKit;
import com.pm.calculate.ISalaryCalculate;
import com.pm.domain.business.ParamExtend;
import com.pm.domain.business.Params;
import com.pm.domain.business.Salary;

import java.util.Map;

/**
 * 累计住房贷款利息
 * @author Administrator
 */
public class Accumulated_housing_loans  extends AbstractSalaryClculate implements ISalaryCalculate {
    @Override
    public void calculate(Salary salary, Map<String, Params> paramMap, Map<String, ParamExtend> paramExtMap) {
        double result = salary.getBefore_accumulated_housing_loans() + salary.getHousing_loans();

        result = NumberKit.getNumberFormatByDouble(result);
        salary.setAccumulated_housing_loans(result);
    }
}

