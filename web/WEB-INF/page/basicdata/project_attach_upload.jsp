<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<h2 class="contentTitle">请选择需要上传的附件</h2>

<form action="${webroot }/ProjectAction.do?method=uploadProjectAttach" method="post" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return iframeCallback(this, $.bringBack)">

<div class="pageContent">
	<div class="pageFormContent" layoutH="97">
		<input type="hidden" id="project_id" name="project_id" value="${param.project_id }"/>
		<input type="hidden" id="attachment_type" name="attachment_type" value="${param.attachment_type }"/>
		<dl>
			<dt>附件：</dt><dd><input type="file" name="image" class="required" size="40" /></dd>
		</dl>
	</div>
	<div class="formBar">
		<ul>
			<li><div class="buttonActive"><div class="buttonContent"><button type="submit">上传</button></div></div></li>
			<li><div class="button"><div class="buttonContent"><button class="close" type="button">关闭</button></div></div></li>
		</ul>
	</div>
</div>
</form>