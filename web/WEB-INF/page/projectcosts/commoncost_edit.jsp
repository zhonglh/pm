<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" action="${webroot}/CommonCostAction.do?method=${next_operation}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="id" value="${commoncost1.id }"/>
			<p>
				<label>项目名称：</label>
				<input name="project.project_id"	type="hidden" value="${commoncost1.project_id}"	 />		
				<input name="project.project_name" class="required" type="text" size="28" maxlength="60" value="${commoncost1.project_name}" readonly="true" />
					<a class="btnLook" href="${webroot }/ProjectAction.do?method=lookup" lookupGroup="project" lookupPk="project_id" width="950">选择项目</a>	
			</p>
			<p>
				<label>项目编号：</label>
				<input name="project.project_no" class="text" type="text" size="30" maxlength="30" value="${commoncost1.project_no}" readonly="true"/>
			</p>
			<p>
				<label>支付月份：</label>
				<input name="pay_date" class="date required" type="text" size="30" value="<fmt:formatDate value="${commoncost1.pay_date }" pattern="yyyy-MM-dd"/>" />
			</p>
			<p>
				<label>月份：</label>
				<input name="use_month" class="number required" type="text" size="30" value="<fmt:formatNumber value="${commoncost1.use_month }" type="number" pattern="####0"/>" />
			</p>
			<p>
				<label>金额：</label>
				<input name="amount" class="number required" type="text" size="30" value="<fmt:formatNumber value="${commoncost1.amount }" type="number" pattern="####0.00#"/>" />
			</p>
			<p>
				<label>备注：</label>
				<input name="description" class="date required" type="text" size="30" maxlength="30" value="${commoncost1.description }" />
			</p>
			<div class="divider"></div>
			<p>
				<label>制单人：</label>  ${commoncost1.build_username }
			</p>
			<p>
				<label>制单日期：</label> <fmt:formatDate value="${commoncost1.build_datetime }" pattern="yyyy-MM-dd"/> 
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
