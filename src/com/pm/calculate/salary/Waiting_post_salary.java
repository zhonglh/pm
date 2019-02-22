package com.pm.calculate.salary;

import com.common.utils.NumberKit;
import com.pm.calculate.ISalaryCalculate;
import com.pm.domain.business.ParamExtend;
import com.pm.domain.business.Params;
import com.pm.domain.business.Salary;
import org.apache.commons.lang.StringUtils;

import java.util.Map;


/**
 * 待岗工资计算
 * @author Administrator
 */
public class Waiting_post_salary extends AbstractSalaryClculate implements ISalaryCalculate {

    @Override
    public void calculate(Salary salary, Map<String, Params> paramMap, Map<String,ParamExtend> paramExtMap) {
        double result = 0.0;
        double should_work_days = salary.getShould_work_days();
        double waiting_post_days = salary.getWaiting_post_days();

        if(should_work_days != 0 && waiting_post_days != 0){
            try{
                ParamExtend paramExtend = null;
                if(paramExtMap != null) {
                    paramExtend = paramExtMap.get("waiting_post_salary");
                }
                if(paramExtend != null && "1".equals(paramExtend.getType1()) && StringUtils.isNotEmpty(paramExtend.getExpression()) ){
                    result = this.getCountSalary(salary)/should_work_days*waiting_post_days*Double.parseDouble(paramExtend.getProcessor());
                }else if(paramExtend != null && "2".equals(paramExtend.getType1()) && StringUtils.isNotEmpty(paramExtend.getExpression()) ){
                    result = Double.parseDouble(paramExtend.getRealVal())/should_work_days*waiting_post_days*Double.parseDouble(paramExtend.getProcessor());
                }else {
                    result = this.getCountSalary(salary)/should_work_days*waiting_post_days;
                }
            }catch(Exception e){
                result = this.getCountSalary(salary)/should_work_days*waiting_post_days;
            }
            result = NumberKit.getNumberFormatByDouble(result);
        }

        salary.setWaiting_post_salary(result);

    }



}