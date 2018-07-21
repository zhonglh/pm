<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="${webroot }/InvoiceManageAction.do?method=search">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${pageSize}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return dialogSearch(this);" action="${webroot }/InvoiceManageAction.do?method=search" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>项目名称：</label>
				<input type="text" name="project_name" value="${param.project_name}"/>
			</li>

			<li>
				<label>发票号：</label>
				<input  type="text" class="text"  name="invoice_no" value="${param.invoice_no}"/>
			</li>

			
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
		</ul>


		
	</div>
	</form>
</div>
<div class="pageContent">
	<table class="table" width="1800" layoutH="116">
		<thead>
			<tr>
				<th width="20"><input type="checkbox" group="ids" class="checkboxCtrl" /></th>
				<th width="40" >选择</th>
				<th width="200">项目名称</th>
				<th width="80"">项目编号</th>
				<th width="120">发票编号</th>
				<th width="160">发票抬头</th>
				<th width="120">发票类型</th>
				<th width="80">是否免税</th>
				<th width="80">发票金额</th>
				<th width="60">税率(%)</th>
				<th width="80">合同编号</th>
				
				<th width="80">是否到款</th>
				<th width="100">到款金额</th>
				<th width="100">到款日期</th>
				<th width="80">开票人员</th>
				<th width="100">发票接收人</th>
				<th width="60">制单人</th>
				<th width="60">核单人</th>		
			</tr>
		</thead>
		
		<tbody>		
			<c:forEach var="invoice"  varStatus="status1" items="${list}">
			<tr target="sid_invoice" rel="${invoice.invoice_id }">
				<td>
					<a class="btnSelect" href="javascript:$.bringBack({invoice_id:'${invoice.invoice_id }',invoice_no:'${invoice.invoice_no }',contract_id:'${invoice.contract_id }',contract_no:'${invoice.contract_no }'})" title="">选择</a>						
				</td>
				<td>${pageNum*pageSize-pageSize + status1.index + 1 }</td>
				<td>${invoice.project_name }</td>
				<td>${invoice.project_no }</td>
				<td>${invoice.invoice_no }</td>
				<td>${invoice.invoice_title }</td>
				<td><spring:message code="invoice.type.${invoice.invoice_type }"/></td>
				<td><spring:message code="boolean.${invoice.is_exemption_tax }"/></td>
				<td align="right"><b><fmt:formatNumber value="${invoice.invoiceno_amount }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="right"><b><fmt:formatNumber value="${invoice.tax_rate }" type="currency" pattern="###,###,##0.00"/></b></td>
				
				<td>${invoice.contract_no }</td>
				<td>
					<c:if test="${'2' == invoice.is_received_payment }">部分到款</c:if>
					<c:if test="${'1' == invoice.is_received_payment }">已到款</c:if>
					<c:if test="${'0' == invoice.is_received_payment }">未到款</c:if>		
				</td>				
				<td align="right"><b><fmt:formatNumber value="${invoice.receive_payment_amount }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td><fmt:formatDate value="${invoice.received_payment_datetime}" pattern="yyyy-MM-dd" /></td>
				<td>${invoice.invoice_staff_name }</td>
				<td>${invoice.invoice_receive_name }</td>
				<td>${invoice.build_username }</td>
				<td>${invoice.verify_username }</td>					
			</tr>	
			</c:forEach>
		</tbody>
	</table>


	<!-- 分页 -->
	<c:import url="../_frag/pager/panelBarDialog.jsp"></c:import>

</div>
