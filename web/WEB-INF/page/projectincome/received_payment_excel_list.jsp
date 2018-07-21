<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">		
			
		<div class="pageFormContent" layoutH="56">	
			<table class="list nowrap" width="1300" >
				<thead>
					<tr>
						<th width="200">导入说明</th>	
						<th  width="150">项目名称</th>
						<th width="120">付款单位</th>
						<th width="120">到款所属月份</th>
						<th width="80">到款金额</th>
						<th width="100">到款日期</th>
						<th width="80">收款方式</th>						
						<th width="100">对应发票号</th>
						<th width="300">备注</th>
					</tr>
				</thead>
				<tbody>					
					<c:forEach var="receivedPayment"  varStatus="status1" items="${list}">
					<tr>
						<td>
							<c:if test="${receivedPayment.errorInfo != null && receivedPayment.errorInfo != '' }">
								<font color="red">${receivedPayment.errorInfo }</font>
							</c:if>
							<c:if test="${receivedPayment.errorInfo == null || receivedPayment.errorInfo == '' }">
								<font color="green">成功</font>
							</c:if>
							
						</td>						
						<td>${receivedPayment.project_name }</td>
						<td>${receivedPayment.payment_unit }</td>
						<td>${receivedPayment.str_month }</td>
						<td>${receivedPayment.receive_payment_amount }</td>						
						<td><fmt:formatDate value="${receivedPayment.receive_payment_datetime }" pattern="yyyy-MM-dd"/></td>
						
						<td>${receivedPayment.receivable_accounts_item_name }</td>
						
						<td>${receivedPayment.invoice_no }</td>
						<td>${receivedPayment.description }</td>
				
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
