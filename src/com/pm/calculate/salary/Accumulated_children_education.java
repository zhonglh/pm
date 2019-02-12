package com.pm.calculate.salary;

import com.pm.calculate.ISalaryCalculate;
import com.pm.domain.business.ParamExtend;
import com.pm.domain.business.Params;
import com.pm.domain.business.Salary;

import java.util.Map;

/**
 * 累计子女教育
 * @author Administrator
 */
public class Accumulated_children_education extends AbstractSalaryClculate implements ISalaryCalculate {
    @Override
    public void calculate(Salary salary, Map<String, Params> paramMap, Map<String, ParamExtend> paramExtMap) {
        double result = salary.getBefore_accumulated_children_education() + salary.getChildren_education();
        salary.setAccumulated_children_education(result);
    }
}
