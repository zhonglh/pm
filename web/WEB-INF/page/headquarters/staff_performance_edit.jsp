<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" action="${webroot}/StaffPerformanceAction.do?method=${next_operation}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="id" value="${staffPerformance1.id }"/>



			<p>
				<label>月份：</label>
				<input name="the_month" id="the_month" class="date required" autocomplete="off"   format="yyyyMM" size="30"  minlength="6" maxlength="6" type="text" <c:if test="${staffPerformance1.the_month>0}">value="${staffPerformance1.the_month }"</c:if>  readonly/>
			</p>


			<p>
				<label>员工：</label>
				<input type="hidden" size="2" name="staff.staff_id" value="${staffPerformance1.staff_id }"/>
				<input type="hidden" size="2" name="staff.staff_no" value="${staffPerformance1.staff_no }"/>
				<input name="staff.staff_name" class="required text" type="text" size="28"  value="${staffPerformance1.staff_name }" readonly />
				<a class="btnLook" href="${webroot }/OtherStaffAction.do?method=lookup" lookupGroup="staff" lookupPk="staff_id" width="970">选择</a>
			</p>


			<p>
				<label>所属部门：</label>
				<input name="staff.dept_id" class="text" type="hidden"  value="${staffPerformance1.dept_id }" />
				<input name="staff.dept_name" class="text required" type="text" size="30"  value="${staffPerformance1.dept_name }" readonly="readonly" lookupPk="dept_id"
					   suggestFields="dept_name" suggestUrl="${webroot }/DeptAction.do?method=lookup" lookupGroup="staff" />
			</p>


			<p>
				<label>绩效工资：</label>
				<input name="performance_salary" class="number required" max="1000000"  type="text" size="30" value="<fmt:formatNumber value="${staffPerformance1.performance_salary }" type="number" pattern="####0.00#"/>" />
			</p>

			<div class="divider"></div>
			<p>
				<label>制单人：</label>  ${staffPerformance1.build_username }
			</p>
			<p>
				<label>制单日期：</label> <fmt:formatDate value="${staffPerformance1.build_datetime }" pattern="yyyy-MM-dd"/>
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
