<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${webroot}/ReimburseCostsAction.do?method=verifyReimburseCosts" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="reimburse_id" value="${reimburseCosts1.reimburse_id }"/>
			<p>
				<label>项目名称：</label>	
				${reimburseCosts1.project_name}
			</p>
			<p>
				<label>项目编号：</label>
				${reimburseCosts1.project_no}
			</p>
			
			
								
			<p>
				<label>报销人：</label>	
				${reimburseCosts1.staff_name }
			</p>
			<p>
				<label>所属部门：</label>
				${reimburseCosts1.dept_name }	
			</p>
			<p>
				<label>报销类别：</label>	
				${reimburseCosts1.pay_item_name }	
			</p>	
			

			<p>
				<label>报销月份：</label>
				${reimburseCosts1.use_month }
			</p>			
			
			
			<p>
				<label>实际支付日期：</label>
				<fmt:formatDate value="${reimburseCosts1.pay_date }" pattern="yyyy-MM-dd"/>
			</p>
			
			<p>
				<label>报销金额：</label>
				<fmt:formatNumber value="${reimburseCosts1.amount }" type="number" pattern="#,###,##0.00#"/>
			</p>
			
			
			<p>
				<dl class="nowrap">
					<dt>描述：</dt>
					<dd>${reimburseCosts1.description }</dd>
				</dl>
			</p>	
						
			<div class="divider"></div>		
			
	
			<c:import url="../common/applyapproveInfo.jsp"></c:import>
		</div>			
	
	
		<c:import url="../common/applyapproveButton.jsp"></c:import>
	</form>
</div>
