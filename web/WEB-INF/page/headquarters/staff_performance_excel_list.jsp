<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<div class="pageContent">
		<div class="pageFormContent" layoutH="56">	
			<table class="list nowrap" width="1200" >
				<thead>
					<tr>
						<th width="300">导入说明</th>
						<th width="70">月份</th>
						<th width="80">工号</th>
						<th width="80">姓名</th>	
						<th width="70">绩效工资</th>	
					</tr>
				</thead>
				<tbody>
					<c:forEach var="StaffPerformances"  varStatus="status1" items="${list}">
					<tr>
						<td>
							<c:if test="${staffPerformance.errorInfo != null && staffPerformance.errorInfo != '' }">
								<font color="red">${staffPerformance.errorInfo }</font>
							</c:if>
							<c:if test="${staffPerformance.errorInfo == null || staffPerformance.errorInfo == '' }">
								<font color="green">成功</font>
							</c:if>
						</td>
						<td>${staffPerformance.the_month }</td>
						<td>${staffPerformance.staff_no }</td>
						<td>${staffPerformance.staff_name }</td>
						<td>${staffPerformance.performance_salary }</td>
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
