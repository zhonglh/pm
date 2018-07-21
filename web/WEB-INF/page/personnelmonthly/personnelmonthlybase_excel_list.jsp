<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<div class="pageContent">
		<div class="pageFormContent" layoutH="56">	
			<table class="list nowrap" width="1200" >
				
				<c:if test="${ not empty list1}">
				<thead>
					<tr>
						<th width="300">导入说明</th>	
						<th width="100">月报类型</th>	
						<th width="100">工号</th>	
						<th width="100">姓名</th>	
						<th width="200">转正当月工资</th>	
					</tr>
				</thead>
				<tbody>
					<c:forEach var="base"  varStatus="status1" items="${list1}">
					<tr>
						<td>
							<c:if test="${base.errorInfo != null && base.errorInfo != '' }">
								<font color="red">${base.errorInfo }</font>
							</c:if>
							<c:if test="${base.errorInfo == null || base.errorInfo == '' }">
								<font color="green">成功</font>
							</c:if>
						</td>
						<td>${base.monthly_type_name }</td>
						<td>${base.staff_no }</td>
						<td>${base.staff_name }</td>
						<td>${base.curr_salary }</td>
					</tr>
					</c:forEach>
				</tbody>
				</c:if>
				
				
				
				<c:if test="${ not empty list2}">
				<thead>
					<tr>
						<th width="300">导入说明</th>	
						<th width="100">月报类型</th>	
						<th width="100">工号</th>	
						<th width="100">姓名</th>	
						<th width="100">调薪时间</th>	
						<th width="100">调薪后薪资</th>	
						<th width="120">调薪当月薪资</th>	
						<th width=300">备注</th>	
					</tr>
				</thead>
				<tbody>
					<c:forEach var="base"  varStatus="status1" items="${list2}">
					<tr>
						<td>
							<c:if test="${base.errorInfo != null && base.errorInfo != '' }">
								<font color="red">${base.errorInfo }</font>
							</c:if>
							<c:if test="${base.errorInfo == null || base.errorInfo == '' }">
								<font color="green">成功</font>
							</c:if>
						</td>
						<td>${base.monthly_type_name }</td>
						<td>${base.staff_no }</td>
						<td>${base.staff_name }</td>					
						
						<td><fmt:formatDate value="${base.change_time }" pattern="yyyy-MM-dd"/></td>
						<td>${base.new_salary }</td>
						<td>${base.curr_salary }</td>
						<td>${base.description }</td>
					</tr>
					</c:forEach>
				</tbody>
				</c:if>
				
				
				<c:if test="${ not empty list3}">
				<thead>
					<tr>
						<th width="300">导入说明</th>	
						<th width="100">月报类型</th>	
						<th width="100">工号</th>	
						<th width="100">姓名</th>	
						<th width="100">调薪时间</th>	
						<th width="100">调薪后薪资</th>	
						<th width="120">调薪当月薪资</th>	
						<th width=300">备注</th>	
					</tr>
				</thead>
				<tbody>
					<c:forEach var="base"  varStatus="status1" items="${list3}">
					<tr>
						<td>
							<c:if test="${base.errorInfo != null && base.errorInfo != '' }">
								<font color="red">${base.errorInfo }</font>
							</c:if>
							<c:if test="${base.errorInfo == null || base.errorInfo == '' }">
								<font color="green">成功</font>
							</c:if>
						</td>
						<td>${base.monthly_type_name }</td>
						<td>${base.staff_no }</td>
						<td>${base.staff_name }</td>					
						
						<td><fmt:formatDate value="${base.change_time }" pattern="yyyy-MM-dd"/></td>
						<td>${base.new_salary }</td>
						<td>${base.curr_salary }</td>
						<td>${base.description }</td>
					</tr>
					</c:forEach>
				</tbody>
				</c:if>
				
				
				
				<c:if test="${ not empty list4}">
				<thead>
					<tr>
						<th width="300">导入说明</th>	
						<th width="100">月报类型</th>	
						<th width="100">工号</th>	
						<th width="100">姓名</th>	
						<th width="150">社保档次</th>	
						<th width="180">社保缴纳单位</th>	
						<th width=300">备注</th>	
					</tr>
				</thead>
				<tbody>
					<c:forEach var="base"  varStatus="status1" items="${list4}">
					<tr>
						<td>
							<c:if test="${base.errorInfo != null && base.errorInfo != '' }">
								<font color="red">${base.errorInfo }</font>
							</c:if>
							<c:if test="${base.errorInfo == null || base.errorInfo == '' }">
								<font color="green">成功</font>
							</c:if>
						</td>
						<td>${base.monthly_type_name }</td>
						<td>${base.staff_no }</td>
						<td>${base.staff_name }</td>					
						
						<td>${base.insurance_radix }</td>
						<td>${base.securty_unit }</td>
						<td>${base.description }</td>
					</tr>
					</c:forEach>
				</tbody>
				</c:if>
				
				
				<c:if test="${ not empty list5}">
				<thead>
					<tr>
						<th width="300">导入说明</th>	
						<th width="100">月报类型</th>	
						<th width="100">工号</th>	
						<th width="100">姓名</th>	
						<th width="150">社保档次</th>	
						<th width="180">社保缴纳单位</th>	
						<th width=300">备注</th>	
					</tr>
				</thead>
				<tbody>
					<c:forEach var="base"  varStatus="status1" items="${list5}">
					<tr>
						<td>
							<c:if test="${base.errorInfo != null && base.errorInfo != '' }">
								<font color="red">${base.errorInfo }</font>
							</c:if>
							<c:if test="${base.errorInfo == null || base.errorInfo == '' }">
								<font color="green">成功</font>
							</c:if>
						</td>
						<td>${base.monthly_type_name }</td>
						<td>${base.staff_no }</td>
						<td>${base.staff_name }</td>					
						
						<td>${base.insurance_radix }</td>
						<td>${base.securty_unit }</td>
						<td>${base.description }</td>
					</tr>
					</c:forEach>
				</tbody>
				</c:if>
				
				
				
				
				<c:if test="${ not empty list6}">
				<thead>
					<tr>
						<th width="300">导入说明</th>	
						<th width="100">月报类型</th>	
						<th width="100">工号</th>	
						<th width="100">姓名</th>	
						<th width="150">社保档次</th>	
						<th width="180">社保缴纳单位</th>	
						<th width=300">备注</th>	
					</tr>
				</thead>
				<tbody>
					<c:forEach var="base"  varStatus="status1" items="${list6}">
					<tr>
						<td>
							<c:if test="${base.errorInfo != null && base.errorInfo != '' }">
								<font color="red">${base.errorInfo }</font>
							</c:if>
							<c:if test="${base.errorInfo == null || base.errorInfo == '' }">
								<font color="green">成功</font>
							</c:if>
						</td>
						<td>${base.monthly_type_name }</td>
						<td>${base.staff_no }</td>
						<td>${base.staff_name }</td>					
						
						<td>${base.insurance_radix }</td>
						<td>${base.securty_unit }</td>
						<td>${base.description }</td>
					</tr>
					</c:forEach>
				</tbody>
				</c:if>
				
				
				<c:if test="${ not empty list7}">
				<thead>
					<tr>
						<th width="300">导入说明</th>	
						<th width="100">月报类型</th>	
						<th width="100">工号</th>	
						<th width="100">姓名</th>	
						<th width="150">社保档次</th>	
						<th width="180">社保缴纳单位</th>	
						<th width=300">备注</th>	
					</tr>
				</thead>
				<tbody>
					<c:forEach var="base"  varStatus="status1" items="${list7}">
					<tr>
						<td>
							<c:if test="${base.errorInfo != null && base.errorInfo != '' }">
								<font color="red">${base.errorInfo }</font>
							</c:if>
							<c:if test="${base.errorInfo == null || base.errorInfo == '' }">
								<font color="green">成功</font>
							</c:if>
						</td>
						<td>${base.monthly_type_name }</td>
						<td>${base.staff_no }</td>
						<td>${base.staff_name }</td>					
						
						<td>${base.insurance_radix }</td>
						<td>${base.securty_unit }</td>
						<td>${base.description }</td>
					</tr>
					</c:forEach>
				</tbody>
				</c:if>
				
				
				<c:if test="${ not empty list8}">
				<thead>
					<tr>
						<th width="300">导入说明</th>	
						<th width="100">月报类型</th>	
						<th width="100">工号</th>	
						<th width="100">姓名</th>	
						<th width="100">奖惩时间</th>	
						<th width="100">奖惩薪资</th>	
						<th width=300">备注</th>	
					</tr>
				</thead>
				<tbody>
					<c:forEach var="base"  varStatus="status1" items="${list8}">
					<tr>
						<td>
							<c:if test="${base.errorInfo != null && base.errorInfo != '' }">
								<font color="red">${base.errorInfo }</font>
							</c:if>
							<c:if test="${base.errorInfo == null || base.errorInfo == '' }">
								<font color="green">成功</font>
							</c:if>
						</td>
						<td>${base.monthly_type_name }</td>
						<td>${base.staff_no }</td>
						<td>${base.staff_name }</td>						
						
						<td><fmt:formatDate value="${base.change_time }" pattern="yyyy-MM-dd"/></td>
						<td>${base.amount }</td>
						<td>${base.description }</td>
					</tr>
					</c:forEach>
				</tbody>
				</c:if>
				
				
				<c:if test="${ not empty list9}">
				<thead>
					<tr>
						<th width="300">导入说明</th>	
						<th width="100">月报类型</th>	
						<th width="100">工号</th>	
						<th width="100">姓名</th>	
						<th width="180">工资补充类型</th>	
						<th width="120">工资补充时间</th>	
						<th width="120">补贴扣除金额</th>	
						<th width=300">备注</th>	
					</tr>
				</thead>
				<tbody>
					<c:forEach var="base"  varStatus="status1" items="${list9}">
					<tr>
						<td>
							<c:if test="${base.errorInfo != null && base.errorInfo != '' }">
								<font color="red">${base.errorInfo }</font>
							</c:if>
							<c:if test="${base.errorInfo == null || base.errorInfo == '' }">
								<font color="green">成功</font>
							</c:if>
						</td>
						<td>${base.monthly_type_name }</td>
						<td>${base.staff_no }</td>
						<td>${base.staff_name }</td>						
						
						<td>${base.supply_type }</td>	
						<td><fmt:formatDate value="${base.supply_time }" pattern="yyyy-MM-dd"/></td>
						<td>${base.amount }</td>
						<td>${base.description }</td>
					</tr>
					</c:forEach>
				</tbody>
				</c:if>
				
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
