<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>   

<form method="post" action="${webroot }/UserAction.do?method=${next_operation }" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
<div class="pageContent">
	
		<div class="pageFormContent" layoutH="56">			
			
			
		
			<input name="user_id" type="hidden" size="25" value="${user1.user_id }" />
				
				
			<p>
				<label>登录名：</label>
				${user1.user_loginname }
			</p>

			<p>
				<label>用户姓名：</label>
				${user1.user_name }
			</p>
														
			<p>
				<label>所在部门：</label>
				${user1.user_deptname }				 
			</p>
			
				
			
			<div class="divider"></div>			
			
			<h3 class="contentTitle">已分配角色</h3>
			<table class="list nowrap" width="100%" >
				<thead>
					<tr>					
						<th width="25" >序号</th>
						<th width="100" >角色名称</th>
						<th width="80">角色范围</th>	
						<th width="80">角色类型</th>	
					</tr>
				</thead>
				
				<tbody>						
					
					
					<c:forEach var="role"  varStatus="status1" items="${roles}">
					<tr>
						<td>${status1.index + 1 }</td>
						<td>${role.role_name }</td>
						<td><spring:message code="role.range.${role.data_range }" /> </td>	
						<td><spring:message code="role.type.${role.role_type }" /> </td>
					</tr>
					</c:forEach>

									
				</tbody>
			</table>
		

					
			
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
</div>
</form>
