<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" action="${webroot}/MarketSetsAction.do?method=${next_operation}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="id" value="${marketSets1.id }"/>
			
			
			<p>
				<label>平台收费比例：</label>
				<input name="platform_ratio" class="number required" max="1" min="0"  type="text" size="30" value="<fmt:formatNumber value="${marketSets1.platform_ratio }" type="number" pattern="####0.0000#"/>" />
			</p>
			
			
			<div class="divider"></div>
			
			<p>
				<label>影响级数：</label>
				<input name="affect_level_number" class="digits required" max="${ MAX_LEVEL }" min="0"  type="text" size="30" value="<fmt:formatNumber value="${marketSets1.affect_level_number }" type="number" pattern="####0"/>" />
			</p>
			<p>
				<label>收费比例：</label>
				<input name="affect_ratio" class="number required" max="1" min="0"  type="text" size="30" value="<fmt:formatNumber value="${marketSets1.affect_ratio }" type="number" pattern="####0.0000#"/>" />
			</p>
			<p>
				<label>收费策略：</label>				
				<input type="radio" name="affect_strategy" value="1" <c:if test="${marketSets1.affect_strategy == '1' }">checked="checked"</c:if> onClick="javaScript:clkAffectFixed()">  固定
				        
				<input type="radio" name="affect_strategy" value="2" <c:if test="${marketSets1.affect_strategy == '2' }">checked="checked"</c:if> onClick="javaScript:clkAffectDecline()">  递减
			</p>
			
			<p id="adf" <c:if test="${marketSets1.affect_strategy == '1' }">style="display:none"</c:if> >
				<label>递减系数：</label>
				<input name="affect_decline_factor" max="1" min="0" class="number required" type="text" size="30" value="<fmt:formatNumber value="${marketSets1.affect_decline_factor }" type="number" pattern="####0.0000#"/>" />
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
