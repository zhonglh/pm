<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${webroot }/ReceivedPaymentAction.do?method=${next_operation}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">			
				
				<input name="receive_payment_id" class="text" type="hidden" size="30" value="${receivedPayment1.receive_payment_id}"/>
			<p>
				<label>项目名称：</label>
				<input name="project.project_id"	type="hidden" value="${receivedPayment1.project_id}"	 />		
				<input name="project.project_name" class="required" type="text" size="28" maxlength="60" value="${receivedPayment1.project_name}" readonly="true" />
					<a class="btnLook" href="${webroot }/ProjectAction.do?method=lookup" lookupGroup="project" lookupPk="project_id" width="950">选择项目</a>	
			</p>
			<p>
				<label>项目编号：</label>
				<input name="project.project_no" class="text" type="text" size="30" maxlength="30" value="${receivedPayment1.project_no}" readonly="true"/>
			</p>		
							
			
			
			<p>
				<label>付款单位：</label>
				<input name="project.project_client_name" class="text" type="text" size="30" maxlength="30" value="${receivedPayment1.payment_unit}" readonly="true"/>
			</p>
			

		

			<p>
				<label>到款所属月份：</label>
				<input  type="text" class="digits required date month"  autocomplete="off" maxlength="6" minlength="6"  format="yyyyMM"  size="30" name="receive_payment_month" value="${receivedPayment1.receive_payment_month}"/>
			</p>
			
			
			<p>
				<label>到款金额：</label>
				<input name="receive_payment_amount" class="number required" type="text" size="30" value="<fmt:formatNumber value="${receivedPayment1.receive_payment_amount }" type="number" pattern="####0.00#"/>" />
			</p>
			
			<p>
				<label>到款日期：</label>
				<input name="receive_payment_datetime" class="date required" type="text" size="30" value="<fmt:formatDate value="${receivedPayment1.receive_payment_datetime }" pattern="yyyy-MM-dd"/>" readonly="readonly" />
			</p>			
			

			
			<p>
				<label>收款方式：</label>								
				<input type="hidden" name="rai.id" value="${receivedPayment1.receivable_accounts_item_id }"/>
				<input name="rai.dic_data_name" class="text required" type="text" size="28"  value="${receivedPayment1.receivable_accounts_item_name }" 
					readonly="readonly" lookupPk="id" suggestFields="dic_data_name" lookupGroup="rai"
					suggestUrl="${webroot }/DicDataAction.do?method=lookup&dic_type_id=PAYMENT_METHOD" />
				<a class="btnAdd" href="${webroot}/DicDataAction.do?method=toEdit&dic_type_id=PAYMENT_METHOD" mask="true" width="500" height="260" rel="add_PAYMENT_METHOD"  target="dialog"><span>添加收款方式</span></a>	
						
				
			</p>

			<p>
				<label>对应发票编号：</label>
				<input name="invoice.invoice_id" class="text" type="hidden"   value="${receivedPayment1.invoice_id}"/>
				<input name="invoice.invoice_no" class="text" type="text" size="28"  value="${receivedPayment1.invoice_no }" readonly="readonly"/>
				<a class="btnLook" href="${webroot }/InvoiceManageAction.do?method=search" lookupGroup="invoice" lookupPk="invoice_id" width="950">选择</a>
			</p>											
			

			<div class="divider"></div>			
		
			<dl class="nowrap">
				<dt>备注：</dt>				
				<dd><textarea name="description" cols="95" rows="4" maxlength="300">${receivedPayment1.description }</textarea></dd>
			</dl>

			<div class="divider"></div>
	
			<p>
				<label>制单人：</label>  ${receivedPayment1.build_username }
			</p>
			<p>
				<label>制单日期：</label> <fmt:formatDate value="${receivedPayment1.build_datetime }" pattern="yyyy-MM-dd"/> 
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
