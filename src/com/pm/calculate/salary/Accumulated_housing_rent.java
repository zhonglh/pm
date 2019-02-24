package com.pm.calculate.salary;

import com.common.utils.NumberKit;
import com.pm.calculate.ISalaryCalculate;
import com.pm.domain.business.ParamExtend;
import com.pm.domain.business.Params;
import com.pm.domain.business.AbstractSalary;

import java.util.Map;

/**
 * 累计住房租金
 * @author Administrator
 */
public class Accumulated_housing_rent  extends AbstractSalaryClculate implements ISalaryCalculate {
    @Override
    public void calculate(AbstractSalary salary, Map<String, Params> paramMap, Map<String, ParamExtend> paramExtMap) {

        double result = salary.getBefore_accumulated_housing_rent() ;
        if(salary.getHousing_loans() <=0 ){
            result += salary.getHousing_rent();
        }

        result = NumberKit.getNumberFormatByDouble(result);
        salary.setAccumulated_housing_rent(result);
    }
}
