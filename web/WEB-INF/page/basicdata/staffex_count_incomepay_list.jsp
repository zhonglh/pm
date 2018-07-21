<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="${webroot }/StaffCountChartsAction.do?method=list">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${pageSize}" />
</form>
<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${webroot }/StaffCountChartsAction.do?method=list" method="post">
	<div class="searchBar">		
		<ul class="searchContent">		
			
			
			<li>
				<label>员工工号：</label>
				<input type="text" name="staff_no" value="${param.staff_no }"/>
			</li>
			<li>
				<label>员工名称：</label>
				<input type="text" name="staff_name" value="${param.staff_name }"/>
			</li>

			
			<li>
				<div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div>
			</li>			
			
		</ul>		
		
			
	</div>	
	
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
		
			<li><a class="icon" href="${webroot}/StaffCountChartsAction.do?method=toStaffChildChart" mask="true" width="950"  height="600" rel="ztree" target="dialog" ><span>下线统计</span></a></li>
			<li><a class="icon" href="${webroot}/StaffCountChartsAction.do?method=toStaffSalaryCatalogChart" mask="true" width="950"  height="600" rel="ztree" target="dialog" ><span>收支统计</span></a></li>

		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
				<th width="100">员工编号</th>	
				<th width="100">员工名称</th>	
				<th width="100">收入</th>
				<th width="100">支出</th>
				<th width="100">个税</th>
				<th width="100">实收</th>
				<th width="100">总下线人数</th>
				<th width="120">核心下线人数</th>
				
			</tr>
		</thead>
		<tbody>
			<c:forEach var="staffCountVO"  varStatus="status1" items="${list}">
			<tr target="sid" rel="${staffCountVO.staff_id }">
			
			
				<td>${staffCountVO.staff_no }</td>
				<td>${staffCountVO.staff_name }</td>
				
				<td align="right"><b><fmt:formatNumber value="${staffCountVO.income_ }" 		type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="right"><b><fmt:formatNumber value="${staffCountVO.pay_ }" 			type="currency" pattern="###,###,##0.00"/></b></td>								
				<td align="right"><b><fmt:formatNumber value="${staffCountVO.tax }" 			type="currency" pattern="###,###,##0.00"/></b></td>				
				<td align="right"><b><fmt:formatNumber value="${staffCountVO.actual_salary }" 	type="currency" pattern="###,###,##0.00"/></b></td>
				<td align="right"><b><fmt:formatNumber value="${staffCountVO.allChilds }"   	pattern="###,###,##0"/></b></td>
				<td align="right"><b><fmt:formatNumber value="${staffCountVO.coreChilds }"   	pattern="###,###,##0"/></b></td>
				
				
			</tr>
			</c:forEach>
		</tbody>
	</table>	
	<!-- 分页 -->
	<c:import url="../_frag/pager/panelBar.jsp"></c:import>
</div>	
