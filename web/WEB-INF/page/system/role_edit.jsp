<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>


<div class="pageContent">
	<form method="post" action="${webroot }/RoleAction.do?method=${next_operation }" class="pageForm required-validate" onsubmit="return validateCheckCallback(this, '${webroot }/RoleAction.do?method=isExist',dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">			
			
			

				<input name="role_id"  type="hidden" size="30"    value="${role1.role_id }" />

			<p>
				<label>角色名称：</label>
				<input name="role_name" class="text required" type="text" size="25" minlength="2" maxlength="30"   value="${role1.role_name }" /> 
			</p>	
			

			<p>
				<label>数据权限：</label>
				<select name="data_range" class="required combox">
					<option value="1" <c:if test="${'1' == role1.data_range }">selected</c:if>><spring:message code="role.range.1"/></option>
					<option value="2" <c:if test="${'2' == role1.data_range }">selected</c:if>><spring:message code="role.range.2"/></option>
					<option value="3" <c:if test="${'3' == role1.data_range }">selected</c:if>><spring:message code="role.range.3"/></option>
				</select>
			</p>						
						
			
			<div class="divider"></div>			



			<h3 class="contentTitle">分配权限</h3>
			<c:forEach var="group"  varStatus="status1" items="${groups}">
			<fieldset>
				<legend><spring:message code="${group[0].group_code_i18n }" /></legend>
				
					<c:forEach var="permit"  varStatus="status1" items="${group}">
						<c:if test="${permit != null }">
							<label>
								<input type="checkbox" name="permit_id" value="${permit.permit_id }" <c:if test="${permit.selected }">checked="checked"</c:if> />
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
					<div class="button"><div class="buttonContent"><button type="button" class="checkboxCtrl" group="permit_id" selectType="all">全选</button></div></div>
				</li>
			
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="checkboxCtrl" group="permit_id" selectType="invert">反选</button></div></div>
				</li>
				<li>
					<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
				</li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>