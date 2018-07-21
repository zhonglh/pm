<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>



<div class="pageContent">
	<form method="post" action="${webroot }/ParamExtendAction.do?method=save" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
	
		<input type="hidden"   name="param_id" id="param_id" value="${paramExtend1.param_id}" />
		<input type="hidden"   name="group1" id="group1" value="${paramExtend1.group1}" />
		<input type="hidden"   name="group2" id="group2" value="${paramExtend1.group2}" />
	
		<c:if test="${paramExtend1.group1=='salary' &&  paramExtend1.group2=='sick_leave_salary'}">
			<div class="pageFormContent" layoutH="56">
				
				<div class="sick_leave_salary">
					<p>
						<label>类型</label>
						<select id="type1" name="type1" onchange="changeType1(this);">
							<option <c:if test="${paramExtend1.type1=='2' }">selected="selected"</c:if> value="2">按照最低工资标准</option>
							<option <c:if test="${paramExtend1.type1=='1' }">selected="selected"</c:if> value="1">按照工资总数</option>
						</select>
					</p>
					
					<p>
						<label>最低工资标准</label>
						<input type="text" class="number required" size="30"  name="realVal" id="realVal" value="<fmt:formatNumber value="${paramExtend1.realVal }" type="number" pattern="####0.00#"/>"/>
					</p>
					
					
					<p>
						<label>比例(%)</label>
						<input type="text" class="number required" size="30"  name="processor" id="processor" value="<fmt:formatNumber value="${paramExtend1.processor*100 }" type="number" pattern="####0.00#"/>"/>
					</p>
				</div>
			</div>
			
			
			
			
		</c:if>
		
		
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


</div>