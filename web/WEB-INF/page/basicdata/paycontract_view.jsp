<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" action="${webroot}/PayContractAction.do?method=verifyPayContract" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="id" value="${payContract1.id }"/>
			<p>
				<label>合同编号：</label>
				${payContract1.contract_no }
			</p>
			<p>
				<label>公司名称：</label>
				${payContract1.company_name }
			</p>
			<p>
				<label>执行合同：</label>
				${payContract1.exec_contract }
			</p>
			<p>
				<label>金额：</label>
				<fmt:formatNumber value="${payContract1.amount }" type="currency" pattern="###,###,##0.00"/>
			</p>
			<p>
				<label>合同签订日期：</label>
				<fmt:formatDate value="${payContract1.signing_date }" pattern="yyyy-MM-dd"/>
			</p>
			<p>
				<label>合同有效日期1</label>
				<fmt:formatDate value="${payContract1.validity_date1 }" pattern="yyyy-MM-dd"/>
				---
				<fmt:formatDate value="${payContract1.validity_date2 }" pattern="yyyy-MM-dd"/>
			</p>


			<p>
				<label>负责人：</label>
				${payContract1.manager_username }
			</p>


			<p>
				<label>分包项目：</label>
				${payContract1.project_name }
			</p>

			<p>
				<label>提交日期：</label>
				<fmt:formatDate value="${payContract1.submit_date }" pattern="yyyy-MM-dd"/>
			</p>
			<p>
				<label>客户联系人：</label>
				${payContract1.client_linkman }
			</p>
			<p>
				<label>邮箱/电话：</label>
				${payContract1.email_phone }
			</p>
			<p>
				<label>合同份数：</label>
				<fmt:formatNumber value="${payContract1.contract_number }" type="number" pattern="###,###,##0"/>
			</p>

			<p>
				<label>付款方式：</label>
				${payContract1.paymen_mode }
			</p>
			<p>
				<label>备注：</label>
				${payContract1.description }
			</p>

			<div class="divider"></div>
			<p>
				<label>制单人：</label>  ${payContract1.build_username }
			</p>
			<p>
				<label>制单日期：</label> <fmt:formatDate value="${payContract1.build_datetime }" pattern="yyyy-MM-dd"/>
			</p>

			<c:import url="../common/applyapproveInfo.jsp"></c:import>
		</div>
		<c:import url="../common/applyapproveButton.jsp"></c:import>
	</form>
</div>
