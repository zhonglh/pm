<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">		
			
		<div class="pageFormContent" layoutH="56">	
			<table class="list nowrap" width="1000" >
				<thead>
					<tr>
						<th width="200">导入说明</th>	
						<th width="80">报销月份</th>
						<th  width="120">描述</th>
						<th width="120">报销金额</th>
						<th width="120">项目名称</th>
						<th width="100">报销类别</th>
						<th width="120">所属部门</th>
						<th width="60">报销人</th>
						<th width="120">实际支付日期</th>
					</tr>
				</thead>
				<tbody>					
					<c:forEach var="departCosts"  varStatus="status1" items="${list}">
					<tr>
						<td>
							<c:if test="${departCosts.errorInfo != null && departCosts.errorInfo != '' }">
								<font color="red">${departCosts.errorInfo }</font>
							</c:if>
							<c:if test="${departCosts.errorInfo == null || departCosts.errorInfo == '' }">
								<font color="green">成功</font>
							</c:if>
							
						</td>						
						<td>${departCosts.str_month }</td>
						<td>${departCosts.description }</td>
						<td>${departCosts.amount }</td>
						<td>${departCosts.project_name }</td>
						<td>${departCosts.pay_item_name }</td>
						<td>${departCosts.dept_name }</td>
						<td>${departCosts.staff_name }</td>
						<td><fmt:formatDate value="${departCosts.pay_date }" pattern="yyyy-MM-dd"/></td>
				
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
