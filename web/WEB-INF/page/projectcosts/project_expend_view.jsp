<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${webroot}/ProjectExpendAction.do?method=verifyProjectExpend" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="project_expend_id" value="${projectExpend1.project_expend_id }"/>
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
				${projectExpend1.amount}
			</p>

			<p>
				<label>支出对象：</label>
				<spring:message code="expend.object.${projectExpend1.expend_object }"/>
			</p>			

			<p>
				<label>分包商名称：</label>
				${projectExpend1.sub_contractor_name}
			</p>
			

			<p>
				<label>分包合同编号：</label>
				${projectExpend1.sub_contractor_no}
			</p>						
			
			<p>
				<label>收到的发票号：</label>
				${projectExpend1.invoiceno}
			</p>	
			

			<p>
				<label>应付月份：</label>
				${projectExpend1.use_month}
			</p>			
			
			<p>
				<label>实际支付日期：</label>
				<fmt:formatDate value="${projectExpend1.pay_date }" pattern="yyyy-MM-dd"/>
			</p>	
			
			<p>
				<label>实付金额：</label>
				<fmt:formatNumber value="${projectExpend1.pay_amount }" type="number" pattern="#,###,##0.00#"/>
			</p>
			
			
			
			<p>
				<dl class="nowrap">
					<dt>描述：</dt>
					<dd>${projectExpend1.description }</dd>
				</dl>
			</p>	
						
		
	
			<c:import url="../common/applyapproveInfo.jsp"></c:import>
		</div>			

	
	
		<c:import url="../common/applyapproveButton.jsp"></c:import>
	</form>
</div>
