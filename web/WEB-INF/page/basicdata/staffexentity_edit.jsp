<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" action="${webroot}/StaffExEntityAction.do?method=${next_operation}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="id" value="${staffExEntity1.id }"/>
			<input type="hidden" name="staff_id" value="${staffExEntity1.staff_id }"/>
			
			<p>
				<label>员工编号：</label>
				${staffExEntity1.staff_no }<input name="staff_no" type="hidden" value="${staffExEntity1.staff_no }"/>
			</p>			
			
			<p>
				<label>员工名称：</label>
				${staffExEntity1.staff_name }<input name="staff_name" type="hidden" value="${staffExEntity1.staff_name }"/>
			</p>
			
			<p>
				<label>工资模式：</label>
				<input type="radio" name="salary_model" value="0" <c:if test="${staffExEntity1.salary_model == '0' }">checked="checked"</c:if> onClick="javaScript:clkSalaryModel('0')">  原有模式
				        
				<input type="radio" name="salary_model" value="1" <c:if test="${staffExEntity1.salary_model == '1' }">checked="checked"</c:if> onClick="javaScript:clkSalaryModel('1')">  营销模式
		
			</p>	

			<p id="ga" <c:if test="${staffExEntity1.salary_model == null || staffExEntity1.salary_model == '0' }">style="display:none"</c:if> >
				<label>垫资金额：</label>
				<input name="guarantee_amount" class="number required" type="text" size="30" value="<fmt:formatNumber value="${staffExEntity1.guarantee_amount }" type="number" pattern="#####0.00#"/>" />
			</p>					
			
			<p id="ps" <c:if test="${staffExEntity1.salary_model == null || staffExEntity1.salary_model == '0' }">style="display:none"</c:if> >
				<label>上线：</label>
				<input type="hidden" size="2" name="parent1.staff_id" value="${staffExEntity1.parent_id }"/>				
				<input name="parent1.staff_name" class="text" type="text" size="28"  value="${staffExEntity1.parent_name }" readonly="readonly" />
				<a class="btnLook" href="${webroot }/StaffCostAction.do?method=lookup2&ex_staff_id=${staffExEntity1.staff_id }&outsource_staff=0&staff_type=1" lookupGroup="parent1" lookupPk="staff_id" width="950">选择</a>
			</p>
			
			<p id="oe" <c:if test="${staffExEntity1.salary_model == null || staffExEntity1.salary_model == '0' }">style="display:none"</c:if> >
				<label>其它固定支出金额：</label>
				<input name="other_expenditures" class="number required" type="text" size="30" maxlength="30" value="<fmt:formatNumber value="${staffExEntity1.other_expenditures }" type="number" pattern="####0.00#"/>" />
			</p>
			
			<p id="or" <c:if test="${staffExEntity1.salary_model == null || staffExEntity1.salary_model == '0' }">style="display:none"</c:if> >
				<label>其它支出系数：</label>
				<input name="other_ratio" max="1" min="0" class="number required" type="text" size="30" maxlength="30" value="<fmt:formatNumber value="${staffExEntity1.other_ratio }" type="number" pattern="####0.0000#"/>" />
			</p>
			
			<p id="cta" <c:if test="${staffExEntity1.salary_model == null || staffExEntity1.salary_model == '0' }">style="display:none"</c:if> >
				<label>抵税发票金额：</label>
				<input name="credit_tax_amount" max="100000" min="0" class="number required" type="text" size="30" maxlength="30" value="<fmt:formatNumber value="${staffExEntity1.credit_tax_amount }" type="number" pattern="####0.00#"/>" />
			</p>
			
			<p id="pr" <c:if test="${staffExEntity1.salary_model == null || staffExEntity1.salary_model == '0' }">style="display:none"</c:if> >
				<label>平台收费比例：</label>
				<fmt:formatNumber value="${platform_ratio }" type="number" pattern="####0.0000#"/>
			</p>
			

			<div class="divider"></div>			
		
			<p>					
				<textarea name="remarks" cols="55" rows="4" maxlength="500">${staffExEntity1.remarks }</textarea>
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
