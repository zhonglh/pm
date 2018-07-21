<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${webroot }/InvoiceManageAction.do?method=verifyInvoice" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">			
				
				<input name="invoice_id" class="text" type="hidden" size="30" value="${invoice1.invoice_id}"/>
			<p>
				<label>项目名称：</label>
				${invoice1.project_name}
			</p>
			<p>
				<label>项目编号：</label>
				${invoice1.project_no}
			</p>		
					
			
			<p>
				<label>发票编号：</label>
				${invoice1.invoice_no}
			</p>			
			
			
			<p>
				<label>发票抬头：</label>
				${invoice1.invoice_title}
			</p>			
			
		
			<p>
				<label>开票日期：</label>				
				<fmt:formatDate value="${invoice1.invoice_date }" pattern="yyyy-MM-dd"/>
			</p>

			<p>
				<label>发票内容：</label>
				${invoice1.invoice_content_name }
			</p>
			
			
			
			<p>
				<label>发票类型：</label>
				<c:if test="${'1' == invoice1.invoice_type }">增值税普通发票</c:if>
				<c:if test="${'2' == invoice1.invoice_type }">增值税专用发票</c:if>
			</p>			
			

			<p>
				<label>是否免税：</label>
				<c:if test="${'1' == invoice1.is_exemption_tax }">是</c:if>
				<c:if test="${'0' == invoice1.is_exemption_tax }">否</c:if>
			</p>		
			<p>
				<label>发票金额：</label>
				<fmt:formatNumber value="${invoice1.invoiceno_amount }" type="currency" pattern="###,###,##0.00"/>
			</p>
			
			<p>
				<label>税率：</label>
				${invoice1.tax_rate }(%)
			</p>											
			
			<p>
				<label>开票人员：</label>
				${invoice1.invoice_staff_name }	
			</p>												
			
			<p>
				<label>发票接收人员：</label>
				${invoice1.invoice_receive_name}
			</p>					
			
			
			<p>
				<label>月度结算单：</label>
				<c:if test="${invoice1.monthly_statement_id != null }">
					<a class="edit" href="${webroot}/MonthlyStatementAction.do?method=toView&monthly_statement_id=${invoice1.monthly_statement_id}" mask="true" width="960"  height="550" rel="monthly_view" target="dialog" >
						<u>${invoice1.monthly_statement_name }<spring:message code="statement.type.${invoice1.statement_type }"/></u>
					</a>
				</c:if>
			</p>		
			
			
			<p>
				<label>合同编号：</label>
				<c:if test="${invoice1.contract_no != null && invoice1.contract_no != '' }">
					<a class="edit" href="${webroot}/ContractAction.do?method=toView&id=${invoice1.contract_id}" mask="true" width="860"  height="450" title="合同信息" rel="contract_view" target="dialog" >
						<u>${invoice1.contract_no}</u>
					</a>
				</c:if>
			</p>			
							
									
			<div class="divider"></div>		

			<p>
				<label>是否到款：</label>
				<c:if test="${'2' == invoice1.is_received_payment }">部分到款</c:if>
				<c:if test="${'1' == invoice1.is_received_payment }">已到款</c:if>
				<c:if test="${'0' == invoice1.is_received_payment }">未到款</c:if>
			</p>												
			
			<p>
				<label>到款日期：</label>
				 <fmt:formatDate value="${invoice1.received_payment_datetime }" pattern="yyyy-MM-dd"/> 
			</p>	
			<div class="divider"></div>			
		
			<dl class="nowrap">
				<dt>备注：</dt>
				<dd>${invoice1.description }</dd> 
			</dl>

			<div class="divider"></div>
	
			
	
			<c:import url="../common/applyapproveInfo.jsp"></c:import>
		</div>			

	
	
		<c:import url="../common/applyapproveButton.jsp"></c:import>
	</form>
</div>
