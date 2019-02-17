<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<div class="pageContent">
		<div class="pageFormContent" layoutH="56">	
			<table class="list nowrap" width="1200" >
				<thead>
					<tr>
						<th width="200">导入说明</th>	
						<th width="100">金额</th>	
						<th width="100">备注</th>	
					</tr>
				</thead>
				<tbody>
					<c:forEach var="commoncost"  varStatus="status1" items="${list}">
					<tr>
						<td>
							<c:if test="${commoncost.errorInfo != null && commoncost.errorInfo != '' }">
								<font color="red">${commoncost.errorInfo }</font>
							</c:if>
							<c:if test="${commoncost.errorInfo == null || commoncost.errorInfo == '' }">
								<font color="green">成功</font>
							</c:if>
						</td>
						<td>${commoncost.amount }</td>	
						<td>${commoncost.description }</td>	
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
