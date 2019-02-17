<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" action="${webroot}/CommonCostAction.do?method=verifyCommonCost" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="id" value="${commoncost1.id }"/>
			<p>
				<label>支付月份：</label>
				<fmt:formatDate value="${commoncost1.pay_date }" pattern="yyyy-MM-dd"/>
			</p>
			<p>
				<label>月份：</label>
				<fmt:formatNumber value="${commoncost1.use_month }" type="number" pattern="###,###,##0"/>
			</p>
			<p>
				<label>金额：</label>
				<fmt:formatNumber value="${commoncost1.amount }" type="currency" pattern="###,###,##0.00"/>
			</p>
			<p>
				<label>备注：</label>
				${commoncost1.description }
			</p>

			<c:import url="../common/applyapproveInfo.jsp"></c:import>
		</div>
		<c:import url="../common/applyapproveButton.jsp"></c:import>
	</form>
</div>
