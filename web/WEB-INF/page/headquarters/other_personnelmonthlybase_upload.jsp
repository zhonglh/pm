<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<h2 class="contentTitle">请选择需要上传的人事月报信息文件</h2>
<form action="${webroot }/OtherPersonnelMonthlyBaseAction.do?method=doExcel" method="post" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return iframeCallback(this, dailogDoneProcessError)">
<div class="pageContent">
	<div class="pageFormContent" layoutH="97">
		
				
		
		<p>
			<label>文件：</label>
			<input type="file" name="image" class="required" size="40" />
		</p>
		
		<br /><br /><br /><br />
		
		
				
		
		<p>
		<label>报表月份：</label>
		<input name="the_month" id="the_month" readonly class="digits required  month date" format="yyyyMM" minlength="6" maxlength="6" type="text"  value="${the_month }" />
		</p>
		
	</div>
	<div class="formBar">
		<ul>
			<li><div class="buttonActive"><div class="buttonContent"><button type="submit">上传</button></div></div></li>
			<li><div class="button"><div class="buttonContent"><button class="close" type="button">关闭</button></div></div></li>
		</ul>
	</div>
</div>
</form>
