<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">		
			
		<div class="pageFormContent" layoutH="56">	
			<table class="list nowrap" width="1200" >
				<thead>
					<tr>
						<th width="200">导入说明</th>							
						<th width="150">项目名称</th>													
						<th width="80">应付金额</th>												
						<th width="80">支出对象</th>
						<th width="200">分包商名称</th>
						<th width="100">分包合同编号</th>
						<th width="100">收到的发票号</th>
						<th width="80">应付月份</th>
						<th width="300">描述</th>
					</tr>
				</thead>
				<tbody>					
					<c:forEach var="projectExpend"  varStatus="status1" items="${list}">
					<tr>
						<td>
							<c:if test="${projectExpend.errorInfo != null && projectExpend.errorInfo != '' }">
								<font color="red">${projectExpend.errorInfo }</font>
							</c:if>
							<c:if test="${projectExpend.errorInfo == null || projectExpend.errorInfo == '' }">
								<font color="green">成功</font>
							</c:if>
							
						</td>						
						<td>${projectExpend.project_name }</td>
						<td align="right"><b><fmt:formatNumber value="${projectExpend.amount }" type="currency" pattern="###,###,##0.00"/></b></td>
						<td>
							<c:if test="${'1' == projectExpend.expend_object }"><spring:message code="expend.object.1"/></c:if>
							<c:if test="${'2' == projectExpend.expend_object }"><spring:message code="expend.object.2"/></c:if>
							<c:if test="${projectExpend.expend_object != null && '1' != projectExpend.expend_object && '2' != projectExpend.expend_object}">
								${projectExpend.expend_object }
							</c:if>
						</td>
						<td>${projectExpend.sub_contractor_name }</td>
						<td>${projectExpend.sub_contractor_no }</td>
						<td>${projectExpend.invoiceno }</td>
						<td>${projectExpend.str_month }</td>
						<td>${projectExpend.description }</td>
				
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
