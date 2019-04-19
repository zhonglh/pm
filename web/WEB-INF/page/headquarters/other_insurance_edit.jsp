<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" action="${webroot}/OtherInsuranceAction.do?method=${next_operation}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="id" value="${insurance1.id }"/>



			<p>
				<label>月份：</label>
				<input name="salary_month" id="salary_month" class="date required" autocomplete="off"   format="yyyyMM" size="30"  minlength="6" maxlength="6" type="text"  value="${insurance1.salary_month }" readonly/>
			</p>


			<p>
				<label>员工：</label>
				<input type="hidden" size="2" name="staff.staff_id" value="${insurance1.staff_id }"/>
				<input type="hidden" size="2" name="staff.staff_no" value="${insurance1.staff_no }"/>
				<input name="staff.staff_name" class="required text" type="text" size="28"  value="${insurance1.staff_name }" readonly />
				<a class="btnLook" href="${webroot }/OtherStaffAction.do?method=lookup" lookupGroup="staff" lookupPk="staff_id" width="970">选择</a>
			</p>


			<p>
				<label>养老个人：</label>
				<input name="endowment_insurance_bypersonal" class="number required" max="1000000"  type="text" size="30" value="<fmt:formatNumber value="${insurance1.endowment_insurance_bypersonal }" type="number" pattern="####0.00#"/>" />
			</p>
			<p>
				<label>养老单位：</label>
				<input name="endowment_insurance_bycompany" class="number required" max="1000000" type="text" size="30" value="<fmt:formatNumber value="${insurance1.endowment_insurance_bycompany }" type="number" pattern="####0.00#"/>" />
			</p>


			<p>
				<label>医疗个人：</label>
				<input name="medical_insurance_bypersonal" class="number required" max="1000000"  type="text" size="30" value="<fmt:formatNumber value="${insurance1.medical_insurance_bypersonal }" type="number" pattern="####0.00#"/>" />
			</p>
			<p>
				<label>医疗单位：</label>
				<input name="medical_insurance_bycompany" class="number required" max="1000000"  type="text" size="30" value="<fmt:formatNumber value="${insurance1.medical_insurance_bycompany }" type="number" pattern="####0.00#"/>" />
			</p>

			<p>
				<label>失业个人：</label>
				<input name="losejob_insurance_bypersonal" class="number required" max="1000000"  type="text" size="30" value="<fmt:formatNumber value="${insurance1.losejob_insurance_bypersonal }" type="number" pattern="####0.00#"/>" />
			</p>
			<p>
				<label>失业单位：</label>
				<input name="losejob_insurance_bycompany" class="number required" max="1000000"  type="text" size="30" value="<fmt:formatNumber value="${insurance1.losejob_insurance_bycompany }" type="number" pattern="####0.00#"/>" />
			</p>

			<p>
				<label>生育单位：</label>
				<input name="maternity_insurance_bycompany" class="number required" max="1000000"  type="text" size="30" value="<fmt:formatNumber value="${insurance1.maternity_insurance_bycompany }" type="number" pattern="####0.00#"/>" />
			</p>


			<p>
				<label>工伤单位：</label>
				<input name="jobharm_insurance_bycompany" class="number required" max="1000000"  type="text" size="30" value="<fmt:formatNumber value="${insurance1.jobharm_insurance_bycompany }" type="number" pattern="####0.00#"/>" />
			</p>

			<p>
				<label>公积金个人：</label>
				<input name="reservefund_bypersonal" class="number required" max="1000000"  type="text" size="30" value="<fmt:formatNumber value="${insurance1.reservefund_bypersonal }" type="number" pattern="####0.00#"/>" />
			</p>
			<p>
				<label>公积金单位：</label>
				<input name="reservefund_bypcompany" class="number required" max="1000000"  type="text" size="30" value="<fmt:formatNumber value="${insurance1.reservefund_bypcompany }" type="number" pattern="####0.00#"/>" />
			</p>
			<p>
				<label>社保缴纳单位：</label>
				<input name="securty_unit" class="text" type="text" size="30" maxlength="60" value="${insurance1.securty_unit }" />
			</p>
			<div class="divider"></div>
			<p>
				<label>制单人：</label>  ${insurance1.build_username }
			</p>
			<p>
				<label>制单日期：</label> <fmt:formatDate value="${insurance1.build_datetime }" pattern="yyyy-MM-dd"/>
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
