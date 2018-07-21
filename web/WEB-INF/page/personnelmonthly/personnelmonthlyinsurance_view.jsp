<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" action="${webroot}/PersonnelMonthlyBaseAction.do?method=verifyPersonnelMonthlyBase" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="id" value="${personnelMonthlyInsurance1.id }"/>
			
			
			<p>
				<label>报表月份：</label>
				${personnelMonthlyInsurance1.the_month }
			</p>
			
			
			<p>
				<label>报表类型：</label>
				${personnelMonthlyInsurance1.monthly_type_name }
			</p>
			
			<p>
				<label>项目名称：</label>	
				${personnelMonthlyInsurance1.project_name}
			</p>
			
			
			<p>
				<label>姓名：</label>
				${personnelMonthlyInsurance1.staff_name}
			</p>
			
			
			<p>
				<label>工号：</label>
				${personnelMonthlyInsurance1.staff_no}
			</p>
			
			<div class="divider"></div>
			
			<p>
				<label>社保种类：</label>	
				${personnelMonthlyInsurance1.insurance_radix }
			</p>
			
				
			<p>
				<label>社保缴纳单位：</label>
				${personnelMonthlyInsurance1.securty_unit }
			</p>
			
			<div class="divider"></div>
			
			<p>
				<label>养老个人：</label>
				<fmt:formatNumber value="${personnelMonthlyInsurance1.endowment_insurance_bypersonal }" type="number" pattern="#,###,##0.00#"/>
			</p>
			<p>
				<label>养老单位：</label>
				<fmt:formatNumber value="${personnelMonthlyInsurance1.endowment_insurance_bycompany }" type="number" pattern="#,###,##0.00#"/>
			</p>
			
			<p>
				<label>医疗个人：</label>
				<fmt:formatNumber value="${personnelMonthlyInsurance1.medical_insurance_bypersonal }" type="number" pattern="#,###,##0.00#"/>
			</p>
			<p>
				<label>医疗单位：</label>
				<fmt:formatNumber value="${personnelMonthlyInsurance1.medical_insurance_bycompany }" type="number" pattern="#,###,##0.00#"/>
			</p>
			
			<p>
				<label>失业个人：</label>
				<fmt:formatNumber value="${personnelMonthlyInsurance1.losejob_insurance_bypersonal }" type="number" pattern="#,###,##0.00#"/>
			</p>
			<p>
				<label>失业单位：</label>
				<fmt:formatNumber value="${personnelMonthlyInsurance1.losejob_insurance_bycompany }" type="number" pattern="#,###,##0.00#"/>
			</p>
			
			<p>
				<label>生育单位：</label>
				<fmt:formatNumber value="${personnelMonthlyInsurance1.maternity_insurance_bycompany }" type="number" pattern="#,###,##0.00#"/>
			</p>
			<p>
				<label>工伤单位：</label>
				<fmt:formatNumber value="${personnelMonthlyInsurance1.jobharm_insurance_bycompany }" type="number" pattern="#,###,##0.00#"/>
			</p>
			
			
			
			
			<div class="divider"></div>
			<p>
				<label>制单人：</label>  ${personnelMonthlyInsurance1.build_username }
			</p>
			<p>
				<label>制单日期：</label> <fmt:formatDate value="${personnelMonthlyInsurance1.build_datetime }" pattern="yyyy-MM-dd"/> 
			</p>

			<c:import url="../common/applyapproveInfo.jsp"></c:import>
		</div>
		<c:import url="../common/applyapproveButton.jsp"></c:import>
	</form>
</div>
