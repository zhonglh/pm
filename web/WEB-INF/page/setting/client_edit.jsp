<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>


<div class="pageContent">
	<form method="post" action="${webroot }/ItemsAction.do?method=${next_operation }" class="pageForm required-validate" onsubmit="return validateCheckCallback(this, '${webroot }/ItemsAction.do?method=isExistClient', dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">			
			
			
				<input name="client_id"  type="hidden" size="30"    value="${client1.client_id }" />

			<p>
				<label>客户编号：</label>
				<input name="client_no" class="text required" type="text" size="25" maxlength="50"  value="${client1.client_no }" />
			</p>
			
			<p>
				<label>客户名称：</label>
				<input name="client_name" class="text required" type="text" size="25" maxlength="300"  value="${client1.client_name }" />
			</p>
			

					
			
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
