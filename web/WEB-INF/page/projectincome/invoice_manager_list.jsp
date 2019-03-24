<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="${webroot }/InvoiceManageAction.do?method=list">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${pageSize}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${webroot }/InvoiceManageAction.do?method=list" method="post">
	<div class="searchBar">
		<ul class="searchContent">
		
			<li>
				<label>所在部门：</label>
				<input name="dept.dept_id" class="text" type="hidden" size="2"  value="${invoice.dept_id }" />
				<input name="dept.dept_name" class="text" type="text" value="${invoice.dept_name }" readonly="readonly" lookupPk="dept_id"
				suggestFields="dept_name" suggestUrl="${webroot }/DeptAction.do?method=lookup&use=search" lookupGroup="dept"/>				
			</li>
			
		
			<li>
				<label>项目名称：</label>
				<input type="text" name="project_name" value="${param.project_name}"/>
			</li>


			<li>
				<label>核单情况：</label>			
				<select name="verify_flag" style="width:153px">
				<option value="" <c:if test="${'' == param.verify_flag }">selected</c:if>><spring:message code="verify.flag."/></option>
				<option value="1" <c:if test="${'1' == param.verify_flag }">selected</c:if>><spring:message code="verify.flag.1"/></option>
				<option value="2" <c:if test="${'2' == param.verify_flag }">selected</c:if>><spring:message code="verify.flag.2"/></option>
				<option value="3" <c:if test="${'3' == param.verify_flag }">selected</c:if>><spring:message code="verify.flag.3"/></option>
				</select>
			</li>	
		</ul>


		<ul class="searchContent">		
		
			<li>
				<label>发票号：</label>
				<input  type="text" class="text"  name="invoice_no" value="${param.invoice_no}"/>
			</li>
			
			<li>
				<label>开票日期：</label>
				<input type="text" class="date" name="date1" size="7" value="${param.date1 }"/>
				<input type="text" class="date" name="date2" size="7" value="${param.date2 }"/>
			</li>
			
					
			<li>
				<label>结算单月份：</label>
				<input  type="text" class="digits date month" size="7"  autocomplete="off" maxlength="6" minlength="6"  format="yyyyMM" name="statement_month1" value="${param.statement_month1}"/>
				<input  type="text" class="digits date month" size="7"  autocomplete="off" maxlength="6" minlength="6"  format="yyyyMM" name="statement_month2" value="${param.statement_month2}"/>
			</li>

		</ul>



		<ul class="searchContent">

			<li>
				<label>销售负责人：</label>
				<input  type="text" class="text"  name="sales_username" value="${param.sales_username}"/>
			</li>


			<li style="width:40px">
				<div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div>
			</li>
		</ul>
		
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<c:if test="${operation_insert != null && operation_insert != '' }">
				<li><a class="add" href="${webroot}/InvoiceManageAction.do?method=toEdit" mask="true" width="900" height="450" rel="add_invoice"  target="dialog"><span>添加发票</span></a></li>
			</c:if>
			
			<c:if test="${operation_delete != null && operation_delete != '' }">
				<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${webroot}/InvoiceManageAction.do?method=deleteInvoice" class="delete"><span>删除</span></a></li> 
			</c:if>
			
			<c:if test="${operation_update != null && operation_update != '' }">
				<li><a class="edit" href="${webroot}/InvoiceManageAction.do?method=toEdit&invoice_id={sid_invoice}" mask="true" width="900"  height="450" rel="update_invoice" target="dialog" warn="请选择一条数据"><span>修改发票</span></a></li>
			</c:if>
			
			<c:if test="${operation_read != null && operation_read != '' }">
				<li><a class="edit" href="${webroot}/InvoiceManageAction.do?method=toView&invoice_id={sid_invoice}" mask="true" width="850"  height="450" rel="view" target="dialog" warn="请选择一条数据"><span>查看明细</span></a></li>
			</c:if>
			
			<c:if test="${operation_check != null && operation_check != '' }">
				<li><a title="确定已经批量核实了这些单据吗?" target="selectedTodo" rel="ids" href="${webroot}/InvoiceManageAction.do?method=batchVerifyInvoice" class="delete"><span>批量核单</span></a></li> 
			</c:if>	

			<li class="line">line</li>
			
				
			<c:if test="${operation_read != null && operation_read != '' }">
			<c:if test="${ totalRows <= 15000}">
				<li><a class="icon" href="${webroot}/InvoiceManageAction.do?method=export" target="dwzExport" targetType="navTab" title="确定要导出这些记录吗?"><span>导出EXCEL</span></a></li>
			</c:if>		
			</c:if>
			
			
			
			<li class="line">line</li>
			
			<c:if test="${operation_insert != null && operation_insert != '' }">
				<li><a class="add" href="${webroot }/InvoiceManageAction.do?method=toExcel" mask="true" width="850" height="480" rel="add_invoice" target="dialog"><span>导入EXCEL</span></a></li>
			</c:if>
			 
		
			
			<c:if test="${operation_insert != null && operation_insert != '' }">				
				<li><a class="icon" href="${webroot }/InvoiceManageAction.do?method=downloadtemplet" target="dwzExport" targetType="navTab" title="确定要导出这模板吗?"><span>下载模板</span></a></li>
			</c:if>		
			
			
			<li class="line">line</li>
			
			<c:if test="${operation_read != null && operation_read != '' }">
				<li><a class="icon" href="${webroot}/InvoiceHisAction.do?method=list&invoice_id={sid_invoice}" mask="true" width="1200"  height="600" rel="view" target="dialog" warn="请选择一条数据"><span>历史记录</span></a></li>
			</c:if>				
		</ul>
	</div>
	<table class="table" width="2100" layoutH="162">
		<thead>
			<tr>
				<th width="20"><input type="checkbox" group="ids" class="checkboxCtrl" /></th>
				<th width="30" >序号</th>
				<th width="200">项目名称</th>
				<th width="80">项目编号</th>
				<th width="120">发票编号</th>
				<th width="160">发票抬头</th>
				<th width="120">发票类型</th>
				<th width="80">开票日期</th>
				<th width="80">是否免税</th>
				<th width="80">发票金额</th>
				<th width="60">税率(%)</th>				
				<th width="200">月度结算单</th>
				<th width="80">合同编号</th>
				
				<th width="80">是否到款</th>
				<th width="100">到款金额</th>
				<th width="100">未到款金额</th>
				<th width="100">到款日期</th>
				<th width="80">开票人员</th>
				<th width="100">发票接收人</th>
				<th width="200">备注</th>
				<th width="60">制单人</th>
				<th width="60">核单人</th>		
				<th width="120" >申请状态</th>
			</tr>
		</thead>
		
		<tbody>		
			<c:forEach var="invoice"  varStatus="status1" items="${list}">
			<tr target="sid_invoice" rel="${invoice.invoice_id }">
				<td>
						<c:if test="${invoice.verify_userid==null || invoice.verify_userid=='' }">
						<input name="ids" value="${invoice.invoice_id }" type="checkbox" />
					</c:if>
				</td>
				<td>${pageNum*pageSize-pageSize + status1.index + 1 }</td>
				<td>${invoice.project_name }</td>
				<td>${invoice.project_no }</td>
				<td>${invoice.invoice_no }</td>
				<td>${invoice.invoice_title }</td>
				<td><spring:message code="invoice.type.${invoice.invoice_type }"/></td>
				<td><fmt:formatDate value="${invoice.invoice_date }" pattern="yyyy-MM-dd"/></td>
				<td><spring:message code="boolean.${invoice.is_exemption_tax }"/></td>
				<td align="right"><b><fmt:formatNumber value="${invoice.invoiceno_amount }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="right"><b><fmt:formatNumber value="${invoice.tax_rate }" type="currency" pattern="###,###,##0.00"/></b></td>				
				<td>
					<u>
					<a class="edit" href="${webroot}/MonthlyStatementAction.do?method=toView&monthly_statement_id=${invoice.monthly_statement_id}" mask="true" width="1060"  height="550" rel="monthly_view" target="dialog" >
					${invoice.monthly_statement_name }
					</a>
					</u>
				</td>
				
				<td>${invoice.contract_no }</td>
				
				<td>
					<c:if test="${'2' == invoice.is_received_payment }">部分到款</c:if>
					<c:if test="${'1' == invoice.is_received_payment }">已到款</c:if>
					<c:if test="${'0' == invoice.is_received_payment }">未到款</c:if>				
				</td>
				<td align="right"><b><fmt:formatNumber value="${invoice.receive_payment_amount }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="right"><b><font color="red"><fmt:formatNumber value="${invoice.invoiceno_amount - invoice.receive_payment_amount }" type="currency" pattern="###,###,##0.00"/></font></b></td>
				<td><fmt:formatDate value="${invoice.received_payment_datetime}" pattern="yyyy-MM-dd" /></td>
				<td>${invoice.invoice_staff_name }</td>
				<td>${invoice.invoice_receive_name }</td>
				<td>${invoice.description }</td>
				<td>${invoice.build_username }</td>
				<td>${invoice.verify_username }</td>	
				<td>				
					<c:if test="${invoice.need_approve!=null && invoice.need_approve=='1' }">
						<font color="red">已申请取消核单</font>
					</c:if>
				</td>								
			</tr>	
			</c:forEach>
		</tbody>
	</table>


	<!-- 分页 -->
	<c:import url="../_frag/pager/panelBar_totalAmount.jsp"></c:import>

</div>
