
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="${webroot }/DeptAction.do?method=list">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${pageSize}" />
	<input type="hidden" name="user_name" value="${param.dept_name}" />
</form>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${webroot }/DeptAction.do?method=list" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>部门名称：</label>
				<input type="text" name="dept_name" value="${param.dept_name}"/>
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
			
			<c:if test="${operation_insert != null && operation_insert != '' }">
				<li><a class="add" href="${webroot }/DeptAction.do?method=toEdit" mask="true" width="850" height="350" rel="add_dept"  target="dialog"><span>添加部门</span></a></li>
			</c:if>
			
			<c:if test="${operation_delete != null && operation_delete != '' }">
				<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${webroot }/DeptAction.do?method=deleteDept" class="delete"><span>删除</span></a></li> 
			</c:if>
			
			<c:if test="${operation_update != null && operation_update != '' }">
				<li><a class="edit" href="${webroot }/DeptAction.do?method=toEdit&dept_id={sid_dept}" mask="true" width="850"  height="350" rel="update_dept" target="dialog" warn="请选择一条数据"><span>修改部门</span></a></li>
			</c:if>
			
			<c:if test="${operation_read != null && operation_read != '' }">
				<li><a class="edit" href="${webroot }/DeptAction.do?method=toView&dept_id={sid_dept}" mask="true" width="850"  height="350" rel="view_dept" target="dialog" warn="请选择一条数据"><span>查看明细</span></a></li>
			</c:if>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
				<th width="30" >序号</th>
				<th width="25"><input type="checkbox" group="ids" class="checkboxCtrl" /></th>				
				<th width="200" >部门名称</th>
				<th width="80">部门编号</th>	
				<th width="100">部门领导</th>	
				<th width="80">是否统计</th>	
				<th width="120">(${curr_years })利润目标</th>	
				<th width="80">创建人</th>	
				<th width="140">创建时间</th>	
				<th >描述</th>				
			</tr>
		</thead>
		<tbody>
			
			<c:forEach var="dept"  varStatus="status1" items="${list}">
			<tr target="sid_dept" rel="${dept.dept_id }">
				
				<td>${pageNum*pageSize-pageSize + status1.index + 1 }</td>
				<td><input name="ids" value="${dept.dept_id }" type="checkbox" /></td>
				<td>${dept.dept_name }</td>
				<td>${dept.dept_no }</td>	
				<td>${dept.lead_name }</td>
				
				<td><spring:message code="boolean.${dept.statisticsed }" /></td>
				
				<td align="right"><b><fmt:formatNumber value="${dept.curr_profit_objective }" type="currency" pattern="###,###,##0.00"/></b></td>
						
				<td>${dept.build_username }</td>
				<td><fmt:formatDate value="${dept.build_time}" pattern="yyyy-MM-dd  HH:mm:ss" /></td>
				<td title="${dept.description }">${dept.description }</td>
										
			</tr>	
			</c:forEach>	
		</tbody>
	</table>
	
	<!-- 分页 -->
	<c:import url="../_frag/pager/panelBar.jsp"></c:import>	

	
</div>
