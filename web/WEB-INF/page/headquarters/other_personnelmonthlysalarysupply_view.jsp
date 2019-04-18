<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" action="${webroot}/OtherPersonnelMonthlyBaseAction.do?method=verifyPersonnelMonthlyBase" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="id" value="${personnelMonthlySalarySupply1.id }"/>			
			
			<p>
				<label>报表月份：</label>
				${personnelMonthlySalarySupply1.the_month }
				</p>
			
			
			<p>
				<label>报表类型：</label>
				${personnelMonthlySalarySupply1.monthly_type_name }
			</p>
			
			<p>
				<label>部门名称：</label>
				${personnelMonthlySalarySupply1.dept_name}
			</p>
			
			
			<p>
				<label>姓名：</label>
				${personnelMonthlySalarySupply1.staff_name}
			</p>
			
			
			<p>
				<label>工号：</label>
				${personnelMonthlySalarySupply1.staff_no}
			</p>
			
			<div class="divider"></div>
			
			<p>
				<label>工资补充类型：</label>
				${personnelMonthlySalarySupply1.supply_type }
			</p>
			
			<p>
				<label>工资补充时间：</label>
				<fmt:formatDate value="${personnelMonthlySalarySupply1.supply_time }" pattern="yyyy-MM-dd"/>
			</p>
			
			<p>
				<label>补贴扣除金额：</label>
				<fmt:formatNumber value="${personnelMonthlySalarySupply1.amount }" type="number" pattern="##,##0.00#"/>
			</p>
			
			
			<p>
				<label>备注：</label>
				${personnelMonthlySalarySupply1.description }
			</p>

			<c:import url="../common/applyapproveInfo.jsp"></c:import>
		</div>
		<c:import url="../common/applyapproveButton.jsp"></c:import>
	</form>
</div>
