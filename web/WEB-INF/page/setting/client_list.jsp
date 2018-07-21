<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<form id="pagerForm" method="post" action="${webroot }/ItemsAction.do?method=clientList">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="client" value="${pageSize}" />
	
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${webroot }/ItemsAction.do?method=clientList" method="post">
	<div class="searchBar">
		<ul class="searchContent">
		
			<li>
				<label>客户编号：</label>
				<input type="text" id="client_no" name="client_no" value="${param.client_no}" />
			</li>		

			<li>
				<label>客户名称：</label>
				<input type="text" id="client_name" name="client_name" value="${param.client_name}" />
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
				<li><a class="add" href="${webroot }/ItemsAction.do?method=toEditClient" mask="true" width="420" height="200" rel="client_add" target="dialog"><span>添加客户</span></a></li>
			</c:if>
			
			<c:if test="${operation_delete != null && operation_delete != '' }">						
				<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${webroot }/ItemsAction.do?method=deleteClient" class="delete"><span>删除</span></a></li>
			</c:if>
			
			<c:if test="${operation_update != null && operation_update != '' }">
				<li><a class="edit" href="${webroot }/ItemsAction.do?method=toEditClient&client_id={client_id}" rel="client_edit" width="420" height="200" mask="true" target="dialog" warn="请选择一条数据"><span>修改客户</span></a></li>
			</c:if>
		</ul>
	</div>
	<table class="table" width="800" layoutH="92">
		<thead>
			<tr>
				<th width="25"><input type="checkbox" group="ids" class="checkboxCtrl" /></th>
				<th width="40">序号</th>
				<th width="140">客户编号</th>
				<th width="260">客户名称</th>
				<th width="100">创建人</th>
				<th width="120">创建时间</th>
						
			</tr>
		</thead>
		<tbody>
		
			<c:forEach var="client"  varStatus="status1" items="${list}">
			<tr target="client_id" rel="${client.client_id }">				
				<td>
					<c:if test="${client.delete_flag == '0' }">
					<input name="ids" value="${client.client_id }" type="checkbox" />
					</c:if>
				</td>
				<td>${pageNum*pageSize-pageSize + status1.index + 1 }</td>
				<td>${client.client_no }</td>
				<td title="${client.client_name }">${client.client_name }</td>
				<td>${client.build_username }</td>
				<td><fmt:formatDate value="${client.build_datetime }" pattern="yyyy-MM-dd"/></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>


</div>
