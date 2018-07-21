<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

		<div class="divider"></div>
	

		<br />
		<c:if test="${infos!=null }">
		<table class="list nowrap" width="100%" >
			<thead>
				<tr>
					<th align="center" width="60" nowrap>操作人</th>						
					<th align="center" width="140" nowrap>操作时间</th>
					<th align="center" width="120" >操作类型</th>	
					<th align="center" width="80" >操作结果</th>
					<th align="center" width="400" >说明</th>
				</tr>
			</thead>			
			
			<tbody>
				<c:forEach var="info"  varStatus="status2" items="${infos}">
				<tr>
					<td align="center">${info.user_name }</td>
					<td align="center"><fmt:formatDate value="${info.operatioin_time }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td align="center"><spring:message code="operation_type.${info.operation_type }" /></td>
					<td align="center">
						<c:if test="${info.approve_result!=null && info.approve_result!='' }">
							<c:if test="${info.approve_result=='0' }">驳回申请</c:if>
							<c:if test="${info.approve_result=='1' }">同意申请</c:if>
						</c:if>
						<c:if test="${info.delete_flag=='1' }"><font color="red">已撤销</font></c:if>
					</td>
					<td align="left" title="${info.description }">${info.description }</td>
				</tr>
				</c:forEach>
			</tbody>	
		</table>
		</c:if>		

