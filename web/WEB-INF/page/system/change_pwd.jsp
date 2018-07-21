<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">
	
	<form method="post" action="${webroot }/UserAction.do?method=updatePassword" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
		<div class="pageFormContent" layoutH="58">
			<input type="hidden" name="user_id" value="${param.user_id }"/>

			<div class="unit">
				<label>旧密码：</label>
				<input type="password" name="oldPassword" size="30" minlength="6" maxlength="30" class="required alphanumeric" />
			</div>
			<div class="unit">
				<label>新密码：</label>
				<input type="password" id="user_password" name="user_password" size="30" minlength="6" maxlength="30" class="required alphanumeric"/>
			</div>
			<div class="unit">
				<label>确认新密码：</label>
				<input type="password" name="rnewPassword" size="30" equalTo="#user_password" class="required alphanumeric"/>
			</div>
			
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
	
</div>
