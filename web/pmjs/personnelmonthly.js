

/**
 * 人事月报查询提交方法
 * @param form
 * @param navTabId
 * @returns {Boolean}
 */
function pmSearch(form, navTabId){
	var $form = $(form);
	var the_month =  $("#the_month").val();
	var staff_name =  $("#staff_name").val();
	if(the_month == "" && staff_name == ""){
		alertMsg.info('请先选择人员姓名或者月份后再检索！');
		return false;
	}
	return navTabSearch(form,navTabId);
}




