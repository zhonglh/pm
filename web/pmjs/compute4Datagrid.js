


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
		result = fixed(result);		
		//if(result < 0) result = 0;
		backFill(field,key,result);
	}	
	

	/**
	 * 个人所得税
	 * @param field
	 * @param key
	 * @param val
	 */
	function personal_income_tax(field,key,val){
		
		var result = 0;

		var taxableIncome = fixed(field.taxable_income);
        var paySalary = taxableIncome ;
        //fixed(field.should_salary) - fixed(field.deductions_cost);



        if(paySalary > 80000){
            result = taxableIncome * 0.45 - 15160;
        }else if(paySalary > 55000){
            result = taxableIncome * 0.35 - 7160;
        }else if(paySalary > 35000){
            result = taxableIncome * 0.30 - 4410;
        }else if(paySalary > 25000){
            result = taxableIncome * 0.25 - 2660;
        }else if(paySalary > 12000){
            result = taxableIncome * 0.20 - 1410;
        }else if(paySalary > 3000){
            result = taxableIncome * 0.10 - 210;
        }else if(paySalary > 0){
            result = taxableIncome * 0.03;
        }else {
            result = 0;
        }

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
	

