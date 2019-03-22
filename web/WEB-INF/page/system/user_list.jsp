
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="${webroot }/UserAction.do?method=list">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${pageSize}" />
	<input type="hidden" name="user_name" value="${param.user_name}" />
	<input type="hidden" name="dept.dept_id" value="${dept_id}" />
	<input type="hidden" name="dept.dept_name" value="${dept_name}" />
</form>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${webroot }/UserAction.do?method=list" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>人员姓名：</label>
				<input type="text" name="user_name" value="${param.user_name}"/>
				<!-- 
					<a class="btnLook" href="${webroot }/UserAction.do?method=search" lookupGroup="user">查找</a>	
					<input name="user.user_id" value="" type="text" size="2"/>
					<input class="required" name="user.user_name" size="2"/>
				 -->
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
	<div class="panelBar">
		<ul class="toolBar">
			<c:if test="${operation_insert != null && operation_insert != '' }">
				<li><a class="add" href="${webroot}/UserAction.do?method=toEdit" mask="true" width="850" height="350" rel="add_user"  target="dialog"><span>添加用户</span></a></li>
			</c:if>
			
			<c:if test="${operation_delete != null && operation_delete != '' }">
				<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${webroot}/UserAction.do?method=delete" class="delete"><span>删除</span></a></li> 
			</c:if>
			
			<c:if test="${operation_update != null && operation_update != '' }">
				<li><a class="edit" href="${webroot}/UserAction.do?method=toEdit&user_id={sid_user}" mask="true" width="850"  height="350" rel="update_user" target="dialog" warn="请选择一条数据"><span>修改用户</span></a></li>
			</c:if>


			<c:if test="${operation_update != null && operation_update != '' }">
				<li><a class="edit" href="${webroot}/UserAction.do?method=toManageDept&user_id={sid_user}" mask="true" width="850"  height="350" rel="update_user" target="dialog" warn="请选择一条数据"><span>分配管理部门</span></a></li>
			</c:if>
			
			<c:if test="${operation_changepwd != null && operation_changepwd != '' }">
				<li><a class="edit" href="${webroot}/UserAction.do?method=toAdminUpdatePassword&user_id={sid_user}" mask="true" width="450"  height="200" rel="update_admin_password" target="dialog" warn="请选择一条数据"><span>修改密码</span></a></li>
			</c:if>
			
			<c:if test="${operation_read != null && operation_read != '' }">
				<li><a class="edit" href="${webroot}/UserAction.do?method=toView&user_id={sid_user}" mask="true" width="850"  height="350" rel="view" target="dialog" warn="请选择一条数据"><span>查看明细</span></a></li>
			</c:if>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
				<th width="25" >序号</th>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl" /></th>
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
				<td>${pageNum*pageSize-pageSize + status1.index + 1 }</td>
				<td>
					<c:if test="${user.delete_flag=='0' && user.user_id!='1'}">
						<input name="ids" value="${user.user_id }" type="checkbox" />
					</c:if>
				</td>
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
	<c:import url="../_frag/pager/panelBar.jsp"></c:import>

</div>
