<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="${webroot}/ReimburseCostsAction.do?method=${next_operation}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="reimburse_id" value="${reimburseCosts1.reimburse_id }"/>
			<p>
				<label>项目名称：</label>
				<input name="project.project_id"	type="hidden" value="${reimburseCosts1.project_id}"	 />	
				<input name="project.project_name" class="required" type="text" size="28" maxlength="60" value="${reimburseCosts1.project_name}" readonly="true" />
					<a class="btnLook" href="${webroot }/ProjectAction.do?method=lookup" lookupGroup="project" lookupPk="project_id" width="950">选择项目</a>	
			</p>
			<p>
				<label>项目编号：</label>
				<input name="project.project_no" class="text required" type="text" size="30" maxlength="30" value="${reimburseCosts1.project_no}" readonly="true"/>
			</p>
			
			
			<p>
				<label>所属部门：</label>
				<input name="project.deptid" class="text" type="hidden"  value="${reimburseCosts1.dept_id }" />
				<input name="project.deptname" class="text required" type="text" size="30"  value="${reimburseCosts1.dept_name }" readonly="readonly" />		
			</p>
			
			
								
			<p>
				<label>报销人：</label>	
				<input type="hidden" size="2" name="staff.staff_id" value="${reimburseCosts1.staff_id }"/>
				<input type="hidden" size="2" name="staff.staff_no" value="${reimburseCosts1.staff_no }"/>
				<input name="staff.staff_name" class="text" type="text" size="28"  value="${reimburseCosts1.staff_name }" />
				<a class="btnLook" href="${webroot }/StaffCostAction.do?method=lookup2" lookupGroup="staff" lookupPk="staff_id" width="970">选择</a>
			</p>
			
			<p>
				<label>报销类别：</label>
				<input type="hidden" name="rai.id" value="${reimburseCosts1.pay_item_id }"/>
				<input name="rai.dic_data_name" class="text" type="text" size="28"  value="${reimburseCosts1.pay_item_name }" 
					readonly="readonly" lookupPk="id" suggestFields="dic_data_name" lookupGroup="rai"
					suggestUrl="${webroot }/DicDataAction.do?method=lookup&dic_type_id=REIMBURSE_ITEM" />	
				<a class="btnAdd" href="${webroot}/DicDataAction.do?method=toEdit&dic_type_id=REIMBURSE_ITEM" mask="true" width="500" height="260" rel="add_ReimburseItem"  target="dialog"><span>添加报销类别</span></a>	
						
			</p>	
			
			<p>
				<label>报销月份：</label>
				<input name="use_month" class="digits required month date" format="yyyyMM" minlength="6" maxlength="6" type="text" size="30" value="${reimburseCosts1.use_month }" />
			</p>
			
			
			<p>
				<label>实际支付日期：</label>
				<input name="pay_date" class="date required"  type="text" size="30" value="<fmt:formatDate value="${reimburseCosts1.pay_date }" pattern="yyyy-MM-dd"/>" />
			</p>
			
			
			<p>
				<label>报销金额：</label>
				<input name="amount" class="number required" type="text" size="30" value="<fmt:formatNumber value="${reimburseCosts1.amount }" type="number" pattern="####0.00#"/>" />
			</p>
			
			
			<p>
				<dl class="nowrap">
					<dt>描述：</dt>
					<dd><textarea name="description" cols="95" rows="4" maxlength="300">${reimburseCosts1.description }</textarea></dd>
				</dl>
			</p>	
						
			<div class="divider"></div>
			<p>
				<label>制单人：</label>  ${reimburseCosts1.build_username }
			</p>
			<p>
				<label>制单日期：</label> <fmt:formatDate value="${reimburseCosts1.build_datetime }" pattern="yyyy-MM-dd"/> 
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
