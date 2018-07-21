


	function submitSalaryInfo(form, callback) {
		var $form = $(form);
		
		var json1=window.frames["salaryDetails"].accept();
		if("1" == json1 ){
			return false;
		}
	
		$.ajax({
			type: form.method || 'POST',
			url:$form.attr("action"),
            data : {"info":json1},
			dataType:"json",
			cache: false,
			success: callback || DWZ.ajaxDone,
			error: DWZ.ajaxError
		});
		return false;
	}


	
	/**
	 * 填充工资输入框
	 * @param field
	 * @param key
	 * @param result
	 */
	function backFill(field,key,result){	
		if(isNaN( result )) result = 0;
				
		$("#"+key,field.tr).attr("readonly",true);
		if($("#"+key,field.tr).val() != result){
			$("#"+key,field.tr).val(result);
			eval("field."+key+" = result;");
			
			var $id = $("#id",field.tr);
			$id.val(field.tr.index()+1);	
		}
	}


(function($){
	
	$.fn.extend({
		
		
		salaryChangeComputer: function(){			

			return this.each(function(){
				var $table = $(this).css("clear","both"), $tbody = $table.find("tbody");
				var fields=[];

				$tbody.find("tr").each(function(i){
					var $tr = $(this);
					
					var field = tr2Obj($tr);
					
					fields.push(field);
				});

				initFormula(fields);
				initChange(fields);
				
			});
			
		},
		
		
		
		salaryComputer: function(){			

			return this.each(function(){
				var $table = $(this).css("clear","both"), $tbody = $table.find("tbody");
				var fields=[];

				$tbody.find("tr").each(function(i){
					var $tr = $(this);
					
					var field = tr2Obj($tr);
					
					fields.push(field);
				});
				
				initFormula(fields);
				initChange(fields);
				
			});			
			
		}
		
	});

	
	function tr2Obj(tr){
		var $tr = tr;
		var field = {
			basic_salary: parseFloat($("#basic_salary",$tr).val()),
			post_salary: parseFloat($("#post_salary",$tr).val()),
			performance_allowances: parseFloat($("#performance_allowances",$tr).val()),
			
			travel_allowance: parseFloat($("#travel_allowance",$tr).val()),
			tax_bonus: parseFloat($("#tax_bonus",$tr).val()),
			should_work_days: parseFloat($("#should_work_days",$tr).val()),
			work_days: parseFloat($("#work_days",$tr).val()),
			paid_leave_days: parseFloat($("#paid_leave_days",$tr).val()),
			business_trip_days: parseFloat($("#business_trip_days",$tr).val()),
			personal_leave_days: parseFloat($("#personal_leave_days",$tr).val()),
			sick_leave_days: parseFloat($("#sick_leave_days",$tr).val()),
			neglect_work_days: parseFloat($("#neglect_work_days",$tr).val()),
			late_days: parseFloat($("#late_days",$tr).val()),
			weekend_overtime_days: parseFloat($("#weekend_overtime_days",$tr).val()),
			sick_leave_salary: parseFloat($("#sick_leave_salary",$tr).val()),
			neglect_work_salary: parseFloat($("#neglect_work_salary",$tr).val()),
			late_salary: parseFloat($("#late_salary",$tr).val()),
			actual_travel_allowance: parseFloat($("#actual_travel_allowance",$tr).val()),
			actual_computer_allowance: parseFloat($("#actual_computer_allowance",$tr).val()),
			actual_extra_allowance: parseFloat($("#actual_extra_allowance",$tr).val()),
			overtime_allowance: parseFloat($("#overtime_allowance",$tr).val()),
			meals_allowance: parseFloat($("#meals_allowance",$tr).val()),
			actual_tax_bonus: parseFloat($("#actual_tax_bonus",$tr).val()),
			should_salary: parseFloat($("#should_salary",$tr).val()),
			pension_insurance: parseFloat($("#pension_insurance",$tr).val()),
			unemployment_insurance: parseFloat($("#unemployment_insurance",$tr).val()),
			medical_Insurance: parseFloat($("#medical_Insurance",$tr).val()),
			accumulation_fund: parseFloat($("#accumulation_fund",$tr).val()),
			deductions_cost: parseFloat($("#deductions_cost",$tr).val()),
			taxable_income: parseFloat($("#taxable_income",$tr).val()),
			personal_income_tax: parseFloat($("#personal_income_tax",$tr).val()),
			actual_bonus: parseFloat($("#actual_bonus",$tr).val()),
			overdue_tax_salary: parseFloat($("#overdue_tax_salary",$tr).val()),
			actual_salary: parseFloat($("#actual_salary",$tr).val()),
			
			computer_allowance: parseFloat($("#computer_allowance",$tr).val()),
			meal_allowance: parseFloat($("#meal_allowance",$tr).val()),
			tr:$tr 
		};
		return field;
	}
	
	/**
	 * 计算未核单的数据， ，并且将公式所在的输入框设置为只读
	 */
	function initFormula(fields){
		$(fields).each(function(){	
			var ischeck = "0";
			try{
				ischeck = $("#ischeck",this.tr).val();
			}catch(e){}
			
			if("1" == ischeck){
				this.tr.find("input").attr("readonly",true);
			}else {
				handleFormula(this);			
			}
			
		});
	}
	

	/**
	 * 输入框修改时，重新按公式计算
	 */
	function initChange(fields){
		$(fields).each(function(){	
			var field = this;
			field.tr.find("input[type=text]").change(function(){
				thisVal = changeDouble(this);
				eval("field."+this.id+"="+thisVal+";");
				handleFormula(field);
			});
		});
		
	}
	
	/**
	 * 处理公式
	 */
	function handleFormula(field){
		
		$field = $(field);
		$.each(field,function(k,v){
			try{
				var execCode = k+"($field.get(0),k,v);";							
				eval(execCode);
			}catch(e){
										
			}
		});
	}
	
	
	/**
	 * 取的总工资数，  基本工资+岗位工资+绩效津贴
	 */
	function getCountSalary(field){
		return field.basic_salary + field.post_salary + field.performance_allowances;
	}
	
	
	
	
	/**
	 * 病假工资
	 * @param field
	 * @param key
	 * @param val
	 */
	function sick_leave_salary(field,key,val){
		var result = 0.0;
		if(field.should_work_days != 0){
			try{
				if(exp_sick_leave_salary!=undefined){
					result = eval(exp_sick_leave_salary);
				}else {
					result = getCountSalary(field)/field.should_work_days*field.sick_leave_days*0.5;
				}
			}catch(e){
				result = getCountSalary(field)/field.should_work_days*field.sick_leave_days*0.5;
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
		if(field.should_work_days != 0){
			result = getCountSalary(field)/field.should_work_days*field.neglect_work_days*2;
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
		var result = field.late_days * 50;
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
		if(field.should_work_days != 0){
			result = field.travel_allowance/field.should_work_days*field.business_trip_days;
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
		if(field.should_work_days != 0){
			result = field.computer_allowance/field.should_work_days*field.work_days;
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
		var result = 100 * field.weekend_overtime_days;
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
		var result = field.meal_allowance * (field.work_days); // - field.business_trip_days
		result = fixed(result);		
		//backFill(field,key,result);
		
		if(isNaN( result )) result = 0;
		$("#"+key,field.tr).val(result);		
		$("#"+key,field.tr).attr("readonly",true);

		eval("field."+key+" = result;");
	}
	

	/**
	 * 实发奖金(税前)
	 * @param field
	 * @param key
	 * @param val
	 */
	function actual_tax_bonus(field,key,val){
		var result = 0.0;
		if(field.should_work_days != 0){
			
			result = field.tax_bonus/field.should_work_days;
			if(result != 0){
				result = result * (field.work_days+field.paid_leave_days) ;
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
		if(field.should_work_days != 0){
			result = getCountSalary(field)/field.should_work_days*(field.work_days+field.paid_leave_days);
		}
		result = result + field.sick_leave_salary - field.neglect_work_salary - field.late_salary;
		result = result + field.actual_travel_allowance + field.actual_computer_allowance;
		result = result + field.actual_extra_allowance + field.overtime_allowance;
		result = result + field.meals_allowance + field.actual_tax_bonus;
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
		var result = field.pension_insurance + field.unemployment_insurance;
		result = result + field.medical_Insurance + field.accumulation_fund;
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
		var result = field.should_salary - field.deductions_cost - 3500;
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
		
		var paySalary = field.should_salary - field.deductions_cost;
		var taxableIncome = field.taxable_income;
		
		if(paySalary > 83500){
			result = taxableIncome * 0.45 - 13505;
		}else if(paySalary > 58500){
			result = taxableIncome * 0.35 - 5505;
		}else if(paySalary > 38500){
			result = taxableIncome * 0.30 - 2755;
		}else if(paySalary > 12500){
			result = taxableIncome * 0.25 - 1005;
		}else if(paySalary > 8000){
			result = taxableIncome * 0.20 - 555;
		}else if(paySalary > 5000){
			result = taxableIncome * 0.10 - 105;
		}else if(paySalary > 3500){
			result = taxableIncome * 0.03;
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
		var result = field.should_salary - field.deductions_cost - field.personal_income_tax;
		result = result + field.actual_bonus + field.overdue_tax_salary ;
		result = fixed(result);
		backFill(field,key,result);
	}		
	
})(jQuery);

