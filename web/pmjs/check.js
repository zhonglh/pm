
/**
 * 先验证， 在决定是否继续提交
 * @param form
 * @param validate_url
 * @param callback
 * @returns {Boolean}
 */
function validateCheckCallback(form, validate_url, callback) {
	var $form = $(form);
	if (!$form.valid()) {
		return false;
	}

	if(validate_url){
		$.ajax({
			type: form.method || 'POST',
			url:validate_url,
			data:$form.serializeArray(),
			dataType:"json",
			cache: false,
			success: function(json){
				if (json.statusCode == DWZ.statusCode.ok){
					$.ajax({
						type: form.method || 'POST',
						url:$form.attr("action"),
						data:$form.serializeArray(),
						dataType:"json",
						cache: false,
						success: callback || DWZ.ajaxDone,
						error: DWZ.ajaxError
					});
				}else {
					DWZ.ajaxDone(json);
				}
				
			},
			error: DWZ.ajaxError
		});		
		
	}else {
		$.ajax({
			type: form.method || 'POST',
			url:$form.attr("action"),
			data:$form.serializeArray(),
			dataType:"json",
			cache: false,
			success: callback || DWZ.ajaxDone,
			error: DWZ.ajaxError
		});
	}
	return false;
}