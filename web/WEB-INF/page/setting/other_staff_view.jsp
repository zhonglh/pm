<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">
	<form method="post">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="staff_id" value="${otherStaff1.staff_id }"/>
			

			<p>
				<label>员工名称：</label>
				${otherStaff1.staff_name}
			</p>
					
					

			<p>
				<label>职位类型：</label>				
				<spring:message code="position.type.${otherStaff1.position_type }"/>
			</p>						
			
		</div>
		
		<div class="formBar">
			<ul>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>
