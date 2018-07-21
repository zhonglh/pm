/**
 * dialog 跳转
 * @param form
 * @returns {Boolean}
 */
function dialogJump(form) {
	var $form = $(form);
	if (!$form.valid()) {
		return false;
	}
	

	$.pdialog.reload($form.attr('action'), {data: $form.serializeArray()});
	
	return false;
}


function dialogJumpURL(form,url) {
	var $form = $(form);
	if (!$form.valid()) {
		return false;
	}
	

	$.pdialog.reload(url, {data: $form.serializeArray()});
	
	return false;
}


function configMsgFunc(json){
	if(json.statusCode == DWZ.statusCode.error) {
		if(json.message && alertMsg) alertMsg.error(json.message);
		else alert(json.message);
	} else if (json.statusCode == DWZ.statusCode.timeout) {
		if(alertMsg) alertMsg.error(json.message || DWZ.msg("sessionTimout"), {okCall:DWZ.loadLogin});
		else DWZ.loadLogin();
	} else {
		if(json.statusCode == DWZ.statusCode.confirm){
			alertMsg.confirm(json.message, {okCall: _okjump});
		}else {
			_okjump();
		}
		
	};
	
}

/**
 * 跳转
 */
function _okjump(){
	$form = $("#add_group_first");
	if(!$form || !$form.get(0)) {		
		$form = $("#monthly_statement_first");
	}
	if($form && $form.get(0) ) _goto_naxtDialog($form);
}


function _goto_naxtDialog(form){
	$.pdialog.reload(form.attr('action'), {data: form.serializeArray()});
}

/**
 * dialog 跳转
 * @param form
 * @returns {Boolean}
 */
function dialogCheckJump(form,check_url,callback) {
	var $form = $(form);
	if (!$form.valid()) {
		return false;
	}
	
	if(check_url) {
		$.ajax({
			type: form.method || 'POST',
			url:check_url,
			data:$form.serializeArray(),
			dataType:"json",
			cache: false,
			success: callback,
			error: DWZ.ajaxError
		});
	}else {
		_goto_naxtDialog($form);
	}
	
	
	
	return false;
}

/**
 * 加处理错误情况
 * @param json
 */
function dailogDoneProcessError(json){
	dialogAjaxDone(json);

	if (json.statusCode == DWZ.statusCode.error){
		if(json.forwardUrl){
			$.pdialog.reload(json.forwardUrl, {});
		}		
	}
}

/**
 * 加处理正确情况
 * @param json
 */
function dailogDoneProcessSuccess(json){
	dialogAjaxDone(json);
	if (json.statusCode == DWZ.statusCode.ok){
		
	}
}

/**
 * 加处理正确情况
 * @param json
 */
function dailogDoneProcessLogin(json){
	dailogDoneProcessSuccess(json);
	if (json.statusCode == DWZ.statusCode.ok){
		top.location.reload(false);
	}
}


function changeresume_no(this1){
	$("input[name='resume_no']").val( this1.value );
}


/**
 * 简历查重
 * @param form1
 */
function reviewRecruit(form1,webroot){
	$("#recrcz").val("1");
	form1.action = webroot + "/RecruitInfoAction.do?method=reviewRecruitInfo";
	//form1.submit();	
}


/**
 * 处理保存简历
 */
function saveRecruit(form1,webroot){
	
			$("#recrcz").val("2");
			form1.action = webroot + "/RecruitInfoAction.do?method=addRecruitInfo";
			//form1.submit();	
			return true;
	
	
	
}





