


	/**
	 * 填充工资输入框
	 * @param field
	 * @param key
	 * @param result
	 */
	function backFill(field,key,result){	
		if(isNaN( result )) result = 0;
		var sourceResult = 	fixed(eval("field."+key));
		if(sourceResult != result){
			eval("field."+key+" = result;");
			field.id = "1";
		}
	}


	
	function loadSuccess(date){
		var fields = $('#dg').datagrid('getRows');
		initFormula(fields);
		
		//var changes = $('#dg').datagrid('getChanges');
		//initChange(fields);
	}
	
	function refreshRow(index){
		$('#dg').datagrid('refreshRow',index);
		//$('#dg').datagrid('acceptChanges',index);
		
	}
	
	function isCheck(verity_status){
		var ischeck = false;
		try{
			if(verity_status == "1") ischeck = true;
		}catch(e){
			
		}
		return ischeck;
	}

	
	
	/**
	 * 计算未核单的数据
	 */
	function initFormula(fields){
		$(fields).each(function(index){	
			
			var field = this;
			
			if(!isCheck(field.verity_status)){
				handleFormula(field,index);	
			}
			
		});
	}
		
	/**
	 * 处理公式
	 */
	function handleFormula(field,index){
		
		sick_leave_salary(field,'sick_leave_salary',field.sick_leave_salary);
		neglect_work_salary(field,'neglect_work_salary',field.neglect_work_salary);
		late_salary(field,'late_salary',field.late_salary);
		actual_travel_allowance(field,'actual_travel_allowance',field.actual_travel_allowance);
		actual_computer_allowance(field,'actual_computer_allowance',field.actual_computer_allowance);
		overtime_allowance(field,'overtime_allowance',field.overtime_allowance);
		meals_allowance(field,'meals_allowance',field.meals_allowance);
		actual_tax_bonus(field,'actual_tax_bonus',field.actual_tax_bonus);
		should_salary(field,'should_salary',field.should_salary);
		deductions_cost(field,'deductions_cost',field.deductions_cost);
		taxable_income(field,'taxable_income',field.taxable_income);


        accumulated_pretax_income(field,'accumulated_pretax_income',field.accumulated_pretax_income);
        accumulated_tax_deduction(field,'accumulated_tax_deduction',field.accumulated_tax_deduction);
        accumulated_children_education(field,'accumulated_children_education',field.accumulated_children_education);
        accumulated_housing_loans(field,'accumulated_housing_loans',field.accumulated_housing_loans);
        accumulated_housing_rent(field,'accumulated_housing_rent',field.accumulated_housing_rent);
        accumulated_support_elderly(field,'accumulated_support_elderly',field.accumulated_support_elderly);
        accumulated_continuing_education(field,'accumulated_continuing_education',field.accumulated_continuing_education);
        accumulated_deductions_cost(field,'accumulated_deductions_cost',field.accumulated_deductions_cost);
        accumulated_taxable_income(field,'accumulated_taxable_income',field.accumulated_taxable_income);
        accumulated_deductible_taxpaid(field,'accumulated_deductible_taxpaid',field.accumulated_deductible_taxpaid);
        accumulated_prepaid_tax(field,'accumulated_prepaid_tax',field.accumulated_prepaid_tax);
        accumulated_replenishment_tax(field,'accumulated_replenishment_tax',field.accumulated_replenishment_tax);

        personal_income_tax(field,'personal_income_tax',field.personal_income_tax);
		actual_salary(field,'actual_salary',field.actual_salary);
		refreshRow(index);
		
		
		/**
		$field = $(field);
		$.each(field,function(k,v){
			try{
				var execCode = k+"($field.get(0),k,v);";							
				eval(execCode);
			}catch(e){
										
			}
		});
		*/
	}
	
	
	/**
	 * 取的总工资数，  基本工资+岗位工资+绩效津贴
	 */
	function getCountSalary(field){
		return fixed(field.basic_salary) + fixed(field.post_salary) + fixed(field.performance_allowances);
	}
	
	
	
	
	/**
	 * 病假工资
	 * @param field
	 * @param key
	 * @param val
	 */
	function sick_leave_salary(field,key,val){
		var result = 0.0;
		if(fixed(field.should_work_days) != 0){
			try{
				if(exp_sick_leave_salary!=undefined){
					result = eval(exp_sick_leave_salary);
				}else {
					result = getCountSalary(field)/fixed(field.should_work_days)*fixed(field.sick_leave_days)*0.5;
				}
			}catch(e){
				result = getCountSalary(field)/fixed(field.should_work_days)*fixed(field.sick_leave_days)*0.5;
			}
			
			
			result = fixed(result);			
		}
		backFill(field,key,result);

	}
	

	/**
	 * 旷工工资
	 * @param field
	 * @param key
	 * @param val
	 */
	function neglect_work_salary(field,key,val){
		var result = 0.0;
		if(fixed(field.should_work_days) != 0){
			result = getCountSalary(field)/fixed(field.should_work_days)*fixed(field.neglect_work_days)*2;
			result = fixed(result);					
		}
		backFill(field,key,result);		
	}		
	
	/**
	 * 迟到工资
	 * @param field
	 * @param key
	 * @param val
	 */
	function late_salary(field,key,val){
		var result = fixed(field.late_days) * 50;
		result = fixed(result);				
		backFill(field,key,result);		
	}
	
	/**
	 * 出差补助
	 * @param field
	 * @param key
	 * @param val
	 */
	function actual_travel_allowance(field,key,val){
		var result = 0.0;
		if(fixed(field.should_work_days) != 0){
			result = fixed(field.travel_allowance)/fixed(field.should_work_days)*fixed(field.business_trip_days);
			result = fixed(result);					
		}
		backFill(field,key,result);		
	}		
	
	/**
	 * 电脑补助
	 * @param field
	 * @param key
	 * @param val
	 */
	function actual_computer_allowance(field,key,val){
		var result = 0.0;
		if(fixed(field.should_work_days) != 0){
			result = fixed(field.computer_allowance)/fixed(field.should_work_days)*fixed(field.work_days);
			result = fixed(result);					
		}
		backFill(field,key,result);		
	}		
	
	/**
	 * 加班补助
	 * @param field
	 * @param key
	 * @param val
	 */
	function overtime_allowance(field,key,val){
		var result = 100 * fixed(field.weekend_overtime_days);
		result = fixed(result);		
		backFill(field,key,result);		
	}
	
	/**
	 * 餐补
	 * @param field
	 * @param key
	 * @param val
	 */
	function meals_allowance(field,key,val){
		var result = fixed(field.meal_allowance) * fixed(field.work_days); // - field.business_trip_days
		result = fixed(result);		
		backFill(field,key,result);
		
	}
	

	/**
	 * 实发奖金(税金)
	 * @param field
	 * @param key
	 * @param val
	 */
	function actual_tax_bonus(field,key,val){
		var result = 0.0;
		if(fixed(field.should_work_days) != 0){
			
			result = fixed(field.tax_bonus)/fixed(field.should_work_days);
			if(result != 0){
				result = result * (fixed(field.work_days)+fixed(field.paid_leave_days)) ;
			}
			result = fixed(result);					
		}
		backFill(field,key,result);		
	}

	
	/**
	 * 应发工资
	 * @param field
	 * @param key
	 * @param val
	 */
	function should_salary(field,key,val){
		var result = 0.0;
		if(fixed(field.should_work_days) != 0){
			result = getCountSalary(field)/fixed(field.should_work_days)*(fixed(field.work_days)+fixed(field.paid_leave_days));
		}
		result = result + fixed(field.sick_leave_salary) - fixed(field.neglect_work_salary) - fixed(field.late_salary);
		result = result + fixed(field.actual_travel_allowance) + fixed(field.actual_computer_allowance);
		result = result + fixed(field.actual_extra_allowance) + fixed(field.overtime_allowance);
		result = result + fixed(field.meals_allowance) + fixed(field.actual_tax_bonus);
		result = fixed(result);					
	
		backFill(field,key,result);		
	}
	

	/**
	 * 准许扣除的费用
	 * @param field
	 * @param key
	 * @param val
	 */
	function deductions_cost(field,key,val){

		//三险一金
		var result = fixed(field.pension_insurance) + fixed(field.unemployment_insurance);
		result = result + fixed(field.medical_Insurance) + fixed(field.accumulation_fund);

		result = fixed(result);		
		backFill(field,key,result);	
	}
	

	/**
	 * 应纳税所得额
	 * @param field
	 * @param key
	 * @param val
	 */
	function taxable_income(field,key,val){
		var result = fixed(field.should_salary) - fixed(field.deductions_cost) - 5000;


        //专项附加扣除
        result -= field.children_education;
        result -= field.continuing_education;
        result -= field.support_elderly;
        if(field.housing_loans > 0){
            result -= field.housing_loans;
        }else {
            result -= field.housing_rent;
        }

		result = fixed(result);		
		//if(result < 0) result = 0;
		backFill(field,key,result);
	}




    /**
     * 累计税前收入额
     * @param field
     * @param key
     * @param val
     */
    function accumulated_pretax_income(field,key,val){
        var result = 0.0;
        result = field.before_accumulated_pretax_income + field.should_salary;
        result = fixed(result);
        //if(result < 0) result = 0;
        backFill(field,key,result);
    }


    /**
     * 个税累计减除费用
     * @param field
     * @param key
     * @param val
     */
    function accumulated_tax_deduction(field,key,val){
        var result = 0.0;
        result = field.before_accumulated_tax_deduction + 5000;
        result = fixed(result);
        //if(result < 0) result = 0;
        backFill(field,key,result);
    }



    /**
     * 累计子女教育
     * @param field
     * @param key
     * @param val
     */
    function accumulated_children_education(field,key,val){
        var result = 0.0;
        result = field.before_accumulated_children_education + field.children_education;
        result = fixed(result);
        //if(result < 0) result = 0;
        backFill(field,key,result);
    }



    /**
     * 累计住房贷款利息
     * @param field
     * @param key
     * @param val
     */
    function accumulated_housing_loans(field,key,val){
        var result = 0.0;
        result = field.before_accumulated_housing_loans + field.housing_loans;
        result = fixed(result);
        //if(result < 0) result = 0;
        backFill(field,key,result);
    }

    /**
     * 累计住房租金
     * @param field
     * @param key
     * @param val
     */
    function accumulated_housing_rent(field,key,val){
        var result = 0.0;
        if(field.housing_loans > 0){
            result = field.before_accumulated_housing_rent ;
        }else {
            result = field.before_accumulated_housing_rent + field.housing_rent;
        }
        result = fixed(result);
        //if(result < 0) result = 0;
        backFill(field,key,result);
    }

    /**
     * 累计赡养老人
     * @param field
     * @param key
     * @param val
     */
    function accumulated_support_elderly(field,key,val){
        var result = 0.0;
        result = field.before_accumulated_support_elderly + field.support_elderly;
        result = fixed(result);
        //if(result < 0) result = 0;
        backFill(field,key,result);
    }

    /**
     * 累计继续教育
     * @param field
     * @param key
     * @param val
     */
    function accumulated_continuing_education(field,key,val){
        var result = 0.0;
        result = field.before_accumulated_continuing_education + field.continuing_education;
        result = fixed(result);
        //if(result < 0) result = 0;
        backFill(field,key,result);
    }


    /**
     * 累计五险一金代扣款
     * @param field
     * @param key
     * @param val
     */
    function accumulated_deductions_cost(field,key,val){
        var result = 0.0;
        result = field.before_accumulated_deductions_cost + field.deductions_cost;
        result = fixed(result);
        //if(result < 0) result = 0;
        backFill(field,key,result);
    }


    /**
     * 累计应纳税所得额
     * @param field
     * @param key
     * @param val
     */
    function accumulated_taxable_income(field,key,val){
        var result = 0.0;
        result = field.accumulated_pretax_income - field.accumulated_tax_deduction -
            field.accumulated_children_education - field.accumulated_continuing_education -
            field.accumulated_housing_loans - field.accumulated_housing_rent -
            field.accumulated_support_elderly - field.accumulated_deductions_cost ;

        result = fixed(result);
        if(result < 0) result = 0;
        backFill(field,key,result);
    }


    /**
     * 累计应扣缴税额
     * @param field
     * @param key
     * @param val
     */
    function accumulated_deductible_taxpaid(field,key,val){
        var result = 0.0;


        var accumulated_taxable_income = field.accumulated_taxable_income;
        var paySalary = accumulated_taxable_income ;


        if(paySalary > 960000){
            result = accumulated_taxable_income * 0.45 - 181920;
        }else if(paySalary > 660000){
            result = accumulated_taxable_income * 0.35 - 85920;
        }else if(paySalary > 420000){
            result = accumulated_taxable_income * 0.30 - 52920;
        }else if(paySalary > 300000){
            result = accumulated_taxable_income * 0.25 - 31920;
        }else if(paySalary > 144000){
            result = accumulated_taxable_income * 0.20 - 16920;
        }else if(paySalary > 36000){
            result = accumulated_taxable_income * 0.10 - 2520;
        }else if(paySalary > 0){
            result = accumulated_taxable_income * 0.03;
        }else {
            result = 0;
        }

        result = fixed(result);
        if(result < 0) result = 0;
        backFill(field,key,result);
    }


    /**
     * 累计已预缴税额
     * @param field
     * @param key
     * @param val
     */
    function accumulated_prepaid_tax(field,key,val){
        //不用计算，仅仅是要只读
        var result = val;
        backFill(field,key,result);
    }


    /**
     * 累计应补（退）税额
     * @param field
     * @param key
     * @param val
     */
    function accumulated_replenishment_tax(field,key,val){
        var result = field.accumulated_deductible_taxpaid - field.accumulated_prepaid_tax;
        result = fixed(result);
        if(result < 0) result = 0;
        backFill(field,key,result);
    }





    /**
     * 当月个人所得税
     * @param field
     * @param key
     * @param val
     */
    function personal_income_tax(field,key,val){
        var result = field.accumulated_replenishment_tax;

        result = fixed(result);
        if(result < 0) result = 0;
        backFill(field,key,result);

    }


    /**
	 * 实发工资
	 * @param field
	 * @param key
	 * @param val
	 */
	function actual_salary(field,key,val){
		var result = fixed(field.should_salary) - fixed(field.deductions_cost) - fixed(field.personal_income_tax);
		result = result + fixed(field.actual_bonus) + fixed(field.overdue_tax_salary) ;
		result = fixed(result);		
		backFill(field,key,result);
	}		
	

