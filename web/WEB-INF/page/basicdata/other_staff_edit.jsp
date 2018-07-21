<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${webroot}/OtherStaffAction.do?method=${next_operation}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="staff_id" value="${otherStaff1.staff_id }"/>
			
			<p>
				<label>工号：</label>
				<input name="staff_no" class="required" type="text" size="30" maxlength="20" value="${otherStaff1.staff_no}" />
			</p>

			<p>
				<label>员工名称：</label>
				<input name="staff_name" class="required" type="text" size="30" maxlength="30" value="${otherStaff1.staff_name}" />
			</p>
						
			<p>
				<label>所在部门：</label>
				<input type="hidden" size="2"  name="dept.dept_id" value="${otherStaff1.dept_id }"/>
				<input name="dept.dept_name" class="text" type="text" size="30"  value="${otherStaff1.dept_name }" readonly="readonly" lookupPk="dept_id"
				suggestFields="dept_name" suggestUrl="${webroot }/DeptAction.do?method=lookup" lookupGroup="dept"/>	
			</p>
					
					

			<p>
				<label>职位类型：</label>
				<select name="position_type" style="width:185px">
					<option value="1" <c:if test="${otherStaff1.position_type == '1' }">selected</c:if>><spring:message code="position.type.1"/></option>
					<option value="2" <c:if test="${otherStaff1.position_type == '2' }">selected</c:if>><spring:message code="position.type.2"/></option>
					<option value="3" <c:if test="${otherStaff1.position_type == '3' }">selected</c:if>><spring:message code="position.type.3"/></option>
					<option value="4" <c:if test="${otherStaff1.position_type == '4' }">selected</c:if>><spring:message code="position.type.4"/></option>
					<option value="5" <c:if test="${otherStaff1.position_type == '5' }">selected</c:if>><spring:message code="position.type.5"/></option>
					<option value="6" <c:if test="${otherStaff1.position_type == '6' }">selected</c:if>><spring:message code="position.type.6"/></option>
					<option value="7" <c:if test="${otherStaff.position_type == '7' }">selected</c:if>><spring:message code="position.type.7"/></option>
					<option value="8" <c:if test="${otherStaff.position_type == '8' }">selected</c:if>><spring:message code="position.type.8"/></option>
					<option value="9" <c:if test="${otherStaff.position_type == '9' }">selected</c:if>><spring:message code="position.type.9"/></option>
					<option value="10" <c:if test="${otherStaff.position_type == '10' }">selected</c:if>><spring:message code="position.type.10"/></option>
				</select>
			</p>						
			
		</div>
		
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>
