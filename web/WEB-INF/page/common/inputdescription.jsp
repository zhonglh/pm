<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>


<div class="pageContent">
	<form method="post" action="${webroot }/ApplyApproveAction.do?method=addApplyApprove" class="pageForm required-validate" onsubmit="return validateCallback(this,  dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">			
				<input name="operation_type"  type="hidden"    value="${param.operation_type }" />
				<input name="data_type"  type="hidden"    value="${param.data_type }" />
				<input name="data_id"  type="hidden"    value="${param.data_id }" />
				<input name="approve_result"  type="hidden"    value="${param.approve_result }" />
				<input name="need_approve"  type="hidden"    value="${param.need_approve }" />
			<p>
				<textarea name="description" cols="60" rows="4" maxlength="300" />
			</p>
			

					
			
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
