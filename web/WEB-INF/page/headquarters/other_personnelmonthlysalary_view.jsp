<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" action="${webroot}/OtherPersonnelMonthlyBaseAction.do?method=verifyPersonnelMonthlyBase" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="id" value="${personnelMonthlySalary1.id }"/>
			
			
			
			
			<p>
				<label>报表月份：</label>
				${personnelMonthlySalary1.the_month }
				</p>
			
			
			<p>
				<label>报表类型：</label>
				${personnelMonthlySalary1.monthly_type_name }
			</p>
			
			<p>
				<label>部门名称：</label>
				${personnelMonthlySalary1.dept_name}
			</p>
			
			
			<p>
				<label>姓名：</label>
				${personnelMonthlySalary1.staff_name}
			</p>
			
			
			<p>
				<label>工号：</label>
				${personnelMonthlySalary1.staff_no}
			</p>
			
			<div class="divider"></div>
			
			
			<p>
				<label>入职时间：</label>
				<fmt:formatDate value="${personnelMonthlySalary1.join_datetime }" pattern="yyyy-MM-dd"/>
			</p>
			<p>
				<label>调薪时间：</label>
				<fmt:formatDate value="${personnelMonthlySalary1.change_time }" pattern="yyyy-MM-dd"/>
			</p>


			<p>
				<label>当月工作日天数：</label>
				${personnelMonthlySalary1.work_days }
			</p>
			
			
			<p>
				<label>调薪前薪资：</label>
				<fmt:formatNumber value="${personnelMonthlySalary1.old_salary }" type="number" pattern="##,##0.00#"/>
			</p>

			<p>
				<label>调薪前工作日天数：</label>
				${personnelMonthlySalary1.old_days }
			</p>

			
			<p>
				<label>调薪后薪资：</label>
				<fmt:formatNumber value="${personnelMonthlySalary1.new_salary }" type="number" pattern="##,##0.00#"/>
			</p>

			<p>
				<label>调薪后工作日天数：</label>
				${personnelMonthlySalary1.new_days }
			</p>
			
			<p>
				<label>调薪当月薪资：</label>
				<fmt:formatNumber value="${personnelMonthlySalary1.curr_salary }" type="number" pattern="##,##0.00#"/>
			</p>
			
			<p>
				<label>备注：</label>
				${personnelMonthlySalary1.description }
			</p>

			<c:import url="../common/applyapproveInfo.jsp"></c:import>
		</div>
		<c:import url="../common/applyapproveButton.jsp"></c:import>
	</form>
</div>
