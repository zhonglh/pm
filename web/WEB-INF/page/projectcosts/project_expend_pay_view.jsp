<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" action="${webroot}/ProjectExpendPayAction.do?method=verifyProjectExpendPay" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="id" value="${projectExpendpay1.id }"/>


			<p>
				<label>项目名称：</label>
				${projectExpend1.project_name}
			</p>
			<p>
				<label>项目编号：</label>
				${projectExpend1.project_no}
			</p>



			<p>
				<label>应付金额：</label>
				${projectExpend1.amount }
			</p>

			<p>
				<label>累计已付金额：</label>
				${projectExpend1.pay_amount }
			</p>


			<div class="divider"></div>


			<p>
				<label>收到的发票号：</label>
				${projectExpendpay1.invoiceno }
			</p>
			<p>
				<label>实际支付日期：</label>
				<fmt:formatDate value="${projectExpendpay1.pay_date }" pattern="yyyy-MM-dd"/>
			</p>
			<p>
				<label>实付金额：</label>
				<fmt:formatNumber value="${projectExpendpay1.pay_amount }" type="currency" pattern="###,###,##0.00"/>
			</p>
			<p>
				<label>备注：</label>
				${projectExpendpay1.description }
			</p>

			<c:import url="../common/applyapproveInfo.jsp"></c:import>
		</div>
		<c:import url="../common/applyapproveButton.jsp"></c:import>
	</form>
</div>
