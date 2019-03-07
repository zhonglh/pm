<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" action="${webroot}/StaffPerformanceAction.do?method=verifyInsurance" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="id" value="${staffPerformance1.id }"/>
			
			
			<p>
				<label>月份：</label>
				${staffPerformance1.the_month }
			</p>


			<p>
				<label>部门：</label>
				${staffPerformance1.dept_name }
			</p>



			
			<p>
				<label>姓名：</label>
				${staffPerformance1.staff_name }(${staffPerformance1.staff_no })
			</p>
			
			
			<p>
				<label>绩效工资：</label>
				<fmt:formatNumber value="${staffPerformance1.performance_salary }" type="currency" pattern="###,###,##0.00"/>
			</p>

			<c:import url="../common/applyapproveInfo.jsp"></c:import>
		</div>
		<c:import url="../common/applyapproveButton.jsp"></c:import>
	</form>
</div>
