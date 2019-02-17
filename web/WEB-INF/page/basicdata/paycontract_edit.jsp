<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" action="${webroot}/PayContractAction.do?method=${next_operation}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="id" value="${payContract1.id }"/>
			<p>
				<label>项目名称：</label>
				<input name="project.project_id"	type="hidden" value="${payContract1.project_id}"	 />		
				<input name="project.project_name" class="required" type="text" size="28" maxlength="60" value="${payContract1.project_name}" readonly="true" />
					<a class="btnLook" href="${webroot }/ProjectAction.do?method=lookup" lookupGroup="project" lookupPk="project_id" width="950">选择项目</a>	
			</p>
			<p>
				<label>项目编号：</label>
				<input name="project.project_no" class="text" type="text" size="30" maxlength="30" value="${payContract1.project_no}" readonly="true"/>
			</p>
			<p>
				<label>合同编号：</label>
				<input name="contract_no" class="date required" type="text" size="30" maxlength="30" value="${payContract1.contract_no }" />
			</p>
			<p>
				<label>公司名称：</label>
				<input name="company_name" class="date required" type="text" size="30" maxlength="30" value="${payContract1.company_name }" />
			</p>
			<p>
				<label>执行合同：</label>
				<input name="exec_contract" class="date required" type="text" size="30" maxlength="30" value="${payContract1.exec_contract }" />
			</p>
			<p>
				<label>金额：</label>
				<input name="amount" class="number required" type="text" size="30" value="<fmt:formatNumber value="${payContract1.amount }" type="number" pattern="####0.00#"/>" />
			</p>
			<p>
				<label>合同签订日期：</label>
				<input name="signing_date" class="date required" type="text" size="30" value="<fmt:formatDate value="${payContract1.signing_date }" pattern="yyyy-MM-dd"/>" />
			</p>
			<p>
				<label>合同有效日期1：</label>
				<input name="validity_date1" class="date required" type="text" size="30" value="<fmt:formatDate value="${payContract1.validity_date1 }" pattern="yyyy-MM-dd"/>" />
			</p>
			<p>
				<label>合同有效日期2：</label>
				<input name="validity_date2" class="date required" type="text" size="30" value="<fmt:formatDate value="${payContract1.validity_date2 }" pattern="yyyy-MM-dd"/>" />
			</p>
			<p>
				<label>提交日期：</label>
				<input name="submit_date" class="date required" type="text" size="30" value="<fmt:formatDate value="${payContract1.submit_date }" pattern="yyyy-MM-dd"/>" />
			</p>
			<p>
				<label>客户联系人：</label>
				<input name="client_linkman" class="date required" type="text" size="30" maxlength="30" value="${payContract1.client_linkman }" />
			</p>
			<p>
				<label>邮箱/电话：</label>
				<input name="email_phone" class="date required" type="text" size="30" maxlength="30" value="${payContract1.email_phone }" />
			</p>
			<p>
				<label>合同份数：</label>
				<input name="contract_number" class="number required" type="text" size="30" value="<fmt:formatNumber value="${payContract1.contract_number }" type="number" pattern="####0"/>" />
			</p>
			<p>
				<label>备注：</label>
				<input name="description" class="date required" type="text" size="30" maxlength="30" value="${payContract1.description }" />
			</p>
			<div class="divider"></div>
			<p>
				<label>制单人：</label>  ${payContract1.build_username }
			</p>
			<p>
				<label>制单日期：</label> <fmt:formatDate value="${payContract1.build_datetime }" pattern="yyyy-MM-dd"/> 
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
