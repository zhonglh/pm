<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">		
			
		<div class="pageFormContent" layoutH="56">	
			<table class="list nowrap" width="600" >
				<thead>
					<tr>
						<th width="180">导入说明</th>	
						<th width="100">工号</th>
						<th width="100">姓名</th>
						<th width="100">所在部门</th>
						<th  width="100">职位</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="staffcost"  varStatus="status1" items="${list}">
					<tr>
						<td>
							<c:if test="${staffcost.errorInfo != null && staffcost.errorInfo != '' }">
								<font color="red">${staffcost.errorInfo }</font>
							</c:if>
							<c:if test="${staffcost.errorInfo == null || staffcost.errorInfo == '' }">
								<font color="green">成功</font>
							</c:if>
							
						</td>						
						<td>${staffcost.staff_no }</td>
						<td>${staffcost.staff_name }</td>
						<td>${staffcost.dept_name }</td>
						<td>${staffcost.position_type_temp }</td>
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
