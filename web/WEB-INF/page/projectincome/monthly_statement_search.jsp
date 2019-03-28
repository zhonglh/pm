<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="${webroot }/MonthlyStatementAction.do?method=search">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${pageSize}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return dialogSearch(this);" action="${webroot }/MonthlyStatementAction.do?method=search" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>项目名称：</label>
				<input type="text" name="project_name" value="${param.project_name}"/>
			</li>

			<li>
				<label>结算单月份：</label>
				<input  type="text" class="digits date month"  autocomplete="off"   maxlength="6" minlength="6"  format="yyyyMM" name="statement_month" value="${param.statement_month}"/>
			</li>

			<li>
				<label>核单情况：</label>			
				<select name="verify_flag" class="combox">
				<option value="" <c:if test="${'' == param.verify_flag }">selected</c:if>><spring:message code="verify.flag."/></option>
				<option value="1" <c:if test="${'1' == param.verify_flag }">selected</c:if>><spring:message code="verify.flag.1"/></option>
				<option value="2" <c:if test="${'2' == param.verify_flag }">selected</c:if>><spring:message code="verify.flag.2"/></option>
				</select>
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
				<li><a class="add" href="${webroot}/MonthlyStatementAction.do?method=toEditFirst" mask="true" width="900" height="350" rel="add_monthlyStatement"  target="dialog"><span>添加月度结算单</span></a></li>
			</c:if>
			
			<c:if test="${operation_delete != null && operation_delete != '' }">
				<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${webroot}/MonthlyStatementAction.do?method=deleteMonthlyStatement" class="delete"><span>删除</span></a></li> 
			</c:if>
			
			<c:if test="${operation_update != null && operation_update != '' }">
				<li><a class="edit" href="${webroot}/MonthlyStatementAction.do?method=toEditFirst&monthly_statement_id={sid_monthlyStatement}" mask="true" width="900"  height="350" rel="update_monthlyStatement" target="dialog" warn="请选择一条数据"><span>修改月度结算单</span></a></li>
			</c:if>
			
			<c:if test="${operation_read != null && operation_read != '' }">
				<li><a class="edit" href="${webroot}/MonthlyStatementAction.do?method=toView&monthly_statement_id={sid_monthlyStatement}" mask="true" width="900"  height="350" rel="view" target="dialog" warn="请选择一条数据"><span>查看明细</span></a></li>
			</c:if>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="136">
		<thead>
			<tr>
				<th width="30" >序号</th>
				<th width="20">选择</th>
				<th width="150" >项目名称</th>	
				<th width="120" >项目编号</th>
				<th width="80" >项目类型</th>
				<th width="80" >结算单类型</th>
				<th width="80" >结算月份</th>
				<th width="100" >结算金额</th>
				<th width="100" >发票总金额</th>
				<th width="100" >到款总金额</th>
				<th width="80" >核单人</th>
			</tr>
		</thead>
		
		<tbody>		
			<c:forEach var="monthlyStatement"  varStatus="status1" items="${list}">
			<tr target="sid_monthlyStatement" rel="${monthlyStatement.monthly_statement_id }">
				<td>${pageNum*pageSize-pageSize + status1.index + 1 }</td>
				<td>
						<a class="btnSelect" href="javascript:$.bringBack({monthly_statement_id:'${monthlyStatement.monthly_statement_id }',monthly_statement_name:'${monthlyStatement.project_name }(${monthlyStatement.statement_month })<spring:message code="statement.type.${monthlyStatement.statement_type }"/>'})" title="">选择</a>
				</td>
				<td>${monthlyStatement.project_name }</td>
				<td>${monthlyStatement.project_no }</td>
				<td><spring:message code="project.type.${monthlyStatement.project_type }"/></td>
				<td><spring:message code="statement.type.${monthlyStatement.statement_type }"/></td>
				<td>${monthlyStatement.statement_month }</td>
				<td align="right"><b><fmt:formatNumber value="${monthlyStatement.amount }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="right"><fmt:formatNumber value="${monthlyStatement.invoice_amount }" type="currency" pattern="###,###,##0.00"/></td>
				<td align="right"><fmt:formatNumber value="${monthlyStatement.receive_amount }" type="currency" pattern="###,###,##0.00"/></td>
				<td>${monthlyStatement.verify_username }</td>
			</tr>	
			</c:forEach>
		</tbody>
	</table>


	<!-- 分页 -->
	<c:import url="../_frag/pager/panelBarDialog.jsp"></c:import>

</div>
