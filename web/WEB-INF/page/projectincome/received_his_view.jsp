<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">
		<div class="pageFormContent" layoutH="56">			
				
				<input name="receive_payment_id" class="text" type="hidden" size="30" value="${receivedPayment1.receive_payment_id}"/>
			<p>
				<label>项目名称：</label>
				${receivedPayment1.project_name}
			</p>
			<p>
				<label>项目编号：</label>
				${receivedPayment1.project_no}
			</p>		
			
			<p>
				<label>付款单位：</label>
				${receivedPayment1.payment_unit}
			</p>

			<p>
				<label>到款所属月份：</label>				
				${receivedPayment1.receive_payment_month}
			</p>
			
			<p>
				<label>到款金额：</label>
				<fmt:formatNumber value="${receivedPayment1.receive_payment_amount }" type="number" pattern="#,###,##0.00#"/>
			</p>
			
			<p>
				<label>到款日期：</label>
				<fmt:formatDate value="${receivedPayment1.receive_payment_datetime }" pattern="yyyy-MM-dd"/>
			</p>			
			

			
			<p>
				<label>收款方式：</label>
				${receivedPayment1.receivable_accounts_item_name }
			</p>					

			<p>
				<label>对应发票编号：</label>
				${receivedPayment1.invoice_no}
			</p>					

			<p>
				<label>合同编号：</label>
				<c:if test="${receivedPayment1.contract_no != null && receivedPayment1.contract_no != '' }">
					<a class="edit" href="${webroot}/ContractAction.do?method=toView&id=${receivedPayment1.contract_id}" mask="true" width="860"  height="450" title="合同信息" rel="contract_view" target="dialog" >
						<u>${receivedPayment1.contract_no}</u>
					</a>
				</c:if>
			</p>											
			

			<div class="divider"></div>			
		
			<dl class="nowrap">
				<dt>备注：</dt>				
				<dd>${receivedPayment1.description }</dd>
			</dl>

			<div class="divider"></div>
	
			
	
		</div>			

	
	
	</form>
</div>
