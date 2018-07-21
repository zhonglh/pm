<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" action="${webroot}/PersonnelMonthlyBaseAction.do?method=verifyPersonnelMonthlyBase" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="id" value="${personnelMonthlyBonus1.id }"/>
			
			
			
			
			<p>
				<label>报表月份：</label>
				${personnelMonthlyBonus1.the_month }
				</p>
			
			
			<p>
				<label>报表类型：</label>
				${personnelMonthlyBonus1.monthly_type_name }
			</p>
			
			<p>
				<label>项目名称：</label>
				${personnelMonthlyBonus1.project_name}
			</p>
			
			
			<p>
				<label>姓名：</label>
				${personnelMonthlyBonus1.staff_name}
			</p>
			
			
			<p>
				<label>工号：</label>
				${personnelMonthlyBonus1.staff_no}
			</p>
			
			<div class="divider"></div>
			
			
			<p>
				<label>入职时间：</label>
				<fmt:formatDate value="${personnelMonthlyBonus1.join_datetime }" pattern="yyyy-MM-dd"/>
			</p>
			<p>
				<label>奖惩时间：</label>
				<fmt:formatDate value="${personnelMonthlyBonus1.change_time }" pattern="yyyy-MM-dd"/>
			</p>
			<p>
				<label>奖惩金额：</label>
				<fmt:formatNumber value="${personnelMonthlyBonus1.amount }" type="currency" pattern="###,###,##0.00"/>
			</p>
			<p>
				<label>备注：</label>
				${personnelMonthlyBonus1.description }
			</p>

			<c:import url="../common/applyapproveInfo.jsp"></c:import>
		</div>
		<c:import url="../common/applyapproveButton.jsp"></c:import>
	</form>
</div>
