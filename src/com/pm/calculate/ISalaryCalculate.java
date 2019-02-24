package com.pm.calculate;

import java.util.Map;

import com.pm.domain.business.ParamExtend;
import com.pm.domain.business.Params;
import com.pm.domain.business.AbstractSalary;

public interface ISalaryCalculate {

	public void calculate(AbstractSalary salary ,Map<String, Params> paramMap, Map<String,ParamExtend> paramExtMap);

}
