/**
 * 用于选择新项目 project_echange_staff.jsp
 * @param id
 * @param checkid
 * @param name1
 */
function  toggle(id,checkid,name1) {
	 $("#"+id).toggle();
	 $check1 = $("#"+checkid);
	 $check1.attr("checked",!$check1.attr("checked"));   
	 
	 if($("#"+name1).hasClass("required")) {
		 $("#"+name1).removeClass("required"); // 移除样式
	 }else {
		 $("#"+name1).addClass("required"); // 追加样式  
	 }
}


/**
 * 删除项目人员信息
 * @param json
 */
function dialogAjaxDoneDeleteItem(json){
	
		dialogAjaxDone(json);
	
		var $parent = $(document);
		
		var $box = $parent.find("#"+json.other).find("tbody");
		$("tr",$box).each(function(index){
			if(json.rownum == index) $(this).remove();
		});
		
		initSuffix1($box);
	
	
}

/**
 * 重新初始化下标
 */
function initSuffix1($tbody) {
	$tbody.find('>tr').each(function(i){
		$(':input, a.btnLook,a.btnAttach', this).each(function(){
			var $this = $(this), name = $this.attr('name'), val = $this.val();

			if (name) $this.attr('name', name.replaceSuffix(i));
			
			var lookupGroup = $this.attr('lookupGroup');
			if (lookupGroup) {$this.attr('lookupGroup', lookupGroup.replaceSuffix(i));}
			
			var suffix = $this.attr("suffix");
			if (suffix) {$this.attr('suffix', suffix.replaceSuffix(i));}
			
			if (val && val.indexOf("#index#") >= 0) $this.val(val.replace('#index#',i+1));

			var table_id = $tbody.parents("table:first").attr("id");
			if( name == "index") {
				$this.val(i);
				$this.attr('name', name + "_"+table_id);
			}
			if(name == "index_" + table_id){
				$this.val(i);
			}
			
		});
		

		$('a.delete', this).each(function(){
			var $this = $(this), href = $this.attr('href');
			var rownumIndex = href.indexOf("rownum");
			if(rownumIndex != -1){
				href = href.substring(0,rownumIndex);
				href = href + "rownum="+i;
				$this.attr('href',href);
			}
			
			
		});		
		
	});
}

/**
 * 处理月度计算单 项目类型的变化
 */
function callBackIndividuation(origin_type,project_type){
	
	if(origin_type == null || origin_type != 'MonthlyStatement') return ;
	
	
	
	var html = '';
	if('2' == project_type){
		//人员外派项目 结算单
		html += '<option value="2">人员外派结算单</option>';
		html += '<option value="3">历史结算单</option>';
		html += '<option value="4">其他结算单</option>';
	}else if('1' == project_type){
		html += '<option value="1">项目外包结算单</option>';
		html += '<option value="3">历史结算单</option>';
		html += '<option value="4">其他结算单</option>';	
	}else {
		html += '<option value="3">历史结算单</option>';
		html += '<option value="4">其他结算单</option>';	
	}
	$ref = $("#monthly_statement_first #statement_type");
	var val = $ref.val();
	$ref.html(html);
	$ref.val(val);
	
	changeMonthlyStatementType($ref.get(0));
		
}

/**
 * 处理月度考勤单数据
 * @param field
 */
function changeMonthlyStatementType(field){
	$this = $(field);
	$form = $this.parents("form:first");
	var typeVal = $this.val();
	if(typeVal == '2'){
		$("#monthly_statement_first #thenext").show();
		$("#monthly_statement_first #thesave").hide();			

		$("#monthly_statement_first #jsje").hide();
		if($("#monthly_statement_first #amount").hasClass("required")) 	
			$("#monthly_statement_first #amount").removeClass("required");
		if($("#monthly_statement_first #amount").hasClass("number")) 
			$("#monthly_statement_first #amount").removeClass("number");
		$("#monthly_statement_first #amount_old").val($("#monthly_statement_first #amount").val());
		//$("#monthly_statement_first #amount").val(0);
		
	}else {
		$("#monthly_statement_first #thenext").hide();
		$("#monthly_statement_first #thesave").show();

		$("#monthly_statement_first #jsje").show();
		if(!$("#monthly_statement_first #amount").hasClass("required")) 	
			$("#monthly_statement_first #amount").addClass("required");
		if(!$("#monthly_statement_first #amount").hasClass("number")) 
			$("#monthly_statement_first #amount").addClass("number");
		$("#monthly_statement_first #amount").val($("#monthly_statement_first #amount_old").val());
	}
}


/**
 * 清空数据
 */
function cancelText(){	
	
	var argnum = arguments.length;
	
    for (var i = 0; i  < argnum; i++) {
        var id = arguments[i];
    	
        $("#"+id).val("");
    }	

}

/**
 * 移除readonly属性
 */
function cancelReadonly(){	
	var argnum = arguments.length;	
    for (var i = 0; i  < argnum; i++) {
        var id = arguments[i];    	
        $("#"+id).removeAttr("readonly");
    }
}


/**
 * 自动计算保险档次的社保基数
 * @param insuRadix
 */
function onchangeInsuRadix(insuRadix){
	var radix = 0;
	try{
		radix = parseFloat($(insuRadix).val());
	}catch(e){
		radix = 0;
	}

	if(radix == undefined) radix = 0;
	else if(isNaN(radix)) radix = 0;
	else $("#base_cardinal").val(radix);
}



/**
 * 更改字典类型
 * @param insuRadix
 */
function onchangeDicType(typeField){	
	try{
		$from = $(typeField).parents("form:first");
		$dicTypeName = $("#dic_type_name",$from);
		$dicTypeName.val($(typeField).find("option:selected").text()); 
	}catch(e){
	}

}


/**
 * 计算应出勤
 * days 	总天数
 * this1	当前操作域
 * other	对方域名称
 */
function jsycq(days , this1 , other){
	;
}


/**
 * 计算实际支付信息中的实际成本
 * @param form1
 */
function computeActualCost(form1){
	var pay_amount = form1.pay_amount.value;
    var tax_deduction = form1.tax_deduction.value;
    if(pay_amount == undefined || pay_amount == NaN || pay_amount == null){
        pay_amount = "0";
	}if(tax_deduction == undefined || tax_deduction == NaN || tax_deduction == null){
        tax_deduction = "0";
    }
    var actual_cost = parseFloat(pay_amount) - parseFloat(tax_deduction) ;
    actual_cost = fixed(actual_cost) ;
    form1.actual_cost.value = actual_cost;
}

