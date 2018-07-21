<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>


<div class="pageContent">
	<form method="post" action="${webroot }/MailSenderAction.do?method=saveMailSender" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">			
			
			
				<input name="sender_id"  type="hidden" size="30"    value="${mailSender1.sender_id }" />


			<p>
				<label>邮箱服务器：</label>
				<input name="mail_server_host" class="text required" type="text" size="25" minlength="2" maxlength="60"   value="${mailSender1.mail_server_host }" />
			</p>
			

			<p>
				<label>邮箱服务器端口：</label>
				<input name="mail_server_port" class="digits required" type="text" size="25" maxlength="5"   value="${mailSender1.mail_server_port }" />
			</p>		

			

			<p>
				<label>发件箱用户名：</label>
				<input name="from_username" class="email required" type="text" size="25" maxlength="100"   value="${mailSender1.from_username }" />
			</p>	
			

			<p>
				<label>发件箱密码：</label>
				<input name="from_password" class="text required" type="text" size="25" maxlength="50"   value="${mailSender1.from_password }" />
			</p>											
					
			<p>
				<label>状态：</label>
				<select name="status" id="status" class="required">
					<option value="1" <c:if test="${'1' == mailSender1.status }">selected</c:if>>启用</option>
					<option value="0" <c:if test="${'0' == mailSender1.status }">selected</c:if>>关闭</option>
				</select>
			</p>			
			

					
			
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
