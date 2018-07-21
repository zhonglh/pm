<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>



<div class="pageContent">
	<form method="post" action="${webroot }/ParamsAction.do?method=save" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
	
		<div class="pageFormContent" layoutH="56">			
			
			<c:forEach var="params"  varStatus="status1" items="${list}">
			<p>
				<label>${params.param_name }</label>
				<input name="param_id${status1.index }" type="hidden" value="${params.param_id }" />
				
				
				
				<input name="param_value${status1.index }" class="required" type="text" size="30" maxlength="60" maxlength="30"   value="${params.param_value }" />
				
				
			</p>
			
		</c:forEach>
					
			
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


</div>