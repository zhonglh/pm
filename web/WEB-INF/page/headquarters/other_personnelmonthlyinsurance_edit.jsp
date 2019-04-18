<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" action="${webroot}/OtherPersonnelMonthlyInsuranceAction.do?method=${next_operation}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="id" value="${personnelMonthlyInsurance1.id }"/>
			
			
			<p>
				<label>报表月份：</label>
				<input name="the_month" id="the_month" class="required" format="yyyyMM" size="30"  minlength="6" maxlength="6" type="text"  value="${personnelMonthlyInsurance1.the_month }" readonly/>
			</p>
			
			
			<p>
				<label>报表类型：</label>
				<input name="monthly_type" id="monthly_type" type="hidden"  value="${personnelMonthlyInsurance1.monthly_type }"/>
				<input name="monthly_type_name" id="monthly_type_name" class="required" size="30"  type="text"  value="${personnelMonthlyInsurance1.monthly_type_name }" readonly/>
			</p>
			
			<p>
				<label>部门名称：</label>
				<input name="dept_id"	type="hidden" value="${personnelMonthlyInsurance1.dept_id}"	 />
				<input name="dept_name" class="text" type="text" size="30" maxlength="60" value="${personnelMonthlyInsurance1.dept_name}" readonly="true" />
			</p>
			
			
			<p>
				<label>姓名：</label>
				<input type="hidden" size="2" name="staff.staff_id" value="${personnelMonthlyInsurance1.staff_id}"	 />	
				<input name="staff.staff_name" id="staff_name" class="text required" size="30" type="text"  readonly value="${personnelMonthlyInsurance1.staff_name}"	 />	
				<a class="btnLook" href="${webroot }/StaffCostAction.do?method=lookup" lookupGroup="staff" lookupPk="staff_id" width="970">选择</a>
			</p>
			
			
			<p>
				<label>工号：</label>
				<input name="staff.staff_no" id="staff_no" class="text required" size="30" type="text"  readonly value="${personnelMonthlyInsurance1.staff_no}"	 />	
			</p>
			
			<div class="divider"></div>
			
			<p>
				<label>社保种类：</label>	
				<input type="hidden"  name="grade.insurance_grade_id" value="${personnelMonthlyInsurance1.insurance_grade_id }"/>	
				<input name="grade.insurance_radix" class="text required" type="text" size="30"  
				value="${personnelMonthlyInsurance1.insurance_radix }" 
				readonly="readonly" lookupPk="insurance_grade_id" 
				suggestFields="insurance_radix,endowment_insurance_bypersonal,medical_insurance_bypersonal,losejob_insurance_bypersonal,endowment_insurance_bycompany,medical_insurance_bycompany,losejob_insurance_bycompany,maternity_insurance_bycompany,jobharm_insurance_bycompany" 
				suggestUrl="${webroot }/InsuranceGradeAction.do?method=lookup" lookupGroup="grade"/>
			</p>
			
				
			<p>
				<label>社保缴纳单位：</label>
				<input name="securty_unit" class="text" type="text" size="30" value="${personnelMonthlyInsurance1.securty_unit }" />
			</p>
			
			<div class="divider"></div>
			
			<p>
				<label>养老个人：</label>
				<input name="grade.endowment_insurance_bypersonal" class="number" readonly type="text" size="30" maxlength="10" value="<fmt:formatNumber value="${personnelMonthlyInsurance1.endowment_insurance_bypersonal }" type="number" pattern="####0.00#"/>" />
			</p>
			<p>
				<label>养老单位：</label>
				<input name="grade.endowment_insurance_bycompany" class="number" readonly type="text" size="30" maxlength="10" value="<fmt:formatNumber value="${personnelMonthlyInsurance1.endowment_insurance_bycompany }" type="number" pattern="####0.00#"/>" />
			</p>
			
			<p>
				<label>医疗个人：</label>
				<input name="grade.medical_insurance_bypersonal" class="number" readonly type="text" size="30" maxlength="10" value="<fmt:formatNumber value="${personnelMonthlyInsurance1.medical_insurance_bypersonal }" type="number" pattern="####0.00#"/>" />
			</p>
			<p>
				<label>医疗单位：</label>
				<input name="grade.medical_insurance_bycompany" class="number" readonly type="text" size="30" maxlength="10" value="<fmt:formatNumber value="${personnelMonthlyInsurance1.medical_insurance_bycompany }" type="number" pattern="####0.00#"/>" />
			</p>
			
			<p>
				<label>失业个人：</label>
				<input name="grade.losejob_insurance_bypersonal" class="number" readonly type="text" size="30" maxlength="10" value="<fmt:formatNumber value="${personnelMonthlyInsurance1.losejob_insurance_bypersonal }" type="number" pattern="####0.00#"/>" />
			</p>
			<p>
				<label>失业单位：</label>
				<input name="grade.losejob_insurance_bycompany" class="number" readonly type="text" size="30" maxlength="10" value="<fmt:formatNumber value="${personnelMonthlyInsurance1.losejob_insurance_bycompany }" type="number" pattern="####0.00#"/>" />
			</p>
			
			<p>
				<label>生育单位：</label>
				<input name="grade.maternity_insurance_bycompany" class="number" readonly type="text" size="30" maxlength="10" value="<fmt:formatNumber value="${personnelMonthlyInsurance1.maternity_insurance_bycompany }" type="number" pattern="####0.00#"/>" />
			</p>
			<p>
				<label>工伤单位：</label>
				<input name="grade.jobharm_insurance_bycompany" class="number" readonly type="text" size="30" maxlength="10" value="<fmt:formatNumber value="${personnelMonthlyInsurance1.jobharm_insurance_bycompany }" type="number" pattern="####0.00#"/>" />
			</p>
			
			
			
			
			<div class="divider"></div>
			<p>
				<label>制单人：</label>  ${personnelMonthlyInsurance1.build_username }
			</p>
			<p>
				<label>制单日期：</label> <fmt:formatDate value="${personnelMonthlyInsurance1.build_datetime }" pattern="yyyy-MM-dd"/> 
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
