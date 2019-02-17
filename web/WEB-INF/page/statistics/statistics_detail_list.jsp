<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<form id="pagerForm" method="post" action="${webroot }/StatisticsDetailAction.do?method=${themethod}">

	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${pageSize}" />
	
	<input type="hidden" name="project_id" value="${param.project_id}" />
	<input type="hidden" name="month1" value="${param.month1}" />
	<input type="hidden" name="month2" value="${param.month2}" />
	
	
	<input type="hidden" name="type_id" value="${param.type_id}" />
	
	
</form>


<!-- 
<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);"  method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				
				<input type="hidden" name="project_id" value="${param.project_id}" />
			</li>

			<li>
				
				<input type="hidden" name="month1" value="${param.month1}" />
			</li>
			
			<li>
				
				<input type="hidden" name="month2" value="${param.month2}" />
			</li>

		</ul>
	
	</div>
	</form>
</div>
 -->
 
<div class="pageContent">

	<div class="panelBar">
		<ul class="toolBar">			
				<li><a class="icon" href="${webroot}/StatisticsDetailAction.do?method=${excelMethod}" target="dwzExport" targetType="dialog" title="确定要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>

	<table class="table" width="120%" layoutH="118">
		<thead>
			<tr>
				<th width="30">序号</th>
				<th width="200">项目名称</th>
				<th width="120">项目编号</th>
				<th width="80">账款类型</th>
				<th width="100">业务类型</th>
				<th width="80">金额</th>
				<th width="60">月份</th>
				<th width="140">实际(收支)日期</th>
				<th width="80">名称</th>
				<th width="100">其他</th>
				<th width="300">描述</th>
			</tr>
		</thead>
		<tbody>
			
			<c:forEach var="statisticsDetail"  varStatus="status1" items="${list}">
				<tr>
					<td>${pageNum*pageSize-pageSize + status1.index + 1 }</td>
					<td>${statisticsDetail.project_name }</td>
					<td>${statisticsDetail.project_no }</td>				
					<td align="center">${statisticsDetail.amount_type }</td>		
					<td>${statisticsDetail.business_type }</td>		
					<td nowarp align="right"><b><fmt:formatNumber value="${statisticsDetail.amount }" type="currency" pattern="###,###,##0.00"/></b></td>
					<td align="center">${statisticsDetail.use_month }</td>		
					<td align="center">
						<c:if test="${ statisticsDetail.business_type != '月度结算单' }">
						<fmt:formatDate value="${statisticsDetail.pay_date}" pattern="yyyy-MM-dd" />
						</c:if>
					</td>
					<td>${statisticsDetail.business_name }</td>		
					<td>${statisticsDetail.otherdesc }</td>		
					<td>${statisticsDetail.description }</td>		
				</tr>
			</c:forEach>

		</tbody>
	</table>
	
	
	<!-- 分页 -->
	<c:import url="../_frag/pager/panelBarDialog.jsp"></c:import>
	
</div>
