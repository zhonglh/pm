<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>


<div class="pageContent">
	<form method="post" action="${webroot }/ItemsAction.do?method=${next_operation }" class="pageForm required-validate" onsubmit="return validateCheckCallback(this, '${webroot }/ItemsAction.do?method=isExistReceivableAccountsItem', dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">			
			
			
				<input name="receivable_accounts_item_id"  type="hidden" size="30"    value="${receivableAccountsItem1.receivable_accounts_item_id }" />

			<p>
				<label>收款方式：</label>
				<input name="item_name" class="text required" type="text" size="25" maxlength="60" maxlength="30"   value="${receivableAccountsItem1.item_name }" />
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
