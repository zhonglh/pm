<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${webroot }/InvoiceManageAction.do?method=${next_operation}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">			
				
				<input name="invoice_id" class="text" type="hidden" size="30" value="${invoice1.invoice_id}"/>
			<p>
				<label>项目名称：</label>
				<input name="project.project_id"	type="hidden" value="${invoice1.project_id}"	 />		
				<input name="project.project_name" class="required" type="text" size="28" maxlength="60" value="${invoice1.project_name}" readonly="true" />
					<a class="btnLook" href="${webroot }/ProjectAction.do?method=lookup" lookupGroup="project" lookupPk="project_id" width="950">选择项目</a>	
			</p>
			<p>
				<label>项目编号：</label>
				<input name="project.project_no" class="text" type="text" size="30" maxlength="30" value="${invoice1.project_no}" readonly="true"/>
			</p>		
					
			
			<p>
				<label>发票编号：</label>
				<input name="invoice_no" class="text required" type="text" size="30" value="${invoice1.invoice_no}"/>
			</p>			
			
			
			<p>
				<label>发票抬头：</label>
				<!-- 发票抬头就是项目客户名称 -->
				<input name="project.project_client_name"  class="text" type="text" size="30" value="${invoice1.invoice_title}" />
			</p>			
			
			
			
			<p>
				<label>开票日期：</label>
				<input name="invoice_date"  class="date" autocomplete="off"  type="text" size="30" value="<fmt:formatDate value="${invoice1.invoice_date }" pattern="yyyy-MM-dd"/>" readonly="readonly" />
			</p>	

		

			<p>
				<label>发票内容：</label>
				<input type="hidden" name="content_dic.id" value="${invoice1.invoice_content }"/>
				<input name="content_dic.dic_data_name" class="text" type="text" size="28"  value="${invoice1.invoice_content_name }" 
					readonly="readonly" lookupPk="id" suggestFields="dic_data_name" lookupGroup="content_dic"
					suggestUrl="${webroot }/DicDataAction.do?method=lookup&dic_type_id=INVOICE_CONTENT" />					
				<a class="btnAdd" href="${webroot}/DicDataAction.do?method=toEdit&dic_type_id=INVOICE_CONTENT" mask="true" width="500" height="260" rel="add_INVOICE_CONTENT"  target="dialog"><span>添加</span></a>
			</p>
			
			
			
			<p>
				<label>发票类型：</label>
				<select name="invoice_type">
					<option value="1" <c:if test="${'1' == invoice1.invoice_type }">selected</c:if> >增值税普通发票</option>
					<option value="2" <c:if test="${'2' == invoice1.invoice_type }">selected</c:if> >增值税专用发票</option>
				</select>
			</p>		
			
			

			<p>
				<label>是否免税：</label>
				<select name="is_exemption_tax">
					<option value="1" <c:if test="${'1' == invoice1.is_exemption_tax }">selected</c:if>>是</option>
					<option value="0" <c:if test="${'0' == invoice1.is_exemption_tax }">selected</c:if>>否</option>
				</select>
			</p>		
			<p>
				<label>发票金额：</label>
				<input name="invoiceno_amount" class="number required" type="text" size="30"  value="<fmt:formatNumber value="${invoice1.invoiceno_amount }" type="number" pattern="####0.00#"/>" />
			</p>
			
			<p>
				<label>税率：</label>				
				<input type="text" class="number required" name="tax_rate"  size="28" value="${invoice1.tax_rate }"/>(%)
			</p>											
			
			<p>
				<label>开票人员：</label>
				<input type="hidden" size="2" name="otherstaff.staff_id" value="${invoice1.invoice_staff_id }"/>
				<input name="otherstaff.staff_name" class="text" type="text" size="28"  value="${invoice1.invoice_staff_name }" readonly="readonly" />
				<a class="btnLook" href="${webroot }/OtherStaffAction.do?method=lookup" lookupGroup="otherstaff" lookupPk="staff_id" width="950">选择</a>			
			</p>												
			
			<p>
				<label>发票接收人员：</label>
				<input name="receive_staff.staff_id" class="text" type="hidden" size="30" value="${invoice1.invoice_receive}" />
				<input name="receive_staff.staff_name" class="text" type="text" size="28"  value="${invoice1.invoice_receive_name }" readonly="readonly" />
				<a class="btnLook" href="${webroot }/OtherStaffAction.do?method=lookup" lookupGroup="receive_staff" lookupPk="staff_id" width="950">选择</a>	
			</p>	
			
			
			<p>
				<label>月度结算单：</label>
				<input type="hidden" id="monthly_statement_id" name="monthlyStatement.monthly_statement_id" value="${invoice1.monthly_statement_id }"/>
				<input id="monthly_statement_name" name="monthlyStatement.monthly_statement_name" class="text" type="text" size="24"  value="${invoice1.monthly_statement_name }<spring:message code="statement.type.${invoice1.statement_type }"/>" readonly="readonly" />
				<a class="btnLook" href="${webroot }/MonthlyStatementAction.do?method=search" lookupGroup="monthlyStatement" lookupPk="monthly_statement_id" width="950">选择</a>
				<a class="btnDelSelf" onclick="cancelText('monthly_statement_id','monthly_statement_name');return false;"><span>删除</span></a>
			</p>	
			
			<p>
				<label>合同编号：</label>
				<input name="contract.id" class="text" type="hidden" size="30" value="${invoice1.contract_id}" />
				<input name="contract.contract_no" class="text" type="text" size="28"  value="${invoice1.contract_no }" readonly="readonly" />
				<a class="btnLook" href="${webroot }/ContractAction.do?method=lookup" lookupGroup="contract" lookupPk="id" width="950">选择</a>	
			</p>	
									
									

			<div class="divider"></div>			
		
			<dl class="nowrap">
				<dt>备注：</dt>				
				<dd><textarea name="description" cols="95" rows="4" maxlength="300">${invoice1.description }</textarea></dd>
			</dl>

			<div class="divider"></div>
	
			<p>
				<label>制单人：</label>  ${invoice1.build_username }
			</p>
			<p>
				<label>制单日期：</label> <fmt:formatDate value="${invoice1.build_datetime }" pattern="yyyy-MM-dd"/> 
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
