<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${webroot }/StaffExEntityAction.do?method=updateParents" class="pageForm required-validate" onsubmit="return submitZtreeInfo(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
		
				<iframe src="${webroot }/StaffExEntityAction.do?method=toTree&staff_name=${staffExEntity1.staff_name}" id="ztreeFrame" name="ztreeFrame" style="width:99%;height:99%;" frameborder="no" border="0" marginwidth="0" marginheight="0"></iframe>
				
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" id="closeSalary" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>				
	
