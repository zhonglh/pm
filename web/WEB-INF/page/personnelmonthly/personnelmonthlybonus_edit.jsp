<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" action="${webroot}/PersonnelMonthlyBonusAction.do?method=${next_operation}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="id" value="${personnelMonthlyBonus1.id }"/>
			
			
			<p>
				<label>报表月份：</label>
				<input name="the_month" id="the_month" class="required" format="yyyyMM" size="30"  minlength="6" maxlength="6" type="text"  value="${personnelMonthlyBonus1.the_month }" readonly/>
			</p>
			
			
			<p>
				<label>报表类型：</label>
				<input name="monthly_type" id="monthly_type" type="hidden"  value="${personnelMonthlyBonus1.monthly_type }"/>
				<input name="monthly_type_name" id="monthly_type_name" class="required" size="30"  type="text"  value="${personnelMonthlyBonus1.monthly_type_name }" readonly/>
			</p>
			
			<p>
				<label>项目名称：</label>
				<input name="project_id"	type="hidden" value="${personnelMonthlyBonus1.project_id}"	 />		
				<input name="project_name" class="text" type="text" size="30" maxlength="60" value="${personnelMonthlyBonus1.project_name}" readonly="true" />
			</p>
			
			
			<p>
				<label>姓名：</label>
				<input type="hidden" size="2" name="staff.staff_id" value="${personnelMonthlyBonus1.staff_id}"	 />	
				<input name="staff.staff_name" id="staff_name" class="text required" size="30" type="text"  readonly value="${personnelMonthlyBonus1.staff_name}"	 />	
				<a class="btnLook" href="${webroot }/StaffCostAction.do?method=lookup" lookupGroup="staff" lookupPk="staff_id" width="970">选择</a>
			</p>
			
			
			<p>
				<label>工号：</label>
				<input name="staff.staff_no" id="staff_no" class="text required" size="30" type="text"  readonly value="${personnelMonthlyBonus1.staff_no}"	 />	
			</p>
			
			<div class="divider"></div>
			
			<p>
				<label>入职时间：</label>
				<input name="join_datetime" class="required" type="text" size="30" maxlength="30" value="<fmt:formatDate value="${personnelMonthlyBonus1.join_datetime }" pattern="yyyy-MM-dd"/>"  readonly />
			</p>
			<p>
				<label>奖惩时间：</label>
				<input name="change_time" class="date required" autocomplete="off"   type="text" size="30" maxlength="30" value="<fmt:formatDate value="${personnelMonthlyBonus1.change_time }" pattern="yyyy-MM-dd"/>" />
			</p>
			<p>
				<label>奖惩金额：</label>
				<input name="amount" class="number required" type="text" size="30" value="<fmt:formatNumber value="${personnelMonthlyBonus1.amount }" type="number" pattern="####0.00#"/>" />
			</p>
			<p>
				<label>备注：</label>
				<input name="description" class="text" type="text" size="30" maxlength="30" value="${personnelMonthlyBonus1.description }" />
			</p>
			<div class="divider"></div>
			<p>
				<label>制单人：</label>  ${personnelMonthlyBonus1.build_username }
			</p>
			<p>
				<label>制单日期：</label> <fmt:formatDate value="${personnelMonthlyBonus1.build_datetime }" pattern="yyyy-MM-dd"/> 
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
