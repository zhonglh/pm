<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>


<div class="pageContent">
	<form method="post" action="${webroot }/RoleAction.do?method=${next_operation }" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">			
			
			

				<input name="role_id"  type="hidden" size="30"    value="${role1.role_id }" />

			<p>
				<label>角色名称：</label>
				${role1.role_name }
			</p>	
			

			<p>
				<label>数据权限：</label>
				<spring:message code="role.range.${role1.data_range }"/>
			</p>						
						
			
			<div class="divider"></div>			

			<h3 class="contentTitle">已分配权限</h3>


			<c:forEach var="group"  varStatus="status1" items="${groups}">
			<fieldset>
				<legend><spring:message code="${group[0].group_code_i18n }" /></legend>
				
					<c:forEach var="permit"  varStatus="status1" items="${group}">
						<c:if test="${permit != null }">
							<label>
								<spring:message code="${permit.permit_name_i18n }" />
							</label>
						</c:if>	
						<c:if test="${permit == null }">
							<div class="divider"></div>	
						</c:if>
					</c:forEach>
					

			</fieldset>
			</c:forEach>

		</div>
		<div class="formBar">
			<ul>			
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>