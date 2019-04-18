<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" action="${webroot}/OtherPersonnelMonthlyReserveFundAction.do?method=${next_operation}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="id" value="${personnelMonthlyReserveFund1.id }"/>
			
			
			<p>
				<label>报表月份：</label>
				<input name="the_month" id="the_month" class="required" format="yyyyMM" size="30"  minlength="6" maxlength="6" type="text"  value="${personnelMonthlyReserveFund1.the_month }" readonly/>
			</p>
			
			
			<p>
				<label>报表类型：</label>
				<input name="monthly_type" id="monthly_type" type="hidden"  value="${personnelMonthlyReserveFund1.monthly_type }"/>
				<input name="monthly_type_name" id="monthly_type_name" class="required" size="30"  type="text"  value="${personnelMonthlyReserveFund1.monthly_type_name }" readonly/>
			</p>
			
			<p>
				<label>部门名称：</label>
				<input name="dept_id"	type="hidden" value="${personnelMonthlyReserveFund1.dept_id}"	 />
				<input name="dept_name" class="text" type="text" size="30" maxlength="60" value="${personnelMonthlyReserveFund1.dept_name}" readonly="true" />
			</p>
			
			
			<p>
				<label>姓名：</label>
				<input type="hidden" size="2" name="staff.staff_id" value="${personnelMonthlyReserveFund1.staff_id}"	 />	
				<input name="staff.staff_name" id="staff_name" class="text required" size="30" type="text"  readonly value="${personnelMonthlyReserveFund1.staff_name}"	 />	
				<a class="btnLook" href="${webroot }/StaffCostAction.do?method=lookup" lookupGroup="staff" lookupPk="staff_id" width="970">选择</a>
			</p>
			
			
			<p>
				<label>工号：</label>
				<input name="staff.staff_no" id="staff_no" class="text required" size="30" type="text"  readonly value="${personnelMonthlyReserveFund1.staff_no}"	 />	
			</p>
			
			<div class="divider"></div>
			
			<p>
				<label>社保种类：</label>	
				<input type="hidden"  name="grade.insurance_grade_id" value="${personnelMonthlyReserveFund1.insurance_grade_id }"/>	
				<input name="grade.insurance_radix" class="text required" type="text" size="30"  
				value="${personnelMonthlyReserveFund1.insurance_radix }" 
				readonly="readonly" lookupPk="insurance_grade_id" 
				suggestFields="insurance_radix,reservefund_bypersonal,reservefund_bypcompany,base_cardinal" 
				suggestUrl="${webroot }/InsuranceGradeAction.do?method=lookup" lookupGroup="grade"/>
			</p>
			
				
			<p>
				<label>社保缴纳单位：</label>
				<input name="securty_unit" class="text" type="text" size="30" value="${personnelMonthlyReserveFund1.securty_unit }" />
			</p>
			
			<div class="divider"></div>
			
			<p>
				<label>个人：</label>
				<input name="grade.reservefund_bypersonal" class="number" readonly type="text" size="30" maxlength="10" value="<fmt:formatNumber value="${personnelMonthlyReserveFund1.reservefund_bypersonal }" type="number" pattern="####0.00#"/>" />
			</p>
			
			<p>
				<label>单位：</label>
				<input name="grade.reservefund_bypcompany" class="number" readonly type="text" size="30" maxlength="10" value="<fmt:formatNumber value="${personnelMonthlyReserveFund1.reservefund_bypcompany }" type="number" pattern="####0.00#"/>" />
			</p>
			
			<p>
				<label>基数：</label>
				<input name="grade.base_cardinal" class="number" readonly type="text" size="30" maxlength="10" value="<fmt:formatNumber value="${personnelMonthlyReserveFund1.base_cardinal }" type="number" pattern="####0.00#"/>" />
			</p>
			
			
			<div class="divider"></div>
			<p>
				<label>制单人：</label>  ${personnelMonthlyReserveFund1.build_username }
			</p>
			<p>
				<label>制单日期：</label> <fmt:formatDate value="${personnelMonthlyReserveFund1.build_datetime }" pattern="yyyy-MM-dd"/> 
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
