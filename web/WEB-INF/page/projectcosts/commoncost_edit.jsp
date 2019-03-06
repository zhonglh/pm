<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" action="${webroot}/CommonCostAction.do?method=${next_operation}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="id" value="${commoncost1.id }"/>



			<p>
				<label>月份：</label>
				<input name="use_month" class="number required" type="text" size="30" value="<fmt:formatNumber value="${commoncost1.use_month }" type="number" pattern="####0"/>" />
			</p>

			<p>
				<label>费用类别：</label>
				<input type="hidden" name="rai.id" value="${commoncost1.pay_item_id }"/>
				<input name="rai.dic_data_name" class="required" type="text" size="28"  value="${commoncost1.pay_item_name }"
					   readonly="readonly" lookupPk="id" suggestFields="dic_data_name" lookupGroup="rai"
					   suggestUrl="${webroot }/DicDataAction.do?method=lookup&dic_type_id=COMMON_COST_ITEM" />
				<a class="btnAdd" href="${webroot}/DicDataAction.do?method=toEdit&dic_type_id=COMMON_COST_ITEM" mask="true" width="500" height="260" rel="add_ReimburseItem"  target="dialog"><span>添加公共费用类别</span></a>

			</p>

			<p>
				<label>金额：</label>
				<input name="amount" class="number required" type="text" size="30" min="0.01" maxlength="10" value="<fmt:formatNumber value="${commoncost1.amount }" type="number" pattern="####0.00#"/>" />
			</p>

			<p>
				<label>支付日期：</label>
				<input name="pay_date" class="date required" type="text" size="30" value="<fmt:formatDate value="${commoncost1.pay_date }" pattern="yyyy-MM-dd"/>" />
			</p>



			<p>
				<label>报销人：</label>
				<input type="hidden" size="2" name="staff.staff_id" value="${commoncost1.staff_id }"/>
				<input type="hidden" size="2" name="staff.staff_no"  value="${commoncost1.staff_no }"/>
				<input name="staff.staff_name" class="text" type="text" size="28" readonly value="${commoncost1.staff_name }" />
				<a class="btnLook" href="${webroot }/OtherStaffAction.do?method=lookup" lookupGroup="staff" lookupPk="staff_id" width="970">选择</a>
			</p>


			<p>
				<dl class="nowrap">
					<dt>描述：</dt>
					<dd><textarea name="description" cols="95" rows="4" maxlength="300">${commoncost1.description }</textarea></dd>
				</dl>
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
