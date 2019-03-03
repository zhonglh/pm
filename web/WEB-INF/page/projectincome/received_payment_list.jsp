<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="${webroot }/ReceivedPaymentAction.do?method=list">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${pageSize}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${webroot }/ReceivedPaymentAction.do?method=list" method="post">
	<div class="searchBar">
		<ul class="searchContent">
		
			<li>
				<label>所在部门：</label>
				<input name="dept.dept_id" class="text" type="hidden" size="2"  value="${receivedPayment.dept_id }" />
				<input name="dept.dept_name" class="text" type="text" size="20"  value="${receivedPayment.dept_name }" readonly="readonly" lookupPk="dept_id"
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
				<label>到款所属月份：</label>
				<input  type="text" class="digits date month" size="7" maxlength="6" minlength="6" format="yyyyMM"  name="receive_payment_month1" value="${param.receive_payment_month1}"/>
				<input  type="text" class="digits date month" size="7" maxlength="6" minlength="6" format="yyyyMM"  name="receive_payment_month2" value="${param.receive_payment_month2}"/>
			</li>
			
			<li>
				<label>到款日期：</label>
				<input name="date1" class="date" type="text" size="7" value="${param.date1 }" />
				<input name="date2" class="date" type="text" size="7" value="${param.date2 }" />
			</li>
			
			<li>
				<label>发票编号：</label>
				<input type="text" name="invoice_no" value="${param.invoice_no}"/>
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


		<!-- 
		<div class="subBar">
			<ul>
			</ul>
		</div>
		 -->
		
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<c:if test="${operation_insert != null && operation_insert != '' }">
				<li><a class="add" href="${webroot}/ReceivedPaymentAction.do?method=toEdit" mask="true" width="900" height="450" rel="add_receivedPayment"  target="dialog"><span>添加到款记录</span></a></li>
			</c:if>
			
			<c:if test="${operation_delete != null && operation_delete != '' }">
				<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${webroot}/ReceivedPaymentAction.do?method=deleteReceivedPayment" class="delete"><span>删除</span></a></li> 
			</c:if>
			
			<c:if test="${operation_update != null && operation_update != '' }">
				<li><a class="edit" href="${webroot}/ReceivedPaymentAction.do?method=toEdit&receive_payment_id={sid_receivedPayment}" mask="true" width="900"  height="450" rel="update_receivedPayment" target="dialog" warn="请选择一条数据"><span>修改到款记录</span></a></li>
			</c:if>
			
			<c:if test="${operation_read != null && operation_read != '' }">
				<li><a class="edit" href="${webroot}/ReceivedPaymentAction.do?method=toView&receive_payment_id={sid_receivedPayment}" mask="true" width="850"  height="450" rel="view" target="dialog" warn="请选择一条数据"><span>查看明细</span></a></li>
			</c:if>
			
			
			<c:if test="${operation_check != null && operation_check != '' }">
				<li><a title="确定已经批量核实了这些单据吗?" target="selectedTodo" rel="ids" href="${webroot}/ReceivedPaymentAction.do?method=batchVerifyReceivedPayment" class="delete"><span>批量核单</span></a></li> 
			</c:if>	

			<li class="line">line</li>
			
				
			<c:if test="${operation_read != null && operation_read != '' }">
			<c:if test="${ totalRows <= 15000}">
				<li><a class="icon" href="${webroot}/ReceivedPaymentAction.do?method=export" target="dwzExport" targetType="navTab" title="确定要导出这些记录吗?"><span>导出EXCEL</span></a></li>
			</c:if>		
			</c:if>
			
			
			<li class="line">line</li>
			
			<c:if test="${operation_insert != null && operation_insert != '' }">
				<li><a class="add" href="${webroot }/ReceivedPaymentAction.do?method=toExcel" mask="true" width="850" height="480" rel="add_riemburse" target="dialog"><span>导入EXCEL</span></a></li>
			</c:if>
			 
		
			
			<c:if test="${operation_insert != null && operation_insert != '' }">				
				<li><a class="icon" href="${webroot }/ReceivedPaymentAction.do?method=downloadtemplet" target="dwzExport" targetType="navTab" title="确定要导出这模板吗?"><span>下载模板</span></a></li>
			</c:if>		
			
			
			<li class="line">line</li>
			
			<c:if test="${operation_read != null && operation_read != '' }">
				<li><a class="icon" href="${webroot}/ReceivedHisAction.do?method=list&receive_payment_id={sid_receivedPayment}" mask="true" width="1200"  height="600" rel="view" target="dialog" warn="请选择一条数据"><span>历史记录</span></a></li>
			</c:if>		
						
		</ul>
	</div>
	<table class="table" width="1380" layoutH="162">
		<thead>
			<tr>
				<th width="20"><input type="checkbox" group="ids" class="checkboxCtrl" /></th>
				<th width="30" >序号</th>
				<th width="200">项目名称</th>
				<th width="80">项目编号</th>
				<th width="120">付款单位</th>
				<th width="100">到款所属月份</th>
				<th width="80">到款金额</th>
				<th width="100">到款日期</th>
				<th width="100">收款方式</th>
				<th width="120">对应发票号</th>					
				<th width="120">发票金额</th>	
				<th width="120">合同编号</th>		
				<th width="60">制单人</th>
				<th width="60">核单人</th>		
				<th width="120" >申请状态</th>
			</tr>
		</thead>
		
		<tbody>		
			<c:forEach var="receivedPayment"  varStatus="status1" items="${list}">
			<tr target="sid_receivedPayment" rel="${receivedPayment.receive_payment_id }">
				<td>
						<c:if test="${receivedPayment.verify_userid==null || receivedPayment.verify_userid=='' }">
						<input name="ids" value="${receivedPayment.receive_payment_id }" type="checkbox" />
					</c:if>
				</td>
				<td>${pageNum*pageSize-pageSize + status1.index + 1 }</td>
				<td>${receivedPayment.project_name }</td>
				<td>${receivedPayment.project_no }</td>
				<td>${receivedPayment.payment_unit }</td>
				<td>${receivedPayment.receive_payment_month }</td>
				<td align="right"><b><fmt:formatNumber value="${receivedPayment.receive_payment_amount }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td><fmt:formatDate value="${receivedPayment.receive_payment_datetime}" pattern="yyyy-MM-dd" /></td>
				<td>${receivedPayment.receivable_accounts_item_name }</td>
				<td>
					<u>
					<a class="edit" href="${webroot}/InvoiceManageAction.do?method=toView&invoice_id=${receivedPayment.invoice_id}" 
						title="发票信息" mask="true" width="850"  height="450" rel="invoice_view" target="dialog" >
					${receivedPayment.invoice_no }
					</a>
					</u>
				</td>
				<td align="right" <c:if test="${receivedPayment.is_received_payment== '2' ||  receivedPayment.is_received_payment== '0'}">style="background:red"</c:if>>
					<b>
						<fmt:formatNumber value="${receivedPayment.invoice_amount }" type="currency" pattern="###,###,##0.00"/>
					</b>
				</td>
				<td>${receivedPayment.contract_no }</td>
				<td>${receivedPayment.build_username }</td>
				<td>${receivedPayment.verify_username }</td>
				<td>
					<c:if test="${receivedPayment.need_approve!=null && receivedPayment.need_approve=='1' }">
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
