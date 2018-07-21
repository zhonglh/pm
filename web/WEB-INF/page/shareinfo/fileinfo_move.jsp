<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${webroot }/FileInfoAction.do?method=doMove&id=${fileInfo1.id}" class="pageForm required-validate" onsubmit="return submitFolderZtreeInfo(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">		
			
			<table  id="table1" class="list"  height="100%" width="100%" layoutH="60">
				<tr><td>
				<iframe src="${webroot }/FileInfoAction.do?method=folderZtree&id=${fileInfo1.id}" id="ztreeFrame" name="ztreeFrame" style="width:100%;height:100%;" frameborder="no" border="0" marginwidth="0" marginheight="0"></iframe>
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
	
	

	function submitFolderZtreeInfo(form, callback) {

		var $form = $(form);		
		var info = "";
		
		debugger;
		var json1=window.frames["ztreeFrame"].accept();
		if(json1 == ""){
			alertMsg.warn("请先选择要移动到的目录"); 
			return false;
		}else if(json1 == "same"){
			alertMsg.warn("目标文件夹和源文件夹相同，不能移动!"); 
			return false;
		}else {
			info = json1;
			$("#info").attr("val",json1);
		}
		

		$.ajax({
			type: form.method || 'POST',
			url:$form.attr("action"),
			data:{destFolderId:info},
			dataType:"json",
			cache: false,
			success: callback || DWZ.ajaxDone,
			error: DWZ.ajaxError
		});
		return false;
	}



</script>
