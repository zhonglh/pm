package com.pm.calculate.salary;

import com.pm.calculate.ISalaryCalculate;
import com.pm.domain.business.ParamExtend;
import com.pm.domain.business.Params;
import com.pm.domain.business.Salary;

import java.util.Map;

/**
 * 累计继续教育
 * @author Administrator
 */
public class Accumulated_continuing_education  extends AbstractSalaryClculate implements ISalaryCalculate {
    @Override
    public void calculate(Salary salary, Map<String, Params> paramMap, Map<String, ParamExtend> paramExtMap) {
        double result = salary.getBefore_accumulated_continuing_education() + salary.getContinuing_education();
        salary.setAccumulated_continuing_education(result);
    }
}
