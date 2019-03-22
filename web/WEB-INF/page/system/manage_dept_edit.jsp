<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>   

<form method="post" action="${webroot }/UserAction.do?method=saveManageDept" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
<div class="pageContent">
	
		<div class="pageFormContent" layoutH="56">			
		
			<input name="user_id" type="hidden" size="25" value="${user1.user_id }" />

			
			<h3 class="contentTitle">分配管理的部门</h3>
			<table class="list nowrap" width="100%" >
				<thead>
					<tr>					
						<th width="25" >序号</th>
						<th width="22"><input type="checkbox" group="dept_id" class="checkboxCtrl"></th>
						<th width="200" >部门名称</th>
						<th width="80">部门编号</th>	
						<th >部门说明</th>	
					</tr>
				</thead>
				
				<tbody>						
					
					
					<c:forEach var="dept"  varStatus="status1" items="${depts}">
					<tr>
						<td>${status1.index + 1 }</td>
						<td><input name="dept_id" value="${dept.dept_id }" type="checkbox" <c:if test="${dept.selected }">checked="checked"</c:if>></td>
						<td>${dept.dept_name }</td>
						<td>${dept.dept_no } </td>
						<td>${dept.description } </td>
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
