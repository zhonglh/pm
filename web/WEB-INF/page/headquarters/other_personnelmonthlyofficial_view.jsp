<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" action="${webroot}/OtherPersonnelMonthlyBaseAction.do?method=verifyPersonnelMonthlyBase" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="id" value="${personnelMonthlyOfficial1.id }"/>
			
			
			<p>
				<label>报表月份：</label>
				${personnelMonthlyOfficial1.the_month }
			</p>
			
			
			<p>
				<label>报表类型：</label>
				${personnelMonthlyOfficial1.monthly_type_name }
			</p>
			
			<p>
				<label>部门名称：</label>
				${personnelMonthlyOfficial1.dept_name}
			</p>
			
			
			<p>
				<label>姓名：</label>
				${personnelMonthlyOfficial1.staff_name}
			</p>
			
			
			<p>
				<label>工号：</label>
				${personnelMonthlyOfficial1.staff_no}
			</p>
			
			<div class="divider"></div>
			
			
			
			<p>
				<label>入职时间：</label>
				<fmt:formatDate value="${personnelMonthlyOfficial1.join_datetime }" pattern="yyyy-MM-dd"/>
			</p>
			<p>
				<label>转正日期：</label>
				<fmt:formatDate value="${personnelMonthlyOfficial1.confirmation_date }" pattern="yyyy-MM-dd"/>
			</p>

			<p>
				<label>当月工作日天数：</label>
				${personnelMonthlyOfficial1.work_days }
			</p>

			<p>
				<label>试用期工资：</label>
				<fmt:formatNumber value="${personnelMonthlyOfficial1.tryout_salary }" type="number" pattern="##,##0.00#"/>
			</p>

			<p>
				<label>当月试用期工作日天数：</label>
				${personnelMonthlyOfficial1.tryout_days }
			</p>

			<p>
				<label>正式工资：</label>
				<fmt:formatNumber value="${personnelMonthlyOfficial1.official_salary }" type="number" pattern="##,##0.00#"/>
			</p>

			<p>
				<label>当月转正工作日天数：</label>
				${personnelMonthlyOfficial1.official_days }
			</p>

			<p>
				<label>转正当月工资：</label>
				<fmt:formatNumber value="${personnelMonthlyOfficial1.curr_salary }" type="number" pattern="##,##0.00#"/>
			</p>
			
			
			<div class="divider"></div>
			<p>
				<label>制单人：</label>  ${personnelMonthlyOfficial1.build_username }
			</p>
			<p>
				<label>制单日期：</label> <fmt:formatDate value="${personnelMonthlyOfficial1.build_datetime }" pattern="yyyy-MM-dd"/> 
			</p>

			<c:import url="../common/applyapproveInfo.jsp"></c:import>
		</div>
		<c:import url="../common/applyapproveButton.jsp"></c:import>
	</form>
</div>
