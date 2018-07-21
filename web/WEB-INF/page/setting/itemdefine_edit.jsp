<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" action="${webroot}/ItemDefineAction.do?method=${next_operation}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDoneEx);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="id" value="${itemDefine1.id }"/>
			
			<p>
				<label>项目名称：</label>
				<input name="item_name" class="required" type="text" size="30" maxlength="30" value="${itemDefine1.item_name }" />
			</p>
			
			<p>
				<label>使用方向：</label>
				<input type="radio" name="item_direction" value="0" <c:if test="${itemDefine1.item_direction == '0' }">checked="checked"</c:if>>  支出
				        
				<input type="radio" name="item_direction" value="1" <c:if test="${itemDefine1.item_direction == '1' }">checked="checked"</c:if>>  收入

			</p>
			<p>
				<label>备注：</label>
				<input name="description" class="text" type="text" size="30" maxlength="30" value="${itemDefine1.description }" />
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
