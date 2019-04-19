<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" action="${webroot}/OtherInsuranceAction.do?method=verifyInsurance" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="id" value="${insurance1.id }"/>
			
			
			<p>
				<label>月份：</label>
				${insurance1.salary_month }
			</p>
			
			
			
			<p>
				<label>姓名：</label>
				${insurance1.staff_name }(${insurance1.staff_no })
			</p>
			
			
			<p>
				<label>养老个人：</label>
				<fmt:formatNumber value="${insurance1.endowment_insurance_bypersonal }" type="currency" pattern="###,###,##0.00"/>
			</p>
			<p>
				<label>养老单位：</label>
				<fmt:formatNumber value="${insurance1.endowment_insurance_bycompany }" type="currency" pattern="###,###,##0.00"/>
			</p>
			
			
			<p>
				<label>医疗个人：</label>
				<fmt:formatNumber value="${insurance1.medical_insurance_bypersonal }" type="currency" pattern="###,###,##0.00"/>
			</p>
			<p>
				<label>医疗单位：</label>
				<fmt:formatNumber value="${insurance1.medical_insurance_bycompany }" type="currency" pattern="###,###,##0.00"/>
			</p>
			<p>
				<label>失业个人：</label>
				<fmt:formatNumber value="${insurance1.losejob_insurance_bypersonal }" type="currency" pattern="###,###,##0.00"/>
			</p>
			
			<p>
				<label>失业单位：</label>
				<fmt:formatNumber value="${insurance1.losejob_insurance_bycompany }" type="currency" pattern="###,###,##0.00"/>
			</p>
			
			<p>
				<label>生育单位：</label>
				<fmt:formatNumber value="${insurance1.maternity_insurance_bycompany }" type="currency" pattern="###,###,##0.00"/>
			</p>
			
			<p>
				<label>工伤单位：</label>
				<fmt:formatNumber value="${insurance1.jobharm_insurance_bycompany }" type="currency" pattern="###,###,##0.00"/>
			</p>
			<p>
				<label>公积金个人：</label>
				<fmt:formatNumber value="${insurance1.reservefund_bypersonal }" type="currency" pattern="###,###,##0.00"/>
			</p>
			<p>
				<label>公积金单位：</label>
				<fmt:formatNumber value="${insurance1.reservefund_bypcompany }" type="currency" pattern="###,###,##0.00"/>
			</p>
			<p>
				<label>社保缴纳单位：</label>
				${insurance1.securty_unit }
			</p>

			<c:import url="../common/applyapproveInfo.jsp"></c:import>
		</div>
		<c:import url="../common/applyapproveButton.jsp"></c:import>
	</form>
</div>
