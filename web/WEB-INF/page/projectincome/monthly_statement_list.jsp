<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="${webroot }/MonthlyStatementAction.do?method=list">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${pageSize}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${webroot }/MonthlyStatementAction.do?method=list" method="post">
	<div class="searchBar">
		<ul class="searchContent">
		
			<li>
				<label>所在部门：</label>
				<input name="dept.dept_id" class="text" type="hidden" size="2"  value="${searchMonthlyStatement1.dept_id }" />
				<input name="dept.dept_name" class="text" type="text" value="${searchMonthlyStatement1.dept_name }" readonly="readonly" lookupPk="dept_id"
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
				<label>结算单月份：</label>
				<input  type="text" class="digits date month" size="7"  autocomplete="off" maxlength="6" minlength="6"  format="yyyyMM" name="statement_month1" value="${param.statement_month1}"/>
				<input  type="text" class="digits date month" size="7"  autocomplete="off" maxlength="6" minlength="6"  format="yyyyMM" name="statement_month2" value="${param.statement_month2}"/>
			</li>
			
			<li>
				<label>销售负责人：</label>
				<select name="sales_userid" style="width:153px">
				<option value="">全部</option>
					<c:forEach var="sales"  varStatus="status" items="${saless}">	
						<option value="${sales.staff_id }" <c:if test="${sales.staff_id == param.sales_userid }">selected</c:if>>${sales.staff_name }</option>
					</c:forEach>
				</select>
			</li>



			<li>
				<label>结算单类型：</label>
				<select id="statement_type" name="statement_type" style="width:153px">
					<option value="">全部</option>
					<option value="1" <c:if test="${param.statement_type == '1' }">selected</c:if> ><spring:message code="statement.type.1"/></option>
					<option value="2" <c:if test="${param.statement_type == '2' }">selected</c:if> ><spring:message code="statement.type.2"/></option>
					<option value="3" <c:if test="${param.statement_type == '3' }">selected</c:if> ><spring:message code="statement.type.3"/></option>
					<option value="4" <c:if test="${param.statement_type == '4' }">selected</c:if> ><spring:message code="statement.type.4"/></option>
				</select>

			</li>

			<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>

		</ul>		
		
		<!-- 
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
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
				<li><a class="add" href="${webroot}/MonthlyStatementAction.do?method=toEditFirst" mask="true" width="900" height="350" rel="add_monthlyStatement"  target="dialog"><span>添加月度结算单</span></a></li>
			</c:if>
			
			<c:if test="${operation_delete != null && operation_delete != '' }">
				<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${webroot}/MonthlyStatementAction.do?method=deleteMonthlyStatement" class="delete"><span>删除</span></a></li> 
			</c:if>
			
			<c:if test="${operation_update != null && operation_update != '' }">
				<li><a class="edit" href="${webroot}/MonthlyStatementAction.do?method=toEditFirst&monthly_statement_id={sid_monthlyStatement}" mask="true" width="900"  height="350" rel="update_monthlyStatement" target="dialog" warn="请选择一条数据"><span>修改月度结算单</span></a></li>
			</c:if>
			
			<c:if test="${operation_read != null && operation_read != '' }">
				<li><a class="edit" href="${webroot}/MonthlyStatementAction.do?method=toView&monthly_statement_id={sid_monthlyStatement}" mask="true" width="1160"  height="550" rel="view" target="dialog" ><span>查看明细</span></a></li>
			</c:if>
			
			
			<c:if test="${operation_check != null && operation_check != '' }">
				<li><a title="确定已经批量核实了这些单据吗?" target="selectedTodo" rel="ids" href="${webroot}/MonthlyStatementAction.do?method=batchVerifyMonthlyStatement" class="delete"><span>批量核单</span></a></li> 
			</c:if>	
			
			<li class="line">line</li>
			
				
			<c:if test="${operation_read != null && operation_read != '' }">
			<c:if test="${ totalRows <= 15000}">
				<li><a class="icon" href="${webroot}/MonthlyStatementAction.do?method=export" target="dwzExport" targetType="navTab" title="确定要导出这些记录吗?"><span>导出EXCEL</span></a></li>
			</c:if>	
			</c:if>	
			
			
			<c:if test="${operation_read != null && operation_read != '' }">
			<c:if test="${ totalRows <= 15000}">
				<li><a class="icon" href="${webroot}/MonthlyStatementAction.do?method=exportDetail" target="dwzExport" targetType="navTab" title="确定要导出这些记录吗?"><span>导出明细EXCEL</span></a></li>
			</c:if>
			</c:if>		
			
			<li class="line">line</li>
			
			<c:if test="${operation_read != null && operation_read != '' }">
				<li><a class="icon" href="${webroot}/MonthlyHisAction.do?method=list&monthly_statement_id={sid_monthlyStatement}" mask="true" width="1200"  height="600" rel="view" target="dialog" warn="请选择一条数据"><span>历史记录</span></a></li>
			</c:if>		
			
		</ul>
	</div>
	<table class="table" width="100%" layoutH="136">
		<thead>
			<tr>
				<th width="30" >序号</th>
				<th width="20"><input type="checkbox" group="ids" class="checkboxCtrl" /></th>
				<th width="150" >项目名称</th>	
				<th width="120" >项目编号</th>
				<th width="80" >项目类型</th>
				<th width="80" >结算单类型</th>
				<th width="80" >结算月份</th>
				<th width="100" >结算金额</th>
				<th width="100" >发票总金额</th>
				<th width="100" >到款总金额</th>				
				<th width="120" >未开发票金额</th>
				<th width="140" >结算单未回款金额</th>
				<th width="140" >开发票未回款金额</th>
				<th width="80" >制单人</th>
				<th width="80" >核单人</th>
				<th width="120" >申请状态</th>
			</tr>
		</thead>
		
		<tbody>		
			<c:forEach var="monthlyStatement"  varStatus="status1" items="${list}">
			<tr target="sid_monthlyStatement" rel="${monthlyStatement.monthly_statement_id }">
				<td>${pageNum*pageSize-pageSize + status1.index + 1 }</td>
				<td>
						<c:if test="${monthlyStatement.verify_userid==null || monthlyStatement.verify_userid=='' }">
						<input name="ids" value="${monthlyStatement.monthly_statement_id }" type="checkbox" />
					</c:if>
				</td>
				<td>${monthlyStatement.project_name }</td>
				<td>${monthlyStatement.project_no }</td>
				<td><spring:message code="project.type.${monthlyStatement.project_type }"/></td>
				<td><spring:message code="statement.type.${monthlyStatement.statement_type }"/></td>
				<td>${monthlyStatement.statement_month }</td>
				<td align="right"><b><fmt:formatNumber value="${monthlyStatement.amount }" type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="right"><fmt:formatNumber value="${monthlyStatement.invoice_amount }" type="currency" pattern="###,###,##0.00"/></td>
				<td align="right"><fmt:formatNumber value="${monthlyStatement.receive_amount }" type="currency" pattern="###,###,##0.00"/></td>
				<td align="right"><fmt:formatNumber value="${monthlyStatement.not_open_invoice_amount }" type="currency" pattern="###,###,##0.00"/></td>
				<td align="right"><fmt:formatNumber value="${monthlyStatement.not_receive_amount }" type="currency" pattern="###,###,##0.00"/></td>
				<td align="right"><fmt:formatNumber value="${monthlyStatement.invoice_not_receive_amount }" type="currency" pattern="###,###,##0.00"/></td>
				<td>${monthlyStatement.build_username }</td>
				<td>${monthlyStatement.verify_username }</td>
				<td>				
					<c:if test="${monthlyStatement.need_approve!=null && monthlyStatement.need_approve=='1' }">
						<font color="red">已申请取消核单</font>
					</c:if>
				</td>
			</tr>	
			</c:forEach>
		</tbody>
	</table>


	<!-- 分页 -->
	<c:import url="../_frag/pager/panelBar_totalAmount_any.jsp"></c:import>

</div>
