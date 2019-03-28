<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<form id="pagerForm" method="post" action="${webroot }/ProjectSalesStatisticsAction.do?method=salesUserList">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${pageSize}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabStaisticsSearch(this,sales_urls);" action="${webroot }/ProjectSalesStatisticsAction.do?method=salesUserList" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>销售负责人：</label>
				<input type="text" name="sales_username" value="${param.sales_username}"/>
			</li>
			<li>
				<label>项目管理人：</label>
				<input type="text" name="manage_username" value="${param.manage_username}"/>
			</li>	
			
			<li>
				<label>时间范围：</label>
				<input type="text" class="digits date month"  autocomplete="off"   maxlength="6" minlength="6"  format="yyyyMM" name="month1" size="7" value="${param.month1}"/>
				<input type="text" class="digits date month"  autocomplete="off"   maxlength="6" minlength="6"  format="yyyyMM" name="month2" size="7" value="${param.month2}"/>
				
				
			</li>										
		</ul>
		<ul class="searchContent">
			<li>
				<label>销售额归属部门：</label>
				<input type="text" name="sales_amount_deptname" value="${param.sales_amount_deptname}"/>
			</li>
			<li>
				<label>执行额归属部门：</label>
				<input type="text" name="exec_amount_deptname" value="${param.exec_amount_deptname}"/>
			</li>				

			<li>
				<label>项目归属部门：</label>
				<input type="text" name="deptname" value="${param.deptname}"/>
			</li>				
			
							
		</ul>		
		
		
		
		<ul class="searchContent">
			
			<li>
				<label>项目名称：</label>
				<input type="text" name="project_name" value="${param.project_name}"/>
			</li>	
		
			<li>
				<label>统计口径：</label>
				<select id="statisticsby" name="statisticsby" style="width:153px">
					<option value="1" <c:if test="${param.statisticsby == '1' }">selected="selected"</c:if> >按项目</option>
					<option value="2" <c:if test="${param.statisticsby == '2' }">selected="selected"</c:if> >按销售负责人</option>
					<option value="3" <c:if test="${param.statisticsby == '3' }">selected="selected"</c:if> >按项目管理人</option>
					<option value="4" <c:if test="${param.statisticsby == '4' }">selected="selected"</c:if> >按项目信息来源</option>
					<option value="5" <c:if test="${param.statisticsby == '5' }">selected="selected"</c:if> >按项目客户</option>
					<option value="6" <c:if test="${param.statisticsby == '6' }">selected="selected"</c:if> >按销售额归属部门</option>
					<option value="7" <c:if test="${param.statisticsby == '7' }">selected="selected"</c:if> >按执行额归属部门</option>
					<option value="8" <c:if test="${param.statisticsby == '8' }">selected="selected"</c:if> >按项目归属部门</option>
					<option value="9" <c:if test="${param.statisticsby == '9' }">selected="selected"</c:if> >按年度</option>
					<option value="10" <c:if test="${param.statisticsby == '10' }">selected="selected"</c:if> >按季度</option>		
					<option value="11" <c:if test="${param.statisticsby == '11' }">selected="selected"</c:if> >按全部</option>					
				</select>
			</li>					

			<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>						
		</u>


		
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="icon" href="${webroot }/ProjectSalesStatisticsAction.do?method=exportSalesUserList" target="dwzExport" targetType="navTab" title="实要导出所有记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="300" layoutH="162">
		<thead>
			<tr>
				<th width="25">序号</th>
				<th width="100">销售负责人</th>
				<th width="120">销售额</th>	
			</tr>
		</thead>
		<tbody>
		
			<c:forEach var="statistics"  varStatus="status1" items="${list}">
			<tr>
				<td>${pageNum*pageSize-pageSize + status1.index + 1 }</td>
				<td>${statistics.sales_username }</td>		
				<td nowarp align="right"><b><c:if test="${statistics.statistics_amount<0 }"><font color="red"></c:if><fmt:formatNumber value="${statistics.statistics_amount }" type="currency" pattern="###,###,##0.00"/><c:if test="${statistics.statistics_amount<0 }"></font></c:if></b></td>						
			</tr>
			</c:forEach>
		</tbody>
	</table>


	<!-- 分页 -->
	<c:import url="../_frag/pager/panelBar_totalAmount.jsp"></c:import>
</div>
