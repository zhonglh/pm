<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" id="add_group_first" action="${webroot }/OtherPersonnelMonthlyBaseAction.do?method=toEditNext" class="pageForm required-validate" onsubmit="return dialogCheckJump(this);">
		<div class="pageFormContent" layoutH="56">	

			<p>
				<label>报表月份：</label>
				<input name="the_month" id="the_month" class="digits required month date"  autocomplete="off"   format="yyyyMM" size="30"  minlength="6" maxlength="6" type="text"  value="${the_month }" readonly/>
			</p>
			
			<p>
				<label>报表类型：</label>
				<select name="monthly_type" style="width:210px">
				<option value="1" <c:if test="${'1' == param.monthly_type }">selected</c:if>><spring:message code="personnel.monthly.1"/></option>
				<option value="2" <c:if test="${'2' == param.monthly_type }">selected</c:if>><spring:message code="personnel.monthly.2"/></option>
				<option value="3" <c:if test="${'3' == param.monthly_type }">selected</c:if>><spring:message code="personnel.monthly.3"/></option>
				<option value="4" <c:if test="${'4' == param.monthly_type }">selected</c:if>><spring:message code="personnel.monthly.4"/></option>
				<option value="5" <c:if test="${'5' == param.monthly_type }">selected</c:if>><spring:message code="personnel.monthly.5"/></option>
				<option value="6" <c:if test="${'6' == param.monthly_type }">selected</c:if>><spring:message code="personnel.monthly.6"/></option>
				<option value="7" <c:if test="${'7' == param.monthly_type }">selected</c:if>><spring:message code="personnel.monthly.7"/></option>
				<option value="8" <c:if test="${'8' == param.monthly_type }">selected</c:if>><spring:message code="personnel.monthly.8"/></option>
				<option value="9" <c:if test="${'9' == param.monthly_type }">selected</c:if>><spring:message code="personnel.monthly.9"/></option>
				</select>
			</p>
			
			<p>
				<label>姓名：</label>
				<input type="hidden" size="2" name="staff.staff_id" />
				<input name="staff.staff_name" id="staff_name" class="text required" size="30" type="text"  readonly />
				<a class="btnLook" href="${webroot }/OtherStaffAction.do?method=lookup" lookupGroup="staff" lookupPk="staff_id" width="970">选择</a>
			</p>
			
			<div class="divider"></div>


		</div>
		<div class="formBar">
			<ul>	
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">下一步</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
