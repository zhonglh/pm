<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${webroot}/ProjectExpendPayAction.do?method=${next_operation}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="id" value="${projectExpendpay1.id }"/>
			<input type="hidden" name="project_expend_id" value="${projectExpend1.project_expend_id }"/>
			<p>
				<label>项目名称：</label>
				<input name="project.project_name" class="text" type="text" size="30" maxlength="60" value="${projectExpend1.project_name}" readonly="true" />
			</p>
			<p>
				<label>项目编号：</label>
				<input name="project.project_no" class="text" type="text" size="30" maxlength="30" value="${projectExpend1.project_no}" readonly="true"/>
			</p>
			

			
			<p>
				<label>应付金额：</label>
				${projectExpend1.amount }
			</p>

			<p>
				<label>已付金额累计：</label>
				${projectExpend1.pay_amount }
			</p>
			<p>
				<label>进项税额累计：</label>
				${projectExpend1.tax_deduction }
			</p>
			<p>
				<label>实际成本累计：</label>
				${projectExpend1.actual_cost }
			</p>


			<div class="divider"></div>


			<p>
				<label>本次收到的发票：</label>
				<input name="invoiceno" class="text" type="text" size="30" value="${projectExpendpay1.invoiceno }" />
			</p>
						
			<p>
				<label>本次实付金额：</label>
				<input name="pay_amount" class="number required" type="text" size="30" onchange="computeActualCost(this.form)" value="<fmt:formatNumber value="${projectExpendpay1.pay_amount }"  type="number" pattern="####0.00#"/>" />
			</p>


			<p>
				<label>本次进项税额：</label>
				<input name="tax_deduction" class="number required" min="0.00" type="text" size="30" onchange="computeActualCost(this.form)" value="<fmt:formatNumber value="${projectExpendpay1.tax_deduction }" type="number" pattern="####0.00#"/>" />

			</p>
			<p>
				<label>本次实际成本：</label>
				<input name="actual_cost" class="number required" readonly type="text" size="30" value="<fmt:formatNumber value="${projectExpendpay1.actual_cost }" type="number" pattern="####0.00#"/>" />

			</p>
			
			
			<p>
				<label>实际支付日期：</label>
				<input name="pay_date" class="date required" autocomplete="off"   type="text" size="30" value="<fmt:formatDate value="${projectExpendpay1.pay_date }" pattern="yyyy-MM-dd"/>" readonly="readonly" />
			</p>


			<p>
				<label>备注：</label>
				<input name="description" class="text" type="text" size="30" value="${projectExpendpay1.description }" />
			</p>
			

		</div>
		
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存付款信息</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
