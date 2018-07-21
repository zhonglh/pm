<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
			
<div class="pageContent">
	<form method="post" action="projectcosts/work_record_edit2.html" class="pageForm required-validate" onsubmit="return dialogJump(this);">
		<div class="pageFormContent" layoutH="20">				
			
			<h3 class="contentTitle">日志明细</h3>
				<table class="list"  width="100%">
					<thead>
						<tr>
							<th width="80">数据项</th>
							<th width="120">操作前</th>
							<th width="120">操作后</th>
						</tr>													
					</thead>
					
					<tbody>								
						<c:forEach var="detail"  varStatus="status1" items="${details}">
						<tr>
							<td><spring:message code="${detail.data_item_i18n }" /></td>									
							<td>
								<c:if test="${detail.data_item_code =='data_range' }">
										<spring:message code="role.range.${detail.operation_before }"/>
								</c:if>
								<c:if test="${detail.data_item_code !='data_range' }">
									${detail.operation_before }
								</c:if>
							</td>
							<td>
								<c:if test="${detail.data_item_code =='data_range' }">
										<spring:message code="role.range.${detail.operation_after }"/>
								</c:if>
								<c:if test="${detail.data_item_code !='data_range' }">
									${detail.operation_after }
								</c:if>
																	
							</td>
						</tr>	
						</c:forEach>	
					</tbody>
				</table>
				
			</div>
		</form>
	</div>				
					