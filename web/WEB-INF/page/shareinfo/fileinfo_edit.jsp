<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">		

		
			<table class="list" id="fileupload" height="100%" width="100%" layoutH="60">
				<tr><td>
				<input type="hidden" name="info" id="info" />
				<iframe src="${webroot}/FileInfoAction.do?method=toUpload&parent_id=${fileInfo1.parent_id}" id="file1" name="file1" style="width:100%;height:100%;" frameborder="no" border="0" marginwidth="0" marginheight="0"></iframe>
				</td></tr>
			</table>
			
						

		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="button"><div class="buttonContent"><button type="button"  class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>				
	
