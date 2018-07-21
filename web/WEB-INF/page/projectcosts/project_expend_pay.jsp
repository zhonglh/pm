<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${webroot}/ProjectExpendAction.do?method=${next_operation}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
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
				<label>收到的发票号：</label>
				<input name="invoiceno" class="text required" type="text" size="30" value="${projectExpend1.invoiceno}" />
			</p>
			
			<p>
				<label>应付金额：</label>
				${projectExpend1.amount }
			</p>
			
						
			<p>
				<label>实付金额：</label>
				<input name="pay_amount" class="number required" type="text" size="30" value="<fmt:formatNumber value="${projectExpend1.pay_amount }" type="number" pattern="####0.00#"/>" />
			</p>
			
			
			<p>
				<label>实际支付日期：</label>
				<input name="pay_date" class="date required" type="text" size="30" value="<fmt:formatDate value="${project1.pay_date }" pattern="yyyy-MM-dd"/>" readonly="readonly" />
			</p>
			

		</div>
		
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">付款</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
