package com.pm.calculate.salary;

import com.pm.calculate.ISalaryCalculate;
import com.pm.domain.business.ParamExtend;
import com.pm.domain.business.Params;
import com.pm.domain.business.Salary;

import java.util.Map;

/**
 * 累计住房租金
 * @author Administrator
 */
public class Accumulated_housing_rent  extends AbstractSalaryClculate implements ISalaryCalculate {
    @Override
    public void calculate(Salary salary, Map<String, Params> paramMap, Map<String, ParamExtend> paramExtMap) {
        double result = salary.getBefore_accumulated_housing_rent() + salary.getHousing_rent();
        salary.setAccumulated_housing_rent(result);
    }
}
