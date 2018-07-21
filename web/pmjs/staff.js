


/**
 * 处理试用期工资
 * @param tryoutSalaryField
 * @param def_basic_salary
 */
function tryoutSalaryProcess(tryoutSalaryField,def_basic_salary){

	var $this = $(tryoutSalaryField);
	var $form = $this.parents("form:first");
	var $base_cardinal = $("#base_cardinal",$form);
	computeTryoutSalary($this.val(),$base_cardinal.val(),def_basic_salary);
}

/**
 * 处理正式工资
 * @param officialSalaryField
 * @param def_basic_salary
 */
function officialSalaryProcess(officialSalaryField,def_basic_salary){
	var $this = $(officialSalaryField);
	var $form = $this.parents("form:first");
	var $base_cardinal = $("#base_cardinal",$form);
	computeOfficialSalary($this.val(),$base_cardinal.val(),def_basic_salary);
	
}

/**
 * 处理 社保种类变化
 * @param radixField
 */
function gradeChangeProcess($form){
	
	var DEFAULT_BASIC_SALARY = $("#DEFAULT_BASIC_SALARY",$form).val();
	var def_basic_salary = 0;
	if(DEFAULT_BASIC_SALARY) def_basic_salary = parseFloat(DEFAULT_BASIC_SALARY)

	var $tryoutSalaryField = $("#tryout_salary",$form);
	var $officialSalaryField = $("#official_salary",$form);
	
	tryoutSalaryProcess($tryoutSalaryField[0],def_basic_salary);
	officialSalaryProcess($officialSalaryField[0],def_basic_salary);
	
}


/**
 * 计算试用期 岗位  基本  绩效工资
 * @param tryoutsalary
 * @param base_cardinal
 * @param def_basic_salary
 */
function computeTryoutSalary(tryout,base_cardinal,def_basic_salary){
	
	var tryout_salary = 0;
	var base_cardinal_salary = 0;
	if(tryout) tryout_salary = parseFloat(tryout);
	if(base_cardinal) base_cardinal_salary = parseFloat(base_cardinal);
	
	var staffCost = new Object();
	
	if(tryout_salary > 0){
		
		if(tryout_salary >= base_cardinal_salary){
			if(base_cardinal_salary >= def_basic_salary){
				staffCost.tryout_basic_salary = (def_basic_salary);
				staffCost.tryout_post_salary = (base_cardinal_salary-def_basic_salary);
				staffCost.tryout_performance_allowances = (tryout_salary - base_cardinal_salary);
			}else {
				staffCost.tryout_basic_salary = (base_cardinal_salary);
				staffCost.tryout_post_salary = (0);
				staffCost.tryout_performance_allowances = (tryout_salary - base_cardinal_salary);
			}
		}else {
			if(tryout_salary >= def_basic_salary){
				staffCost.tryout_basic_salary = (def_basic_salary);
				staffCost.tryout_post_salary = (tryout_salary-def_basic_salary);
				staffCost.tryout_performance_allowances = (0);
			}else {
				staffCost.tryout_basic_salary = (tryout_salary);
				staffCost.tryout_post_salary = (0);
				staffCost.tryout_performance_allowances = (0);
			}
		}	
	}else {
		staffCost.tryout_basic_salary = (0);
		staffCost.tryout_post_salary = (0);
		staffCost.tryout_performance_allowances = (0);
	}
	
	$("#tryout_basic_salary").val(staffCost.tryout_basic_salary);
	$("#tryout_post_salary").val(staffCost.tryout_post_salary);
	$("#tryout_performance_allowances").val(staffCost.tryout_performance_allowances);
	
	
}

/**
 * 计算 岗位  基本  绩效工资
 * @param officialsalary
 * @param base_cardinal
 * @param def_basic_salary
 */
function computeOfficialSalary(official,base_cardinal,def_basic_salary){

	var official_salary = 0;
	var base_cardinal_salary = 0;
	if(official) official_salary = parseFloat(official);
	if(base_cardinal) base_cardinal_salary = parseFloat(base_cardinal);

	var staffCost = new Object();
	
	if(official_salary > 0){
		
		if(official_salary >= base_cardinal_salary){
			if(base_cardinal_salary >= def_basic_salary){
				staffCost.basic_salary = (def_basic_salary);
				staffCost.post_salary = (base_cardinal_salary-def_basic_salary);
				staffCost.performance_allowances = (official_salary - base_cardinal_salary);
			}else {
				staffCost.basic_salary = (base_cardinal_salary);
				staffCost.post_salary = (0);
				staffCost.performance_allowances = (official_salary - base_cardinal_salary);
			}
		}else {
			if(official_salary >= def_basic_salary){
				staffCost.basic_salary = (def_basic_salary);
				staffCost.post_salary = (official_salary-def_basic_salary);
				staffCost.performance_allowances = (0);
			}else {
				staffCost.basic_salary = (official_salary);
				staffCost.post_salary = (0);
				staffCost.performance_allowances = (0);
			}
		}	
	}else {
		staffCost.basic_salary = (0);
		staffCost.post_salary = (0);
		staffCost.performance_allowances = (0);
	}
	

	$("#basic_salary").val(staffCost.basic_salary);
	$("#post_salary").val(staffCost.post_salary);
	$("#performance_allowances").val(staffCost.performance_allowances);
	
}



/**
 * 处理身份证号码
 * @param idCardField
 */
function idCardProce(idCardField){
	var $this = $(idCardField);
	var $form = $this.parents("form:first");
	var val = $this.val();
	var len = val.length;
	
	var $sexField = $("#sex",$form);
	var $birthdayField = $("#birthday",$form);
	if(len != 18) {
		$sexField.val("");
		$birthdayField.val("");
	}else {
		$sexField.val(computeSex(val));
		$birthdayField.val(computeBirthday(val));
	}
	
}

/**
 * 根据身份证号码计算性别
 */
function computeSex(UUserCard){
	
	if (parseInt(UUserCard.substr(16, 1)) % 2 == 1) { 
		return "男"
	}else {
		return "女";
	}
}

/**
 * 计算生日
 */
function computeBirthday(UUserCard){	
	return UUserCard.substring(6, 10) + "-" + UUserCard.substring(10, 12) + "-" + UUserCard.substring(12, 14);
}




