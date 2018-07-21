<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="${webroot }/RoleAction.do?method=list">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="100000" />
	<input type="hidden" name="role_name" value="${param.role_name}" />
</form>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${webroot }/RoleAction.do?method=list" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>角色名称：</label>
				<input type="text" name="role_name" value="${param.role_name}"/>
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
				<li><a class="add" href="${webroot }/RoleAction.do?method=toEdit" mask="true" width="850" height="350" rel="add_role"  target="dialog"><span>添加角色</span></a></li>
			</c:if>
			
			<c:if test="${operation_delete != null && operation_delete != '' }">
				<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${webroot }/RoleAction.do?method=deleteRole" class="delete"><span>删除</span></a></li> 
			</c:if>
			
			<c:if test="${operation_update != null && operation_update != '' }">
				<li><a class="edit" href="${webroot }/RoleAction.do?method=toEdit&role_id={sid_role}" mask="true" width="850"  height="350" rel="update_role" target="dialog" warn="请选择一条数据"><span>修改角色</span></a></li>
			</c:if>
			
			<c:if test="${operation_read != null && operation_read != '' }">
				<li><a class="edit" href="${webroot }/RoleAction.do?method=toView&role_id={sid_role}" mask="true" width="850"  height="350" rel="view_role" target="dialog" warn="请选择一条数据"><span>查看明细</span></a></li>
			</c:if>			
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
				<th width="30" >序号</th>
				<th width="25"><input type="checkbox" group="ids" class="checkboxCtrl" /></th>				
				<th width="150" >角色名称</th>
				<th width="80">角色范围</th>	
				<th width="100">角色类型</th>	
				<th width="80">创建人</th>	
				<th width="80">创建时间</th>				
			</tr>
		</thead>
		<tbody>
			
			<c:forEach var="role"  varStatus="status1" items="${list}">
			<tr target="sid_role" rel="${role.role_id }">
				
				<td>${status1.index + 1 }</td>
				<td>
					<c:if test="${role.role_type == '2' }">
						<input name="ids" value="${role.role_id }" type="checkbox" />
					</c:if>
				</td>
				<td>${role.role_name }</td>
				<td><spring:message code="role.range.${role.data_range }" /> </td>	
				<td><spring:message code="role.type.${role.role_type }" /> </td>
				
				<td>${role.build_username }</td>
				<td><fmt:formatDate value="${role.build_time}" pattern="yyyy-MM-dd  HH:mm:ss" /></td>
										
			</tr>	
			</c:forEach>	
		</tbody>
	</table>
	

	
</div>
