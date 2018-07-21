<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>


<div class="pageContent">

		<div layoutH="16" style="float:left; display:block; overflow:auto; width:330px; border:solid 1px #CCC; line-height:21px; background:#fff">

			<table class="table" width="350" layoutH="122">
				<thead>
					<tr>
						<th width="30">版本</th>
						<th width="150">操作时间</th>
						<th width="80">操作人</th>
						<th width="80">操作类型</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="his"  varStatus="status1" items="${list}">
					<tr target="sid_invoice" rel="${his.id }">
						<td>${status1.count }</td>
						<td>
							<a style="text-decoration:underline;" href="${webroot}/InvoiceHisAction.do?method=toView&id=${his.id }" target="ajax" rel="viewBox">
							<fmt:formatDate value="${his.his_date }" pattern="yyyy-MM-dd HH:mm:ss"/>
							</a>
						</td>
						<td>${his.his_user_name }</td>
						<td><spring:message code="${his.his_operation_type }" /></td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
		<div id="viewBox" class="unitBox" style="margin-left:330px;">
		</div>

</div>
