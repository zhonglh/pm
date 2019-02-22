package com.pm.calculate.salary;

import com.common.utils.NumberKit;
import com.pm.calculate.ISalaryCalculate;
import com.pm.domain.business.ParamExtend;
import com.pm.domain.business.Params;
import com.pm.domain.business.Salary;
import com.pm.util.constant.EnumSalary;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * 计算产假工资
 * @author Administrator
 */
public class Maternity_leave_salary extends AbstractSalaryClculate implements ISalaryCalculate {

    @Override
    public void calculate(Salary salary, Map<String, Params> paramMap, Map<String, ParamExtend> paramExtMap) {
        double result = 0.0;
        double should_work_days = salary.getShould_work_days();
        double maternity_leave_days = salary.getMaternity_leave_days();

        if (should_work_days != 0 && maternity_leave_days != 0) {
            try {
                ParamExtend paramExtend = null;
                if (paramExtMap != null) {
                    paramExtend = paramExtMap.get(EnumSalary.maternity_leave_salary.getId());
                }
                if (paramExtend != null && "1".equals(paramExtend.getType1()) && StringUtils.isNotEmpty(paramExtend.getExpression())) {
                    result = this.getCountSalary(salary) / should_work_days * maternity_leave_days * Double.parseDouble(paramExtend.getProcessor());
                } else if (paramExtend != null && "2".equals(paramExtend.getType1()) && StringUtils.isNotEmpty(paramExtend.getExpression())) {
                    result = Double.parseDouble(paramExtend.getRealVal()) / should_work_days * maternity_leave_days * Double.parseDouble(paramExtend.getProcessor());
                } else {
                    result = this.getCountSalary(salary) / should_work_days * maternity_leave_days;
                }
            } catch (Exception e) {
                result = this.getCountSalary(salary) / should_work_days * maternity_leave_days;
            }
            result = NumberKit.getNumberFormatByDouble(result);
        }

        salary.setMaternity_leave_salary(result);

    }

}