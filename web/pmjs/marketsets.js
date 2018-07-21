
//处理薪资模式设置功能

function clkAffectFixed(){
	$("#adf").hide();
}



function clkAffectDecline(){
	$("#adf").show();
}





//处理人员薪资模式
function clkSalaryModel(model){
	if(model == '0'){
		$("#ga").hide();
		$("#ps").hide();
		$("#oe").hide();
		$("#or").hide();
		$("#pr").hide();
		$("#cta").hide();
	}else {
		$("#ga").show();
		$("#ps").show();
		$("#oe").show();	
		$("#or").show();
		$("#pr").show();	
		$("#cta").show();
	}
}


/**
 * 提交树数据， 对应 模式设置菜单中的  树状显示的保存按钮
 * @param form
 * @param callback
 * @returns {Boolean}
 */
function submitZtreeInfo(form, callback) {
	var $form = $(form);
	
	var json1=window.frames["ztreeFrame"].accept();
	if(json1.indexOf("层级太大")>0){
		alertMsg.warn(json1);
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

function appendFormula(t){

    $("#computational_formula_text").focus();
    $("#computational_formula_text").insertAtCaret("  "+t+"  ");
}


jQuery.fn.extend({
	insertAtCaret : function(myValue) {
		return this.each(function(i) {
			if (document.selection) {
				// For browsers like Internet Explorer
				this.focus();
				sel = document.selection.createRange();
				sel.text = myValue;
				this.focus();
			} else if (this.selectionStart || this.selectionStart == '0') {
				// For browsers like Firefox and Webkit based
				var startPos = this.selectionStart;
				var endPos = this.selectionEnd;
				var scrollTop = this.scrollTop;
				this.value = this.value.substring(0, startPos) + myValue
						+ this.value.substring(endPos, this.value.length);
				this.focus();
				this.selectionStart = startPos + myValue.length;
				this.selectionEnd = startPos + myValue.length;
				this.scrollTop = scrollTop;
			} else {
				this.value += myValue;
				this.focus();
			}
		})
	}
});
