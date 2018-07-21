<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>   

<form method="post" action="${webroot }/UserAction.do?method=${next_operation }" class="pageForm required-validate" onsubmit="return validateCheckCallback(this, '${webroot }/UserAction.do?method=isExist',dialogAjaxDone);">
<div class="pageContent">
	
		<div class="pageFormContent" layoutH="56">			
		
			<input name="user_id" type="hidden" size="25" value="${user1.user_id }" />

			<p>
				<label>登录名：</label>
				<input name="user_loginname" class="alphanumeric required" type="text" size="25" minlength="2" maxlength="20" value="${user1.user_loginname }" />
			</p>
			
			
			<p>
				<label>对应(行政)人员：</label>
				<input type="hidden" size="2" id="staff_staff_id" name="staff.staff_id" value="${user1.staff_id }"/>
				<input type="hidden" size="2" id="staff_staff_type" name="staff.staff_type" value="${user1.staff_type }"/>
				<input id="staff_staff_name" name="staff.staff_name" id="staff_staff_name" class="text" type="text" size="22"  value='${(user1.staff_id == null || empty user1.staff_id)?"":user1.user_name }' readonly="readonly" />
				<a class="btnLook" href="${webroot }/OtherStaffAction.do?method=lookup" lookupGroup="staff" lookupPk="staff_id" width="650">选择</a>
				<a class="btnDelSelf" onclick="cancelText('staff_staff_id','staff_staff_type','staff_staff_name','staff_dept_id','staff_dept_name');cancelReadonly('staff_user_name');return false;"><span>删除</span></a>
			</p>			

			<p>
				<label>用户姓名：</label>
				<input id="staff_user_name" id="staff_user_name" name="staff.user_name" class="text required" 
				type="text" size="25"  maxlength="30" value="${user1.user_name }" />
			</p>														
			<p>
				<label>所在部门：</label>
				<input id="staff_dept_id" name="staff.dept_id" class="text" type="hidden" size="25"  value="${user1.user_deptid }" />
				<input id="staff_dept_name" name="staff.dept_name" class="text" type="text" size="25"  value="${user1.user_deptname }" readonly="readonly"/>
			</p>
			
			<c:if test="${next_operation == 'add'}">
			<p>
				<label>密码：</label>
				<input id="user_password" type="password" name="user_password" size="25" class="required alphanumeric" minlength="6" maxlength="30" alt="字母、数字、下划线 6-20位"/>
			</p>
			

			<p>
				<label>密码确认：</label>
					<input type="password" name="passwordconfig" size="25" class="required" equalto="#user_password"/>
			</p>			
			</c:if>
			
				
			
			<div class="divider"></div>			
			
			<h3 class="contentTitle">分配角色</h3>
			<table class="list nowrap" width="100%" >
				<thead>
					<tr>					
						<th width="25" >序号</th>
						<th width="22"><input type="checkbox" group="role_id" class="checkboxCtrl"></th>
						<th width="100" >角色名称</th>
						<th width="80">角色范围</th>	
						<th width="80">角色类型</th>	
					</tr>
				</thead>
				
				<tbody>						
					
					
					<c:forEach var="role"  varStatus="status1" items="${roles}">
					<tr>
						<td>${status1.index + 1 }</td>
						<td><input name="role_id" value="${role.role_id }" type="checkbox" <c:if test="${role.selected }">checked="checked"</c:if>></td>
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
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
</div>
</form>
