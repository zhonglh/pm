<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">		
			
		<div class="pageFormContent" layoutH="56">	
			<table class="list nowrap" width="1900" >
				<thead>
					<tr>
						<th width="200">导入说明</th>	
						<th  width="150">项目名称</th>
						<th width="120">发票编号</th>
						<th width="150">发票抬头</th>
						<th width="120">发票类型</th>
						<th width="150">发票内容</th>
						<th width="80">开票日期</th>
						<th width="80">是否免税</th>						
						<th width="80">发票金额</th>
						<th width="80">税率(%)</th>
											
						<th width="80">开票人员</th>					
						<th width="120">发票接收人员</th>				
						<th width="120">合同编号</th>					
						<th width="200">备注</th>
						
					</tr>
				</thead>
				<tbody>					
					<c:forEach var="invoice"  varStatus="status1" items="${list}">
					<tr>
						<td>
							<c:if test="${invoice.errorInfo != null && invoice.errorInfo != '' }">
								<font color="red">${invoice.errorInfo }</font>
							</c:if>
							<c:if test="${invoice.errorInfo == null || invoice.errorInfo == '' }">
								<font color="green">成功</font>
							</c:if>
							
						</td>						
						<td>${invoice.project_name }</td>
						<td>${invoice.invoice_no }</td>
						<td>${invoice.invoice_title }</td>
						
						<td>
							<c:if test="${invoice.invoice_type == '1' }">增值税普通发票</c:if>		
							<c:if test="${invoice.invoice_type == '2' }">增值税专用发票</c:if>					
								
							<c:if test="${invoice.invoice_type != '2' && invoice.invoice_type != '1'}">${invoice.invoice_type }</c:if>	
						</td>						
						
						<td>${invoice.invoice_content_name }</td	>
						
						<td><fmt:formatDate value="${invoice.invoice_date }" pattern="yyyy-MM-dd"/></td>					
						<td>
							<c:if test="${invoice.is_exemption_tax == '1' }">是</c:if>		
							<c:if test="${invoice.is_exemption_tax == '0' }">否</c:if>								
							<c:if test="${invoice.is_exemption_tax != '0' && invoice.is_exemption_tax != '1'}">${invoice.is_exemption_tax }</c:if>	
						</td>							
						
																
						<td><fmt:formatNumber value="${invoice.invoiceno_amount }" type="currency" pattern="##0.00"/></td>	
						<td><fmt:formatNumber value="${invoice.tax_rate }" type="currency" pattern="##0.00"/></td>
						<td>${invoice.invoice_staff_name }</td>	
						<td>${invoice.invoice_receive_name }</td>	
						<td>${invoice.contract_no }</td>	
						<td>${invoice.description }</td>
				
					</tr>
					</c:forEach>
				</tbody>
			</table>			
		</div>




		<div class="formBar">
			<ul>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
</div>
