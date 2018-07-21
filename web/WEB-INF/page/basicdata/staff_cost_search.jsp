<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<form id="pagerForm" method="post" action="${webroot }/StaffCostAction.do?method=lookup">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${pageSize}" />
	
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return dialogSearch(this);" action="${webroot }/StaffCostAction.do?method=lookup" method="post">
	<div class="searchBar">	
		<input type="hidden" name="in_project" value="${param.in_project}" />
		<input type="hidden" name="project_type" value="${param.project_type}" />
		<input type="hidden" name="ex_staff_id" value="${param.ex_staff_id}" />
		
		<ul class="searchContent">
			<li>
				<label>人员名称：</label>
				<input type="text" name="staff_name" value="${staffCost.staff_name }"/>
			</li>
			
			<li>
				<label>项目名称：</label>
				<input type="text" name="project_name" value="${staffCost.project_name }"/>
			</li>

			<li>
				<label>在职状态：</label>
				<select name="delete_flag">
					<option value="">全部</option>
					<option value="0" <c:if test="${staffCost.delete_flag == '0' }">selected</c:if>>在职</option>
					<option value="1" <c:if test="${staffCost.delete_flag == '1' }">selected</c:if>>离职</option>
				</select>
			</li>			
			
			
				<div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div>
			
		</ul>
	</div>
	</form>
</div>
<div class="pageContent">
	<table class="table" width="950" layoutH="102">
		<thead>
			<tr>
				<th width="40">选择</th>
				<th width="60">员工工号</th>
				<th width="80">员工名称</th>
				<th width="110">入职时间</th>
				<th width="90">人员总成本</th>
				<th width="100">客户最新报价</th>
				<th width="70">基本工资</th>				
				<th width="70">岗位工资</th>
				<th width="70">绩效津贴</th>
				<th width="70">差价</th>
				<th width="70">外协人员</th>		
			</tr>
		</thead>
		<tbody>
		
		
		
			<c:forEach var="staffcost"  varStatus="status1" items="${list}">
			<tr target="staff_id" rel="${staffcost.staff_id }"  >
				<td>
					<c:if test="${staffcost.delete_flag == '0' }"></c:if>
					<a class="btnSelect" href="javascript:$.bringBack({staff_id:'${staffcost.staff_id }',staff_name:'${staffcost.staff_name }',staff_no:'${staffcost.staff_no }',
					technical_cost:'<c:if test="${staffcost.technical_cost == 0 }">${staffcost.qustomerquotes }</c:if><c:if test="${staffcost.technical_cost != 0 }">${staffcost.technical_cost }</c:if>' })" 
					title="">选择</a>
					
				</td>
				<td>${staffcost.staff_no }</td>
				<td>${staffcost.staff_name }</td>
				<td><fmt:formatDate value="${staffcost.join_datetime }" pattern="yyyy-MM-dd"/></td>
				<td align="right"><fmt:formatNumber value="${staffcost.totalcost }" type="currency" pattern="###,###,##0.00"/></td>
				<td align="right"><fmt:formatNumber value="${staffcost.qustomerquotes }" type="currency" pattern="###,###,##0.00"/></td>
				<td align="right"><fmt:formatNumber value="${staffcost.basic_salary }" type="currency" pattern="###,###,##0.00"/></td>
				<td align="right"><fmt:formatNumber value="${staffcost.post_salary }" type="currency" pattern="###,###,##0.00"/></td>
				<td align="right"><fmt:formatNumber value="${staffcost.performance_allowances }" type="currency" pattern="###,###,##0.00"/></td>			
				<td align="right" <c:if test="${staffcost.difference < staff_costs_threshold }">style="background:red"</c:if>>
					<fmt:formatNumber value="${staffcost.difference }" type="currency" pattern="###,###,##0.00"/>
				</td>
				<td><spring:message code="boolean.${staffcost.outsource_staff }"/></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<!-- 分页 -->
	<c:import url="../_frag/pager/panelBarDialog.jsp"></c:import>

</div>
