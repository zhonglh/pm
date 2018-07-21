<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${webroot}/InsuranceGradeAction.do?method=${next_operation}" class="pageForm required-validate" onsubmit="return validateCheckCallback(this, '${webroot }/InsuranceGradeAction.do?method=isExist',dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="insurance_grade_id" value="${insuranceGrade1.insurance_grade_id }"/>
			
			
			<p>
				<label>社保种类：</label>
				${insuranceGrade1.insurance_radix }
			</p>
			
			<p>
				<label>社保基数：</label>
				<fmt:formatNumber value="${insuranceGrade1.base_cardinal }" type="number" pattern="#,###,##0.00#"/>
			</p>
			
			<div class="divider"></div>
			
			<p>
				<label>个人缴纳养老险：</label>
				<fmt:formatNumber value="${insuranceGrade1.endowment_insurance_bypersonal }" type="number" pattern="#,###,##0.00#"/>
			</p>
			<p>
				<label>个人缴纳医疗险：</label>
				<fmt:formatNumber value="${insuranceGrade1.medical_insurance_bypersonal }" type="number" pattern="#,###,##0.00#"/>
			</p>
			<p>
				<label>个人缴纳失业险：</label>
				<fmt:formatNumber value="${insuranceGrade1.losejob_insurance_bypersonal }" type="number" pattern="#,###,##0.00#"/>
			</p>
			<p>
				<label>个人缴纳公积金：</label>
				<fmt:formatNumber value="${insuranceGrade1.reservefund_bypersonal }" type="number" pattern="#,###,##0.00#"/>
			</p>
			<p>
				<label>个人所得税：</label>
				<fmt:formatNumber value="${insuranceGrade1.incometax_bypersonal }" type="number" pattern="#,###,##0.00#"/>
			</p>
			<p>
				<label>公司缴纳养老保险：</label>
				<fmt:formatNumber value="${insuranceGrade1.endowment_insurance_bycompany }" type="number" pattern="#,###,##0.00#"/>
			</p>
			<p>
				<label>公司缴纳医疗保险：</label>
				<fmt:formatNumber value="${insuranceGrade1.medical_insurance_bycompany }" type="number" pattern="#,###,##0.00#"/>
			</p>
			<p>
				<label>公司缴纳失业保险：</label>
				<fmt:formatNumber value="${insuranceGrade1.losejob_insurance_bycompany }" type="number" pattern="#,###,##0.00#"/>
			</p>
			<p>
				<label>公司缴纳生育保险：</label>
				<fmt:formatNumber value="${insuranceGrade1.maternity_insurance_bycompany }" type="number" pattern="#,###,##0.00#"/>
			</p>
			<p>
				<label>公司缴纳工伤保险：</label>
				<fmt:formatNumber value="${insuranceGrade1.jobharm_insurance_bycompany }" type="number" pattern="#,###,##0.00#"/>
			</p>
			<p>
				<label>公司缴纳公积金：</label>
				<fmt:formatNumber value="${insuranceGrade1.reservefund_bypcompany }" type="number" pattern="#,###,##0.00#"/>
			</p>
					
		</div>
		
		<div class="formBar">
			<ul>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>
