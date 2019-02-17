
/**
 * 日期处理
 */
function dateProcess($p){
	
	if ($.fn.datepicker){
		$('input.date', $p).each(function(){
			
			var $this = $(this);
			var opts = {};
			if ($this.attr("format")) opts.pattern = $this.attr("format");
			if ($this.attr("yearstart")) opts.yearstart = $this.attr("yearstart");
			if ($this.attr("yearend")) opts.yearend = $this.attr("yearend");
			if ($this.attr("viewMode")) opts.viewMode = $this.attr("viewMode");
			if ($this.attr("minViewMode")) opts.minViewMode = $this.attr("minViewMode");
			//$this.datepicker(opts);
			$this.click(function(){WdatePicker({dateFmt:opts.pattern})});
		});
	}
	
}

/**
 * 加薪记录
 * @param this1
 * @param id
 */
function edit_raiserecords(this1,id){

	var $bj = $(this1);
	
	if($bj.attr("disabled")) return ;
	
	var $tr = $bj.parents("tr:first");
	var trNum = $tr[0].rowIndex -1 ;	

	$bj.attr("disabled",true); 
	
	
	var $raise_time = $tr.children('td').eq(0);	
	$raise_time.html(
			'<input type="text" readonly="readonly" name="items['+trNum+'].raise_time_1" value="'+$raise_time.text()+'" class="date required textInput readonly valid" format="yyyy-MM-dd" size="12">'
	);	
	
	var $amount = $tr.children('td').eq(1);	
	$amount.html(
			'<input type="text"  name="items['+trNum+'].amount_1" value="'+$amount.text()+'" class="number required " maxlength="10" size="10">'
	);
	
	var $description = $tr.children('td').eq(2);	
	$description.html(
			'<input type="text" name="items['+trNum+'].description_1" value="'+$description.text()+'" class="text textInput required" maxlength="150" size="62">' +
			'<input type="hidden" name="index_raise_record_table" value="'+trNum+'">' +
			'<input type="hidden" name="items['+trNum+'].id_1" value="'+id+'">'
	);

	dateProcess($tr);
	
}


/**
 * 奖惩记录
 * @param this1
 * @param id
 */
function edit_rewardpenaltys(this1,id){

	var $bj = $(this1);
	
	if($bj.attr("disabled")) return ;
	
	var $tr = $bj.parents("tr:first");
	var trNum = $tr[0].rowIndex -1 ;	

	$bj.attr("disabled",true); 
	
	
	var $rp_time = $tr.children('td').eq(0);	
	$rp_time.html(
			'<input type="text" readonly="readonly" name="items['+trNum+'].rp_time_2" value="'+$rp_time.text()+'" class="date required textInput readonly valid" format="yyyy-MM-dd" size="12">'
	);	
	
	var $amount = $tr.children('td').eq(1);	
	$amount.html(
			'<input type="text"  name="items['+trNum+'].amount_2" value="'+$amount.text()+'" class="number required " maxlength="10" size="10">'
	);
	
	var $description = $tr.children('td').eq(2);	
	$description.html(
			'<input type="text" name="items['+trNum+'].description_2" value="'+$description.text()+'" class="text textInput required" maxlength="150" size="62">' +
			'<input type="hidden" name="index_reward_penalty_table" value="'+trNum+'">' +
			'<input type="hidden" name="items['+trNum+'].id_2" value="'+id+'">'
	);

	dateProcess($tr);
	
}




/**
 * 绩效考核
 * @param this1
 * @param id
 */
function edit_assessments(this1,id){

	var $bj = $(this1);
	
	if($bj.attr("disabled")) return ;
	
	var $tr = $bj.parents("tr:first");
	var trNum = $tr[0].rowIndex -1 ;	

	$bj.attr("disabled",true); 
	
	
	var $assessment_time = $tr.children('td').eq(0);	
	$assessment_time.html(
			'<input type="text" readonly="readonly" name="items['+trNum+'].assessment_time_3" value="'+$assessment_time.text()+'" class="date required textInput readonly valid" format="yyyy-MM-dd" size="12">'
	);	
	
	var $attendance_rate = $tr.children('td').eq(1);	
	$attendance_rate.html(
			'<input type="text"  name="items['+trNum+'].attendance_rate_3" value="'+$attendance_rate.text()+'" class="number required " maxlength="10" size="10">'
	);

	var $score = $tr.children('td').eq(2);	
	$score.html(
			'<input type="text"  name="items['+trNum+'].score_3" value="'+$score.text()+'" class="digits required " maxlength="4" size="10">'
	);
	
	var $description = $tr.children('td').eq(3);	
	$description.html(
			'<input type="text" name="items['+trNum+'].description_3" value="'+$description.text()+'" class="text textInput required" maxlength="150" size="38">' +
			'<input type="hidden" name="index_assessment_table" value="'+trNum+'">' +
			'<input type="hidden" name="items['+trNum+'].id_3" value="'+id+'">'
	);

	dateProcess($tr);
	
}


/**
 * 晋升记录
 * @param this1
 * @param id
 */
function edit_positions(this1,id){

	var $bj = $(this1);
	
	if($bj.attr("disabled")) return ;
	
	var $tr = $bj.parents("tr:first");
	var trNum = $tr[0].rowIndex -1 ;	

	$bj.attr("disabled",true); 
	
	
	var $positions_time = $tr.children('td').eq(0);	
	$positions_time.html(
			'<input type="text" readonly="readonly" name="items['+trNum+'].positions_time_4" value="'+$positions_time.text()+'" class="date required textInput readonly valid" format="yyyy-MM-dd" size="12">'
	);	
	
	var $level = $tr.children('td').eq(1);	
	$level.html(
			'<input type="text"  name="items['+trNum+'].level_4" value="'+$level.text()+'" class="text required " maxlength="10" size="10">'
	);
	
	var $description = $tr.children('td').eq(2);	
	$description.html(
			'<input type="text" name="items['+trNum+'].description_4" value="'+$description.text()+'" class="text textInput required" maxlength="150" size="62">' +
			'<input type="hidden" name="index_positions_table" value="'+trNum+'">' +
			'<input type="hidden" name="items['+trNum+'].id_4" value="'+id+'">'
	);

	dateProcess($tr);
	
}


/**
 * 客户跟进记录
 * @param this1
 * @param id
 */
function edit_followups(this1,id){


	var $bj = $(this1);
	
	if($bj.attr("disabled")) return ;
	
	var $tr = $bj.parents("tr:first");
	var trNum = $tr[0].rowIndex -1 ;	

	$bj.attr("disabled",true); 
	

	
	
	
	
	var $link_time = $tr.children('td').eq(0);	
	$link_time.html(
			'<input type="text" readonly="readonly" name="items['+trNum+'].link_time" value="'+$link_time.text()+'" class="date date required textInput readonly valid" format="yyyy-MM-dd HH:mm" size="14">'
	);	


	var $link_type = $tr.children('td').eq(1);	
	$link_type.html(
			'<input type="text" name="items['+trNum+'].link_type" value="'+$link_type.text()+'" class="text textInput required" maxlength="5" size="4">'
	);

	var $link_content = $tr.children('td').eq(2);	
	$link_content.html(
			'<input type="text" name="items['+trNum+'].link_content" value="'+$link_content.text()+'" class="text textInput required" maxlength="500" size="60">'
	);
	
	var $description = $tr.children('td').eq(3);	
	$description.html(
			'<input type="text" name="items['+trNum+'].description" value="'+$description.text()+'" class="text textInput required" maxlength="150" size="12">' +
			'<input type="hidden" name="index_followups_table" value="'+trNum+'">' +
			'<input type="hidden" name="items['+trNum+'].id" value="'+id+'">'
	);

	dateProcess($tr);
	

	
}


/**
 * 编辑项目人员信息
 * @param form
 * @param validate_url
 * @param callback
 */
function edit_project_staff(this1,project_staff_id) {

	var $bj = $(this1);
	
	if($bj.attr("disabled")) return ;
	
	var $tr = $bj.parents("tr:first");
	var trNum = $tr[0].rowIndex -1 ;	

	$bj.attr("disabled",true); 
	

	var $staff_name = $tr.children('td').eq(0);	
	$staff_name.html(
			$staff_name.text() +
			'<input type="hidden" name="items['+trNum+'].staff.staff_name" value="'+$staff_name.text()+'">'
	);	
	
	var $join_project_datetime = $tr.children('td').eq(1);	
	$join_project_datetime.html(
			'<input type="text" readonly="readonly" name="items['+trNum+'].join_project_datetime" value="'+$join_project_datetime.text()+'" class="date date required textInput readonly valid" format="yyyy-MM-dd" size="10">'
	);	
	
	

	var $technical_cost = $tr.children('td').eq(3);	
	$technical_cost.html(
			'<input type="text"  name="items['+trNum+'].staff.technical_cost" value="'+$technical_cost.text()+'" class="number required " maxlength="12" size="8">'
	);

	var $client_dept = $tr.children('td').eq(4);	
	$client_dept.html(
			'<input type="text" name="items['+trNum+'].client_dept" value="'+$client_dept.text()+'" class="text textInput" maxlength="25" size="12">'
	);

	var $description = $tr.children('td').eq(5);	
	$description.html( 
		$description.text() +
		'<input type="hidden" name="index_project_staff_cost_table" value="'+trNum+'">' +
		'<input type="hidden" name="items['+trNum+'].project_staff_id" value="'+project_staff_id+'">'
	);
	
	dateProcess($tr);
	
}

/**
 * 增加月报明细记录
 */
function addMSD(btn , webroot ){
    var $table ;
    var $btn = $(btn);
    var curr = $btn.attr("curr");
    curr = parseInt(curr);
    var $box = $btn.parents(".unitBox:first");
    if($box == undefined || $box == null) {
        $table = $("#monthly_statement_detail_table");
    }else {
        $table = $("#monthly_statement_detail_table" , $box);
	}
	var $tbody = $table.find("tbody");

    var trTm = "<tr>";
    trTm += '<td nowrap><a href="javascript:void(0)" class="btnDel ">删除</a></td>';

    trTm += '<td nowrap>';
    trTm += ' <input type="hidden" size="2" id="id" name="id" value="'+(curr+1)+'"/> <input type="hidden"   name="monthly_statement_detail_id'+(curr+1)+'"/>' ;

    trTm += ' <input type="hidden" name="staff'+(curr+1)+'.staff_id">';
    trTm += ' <input type="text" readonly name="staff'+(curr+1)+'.staff_name" lookuppk="staff_id" size="8" class="required textInput readonly valid">';
    trTm += ' <a class="btnLook" width="950" href="'+webroot+'/StaffCostAction.do?method=lookup&delete_flag=0" lookupgroup="staff'+(curr+1)+'" lookuppk="staff_id" title="查找">查找</a>' ;


    trTm += '</td>';


    trTm += '<td><input type="text" value="0.0" size="7" maxlength="10" class="number required"  id="technical_cost" 	name="technical_cost'+(curr+1)+'"  /></td>';
    trTm += '<td><input type="text" value="0.0" size="7" maxlength="10" class="number required"  id="should_work_days" 	name="should_work_days'+(curr+1)+'"  /></td>';
    trTm += '<td><input type="text" value="0.0" size="7" maxlength="10" class="number required"  id="work_days" 	name="work_days'+(curr+1)+'"  /></td>';
    trTm += '<td><input type="text" value="0.0" size="7" maxlength="10" class="number required"  id="daily_overtime" 	name="daily_overtime'+(curr+1)+'"  /></td>';
    trTm += '<td><input type="text" value="0.0" size="7" maxlength="10" class="number required"  id="weekend_overtime" 	name="weekend_overtime'+(curr+1)+'"  /></td>';
    trTm += '<td><input type="text" value="0.0" size="7" maxlength="10" class="number required"  id="overtime_cost" 	name="overtime_cost'+(curr+1)+'"  /></td>';
    trTm += '<td><input type="text" value="0.0" size="7" maxlength="10" class="number required"  id="business_trip_days" 	name="business_trip_days'+(curr+1)+'"  /></td>';
    trTm += '<td><input type="text" value="0.0" size="7" maxlength="10" class="number required"  id="business_trip_cost" 	name="business_trip_cost'+(curr+1)+'"  /></td>';
    trTm += '<td><input type="text" value="0.0" size="7" maxlength="10" class="number required"  id="other_cost" 	name="other_cost'+(curr+1)+'"  /></td>';
    trTm += '<td><input type="text" value="0.0" size="7" maxlength="10" class="number required"  id="man_month" 	name="man_month'+(curr+1)+'"  /></td>';
    trTm += '<td><input type="text" value="0.0" size="7" maxlength="10" class="number required"  id="total_cost" 	name="total_cost'+(curr+1)+'"  /></td>';
    trTm += '<td><input type="text" value="0.0" size="16" maxlength="50" class="number required"  id="client_dept" 	name="client_dept'+(curr+1)+'"  /></td>';
    trTm += '<td><input type="text" value="0.0" size="38" maxlength="150" class="number required"  id="description" 	name="description'+(curr+1)+'"  /></td>';
    trTm += "</tr>";

    var $tr = $(trTm);
    $tr.appendTo($tbody).find("a.btnDel").click(function(){
        $(this).parents("tr:first").remove();
        return false;
    });

    $box.initUI();

    $btn.attr("curr" , (curr+1));
}