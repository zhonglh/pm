<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="system.name"/></title>
<link href="${webroot}/dwz/themes/css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">if(window!=top){top.location.replace(document.URL);}</script>
	<script type="text/javascript">
		function onLoadBody(form1){
			
			if (window != top) 
				top.location.href = location.href;
			
			var username1 = form1.username.value;

			
			if(username1 == "" || username1 == " ")
				form1.username.focus();
			else 
				form1.password.focus();
		}
	</script>

</head>





<body onload="onLoadBody(form1);">
	<div id="login">
		<div id="login_header">
			<h1 class="login_logo">
			</h1>
			<div class="login_headerContent">
				<div class="navList">
					<ul>
					<!-- 
						<li><a href="#"><spring:message code="login.setup_home" /></a></li>
						<li><a href="#"><spring:message code="login.help" /></a></li>
					 -->
					</ul>
				</div>
				<h2 class="login_title"></h2>
			</div>
		</div>
		<div id="login_content">
			<div class="loginForm">
				<form method="post" name="form1" action="LoginAction.do?method=login">
					<c:if test="${!empty message}"><p style="color: red">${message}</p></c:if>
					<p>
						<label><spring:message code="login.loginname" />：</label>
						<input type="text" id="username" name="username" size="12" class="login_input" value="${loginName }" />
					</p>
					<p>
						<label><spring:message code="login.password" />：</label>
						<input type="password" id="password" name="password" size="12" class="login_input"  value="" />
					</p>
					<p id="checkcode" style="display:none">
						<label><spring:message code="login.verification_cod" />：</label>
						<input class="code" type="text" size="5" />
						<span><img src="${webroot}/dwz/themes/default/images/header_bg.png" alt="" width="75" height="24" /></span>
					</p>
					<div class="login_bar">
						<input class="sub" type="submit" value=" " />
					</div>
				</form>
			</div>
			<div class="login_banner"><img src="${webroot}/dwz/themes/default/images/login_banner.jpg" width="950" height="270" /></div>
			<div class="login_main">
				<ul class="helpList">
					<!-- 
					<li><a href="#">下载驱动程序</a></li>
					<li><a href="#">如何安装密钥驱动程序？</a></li>
					<li><a href="#">忘记密码怎么办？</a></li>
					<li><a href="#">为什么登录失败？</a></li>
					 -->
				</ul>
				<div class="login_inner">
					<p><spring:message code="system.description1" /></p>
					<p><spring:message code="system.description2" /></p>
					<p><spring:message code="system.description3" /></p>
					<p><spring:message code="system.description4" /></p>
				</div>
			</div>
		</div>
		<div id="login_footer">
			Copyright &copy; 2015 http://www.souvi.com/     &nbsp;&nbsp;  Inc. All Rights Reserved.
		</div>
	</div>
</body>
</html>