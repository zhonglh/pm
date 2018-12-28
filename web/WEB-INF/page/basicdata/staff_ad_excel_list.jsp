<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">		
			
		<div class="pageFormContent" layoutH="56">	
			<table class="list nowrap" width="1100" >
				<thead>
					<tr>
						<th width="300">导入说明</th>	
						<th  width="80">员工工号</th>
						<th width="80">员工名称</th>
						<th width="100">子女教育</th>
						<th width="100">继续教育</th>
						<th width="100">住房贷款利息</th>
						<th width="100">住房租金</th>
						<th width="100">赡养老人</th>
						
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
						<td>${staffcost.children_education }</td>
						<td>${staffcost.continuing_education }</td>
						<td>${staffcost.housing_loans }</td>
						<td>${staffcost.housing_rent }</td>
						<td>${staffcost.support_elderly }</td>
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
