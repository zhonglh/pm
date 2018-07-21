<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<form id="pagerForm" method="post" action="${webroot }/StaffCostAction.do?method=lookup2">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${pageSize}" />
	<input type="hidden" name="ex_staff_id" value="${param.ex_staff_id}" />
	<input type="hidden" name="outsource_staff" value="${param.outsource_staff}" />
	<input type="hidden" name="staff_type" value="${param.staff_type}" />
	
	
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return dialogSearch(this);" action="${webroot }/StaffCostAction.do?method=lookup2" method="post">
	<div class="searchBar">	
		
		<ul class="searchContent">
		
		
			<li>
				<label>人员工号：</label>
				<input type="text" name="staff_no" value="${staffCost.staff_no }"/>
			</li>
			
			<li>
				<label>人员名称：</label>
				<input type="text" name="staff_name" value="${staffCost.staff_name }"/>
			</li>
			

			
			<li>
				<div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div>
			</li>
		</ul>
	</div>
	</form>
</div>
<div class="pageContent">
	<table class="table" width="250" layoutH="102">
		<thead>
			<tr>
				<th width="40">选择</th>
				<th width="60">员工工号</th>
				<th width="80">员工名称</th>
			</tr>
		</thead>
		<tbody>
		
		
		
			<c:forEach var="staffcost"  varStatus="status1" items="${list}">
			<tr target="staff_id" rel="${staffcost.staff_id }"  >
				<td>
					<c:if test="${staffcost.delete_flag == '0' }"></c:if>
					<a class="btnSelect" href="javascript:$.bringBack({staff_id:'${staffcost.staff_id }',staff_name:'${staffcost.staff_name }',staff_no:'${staffcost.staff_no }'})" 
					title="">选择</a>
					
				</td>
				<td>${staffcost.staff_no }</td>
				<td>${staffcost.staff_name }</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<!-- 分页 -->
	<c:import url="../_frag/pager/panelBarDialog.jsp"></c:import>

</div>
