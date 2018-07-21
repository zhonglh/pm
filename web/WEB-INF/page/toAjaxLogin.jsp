<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>


<div class="pageContent">

	
	<form method="post" action="LoginAction.do?method=ajaxLogin" class="pageForm" onsubmit="return validateCallback(this, dailogDoneProcessLogin)">
		<div class="pageFormContent" layoutH="58">
			<br>
			<div class="unit">
						<label><spring:message code="login.loginname" />：</label>
						<input type="text" name="username" size="15" class="login_input required" value="${loginName }" />	
			</div>
			<div class="unit">

					<label><spring:message code="login.password" />：</label>
					<input type="password" name="password" size="15" class="login_input required"  value="" index="0"/>
					
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

