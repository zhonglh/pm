<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<form id="pagerForm" method="post" action="${webroot }/ItemsAction.do?method=lookupClient">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="client" value="${pageSize}" />
	<input type="hidden" name="client_name" value="${param.client_name}" />
	<input type="hidden" name="client_no" value="${param.client_no}" />
	
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return dialogSearch(this);" action="${webroot }/ItemsAction.do?method=lookupClient" method="post">
	<div class="searchBar">
		<ul class="searchContent">
		
			<li>
				<label>客户编号：</label>
				<input type="text" id="client_name" name="client_no" value="${param.client_no}" />
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
	
	<table class="table" width="800" layoutH="92">
		<thead>
			<tr>
				<th width="25">选择</th>
				<th width="40">序号</th>
				<th width="150">客户编号</th>
				<th width="380">客户名称</th>
				<th width="100">创建人</th>
				<th width="120">创建时间</th>
						
			</tr>
		</thead>
		<tbody>
		
			<c:forEach var="client"  varStatus="status1" items="${list}">
			<tr target="client_id" rel="${client.client_id }">				
				<td><a class="btnSelect" href="javascript:$.bringBack({client_id:'${client.client_id }',client_name:'${client.client_name }'});" title="">选择</a></td>
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
