<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<form id="pagerForm" method="post" action="${webroot }/StaffCostJoinStatisticsAction.do?method=list">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${pageSize}" />
</form>


<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${webroot }/StaffCostJoinStatisticsAction.do?method=list" method="post">
	<div class="searchBar">
		<ul class="searchContent">
		
			<li>
				<label>所在部门：</label>
				<input name="dept.dept_id" class="text" type="hidden" size="2"  value="${staffCostStatistics1.dept_id }" />
				<input name="dept.dept_name" class="text" type="text" size="20"  value="${staffCostStatistics1.dept_name }" readonly="readonly" lookupPk="dept_id"
				suggestFields="dept_name" suggestUrl="${webroot }/DeptAction.do?method=lookup&use=search" lookupGroup="dept"/>				
			
			</li>
		

			<li>
				<label>项目名称：</label>
				<input type="text" name="project_name" value="${param.project_name }"/>	
			</li>
			
			<li>
				<label>员工名称：</label>
				<input type="text" name="staff_name" value="${param.staff_name }"/>
			</li>
		</ul>
		<ul class="searchContent">		

			<li>				
				<label>在职状态：</label>
				<select name="delete_flag" style="width:153px">
					<option value="">全部</option>
					<option value="0" <c:if test="${param.delete_flag == '0' }">selected</c:if>>在职</option>
					<option value="1" <c:if test="${param.delete_flag == '1' }">selected</c:if>>离职</option>					
				</select>		
			</li>	


			<li>
				<label>招聘专员名称：</label>
				<input type="text" name="recruitment_username" value="${param.recruitment_username }"/>
			</li>
			
			<li>
				<label>时间段：</label>
				<input name="date1"  class="date" autocomplete="off"  type="text" size="7" value="${param.date1 }" />
				<input name="date2"  class="date" autocomplete="off"  type="text" size="7" value="${param.date2 }" />
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
		
			<li><a class="icon" href="${webroot}/StaffCostJoinStatisticsAction.do?method=export" target="dwzExport" targetType="navTab" title="确定要导出这些记录吗?"><span>导出EXCEL</span></a></li>
					
		</ul>
	</div>
	<table class="table" width="1300" layoutH="136">
		<thead>
			<tr>
				<th  width="70">员工工号</th>
				<th width="70">员工名称</th>
				<th width="180">项目名称</th>
				<th width="100">部门</th>
				<th width="120">招聘专员名称</th>
				<th width="110">加入项目时间</th>	
				<th width="100">技术费用</th>				
				<th width="100">客户所在部门</th>
				<th width="300">说明</th>		
			</tr>
		</thead>
		<tbody>
		
			<c:forEach var="staffcost"  varStatus="status1" items="${list}">
			<tr target="staff_id" rel="${staffcost.staff_id }"  >
				
				<td>${staffcost.staff_no }</td>
				<td>${staffcost.staff_name }</td>
				<td>${staffcost.project_name }</td>
				<td>${staffcost.dept_name }</td>
				<td>${staffcost.recruitment_username }</td>
					
				<td><fmt:formatDate value="${staffcost.join_project_datetime }" pattern="yyyy-MM-dd"/></td>	
				<td align="right"><fmt:formatNumber value="${staffcost.technical_cost }" type="currency" pattern="###,###,##0.00"/></td>				
				
				<td>${staffcost.client_dept }</td>
				<td title="${staffcost.description }">${staffcost.description }</td>
				
				
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<!-- 分页 -->
	<c:import url="../_frag/pager/panelBar.jsp"></c:import>

</div>
