<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="${webroot }/UserAction.do?method=search">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${pageSize}" />
	<input type="hidden" name="user_name" value="${param.user_name}" />
	<input type="hidden" name="dept.dept_id" value="${dept_id}" />
	<input type="hidden" name="dept.dept_name" value="${dept_name}" />
</form>

<div class="pageHeader">
	<form onsubmit="return dialogSearch(this);" action="${webroot }/UserAction.do?method=search" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>人员姓名：</label>
				<input type="text" name="user_name" value="${param.user_name}"/>
			</li>
			<li>
				<label>所在部门：</label>
				<input name="dept.dept_id" class="text" type="hidden" size="2"  value="${dept_id }" />
				<input name="dept.dept_name" class="text" type="text" size="20"  value="${dept_name }" readonly="readonly" lookupPk="dept_id"
				suggestFields="dept_name" suggestUrl="${webroot }/DeptAction.do?method=lookup&use=search" lookupGroup="dept"/>				
			</li>
			<li>
				<div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div>
			</li>			
		</ul>
		
	</div>
	</form>
</div>
<div class="pageContent">

	<table class="table" width="100%" layoutH="114">
		<thead>
			<tr>
				<th width="40" >选择</th>
				<th width="25" >序号</th>
				<th width="150" >姓名</th>	
				<th width="120" >登陆名</th>
				<th width="120" >部门</th>
				<th width="120" >创建人</th>
				<th width="120" >创建时间</th>
			</tr>
		</thead>
		<tbody>
		
			<c:forEach var="user"  varStatus="status1" items="${list}">
			<tr target="sid_user" rel="${user.user_id }">				
				<td><a class="btnSelect" href="javascript:$.bringBack({user_id:'${user.user_id }',user_name:'${user.user_name }'})" title="">选择</a></td>
				<td>${pageNum*pageSize-pageSize + status1.index + 1 }</td>
				<td>${user.user_name }</td>
				<td>${user.user_loginname }</td>
				<td>${user.user_deptname }</td>
				<td>${user.build_username }</td>
				<td><fmt:formatDate value="${user.build_time}" pattern="yyyy-MM-dd  HH:mm:ss" /></td>
			</tr>	
			</c:forEach>
		</tbody>
	</table>


	<!-- 分页 -->
	<c:import url="../_frag/pager/panelBarDialog.jsp"></c:import>

</div>
