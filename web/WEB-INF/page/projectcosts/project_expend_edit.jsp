<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${webroot}/ProjectExpendAction.do?method=${next_operation}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="project_expend_id" value="${projectExpend1.project_expend_id }"/>
			<p>
				<label>项目名称：</label>
				<input name="project.project_id"	type="hidden" value="${projectExpend1.project_id}"	 />		
				<input name="project.project_name" class="required" type="text" size="28" maxlength="60" value="${projectExpend1.project_name}" readonly="true" />
					<a class="btnLook" href="${webroot }/ProjectAction.do?method=lookup" lookupGroup="project" lookupPk="project_id" width="950">选择项目</a>	
			</p>
			<p>
				<label>项目编号：</label>
				<input name="project.project_no" class="text" type="text" size="30" maxlength="30" value="${projectExpend1.project_no}" readonly="true"/>
			</p>
			
			
			
			<p>
				<label>应付金额：</label>
				<input name="amount" class="number required" type="text" size="30" value="<fmt:formatNumber value="${projectExpend1.amount }" type="number" pattern="####0.00#"/>" />
			</p>
			

			<p>
				<label>支出对象：</label>
					<select name="expend_object" class="required combox">
					<option value="1" <c:if test="${'1' == projectExpend1.expend_object }">selected</c:if>><spring:message code="expend.object.1"/></option>
					<option value="2" <c:if test="${'2' == projectExpend1.expend_object }">selected</c:if>><spring:message code="expend.object.2"/></option>
					</select>
			</p>			

			<p>
				<label>分包商名称：</label>
				<input name="sub_contractor_name" class="text required" type="text" size="30" maxlength="30" value="${projectExpend1.sub_contractor_name}" />
			</p>
			

			<p>
				<label>分包合同编号：</label>
				<input name="sub_contractor_no" class="text required" type="text" size="30" maxlength="30" value="${projectExpend1.sub_contractor_no}" />
			</p>						
			
			<p>
				<label>收到的发票号：</label>
				<input name="invoiceno" class="text" type="text" size="30" maxlength="30" value="${projectExpend1.invoiceno}" />
			</p>	
			

			<p>
				<label>应付月份：</label>
				<input name="use_month" class="digits required date month" format="yyyyMM" minlength="6" maxlength="6" type="text" size="30" value="${projectExpend1.use_month }" />
			</p>	
			
			
			
			
			
			
			<p>
				<dl class="nowrap">
					<dt>描述：</dt>
					<dd><textarea name="description" cols="95" rows="4" maxlength="300">${projectExpend1.description }</textarea></dd>
				</dl>
			</p>	
						
			<div class="divider"></div>
			<p>
				<label>制单人：</label>  ${projectExpend1.build_username }
			</p>
			<p>
				<label>制单日期：</label> <fmt:formatDate value="${projectExpend1.build_datetime }" pattern="yyyy-MM-dd"/> 
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
