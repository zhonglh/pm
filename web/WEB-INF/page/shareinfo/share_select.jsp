<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${webroot }/FileInfoAction.do?method=saveShareInfo&file_id=${shareInfo1.file_id}" class="pageForm required-validate" onsubmit="return submitShareZtreeInfo(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">		
			<br>
			<div><input type="checkbox" id="is_share_all" name="is_share_all" <c:if test="${shareInfo1.is_share_all == '1' }">checked</c:if>  value="1" onclick="javaScript:clkShareAll(this);" />分享给所有人</div>
			<br><br>
			
		
			<input type="hidden" id="info" name="info"/>
			<table <c:if test="${shareInfo1.is_share_all == '1' }">style="display:none"</c:if> id="table1" class="list"  height="100%" width="100%" layoutH="115">
				<tr><td>
				<iframe src="${webroot }/FileInfoAction.do?method=toZtree&file_id=${shareInfo1.file_id}" id="ztreeFrame" name="ztreeFrame" style="width:100%;height:100%;" frameborder="no" border="0" marginwidth="0" marginheight="0"></iframe>
				</td>
			</table>
			
			


		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" id="closeSalary" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>				
	
<script type="text/javascript">

	function clkShareAll(ck1){
		if(ck1.checked)
			$("#table1").hide();
		else $("#table1").show();
	}
	
	

	function submitShareZtreeInfo(form, callback) {

		var $form = $(form);
		var ck1 = $("#is_share_all")[0];
		
		var info = "";
		var is_share_all = "0";
		
		
		if(!ck1.checked){
			var json1=window.frames["ztreeFrame"].accept();
			if(json1.indexOf("层级太大")>0){
				alertMsg.warn(json1);
				return false;
			}else {
				info = json1;
				$("#info").attr("val",json1);
			}
		}else {
			is_share_all = "1";
			$("#info").attr("val","");
		}

		$.ajax({
			type: form.method || 'POST',
			url:$form.attr("action"),
			data:{info:info,is_share_all:is_share_all},
			dataType:"json",
			cache: false,
			success: callback || DWZ.ajaxDone,
			error: DWZ.ajaxError
		});
		return false;
	}



</script>
